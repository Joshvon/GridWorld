import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;

public class removeTwice
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Actor alice = new Actor();
        world.add(alice);
        
        Grid gr = alice.getGrid();
        alice.removeSelfFromGrid();
        alice.putSelfInGrid(gr, new Location(0, 0));
    }
}