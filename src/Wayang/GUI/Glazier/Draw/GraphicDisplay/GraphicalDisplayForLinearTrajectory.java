                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay;


/* Import */
import Wayang.GUI.Glazier.Draw.Figures.FigureShape;
import Wayang.GUI.Glazier.Draw.Figures.LinearTrail;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;




/**
 * <code>GraphicalDisplayForLinearTrajectory</code> a subclass of
 * <code>GraphicalDisplay</code> that handles linear trajectory.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class GraphicalDisplayForLinearTrajectory
       extends GraphicalDisplayTrajectory
{
    /* Variables */
    private LinearTrail trail;


    /**
     * Constructs a <code>GraphicalDisplayForLinearTrajectory</code> to be
     * drawn.
     *
     * @param figColor
     * @param xPx
     * @param yPx
     * @param figure
     * @param xSp
     * @param ySp
     * @param magXSp
     * @param magYSp
     */
    public GraphicalDisplayForLinearTrajectory(Color figColor, int xPx, int yPx,
                            FigureShape figure,
                            String xSp, String ySp,
                            String magXSp, String magYSp)
    {
        super(figColor, xPx, yPx, figure, xSp, ySp, magXSp, magYSp);

        trail = new LinearTrail(getTrailPoint(magXSp, magYSp),
                          new Point.Double(getXPixel(), getYPixel()));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2D)
    {
        g2D.setStroke(getInstructor().getEpistemicStatus().getTrailStroke());
        g2D.setColor(getFigColorShade());

        trail.draw(g2D);
        getAnnotationLabel().draw(g2D);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Area getEnvelope()
    {
        Area envelope;

        envelope = getFigure().getEnvelope();
        envelope.add(trail.getEnvelope());

        return(envelope);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getAnnotationText()
    {
        return(getPrecedentLabel() + "Linear, CV: " +
               GRAPHIC_NUMBER_FORMAT.format(getInstructor().getCV()));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicalContainer contains(Point point)
    {
        if(trail.contains(point) != null)
            return(this);
        else
            return(null);
    }
}
