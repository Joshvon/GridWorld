package spiralBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class SpiralBugRunner 
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        UnboundedGrid grid = new UnboundedGrid<SpiralBug>();
        ActorWorld world = new ActorWorld(grid);
        SpiralBug alice = new SpiralBug(3);
        alice.setColor(Color.ORANGE);
        world.add(new Location(7, 8), alice);
        world.show();
    }  
}