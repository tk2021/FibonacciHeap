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


}
