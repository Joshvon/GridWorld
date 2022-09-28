import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

public class removeTwice
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Actor alice = new Actor();
        world.add(alice);
        alice.removeSelfFromGrid();
        alice.removeSelfFromGrid();
        world.show();
    }
}