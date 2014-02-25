                                    /*OHM*/


/* Package */
package Wayang;


/* Import */
import java.io.NotSerializableException;




/**
 * <code>ExceptionHandler</code> handles <code>thrown Exception</code>
 * scenarios.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class ExceptionHandler
{
    /* Constants */
    /* Constants - Text */
    private final static String ERR_NOT_SERIALIZABLE =
                                "Class Is Not Serializable:";


    /**
     * Prevent the construction of this class.
     */
    private ExceptionHandler()
    {}


    /**
     * Handles exception calls.
     *
     * @param e the <code>thrown Exception</code>
     * @param msg the error message
     */
    public static void printException(Exception e, String msg)
    {
        System.err.println(msg);
        System.err.println(e.getMessage());
    }


    /**
     * Handles exceptions to prevent classes from Serializing.
     *
     * @param className the name of the class being prevented
     */
    public static void NotSerializableException(String className)
    {
        printException(new NotSerializableException("Class Name: " + className),
                           ERR_NOT_SERIALIZABLE);
    }
}
