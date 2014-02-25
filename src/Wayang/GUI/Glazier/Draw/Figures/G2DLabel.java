                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.Figures;


/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.OverlayGlass;
import java.awt.AlphaComposite;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;




/**
 * <code>G2DLabel</code> renders a graphical text in a box.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class G2DLabel implements Constants
{
    /* Variables */
    private int stackOrder, shadeOrder;
    private String label;
    private Point.Double location;




    /**
     * Constructs a <code>G2DLabel</code>.
     *
     * @param label
     * @param x
     * @param y
     * @param stackOrder
     * @param shadeOrder
     */
    public G2DLabel(String label, double x, double y,
                    int stackOrder, int shadeOrder)
    {
        this.label = label;
        location = new Point.Double(x, y);

        this.stackOrder = stackOrder + 1;
        this.shadeOrder = shadeOrder;
    }


    /**
     * Draw the label onto the specified graphic resource.
     *
     * @param g2D
     */
    public void draw(Graphics2D g2D)
    {
        FontMetrics metrics;
        Rectangle2D box;
        Point.Double offset;


        metrics = g2D.getFontMetrics();
        box = metrics.getStringBounds(label, g2D);


        offset = new Point.Double(location.getX() +
                                  ((TEXT_BOX_PADDING) * stackOrder),
                                  location.getY() +
                                  ((TEXT_BOX_PADDING/2) * stackOrder) +
                                  ((box.getHeight() + TEXT_BOX_PADDING) * stackOrder)
                                 );
        box.setFrame(offset.getX() - (TEXT_BOX_PADDING/2),
                     offset.getY() + box.getY() - (TEXT_BOX_PADDING/2),
                     box.getWidth() + TEXT_BOX_PADDING,
                     box.getHeight() + TEXT_BOX_PADDING);


        g2D.setStroke(TEXT_BOX_STROKE);
        g2D.draw(box);


        g2D.setComposite(AlphaComposite.
                          getInstance(AlphaComposite.SRC_OVER, TEXT_BOX_ALPHA));

        for(int diver = 1; diver <= shadeOrder; diver++)
            g2D.fill(box);

        g2D.setComposite(OverlayGlass.getG2DAlphaComposite());


        g2D.setColor(g2D.getColor().darker().darker().darker().darker());

        //TEST: Stack Order, Shade Order, Draw ID
//        label = "["+stackOrder+","+shadeOrder+"]" + label;
//        st1 = System.nanoTime();
        g2D.drawString(label, (float)offset.getX(),
                              (float)offset.getY());
    }
}
