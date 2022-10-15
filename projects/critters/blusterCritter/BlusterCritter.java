package critters.blusterCritter;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter
{
    private int c;

    public BlusterCritter(int c)
    {
        this.c = c;
    }

    public ArrayList<Actor> getActors()
    {
        Location loc = getLocation();
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        for(int r = loc.getRow() - 2; r < loc.getRow() + 2; r++) {
            for(int c = loc.getCol() - 2; c < loc.getCol() +2; c++) {
                Location location = new Location(r, c);
                if(getGrid().isValid(location)) {
                    Actor actor = getGrid().get(location);
                    if(actor != null && actor != this) neighbors.add(actor);
                }
            }
        }
        return neighbors;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        int cnt = 0;
        for (Actor a : actors)
        {
            if (a instanceof Critter) cnt++;
        }
        if(cnt < this.c) changeColor(0);
        else changeColor(1);
    }

    /**
     * make critter color brighten or darken
     * @param mode  0:brighten 1:darken
     */
    public void changeColor(int mode)
    {
        Color c = getColor();
        if(mode == 0) {
            int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        }
        else {
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        }
    }
}