                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;

/* Import */
/* Import - Standard */
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.plaf.metal.MetalSliderUI;

/* Import - Custom */
import Wayang.Constants;

import com.jpackages.jflashplayer.*;





/**
 * <code>FlasherSliderUI</code> provides the means to modify the existing
 * <code>MetalSliderUI</code> to permit customizable functionalities and
 * features.
 * <p>
 * <strong>Value Updates</strong>
 * <code>JSlider</code> does not have an inherent mechanism to listen to an
 * event that the slider is attached to and to trigger an event. A trigger that
 * would provide the mechanism to keep the value of the slider updated.
 * <p>
 * To update the slider to the current frame of the played animation, a new
 * thread is created to manage the real time reflection of its value. Care must
 * be taken to ensure that the <code>ChangeListener</code> isn't triggered
 * recursively by the <code>setvalue</code> triggered by the thread managing
 * the update.
 * <p>
 * <strong>Click To Value</strong>
 * <code>JSlider</code> UI mechanism is to move by a unit when clicked on
 * its track. Its user interface is overridden to change its behavior to move
 * to the clicked position. The UI is overwritten in an extremely conservative
 * manner. A click on the track of a slider triggers
 * <code>scrollDueToClickInTrack</code> which in turn triggers
 * <code>scrollByUnit</code>. The inherent mechanism is allowed to play out
 * first before calling <code>scrollByClick</code>, a custom method, to achieve
 * the desired functionality.
 * <p>
 * <strong>Drag Slider</strong>
 * <code>JSlider</code> provides the interface to drag the slider but additional
 * care needs to be taken to ensure that this functionality persists when the
 * slider is clicked to a point.
 * <p>
 * <strong>Status Update</strong>
 * As part of the interface functionality, of the slider being consistently
 * updated to the frame information, it also updates the status bar of both the
 * frame information and also the frame currently being pointed to. This
 * includes frame information when the user manually moves the thumb of the
 * slider to represent the current frome of the SWF.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 * @see FlashPanel
 */
public class FlasherSliderUI extends MetalSliderUI implements Constants
{
    /* Constants */
    /* Consatnts - Text */
    /** Thread name for synchronizing slider updates. */
    protected static final String THREAD_SLIDER = "SLIDER";



    /* Variables */
    private final FlasherSlider flashSlider;
    private FlashPanel screen;
    private FlasherLabel valueDisplay;
    private FlasherStatusBar statusBar;
    private JPopupMenu trackPop;
    private JMenuItem trackValue;
    private DangerMouse mouseHandler;
    private Thread loneRunner;
    private boolean adjusting, adjustedState;


    /**
     * Constructs a basic <code>FlasherSliderUI</code>.
     *
     * @param slider the <code>FlasherSlider</code> that is implementing the
     *               user interface.
     */
    public FlasherSliderUI(FlasherSlider slider)
    {
        super();

        flashSlider = slider;
        screen = flashSlider.getScreen();
        statusBar = flashSlider.getStatusBar();
        valueDisplay = flashSlider.getDisplayLabel();

        contructTrackPop();

        mouseHandler = new DangerMouse();
        flashSlider.addMouseListener(mouseHandler);
        flashSlider.addMouseMotionListener(mouseHandler);

        adjusting = false;
        flashSlider.addChangeListener(new SliderMonitor());
        screenProjectionist();
    }


    /**
     * Construct the pop-up menu that will be used as a display mode to
     * identify the value on the track pointed at.
     */
    private void contructTrackPop()
    {
        trackPop = new JPopupMenu();
        trackValue = new JMenuItem();

        trackValue.enableInputMethods(false);
        trackValue.setArmed(false);
        trackValue.setFocusable(false);
        trackValue.setSelected(false);
        trackValue.setBorderPainted(false);
        trackValue.setContentAreaFilled(false);
        trackValue.setFocusPainted(false);
        trackValue.setHideActionText(true);
        trackValue.setMargin(BORDERLESS_INSETS);
        trackValue.setVerifyInputWhenFocusTarget(false);
        trackValue.setEnabled(false);
        trackValue.setFont(trackValue.getFont()
                                     .deriveFont(Font.CENTER_BASELINE));

        trackPop.add(trackValue);
        trackPop.setDoubleBuffered(true);
        trackValue.setDoubleBuffered(true);
    }


