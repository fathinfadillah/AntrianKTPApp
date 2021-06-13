public class SLinkedList {
    private Node head;
    private Node tail;
    private Node middle;
    private int size;

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        if ((this.head==null)  &&  (this.tail==null))  {
            return true;
        } else {
            return false;
        }
    }

    public int getSize() {
        return size;
    }

    public String getNode () {
        return this.head.getData();
    }

    public void addFirst(Node node) {
        if (!isEmpty()) {
            node.setNextReference(head);
            head = node;
        } else {
            node.setNextReference(null);
            tail = node;
            head = node;
        }
        ++this.size;
    }

    public void addLast(Node node) {
        if (!isEmpty()) {
            node.setNextReference(null);
            this.tail.setNextReference(node);
            tail = node;
        } else {
            node.setNextReference(null);
            tail = node;
            head = node;
        }
        ++this.size;
    }

    public void addMiddle(Node node, int hitung) {
        Node pointer = head;

        if (!isEmpty()) {
            for(int i = 0; i < hitung-1; i++){
                pointer = pointer.getNextReference();
            }
            node.setNextReference(pointer.getNextReference());
            pointer.setNextReference(node);
        } else {
            this.addLast(node);
        }
        ++this.size;
    }

    public void display() {
        Node pointer;

        pointer = head;

        System.out.println("Size : " + this.size);
        while (pointer != null) {
            System.out.println(pointer.getData());
            pointer = pointer.getNextReference();
        }
    }

    public Node search(String data) {
        Node pointer;

        if (isEmpty()) return null;

        pointer = head;

        while (pointer != null) {

            if (pointer.getData().contentEquals(data)) return pointer;

            pointer = pointer.getNextReference();
        }
        return null;
    }

    public void deleteHead() {
        Node pointer;

        pointer = head;
        head = pointer.getNextReference();
        pointer = null;
    }

    public void deleteMiddle(int hitung) {
        Node pointer;

        pointer = head;
        int i = 0;
        while (i < hitung-1) {
            pointer = pointer.getNextReference();
            i++;
        }
        middle = pointer;
        middle.setNextReference(pointer.getNextReference().getNextReference());
        --this.size;
    }

    public void deleteTail() {
        Node pointer;

        pointer = head;
        int i = 0;
        while(i < size-2){
            pointer = pointer.getNextReference();
            i++;
        }
        tail = pointer;
        tail.setNextReference(null);
        --this.size;
    }
}

