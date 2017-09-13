import java.util.ArrayList;

public class Node{
    private Node right, left, parent, child;
    private int value, degree;

    Node(int value){
        this.value = value;
        this.right = this;
        this.left = this;
        this.parent = null;
        this.degree = 0;
    }

    public void setChild(Node child){
        if(child == null) {
            this.child = null;
            return;
        }
        this.degree++;
        if(this.child == null) {
            this.child = child;
            this.child.left = child;
            this.child.right = child;
        }
        else{
            Node leftSibling = this.child.getLeft();
            this.child.setLeft(child);
            child.setRight(this.child);
            leftSibling.setRight(child);
            child.setLeft(leftSibling);
        }
        child.setParent(this);
    }

    public void changeChild(Node child){
        this.child = child;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node getParent(){
        return parent;
    }

    public Node getChild(){
        return child;
    }

    public Node getRight(){
        return right;
    }

    public Node getLeft(){
        return left;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public int getDegree(){
        return this.degree;
    }

    public void decrementDegree(){
        this.degree--;
    }

    public int getValue(){
        return this.value;
    }



}