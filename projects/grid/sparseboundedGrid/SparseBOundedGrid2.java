package grid.sparseboundedGrid;

import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.grid.AbstractGrid;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> 
{
    private Map<Location, E> occupantMap;
    private int rows;
    private int cols;

    public SparseBOundedGrid2(int r, int c) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantMap = new HashMap<Location, E>();
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
        for (Location loc : occupantMap.keySet()){
            theLocations.add(loc);
        }
        return theLocations;
    }

    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        return occupantMap.remove(loc);
    }
}