import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by TomasK on 9/12/2017.
 */
public class FibonacciHeap {
    private Node min, dummyRoot;
    private int size;

    FibonacciHeap(){
        this.min = null;
        this.dummyRoot = new Node(-1);
        this.size = 0;
    }

    public Node getMin(){
        return min;
    }

    public void setMin(Node min){
        this.min = min;
    }

    public int size(){
        return this.size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void insert(Node x){
        this.size++;
        dummyRoot.setChild(x);
        if(getMin() == null){
            setMin(x);
        }
        else{
            if(min.getValue() > x.getValue())
                setMin(x);
        }
    }

    //remove a child from a child list updating the parent's child pointer
    public void removeChild(Node node){
        Node left = node.getLeft();
        Node right = node.getRight();

        if(left == node){
            node.getParent().changeChild(null);
            node.setLeft(node);
            node.setRight(node);
            return;
        }
        else{
            left.setRight(right);
            right.setLeft(left);
            node.setLeft(node);
            node.setRight(node);
        }
        if(node.getParent().getChild() == node) {
            node.getParent().changeChild(left);
        }
    }

    public void moveAllChildrenToRoot(Node node){
        Node poppedChild = node.getChild();
        if(poppedChild != null) {
            while (poppedChild.getRight() != poppedChild) {
                System.out.println("stuck in here for a WHILE " + poppedChild.getValue() + " " + poppedChild.getRight().getValue());
                Node temp = poppedChild.getRight();
                moveChildToRoot(poppedChild);
                poppedChild = temp;

                System.out.println("printing from the while in pop");
                printTree(getMin());
            }
            insert(poppedChild);
            node.changeChild(null);
            decrementSize();

            System.out.println("printing in pop after the while last insert");
            printTree(getMin());
        }
    }

    public void moveChildToRoot(Node node){
        removeChild(node);
        insert(node);
        decrementSize();
    }

    public Node pop(){
        Node poppedNode = getMin();
        if(poppedNode == null)
            return null;
//        Node poppedChild = poppedNode.getChild();
//        if(poppedChild != null) {
//            while (poppedChild.getRight() != poppedChild) {
//                System.out.println("stuck in here for a WHILE " + poppedChild.getValue() + " " + poppedChild.getRight().getValue());
//                Node temp = poppedChild.getRight();
//                removeChild(poppedChild);
//                insert(poppedChild);
//                decrementSize();
//                poppedChild = temp;
//
//                System.out.println("printing from the while in pop");
//                printTree(getMin());
//            }
//            insert(poppedChild);
//            poppedNode.changeChild(null);
//            decrementSize();
//
//            System.out.println("printing in pop after the while last insert");
//            printTree(getMin());
//        }

        moveAllChildrenToRoot(poppedNode);

//        Node left = poppedNode.getLeft();
//        Node right = poppedNode.getRight();
//
//        left.setRight(right);
//        right.setLeft(left);

        setMin(poppedNode.getLeft());

        removeChild(poppedNode);


        consolidate();

        decrementSize();
        System.out.println("before dummyRoot.changeChild() " + getMin().getValue());
        this.dummyRoot.changeChild(getMin());
        System.out.println("after dummyRoot.changeChild() " + getMin().getValue());


        return poppedNode;
    }

    private void consolidate(){
        System.out.println("inside consolidate");
        System.out.println("size " + size());
        printTree(getMin());

        Node array [] = new Node [size() + 1];

        for(int i = 0; i < array.length ; i++)
            array[i] = null;

        Node start = getMin();
        Node counter = getMin();


        Node iterateNode = getMin();
        for(int i = 0; i < 5; i++){
            System.out.println(iterateNode.getValue() + " " + iterateNode.getDegree());
            iterateNode = iterateNode.getRight();
        }

        AtomicReference<Node> refCounter = new AtomicReference<>(counter);
        AtomicReference<Node> refStart = new AtomicReference<>(start);

        int k = 0;
        do{
            Node temp = counter;
            mergeNodeOfSameDegree(temp, array, refCounter, refStart);
        }while(refStart.get() != refCounter.get() || ++k < size());

        updateMin(array);
    }

    public void updateMin(Node array []){
        setMin(null);

        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                System.out.print(array[i].getValue());
            }
            else
                System.out.print('x');
        }
        System.out.println();

        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                if(getMin() == null) {
                    setMin(array[i]);
                    System.out.println("ITS NOT NULL!!!!!!!!!! " + array[i].getValue() + " " + array[i].getDegree());

                }
                else{
                    if(array[i].getValue() < getMin().getValue()) {
                        setMin(array[i]);
                        System.out.println("******************" + array[i].getValue() + " " + array[i].getDegree());

                    }
                }
            }
        }
    }

    public void mergeNodeOfSameDegree(Node node, Node array [], AtomicReference<Node> start, AtomicReference<Node> current){
        node = current.get();

        int degree = node.getDegree();

        System.out.println(node.getValue() + " " + node.getDegree());

        Node child = node.getChild();
        for(int i = 0; i < 50; i++){
            if(child == null)
                break;
            System.out.print(child.getValue() + " ");
            child = child.getRight();
        }
        System.out.println();

        while(array[degree] != null){
            Node nodeOfDegree = array[degree];
            if(node.getValue() < nodeOfDegree.getValue()){
                if(start.get() == nodeOfDegree){
                    start.set(start.get().getRight());
                }
                removeChild(nodeOfDegree);
                node.setChild(nodeOfDegree);
            }
            else{
                current.set(current.get().getLeft());
                removeChild(node);
                nodeOfDegree.setChild(node);
                node = nodeOfDegree;
            }
            array[degree++] = null;

        }
        array[degree] = node;
        current.set(current.get().getRight());
    }

    public static FibonacciHeap unionHeaps(FibonacciHeap heap1, FibonacciHeap heap2){
        FibonacciHeap unionOfHeaps = new FibonacciHeap();

        if(heap1 == null && heap2 == null){
            return unionOfHeaps;
        }
        if(heap1 == null && heap2 != null){
            return heap2;
        }
        if(heap1 != null && heap2 == null) {
            return heap1;
        }

        if(heap1.size() == 0){
            return heap2;
        }
        else if(heap2.size() == 0){
            return heap1;
        }

        unionOfHeaps.insert(heap1.getMin());

        Node min1 = heap1.getMin();
        Node tail1 = min1.getLeft();
        Node min2 = heap2.getMin();
        Node tail2 = min2.getLeft();

        min1.setLeft(tail2);
        tail2.setRight(min1);

        min2.setLeft(tail1);
        tail1.setRight(min2);

        if(min2.getValue() < min1.getValue())
            unionOfHeaps.setMin(min2);

        unionOfHeaps.setSize(heap1.size() + heap2.size());

        return unionOfHeaps;

    }

    private void decrementSize(){
        this.size--;
    }


    public void printTree(Node root){
        Node temp = root;
        if(temp == null)
            return;

        System.out.print(temp.getValue() + " ");

        Queue<Node> queue = new LinkedList<>();

        queue.add(temp.getChild());

        temp = temp.getRight();

        while(temp != root){
            System.out.print(temp.getValue() + " ");
            queue.add(temp.getChild());
            temp = temp.getRight();
        }

        while(!queue.isEmpty()){
            System.out.println();
            temp = queue.remove();
            if(temp != null)
                System.out.print(temp.getParent().getValue() + ": ");
            printTree(temp);
        }

        System.out.println();
    }


}
