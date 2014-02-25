                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier;


/* Import */
import Wayang.GUI.Flasher.FlasherScreen;
import Wayang.GUI.Glazier.Draw.DrawList;




/**
 * <code>OverlaySync</code> is the synchronizing mechanism between
 * <code>FlasherScreen</code> and <code>OverlayGlass</code>. It ensures that
 * the overlay functionality timely annates over the respective <em>SWF<em>
 * frames.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class OverlaySync implements Runnable
{
    /* Variables */
    private FlasherScreen screen;
    private OverlayGlass glass;
    private DrawList drawer;




    /**
     * Prevent this class from being initiated without parameters.
     */
    private OverlaySync()
    {}


    /**
     * Constructs an instance of <code>OverlaySync</code>.
     *
     * @param flash the <code>FlasherScreen</code> over which the overlay effect
     *              is to be maintained
     * @param overlay the <code>OverlayGlass</code> which is to provide
     *        annotations for the flash animation
     */
    public OverlaySync(FlasherScreen flash, OverlayGlass overlay)
    {
        screen = flash;
        glass = overlay;
        drawer = glass.getDrawn();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void run()
    {
        while(true)
        {
            drawer.setCurrentFrame(screen.getCurrentFrame());
            glass.repaint();
        }
    }
}
