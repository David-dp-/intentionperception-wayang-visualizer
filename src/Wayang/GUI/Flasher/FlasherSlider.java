                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;

/* Import */
/* Import - Standard */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

/* Import - Custom */
import Wayang.Constants;
import Wayang.ExceptionHandler;

import com.jpackages.jflashplayer.*;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.JLabel;




/**
 * A template for the flash player slider. The <code>FlashPanel</code> flash
 * player screen is attached to the slider. It permits the interaction between
 * the flash animation frame and its representation via <code>JSlider</code>.
 * The <code>FlasherStatusBar</code> is also attached to provide the means for
 * the slider to directly update its value to the status bar.
 * <p>
 * Due to the functionality of the slider to function as a frame selector and
 * update, several of its functionalities are unique to its interface. To meet
 * these requirements, the user interface applied to the slider needs to be
 * modified to cater to these needs.
 * <p>
 * <font color="FF0000"><em>Limitation:</em></font> Currently
 * <code>JSlider</code> are implemented with the value
 * range of an <code>int</code>. While <code>FlashPanel</code> deals with
 * frames in the range of a <code>long</code>.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 * @see FlashPanel
 */
@SuppressWarnings("serial")
public class FlasherSlider extends JSlider implements Constants
{
    /* Constants */
    /* Constants - Enums */
    /**
     * <code>Enum</code> <code>Status</code> contains the possible statuses of
     * <code>FlasherSlider</code>.
     */
    public static enum Status
    {
        /* Variables */
        /* Variables - Enum */
        /** Enable slider bar. */
        SLIDER_BAR_ENABLE("Slider Bar Is Enabled"),
        /** Disable slider bar. */
        SLIDER_BAR_DISABLE("Slider Bar Is Disabled");

        /** Default status. */
        protected final static Status SLIDER_BAR_DEFAULT = SLIDER_BAR_ENABLE;


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
            return(SLIDER_BAR_ENABLE == this);
        }
    }


    /* Constants - Data */
    private static final int SLIDER_MIN = (int)FLASHPLAYER_START;
    private static final int SLIDER_VAL = (int)FLASHPLAYER_START;
    private static final int SLIDER_MAJOR = 2;
    private static final int SLIDER_MINOR = 4;


    /* Variables */
    /* Variables - Data */
    private FlashPanel screen;
    private FlasherLabel valueDisplay;
    private FlasherStatusBar statusBar;
    private Status status;
    private int totalFrames;
    private boolean displayLabel;


    /**
     * Constructs a flash controller slider.
     *
     * @param flasher the flash screen to be controlled
     * @param statusBar the status bar to be updated
     * @param valueDisplay the label to display slider value
     * @param status the functional status of the slider of type
     *               <code>Status</code>
     * @see FlashPanel
     */
    public FlasherSlider(FlashPanel flasher, FlasherStatusBar statusBar,
                         FlasherLabel valueDisplay, Status status)
    {
        super();

        screen = flasher;
        this.statusBar = statusBar;
        this.valueDisplay = valueDisplay;
        this.status = status;

        construct();
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
     * Constructs <code>FlasherSlider</code>.
     */
    private void construct()
    {
        updateFrames();

        setUI(new FlasherSliderUI(this));
        setOrientation(SwingConstants.HORIZONTAL);
        setMinimum(SLIDER_MIN);
        setMaximum(totalFrames);
        setValue(SLIDER_VAL);

        setEnabled(this.status.isEnabled());
        displayLabel = false;
        updateState();
    }


    /**
     * {@inheretDoc}
     */
    @Override
    public void setEnabled(boolean enabled)
    {
        if(enabled)
            setEnabled(Status.SLIDER_BAR_ENABLE);
        else
            setEnabled(Status.SLIDER_BAR_DISABLE);
    }


    /**
     * Updates the status of <code>FlasherSlider</code>.
     *
     * @param status the status of type <code>Status</code>
     */
    public void setEnabled(Status status)
    {
        super.setEnabled(status.isEnabled());
    }


    /**
     * Updates the total frame count.
     */
    private void updateFrames()
    {
        totalFrames = Math.abs((int)(screen.getTotalFrames()));
    }


    /**
     * Updates the slider to display its interface details.
     */
    private void updateState()
    {
        Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();

        if(isEnabled())
        {
            setValue(SLIDER_VAL);
            setMaximum(totalFrames);


            labelTable.put(new Integer(SLIDER_MIN),
                           new JLabel("" + SLIDER_MIN));
            labelTable.put(new Integer((int)Math.round(totalFrames/
                                                       (double)SLIDER_MAJOR)),
                           new JLabel("" + Math.round(totalFrames/(double)
                                                      SLIDER_MAJOR)));
            labelTable.put(new Integer(totalFrames),
                           new JLabel("" + totalFrames));
            setLabelTable(labelTable);


            setPaintTicks(isEnabled());
            setPaintLabels(isEnabled());
            setMinorTickSpacing(totalFrames / SLIDER_MINOR);


            if(displayLabel)
            {
                setMajorTickSpacing(((int)(screen.getTotalFrames())) /
                                           SLIDER_MAJOR);
                setMinorTickSpacing(((int)(screen.getTotalFrames())) /
                                           SLIDER_MINOR);
            }
        }

        setEnabled(isEnabled());
    }


    /**
     * Updates the slider to display its interface details.
     */
    public void updateSliderState()
    {
        updateFrames();
        updateState();
    }


    /**
     * Returns the <code>FlashPanel</code> that is being monitored.
     *
     * @return the <code>FlashPanel</code> being monitored
     */
    public FlashPanel getScreen()
    {
        return(screen);
    }


    /**
     * Returns the <code>FlasherStatusBar</code> to update the player status.
     *
     * @return the status bar of the player
     */
    public FlasherStatusBar getStatusBar()
    {
        return(statusBar);
    }


    /**
     * Returns the <code>FlasherLabel</code> to update the value of the slider.
     *
     * @return the display label
     */
    public FlasherLabel getDisplayLabel()
    {
        return(valueDisplay);
    }
}
