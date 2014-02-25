                                    /*OHM*/


/* Package */
package Wayang;


/* Import Packages */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;




/**
 * Manages file access.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class FileAccess
{
    /* Constants */
    private final static String ERR_FILE_NOT_FOUND = "File Not Found!";
    private final static String ERR_FILE_ACCESS = "File Access Error!";
    private final static String ERR_FILE_READ = "File Read Error!";


    /* Variables */
    private BufferedReader bReader;
    private LineNumberReader lnReader;
    private String filename;




    /**
     * Constructs an instance, input from standard input.
     *
     * @param filename The filename of the file to perform file operations on.
     */
    public FileAccess(String filename)
    {
        this.filename = filename;
        setReader();
    }


    /**
     * Configure to read text files.
     */
    private void setReader()
    {
        try
        {
            bReader = new BufferedReader(new FileReader(
                                             Resource.getFile(filename)));
            lnReader = new LineNumberReader(new FileReader(
                                                Resource.getFile(filename)));
        }
        catch(FileNotFoundException fnfe)
        {
            errorException(fnfe, ERR_FILE_NOT_FOUND);
        }
    }


    /**
     * Closes file resources.
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable
    {
        try
        {
            close();
        }
        finally
        {
            super.finalize();
        }
    }


    /**
     * Closes <code>Closeable</code> resources.
     */
    public void close()
    {
        try
        {
            if(bReader != null)
                bReader.close();
            if(lnReader != null)
                lnReader.close();
        }
        catch(IOException ioe)
        {
            errorException(ioe, ERR_FILE_ACCESS);
        }
    }


    /**
     * Ensures that stream is configured and functional.
     *
     * @return <code>true</code> if <code>FileAccess</code> is configured.
     */
    public boolean isReady()
    {
        return((bReader != null));
    }


    /**
     * Returns the file's line count
     *
     * @return
     */
    public long getLineCount()
    {
        try
        {
            lnReader.skip(Long.MAX_VALUE);
        }
        catch(IOException ioe)
        {
            errorException(ioe, ERR_FILE_READ);
            return(-1);
        }

        return(lnReader.getLineNumber());
    }


    /**
     * Reads a line from the file.
     *
     * @return The read line as a <code>String</code>.
     */
    public String readLine()
    {
        try
        {
            return(bReader.readLine());
        }
        catch(FileNotFoundException fnfe)
        {
            errorException(fnfe, ERR_FILE_NOT_FOUND);
            return(null);
        }
        catch(IOException ioe)
        {
            errorException(ioe, ERR_FILE_READ);
            return(null);
        }
    }


    /**
     * Handles the error exceptions.
     *
     * @param e The exception thrown.
     * @param message The message to be printed.
     */
    private void errorException(Exception e, String message)
    {
        System.err.format("%n%n%s%n", message);
        System.err.format("Filename - %s%n", filename);
        System.err.println(e.getMessage());
    }
}












