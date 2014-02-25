                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;




/**
 * A subclass of <code>ToolTipDetail</code> for the intention to be at
 * a position.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForIntentionToBeAtPosition extends ToolTipDetail
{
    /* Variables */
    private String atXSp, atYSp;




    /**
     * Constructs a <code>TooltipForIntentionToBeAtPosition</code> to provide
     * details.
     *
     * @param atXSp
     * @param atYSp
     */
    public TooltipForIntentionToBeAtPosition
           (String atXSp,
            String atYSp)
    {
        this.atXSp = atXSp;
        this.atYSp = atYSp;
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
                        " intends to be at position " +
                        "(" + atXSp + ", " + atYSp + ") " +
                        "at time " +
                        getGraphicDisplay().getInstructor().getEndTime() +
                        "msec");

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
               "To Be At (Spatial): " +
               "(" + atXSp + ", " + atYSp + ") " +
               "<br>"));
    }
}
