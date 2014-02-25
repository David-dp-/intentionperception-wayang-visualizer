                                    /*OHM*/


/* Package */
package Wayang;


/* Import */
import Wayang.GUI.FrontEnd;
import javax.swing.SwingUtilities;




/**
 * Research: Ascribing Intention Behaviour
 * Implementation: Wayang
 * <p>
 * The front-end for the <code>Wayang</code> system. While ascribing
 * computations are performed via <code>Eclispe</code>.
 * <p>
 * It functions the role of an UI/MMI allowing users to control the
 * <code>Wayang</code> system and to retrieve computed data from
 * <code>Wayang</code> and display its estimations and parsing processes via
 * a UI display.
 * <p>
 * <em>Citations, References & Acknowledgments:</em>
 * <ul>
 * <li><a href="http://download.oracle.com/javase/tutorial/">
 *     The Java Tutorials</a>
 * <li><a href="http://www.jpackages.com/">JPackages</a> for their
 *     <a href="http://www.jpackages.com/jflashplayer/">JFlashPlayer</a>
 * <li><a href="http://findicons.com/">Find Icons</a>
 * </ul>
 * <p>
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class Wayang
{
    /* Variables */
    public static String RUNNING_PATH;


    /**
     * The application entry for <code>Wayang</code>.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                Wayang AIB = new Wayang();

                AIB.launch();
            }
        });
    }


    /**
     * Empty Constructor declared private to prevent the initialisation of
     * <code>Wayang</code>.
     */
    private Wayang()
    {
        RUNNING_PATH = Resource.getSourceLocation(
                        this.getClass()).replaceFirst("Wayang.jar", "");
    }


    /**
     * Launches the front-end application.
     */
    private void launch()
    {
        FrontEnd GUI = new FrontEnd();
    }
}
