                                    /*OHM*/


/* Package */
package Wayang.GUI;


/* Import */
import Wayang.ExceptionHandler;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;




/**
 * <code>ApplicationFrame</code> of type <code>JFrame</code> invokes OS windows
 * for the application.
 * <p>
 * <strong>Window Mode Concepts</strong>
 * The windows are created with the concept of functional modes. This allows
 * the separation of specific behaviors between the windows. Mode options are
 * set via <code>enum</code> <code>Mode</code>.
 * <p>
 * <em>Mode Options:</em>
 * <ul>
 * <li> MODE_MAIN - Main Application Window
 * <li> MODE_CHILD - Child Application Window
 * </ul>
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class ApplicationFrame extends JFrame
{
    /* Constants */
    /* Constants - Enums */
    /**
     * <code>Enum</code> <code>Mode</code> contains the functional modes of
     * <code>ApplicationFrame</code>.
     */
    public static enum Mode
    {
        /* Variables */
        /* Variables - Enum */
        /** Main Application Window. */
        MODE_MAIN("Main Application Window")
        {
            /**
             * (@inheritDoc)
             */
            @Override
            void setDefaultCloseOperation(ApplicationFrame windowFrame)
            {
                windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        },
        /** Child Application Window. */
        MODE_CHILD("Child Application Window")
        {
            /**
             * (@inheritDoc)
             */
            @Override
            void setDefaultCloseOperation(ApplicationFrame windowFrame)
            {
                windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        };


        /** Default application window mode. */
        protected final static Mode DEFAULT_MODE = MODE_MAIN;


        /* Variables - Data */
        private final String description;


        /**
         * Sets the <code>ApplicationFrame</code> default close operation.
         */
        abstract void setDefaultCloseOperation(ApplicationFrame windowFrame);




        /**
         * Constructs an <code>Enum</code> with the specified description.
         *
         * @param description the <code>enum</code>'s description
         */
        Mode(String description)
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
    }




    /* Variables */
    /* Variables - Data */
    private Mode applicationMode;




    /**
     * Constructs a <code>ApplicationFrame</code> with the specified title.
     */
    public ApplicationFrame()
    {
        super();

        applicationMode = Mode.DEFAULT_MODE;
        setFrameCloser();
    }


    /**
     * Constructs a <code>ApplicationFrame</code> with the specified title.
     *
     * @param title the title of the <code>ApplicationFrame</code>
     */
    public ApplicationFrame(String title)
    {
        this();

        setTitle(title);
    }


    /**
     * Constructs a <code>ApplicationFrame</code> with the specified title and
     * functional mode.
     *
     * @param title the title of the <code>ApplicationFrame</code>
     * @param mode functional mode of type <code>Mode</code>
     */
    public ApplicationFrame(String title, Mode mode)
    {
        this(title);

        applicationMode = mode;
        setFrameCloser();
    }


    /**
     * Constructs a <code>ApplicationFrame</code> with the specified title,
     * functional mode, size and location.
     *
     * @param title the title of the <code>JFrame</code>
     * @param mode functional mode of type <code>Mode</code>
     * @param size the size of the <code>JFrame</code>
     * @param location the location of the <code>JFrame</code>
     */
    public ApplicationFrame(String title, Mode mode, Dimension size,
                       Point location)
    {
        this(title, mode);
        setSize(size);
        setLocation(location);
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
     * Sets the closing application procedure.
     *
     * @param main <code>true</code> if the window is a main application window
     */
    private void setFrameCloser()
    {
        applicationMode.setDefaultCloseOperation(this);
    }
}
