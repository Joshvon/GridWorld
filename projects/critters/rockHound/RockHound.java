package critters.rockHound;

import info.gridworld.actor.Critter;

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