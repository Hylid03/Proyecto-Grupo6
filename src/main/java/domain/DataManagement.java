package domain;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class DataManagement {
    private Node document;
    private String filePath;

    public DataManagement(String filePath) throws Exception {
        this.filePath = filePath;
        loadDocument();
    }

    private void loadDocument() throws Exception {
        File xmlFile = new File(filePath);
        if (!xmlFile.exists()) {
            document = new Node("root");
            saveDocument();
        } else {
            // Use a basic implementation to simulate loading an XML document into custom Node structure
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docBuilder.parse(xmlFile);
            org.w3c.dom.Element rootElement = doc.getDocumentElement();
            document = convertToCustomNode(rootElement);
        }
    }

    private Node convertToCustomNode(org.w3c.dom.Node w3cNode) {
        Node customNode = new Node(w3cNode.getNodeName());
        if (w3cNode.hasChildNodes()) {
            org.w3c.dom.NodeList nodeList = w3cNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Node w3cChildNode = nodeList.item(i);
                if (w3cChildNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    customNode.setNext(convertToCustomNode(w3cChildNode));
                }
            }
        }
        return customNode;
    }

    private void saveDocument() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        org.w3c.dom.Document w3cDocument = docBuilder.newDocument();
        org.w3c.dom.Element rootElement = w3cDocument.createElement(document.getData().toString());
        w3cDocument.appendChild(rootElement);
        saveCustomNode(w3cDocument, rootElement, document.getNext());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(w3cDocument);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

    private void saveCustomNode(org.w3c.dom.Document w3cDocument, org.w3c.dom.Element w3cParentElement, Node customNode) {
        while (customNode != null) {
            org.w3c.dom.Element w3cElement = w3cDocument.createElement(customNode.getData().toString());
            w3cParentElement.appendChild(w3cElement);
            saveCustomNode(w3cDocument, w3cElement, customNode.getNext());
            customNode = customNode.getNext();
        }
    }

    public void addElement(String parentTag, String tagName, String textContent) throws Exception {
        Node parent = findNode(document, parentTag);
        if (parent != null) {
            Node newElement = new Node(tagName);
            newElement.setNext(new Node(textContent));
            appendChild(parent, newElement);
            saveDocument();
        } else {
            throw new Exception("Parent tag not found");
        }
    }

    private Node findNode(Node currentNode, String tagName) {
        while (currentNode != null) {
            if (currentNode.getData().equals(tagName)) {
                return currentNode;
            }
            Node found = findNode(currentNode.getNext(), tagName);
            if (found != null) {
                return found;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    private void appendChild(Node parent, Node child) {
        Node current = parent;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(child);
    }

    public String getElementValue(String tagName) throws Exception {
        Node node = findNode(document, tagName);
        if (node != null && node.getNext() != null) {
            return node.getNext().getData().toString();
        } else {
            throw new Exception("Tag not found");
        }
    }

    public void updateElementValue(String tagName, String newValue) throws Exception {
        Node node = findNode(document, tagName);
        if (node != null && node.getNext() != null) {
            node.getNext().setData(newValue);
            saveDocument();
        } else {
            throw new Exception("Tag not found");
        }
    }

    public void deleteElement(String tagName) throws Exception {
        if (document != null && document.getData().equals(tagName)) {
            document = document.getNext();
            saveDocument();
        } else {
            deleteElement(document, tagName);
        }
    }

    private void deleteElement(Node currentNode, String tagName) throws Exception {
        while (currentNode != null && currentNode.getNext() != null) {
            if (currentNode.getNext().getData().equals(tagName)) {
                currentNode.setNext(currentNode.getNext().getNext());
                saveDocument();
                return;
            }
            currentNode = currentNode.getNext();
        }
        throw new Exception("Tag not found");
    }

    public void printDocument() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        org.w3c.dom.Document w3cDocument = docBuilder.newDocument();
        org.w3c.dom.Element rootElement = w3cDocument.createElement(document.getData().toString());
        w3cDocument.appendChild(rootElement);
        saveCustomNode(w3cDocument, rootElement, document.getNext());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(w3cDocument);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }///From this and bellow is for the creation of pdf files

    public static void createPdf(String dest) throws FileNotFoundException {
        // Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Add a paragraph to the document
        document.add(new Paragraph("Hello, World!"));

        // Close the document
        document.close();

        System.out.println("PDF created successfully!");
    }
}