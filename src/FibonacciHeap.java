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
        return size;
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
        incrementSize();
    }

    public HeapNode pop(){
        /*
        * move all children of min to root list
        * remove min from root list store in variable
        * combine nodes in root list of like degree
        * update min
        * return previous min
        * */
        HeapNode currentMin = min();

        return currentMin;
    }

    public void moveMinChildrenToRootList(){
        CircularDoublyLinkedList minChildList = min().getChildList();
        while(!minChildList.isEmpty()){
            HeapNode child = minChildList.start().getData();
            minChildList.remove(child);
            moveChildToRootList(child);
        }

    }

    private void moveChildToRootList(HeapNode child){
        this.insert(child);
        decrementSize();
    }

    private void incrementSize(){
        size++;
    }

    private void decrementSize(){
        size--;
    }
}