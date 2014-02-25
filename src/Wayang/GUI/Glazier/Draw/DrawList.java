                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw;


/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplay;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;




/**
 * <code>DrawList</code> contains a list of <code>DrawInstructor</code> for the
 * intent of providing a collection of data to draw.
 * <p>
 *
 * @see DrawInstructor
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class DrawList implements Constants
{
    /* Variables */
    public static DrawInstructor baseInstruct;
    private final ArrayList<DrawInstructor> drawInstructs;
    private ArrayList<DrawInstructor> currentDrawInstructs,
                                      overlayInstructs, listInstructs;
    private ArrayList<ArrayList<DrawInstructor>> overlayCatalogue,
                                                 listCatalogue;

    /* Variables - Filters */
    private long currentFrame;




    /**
     * Construct a default <code>DrawList</code> mechanism.
     */
    public DrawList()
    {
        baseInstruct = null;

        drawInstructs =  new ArrayList<>();
        currentDrawInstructs = new ArrayList<>();

        currentFrame = FLASHPLAYER_START;
    }


    /**
     * Draw instructions.
     *
     * @param episStatus
     * @param drawID
     * @param ancestorIDs
     * @param figID
     * @param frameNo
     * @param lhs
     * @param ascription
     * @param startTime
     * @param endTime
     * @param cv
     * @param graphicData
     */
    public synchronized void draw(
           EpistemicStatus episStatus,
           int drawID, ArrayList<Integer> ancestorIDs, int figID,
           int frameNo, String lhs, String ascription,
           long startTime, long endTime,
           double cv, GraphicalDisplay graphicData)
    {
        drawInstructs.add(new DrawInstructor(episStatus,
                          drawID,
                          ancestorIDs, getAncestorInstructs(ancestorIDs),
                          figID,
                          frameNo, lhs, ascription,
                          startTime, endTime,
                          cv, graphicData));
    }


    /**
     * Returns the draw instruct identified by <code>DrawID</code>.
     *
     * @return
     */
    private synchronized ArrayList<DrawInstructor> getAncestorInstructs(
            ArrayList<Integer> ancestorIDs)
    {
        ArrayList<DrawInstructor> ancestorInstructs;

        ancestorInstructs = new ArrayList<>();
        for(Integer ancestorID : ancestorIDs)
            for(DrawInstructor drawInstruct : drawInstructs)
                if(drawInstruct.getDrawID() == ancestorID)
                    ancestorInstructs.add(drawInstruct);

        return(ancestorInstructs);
    }


    /**
     * Get the list of draw data.
     *
     * @return
     */
    public synchronized ArrayList<DrawInstructor> getDrawInstructorList()
    {
        return(drawInstructs);
    }


    /**
     * <code>DrawList</code> the list to be drawn onto the graphics resource.
     * They are to be drawn based on filters which are data specific.
     *
     * @param g2D the graphic resource to be drawn to
     */
    public synchronized void drawGraphicDisplay(Graphics2D g2D)
    {
        if(drawInstructs.isEmpty())
            return;

        //Test: List Draw Instructions
//        printAllDrawInstructsDescending();


        currentDrawInstructs.clear();
        for(final DrawInstructor drawInstruct : drawInstructs)
        {
            if(drawInstruct.getFrameNo() == currentFrame)
                currentDrawInstructs.add(drawInstruct);
        }

        generateOverlayList();
        for(final DrawInstructor drawInstruct : overlayInstructs)
        {
            baseInstruct = drawInstruct;

            //Test: Draw only specific figure or frame
//            if(drawInstruct.getFigID() == 2)
//            if(drawInstruct.getFrameNo() == 32)
            drawInstruct.draw(g2D);
        }
    }


    /**
     * Set the current frame filter.
     *
     * @param frameNo the frame number to be drawn
     */
    public void setCurrentFrame(long frameNo)
    {
        currentFrame = frameNo;
    }


    /**
     * Generates the overlay display list.
     */
    public void generateOverlayList()
    {
        // TEST: All Frame specific generation
        //overlayCatalogue = splitByAttribute(drawInstructs);
        overlayCatalogue = splitByAttribute(currentDrawInstructs);
        overlayInstructs = getPrecedence(overlayCatalogue);

        // TEST: Print InOverlayList
        //printInstructsCatalogue(overlayInstructs, overlayCatalogue);
    }


    /**
     * Make a copy of <code>ArrayList<DrawInstructor></code>.
     *
     * @param drawInstructlistSrc
     * @return
     */
    private ArrayList<DrawInstructor> copyList(
            ArrayList<DrawInstructor> drawInstructsSrc)
    {
        ArrayList<DrawInstructor> drawInstructlistCopy;

        drawInstructlistCopy = new ArrayList<>();
        for(DrawInstructor drawInstruct: drawInstructsSrc)
        {
            drawInstructlistCopy.add(drawInstruct);
        }

        return(drawInstructlistCopy);
    }


    /**
     * Split <code>ArrayList<DrawInstructor></code> by Attribute.
     *
     * @param drawInstructlist
     * @return
     */
    private ArrayList<ArrayList<DrawInstructor>> splitByAttribute(
            ArrayList<DrawInstructor> drawInstructsList)
    {
        DrawInstructor drawInstruct;
        ArrayList<DrawInstructor> drawInstructsCopy,
                                  attribute;
        ArrayList<ArrayList<DrawInstructor>> drawInstructsAtt;


        drawInstructsCopy = copyList(drawInstructsList);
        drawInstructsAtt = new ArrayList<>();


        while(!drawInstructsCopy.isEmpty())
        {
            attribute = new ArrayList<>();

            drawInstruct = drawInstructsCopy.get(0);
            attribute.add(drawInstruct);
            drawInstructsCopy.remove(0);
            for(DrawInstructor matchInstruct: drawInstructsCopy)
            {
                if(drawInstruct.isSimilarForOverlay(matchInstruct))
                {
                    attribute.add(matchInstruct);
                }
            }

            for(DrawInstructor matchInstruct: attribute)
            {
                drawInstructsCopy.remove(matchInstruct);
            }

            drawInstructsAtt.add(attribute);
        }

        return(drawInstructsAtt);
    }


    /**
     * Returns an <code>ArrayList</code> of <code>DrawInstructor</code>,
     * that has precedence from the specified <code>ArrayList</code>.
     *
     * @param drawInstructsList
     * @return
     */
    private ArrayList<DrawInstructor> getPrecedence(
            ArrayList<ArrayList<DrawInstructor>> drawInstructsAtt)
    {
        DrawInstructor precedent;
        ArrayList<DrawInstructor> precedenceList;

        precedenceList = new ArrayList<>();
        for(ArrayList<DrawInstructor> drawInstructsList: drawInstructsAtt)
        {
            precedent = drawInstructsList.get(0);
            for(DrawInstructor drawInstruct : drawInstructsList)
            {
                if(!precedent.hasPrecedenceForOverlay(drawInstruct))
                {
                    precedent = drawInstruct;
                }
            }

            precedent.setPrecedenceInGroup();
            precedenceList.add(precedent);
        }

        return(precedenceList);
    }


    // TEST: Print Generated Lists
    /**
     * Prints the details of draw instructions.
     *
     * @param overlayInstructs
     * @param overlayCatalogue
     */
    private void printInstructsCatalogue(
            ArrayList<DrawInstructor> instructs,
            ArrayList<ArrayList<DrawInstructor>> catalogue)
    {
        System.out.println("#DrawID,Start Time,End Time,Confidence Value,Span,"
                           + "LHSModified");
        for(DrawInstructor instruct: instructs)
        {
            System.out.println("#Figure ID=" + instruct.getFigID() + " " +
                               "Ascription Level=" +
                               instruct.getAscription() + " " +
                               " Overlay List");
            System.out.println(
                               instruct.getDrawID() + "," +
                               instruct.getStartTime() + "," +
                               instruct.getEndTime() + "," +
                               instruct.getCV() + "," +
                               (instruct.getEndTime() -
                                instruct.getStartTime()) + "," +
                               "\"" + instruct.getLHS() + "\","
                              );
        }

        System.out.println("\n\n");

        for(DrawInstructor instruct: instructs)
        {
            System.out.println("#DrawID " +
                                instruct.getDrawID() + " " +
                                " Draw List");

            for(DrawInstructor catalogueInstruct :
                catalogue.get(instructs.indexOf(instruct)))
            {
                if(instruct != catalogueInstruct)
                {
                    System.out.println(
                                catalogueInstruct.getDrawID() + "," +
                                catalogueInstruct.getStartTime() + "," +
                                catalogueInstruct.getEndTime() + "," +
                                catalogueInstruct.getCV() + "," +
                                (catalogueInstruct.getEndTime() -
                                catalogueInstruct.getStartTime()) + "," +
                                "\"" + catalogueInstruct.getLHS() + "\","
                                        );
                }
            }
        }
    }
    // TEST:


    // TEST: Provide a Competitor & Clone list generation.
    /**
     * Prints the list of filters and its details.
     */
    public synchronized void getDrawLists()
    {
        DrawInstructor drawInstructPrime;
        ArrayList<DrawInstructor> competitors, nearDups, InstructsList;
        ArrayList<ArrayList<DrawInstructor>> clones;


        InstructsList = new ArrayList<>();
        competitors = new ArrayList<>();
        clones = new ArrayList<>();

        for(DrawInstructor drawInstruct: drawInstructs)
        {
            InstructsList.add(drawInstruct);
        }


        System.out.println("#DrawID,Start Time,End Time,Confidence Value,Span,"
                           + "LHSModified");
        while(!InstructsList.isEmpty())
        {
            nearDups = new ArrayList<>();

            drawInstructPrime = InstructsList.get(0);
            InstructsList.remove(0);
            for(DrawInstructor matchInstruct: InstructsList)
            {
                if(drawInstructPrime.isSimilarForListing(matchInstruct))
                {
                    if(drawInstructPrime.hasPrecedenceForListing(matchInstruct))
                    {
                        nearDups.add(matchInstruct);
                        InstructsList.remove(matchInstruct);
                    }
                    else
                    {
                        nearDups.add(drawInstructPrime);
                        drawInstructPrime = matchInstruct;
                        InstructsList.remove(matchInstruct);
                    }
                }
                else
                    nearDups.add(null);
            }

            competitors.add(drawInstructPrime);
            clones.add(nearDups);
        }


        for(DrawInstructor competitor: competitors)
        {
            System.out.println("#EndTime=" + competitor.getEndTime() +
                               " Competitor List");
            System.out.println(
                               competitors.indexOf(competitor) + "," +
                               competitor.getStartTime() + "," +
                               competitor.getEndTime() + "," +
                               competitor.getCV() + "," +
                               (competitor.getEndTime() -
                                competitor.getStartTime()) +
                               "\"" + competitor.getLHS() + "\","
                              );
        }



            for(ArrayList<DrawInstructor> clone : clones)
            {

                    System.out.println("#DrawID " + clones.indexOf(clone)
                                    + " Clone List");

                    for(DrawInstructor nearDup : clone)
                    {
                        if(nearDup != null)
                        {
                        System.out.println(
                                        clones.indexOf(clone) + "," +
                                        nearDup.getStartTime() + "," +
                                        nearDup.getEndTime() + "," +
                                        nearDup.getCV() + "," +
                                        (nearDup.getEndTime() -
                                            nearDup.getStartTime()) +
                                        "\"" + nearDup.getLHS() + "\","
                                            );
                        }
                    }
            }
    }
    // TEST:


    //TEST: Print Draw Instructs
    /**
     * Display all draw instructions.
     */
    public synchronized void printAllDrawInstructsDescending()
    {
        System.out.println("Draw ID,Fig ID,Frame No,Start Msec,End Msec,LHS,"
                           + "LHSFunctor,CV,All Ancestors,All Descendants");
        for(int r = drawInstructs.size()-1; r >= 0; --r)
        {
            baseInstruct = drawInstructs.get(r);
            DrawInstructor instruct = drawInstructs.get(r);

            System.out.print(
                             instruct.getDrawID() + "," +
                             instruct.getFigID() + "," +
                             instruct.getFrameNo() + "," +
                             instruct.getStartTime() + "," +
                             instruct.getEndTime() + "," +
                             "\"" + instruct.getLHS() + "\"," +
                             instruct.getAscription() + "," +
                             instruct.getCV() + ","
                            );


            for(DrawInstructor ancestor : instruct.getDrawAncestorInstructs())
                 System.out.print(ancestor.getDrawID() + "|");
            System.out.print(",");

            for(DrawInstructor predecessor : instruct.getPredecessorInstructs())
                if(baseInstruct.getDrawID() != predecessor.getDrawID())
                    System.out.print(predecessor.getDrawID() + "|");
            System.out.print(",");

            System.out.println("");
        }

        System.exit(0);
    }
    //TEST:


    /**
     * True if point is within <code>GraphicalDisplay</code>.
     *
     * @param point
     * @return
     */
    public GraphicalContainer contains(Point point)
    {
        if(overlayInstructs == null)
            return(null);


        GraphicalContainer atDrawInstruct;

        for(DrawInstructor drawInstruct: overlayInstructs)
        {
            atDrawInstruct = drawInstruct.contains(point);
            if(atDrawInstruct != null)
                return(atDrawInstruct);
        }


        return(null);
    }


    /**
     * Returns tool tip data.
     *
     * @param point
     * @return
     */
    public String getToolTip(Point point)
    {
        GraphicalContainer drawInstruct;
        StringBuilder tipText = new StringBuilder();


        drawInstruct = contains(point);
        if(drawInstruct != null)
        {
            tipText.append("<html>");
            tipText.append(drawInstruct.getToolTip());
            tipText.append("</html>");

            return(tipText.toString());
        }

        return(null);
    }
}
