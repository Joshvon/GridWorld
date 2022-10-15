package grid.sparseboundedGrid;

import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

public class SparseBoundedGrid extends AbstractGrid<Object>
{
    private SparseNode[] head;
    private int rows;
    private int cols;

    public SparseBoundedGrid(int r, int c) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        head = new SparseNode[rows];
        for(int i = 0; i < rows; i++) head[0] = new SparseNode();
        rows = r;
        cols = c;
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for(int i = 0; i < getNumRows(); i++) {
            SpaeseNode node = head[i];
            while(node.next != null) {
                node = node.next;
                Location loc = new Location(i, node.getCol());
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
        return theLocations;
    }
    public Object get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        SparseNode node = head[loc.getRow()];
        while(node.next != null) {
            node = node.next;
            if(node.getCol() == loc.getCol()) return node.getObject();
        }
        return null;
    }

    public Object put(Location loc, Object obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        Object oldOccupant = get(loc);
        SparseNode node = head[loc.getRow()];
        node.addByHead(obj);
        return oldOccupant;
    }

    public Object remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        Object r = get(loc);
        head[loc.getRow()].removeByHead(loc.getCol());
        return r;
    }
}