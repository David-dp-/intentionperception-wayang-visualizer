                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay;


/* Import */
import Wayang.GUI.Glazier.Draw.DrawList;
import Wayang.GUI.Glazier.Draw.Figures.FigureShape;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.ToolTipDetail;
import Wayang.GUI.Glazier.Draw.GraphicalContainer;
import Wayang.GUI.Glazier.OverlayGlass;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;




/**
 * <code>GraphicalDisplayForHighLevelAscription</code> a subclass of
 * <code>GraphicalDisplay</code> that handles high level ascription.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class GraphicalDisplayForHighLevelAscription
                      extends GraphicalDisplay
{
    /* Variables */
    private ArrayList<Point> xyPx;
    private ArrayList<FigureShape> figureList;
    private Area envelope;
    private ToolTipDetail tooltipDetails;




    /**
     * Constructs a <code>GraphicalDisplayForHighLevelAscription</code> to be
     * drawn.
     *
     * @param figColor
     * @param xyPx
     * @param figure
     * @param tooltipDetails
     */
    public GraphicalDisplayForHighLevelAscription(
            Color figColor,
            ArrayList<Point> xyPx,
            FigureShape figure,
            ToolTipDetail tooltipDetails)
    {
        super(figColor, xyPx.get(xyPx.size()-1).x, xyPx.get(xyPx.size()-1).y,
              figure);

        this.xyPx = xyPx;
        this.tooltipDetails = tooltipDetails;

        figureList = new ArrayList<>();

        tooltipDetails.setGraphicDisplay(this);

        constructDisplay();
    }


    /**
     * Construct display details.
     */
    private void constructDisplay()
    {
        for(Point xy : xyPx)
        {
            FigureShape copy;

            copy = getFigure().copyThis();
            copy.constructShapeAt(xy.getX(),
                                  GraphicalDisplay.calibrateYAxis(xy.getY()));
            figureList.add(copy);
        }


        envelope = new Area();
        for(FigureShape fig : figureList)
        {
            envelope.add(fig.getEnvelope());
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2D)
    {
        Area ancestorEnvelop;


        g2D.setColor(getFigColorShade());
        g2D.setStroke(getInstructor().getEpistemicStatus().getFigureStroke());


        g2D.setComposite(AlphaComposite.
                          getInstance(AlphaComposite.SRC_OVER, ENVELOPE_ALPHA));


        ancestorEnvelop = getInstructor().getSizedAncestorEnvelope();
        g2D.fill(ancestorEnvelop);

        g2D.setComposite(OverlayGlass.getG2DAlphaComposite());


        if((ENVELOPE_BORDER) &&
           (getInstructor().getDrawID() == DrawList.baseInstruct.getDrawID()))
            g2D.draw(ancestorEnvelop);


        getAnnotationLabel().draw(g2D);
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
    public String getAnnotationText()
    {
        return(getPrecedentLabel() +
               tooltipDetails.getAnnotationDetails().toString() +
               " , CV: " +
               GRAPHIC_NUMBER_FORMAT.format(getInstructor().getCV()));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStackable()
    {
        return(true);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicalContainer contains(Point point)
    {
        if(getInstructor().getSizedAncestorEnvelope().contains(point))
            return(this);
        else
            return(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder getToolTip()
    {
        return(tooltipDetails.getToolTip().append(
               getFigure().getToolTip()));
    }
}
