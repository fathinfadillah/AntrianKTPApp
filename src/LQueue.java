import javax.swing.*;

public class LQueue {
    private SLinkedList elements;
    public int size;

    public LQueue() {
        elements = new SLinkedList();
        size = 0;
    }

    public void enqueue (String newElement) {
        Node newNode = new Node(newElement);
        elements.addLast(newNode);
        ++size;
    }

    public void  show()
    {
        elements.display();
    }

    public String dequeue() {
        if (size == 0) {
            JOptionPane.showMessageDialog(null, "Antrian kosong! ","Informasi",JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        String frontElement = elements.getNode();
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

