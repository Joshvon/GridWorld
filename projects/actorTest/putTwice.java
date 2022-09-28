import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;

public class putTwice
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Actor alice = new Actor();
        world.add(alice);
        alice.putSelfInGrid(alice.getGrid(), new Location(0, 0));
        world.show();
    }
}