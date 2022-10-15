package critter.crabCritter;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            Location loc = a.getLocation().getAdjacentLocation(getDirection() + Location.AHEAD);
            if(getGrid().isValid(loc)) a.moveTo(loc);
            else a.removeSelfFromGrid();
        }
    }
}