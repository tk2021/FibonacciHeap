import org.junit.Assert;
import org.junit.Test;

/**
 * Created by TomasK on 9/12/2017.
 */
public class FibonacciHeapTests {

    @Test
    public void fibonacciHeapCreation(){
        FibonacciHeap fibonacciHeap = new FibonacciHeap();

        Assert.assertTrue(fibonacciHeap.getMin() == null && fibonacciHeap.size() == 0);
    }

    @Test
    public void insertTest(){
        FibonacciHeap fibonacciHeap = new FibonacciHeap();

        fibonacciHeap.insert(new Node(5));
        fibonacciHeap.insert(new Node(3));

        Node min = fibonacciHeap.getMin();

        Assert.assertTrue(min.getValue() == 3 && min.getLeft().getValue() == 5);
    }

    @Test
    public void getMinTest(){
        FibonacciHeap fibonacciHeap = new FibonacciHeap();

        fibonacciHeap.insert(new Node(5));
        fibonacciHeap.insert(new Node(3));
        fibonacciHeap.insert(new Node(6));
        fibonacciHeap.insert(new Node(1));
        fibonacciHeap.insert(new Node(5));
        fibonacciHeap.insert(new Node(3));

        Assert.assertTrue(fibonacciHeap.getMin().getValue() == 1);
    }

    @Test
    public void getSizeTest(){
        FibonacciHeap fibonacciHeap = new FibonacciHeap();

        fibonacciHeap.insert(new Node(5));
        fibonacciHeap.insert(new Node(3));
        fibonacciHeap.insert(new Node(6));
        fibonacciHeap.insert(new Node(1));
        fibonacciHeap.insert(new Node(5));
        fibonacciHeap.insert(new Node(3));

        Assert.assertTrue(fibonacciHeap.size() == 6);
    }

    @Test
    public void unionNewMinTest(){
        FibonacciHeap heap = unionTwoHeaps();
        Assert.assertTrue(heap.getMin().getValue() == 1);
    }

    @Test
    public void unionNewSizeTest(){
        FibonacciHeap heap = unionTwoHeaps();
        Assert.assertTrue(heap.size() == 4);
    }

    @Test
    public void unionNullHeapTest(){
        FibonacciHeap heap1 = new FibonacciHeap();
        heap1.insert(new Node(1));

        FibonacciHeap heap = FibonacciHeap.unionHeaps(heap1, new FibonacciHeap());

        Assert.assertTrue(heap.getMin().getValue() == 1);
    }

    public FibonacciHeap unionTwoHeaps(){
        FibonacciHeap heap1 = new FibonacciHeap();
        FibonacciHeap heap2 = new FibonacciHeap();

        heap1.insert(new Node(5));
        heap1.insert(new Node(3));

        heap2.insert(new Node(1));
        heap2.insert(new Node(6));

        return FibonacciHeap.unionHeaps(heap1, heap2);
    }


}
