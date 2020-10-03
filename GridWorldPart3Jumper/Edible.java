import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 *  The purpose of this interface is to facilitate the determination
 *  of which objects a Jumper can remove by jumping on them.
 *  It is awkward and error prone to modify Jumper's canMove when a new
 *  actor class is created.  Instead, this design uses the Edible
 *  interface.  If an actor implements Edible, it can be jumped on,
 *  otherwise it can't.  Currently Jumper implements Edible.
 *  Note that implementing Edible does not require an actor class to
 *  implement any additional methods.  The only method in Edible
 *  (removeSelfFromGrid) is already implemented in Actor, which is
 *  extended by the new class.
 *
 *  @author  Doris Wei and Twisha Sundararajan
 *  @version Oct 16, 2018
 *  @author  Period: 5
 *  @author  Assignment: GridWorldP3Jumper
 *
 *  @author  Sources: Robert Glen Martin
 */
public interface Edible
{
    /**
     * Removes this actor from its grid. <br />
     * Precondition: This actor is contained in a grid
     */
    public void removeSelfFromGrid();
}

