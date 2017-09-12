import org.junit.Assert;
import org.junit.Test;

/**
 * Created by TomasK on 9/12/2017.
 */
public class NodeTests {

    @Test
    public void createNodeTest(){
        Assert.assertTrue(new Node(5) != null);
    }

    @Test
    public void getParentTest(){
        Node node = new Node(5);
        Node childNode = new Node(3);

        childNode.setParent(node);

        Assert.assertTrue(childNode.getParent() == node);
    }

    @Test
    public void singleChildDoublyLinkedTest(){
        Node node = new Node(5);

        Assert.assertTrue(node.getLeft() == node && node.getRight() == node);
    }

    @Test
    public void childListCreation(){
        Node node = new Node(5);
        Node child1 = new Node(3);
        Node child2 = new Node(4);

        node.setChild(child1);
        node.setChild(child2);

        Assert.assertTrue( node.getChild() == child1 ? child1.getLeft() == child2 : child2.getLeft() == child1);
    }

    @Test
    public void numberOfChildren(){
        Node node = new Node(5);
        Node child1 = new Node(3);
        Node child2 = new Node(4);
        Node child3 = new Node(2);

        node.setChild(child1);
        node.setChild(child2);
        node.setChild(child3);

        Assert.assertTrue(node.getDegree() == 3);
    }

}
