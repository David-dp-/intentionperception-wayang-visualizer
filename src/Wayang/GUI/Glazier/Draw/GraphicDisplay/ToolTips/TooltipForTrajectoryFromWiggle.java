                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;




/**
 * A subclass of <code>ToolTipDetail</code> for "Noticing".
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForTrajectoryFromWiggle extends ToolTipDetail
{
    /* Variables */
    private String trajectoryType, xSp, ySp, magXSp, magYSp;




    /**
     * Constructs a <code>TooltipForTrajectoryFromWiggle</code> to provide details.
     *
     * @param trajectoryType
     * @param xSp
     * @param ySp
     * @param magXSp
     * @param magYSp
     */
    public TooltipForTrajectoryFromWiggle
           (String trajectoryType,
            String xSp,
            String ySp,
            String magXSp,
            String magYSp)
    {
        this.trajectoryType = trajectoryType;
        this.xSp = xSp;
        this.ySp = ySp;
        this.magXSp = magXSp;
        this.magYSp = magYSp;
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
                        " moves ");

        if(trajectoryType.contains("linear"))
            detail.append("linearly ");
        else
            detail.append("along a curved path ");

        detail.append("as it wiggles");

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
               "Trajectory: " + trajectoryType + "<br>" +
               "Mag X: " + magXSp + "<br>" +
               "Mag Y: " + magYSp + "<br>" +
               "Spatial X: " + xSp + "<br>" +
               "Spatial Y: " + ySp + "<br>"));
    }
}
