package grid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class UnboundedGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;
    private int sidelength = 16;

    public UnboundedGrid() {
        occupantArray = new Object[sidelength][sidelength];
    }
    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && 0 <= loc.getCol();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for(int i = 0; i < sidelength; i++) {
            for(int j = 0; j < sidelength; j++) {
                Location loc = new Location(i, j);
                if(get(loc) != null) theLocations.add(loc); 
            }
        }
        return theLocations;
    }

    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= sidelength || loc.getCol() >= sidelength) {
            return null;
        }
        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getCol() >= sidelength || loc.getRow() >= sidelength) {
            changeTheGrid(loc);
        }
        if (obj == null)
            throw new NullPointerException("obj == null");
        
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= sidelength || loc.getCol() >= sidelength) {
            return null;
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

    private void changeTheGrid(Location loc) {
        int doublelength = sidelength * 2;
        while (doublelength <= loc.getCol() || doublelength <= loc.getRow()) {
            doublelength = doublelength + doublelength;
        }
        Object[][] tmp = new Object[doublelength][doublelength];

        for (int i = 0; i < sidelength; i++) {
            for (int j = 0; j < sidelength; j++) {
                tmp[i][j] = occupantArray[i][j];
            }
        }
        sidelength = doublelength;
        occupantArray = tmp;
    }
}