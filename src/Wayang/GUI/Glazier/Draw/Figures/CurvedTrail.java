                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.Figures;


/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.Draw.CurveDirection;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;




/**
 * <code>CurvedTrail</code> a subclass of <code>FigureShape</code> handles
 * the drawing of trails.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class CurvedTrail extends FigureShape implements Constants
{
    /* Constants */
    private static double ARC_ARROW_OFFSET = 85.0;


    /* Variables */
    private Point.Double start, end;
    private Rectangle.Double fittedCircle;
    private CurveDirection direction;
    private Arc2D.Double trail;
    private Path2D.Double arrow;
    private Area envelope;




    /**
     * Constructs a trail at the specified points.
     *
     * @param start
     * @param end
     * @param fittedCircle
     * @param direction
     */
    public CurvedTrail(Point.Double start, Point.Double end,
                       Rectangle.Double fittedCircle,
                       CurveDirection direction)
    {
        this.start = new Point.Double(start.getX(), start.getY());
        this.end = new Point.Double(end.getX(), end.getY());
        this.fittedCircle = fittedCircle;
        this.direction = direction;

        trail = new Arc2D.Double();

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
        return(new CurvedTrail(start, end,
                               fittedCircle,
                               direction));
    }


    /**
     * Construct trail.
     */
    private void constructTrail()
    {
        double frameOffset;
        Rectangle2D frame;


        if(direction == CurveDirection.ANTICLOCKWISE)
        {
            trail = Trail.arcByCenter(new Point2D.Double(
                                                    fittedCircle.getX(),
                                                    fittedCircle.getY()),
                                                    start, end,
                                                    fittedCircle.getHeight(),
                                                    Arc2D.OPEN);

            arrow = Trail.arrowHead(end, new Point2D.Double(
                                                    fittedCircle.getX(),
                                                    fittedCircle.getY()),
                                              ARROW_ANGLE, ARROW_LENGTH,
                                              -ARC_ARROW_OFFSET);
        }
        else
        {
            trail = Trail.arcByCenter(new Point2D.Double(
                                                    fittedCircle.getX(),
                                                    fittedCircle.getY()),
                                                    end, start,
                                                    fittedCircle.getHeight(),
                                                    Arc2D.OPEN);

            arrow = Trail.arrowHead(end, new Point2D.Double(
                                                    fittedCircle.getX(),
                                                    fittedCircle.getY()),
                                              ARROW_ANGLE, ARROW_LENGTH,
                                              ARC_ARROW_OFFSET);
        }


        frame = trail.getFrame();
        frameOffset = TRAIL_THICKNESS + ENVELOPE_PADDING;
        envelope = new Area(new Ellipse2D.Double(
                                    (frame.getX() +
                                     frameOffset - (frameOffset/2)),
                                    (frame.getY() +
                                     frameOffset - (frameOffset/2)),
                                    (frame.getHeight() -
                                     frameOffset),
                                    (frame.getWidth() -
                                     frameOffset)));

        envelope.exclusiveOr(new Area(new Ellipse2D.Double(
                                    (frame.getX() -
                                     frameOffset + (frameOffset/2)),
                                    (frame.getY() -
                                     frameOffset + (frameOffset/2)),
                                    (frame.getHeight() +
                                     frameOffset),
                                    (frame.getWidth() +
                                     frameOffset))));

        frameOffset = TRAIL_THICKNESS + TRAIL_THICKNESS + ENVELOPE_PADDING;
        envelope.intersect(new Area(new Arc2D.Double(
                                            trail.getX() -
                                            (frameOffset / 2),
                                            trail.getY() -
                                            (frameOffset / 2),
                                            trail.getWidth() +
                                            frameOffset,
                                            trail.getHeight() +
                                            frameOffset,
                                            trail.getAngleStart() -
                                            (ENVELOPE_PADDING / 2),
                                            trail.getAngleExtent() +
                                            ENVELOPE_PADDING,
                                            Arc2D.PIE)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2D)
    {
        g2D.draw(trail);
        g2D.draw(arrow);
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
        if(Trail.containsLine(new Path2D.Double(trail),
                                        TRAIL_THICKNESS, point) ||
           Trail.containsLine(arrow,
                                        TRAIL_THICKNESS, point))
            return(this);
        else
            return(null);
    }
}
