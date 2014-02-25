                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;


/* Import */
import Wayang.Constants;
import Wayang.ExceptionHandler;
import Wayang.Resource;
import Wayang.Wayang;
import com.jpackages.jflashplayer.FlashPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




/**
 * <code>FlasherScreen</code> handles and is an interface to
 * <code>JPanel</code> that will screen the flash animation.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 * @see FlashPanel
 */
@SuppressWarnings("serial")
public class FlasherScreen extends FlashPanel implements Constants
{
    /* Constants */
    /** General Error/Unsuccessful Code */
    protected static final int ERROR_CODE = -1;

    /* Variables */
    private File movieFile;




    /**
     * Constructs a flash player.
     */
    public FlasherScreen()
    {
        //TODO: JAR Package Run
//        super(Resource.getFileAsResource(LOGO_SWF),
//              Resource.getBufferedImageAsResource(LOGO_IMAGE));
//
//        movieFile = Resource.getFileAsResource(LOGO_SWF);
        //TODO: End


        //TODO: JAR Run Unix
        super(Resource.getFile(
                (Wayang.RUNNING_PATH + LOGO_SWF)),
              Resource.getBufferedImage(
                (Wayang.RUNNING_PATH + LOGO_IMAGE)));

        movieFile = Resource.getFile(Wayang.RUNNING_PATH + LOGO_SWF);
        //TODO: End


        setLoop(false);
        setQualityHigh();
        setScale(FlashPanel.SCALE_SHOWALL);
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
     * {@inheritDoc}
     *
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        super.paintComponents(g);
    }


    /**
     * {@inheritDoc}
     *
     * @param f
     * @throws FileNotFoundException
     */
    @Override
    public void setMovie(File f) throws FileNotFoundException
    {
        super.setMovie(f);

        movieFile = f.getAbsoluteFile();
    }


    /**
     * Returns the movie file being played.
     *
     * @return the movie file of type <code>File</code>
     */
    public File getMovie()
    {
        return(movieFile);
    }


    /**
     * {@inheritDoc}
     *
     * @return the current frame
     */
    @Override
    public long getCurrentFrame()
    {
        long frame;

        frame = super.getCurrentFrame();
        if(frame == ERROR_CODE)
            return(frame);

        return(frame + FLASHPLAYER_OFFSET);
    }


    /**
     * {@inheritDoc}
     *
     * @param frame_index the frame index to set to
     * @return returns <code>true</code> if set successfully
     */
    @Override
    public boolean setCurrentFrame(long frame_index)
    {
        return(super.setCurrentFrame(frame_index - FLASHPLAYER_OFFSET));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize()
    {
        try
        {
            return((new FlasherAnalyzer(getMovie()).getFrameSize()));

        }
        catch(IOException ioe)
        {
            return(super.getPreferredSize());
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize()
    {
        return(getPreferredSize());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMaximumSize()
    {
        return(getPreferredSize());
    }
}
