                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay;


/* Import */
import Wayang.GUI.Glazier.Draw.Figures.FigureShape;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;




/**
 * <code>GraphicalDisplayForStationaryTrajectory</code> a subclass of
 * <code>GraphicalDisplay</code> that handles stationary trajectory.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class GraphicalDisplayForStationaryTrajectory
       extends GraphicalDisplayTrajectory
{
    /**
     * Constructs a <code>GraphicalDisplayForStationaryTrajectory</code> to be
     * drawn.
     *
     * @param figColor
     * @param xPx
     * @param yPx
     * @param figure
     * @param xSp
     * @param ySp
     */
    public GraphicalDisplayForStationaryTrajectory
           (Color figColor, int xPx, int yPx,
            FigureShape figure,
            String xSp, String ySp)
    {
        super(figColor, xPx, yPx, figure, xSp, ySp, MOTIONLESS, MOTIONLESS);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2D)
    {
        g2D.setStroke(getInstructor().getEpistemicStatus().getFigureStroke());
        g2D.setColor(getFigColorShade());

        getFigure().draw(g2D);
        getAnnotationLabel().draw(g2D);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Area getEnvelope()
    {
        Area envelop;

        envelop = new Area(getFigure().getEnvelope());
        envelop.transform(AffineTransform.getScaleInstance(
                         (1 / (envelop.getBounds2D().getWidth())) *
                               (envelop.getBounds2D().getWidth() *
                                1.5),
                         (1 / (envelop.getBounds2D().getHeight())) *
                               (envelop.getBounds2D().getHeight() *
                                1.5)));
        envelop.transform(AffineTransform.getTranslateInstance(
                         getFigure().getEnvelope().getBounds2D().getCenterX() -
                         envelop.getBounds2D().getCenterX(),
                         getFigure().getEnvelope().getBounds2D().getCenterY() -
                         envelop.getBounds2D().getCenterY()));

        return(envelop);

        //return(getFigure().getEnvelope());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getAnnotationText()
    {
        return(getPrecedentLabel() + "Stationary , CV: " +
               GRAPHIC_NUMBER_FORMAT.format(getInstructor().getCV()));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicalContainer contains(Point point)
    {
        if(getFigure().contains(point) != null)
            return(this);
        else
            return(null);
    }
}
