                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;




/**
 * A subclass of <code>ToolTipDetail</code> for intention to react.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForIntentionToApproachOrAvoid extends ToolTipDetail
{
    /* Variables */
    private String intentionType, targetFigID;




    /**
     * Constructs a <code>TooltipForIntentionToApproachOrAvoid</code> to
     * provide details.
     *
     * @param intentionType
     * @param targetFigID
     */
    public TooltipForIntentionToApproachOrAvoid
           (String intentionType,
            String targetFigID)
    {
        this.intentionType = intentionType;
        this.targetFigID = targetFigID;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getAnnotationDetails()
    {
        StringBuilder detail;


        detail = new StringBuilder("Fig" +
                        getGraphicDisplay().getInstructor().getFigID() +
                        " intends to ");

        if(intentionType.contains("approach"))
            detail.append("approach ");
        else
            detail.append("avoid ");

        detail.append("Fig" + targetFigID);


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
               "Intention: " + intentionType + "<br>" +
               "Target: " + targetFigID + "<br>"));
    }
}
