package Jumper;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class JumperRunner 
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        world.add(new Location(0, 0), jumper);
        world.show();
    }  
}