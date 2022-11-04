package grid.sparseboundedGrid;

import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.grid.AbstractGrid;
import java.util.ArrayList;

public class SparseBoundedGrid<E> extends AbstractGrid<E>
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
            SparseNode node = head[i];
            while(node != null) {
                Location loc = new Location(i, node.getCol());
                theLocations.add(loc);
                node = node.getNext();
            }
        }
        return theLocations;
    }
    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        SparseNode node = getNode(loc);
        if (node != null) return getObj(node);
        else return null;
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        E oldOccupant = remove(loc);

        SparseNode oldhead = head[loc.getRow()];
        SparseGridNode newhead = new SparseGridNode(obj, loc.getCol(), oldhead, null);
        if(oldhead != null) {
            oldhead.setPrev(newhead);
        }
        head[loc.getRow()] = newhead;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        SparseNode node = getNode(loc);
        E r = getObj(node);
        if(r == null) return r;
        if(node != null) {
            SparseNode preNode = node.getPrev();
            SparseNode nextNode = node.getNext();
            if(preNode != null) preNode.setNext(nextNode);
            else if(head[loc.getRow()] != null) head[loc.getRow()] = nextNode;
            if(nextNode != null) nextNode.setPrev(preNode);
        }
        return r;
    }

    private SparseNode getNode(Location loc) {
        SparseNode node = head[loc.getRow()];
        while(node != null) {
            if(node.getCol() == loc.getCol()) return node;
            else node = node.getNext();
        }
        return null;
    }
    private E getObj(SparseNode node) {
        if(node != null) return (E) node.getObject();
        else return null;
    }
}