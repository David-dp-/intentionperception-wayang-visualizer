                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw;


/* Import */
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;




/**
 * <code>GraphicalContainer</code> an <code>interface</code> for the purpose of
 * providing the rudimentary structure for containing drawn graphics.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public interface GraphicalContainer
{
    /**
     * Draw the graphical content.
     *
     * @param g2D the <code>Graphics</code> resource to be drawn
     */
    public abstract void draw(Graphics2D g2D);


    /**
     * Returns the enveloping shape.
     *
     * @return
     */
    public abstract Area getEnvelope();


    /**
     * Returns highest ordered <code>GraphicalContainer</code> within the
     * specified <code>point</code>.
     *
     * @param point
     * @return
     */
    public abstract GraphicalContainer contains(Point point);


    /**
     * Returns tool tip content.
     *
     * @return
     */
    public abstract StringBuilder getToolTip();
}
