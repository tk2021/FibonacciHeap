

public class HeapNode {
    private HeapNode parent;
    private boolean mark;
    private int data;
    private CircularDoublyLinkedList childList;


    HeapNode(int data){
        this.parent = null;
        this.mark = false;
        this.data = data;
        this.childList = new CircularDoublyLinkedList();
    }

    public int degree(){
        return childList.size();
    }
}