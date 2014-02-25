                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;


/* Import */
import java.text.DecimalFormat;




/**
 * A subclass of <code>ToolTipDetail</code> for conjunction of forces.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class TooltipForConjunctionOfForces extends ToolTipDetail
{
    /* Variables */
    private String magRepulsionForceSp, magAttractionForceSp,
                   repulsorID, attractorID;




    /**
     * Constructs a <code>TooltipForConjunctionOfForces</code> to provide
     * details.
     *
     * @param magRepulsionForceSp
     * @param magAttractionForceSp
     * @param repulsorID
     * @param attractorID
     */
    public TooltipForConjunctionOfForces
           (String magRepulsionForceSp,
            String magAttractionForceSp,
            String repulsorID,
            String attractorID)
    {
        this.magRepulsionForceSp = magRepulsionForceSp;
        this.magAttractionForceSp = magAttractionForceSp;
        this.repulsorID = repulsorID;
        this.attractorID = attractorID;
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

        detail.append("attracted by Fig" + attractorID +
                      "with magnitude " +
                      forceFormat.format(new Double(magAttractionForceSp)
                                                    .doubleValue()) +
                      " and " +
                      "repulsed by Fig" + repulsorID +
                      "with magnitude " +
                      forceFormat.format(new Double(magRepulsionForceSp)
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
               "Repulsor: " + repulsorID + "<br>" +
               "Repulsion Mag: " + magRepulsionForceSp + "<br>" +
               "Attractor: " + attractorID + "<br>" +
               "Attraction Mag: " + magAttractionForceSp + "<br>"));
    }
}
