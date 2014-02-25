                                    /*OHM*/


/* Package */
package Wayang.GUI;


/* Import */
/* Import - Standard */
import Wayang.GUI.Flasher.FlasherPanel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;




/**
 * <code>FrontEnd</code> is the GUI front-end of the <code>Wayang</code> system.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 * @see FlasherPanel
 */
public class FrontEnd
{
    /* Constants */
    /* Constants - Text */
    private final static String MAIN_TITLE = "Wayang";

    /* Variables */
    private ApplicationFrame mainFrame;
    private Container frameContent;
    private FlasherPanel flasher;


    /**
     * Constructs an instance of <code>Library</code>.
     */
    public FrontEnd()
    {
        mainFrame = new ApplicationFrame(MAIN_TITLE,
                                         ApplicationFrame.Mode.MODE_MAIN);
        frameContent = mainFrame.getContentPane();
        flasher = new FlasherPanel();

        constructGUI();
    }


    /**
     * GUI construction in progress.
     */
    private void constructGUI()
    {
        Dimension minSize;
        Insets framer;

        frameContent.add(flasher);

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.toFront();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getRootPane().setDoubleBuffered(true);
        mainFrame.setAlwaysOnTop(true);
        mainFrame.setResizable(false);

        minSize = flasher.getMinControllerSize();
        framer = mainFrame.getInsets();
        minSize = new Dimension(
                      minSize.width + framer.left + framer.right,
                      minSize.height + framer.top + framer.bottom);
        mainFrame.setMinimumSize(minSize);
    }
}
