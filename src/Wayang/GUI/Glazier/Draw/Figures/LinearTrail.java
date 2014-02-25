                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.Figures;


/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;




/**
 * <code>LinearTrail</code> a subclass of <code>FigureShape</code> handles
 * the drawing of trails.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class LinearTrail extends FigureShape implements Constants
{
    /* Variables */
    private Point.Double start, end;
    private Path2D.Double trail;
    private Area envelope;


    /**
     * Constructs a trail at the specified points.
     *
     * @param start
     * @param end
     */
    public LinearTrail(Point.Double start, Point.Double end)
    {
        this.start = new Point.Double(start.getX(), start.getY());
        this.end = new Point.Double(end.getX(), end.getY());

        constructTrail();
    }


    /**
     * Adapter Implementation.
     * {@inheritDoc}
     */
    @Override
    public void constructShapeAt(double x, double y)
    {}


    /**
     * {@inheritDoc}
     */
    @Override
    public FigureShape copyThis()
    {
        return(new LinearTrail(start, end));
    }


    /**
     * Construct trail.
     */
    private void constructTrail()
    {
        trail = Trail.arrowLine(end, start,
                                          ARROW_ANGLE, ARROW_LENGTH);


        envelope = new Area(new Ellipse2D.Double(
                                    (start.getX() -
                                     ((TRAIL_THICKNESS + ENVELOPE_PADDING)/2)),
                                    (start.getY() -
                                     ((TRAIL_THICKNESS + ENVELOPE_PADDING)/2)),
                                    (start.distance(end) + ENVELOPE_PADDING),
                                    (TRAIL_THICKNESS + ENVELOPE_PADDING)));
        envelope.transform(AffineTransform.getRotateInstance(
                                           (end.getX() - start.getX()),
                                           (end.getY() - start.getY()),
                                           start.getX(), start.getY()));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2D)
    {
        g2D.draw(trail);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Area getEnvelope()
    {
        return(envelope);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicalContainer contains(Point point)
    {
        if(Trail.containsLine(trail, TRAIL_THICKNESS, point))
            return(this);
        else
            return(null);
    }
}
