                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay;


/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.Draw.DrawInstructor;
import Wayang.GUI.Glazier.Draw.Figures.FigureShape;
import Wayang.GUI.Glazier.Draw.Figures.G2DLabel;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import java.awt.Color;




/**
 * <code>GraphicalDisplay</code> an <code>abstract</code> class for the purpose
 * of providing the rudimentary structure for draws.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public abstract class GraphicalDisplay implements GraphicalContainer, Constants
{
    /* Variables */
    private int xPx, yPx;
    private FigureShape figure;
    private Color figColor;
    private DrawInstructor instructor;




    /**
     * Prevent this class from being initiated without parameters.
     */
    private GraphicalDisplay()
    {}


    /**
     * Constructs a <code>GraphicalDisplay</code> to be drawn.
     *
     * @param figColor
     * @param xPx
     * @param yPx
     * @param figure
     */
    public GraphicalDisplay(Color figColor, int xPx, int yPx,
                            FigureShape figure)
    {
        this.figColor = figColor;
        this.xPx = xPx;
        this.yPx = yPx;

        this.figure = figure;
        figure.constructShapeAt(getXPixel(), getYPixel());

        instructor = null;
    }


    /**
     * Sets the <code>DrawInstructor</code> of the
     * <code>GraphicalDisplay</code>.
     *
     * @param instructor
     */
    public void setInstructor(DrawInstructor instructor)
    {
        this.instructor = instructor;
    }


    /**
     * Returns the <code>DrawInstructor</code> of the
     * <code>GraphicalDisplay</code>.
     *
     * @return
     */
    public DrawInstructor getInstructor()
    {
        return(instructor);
    }


    /**
     * Returns the calibrated Y axis.
     *
     * @param yPx
     * @return
     */
    public static double calibrateYAxis(double yPx)
    {
        return(Wayang.GUI.Flasher.FlasherAnalyzer
                     .getStaticFrameSize().getHeight() - yPx);
    }


    /**
     * Returns the yPx position. Calibrated  to the coordinate of the
     * <em>User Space</em>.
     *
     * @return
     */
    public int getYPixel()
    {
        return((int) GraphicalDisplay.calibrateYAxis(yPx));
    }


    /**
     * Returns the xPx position.
     *
     * @return returns the xPx position
     */
    public int getXPixel()
    {
        return(xPx);
    }


    /**
     * Returns the figure.
     *
     * @return returns the figure
     */
    public FigureShape getFigure()
    {
        return(figure);
    }


    /**
     * Returns the color.
     *
     * @return returns the color
     */
    public Color getFigColorShade()
    {
        return(figColor.darker().darker());
    }


    /**
     * Returns the label indicating precedence.
     *
     * @return
     */
    protected String getPrecedentLabel()
    {
        if(!getInstructor().isPrecedentInGroup())
           return(PRECEDENCE_INDICATOR);
        else
           return("");
    }


    /**
     * Returns the graphic label.
     *
     * @return
     */
    protected G2DLabel getAnnotationLabel()
    {
        // DEMO:
//        if(getInstructor().getFigID() == 2)
//        return(new G2DLabel(
//                   getInstructor().getDrawID() + " | " +
//                   getAnnotationText(),
//                   40,
//                   40,
//                   getInstructor().getAnnotationStackOrder(),
//                   getInstructor().getAnnotationShadeOrder()));


            return(new G2DLabel(
                       getInstructor().getDrawID() + " | " +
                       getAnnotationText(),
                       getInstructor().getLargestStackedBounds().getMinX(),
                       (getInstructor().getLargestStackedBounds().getMaxY() +
                        TRAIL_THICKNESS),
                       getInstructor().getAnnotationStackOrder(),
                       getInstructor().getAnnotationShadeOrder()));
    }


    /**
     * Returns the annotation label.
     *
     * @return
     */
    public abstract String getAnnotationText();


    /**
     * Returns <code>true</code> if generated envelope is of a stackable order.
     *
     * @return
     */
    public abstract boolean isStackable();
}
