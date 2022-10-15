package grid.sparseboundedGrid;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class SparseBoundedGridRunner {
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new SparseBoundedGrid(10, 10));
        Bug bug = new Bug();
        world.add(new Location(5, 5), bug);
        world.show();
    }
}