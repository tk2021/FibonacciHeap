

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

    public void addChild(HeapNode child){
        childList.insert(child);
    }

    public void removeChild(HeapNode child){
        childList.remove(child);
    }

    public CircularDoublyLinkedList getChildList(){
        return childList;
    }

    public int getData(){
        return data;
    }
}