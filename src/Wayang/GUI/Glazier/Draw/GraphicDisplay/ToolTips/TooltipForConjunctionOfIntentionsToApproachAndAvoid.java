                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;


/* Import */
import java.text.DecimalFormat;




/**
 * A subclass of <code>ToolTipDetail</code> for intention to react.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForConjunctionOfIntentionsToApproachAndAvoid
             extends ToolTipDetail
{
    /* Variables */
    private String approachFigID, threatFigID;




    /**
     * Constructs a
     * <code>TooltipForConjunctionOfIntentionsToApproachAndAvoid</code> to
     * provide details.
     *
     * @param approachFigID
     * @param threatFigID
     */
    public TooltipForConjunctionOfIntentionsToApproachAndAvoid
           (String approachFigID,
            String threatFigID)
    {
        this.approachFigID = approachFigID;
        this.threatFigID = threatFigID;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getAnnotationDetails()
    {
        DecimalFormat forceFormat;
        StringBuilder detail;


        detail = new StringBuilder("Fig" +
                        getGraphicDisplay().getInstructor().getFigID() +
                        " intends to ");

        detail.append("approach Fig" + approachFigID +
                      " and " +
                      "avoid Fig" + threatFigID);


        return(detail.toString());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder getToolTip()
    {
        return(new StringBuilder(
               "<u><b><i>Ascription:</i></b></u><br>" +
               "Approach: " + approachFigID + "<br>" +
               "Avoid: " + threatFigID + "<br>"));
    }
}
