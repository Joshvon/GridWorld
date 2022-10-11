import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class Jumper extends Bug
{
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if(!gr.isValid(next))
            return false;
        Location next_next = next.getAdjacentLocation(getDirection());
        Actor neighbor1 = gr.get(next);
        if (!gr.isValid(next_next))
            return false;
        Actor neighbor2 = gr.get(next_next);
        return (neighbor2 == null) || (neighbor2 instanceof Flower)
    }
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next_next = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next_next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    public void act() {
        if(canJump())
            jump();
        else
            turn();
    }
}