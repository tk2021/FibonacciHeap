import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

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
        FibonacciHeap fibonacciHeap = makeHeap();

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

    @Test
    public void popMinTest(){
        FibonacciHeap heap = makeHeap();
        int size = heap.size();

        Node min = heap.pop();


        Assert.assertTrue(min.getValue() == 1 && size - heap.size() == 1 && heap.getMin().getValue() == 3);
    }

    @Test
    public void mergeRootNodesOfSameDegreeTest(){
        FibonacciHeap heap = new FibonacciHeap();

        Node child1 = new Node(0);
        Node child2 = new Node(1);
        Node child3 = new Node(2);

        heap.insert(child1);
        heap.insert(child2);
        heap.insert(child3);

        heap.printTree(heap.getMin());

        Node array [] = new Node [heap.size() + 1];

        for(int i = 0; i < array.length ; i++)
            array[i] = null;

        AtomicReference<Node> start = new AtomicReference<>(child1);
        AtomicReference<Node> current = new AtomicReference<>(child1);
        heap.mergeNodeOfSameDegree(heap.getMin(), array, start, current);

        Assert.assertTrue(array[0] == child1);
        Assert.assertTrue("start did not stay the same", start.get() == child1);
        Assert.assertTrue("Current did not update", current.get() == child2);

        heap.mergeNodeOfSameDegree(child2, array, start, current);

        Assert.assertTrue(array[0] == null);
        Assert.assertTrue("start did not stay the same", start.get() == child1);
        Assert.assertTrue("Current did not update", current.get() == child3);
        Assert.assertTrue("child1 did not have children", child1.getChild() != null);

        heap.printTree(child1);

        heap.mergeNodeOfSameDegree(child3, array, start, current);

        heap.printTree(child1);


    }

    @Test
    public void mergeRootNodesOfSameDegreeArrayTest(){
        FibonacciHeap heap = new FibonacciHeap();

        Node fauxMin = new Node(6);

        heap.insert(new Node(5));
        heap.insert(new Node(3));
        heap.insert(new Node(5));
        heap.insert(new Node(3));
        heap.insert(fauxMin);

        heap.setMin(fauxMin);


        heap.printTree(heap.getMin());

        Node array [] = new Node [heap.size() + 1];

        for(int i = 0; i < array.length ; i++)
            array[i] = null;

        AtomicReference<Node> start = new AtomicReference<>(heap.getMin());
        AtomicReference<Node> current = new AtomicReference<>(heap.getMin());

        do {
            heap.mergeNodeOfSameDegree(current.get(), array, start, current);
            printArray(array);
        }while(current.get() != start.get());




//        Assert.assertTrue(array[0].getValue() == 5 && array[2].getValue() == 3);
        heap.printTree(heap.getMin());


    }

    public void printArray(Node array []){
        System.out.println();

        for(int i = 0 ; i < array.length; i++) {
            if(array[i] != null)
                System.out.print(array[i].getValue());
            else
                System.out.print('x');

        }
        System.out.println();
    }

    @Test
    public void mergeOneRootNodesOfSameDegreeTest(){
        FibonacciHeap heap = new FibonacciHeap();

        Node child1 = new Node(0);


        heap.insert(child1);


        heap.printTree(heap.getMin());

        Node array [] = new Node [heap.size() + 1];

        for(int i = 0; i < array.length ; i++)
            array[i] = null;

        AtomicReference<Node> start = new AtomicReference<>(child1);
        AtomicReference<Node> current = new AtomicReference<>(child1);
        heap.mergeNodeOfSameDegree(heap.getMin(), array, start, current);

        Assert.assertTrue(array[0] == child1);
        Assert.assertTrue("start did not stay the same", start.get() == child1);
        Assert.assertTrue("Current did not update", current.get() == child1);



    }

    @Test
    public void removeChildFromChildListTest(){
        FibonacciHeap heap = makeHeap();

        Node node = heap.getMin();
        node.setChild(new Node(9));

        heap.removeChild(node.getChild());

        Assert.assertTrue(node.getChild() == null);
    }

    @Test
    public void removeChildFromChildNonEmptyListTest(){
        FibonacciHeap heap = makeHeap();

        Node node = heap.getMin();
        node.setChild(new Node(9));
        node.setChild(new Node(10));

        heap.removeChild(node.getChild());

        Assert.assertTrue(node.getChild().getLeft() == node.getChild());
    }

    @Test
    public void removeAllChildrenFromNodeTest(){
        FibonacciHeap heap = makeHeap();

        Node node = heap.getMin();
        node.setChild(new Node(9));
        node.setChild(new Node(10));
        node.setChild(new Node(11));

        heap.moveAllChildrenToRoot(node);

        Assert.assertTrue(node.getChild() == null);
    }

    @Test
    public void removeAllChildrenAndPutThemUnderRootTest(){
        FibonacciHeap heap = makeHeap();

        Node node = heap.getMin();
        node.setChild(new Node(9));
        node.setChild(new Node(10));
        node.setChild(new Node(11));

        heap.moveAllChildrenToRoot(node);

        Node temp = node.getRight();
        ArrayList<Integer> list = new ArrayList<>();

        list.add(node.getValue());
        while(temp != node){
            list.add(temp.getValue());
            temp = temp.getRight();
        }

        for(Integer value : list)
            System.out.print(" " + value);
        System.out.println();

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(5);
        collection.add(5);
        collection.add(3);
        collection.add(3);
        collection.add(1);
        collection.add(6);
        collection.add(9);
        collection.add(10);
        collection.add(11);

        for(Integer value : collection)
            System.out.print(" " + value);
        System.out.println();

        Assert.assertTrue(list.containsAll(collection));
    }

    @Test
    public void updateMinTest(){
        Node array [] = new Node [6];

        array[0] = new Node(3);

        array[3] = new Node(2);

        array[2] = new Node(1);

        FibonacciHeap heap = new FibonacciHeap();

        heap.updateMin(array);

        Assert.assertTrue(heap.getMin().getValue() == 1);
    }

    @Test
    public void popAllTest(){
        FibonacciHeap heap = makeHeap();
        ArrayList<Integer> list = new ArrayList<>();



        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(5);
        collection.add(5);
        collection.add(3);
        collection.add(3);
        collection.add(1);
        collection.add(6);

        for(int i = 0; i < 50; i++){
            int k = (int)(Math.random() * 100);
            collection.add(k);
            list.add(k);
            heap.insert(new Node(k));
        }

        while(heap.size() > 0) {
            list.add(heap.pop().getValue());
            System.out.println("\nnew min: " + heap.getMin().getValue());

            Node temp = heap.getMin();
            Node child = temp.getChild();

            for(int j = 0; j < 5 && child != null; j++){
                System.out.print(child.getValue() + " ");
                child = child.getRight();
            }

            System.out.println("\n");


            for(int j = 0; j < 5; j++){
                System.out.print(temp.getValue() + " ");
                temp = temp.getRight();
            }
            System.out.println("\n");
        }

        for(Integer value : list)
            System.out.println(value);

        Assert.assertTrue(list.containsAll(collection));
    }

    public FibonacciHeap makeHeap(){
        FibonacciHeap fibonacciHeap = new FibonacciHeap();

        fibonacciHeap.insert(new Node(5));
        fibonacciHeap.insert(new Node(3));
        fibonacciHeap.insert(new Node(6));
        fibonacciHeap.insert(new Node(1));
        fibonacciHeap.insert(new Node(5));
        fibonacciHeap.insert(new Node(3));

        return fibonacciHeap;
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
