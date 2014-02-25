                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;

/* Import */
/* Import - Standard */
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/* Import - Custom */
import Wayang.ExceptionHandler;
import Wayang.Resource;




/**
 * A template for the flash player toggle buttons. A basic setup will consist
 * an icon, alternate text, tool tip and action implementation.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class FlasherToggle extends JToggleButton
{
    /* Constants */
    /* Constants - Variables */
    private static final int GAP = 10;

    /* Variables */
    private ImageIcon icon;


    /**
     * Constructs a flash controller button.
     *
     * @param iconPath the pathname of the image
     * @param toolTip the tool tip message
     * @param actionCommand the action command of the button
     * @param actor the <code>ActionListener</code> for the button
     */
    public FlasherToggle(String iconPath, String toolTip,
                         String actionCommand, ActionListener actor)
    {
        super();
        setIcon(iconPath, toolTip);
        setToolTipText(toolTip);
        setActionCommand(actionCommand);
        addActionListener(actor);
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
    private void setIcon(String iconPath, String altTxt)
    {
        icon = Resource.getImageIcon(iconPath, altTxt);
        setIcon(icon);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight()
    {
        return(icon.getIconHeight() + GAP);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth()
    {
        return(icon.getIconWidth() + GAP);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize()
    {
        return(new Dimension(icon.getIconWidth() + GAP,
                             icon.getIconHeight() + GAP));
    }
}
