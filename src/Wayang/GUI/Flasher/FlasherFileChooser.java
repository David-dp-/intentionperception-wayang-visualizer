                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;


/* Import */
import Wayang.ExceptionHandler;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;




/**
 * <code>JFileChosoer</code> customised specific for choosing SWF files only
 * via <code>FileFilter</code>.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class FlasherFileChooser extends JFileChooser
{
    /* Constants */
    /* Constants - Text */
    private final static String SWF_EXT = ".swf";
    private final static String SWF_DES = "Shockwave Files (SWF)";


    /* Variables */
    private Component parentComponent;
    private String actionLabel;
    private File selectedFile;


    /**
     * Constructs a default <code>JFileChooser</code>.
     *
     * @param parent the <code>Component</code> the dialog is attached to
     * @param action the dialog action
     * @param title the title of the dialog
     * @param actionTip the tool tip text for the action
     */
    public FlasherFileChooser(Component parent, String action,
                              String title, String actionTip)
    {
        super();

        parentComponent = parent;
        actionLabel = action;
        selectedFile = null;

        setDialogTitle(title);
        setApproveButtonToolTipText(actionTip);
        setAcceptAllFileFilterUsed(false);
        setFileFilter(new SWFFilter());
        setMultiSelectionEnabled(false) ;
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
     * Gets the selected file as a <code>File</code>.
     *
     * @return the absolute filename
     */
    public File getSWFFile()
    {
        return(selectedFile);
    }


    /**
     * Gets the selected file as a <code>FileInputStream</code>.
     *
     * @return
     * @throws FileNotFoundException
     */
    public FileInputStream getSWFFileInputStream() throws FileNotFoundException
    {
        return(new FileInputStream(selectedFile));
    }


    /**
     * Gets the user selected file.
     */
    public void getFileSelection()
    {
        if(selectedFile == null)
        {
            if(showDialog(parentComponent, actionLabel) ==
               JFileChooser.APPROVE_OPTION)
                selectedFile = getSelectedFile();
        }
    }

    /**
     * <code>SWFFilter</code> is a <code>FileFilter</code> that accepts only
     * Shockwave Files.
     */
    private class SWFFilter extends FileFilter
    {
        @Override
        public boolean accept(File pathname)
        {
            if(pathname.isDirectory())
                return(true);

            if(pathname.getName().toLowerCase().endsWith(SWF_EXT))
                return(true);

            return(false);
        }


        @Override
        public String getDescription()
        {
            return(SWF_DES);
        }
    }
}
