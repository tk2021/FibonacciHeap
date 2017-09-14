import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by TomasK on 9/13/2017.
 */
public class CircularDoublyLinkedListTests {
    CircularDoublyLinkedList list;
    HeapNode testNode;

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
        ListNode listTestNode = list.find(testNode);

        Assert.assertTrue("start not updated to inserted node", list.start() == listTestNode);
        Assert.assertTrue("start not pointing next to start", list.start().next() == listTestNode);
    }

    public void insertOneNodeIntoList(){
        testNode = new HeapNode(11);
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
        ListNode listTestNode = list.find(testNode);

        Assert.assertTrue("end not updated to inserted node", list.end() == listTestNode);
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
        list.insert(new HeapNode(1));

        Assert.assertTrue("size not incremented by 1", list.size() == size + 1);

    }

    @Test
    public void insertAddToPopulatedListTest(){
        randomlyPopulateList();
        insertOneNodeIntoList();

        ListNode listTestNode = list.find(testNode);

        Assert.assertTrue("start.next() does not point to inserted node", list.start().next() == listTestNode);
        Assert.assertTrue("node.prev() does not point to start", listTestNode.prev() == list.start());
        Assert.assertTrue("start.next().next() does not point to inserted node .next()", list.start().next().next() == listTestNode.next());

    }

    @Test
    public void removeFromEmptyListSizeTest(){
        list.remove(new HeapNode(4));

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
        ListNode listTestNode = list.find(testNode);


        Assert.assertTrue(list.start().next() != listTestNode && list.start().prev() != listTestNode);
    }

    @Test
    public void removeStartFromTwoNodeList(){
        insertOneNodeIntoList();
        list.insert(new HeapNode(3));
        list.remove(testNode);
        ListNode listTestNode = list.find(testNode);


        Assert.assertTrue("start not updated", list.start() != listTestNode && list.start().next() == list.start());
    }

    @Test
    public void removeEndFromTwoNodeList(){
        insertTwoNodesIntoList();
        list.remove(testNode);
        ListNode listTestNode = list.find(testNode);


        Assert.assertTrue("end not updated", list.end() != listTestNode && list.end().next() == list.end());
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


        Assert.assertTrue("node not removed from list", list.find(testNode) == null);
    }

    @Test
    public void removeNodeNotInPopulatedListSizeTest(){
        randomlyPopulateList();
        int size = list.size();
        list.remove(new HeapNode(99));

        Assert.assertTrue(size == list.size());
    }

    public void randomlyPopulateList(){
        int numberOfNodes = (int)(Math.random() * 50000);

        for(int i = 0; i < numberOfNodes; i++){
            if(i == (numberOfNodes-1) % 200){
                testNode = new HeapNode((int)Math.random() * 2000);
                list.insert(testNode);
            }
            else{
                list.insert(new HeapNode((int)Math.random() * 2000));

            }
        }
    }
}
