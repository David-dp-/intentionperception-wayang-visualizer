                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier;


/* Import */
import Wayang.Constants;
import Wayang.ExceptionHandler;
import Wayang.GUI.ApplicationFrame;
import Wayang.GUI.Flasher.FlasherScreen;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JDialog;




/**
 * <code>OverlayDialog</code> provides the GUI frame for an overlay window. It
 * is an extension of <code>JDialog</code>.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class OverlayDialog extends JDialog implements Constants
{
    /* Consatnts - Text */
    /** Thread name for synchronizing overlay annotation. */
    protected static final String THREAD_SYNC = "SYNCHRONIZER";

    /* Variables */
    private ApplicationFrame parent;
    private Container content;
    private FlasherScreen screen;
    private OverlayGlass glass;
    private Thread syncThread;




    /**
     * Constructs an instance of <code>OverlayDialog</code>.
     *
     * @param frame The <code>JFrame</code> to which the <code>JDialog</code> is
     *              to be attached to.
     * @param flash The <code>FlasherScreen</code> over which the overlay effect
     *              is to be maintained.
     */
    public OverlayDialog(ApplicationFrame frame, FlasherScreen flash)
    {
        super(frame, false);

        parent = frame;
        screen = flash;

        content = getContentPane();
        glass = new OverlayGlass();

        constructDialog();
    }


    /**
     * Prevents the automatic serialization of the class.
     *
     * @param oin
     * @throws IOException
     */
    private void readObject(ObjectInputStream oin) throws IOException
    {
        ExceptionHandler.NotSerializableException(this.getName());
    }


    /**
     * Prevents the automatic serialization of the class.
     *
     * @param oos
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException
    {
        ExceptionHandler.NotSerializableException(this.getName());
    }


    /**
     * Constructs a <code>OverlayDialog</code> with the specific settings.
     */
    private void constructDialog()
    {
        content.add(glass);

        setUndecorated(true);
        setBackground(COLOR_TRANSPARENT_FILL);
        pack();

        syncThread = new Thread(new OverlaySync(screen, glass), THREAD_SYNC);
        syncThread.start();

        parent.addWindowStateListener(new ShadowParent());
    }


    /**
     * Overlay annotation.
     */
    public void annotate()
    {
        glass.annotate();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        setLocation(screen.getLocationOnScreen());
        setSize(screen.getSize().width + OVERLAY_SCREEN_EXPAND,
                screen.getSize().height + OVERLAY_SCREEN_EXPAND);
    }


    /**
     * {@inheritDoc}
     *
     * @return the size of the parent
     */
    @Override
    public Dimension getPreferredSize()
    {
        return(new Dimension(getParent().getSize()));
    }


    /**
     * Implements a <code>WindowStateListener</code> to monitor it parent to
     * shadow its state.
     */
    private class ShadowParent implements WindowStateListener
    {
        /**
         * {@inheritDoc}
         */
        @Override
        public void windowStateChanged(WindowEvent e)
        {
            if(e.getNewState() == Frame.ICONIFIED)
                setVisible(false);

            if(e.getNewState() == Frame.NORMAL)
                setVisible(true);
        }
    }
}
