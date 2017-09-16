import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void consolidateTwoNodesOfZeroDegreeSizeTest(){
        insertOneNode(5);
        insertOneNode(3);

        heap.consolidate();

        Assert.assertTrue("size not updated after merge should be 1 but its " + heap.getRootList().size(), heap.getRootList().size() == 1);
    }

    @Test
    public void consolidateTwoNodesOfZeroDegreeMaxMergedOntoMinTest(){
        insertOneNode(5);
        insertOneNode(3);

        heap.consolidate();

        Assert.assertTrue("nodes of zero degree not merged",heap.getRootList().start().getData() == testNode);
    }

    @Test
    public void consolidateTwoNodesOfZeroDegreeMakeNodeOfOneDegreeTest(){
        insertOneNode(5);
        insertOneNode(3);

        heap.consolidate();

        Assert.assertTrue("node merged onto does not have a degree of one",heap.getRootList().start().getData().degree() == 1);
    }

    @Test
    public void consolidate31NodesSizeTest(){
        insert31NodesAndConsolidateThem();
        heap.printTree();

        Assert.assertTrue("size of heap.rootlist is incorrectly updated; should be 5 but is " + heap.getRootList().size(), heap.getRootList().size() == 5);
    }

    @Test
    public void consolidate31NodesTest(){
        insert31NodesAndConsolidateThem();

        Assert.assertTrue("heap does not merge 31 nodes of degree 0 and produce 5 nodes of degrees 4,3,2,1,0" , checkRootListForCorrectDegreesAfter31NodesConsolidate());
    }

    @Test
    public void populateListAndPopAllNodesEnsureTheyAreInSortedOrder(){
        randomlyPopulateHeap(10);
        boolean currentNodeIsGTEPrevious = true;
        HeapNode current = heap.min();
        HeapNode prev = heap.min();
        while(heap.size() > 0){
            System.out.println("\nsize: " + heap.size() + "\n");
            heap.printTree();
            prev = current;
            current = heap.pop();
            currentNodeIsGTEPrevious = currentNodeIsGTEPrevious && prev.getData() <= current.getData();
        }

        Assert.assertTrue("nodes were not popped in reverse sorted order", currentNodeIsGTEPrevious);
    }

    public boolean checkRootListForCorrectDegreesAfter31NodesConsolidate(){
        ListNode current = heap.getRootList().start();
        ListNode start = heap.getRootList().start();
        HashMap<Integer, Boolean> degreeMap = new HashMap<>();
        degreeMap.put(4, true);
        degreeMap.put(3, true);
        degreeMap.put(2, true);
        degreeMap.put(1, true);
        degreeMap.put(0, true);

        boolean all5NodesAreOfCorrectDegree = true;

        do{
            HeapNode node = current.getData();
            int degreeOfCurrent = node.degree();
            all5NodesAreOfCorrectDegree = all5NodesAreOfCorrectDegree && degreeMap.get(degreeOfCurrent);
            degreeMap.put(degreeOfCurrent, false);
        }while(current != start);

        return all5NodesAreOfCorrectDegree;
    }

    /*
    * Inserts 31 nodes into the heap root list
    * Calls consolidate()
    * new root list has 5 nodes of degrees: 4, 3, 2, 1, 0
     */
    public void insert31NodesAndConsolidateThem(){
        for(int i = 0; i < 31; i++){
            int value = (int)(Math.random() * 200000);
            HeapNode node = new HeapNode(value);
            heap.insert(node);
        }
        heap.consolidate();
    }

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
            int value = (int)(Math.random() * 200000);
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
            int value = (int)(Math.random() * 200000);
            if(value < minValue)
                minValue = value;
            if(i == (numberOfNodes-1) % ((numberOfNodes/5) + 1)){
                testNode = new HeapNode(value);
                heap.insert(testNode);
            }
            else{
                heap.insert(new HeapNode(value));

            }
        }
    }
}