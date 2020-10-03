import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;


/**
 * In the beginning of the 19th century a practical method for solving the
 * Knight's tour emerged. In "Des Resselsprungs einfachste und allgemeinste
 * Losung" (Schmalkalden, 1823) H. C. Warnsdorff presented his method of
 * constructing knight's tours.
 * 
 * The aim is to avoid creating dead ends - squares from which the knight cannot
 * get further without getting to an already visited square. For that reason the
 * possible squares to be chosen next are examined before every move. One counts
 * the number of free new choices - entrances - every one of them has, and then
 * moves to the square with the lowest number of new choices. <br />
 * 
 * @author Twisha Sundararajan
 * @version Nov 19, 2018
 * @author Period: 5
 * @author Assignment: KnightCritterTour
 *
 */
public class KnightTourCritter extends KnightCritter
{
    // An analysis of the accessibility of an 8x8 grid is shown below.
    // A square with an accessibility of 8 means that it can be
    // approached from 8 different other squares. A corner square is
    // rated at 2, while the edges are rated at 3 or 4. It makes sense
    // to try and visit squares with lower accessibility values first,
    // leaving the more accessible middle squares for later in the
    // algorithm.
    public int[][] accessGrid = { { 2, 3, 4, 4, 4, 4, 3, 2 },
        { 3, 4, 6, 6, 6, 6, 4, 3 }, { 4, 6, 8, 8, 8, 8, 6, 4 },
        { 4, 6, 8, 8, 8, 8, 6, 4 }, { 4, 6, 8, 8, 8, 8, 6, 4 },
        { 4, 6, 8, 8, 8, 8, 6, 4 }, { 3, 4, 6, 6, 6, 6, 4, 3 },
        { 2, 3, 4, 4, 4, 4, 3, 2 } };


    /**
     * Determines, out of the potential moves, which one moves toward a location
     * with the lowest accessibility. It will return the location of first move
     * with the corresponding accessibility from the accessibility grid that is
     * the lowest. If there is a tie, it will return the location of smaller
     * move value in the list of locations. If all accessibility values are < 0,
     * the method returns null
     * 
     * @param locs
     *            valid possible move locations
     * @return location corresponding to the lowest first move indicated by the
     *         accessibility matrix.
     */
    public Location getBestMove( ArrayList<Location> locs )
    {
        Location bestMove = null;

        int smlNum = 8;

        for ( int i = 0; i < locs.size(); i++ )
        {
            int row = locs.get( i ).getRow();
            int col = locs.get( i ).getCol();

            if ( accessGrid[row][col] < smlNum )
            {
                smlNum = accessGrid[row][col];
                bestMove = locs.get( i );
            }
        }
        return bestMove;
    }


    /**
     * loc contains the coordinates of the latest square to be marked on the
     * access grid. The accessibility numbers of all valid moves reachable from
     * loc are decreased by 1. This algorithm will allow accessibility numbers
     * to go negative.
     * 
     * @param loc
     *            location of the latest square to be marked on the access grid
     */
    public void updateAccess( Location loc )
    {
       
        for ( int i = 0; i < MAX_MOVES; i++ )
        {
            int[] x = { -2, -1, 1, 2, 2, 1, -1, -2 };
            int[] y = { 1, 2, 2, 1, -1, -2, -2, -1 };

            int row = loc.getRow() + x[i];
            int col = loc.getCol() + y[i];

            if ( 0 <= row && row < MAX_MOVES && 0 <= col
                && col < MAX_MOVES )
            {
                accessGrid[row][col]--;
            }
        }
        int r = loc.getRow();
        int c = loc.getCol();
        accessGrid[r][c] = -1; 

    } 
        


    /**
     * Selects the location for the next move. Implemented to pick the possible
     * location with lowest accessibility, or to return the current location
     * (getLocation()) if <code>locs</code> has size 0. After the best move has
     * been chosen, the accessibility of the moves from the current location are
     * updated <br />
     * 
     * Postcondition: (1) The returned location is an element of
     * <code>locs</code>, this critter's current location, or <code>null</code>.
     * (2) The state of all actors is unchanged.
     * 
     * @param locs
     *            the possible locations for the next move
     * @return the location that was selected for the next move.
     */
    public Location selectMoveLocation( ArrayList<Location> locs )
    {
        if ( locs.size() == 0 ) 
        {
            return getLocation();
        }
        Location newLoc = getBestMove( locs );
        updateAccess( newLoc );
        return newLoc;
    }
}