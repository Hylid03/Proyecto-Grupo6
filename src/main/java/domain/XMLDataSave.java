package domain;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
public class XMLDataSave {

    private Document document;
    private String filePath;

    public XMLDataSave(String filePath) throws Exception {
        this.filePath = filePath;
        loadDocument();
    }

    private void loadDocument() throws Exception {
        File xmlFile = new File(filePath);
        if (!xmlFile.exists()) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            document = docBuilder.newDocument();
            Element rootElement = document.createElement("root");
            document.appendChild(rootElement);
            saveDocument();
        } else {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            document = docBuilder.parse(xmlFile);
        }
    }

    private void saveDocument() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public void addElement(String parentTag, String tagName, String textContent) throws Exception {
        NodeList nodeList = document.getElementsByTagName(parentTag);
        if (nodeList.getLength() > 0) {
            Element parent = (Element) nodeList.item(0);
            Element newElement = document.createElement(tagName);
            newElement.appendChild(document.createTextNode(textContent));
            parent.appendChild(newElement);
            saveDocument();
        } else {
            throw new Exception("Parent tag not found");
        }
    }

    public String getElementValue(String tagName) throws Exception {
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            throw new Exception("Tag not found");
        }
    }

    public void updateElementValue(String tagName, String newValue) throws Exception {
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            nodeList.item(0).setTextContent(newValue);
            saveDocument();
        } else {
            throw new Exception("Tag not found");
        }
    }

    public void deleteElement(String tagName) throws Exception {
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            node.getParentNode().removeChild(node);
            saveDocument();
        } else {
            throw new Exception("Tag not found");
        }
    }

    public void printDocument() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }

}
