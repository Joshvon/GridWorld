package critter.crabCritter;

import info.gridworld.grid.Location;

public class QuickCrab extends CrabCritter
{
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (int dir : dirs) {
            Location loc1 = getLocation().getAdjacentLocation(getDirection() + dir);
            Location loc2 = loc1.getAdjacentLocation(getDirection() + dir);
            if (getGrid().isValid(loc2) && getGrid().get(loc2) == null) locs.add(loc2);
        }
        if(locs.size == 0) return super.getMoveLocations();
        return locs;
    }
}