                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;




/**
 * A subclass of <code>ToolTipDetail</code> for "Augmentation of Intention".
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForAugmentationOfIntentionToApproachWithIntentionToAvoid
             extends ToolTipDetail
{
    /* Variables */
    private String approachFigID, threatFigID, startOfAvoidance;




    /**
     * Constructs a
     * <code>TooltipForAugmentationOfIntentionToApproachWithIntentionToAvoid</code>
     * to provide details.
     *
     * @param approachFigID
     * @param threatFigID
     * @param startOfAvoidance
     */
    public TooltipForAugmentationOfIntentionToApproachWithIntentionToAvoid
           (String approachFigID,
            String threatFigID,
            String startOfAvoidance)
    {
        this.approachFigID = approachFigID;
        this.threatFigID = threatFigID;
        this.startOfAvoidance = startOfAvoidance;
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

        detail.append("approach " + approachFigID + " and starts to " +
                      "avoid " + threatFigID +
                      "at " + startOfAvoidance + "msec");


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
               "Avoid: " + threatFigID + "<br>" +
               "Start Of Avoidance: " + startOfAvoidance + "<br>"));
    }
}
