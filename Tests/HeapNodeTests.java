import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeapNodeTests {
    HeapNode testNode;
    HeapNode insertedNode;

    @Before
    public void createHeapNode(){
        testNode = new HeapNode(6);
        testNode.addChild(new HeapNode(5));
        testNode.addChild(new HeapNode(6));
        insertedNode = new HeapNode(0);
        testNode.addChild(insertedNode);
    }

    @Test
    public void insertToChildListDegreeTest(){
        int degree = testNode.degree();
        testNode.addChild(new HeapNode(4));
        Assert.assertTrue(testNode.degree() == degree + 1);
    }

    @Test
    public void removeChildListDegreeTest(){
        int degree = testNode.degree();
        testNode.removeChild(insertedNode);
        Assert.assertTrue(testNode.degree() == degree - 1);
    }

    @Test
    public void removeChildListTest(){
        testNode.removeChild(insertedNode);
        Assert.assertTrue(testNode.getChildList().find(insertedNode) == null);
    }

    @Test
    public void insertChildListTest(){
        HeapNode newInsertNode = new HeapNode(0);
        testNode.addChild(newInsertNode);
        Assert.assertTrue(testNode.getChildList().find(newInsertNode) != null);
    }
}