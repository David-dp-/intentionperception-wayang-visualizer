                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.Figures;


/* Import */
import Wayang.GUI.Glazier.Draw.GraphicalContainer;




/**
 * <code>FigureShape</code> an <code>abstract</code> class for the purpose of
 * providing the rudimentary structure for drawable shapes.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public abstract class FigureShape implements GraphicalContainer
{
    /**
     * Construct <code>FigureShape</code> with the specified parameters.
     *
     * @param x
     * @param y
     */
    public abstract void constructShapeAt(double x, double y);


    /**
     * Construct a copy of this.
     *
     * @return
     */
    public abstract FigureShape copyThis();


    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder getToolTip()
    {
        return(new StringBuilder());
    }
}
