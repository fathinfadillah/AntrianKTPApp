public class LQueue {
    private SLinkedList elements;
    public int size;

    public LQueue() {
        elements = new SLinkedList();
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue (String newElement) {
        Node newNode = new Node(newElement);
        elements.addLast(newNode);
        ++size;
    }

    public String dequeue() {
        if (size == 0) {
            System.out.println("Cannot Dequeue, Queue is Empty");
            return null;
        }

        String frontElement = elements.getNode();

        System.out.println("Dequeue " + frontElement);

        elements.deleteHead();
        size--;

        return frontElement;
    }

    public String peek() {

        if (size == 0) {
            System.out.println("Cannot peek, Queue is Empty");
            return null;
        } else {
            String frontElement = elements.getNode();
            return frontElement;
        }
    }
}

