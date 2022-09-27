import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class DancingBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] a = new int[]{4, 5, 3, 3};
        DancingBug alice = new DancingBug(a);
        alice.setColor(Color.ORANGE);
        world.add(new Location(0, 0), alice);
        world.show();
    }
}