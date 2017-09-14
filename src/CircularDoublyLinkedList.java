/**
 * Created by TomasK on 9/13/2017.
 */
public class CircularDoublyLinkedList {
    private ListNode start, end;
    private int size;

    public CircularDoublyLinkedList(){
        this.size = 0;
        this.start = null;
        this.end = null;
    }

    public int size(){
        return size;
    }

    private void incrementSize(){
        size++;
    }

    private void decrementSize(){
        size--;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public ListNode start(){
        return start;
    }

    public ListNode end(){
        return end;
    }

    public void insert(ListNode node){
        if(isEmpty()){
            start = node;
            end = node;
            node.setNext(node);
            node.setPrev(node);
        }
        else if(size() == 1){
            end = node;
            start.setNext(end);
            start.setPrev(end);
            end.setNext(start);
            end.setPrev(start);
        }
        else{
            ListNode temp = start.next();
            start.setNext(node);
            node.setPrev(start);
            temp.setPrev(node);
            node.setNext(temp);
        }

        incrementSize();
    }

    public void remove(ListNode node){
        if(size() == 0){
            return;
        }
        if(!find(node))
            return;
        else if(size() == 1){
            start = null;
            end = null;
        }
        else{
            if(start == node){
                start = start.next();
            }
            else if(end == node){
                end = end.prev();
            }
            ListNode temp = node.prev();
            temp.setNext(node.next());
            node.next().setPrev(temp);
        }

        decrementSize();
    }

    public boolean find(ListNode node){
        ListNode current = start;
        do{
            if(node == current)
                return true;
            current = current.next();
        }while(current != start);

        return false;
    }

}
