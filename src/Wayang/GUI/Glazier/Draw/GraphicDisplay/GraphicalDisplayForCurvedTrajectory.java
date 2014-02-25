                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay;


/* Import */
import Wayang.GUI.Glazier.Draw.CurveDirection;
import Wayang.GUI.Glazier.Draw.Figures.CurvedTrail;
import Wayang.GUI.Glazier.Draw.Figures.FigureShape;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;




/**
 * <code>GraphicalDisplayForCurvedTrajectory</code> a subclass of
 * <code>GraphicalDisplay</code> that handles linear trajectory.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class GraphicalDisplayForCurvedTrajectory
       extends GraphicalDisplayTrajectory
{
    /* Variables */
    private CurvedTrail trail;


    /**
     * Constructs a <code>GraphicalDisplayForCurvedTrajectory</code> to be
     * drawn.
     *
     * @param figColor
     * @param xPx
     * @param yPx
     * @param fittedCircleXPx
     * @param fittedCircleYPx
     * @param fittedCircleRadiusPx
     * @param direction
     * @param figure
     * @param xSp
     * @param ySp
     * @param magXSp
     * @param magYSp
     * @param fittedCircleXSp
     * @param fittedCircleYSp
     * @param fittedCircleRadiusSp
     * @param fittedErrorSp
     */
    public GraphicalDisplayForCurvedTrajectory(
           Color figColor, int xPx, int yPx,
           int fittedCircleXPx, int fittedCircleYPx,
           int fittedCircleRadiusPx,
           CurveDirection direction,
           FigureShape figure,
           String xSp, String ySp,
           String magXSp, String magYSp,
           String fittedCircleXSp, String fittedCircleYSp,
           String fittedCircleRadiusSp,
           String fittedErrorSp)
    {
        super(figColor, xPx, yPx, figure, xSp, ySp, magXSp, magYSp);

        trail = new CurvedTrail(getTrailPoint(magXSp, magYSp),
                                new Point.Double(getXPixel(), getYPixel()),
                                new Rectangle.Double(
                                     fittedCircleXPx,
                                     GraphicalDisplay.calibrateYAxis(
                                                      fittedCircleYPx),
                                     fittedCircleRadiusPx,
                                     fittedCircleRadiusPx),
                                     direction);
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
        return(getPrecedentLabel() + "Curve, CV: " +
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
