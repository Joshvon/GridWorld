package critters.rockHound;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;

public class RockHound extends Critter
{
    public RockHound() {
        super();
    }
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (a instanceof Rock)
                a.removeSelfFromGrid();
        }
    }
}