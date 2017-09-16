import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        randomlyPopulateHeap(1000);
        giveMinChildren(200);

        heap.moveMinChildrenToRootList();

        Assert.assertTrue(heap.min().degree() == 0);
    }

    @Test
    public void moveChildrenOfMinToRootListSizeTest(){
        randomlyPopulateHeap(1000);
        giveMinChildren(100);

        int degree = heap.min().degree();
        int listSize = heap.getRootList().size();

        heap.moveMinChildrenToRootList();

        Assert.assertTrue(heap.getRootList().size() == listSize + degree);
    }

    @Test
    public void moveChildrenOfMinToRootListTest(){
        randomlyPopulateHeap(1000);
        ArrayList<HeapNode> nodesMovedToRoot = giveMinChildren(100);

        heap.moveMinChildrenToRootList();

        boolean nodesPresent = true;

        for(HeapNode node : nodesMovedToRoot){
            nodesPresent = nodesPresent && heap.getRootList().find(node) != null;
        }

        Assert.assertTrue("one or more nodes from min child list are not found in root list", nodesPresent);
    }

    @Test
    public void popReturnsCurrentMin(){
        randomlyPopulateHeap(1000);
        giveMinChildren(200);

        HeapNode min = heap.min();
        HeapNode popped = heap.pop();

        Assert.assertTrue("min node not returned from pop", min == popped);
    }

    @Test
    public void 

    /*
    * Adds input number of children to min node in heap
    * every 15th input node gets two children
    * one of those children gets its own child
    * */
    public ArrayList<HeapNode> giveMinChildren(int numberOfChildren){
        if(heap == null)
            return null;

        HeapNode child;
        ArrayList<HeapNode> nodesAddedToRoot = new ArrayList<>();

        for(int i = 0; i < numberOfChildren; i++){
            int value = (int)Math.random() * 200000;
            child = new HeapNode(value);
            nodesAddedToRoot.add(child);
            heap.min().getChildList().insert(child);
            if(i % 18 == 0){
                child.getChildList().insert(new HeapNode(4));
                child.getChildList().insert(new HeapNode(2));
                child.getChildList().start().getData().getChildList().insert(new HeapNode(1));
                i+=3;
            }
        }
        return nodesAddedToRoot;
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