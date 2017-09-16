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
            System.out.println("ITS MIN " + min().getData());
        moveMinChildrenToRootList();
        rootList.remove(currentMin);
        decrementSize();
        consolidate();
        updateMin();

        return currentMin;
    }

    public void consolidate(){
        if(size() == 0)
            return;

        HeapNode degreeArray [] = new HeapNode [size() + 2];
        for(HeapNode node : degreeArray)
            node = null;

        ListNode current = getRootList().start();
        ListNode start = getRootList().start();

        do{
            HeapNode node = current.getData();
            int degree = node.degree();
            current = current.next();
            while(degreeArray[degree] != null){
                HeapNode nodeOfLikeDegree = degreeArray[degree];
                if(nodeOfLikeDegree.getData() <= node.getData()){
                    getRootList().remove(node);
                    nodeOfLikeDegree.getChildList().insert(node);
                    node = nodeOfLikeDegree;
                }
                else{
                    if(nodeOfLikeDegree == start.getData()){
                        start = start.prev();
                    }
                    else if(nodeOfLikeDegree == current.getData()){
                        current = current.next();
                    }
                    getRootList().remove(nodeOfLikeDegree);
                    node.getChildList().insert(nodeOfLikeDegree);
                }
                degreeArray[degree++] = null;
                System.out.println("End of while degree of node : " + node.getData());
            }
            degreeArray[degree] = node;
        }while(current != start);

    }

    public void updateMin(){
        printTree();
        if(size() == 0)
            return;

        min = null;
        ListNode current = getRootList().start();
        ListNode start = getRootList().start();
        do{
            if(min == null){
                min = current.getData();
            }
            else if(min.getData() > current.getData().getData()){
                min = current.getData();
            }
            current = current.next();
        }while(current != start);
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

    public void printTree(){
        if(rootList.size() == 0)
            return;
        ListNode current = rootList.start();
        ListNode start = rootList.start();
        do{
            System.out.print(current.getData().getData() + " ");
            current = current.next();
        }while(start != current);
        do{
            printSubTree(current.getData());
            current = current.next();
        }while(start != current);

    }

    public void printSubTree(HeapNode node){
        System.out.println();
        System.out.print(node.getData() + ":  " );
        if(node.getChildList().size() == 0)
            return;
        CircularDoublyLinkedList childList = node.getChildList();
        ListNode current = childList.start();
        ListNode start = childList.start();

        do{
            System.out.print(current.getData().getData() + " ");
            current = current.next();
        }while(start != current);
        do{
            printSubTree(current.getData());
            current = current.next();
        }while(start != current);


    }
}