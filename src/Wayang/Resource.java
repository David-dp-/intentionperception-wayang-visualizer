                                    /*OHM*/


/* Package */
package Wayang;


/* Import */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;




/**
 * <code>Resource</code> handles access to external resources.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class Resource
{
    /* Constants */
    /* Constants - Text */
    private final static String ERR_SOURCE_LOCATION =
                                "Unable To Find Source Location:";
    private final static String ERR_IMAGE_NOT_FOUND = "Unable To Find Image:";
    private final static String ERR_IMAGE_ACCESS = "Unable To Access Image:";
    private final static String ERR_FILE_NOT_FOUND = "Unable To Find File:";
    private final static String ERR_FILE_ACCESS = "Unable To Access File:";




    /**
     * Prevent the construction of this class.
     */
    private Resource()
    {}


    /**
     * Get running source location.
     *
     * @param classReference
     * @return
     */
    public static String getSourceLocation(Class<?> classReference)
    {
        try
        {
            String path = URLDecoder.decode(classReference
                                            .getProtectionDomain()
                                            .getCodeSource()
                                            .getLocation().getPath(),
                                            "UTF-8");

            return(path.replaceFirst("/", ""));
        }
        catch(UnsupportedEncodingException uee)
        {
            ExceptionHandler.printException(uee, ERR_SOURCE_LOCATION);
        }

        return(null);
    }


    /**
     * Creates an <code>ImageIcon</code> with the specified image and
     * alternate text.
     *
     * @param iconPath the pathname of the icon
     * @return ImageIcon the created <code>ImageIcon</code>
     */
    public static ImageIcon getImageIcon(String iconPath)
    {
        URL iconURL = Class.class.getResource(iconPath);

        if (iconURL != null)
        {
           return(new ImageIcon(iconURL));
        }
        else
        {
            ExceptionHandler.printException(new NullPointerException(
                                                "Icon: " + iconPath),
                                            ERR_IMAGE_NOT_FOUND);
        }

        return(null);
    }


    /**
     * Creates an <code>ImageIcon</code> with the specified image and
     * alternate text.
     *
     * @param iconPath the pathname of the icon
     * @param altTxt alternate message
     * @return ImageIcon the created <code>ImageIcon</code>
     */
    public static ImageIcon getImageIcon(String iconPath, String altTxt)
    {
        ImageIcon icon = getImageIcon(iconPath);

        if(icon != null)
        {
            icon.setDescription(altTxt);
            return(icon);
        }

        return(null);
    }


    /**
     * Creating a <code>BufferedImage</code> from the specified path.
     *
     * @param imagePath the pathname of the image
     * @return BufferedImage the created <code>BufferedImage</code>
     */
    public static BufferedImage getBufferedImageAsResource(String imagePath)
    {
        URL imageURL = Class.class.getResource(imagePath);

        if (imageURL != null)
        {
            try
            {
                return(ImageIO.read(new File(imageURL.toURI())));
            }
            catch(URISyntaxException urise)
            {
                ExceptionHandler.printException(urise, ERR_IMAGE_ACCESS);
            }
            catch(IOException ioe)
            {
                ExceptionHandler.printException(ioe, ERR_IMAGE_ACCESS);
            }
        }
        else
        {
            ExceptionHandler.printException(new NullPointerException(
                                                "Image: " + imagePath),
                                            ERR_IMAGE_NOT_FOUND);
        }

        return(null);
    }


    /**
     * Creating a <code>BufferedImage</code> from the specified path.
     *
     * @param imagePath the pathname of the image
     * @return BufferedImage the created <code>BufferedImage</code>
     */
    public static BufferedImage getBufferedImage(String imagePath)
    {
        File file = new File(imagePath);

        if(file != null)
        {
            try
            {
                return(ImageIO.read(new File(imagePath)));
            }
            catch(IOException ioe)
                    {
                        ExceptionHandler.printException(ioe, ERR_IMAGE_ACCESS);
                    }
        }
        else
        {
            ExceptionHandler.printException(new NullPointerException(
                                                "Image: " + imagePath),
                                            ERR_IMAGE_NOT_FOUND);
        }

        return(null);
    }


    /**
     * Creating a <code>File</code> from the specified path.
     *
     * @param filepath the pathname of the file
     * @return File the created specified file as a <code>File</code>
     */
    public static File getFileAsResource(String filepath)
    {
        URL imageURL = Class.class.getResource(filepath);

        if (imageURL != null)
        {
            try
            {
                return(new File(imageURL.toURI()));
            }
            catch(URISyntaxException urise)
            {
                ExceptionHandler.printException(urise, ERR_FILE_ACCESS);
            }
        }
        else
        {
            ExceptionHandler.printException(new NullPointerException(
                                                "File: " + filepath),
                                            ERR_FILE_NOT_FOUND);
        }

        return(null);
    }


    /**
     * Creating a <code>File</code> from the specified path.
     *
     * @param filepath the pathname of the file
     * @return File the created specified file as a <code>File</code>
     */
    public static File getFile(String filepath)
    {
        File file = new File(filepath);

        if(file != null)
        {
            return(file);
        }
        else
        {
            ExceptionHandler.printException(new NullPointerException(
                                                "File: " + filepath),
                                            ERR_FILE_NOT_FOUND);
        }

        return(null);
    }
}
