                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;




/**
 * A subclass of <code>ToolTipDetail</code> for "Noticing".
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForWiggle extends ToolTipDetail
{
    /* Variables */
    private String trajectoryType;




    /**
     * Constructs a <code>TooltipForWiggle</code> to provide details.
     *
     * @param trajectoryType
     */
    public TooltipForWiggle(String trajectoryType)
    {
        this.trajectoryType = trajectoryType;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getAnnotationDetails()
    {
        return("Fig" +
               getGraphicDisplay().getInstructor().getFigID() +
               " wiggles");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder getToolTip()
    {
        return(new StringBuilder(
               "<u><b><i>Ascription:</i></b></u><br>" +
               "Trajectory: " + trajectoryType + "<br>"));
    }
}
