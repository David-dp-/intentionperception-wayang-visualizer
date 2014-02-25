                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;


/* Import */
import java.text.DecimalFormat;




/**
 * A subclass of <code>ToolTipDetail</code> for single forces.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForSingleForce extends ToolTipDetail
{
    /* Variables */
    private String forceType, forceMag, forceSrc;




    /**
     * Constructs a <code>TooltipForSingleForce</code> to provide details.
     *
     * @param forceType
     * @param forceMag
     * @param forceSrc
     */
    public TooltipForSingleForce(String forceType,
                                 String forceMag,
                                 String forceSrc)
    {
        this.forceType = forceType;
        this.forceMag = forceMag;
        this.forceSrc = forceSrc;
    }


    /**
     * @return the forceType
     */
    public String getForceType()
    {
        return forceType;
    }


    /**
     * @return the forceMag
     */
    public String getForceMag()
    {
        return forceMag;
    }


    /**
     * @return the forceSrc
     */
    public String getForceSrc()
    {
        return forceSrc;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getAnnotationDetails()
    {
        DecimalFormat forceFormat;
        StringBuilder detail;


        forceFormat = new DecimalFormat("####E0");

        detail = new StringBuilder("Fig" +
                        getGraphicDisplay().getInstructor().getFigID() +
                        " is ");

        if(forceType.contains("attractive"))
            detail.append("attracted ");
        else
            detail.append("repulsed ");

        detail.append("by Fig" + forceSrc +
                      "with magnitude " +
                      forceFormat.format(new Double(getForceMag())
                                                    .doubleValue()));


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
               "Force Mag: " + getForceMag() + "<br>" +
               "Force Src: " + getForceSrc() + "<br>" +
               "Force Type: " + getForceType() + "<br>"));
    }
}