    /**
     * {@inheritDoc}
     * <p>
     * Modified to call <code>scrollByClick</code> at the end of its
     * existing functionality, to provide a <em>scroll to click</em> to
     * position functionality.
     *
     * @param dir Clicked direction relative to the current value
     */
    @Override
    protected void scrollDueToClickInTrack(int dir)
    {
        super.scrollByUnit(dir);

        scrollByClick();
    }


    /**
     * The functionality to move the slider value to the clicked position
     * in the track.
     */
    public void scrollByClick()
    {
        synchronized(flashSlider)
        {
            if(flashSlider.getMousePosition() != null)
            {
                if(flashSlider.getOrientation() == JSlider.HORIZONTAL)
                {
                    flashSlider.setValue(valueForXPosition(
                                         flashSlider.getMousePosition().x));
                }
                else
                    if(flashSlider.getOrientation() == JSlider.VERTICAL)
                    {
                        flashSlider.setValue(valueForXPosition(
                                             flashSlider.getMousePosition().y));
                    }
            }
        }
    }


    /**
     * Displays the track value where the mouse is currently pointing at.
     *
     * @param me <code>MouseEvent</code> that was triggered
     */
    private void updateTrackValue(MouseEvent me)
    {
        if(flashSlider.isEnabled())
        {
            trackValue.setText("   " + valueForXPosition(me.getX()));
            trackPop.show(flashSlider, me.getX(),
                          flashSlider.getBounds().y - POPUP_X_OFFSET);
        }
    }


    /**
     * Hides the track value display.
     */
    private void hideTrackValue()
    {
        if(flashSlider.isEnabled())
        {
            trackPop.setVisible(false);
        }
    }


    /**
     * Hides the track value display.
     */
    private void updateSliderValue(MouseEvent me)
    {
        if(flashSlider.isEnabled())
        {
            flashSlider.setValue(valueForXPosition(me.getX()));
        }
    }


    /**
     * Start a new thread to monitor the current frame to updated the slider.
     */
    private void screenProjectionist()
    {
        loneRunner = new Thread(new projectionist(), THREAD_SLIDER);
        loneRunner.start();
    }


    /**
     * Provides custom mouse functionalities for the slider user interface.
     */
    private class DangerMouse extends MouseAdapter
    {
        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(MouseEvent me)
        {
            updateTrackValue(me);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseExited(MouseEvent me)
        {
            hideTrackValue();
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseDragged(MouseEvent me)
        {
            updateTrackValue(me);
            updateSliderValue(me);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseMoved(MouseEvent me)
        {
            updateTrackValue(me);
        }

    }


    /**
     * Listens to changes in the state of the <code>JSlider</code>.
     */
    private class SliderMonitor implements ChangeListener
    {
        /**
         * {@inheritDoc}
         */
        @Override
        public void stateChanged(ChangeEvent e)
        {
            if(Thread.currentThread().getName().equals(THREAD_SLIDER))
                return;

            if(flashSlider.getValueIsAdjusting())
            {
                if(!adjusting)
                {
                    adjustedState = flashSlider.getScreen().isPlaying();
                }

                flashSlider.getScreen().stop();
                flashSlider.getScreen().setCurrentFrame(flashSlider.getValue());
                adjusting = true;
            }
            else
            {
                if(!flashSlider.getScreen().isPlaying() && adjusting)
                {
                    flashSlider.getScreen().setCurrentFrame(flashSlider.
                                                            getValue());

                    if(adjustedState)
                        flashSlider.getScreen().play();

                    adjusting = false;
                }
            }
        }
    }


    /**
     * Handles the automated updating of <code>JSlider</code> and
     * <code>JLabel</code> displaying the current frame details.
     */
    private class projectionist implements Runnable
    {
        /**
         * {@inheritDoc}
         */
        @Override
        public void run()
        {
            while(true)
            {
                if(thumbRect != null)
                {
                    valueDisplay.setText("" + screen.getCurrentFrame());
                    valueDisplay.setLocation(thumbRect.x,
                                             valueDisplay.getLocation().y);
                }


                if(!adjusting)
                    flashSlider.setValue((int) screen.getCurrentFrame());

                statusBar.updateFrame((int)
                                      screen.getCurrentFrame() + "/" +
                                      (int) screen.getTotalFrames());
            }
        }
    }
}