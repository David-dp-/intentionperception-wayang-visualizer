                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;


/* Import */
import Wayang.Constants;
import Wayang.ExceptionHandler;
import Wayang.GUI.ApplicationFrame;
import Wayang.GUI.Glazier.OverlayDialog;
import com.jpackages.jflashplayer.FlashPanel;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;




/**
 * <code>FlasherControl</code> creates the GUI interface for the flash player
 * controllers.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 * @see FlashPanel
 */
@SuppressWarnings("serial")
public class FlasherControl extends JPanel implements Constants
{
    /* Constants */
    /* Constants - Text */
    private static final String SWF_TIP = "Load A New SWF File";
    private static final String SWF_ACTION = "SWF";
    private static final String PLAY_TIP = "Play";
    private static final String PAUSE_TIP = "Pause";
    private static final String STOP_TIP = "Stop";
    private static final String SKIP_BACK_TIP = "Skip Backward";
    private static final String SKIP_FORWARD_TIP = "Skip Forward";
    private static final String SEEK_BACK_TIP = "Seek Backward";
    private static final String SEEK_FORWARD_TIP = "Seek Forward";
    private static final String LOOP_TIP = "Loop SWF Animation";
    private static final String LOOP_ACTION = "Loop";
    private static final String PREDICT_TIP = "Show/Hide Overlay";
    private static final String PREDICT_ACTION = "Predict";
    private static final String TREE_TIP = "Parse Tree";
    private static final String TREE_ACTION = "Tree";

    private static final String FUNCTION_STOP = "Stopped";
    private static final String FUNCTION_PLAY = "Playing";
    private static final String FUNCTION_PAUSE = "Paused";
    private static final String FUNCTION_LOAD = "Loading....";


    /* Constants - Error */
    private static final String ERR_MESSAGE_TITLE = "SWF File Load Error";
    private static final String ERR_FILE_NOT_FOUND =
                                "SWF File Not Found or Selected!";
    private static final String ERR_IO = "SWF File IO Error!";


    /* Constants - Data */
    private static final int PRE_GAP = 5;
    private static final int SLIDER_GAP = 1;
    private static final int SEPARATOR_WIDTH = 1;


    /* Variables */
    /* Variables - Core Components */
    private GroupLayout layout;
    private FlasherScreen screen;
    private OverlayDialog glazier;

    /* Variable - Controller Panel */
    private ControllerActions actor;

    private FlasherButton swf;
    private FlasherButton play;
    private FlasherButton pause;
    private FlasherButton stop;
    private FlasherButton skipBack;
    private FlasherButton skipForward;
    private FlasherButton seekBack;
    private FlasherButton seekForward;

    private FlasherToggle loop;
    private FlasherToggle predict;
    private FlasherToggle tree;

    private JPanel sliderValuePanel;
    private FlasherLabel sliderValue;
    private FlasherSlider slider;
    private FlasherStatusBar statusBar;

    private JSeparator separate1, separate2;

    private FlasherAnalyzer analyzer;




