                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay;


/* Import */
import Wayang.GUI.Glazier.Draw.Figures.FigureShape;
import java.awt.Color;
import java.awt.geom.Point2D;




/**
 * <code>GraphicalDisplayTrajectory</code> a subclass of
 * <code>GraphicalDisplay</code> that handles trajectory.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public abstract class GraphicalDisplayTrajectory extends GraphicalDisplay
{
    /* Variables */
    private String xSp, ySp, magXSp, magYSp;


    /**
     * Constructs a <code>GraphicalDisplayTrajectory</code> to be
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
    public GraphicalDisplayTrajectory
           (Color figColor, int xPx, int yPx,
            FigureShape figure,
            String xSp, String ySp,
            String magXSp, String magYSp)
    {
        super(figColor, xPx, yPx, figure);


        this.xSp = xSp;
        this.ySp = ySp;
        this.magXSp = magXSp;
        this.magYSp = magYSp;
    }


    /**
     * Returns the x coordinate in spatial unit.
     *
     * @return
     */
    public String getXSpatial()
    {
        return(xSp);
    }


    /**
     * Returns the Y coordinate in spatial unit.
     *
     * @return
     */
    public String getYSpatial()
    {
        return(ySp);
    }


    /**
     * Returns the magnitude for the xPx axis.
     *
     * @return
     */
    public String getMagX()
    {
        return(magXSp);
    }


    /**
     * Returns the magnitude for the yPx axis.
     *
     * @return
     */
    public String getMagY()
    {
        return(magYSp);
    }


    /**
     * Returns the <code>Point2D.Double</code> of the trail.
     *
     * @param magX
     * @param magY
     * @return
     */
    public Point2D.Double getTrailPoint(String magX, String magY)
    {
        return(new Point2D.Double((getXPixel() -
                                  (new Double(magX).doubleValue() *
                                  TRAIL_FACTOR)),
                                  (getYPixel() +
                                  (new Double(magY).doubleValue() *
                                  TRAIL_FACTOR))));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStackable()
    {
        return(false);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder getToolTip()
    {
        return(new StringBuilder(
               "<u><b><i>Trajectory:</i></b></u><br>" +
               "Mag X: " + getMagX() + "<br>" +
               "Mag Y: " + getMagY() + "<br>" +
               "Spatial X: " + getXSpatial() + "<br>" +
               "Spatial Y: " + getYSpatial() + "<br>").append(
               getFigure().getToolTip()));
    }
}
