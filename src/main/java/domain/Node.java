package domain;

public class Node {
    public Object data; // Element stored in the node
    public Integer priority; // Priority level (1=high, 2=medium, 3=low)
    public Node next; // Link to the next node
    public Node prev; // Link to the previous node
    private int role; // Role: 0=admin, 1=tutor, 2=student

    // Constructors
    public Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Node(Object data, Integer priority) {
        this.data = data;
        this.priority = priority;
        this.next = null;
        this.prev = null;
    }

    // Getters and setters
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
