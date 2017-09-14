import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by TomasK on 9/13/2017.
 */
public class CircularDoublyLinkedListTests {
    CircularDoublyLinkedList list;
    ListNode testNode;

    @Before
    public void initializeList(){
        this.list = new CircularDoublyLinkedList();
    }

    @Test
    public void insertAddToEmptySizeTest(){
        insertOneNodeIntoList();
        Assert.assertTrue("List size did not update", list.size() == 1);
    }

    @Test
    public void insertAddToEmptyTest(){
        insertOneNodeIntoList();
        Assert.assertTrue("start not updated to inserted node", list.start() == testNode);
        Assert.assertTrue("start not pointing next to start", list.start().next() == testNode);
    }

    public void insertOneNodeIntoList(){
        testNode = new ListNode(new HeapNode(1));
        list.insert(testNode);
    }

    @Test
    public void insertAddToOneMemberListSizeTest(){
        insertTwoNodesIntoList();
        Assert.assertTrue("List size did not update", list.size() == 2);
    }

    @Test
    public void insertAddToOneMemberListTest(){
        insertTwoNodesIntoList();
        Assert.assertTrue("end not updated to inserted node", list.end() == testNode);
        Assert.assertTrue("start not pointing next to inserted node", list.start().next() == list.end() && list.start().prev() == list.end());
    }

    public void insertTwoNodesIntoList(){
        insertOneNodeIntoList();
        insertOneNodeIntoList();
    }

    @Test
    public void insertAddToPopulatedListSizeTest(){
        randomlyPopulateList();
        int size = list.size();
        list.insert(new ListNode(new HeapNode(1)));

        Assert.assertTrue("size not incremented by 1", list.size() == size + 1);

    }

    @Test
    public void insertAddToPopulatedListTest(){
        randomlyPopulateList();
        insertOneNodeIntoList();

        Assert.assertTrue("start.next() does not point to inserted node", list.start().next() == testNode);
        Assert.assertTrue("node.prev() does not point to start", testNode.prev() == list.start());
        Assert.assertTrue("start.next().next() does not point to inserted node .next()", list.start().next().next() == testNode.next());

    }

    @Test
    public void removeFromEmptyListSizeTest(){
        list.remove(new ListNode(new HeapNode(4)));

        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void removeFromOneNodeListSizeTest(){
        insertOneNodeIntoList();
        list.remove(testNode);

        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void removeFromOneNodeListTest(){
        insertOneNodeIntoList();
        list.remove(testNode);

        Assert.assertTrue(list.start() == null && list.end() == null);
    }

    @Test
    public void removeFromTwoNodeListTest(){
        insertTwoNodesIntoList();
        list.remove(testNode);

        Assert.assertTrue(list.start().next() != testNode && list.start().prev() != testNode);
    }

    @Test
    public void removeStartFromTwoNodeList(){
        insertOneNodeIntoList();
        list.insert(new ListNode(new HeapNode(3)));
        list.remove(testNode);

        Assert.assertTrue("start not updated", list.start() != testNode && list.start().next() == list.start());
    }

    @Test
    public void removeEndFromTwoNodeList(){
        insertTwoNodesIntoList();
        list.remove(testNode);

        Assert.assertTrue("end not updated", list.end() != testNode && list.end().next() == list.end());
    }

    @Test
    public void removeNodeFromPopulatedListSizeTest(){
        randomlyPopulateList();
        int size = list.size();
        list.remove(testNode);

        Assert.assertTrue("size not decremented", list.size() == size - 1);
    }

    @Test
    public void removeNodeFromPopulatedListTest(){
        randomlyPopulateList();
        list.remove(testNode);

        Assert.assertTrue("node not removed from list", !list.find(testNode));
    }

    @Test
    public void removeNodeNotInPopulatedListSizeTest(){
        randomlyPopulateList();
        int size = list.size();
        list.remove(new ListNode(new HeapNode(99)));

        Assert.assertTrue(size == list.size());
    }

    public void randomlyPopulateList(){
        int numberOfNodes = (int)(Math.random() * 50000);

        for(int i = 0; i < numberOfNodes; i++){
            if(i == (numberOfNodes-1) % 200){
                testNode = new ListNode(new HeapNode((int)Math.random() * 2000));
                list.insert(testNode);
            }
            else{
                list.insert(new ListNode(new HeapNode((int)Math.random() * 2000)));

            }
        }
    }
}
