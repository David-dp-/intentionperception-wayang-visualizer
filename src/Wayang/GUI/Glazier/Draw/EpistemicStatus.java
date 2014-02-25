                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw;


/* Import */
/* Import - Standard */
import Wayang.Constants;
import java.awt.Stroke;




/**
 * <code>EpistemicStatus</code> of draw instructions.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public enum EpistemicStatus implements Constants
{
    /* Constants */
    OBSERVATION(),
    PREDICTION();


    /**
     * Returns specified <code>Stroke</code> for figures.
     *
     * @return
     */
    public Stroke getFigureStroke()
    {
        if(this == EpistemicStatus.OBSERVATION)
            return(OBSERVED_FIGURE_STROKE);
        else
            return(PREDICTION_FIGURE_STROKE);
    }


    /**
     * Returns specified <code>Stroke</code> for trails.
     *
     * @return
     */
    public Stroke getTrailStroke()
    {
        if(this == EpistemicStatus.OBSERVATION)
            return(OBSERVED_TRAIL_STROKE);
        else
            return(OBSERVED_TRAIL_STROKE);
    }
}
