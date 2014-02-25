                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;


/* Import */
/* Import - Standard */
import Wayang.Resource;
import Wayang.ExceptionHandler;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* Import - Custom */
import Wayang.*;




/**
 * A template for the flash player status labels. A basic label defined
 * specific to the needs of status update in a status bar.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class FlasherLabel extends JLabel
{
    /* Variables */
    private ImageIcon icon;


    /**
     * Constructs status labels.
     */
    public FlasherLabel()
    {
        super();
    }


    /**
     * Constructs status labels.
     *
     * @param text the text to display
     */
    public FlasherLabel(String text)
    {
        super(text);
        setForeground(Color.WHITE);
        setFont(getFont().deriveFont(12));
    }


    /**
     * Constructs status labels with an icon.
     *
     * @param iconPath the pathname of the image
     * @param text the text to display
     */
    public FlasherLabel(String iconPath, String text)
    {
        this(text);
        setIcon(iconPath);
    }


    /**
     * Constructs status labels.
     *
     * @param text the text to display
     * @param colorFG text color
     */
    public FlasherLabel(String text, Color colorFG)
    {
        super(text);
        setForeground(colorFG);
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
     * Sets the button icon.
     *
     * @param iconPath the pathname of the image
     * @param altText alternate message
     */
    private void setIcon(String iconPath)
    {
        icon = Resource.getImageIcon(iconPath);
        setIcon(icon);
    }
}