    /**
     * Constructs a flash controllers.
     *
     * @param flasher the projector to be controlled
     * @param over the overlay to be controlled
     * @see FlashPanel
     */
    public FlasherControl(FlasherScreen flasher, OverlayDialog over)
    {
        screen = flasher;
        glazier = over;

        actor = new ControllerActions();

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
     * Construct the GUI interface for the flash controllers.
     */
    private void constructGUI()
    {
        layout = new GroupLayout(this);
        setLayout(layout);

        constructButtons();
        constructToggles();
        constructSeparator();
        constructWatchers();


        layout.setHorizontalGroup
        (
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                .addGroup(
                  layout.createSequentialGroup()

                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                   PRE_GAP, PRE_GAP)
                  .addComponent(swf,GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

                  .addGroup
                  (
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(sliderValuePanel)
                    .addComponent(slider)
                    .addGroup
                    (
                      layout.createSequentialGroup()
                      .addComponent(play,GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(pause,GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(stop,GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(separate1,GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(skipBack,GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(seekBack,GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(seekForward,GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(skipForward,GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(separate2,GroupLayout.PREFERRED_SIZE,
                                              GroupLayout.PREFERRED_SIZE,
                                              GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(loop,GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
                    )
                  )
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup
                  (
                      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addComponent(predict,GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE)
                      .addComponent(tree,GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
                  )
                  .addGap(PRE_GAP, PRE_GAP, PRE_GAP)
                )

                .addComponent(statusBar)
        );



        layout.setVerticalGroup
        (
            layout.createSequentialGroup()
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                   PRE_GAP, PRE_GAP)
                  .addGroup
                   (
                     layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                     .addComponent(swf,GroupLayout.PREFERRED_SIZE,
                                       GroupLayout.PREFERRED_SIZE,
                                       GroupLayout.PREFERRED_SIZE)
                     .addGroup
                     (
                       layout.createSequentialGroup()
                       .addComponent(sliderValuePanel, sliderValue.getPreferredSize().height,
                                                       sliderValue.getPreferredSize().height,
                                                       sliderValue.getPreferredSize().height)
                       .addComponent(slider)
                       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        swf.getHeight() -
                                        (int) slider.getMaximumSize().getHeight()
                                        - play.getHeight() - SLIDER_GAP,
                                        swf.getHeight() -
                                        (int) slider.getMaximumSize().getHeight()
                                        - play.getHeight() - SLIDER_GAP)
                       .addGroup
                       (
                         layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addComponent(play,GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE)
                         .addComponent(pause,GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE)
                         .addComponent(stop,GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE)
                         .addComponent(separate1,GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                         .addComponent(skipBack,GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                         .addComponent(seekBack,GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                         .addComponent(seekForward,GroupLayout.PREFERRED_SIZE,
                                                   GroupLayout.PREFERRED_SIZE,
                                                   GroupLayout.PREFERRED_SIZE)
                         .addComponent(skipForward,GroupLayout.PREFERRED_SIZE,
                                                   GroupLayout.PREFERRED_SIZE,
                                                   GroupLayout.PREFERRED_SIZE)
                         .addComponent(separate2,GroupLayout.PREFERRED_SIZE,
                                                 GroupLayout.PREFERRED_SIZE,
                                                 GroupLayout.PREFERRED_SIZE)
                         .addComponent(loop,GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE)
                       )
                     )
                     .addGroup
                     (
                       layout.createSequentialGroup()
                       .addComponent(predict,GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(tree,GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.PREFERRED_SIZE)
                    )
                   )

                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(statusBar,GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
        );
    }


    /**
     * Construct buttons for the flash player controller.
     */
    private void constructButtons()
    {
        swf = new FlasherButton(SWF_ICON_PATH, SWF_TIP, SWF_ACTION, actor);
        play = new FlasherButton(PLAY_ICON_PATH, PLAY_TIP, PLAY_TIP, actor);
        pause = new FlasherButton(PAUSE_ICON_PATH, PAUSE_TIP, PAUSE_TIP, actor);
        stop = new FlasherButton(STOP_ICON_PATH, STOP_TIP, STOP_TIP, actor);
        skipBack = new FlasherButton(SKIP_BACK_ICON_PATH, SKIP_BACK_TIP,
                                     SKIP_BACK_TIP, actor);
        skipForward = new FlasherButton(SKIP_FORWARD_ICON_PATH,
                                        SKIP_FORWARD_TIP,
                                        SKIP_FORWARD_TIP, actor);
        seekBack = new FlasherButton(SEEK_BACK_ICON_PATH, SEEK_BACK_TIP,
                                     SEEK_BACK_TIP, actor);
        seekForward = new FlasherButton(SEEK_FORWARD_ICON_PATH,
                                        SEEK_FORWARD_TIP,
                                        SEEK_FORWARD_TIP, actor);
    }


    /**
     * Construct toggle buttons for the flash player controller.
     */
    private void constructToggles()
    {
        loop = new FlasherToggle(LOOP_ICON_PATH, LOOP_TIP, LOOP_ACTION, actor);
        loop.setSelected(screen.getLoop());

        predict = new FlasherToggle(PREDICT_ICON_PATH, PREDICT_TIP,
                                    PREDICT_ACTION, actor);
        tree = new FlasherToggle(TREE_ICON_PATH, TREE_TIP, TREE_ACTION, actor);
    }


    /**
     * Construct a <code>JSeparator</code> for the flash player controller.
     */
    private void constructSeparator()
    {
        separate1 = getSeparator();
        separate2 = getSeparator();
    }


    /**
     * Returns a <code>JSeparator</code> specific for the the flash player
     * controller.
     */
    private JSeparator getSeparator()
    {
        JSeparator seperate;

        seperate = new JSeparator(JSeparator.VERTICAL);
        seperate.setPreferredSize(new Dimension(SEPARATOR_WIDTH,
                                      play.getPreferredSize().height));

        return(seperate);
    }


    /**
     * Construct data watchers for the flash player.
     */
    private void constructWatchers()
    {
        sliderValue = new FlasherLabel();
        sliderValue.setText(Long.toString(Long.MAX_VALUE));
        sliderValue.setBounds(POINT_OF_ORIGIN.x, POINT_OF_ORIGIN.y,
                              sliderValue.getPreferredSize().width,
                              sliderValue.getPreferredSize().height);

        sliderValuePanel = new JPanel();
        sliderValuePanel.setLayout(null);
        sliderValuePanel.add(sliderValue);

        statusBar = new FlasherStatusBar();
        slider = new FlasherSlider(screen, statusBar, sliderValue,
                                   FlasherSlider.Status.SLIDER_BAR_ENABLE);
    }


    /**
     * Resize the main application window.
     */
    private void resizeMainApplication(Dimension reSize)
    {
        Dimension screenSize;
        ApplicationFrame mainApplication;

        screenSize = screen.getSize();
        mainApplication = (ApplicationFrame)getTopLevelAncestor();

        mainApplication.setSize(mainApplication.getSize().width +
                                (reSize.width - screenSize.width),
                                mainApplication.getSize().height +
                                (reSize.height - screenSize.height));
    }




    /**
     * <code>ControllerActions</code> handles all action events for the
     * controller buttons.
     *
     * @see FlashPanel
     */
    private class ControllerActions implements ActionListener
    {
        /* Constants */
        /* Constants - Text */
        private final static String SWFChooser_TITLE = "Load A New SWF File";
        private final static String SWFChooser_ACTION = "Load SWF";
        private final static String SWFChooser_ACTION_TIP =
                                    "Load selected SWF file";


        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().compareTo(SWF_ACTION) == 0)
                actionSWF();
            else if(ae.getActionCommand().compareTo(PLAY_TIP) == 0)
                actionPlay();
            else if(ae.getActionCommand().compareTo(PAUSE_TIP) == 0)
                actionPause();
            else if(ae.getActionCommand().compareTo(STOP_TIP) == 0)
                actionStop();
            else if(ae.getActionCommand().compareTo(SKIP_BACK_TIP) == 0)
                actionSkipBack();
            else if(ae.getActionCommand().compareTo(SKIP_FORWARD_TIP) == 0)
                actionSkipForward();
            else if(ae.getActionCommand().compareTo(SEEK_BACK_TIP) == 0)
                actionSeekBack();
            else if(ae.getActionCommand().compareTo(SEEK_FORWARD_TIP) == 0)
                actionSeekForward();
            else if(ae.getActionCommand().compareTo(LOOP_ACTION) == 0)
                actionLoop();
            else if(ae.getActionCommand().compareTo(PREDICT_ACTION) == 0)
                actionPredict();
            else if(ae.getActionCommand().compareTo(TREE_ACTION) == 0)
                actionTree();
        }


        /**
         * Action handler for SWF button.
         */
        private void actionSWF()
        {
            File selectedFile;
            FlasherFileChooser SWFChooser;

            SWFChooser = new FlasherFileChooser(
                             SwingUtilities.getRoot(swf), SWFChooser_ACTION,
                             SWFChooser_TITLE, SWFChooser_ACTION_TIP);
            SWFChooser.getFileSelection();
            selectedFile = SWFChooser.getSWFFile();
            if( selectedFile != null)
            {
                try
                {
                    statusBar.updateFunction(FUNCTION_LOAD);
                    screen.setMovie(selectedFile);
                    slider.updateSliderState();
                    statusBar.setEnableUpdate(
                              FlasherStatusBar.Status.STATUS_BAR_ENABLE);
                    screen.setLoop(loop.isSelected());
                    statusBar.updateFunction(FUNCTION_PLAY);

                    analyzer = new FlasherAnalyzer(SWFChooser.
                                                   getSWFFile());
                    resizeMainApplication(analyzer.getFrameSize());
                    screen.setBackground(analyzer.getBackgroundColor());
                }
                catch(FileNotFoundException fnfe)
                {
                    statusBar.updateFunction(ERR_FILE_NOT_FOUND);
                    ExceptionHandler.printException(fnfe, ERR_FILE_NOT_FOUND);

                    JOptionPane.showMessageDialog(SwingUtilities.getRoot(swf),
                                                  ERR_FILE_NOT_FOUND,
                                                  ERR_MESSAGE_TITLE,
                                                  JOptionPane.ERROR_MESSAGE);
                }
                catch(IOException ioe)
                {
                    statusBar.updateFunction(ERR_IO);
                    ExceptionHandler.printException(ioe, ERR_IO);

                    JOptionPane.showMessageDialog(SwingUtilities.getRoot(swf),
                                                  ERR_IO,
                                                  ERR_MESSAGE_TITLE,
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        /**
         * Action handler for Play button.
         */
        private void actionPlay()
        {
            statusBar.updateFunction(FUNCTION_PLAY);
            screen.play();
        }


        /**
         * Action handler for Pause button.
         */
        private void actionPause()
        {
            if(screen.isPlaying())
                statusBar.updateFunction(FUNCTION_PAUSE);

            screen.stop();
        }


        /**
         * Action handler for Stop button.
         */
        private void actionStop()
        {
            statusBar.updateFunction(FUNCTION_STOP);
            screen.stop();
            screen.rewind();
        }


        /**
         * Action handler for Skip Backward button.
         */
        private void actionSkipBack()
        {
            boolean continuePlay;

            continuePlay = screen.isPlaying();
            screen.rewind();

            if(continuePlay)
                screen.play();
        }


        /**
         * Action handler for Skip Backward button.
         */
        private void actionSkipForward()
        {
            boolean continuePlay;

            continuePlay = screen.isPlaying();
            screen.setCurrentFrame(screen.getTotalFrames());

            if(continuePlay)
                screen.play();
        }


        /**
         * Action handler for Seek Backward button.
         */
        private void actionSeekBack()
        {
            screen.back();
        }


        /**
         * Action handler for Seek Backward button.
         */
        private void actionSeekForward()
        {
            screen.forward();
        }


        /**
         * Action handler for Loop button.
         */
        private void actionLoop()
        {
            screen.setLoop(!(screen.getLoop()));
        }


        /**
         * Action handler for Predict button.
         */
        private void actionPredict()
        {
            if((predict.isSelected()) && (glazier == null))
            {
                glazier = new OverlayDialog(
                              (ApplicationFrame)getTopLevelAncestor(),
                              screen);


                if(!(GraphicsCapability.isPixelTransparencySupport()))
                {
                    glazier.dispose();
                    GraphicsCapability.generalWarning();
                }
                else
                    glazier.annotate();
            }

            if(glazier != null)
                glazier.setVisible(predict.isSelected());
        }


        /**
         * Action handler for Parse Tree button.
         */
        private void actionTree()
        {
            if(tree.isSelected())
                System.out.println("Parse Tree Open");
            else
                System.out.println("Parse Tree Close");
        }
    }


    /**
    * Convenient class to verify platform capabilites to support graphic
    * requirements. It also provides convenient
    * <p>
    * The following supports are verified:
    * <ul>
    * <li>Uniform Translucency
    * <li>Per-Pixel Translucency
    * <li>Shaped Windows
    * </ul>
    *
    * @author kumaresh
    * @version : "%I%, %G%"
    */
    private static class GraphicsCapability
    {
        /**
        * Prevent the construction of this class.
        */
        private GraphicsCapability()
        {}


        /**
         * Returns <code>true</code> if the OS Platform supports per pixel
         * transparency.
         *
         * @return <code>true</code> if per pixel transparency is supported.
         */
        public static boolean isPixelTransparencySupport()
        {
            return(GraphicsEnvironment.getLocalGraphicsEnvironment()
                                      .getDefaultScreenDevice()
                                      .isWindowTranslucencySupported(
                                       GraphicsDevice.WindowTranslucency
                                       .PERPIXEL_TRANSPARENT));
        }


        /**
         * Provides a convenient means to warn the user if the platform does not
         * support translucency or transparency capabilities.
         */
        public static void generalWarning()
        {
            JOptionPane.showMessageDialog
            (null,
             "OS does not support required translucency/transparency features!",
             "Graphic Features Unsupported", JOptionPane.ERROR_MESSAGE);
        }
    }
}
