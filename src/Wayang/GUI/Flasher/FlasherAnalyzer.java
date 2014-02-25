                                    /*OHM*/


/* Package */
package Wayang.GUI.Flasher;


/* Import */
import com.jswiff.SWFDocument;
import com.jswiff.SWFReader;
import com.jswiff.io.InputBitStream;
import com.jswiff.listeners.SWFDocumentReader;
import com.jswiff.swfrecords.SWFHeader;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;




/**
 * Analyses flash animation files to provide an interface to those
 * information.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class FlasherAnalyzer
{
    /* Constants */
    private final static long TWIPS_PER_PIXEL = 20;

    /* Variables */
    private static Dimension frameSize;

    private File file;

    private SWFReader reader;
    private SWFDocumentReader docReader;
    private SWFDocument document;
    private SWFHeader header;




    /**
     * Constructs a <code>FlasherAnalyzer</code> that with the specified
     * <code>File</code>.
     *
     * @param file
     * @throws IOException
     */
    public FlasherAnalyzer(File file) throws IOException
    {
        this.file = file;
        analyze();
    }


    /**
     * Analyse flash animation.
     *
     * @throws IOException
     */
    private void analyze() throws IOException
    {
        docReader = new SWFDocumentReader();

        reader = new SWFReader(new FileInputStream(file));
        reader.addListener(docReader);
        reader.read();

        document = docReader.getDocument();

        header = new SWFHeader(new InputBitStream(new FileInputStream(file)));

        FlasherAnalyzer.frameSize = getFrameSize();
    }


    /**
     * Returns the frame size as a <code>Dimension</code>.
     *
     * @return
     */
    public Dimension getFrameSize()
    {
        return(new Dimension((int)(header.getFrameSize().getXMax() /
                                   TWIPS_PER_PIXEL),
                             (int)(header.getFrameSize().getYMax() /
                                   TWIPS_PER_PIXEL)));
    }


    /**
     * Returns the background colour as a <code>Color</code>.
     *
     * @return
     */
    public Color getBackgroundColor()
    {
        return(new Color(document.getBackgroundColor().getRed(),
                         document.getBackgroundColor().getGreen(),
                         document.getBackgroundColor().getBlue()));
    }


    /**
     * Returns the frame size as a <code>Dimension</code>.
     *
     * @return
     */
    public static Dimension getStaticFrameSize()
    {
        return(frameSize);
    }
}
