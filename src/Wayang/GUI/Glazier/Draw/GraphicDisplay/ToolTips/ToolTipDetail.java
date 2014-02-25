                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips;


/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplay;




/**
 * <code>ToolTipDetail</code> an <code>interface</code> for the purpose of
 * providing the rudimentary structure for containing ascription details.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public abstract class ToolTipDetail implements Constants
{
    /* Variables */
    private GraphicalDisplay graphicDisplay;


    /**
     * Sets the graphic display.
     *
     * @param graphicDisplay
     */
    public void setGraphicDisplay(GraphicalDisplay graphicDisplay)
    {
        this.graphicDisplay = graphicDisplay;
    }


    /**
     * Returns the graphic display.
     *
     * @return
     */
    public GraphicalDisplay getGraphicDisplay()
    {
        return(graphicDisplay);
    }


    /**
     * Returns annotation details.
     *
     * @return
     */
    public abstract String getAnnotationDetails();


    /**
     * Returns tool tip details.
     *
     * @return
     */
    public abstract StringBuilder getToolTip();
}




// NOTE: Possible design motivation to consider in modifying TooltipTexts in the
//       event of a future structural or design modification that gets
//       introduced.
//
//       This is only a design concept and might not indeed be a more suitable
//       implementation choice. It only seems worthwhile at this stage if
//       GraphicDisplayForHigherLevelAscription functionalities requires more
//       variations and it does not yet.
/*
* TooltipTexts abstract/interface design in essence groups data variables
* together that differs between the overall GraphicDisplayForHigherAscription
* class design. An advantage of this is a single
* GraphicDisplayForHigherLevelAscription that is common.
* GraphicDisplayForHigherLevelAscription.
*
* This however restricts its growth as a GraphicDisplay and removes the
* transparent mobility it had as an application interface. It also fails to
* address the functionality of the overall class design.
*
* - To display annotation labels it will not only rely on the members of
*   TooltipTexts, but also that of the GraphicDisplay subclass, for data like
*   Figure ID.
*   - To achieve this the main GraphicDisplayForHigherLevelAscription, is unable
*     to actually access the members of TooltipTexts as it has to have an
*     overall function for all higher level ascription.
*     - It can force itself by first accessing the instance of the TooltipTexts,
*       before switching into segments specific for that particular Higher level
*       ascription/TooltipTexts. Which defeats the advantages of OO.
*     - It passes a reference of itself to TooltipTexts to allow it to access
*       the data it requires without interfering with the overall class design.
*       Causing circular dependencies which are considered as code smells.
*       - Which has been done before but it seems to be much simpler to avoid
*         here though.
*       - This can be countered by including generic casting.
* - GraphicDisplay subclasses handles various other aspects such as handling
*   basic graphic rendering, envelope rendering and identifying mouse over.
*   - As of now there are not any expected differences between higher level
*     ascription.
*   - But if there was, it would break the single common
*     GraphicDisplayForHigherLevelAscription.
*     - This functionalities have to be pushed to TooltipTexts.
*     - Switching within.
*     - Having to create further subclasses to handle variations.
*
* - A possible solution would be to return and not have made changes to
*   the existing GraphicDisplayForXXX design concept.
*   - It would not have introduced new data types/variables/classes.
*   - There would have been a consistency among all GraphicDisplay. An
*     advantage if inter-changeable needs are required, besides keeping growth
*     of classes to the minimal.
*   - Commonality among different HigherLevelAscription can be grouped together
*     with an intermittent class.
*     - Transparent to all interfacing applications.
*       - Allows modification and refactoring without breaking interface.
*     - Allows multiple progressive grouping of HigherLevelAscriptions if
*       functional differences are required without breaking interface.
*     - If all functions are the same except for tooltips, than that is what
*       will differ in the subclasses as it does for TooltipTexts.
*     - Does not introduce cyclic dependencies.
*     - Reduce the number of overall classes without inhibiting modular design
*       concept.
*     - Functionality similar to GraphicDisplayTrajectory, which seems to have
*       lesser motivation compared to GraphicDisplayForHigherLevelAscription.
*/