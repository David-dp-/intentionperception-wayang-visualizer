                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.Figures;




/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;




/**
 * <code>Circle</code> a subclass of <code>FigureShape</code> handles
 * the drawing of a circle.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class Circle extends FigureShape implements Constants
{
    /* Variables */
    private int diameter;
    private Shape circle, circleContain;




    /**
     * Constructs a circle with the specified diameter.
     *
     * @param diameter the diameter of the circle to be drawn
     */
    public Circle(int diameter)
    {
        this.diameter = diameter;
        constructShapeAt(0, 0);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void constructShapeAt(double x, double y)
    {
        double dimension;

        dimension = (diameter + FIGURE_PADDING);
        circle = constructCircle(x, y, dimension);

        dimension += FIGURE_THICKNESS;
        circleContain = constructCircle(x, y, dimension);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public FigureShape copyThis()
    {
        return(new Circle(diameter));
    }


    /**
     * Returns a newly constructed circle </code>Shape</code>.
     *
     * @param x
     * @param y
     * @param radius
     * @return
     */
    private Shape constructCircle(double x, double y, double dimension)
    {
        return(new Ellipse2D.Double((x - (dimension/2)), (y - (dimension/2)),
                                    dimension, dimension));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2D)
    {
        g2D.draw(circle);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Area getEnvelope()
    {
        return(new Area(circle));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicalContainer contains(Point point)
    {
        if(circleContain.contains(point))
            return(this);
        else
            return(null);
    }
}
