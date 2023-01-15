package grid.sparseboundedGrid;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class SparseBoundedGridRunner {
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("UnboundedGrid");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}