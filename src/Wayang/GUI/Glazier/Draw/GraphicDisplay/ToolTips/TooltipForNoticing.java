                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;




/**
 * A subclass of <code>ToolTipDetail</code> for "Noticing".
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForNoticing extends ToolTipDetail
{
    /* Variables */
    private String noticeFigID;




    /**
     * Constructs a <code>TooltipForNoticing</code> to provide details.
     *
     * @param noticeFigID
     */
    public TooltipForNoticing(String noticeFigID)
    {
        this.noticeFigID = noticeFigID;
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
                        " noticed Fig" + noticeFigID +
                        " sometime between " +
                        getGraphicDisplay().getInstructor().getStartTime() +
                        " and " +
                        getGraphicDisplay().getInstructor().getEndTime() +
                        " msec");


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
               "Noticed Fig: " + noticeFigID + "<br>"));
    }
}
