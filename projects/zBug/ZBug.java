import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;

import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int t;

    public ZBug(int length)
    {
        turn();
        turn();
        t = 0;
        steps = 0;
        sideLength = length;
    }

    public void act()
    {
        if(t >= 2) return;
        if (steps < sideLength)
        {
            move();
            steps++;
        }
        else
        {
            t++;
            if(t == 1) for(int i = 0; i < 3; i++) turn();
            if(t == 2) for(int i = 0; i < 5; i++) turn();
            steps = 0;
        }
        if(!canMove()) {
            t++;
            return;
        }
    }
}