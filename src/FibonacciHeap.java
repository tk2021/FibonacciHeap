public class FibonacciHeap {
    int size;
    CircularDoublyLinkedList rootList;
    HeapNode min;

    public FibonacciHeap(){
        this.size = 0;
        this.rootList = new CircularDoublyLinkedList();
        this.min = null;
    }

    public HeapNode min(){
        return min;
    }

    public boolean isEmpty(){
        return rootList.isEmpty();
    }

    public int size(){
        return rootList.size();
    }

    public CircularDoublyLinkedList getRootList(){
        return rootList;
    }

    public void insert(HeapNode node){
        if(min == null)
            min = node;
        else if(min.getData() > node.getData())
            min = node;

        rootList.insert(node);
    }

    public void pop(){
        /*
        * move all children of min to root list
        * remove min from root list store in variable
        * combine nodes in root list of like degree
        * update min
        * return previous min
        * */
    }

    public void moveMinChildrenToRootList(){

    }
}