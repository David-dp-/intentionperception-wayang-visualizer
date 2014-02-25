                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw;


/* Import */
import Wayang.Constants;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplay;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;




/**
 * <code>DrawInstructor</code> class holds the relevant data for a single
 * draw instructions.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class DrawInstructor implements GraphicalContainer, Constants,
                                       Comparable<DrawInstructor>
{
    /* Variables */
    private int drawID, figID;
    private long frameNo, startTime, endTime;
    private double cv;
    private boolean precedent;
    private String lhs, ascription;
    private EpistemicStatus episStatus;
    private GraphicalDisplay graphicData;
    private ArrayList<Integer> ancestorIDs;
    private ArrayList<DrawInstructor> ancestorInstructs,
                                      drawAncestorInstructs,
                                      predecessorInstructs;




    /**
     * Prevent this class from being initiated without parameters.
     */
    private DrawInstructor()
    {}


    /**
     * Constructs a <code>DrawInstructor</code>.
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
    public DrawInstructor(EpistemicStatus episStatus,
                          int drawID, ArrayList<Integer> ancestorIDs, int figID,
                          int frameNo, String lhs, String ascription,
                          long startTime, long endTime,
                          double cv, GraphicalDisplay graphicData)
    {
        this.episStatus = episStatus;
        this.frameNo = frameNo;
        this.drawID = drawID;
        this.ancestorIDs = ancestorIDs;
        this.figID = figID;
        this.lhs = lhs;
        this.ascription = ascription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cv = cv;
        this.graphicData = graphicData;

        precedent = false;

        graphicData.setInstructor(this);
    }


    /**
     * Constructs a <code>DrawInstructor</code>.
     *
     * @param episStatus
     * @param drawID
     * @param ancestorIDs
     * @param ancestorInstructs
     * @param figID
     * @param frameNo
     * @param lhs
     * @param ascription
     * @param startTime
     * @param endTime
     * @param cv
     * @param graphicData
     */
    public DrawInstructor(EpistemicStatus episStatus,
                          int drawID, ArrayList<Integer> ancestorIDs,
                          ArrayList<DrawInstructor> ancestorInstructs,
                          int figID, int frameNo,
                          String lhs, String ascription,
                          long startTime, long endTime,
                          double cv, GraphicalDisplay graphicData)
    {
        this(episStatus,
             drawID, ancestorIDs, figID,
             frameNo, lhs, ascription,
             startTime, endTime,
             cv, graphicData);

        this.ancestorInstructs = ancestorInstructs;
        constructDrawInstructor();
    }


    /**
     * Construct <code>DrawInstructor</code>.
     */
    private void constructDrawInstructor()
    {
        constructHierarchyInstructs();
    }


    /**
     * Construct hierarchy instructs.
     */
    private void constructHierarchyInstructs()
    {
        LinkedHashSet<DrawInstructor> uniqueList;
        String previousAscription;


        drawAncestorInstructs = new ArrayList<>();
        predecessorInstructs = new ArrayList<>();


        for(DrawInstructor ancestorInstruct : ancestorInstructs)
        {
            ancestorInstruct.addPredecessorInstruct(this);

            //TEST: Print all ancestors ignoring InOverlay algorithm
//            drawAncestorInstructs.add(ancestorInstruct);

            previousAscription = getAscription();
            if(!(getAscription().equals(ancestorInstruct.getAscription())))
            {
                previousAscription = ancestorInstruct.getAscription();
                drawAncestorInstructs.add(ancestorInstruct);
            }


            if(!(ancestorInstruct.getDrawAncestorInstructs().isEmpty()))
            {
                if(!(previousAscription.equals(
                     ancestorInstruct.getDrawAncestorInstructs().get(0)
                                     .getAscription())))
                {
                    drawAncestorInstructs.addAll(ancestorInstruct
                                                 .getDrawAncestorInstructs());
                }
                else
                {
                    if(!(ancestorInstruct.getDrawAncestorInstructs().size() > 1))
                        drawAncestorInstructs.addAll(
                                              1, ancestorInstruct
                                                 .getDrawAncestorInstructs());
                }
            }
        }


        uniqueList = new LinkedHashSet<>(drawAncestorInstructs);
        drawAncestorInstructs.clear();
        drawAncestorInstructs.addAll(uniqueList);
        Collections.sort(drawAncestorInstructs);
    }


    /**
     * Returns the episStatus of the DrawInstructor.
     *
     * @return
     */
    public EpistemicStatus getEpistemicStatus()
    {
        return(episStatus);
    }


    /**
     * Returns the frame number of the DrawInstructor.
     *
     * @return returns the DrawInstructor frame.
     */
    public long getFrameNo()
    {
        return(frameNo);
    }


    /**
     * Returns the draw ID.
     *
     * @return returns the ID
     */
    public int getDrawID()
    {
        return(drawID);
    }


    /**
     * Returns the ID of the figure.
     *
     * @return returns the ID
     */
    public int getFigID()
    {
        return(figID);
    }


    /**
     * Returns the graphic display data.
     *
     * @return
     */
    public GraphicalDisplay getGraphicData()
    {
        return(graphicData);
    }


    /**
     * Returns the time draw information begins.
     *
     * @return returns the start time
     */
    public long getStartTime()
    {
        return(startTime);
    }


    /**
     * Returns the time draw information ends.
     *
     * @return returns the end time
     */
    public long getEndTime()
    {
        return(endTime);
    }


    /**
     * Returns the elapsed time.
     *
     * @return returns the elapsed time
     */
    public long getElapsedTime()
    {
        return(endTime - startTime);
    }


    /**
     * Returns the CV of the DrawInstructor.
     *
     * @return returns the CV
     */
    public double getCV()
    {
        return(cv);
    }


    /**
     * Returns the LHS of the DrawInstructor.
     *
     * @return returns the CV
     */
    public String getLHS()
    {
        return(lhs);
    }


    /**
     * Returns the ascription.
     *
     * @return
     */
    public String getAscription()
    {
        return(ascription);
    }


    /**
     * Returns the ascription level.
     *
     * @return
     */
    public int getAscriptionLevel()
    {
        return(ascriptions.get(ascription));
    }


    /**
     * Returns the ancestor ID.
     *
     * @return returns the ID
     */
    public ArrayList<Integer> getAncestorIDs()
    {
        return(ancestorIDs);
    }


    /**
     * Returns a list of ancestor draw instructs.
     *
     * @return
     */
    public ArrayList<DrawInstructor> getAncestorInstructs()
    {
        return(ancestorInstructs);
    }


    /**
     * Returns a list of drawable ancestor instructs.
     *
     * @return
     */
    public ArrayList<DrawInstructor> getDrawAncestorInstructs()
    {
        return(drawAncestorInstructs);
    }


    /**
     * Returns a list of ancestor predecessor instructs.
     *
     * @return
     */
    public ArrayList<DrawInstructor> getPredecessorInstructs()
    {
        return(predecessorInstructs);
    }


    /**
     * Returns a list of drawable predecessor instructs.
     *
     * @return
     */
    public ArrayList<DrawInstructor> getDrawPredecessorInstructs()
    {
        ArrayList<DrawInstructor> drawPredecessorInstructs =
                                  new ArrayList<>();


        drawPredecessorInstructs.add(DrawList.baseInstruct);
        for(DrawInstructor instruct :
            DrawList.baseInstruct.getDrawAncestorInstructs())
        {
            if(getDrawID() < instruct.getDrawID())
                drawPredecessorInstructs.add(instruct);
        }
        Collections.reverse(drawPredecessorInstructs);

        return(drawPredecessorInstructs);
    }


    /**
     * Adds a predecessor.
     *
     * @param predecessor
     */
    public void addPredecessorInstruct(DrawInstructor predecessor)
    {
        predecessorInstructs.add(predecessor);
    }


    /**
     * Sets the draw instruct as having precedence in its classified group.
     */
    public void setPrecedenceInGroup()
    {
        precedent = true;
    }


    /**
     * Returns the status of its precedence within its classified group.
     *
     * @return
     */
    public boolean isPrecedentInGroup()
    {
        return(precedent);
    }


    /**
     * Returns true if <code>this</code> <code>DrawInstructor</code> is similar
     * for Overlay purposes to the specified <code>DrawInstructor</code>.
     *
     * @param drawInstruct
     * @return
     */
    public boolean isSimilarForOverlay(DrawInstructor drawInstruct)
    {
        return((getFigID() == drawInstruct.getFigID()));
    }


    /**
     * Returns <code>true</code> if <code>this</code>
     * <code>DrawInstructor</code> has a higher precedence to be displayed as
     * compared to the specified <code>DrawInstructor</code>.
     *
     * @param drawInstruct
     * @return
     */
    public boolean hasPrecedenceForOverlay(DrawInstructor drawInstruct)
    {
        return(
        (getAscriptionLevel() > drawInstruct.getAscriptionLevel()) ||
        ((getAscriptionLevel() == drawInstruct.getAscriptionLevel()) &&
         (getCV() > drawInstruct.getCV())));
    }


    /**
     * Returns true if <code>this</code> <code>DrawInstructor</code> is a near
     * duplicate to the specified <code>DrawInstructor</code>.
     *
     * @param drawInstruct
     * @return
     */
    public boolean isSimilarForListing(DrawInstructor drawInstruct)
    {
        return((getLHS().equals(drawInstruct.getLHS())) &&
               (getStartTime() == drawInstruct.getStartTime()) &&
               (getEndTime() == drawInstruct.getEndTime()));
    }


    /**
     * Returns <code>true</code> if <code>this</code>
     * <code>DrawInstructor</code> has a higher precedence to be displayed as
     * compared to the specified <code>DrawInstructor</code>.
     *
     * @param drawInstruct
     * @return
     */
    public boolean hasPrecedenceForListing(DrawInstructor drawInstruct)
    {
        return((getCV() > drawInstruct.getCV()) ||
               ((getCV() <= drawInstruct.getCV()) &&
               ((getEndTime() - getStartTime()) >=
               (drawInstruct.getEndTime() - drawInstruct.getStartTime()))));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2D)
    {
        if(getDrawID() == DrawList.baseInstruct.getDrawID())
            for(DrawInstructor ancestorInstruct : drawAncestorInstructs)
                ancestorInstruct.draw(g2D);

        getGraphicData().draw(g2D);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Area getEnvelope()
    {
        return(getGraphicData().getEnvelope());
    }


    /**
     * Returns an envelope encompassing all the ancestors.
     *
     * @return
     */
    public Area getAncestorEnvelope()
    {
        Area envelope;


        envelope = getEnvelope();
        for(DrawInstructor ancestorInstruct : drawAncestorInstructs)
            envelope.add(ancestorInstruct.getAncestorEnvelope());


        return(envelope);
    }


    /**
     * Get ancestor enveloping that is resized based on stack order.
     *
     * @return
     */
    public Area getSizedAncestorEnvelope()
    {
        Area sizedAncestorEnvelop;

        sizedAncestorEnvelop = new Area(getAncestorEnvelope());
        sizedAncestorEnvelop.transform(AffineTransform.getScaleInstance(
                         (1 / (getEnvelope().getBounds2D().getWidth())) *
                               (getEnvelope().getBounds2D().getWidth() +
                                ENVELOPE_PADDING * getEnvelopeStackedOrder()),
                         (1 / (getEnvelope().getBounds2D().getHeight())) *
                               (getEnvelope().getBounds2D().getHeight() +
                                ENVELOPE_PADDING * getEnvelopeStackedOrder())));
        sizedAncestorEnvelop.transform(AffineTransform.getTranslateInstance(
                         getEnvelope().getBounds2D().getCenterX() -
                         sizedAncestorEnvelop.getBounds2D().getCenterX(),
                         getEnvelope().getBounds2D().getCenterY() -
                         sizedAncestorEnvelop.getBounds2D().getCenterY()));

        return(sizedAncestorEnvelop);
    }


    /**
     * Returns the current high level envelope stack order.
     *
     * @return
     */
    public int getEnvelopeStackedOrder()
    {
        int level;


        level = 0;
        for(DrawInstructor instruct :
            getDrawAncestorInstructs())
        {
            if(instruct.getGraphicData().isStackable())
            {
                    level++;
            }
        }


        return(level);
    }


    /**
     * Returns the largest envelope bound.
     *
     * @return
     */
    public Rectangle2D getLargestStackedBounds()
    {
        return(DrawList.baseInstruct.getSizedAncestorEnvelope().getBounds2D());
    }


    /**
     * Returns the annotation label's stacking order.
     *
     * @return
     */
    public int getAnnotationStackOrder()
    {
        int level = 0;


        for(DrawInstructor instruct :
            DrawList.baseInstruct.getDrawAncestorInstructs())
        {
            if(getDrawID() > instruct.getDrawID())
            {
                    level++;
            }
        }


        return(level);
    }


    /**
     * Returns the annotation label's shade order.
     *
     * @return
     */
    public int getAnnotationShadeOrder()
    {
        boolean nonStacker;
        int level;


        level = 0;
        nonStacker = false;
        for(DrawInstructor instruct :
            getDrawPredecessorInstructs())
        {
            if(getDrawID() < instruct.getDrawID())
            {
                if(instruct.getGraphicData().isStackable())
                {
                    level++;
                }
                else
                {
                    nonStacker = true;
                }
            }
        }

        if(getGraphicData().isStackable())
        {
            level++;
        }
        else
        {
            nonStacker = true;
        }


        if(nonStacker)
            level++;

        return(level);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicalContainer contains(Point point)
    {
        ArrayList<DrawInstructor> reversedList;

        reversedList = new ArrayList<>(drawAncestorInstructs);
        Collections.reverse(reversedList);


        for(DrawInstructor ancestorInstruct : reversedList)
        {
            if(ancestorInstruct.contains(point) != null)
                return(ancestorInstruct);
        }


        if(getGraphicData().contains(point) != null)
            return(this);

        return(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder getToolTip()
    {
        return(new StringBuilder(
               "<u><b><i>Draw Detail:</i></b></u><br>" +
               "Draw ID: " + getDrawID() + "<br>" +
               "Fig ID: " + getFigID() + "<br>" +
               "Start Time: " + getStartTime() + "<br>" +
               "End Time: " + getEndTime() + "<br>" +
               "Confidence: " + getCV() + "<br>" +
               "<br>").append(
               getGraphicData().getToolTip()));
    }


    /**
     * Compare <code>DrawInstructor</code> in descending order based on it's
     * draw ID.
     *
     * @param instruct
     * @return
     */
    @Override
    public int compareTo(DrawInstructor instruct)
    {
        if(getDrawID() == instruct.getDrawID())
            return(0);
        if(getDrawID() > instruct.getDrawID())
            return(-1);
        //if(getDrawID() > instruct.getDrawID())
            return(1);
    }
}
