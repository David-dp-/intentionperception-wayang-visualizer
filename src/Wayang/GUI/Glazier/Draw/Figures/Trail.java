                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.Figures;


/* Import */
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;




/**
 * Convenient class to draw standard shapes, lines and text.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class Trail
{
    /* Constants */
    /* Constants - Data */
    private final static int LEFT_RIGHT = 2;
    private final static double ANGLE_BISECT = 2;
    private final static double HALVE = 2;
    private final static double INITIAL_ARC = 0;
    private final static double THETA_NO_OFFSET = 0;




    /**
     * Prevent the construction of this class.
     */
    private Trail()
    {}


    /**
     * Returns a <code>Path2D.Double</code> that represents an arrow.
     *
     * @param lineTip
     * @param lineTail
     * @param angleDeg
     * @param lengthPx
     * @return
     */
    public static Path2D.Double arrowLine(Point2D.Double lineTip,
                                          Point2D.Double lineTail,
                                          double angleDeg,
                                          double lengthPx)
    {
        Path2D.Double lineArrow;

        lineArrow = arrowHead(lineTip, lineTail, angleDeg, lengthPx);
        lineArrow.moveTo(lineTail.getX(), lineTail.getY());
        lineArrow.lineTo(lineTip.getX(), lineTip.getY());

        return(lineArrow);
    }


    /**
     * Returns a <code>Path2D.Double</code> that represents an arrow head.
     *
     * @param lineTip
     * @param lineTail
     * @param angleDeg
     * @param lengthPx
     * @return
     */
    public static Path2D.Double arrowHead(Point2D.Double lineTip,
                                          Point2D.Double lineTail,
                                          double angleDeg,
                                          double lengthPx)
    {
        return(arrowHead(lineTip, lineTail, angleDeg, lengthPx,
                         THETA_NO_OFFSET));
    }


    /**
     * Returns a <code>Path2D.Double</code> that represents an arrow head.
     *
     * @param lineTip
     * @param lineTail
     * @param angleDeg
     * @param lengthPx
     * @param offset
     * @return
     */
    public static Path2D.Double arrowHead(Point2D.Double lineTip,
                                          Point2D.Double lineTail,
                                          double angleDeg,
                                          double lengthPx,
                                          double offset)
    {
        Path2D.Double arrowHead;
        double polarAngle, angleDeviation, polarCoX, polarCoY;


        arrowHead = new Path2D.Double();

        polarAngle = Math.atan2((lineTip.getY() - lineTail.getY()),
                                (lineTip.getX() - lineTail.getX())) +
                     Math.toRadians(offset);

        angleDeviation = polarAngle + Math.toRadians(angleDeg / ANGLE_BISECT);
        for(int direction = 0; direction < LEFT_RIGHT; direction++)
        {
            polarCoX = lineTip.getX() - lengthPx * Math.cos(angleDeviation);
            polarCoY = lineTip.getY() - lengthPx * Math.sin(angleDeviation);

            arrowHead.moveTo(lineTip.getX(), lineTip.getY());
            arrowHead.lineTo(polarCoX, polarCoY);

            angleDeviation = polarAngle -
                             Math.toRadians(angleDeg / ANGLE_BISECT);
        }

        return(arrowHead);
    }


    /**
     * Sets the position, bounds, angular extents, and closure type of this
     * arc to the specified values. The arc is defined by a center point and
     * a radius rather than a framing rectangle for the full ellipse. Sets the
     * starting angle and angular extent of this arc using two points. The
     * first point is used to determine the angle of the starting point
     * relative to the arc's center. The second point is used to determine the
     * angle of the end point relative to the arc's center. The arc will always
     * be non-empty and extend counterclockwise from the first point around to
     * the second point.
     *
     * @param center
     * @param arcSt
     * @param arcExt
     * @param radius
     * @param closure
     * @return
     */
    public static Arc2D.Double arcByCenter(Point2D.Double center,
                                           Point2D.Double arcSt,
                                           Point2D.Double arcExt,
                                           double radius,
                                           int closure)
    {
        Arc2D.Double arc = new Arc2D.Double();

        arc.setArcByCenter(center.getX(), center.getY(),
                           radius, INITIAL_ARC, INITIAL_ARC, closure);
        arc.setAngles(arcSt, arcExt);

        return(arc);
    }

    
    /**
     * Returns true if <code>Point<code> is within <code>Path2d</code>.
     *
     * @param path
     * @param thickness
     * @param point
     * @return
     */
    public static boolean containsLine(Path2D.Double path, double thickness,
                                       Point point)
    {
        return(path.intersects((point.x - (thickness / HALVE)),
                               (point.y - (thickness / HALVE)),
                               (thickness),
                               (thickness)));
    }
}
