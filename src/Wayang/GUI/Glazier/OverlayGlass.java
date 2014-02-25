                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier;


/* Import */
import Wayang.Constants;
import Wayang.ExceptionHandler;
import Wayang.GUI.Glazier.Draw.DrawDataParser;
import Wayang.GUI.Glazier.Draw.DrawList;
import Wayang.Wayang;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;




/**
 * <code>OverlayGlass</code> is an extension of <code>JPanel</code>. Using the
 * inherent optimized graphic rendering mechanism of <code>Swing</code> of
 * painting over areas that are specified, produces an effect of rendering the
 * rest of the unspecified area transparent. Ideal for implementing an overlay
 * over desired components.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
@SuppressWarnings("serial")
public class OverlayGlass extends JPanel implements Constants
{
    /* Variables */
    private static Composite g2DAC;

    private JLabel progressLabel;
    private DrawList drawer;
    private DrawDataParser parser;



    /**
     * Constructs an instance of <code>OverlayGlass</code>.
     */
    public OverlayGlass()
    {
        super();

        progressLabel = new JLabel("Loading....");
        drawer = new DrawList();

        addMouseMotionListener(new GraphicDetailer());
        manageToolTip();

        setOpaque(false);

        setLayout(null);
        add(progressLabel);
        progressLabel.setBounds(40, 40, 100, 40);
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
     * Overlay annotation.
     */
    protected void annotate()
    {
        parser = new DrawDataParser(Wayang.RUNNING_PATH + DRAW_INSTRUCTIONS,
                                    drawer,
                                    progressLabel);
        parser.execute();
    }


    /**
     * Returns the drawing component of the overlay.
     *
     * @return the drawing component of type <code>DrawList</code>
     */
    public DrawList getDrawn()
    {
        return(drawer);
    }


    /**
     * Configure Tool Tip.
     */
    private void manageToolTip()
    {
        ToolTipManager.sharedInstance().setInitialDelay(TOOLTIP_INITAL_DELAY);
    }


    /**
     * Return a customisable tool tip.
     *
     * @return
     */
    @Override
    public JToolTip createToolTip()
    {
        JToolTip toolTip;

		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);

        toolTip = super.createToolTip();
        toolTip.setBackground(COLOR_TOOLTIP_BACKGROUND);

        return(toolTip);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D)g;

        super.paintComponent(g);
        g2DAC = g2D.getComposite();

        setLocation(POINT_OF_ORIGIN);

        g2D.setRenderingHints(new RenderingHints(
                                  RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON));

        if(drawer != null)
            drawer.drawGraphicDisplay(g2D);
    }


    /**
     * Return the <code>Composite<code>.
     *
     * @return
     */
    public static Composite getG2DAlphaComposite()
    {
        return(g2DAC);
    }




    /**
     * Provides the mechanism to display details of annotation via tool tip.
     */
    private class GraphicDetailer extends MouseAdapter
    {
        /*
         * {@inheritDoc}
         */
        @Override
        public void mouseMoved(MouseEvent e)
        {
            if(drawer != null)
                setToolTipText(drawer.getToolTip(e.getPoint()));
        }
    }
}
