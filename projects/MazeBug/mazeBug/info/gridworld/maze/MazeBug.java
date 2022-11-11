package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	private Set<E> hasVisited = new HashSet();

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} 
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		for(Location location : gr.getEmptyAdjacentLocations(loc)) {
			if(location.getRow() == loc.getRow() || location.getCol() == loc.getCol())
				valid.add(location);
		}
		for(Actor actor : gr.getNeighbors(loc)) {
			if(actor instanceof Flower) {
				Location location = actor.getLocation();
				if(location.getRow() == loc.getRow() || location.getCol() == loc.getCol())
					valid.add(location);
			}
			if(actor instanceof Rock && actor.getColor() == Color.RED) {
				Location location = actor.getLocation();
				if(location.getRow() == loc.getRow() || location.getCol() == loc.getCol())
					isEnd = true;
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return false;
		Location loc = getLocation();
		ArrayList<Location> locs = getValid(loc);
		for(Location location : locs) {
			if(hasVisited.contains(location)) locs.remove(location);
		}
		if(locs.size() > 0) {
			int index = (int) (Math.random()* locs.size());
			this.next = locs.get(index);
			locs.add(loc);
			crossLocation.push(locs);
		}
		else {
			ArrayList<Location> l = crossLocation.pop();
			next = l.get(l.size() - 1);
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		hasVisited.add(loc);
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
