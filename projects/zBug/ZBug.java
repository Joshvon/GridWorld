import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int t;

    public ZBug(int length)
    {
        setDirection(Location.EAST);
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
            if(t == 1) setDirection(Location.SOUTHEAST);
            if(t == 2) setDirection(Location.EAST);
            steps = 0;
        }
        
    }
}