import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FibonacciHeapTests {
    FibonacciHeap heap;
    HeapNode testNode;
    int minValue;

    @Before
    public void initializeHeap(){
        heap = new FibonacciHeap();
    }

    public void insertOneNode(int value){
        testNode = new HeapNode(value);
        heap.insert(testNode);
    }

    @Test
    public void createHeapSizeTest(){
        Assert.assertTrue(heap.size() == 0);
    }

    @Test
    public void createHeapEmptyRootListTest(){
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void createHeapMinIsNullTest(){
        Assert.assertTrue(heap.min() == null);
    }

    @Test
    public void insertToEmptyHeapSizeIsIncrementedTest(){
        insertOneNode(22);
        Assert.assertTrue(heap.size() == 1);
    }

    @Test
    public void insertToEmptyHeapNodeIsMinTest(){
        insertOneNode(22);
        Assert.assertTrue("min did not get updated", heap.min() != null);
        Assert.assertTrue("min got updated incorrectly", heap.getRootList().start().getData() == testNode);
    }

    @Test
    public void insertNewMinIntoHeapTest(){
        insertOneNode(2);
        insertOneNode(1);
        Assert.assertTrue("min is not current", heap.min() == testNode);
    }

    @Test
    public void insertUpdatesSizeOfPopulatedHeapTest(){
        randomlyPopulateHeap(100000);
        int size = heap.size();
        insertOneNode(1);
        Assert.assertTrue("size not updated correctly", heap.size() == size + 1);
    }

    @Test
    public void maintainsMinOfPopulatedHeapTest(){
        randomlyPopulateHeap(100000);
        Assert.assertTrue("min not maintained", heap.min().getData() == minValue);
    }

    @Test
    public void moveChildrenOfMinToRootListDegreeOfMinTest(){
        int degree = heap.min().degree();
    }

    public void randomlyPopulateHeap(int numberOfNodes){
        minValue = Integer.MAX_VALUE;

        for(int i = 0; i < numberOfNodes; i++){
            int value = (int)Math.random() * 200000;
            if(value < minValue)
                minValue = value;
            if(i == (numberOfNodes-1) % (numberOfNodes/5)){
                testNode = new HeapNode(value);
                heap.insert(testNode);
            }
            else{
                heap.insert(new HeapNode(value));

            }
        }
    }
}