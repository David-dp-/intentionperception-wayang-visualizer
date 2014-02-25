                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;


/* Import */
import Wayang.Constants;
import Wayang.ExceptionHandler;
import Wayang.GUI.Glazier.OverlayDialog;
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;




/**
 * <code>FlasherPanel</code> creates the GUI interface for the flash player. It
 * includes the flash player screen panel and control panel.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class FlasherPanel extends JPanel implements Constants
{
    /* Variables */
    private GroupLayout layout;
    private FlasherScreen flasher;
    private OverlayDialog glazier;
    private FlasherControl controller;


    /**
     * Constructs a flash player.
     */
    public FlasherPanel()
    {
        flasher = new FlasherScreen();
        controller = new FlasherControl(flasher, glazier);

        constructGUI();
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
     * Construct the GUI interface for the flash player.
     */
    private void constructGUI()
    {
        layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateContainerGaps(false);
        layout.setAutoCreateGaps(false);

        layout.setHorizontalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)

            .addGroup
            (
              layout.createSequentialGroup()
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                               OVERLAY_SCREEN_EXPAND, OVERLAY_SCREEN_EXPAND)
              .addComponent(flasher, GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                              OVERLAY_SCREEN_EXPAND, OVERLAY_SCREEN_EXPAND)
            )

            .addGroup
            (
              layout.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addComponent(controller,GroupLayout.PREFERRED_SIZE,
                                       GroupLayout.PREFERRED_SIZE,
                                       GroupLayout.DEFAULT_SIZE)
            )
        );

        layout.setVerticalGroup
        (
            layout.createSequentialGroup()
                    .addGroup
                    (
                      layout.createSequentialGroup()
                      .addGap(OVERLAY_SCREEN_EXPAND, OVERLAY_SCREEN_EXPAND,
                              OVERLAY_SCREEN_EXPAND)
                      .addComponent(flasher, GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE)
                      .addGap(OVERLAY_SCREEN_EXPAND, OVERLAY_SCREEN_EXPAND,
                              OVERLAY_SCREEN_EXPAND)
                      .addComponent(controller,GroupLayout.PREFERRED_SIZE,
                                               GroupLayout.PREFERRED_SIZE,
                                               GroupLayout.DEFAULT_SIZE)
                    )
        );
    }


    /**
     * Returns the minimum size for the controller <code>JPanel</code>.
     *
     * @return the minimum size as a <code>Dimension</code>
     */
    public Dimension getMinControllerSize()
    {
        return(controller.getPreferredSize());
    }
}
