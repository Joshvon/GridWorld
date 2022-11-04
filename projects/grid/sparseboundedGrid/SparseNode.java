package grid.sparseboundedGrid;

public class SparseNode 
{
    private int col;
    private Object object;
    private SparseNode next;
    private SparseNode pre;

    public SparseNode(Object object, int col, SparseNode next, SparseNode pre) {
        this.col = col;
        this.object = object;
        this.next = next;
        this.pre = pre;
    }

    public int getCol() {
        return col;
    }
    public Object getObject() {
        return object;
    }
    public SparseNode getNext() {
        return next;
    }
    public SparseNode getPrev() {
        return pre;
    }
    
    public void setObj(Object object) {
        this.object = object;
    }
    public void setNext(SparseNode next) {
        this.next = next;
    }
    public void setPrev(SparseNode pre) {
        this.pre = pre;
    }
}