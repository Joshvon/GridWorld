import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;

import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int t;

    public SpiralBug(int length)
    {
        t = 0;
        steps = 0;
        sideLength = length;
    }

    public void act()
    {
        if(!canMove() || t >= 2) return;
        if (steps < sideLength)
        {
            move();
            steps++;
        }
        else
        {
            t++;
            for(int i = 0; i < 5; i++) turn();
            steps = 0;
            sideLength += 1;
        }
    }
}