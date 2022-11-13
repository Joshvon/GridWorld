package info.gridworld.maze;

import info.gridworld.grid.*;
import info.gridworld.actor.Actor;

import java.awt.Color;
import java.util.ArrayList;

public class SmarterMazeBug extends MazeBug{

    private int[] factor = {0, 0, 0, 0};

    public SmarterMazeBug() {
        setColor(Color.GREEN);
		last = new Location(0, 0);
    }

    @Override
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
		if (gr == null)
			return false;
		Location loc = getLocation();
		ArrayList<Location> locs = getValid(loc);
        for(int i = locs.size() -1; i >= 0; i--) {
			Location location = locs.get(i);
			if(hasVisited.contains(location)) locs.remove(i);
		}
        if(locs.size() > 0) {
			next = getNext(locs);
			locs.add(loc);
			crossLocation.push(locs);
		}
        else {
            ArrayList<Location> l = crossLocation.pop();
            next = l.get(l.size() - 1);
            if (next.getDirectionToward(getLocation()) == Location.SOUTH) {
                // north
                factor[1]--;
            }
            if (next.getDirectionToward(getLocation()) == Location.NORTH) {
                // south
                factor[0]--;
            }
            if (next.getDirectionToward(getLocation()) == Location.WEST) {
                // east
                factor[2]--;
            }
            if (next.getDirectionToward(getLocation()) == Location.EAST) {
                // west
                factor[3]--;
            }
        }
        return true;
    }

    private Location getNext(ArrayList<Location> locs) {
        Location[] validloc = { null, null, null, null };
        for (int i = 0; i < locs.size(); i++) {
            if (locs.get(i).getDirectionToward(getLocation()) == Location.SOUTH) {
                // north
                validloc[0] = locs.get(i);
            }
            if (locs.get(i).getDirectionToward(getLocation()) == Location.NORTH) {
                // south
                validloc[1] = locs.get(i);
            }
            if (locs.get(i).getDirectionToward(getLocation()) == Location.WEST) {
                // east
                validloc[3] = locs.get(i);
            }
            if (locs.get(i).getDirectionToward(getLocation()) == Location.EAST) {
                // west
                validloc[2] = locs.get(i);
            }
        }
        int target = 0;
        for(int i = 0; i < validloc.length; i++) {
            if(validloc[i] != null) {
                if(factor[i] > factor[target]) target = i;
            }
        }
        factor[target]++;
        return validloc[target];
    }
}