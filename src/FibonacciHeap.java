/**
 * Created by TomasK on 9/12/2017.
 */
public class FibonacciHeap {
    private Node min, dummyRoot;

    FibonacciHeap(){
        this.min = null;
        this.dummyRoot = new Node(-1);
    }

    public Node getMin(){
        return min;
    }

    public void setMin(Node min){
        this.min = min;
    }

    public int size(){
        return this.dummyRoot.getDegree();
    }

    public void insert(Node x){
        dummyRoot.setChild(x);
        if(getMin() == null){
            setMin(x);
        }
        else{
            if(min.getValue() > x.getValue())
                setMin(x);
        }
    }


}
