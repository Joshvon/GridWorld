public class SparseNode 
{
    private int col;
    private Object object;
    private SparseNode next;

    public SparseNode() {
        col = -1;
        object = null;
        next = null;
    }
    public SparseNode(int c, Object o) {
        col = c;
        object = o;
        next = null;
    }
    public int getCol() {
        return col;
    }
    public int getObject() {
        return object;
    }
    public void addByHead(SparseNode new_node) {
        SparseNode node = this;
        while(node.next != null) {
            node = node.next;
        }
        node.next = new_node;
    }
    public void deleteByHead(int col) {
        SparseNode node = this;
        while(node.next != null) {
            if(node.next.getCol() == col) {
                if(node.next.next == null) node.next = null;
                else node.next = node.next.next;
            }
        }
    }
}