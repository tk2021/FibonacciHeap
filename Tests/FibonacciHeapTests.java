import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

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
    public void popAllTest(){
        FibonacciHeap heap = makeHeap();
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            list.add(heap.pop().getValue());
            System.out.println("\nnew min: " + heap.getMin().getValue());

            Node temp = heap.getMin();
            Node child = temp.getChild();

            for(int j = 0; j < 5; j++){
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

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(5);
        collection.add(5);
        collection.add(3);
        collection.add(3);
        collection.add(1);
        collection.add(6);

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
