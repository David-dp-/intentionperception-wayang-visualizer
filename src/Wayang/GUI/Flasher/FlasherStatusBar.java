                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;


/* Import - Standard */
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;

/* Import - Custom */
import Wayang.Constants;
import Wayang.ExceptionHandler;




/**
 * <code>FlasherStatusBar</code> creates a status bar for the GUI interface
 * It updates and displays the current functional status of the player and the
 * current frame of the player.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class FlasherStatusBar extends JPanel implements Constants
{
    /* Constants */
    /* Constants - Text */
    private static final String CONTAINER_GAP = "    ";
    private static final String FUNCTION = "Wayang";
    private static final String FRAME = " "  + CONTAINER_GAP;


    /* Constants - Enums */
    /**
     * <code>Enum</code> <code>Status</code> contains the possible statuses of
     * <code>FlasherStatusBar</code>.
     */
    public static enum Status
    {
        /* Variables */
        /* Variables - Enum */
        /** Enable slider bar. */
        STATUS_BAR_ENABLE("Status Bar Is Enabled"),
        /** Disable slider bar. */
        STATUS_BAR_DISABLE("Status Bar Is Disabled");

        /** Default status. */
        protected final static Status STATUS_BAR_DEFAULT = STATUS_BAR_ENABLE;


        /* Variables - Data */
        private final String description;


        /**
         * Constructs an <code>Enum</code> with the specified description.
         *
         * @param description the <code>enum</code>'s description
         */
        Status(String description)
        {
            this.description = description;
        }


        /**
         * Returns the description of the <code>enum</code> constant.
         *
         * @return the description as of type <code>String</code>
         */
        public String getDescription()
        {
            return(description);
        }


        /**
         * Returns the description of the <code>enum</code> constant.
         *
         * @return the description as of type <code>String</code>
         */
        public boolean isEnabled()
        {
            return(STATUS_BAR_ENABLE == this);
        }
    }


    /* Variables */
    private FlasherLabel statusFunction, statusFrame;
    private Status status;


    /**
     * Constructs a status bar for the flash player controls.
     */
    public FlasherStatusBar()
    {
        super();
        constructStatusBar();
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
     * Construct the GUI for the status bar.
     */
    private void constructStatusBar()
    {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        status = Status.STATUS_BAR_DEFAULT;
        constructLabel();
        add(statusFunction, BorderLayout.LINE_START);
        add(statusFrame, BorderLayout.LINE_END);
    }


    /**
     * Updates the status of the <code>StatusBar</code>.
     *
     * @param status the status of type <code>Status</code>
     */
    public void setEnableUpdate(Status status)
    {
        this.status = status;
    }


    /**
     * Construct <code>JLabels</code> to update and display statuses.
     */
    private void constructLabel()
    {
        statusFunction = new FlasherLabel(REEL_ICON_PATH, FUNCTION);
        statusFrame = new FlasherLabel(FRAME);
    }


    /**
     * Updates the functional status of the player.
     *
     * @param text the text to display
     */
    public void updateFunction(String text)
    {
        if(status.isEnabled())
            statusFunction.setText(text);
        else
            statusFunction.setText(FUNCTION);
    }


    /**
     * Updates the frame status of the player.
     *
     * @param text the current frame value to display
     */
    public void updateFrame(String text)
    {
        if(status.isEnabled())
            statusFrame.setText(text + CONTAINER_GAP);
        else
            statusFrame.setText(FRAME);
    }
}
