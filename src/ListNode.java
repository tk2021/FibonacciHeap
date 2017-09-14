/**
 * Created by TomasK on 9/13/2017.
 */
public class ListNode {
    private HeapNode data;
    private ListNode prev;
    private ListNode next;

    ListNode(HeapNode data){
        this.data = data;
        this.prev = this;
        this.next = this;
    }

    public ListNode next(){
        return next;
    }

    public ListNode prev(){
        return prev;
    }

    public void setNext(ListNode node){
        this.next = node;
    }

    public void setPrev(ListNode node){
        this.prev = node;
    }
}
