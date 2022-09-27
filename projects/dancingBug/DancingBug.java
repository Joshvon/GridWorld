import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int index;
    private int[] dancearray;

    public DancingBug(int[] arr)
    {
        index = 0;
        dancearray = arr;
    }
    public void act()
    {
        if(canmove()) move();
        if(index == dancearray.length) index = 0;
        else {
            for(int i = 0; i < dancearray[index]; i++) turn();
            index++;
        }
    }
}