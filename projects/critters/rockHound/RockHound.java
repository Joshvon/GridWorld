package critters.rockHound;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.util.ArrayList;

public class RockHound extends Critter
{
    public RockHound() {
        super();
    }
    public void processActors(ArrayList<Actors> actors)
    {
        for (Actor a : actors)
        {
            if (a instanceof Rock)
                a.removeSelfFromGrid();
        }
    }
}