                                    /*OHM*/


/* Package */
package Wayang.GUI.Glazier.Draw;


/* Import */
import Wayang.FileAccess;
import Wayang.GUI.Glazier.Draw.Figures.Circle;
import Wayang.GUI.Glazier.Draw.Figures.FigureShape;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplay;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplayForCurvedTrajectory;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplayForHighLevelAscription;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplayForLinearTrajectory;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.GraphicalDisplayForStationaryTrajectory;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.ToolTipDetail;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForAugmentationOfIntentionToApproachWithIntentionToAvoid;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForConjunctionOfForces;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForConjunctionOfIntentionsToApproachAndAvoid;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForIntentionToApproachOrAvoid;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForIntentionToBeAtPosition;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForNoticing;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForSingleForce;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForTrajectoryFromWiggle;
import Wayang.GUI.Glazier.Draw.GraphicDisplay.ToolTips.TooltipForWiggle;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingWorker;




/**
 * <code>DrawDataParser</code> parsers the draw instruction data into a
 * <code>Collection</code> of <code>DrawInstructor</code>.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public class DrawDataParser extends SwingWorker<DrawList, Integer>
{
    /* Variables */
    private ArrayList<Object> objectList;
    private JLabel progress;
    private String dataFilename;
    private DrawList drawList;




    /**
     * Prevent this class from being initiated without parameters.
     *
     * @param dataFilename
     * @param drawList
     * @param progress
     */
    public DrawDataParser(String dataFilename, DrawList drawList, JLabel progress)
    {
        objectList = new ArrayList<>();

        this.dataFilename = dataFilename;
        this.progress = progress;

        this.drawList = drawList;
        drawList.getDrawInstructorList().clear();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected DrawList doInBackground()
    {
        parseData();

        return(drawList);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void done()
    {
        progress.setText(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(List<Integer> chunks)
    {
        progress.setText("Loading...." + (chunks.get(chunks.size() - 1)) +
                         "%");
    }


    /**
     * Parse data file.
     */
    private void parseData()
    {
        FileAccess dataFile = new FileAccess(dataFilename);
        long line = 1, totalLine = dataFile.getLineCount();
        int progression;
        String data;


        objectList.clear();

        //TEST: Limit Data Extraction
//        while(((data = dataFile.readLine()) != null) && (line < 1000))
        while((data = dataFile.readLine()) != null)
        {
            progression = (int)((100.0 / totalLine) * line++);
            publish(progression);
            setProgress(progression);
            parseDrawInstruction(data);
        }

        dataFile.close();
    }


    /**
     * Parse draw instruction.
     */
    private Object parseDrawInstruction(String command)
    {
        splitConstructors(command);

        return(null);
    }


    /**
     * Split by constructors
     */
    private void splitConstructors(String constructor)
    {
        int newStatement, paramStart, paramEnd,
            endStatement, endComma, endBracket;

        newStatement = constructor.lastIndexOf("new");
        paramStart = constructor.indexOf('(', newStatement);
        paramEnd = constructor.indexOf(')', newStatement);


        endComma = constructor.indexOf(',', paramEnd);
        endBracket = constructor.indexOf(')', paramEnd+1);
        if(((endComma < endBracket) ||
            (constructor.substring(paramStart, paramEnd)
                        .contains("Arrays.asList"))) &&
           (endComma >= 0))
            endStatement = endComma;
        else
            endStatement = endBracket;


        if((newStatement != -1) &&
           (paramStart != -1) &&
           (paramEnd != -1) &&
           (endStatement > 0))
        {
            int newStatementMod = newStatement;
            int endStatementMod = endStatement;

            if(newStatementMod < 0)
                newStatementMod = 0;
            if(endStatementMod <= 0)
                endStatementMod = constructor.length();

            splitParameters(constructor.substring(newStatementMod,
                                                  endStatementMod));


            splitConstructors(constructor.substring(0, newStatement) +
                              constructor.substring(endStatement,
                                                    constructor.length()));
        }
        else
        {
            splitParameters(constructor);
        }
    }


    /**
     * Split by parameters.
     *
     * @param parameters
     * @return
     */
    private void splitParameters(String parameters)
    {
        int paramStart, paramEnd;
        String parameterArray[];

        paramStart = parameters.indexOf('(') + 1;
        paramEnd = parameters.lastIndexOf(')');


        if(parameters.contains("ArrayList<Point>(Arrays.asList"))
        {
            parameterArray = parameters
                             .replaceAll("^.*asList\\(", " ")
                             .replaceAll("\\)+", " ")
                             .split(",");
        }
        else
        if(parameters.contains("ArrayList<Integer>(Arrays.asList"))
        {
            parameterArray = parameters
                             .replaceAll("^.*asList\\(", "")
                             .replaceAll("\\)+", "")
                             .split(",");
        }
        else
        if(parameters.contains("draw("))
        {
            int quotedBegin, qoutedEnd;
            String[] quotedStrs;


            quotedBegin = parameters.indexOf('\"');
            qoutedEnd = parameters.lastIndexOf('\"');
            quotedStrs = parameters.substring(quotedBegin, qoutedEnd + 1)
                                  .replace("\",\"",";")
                                  .replace("\"","")
                                  .split(";");
            parameterArray = parameters
                             .replaceAll("^,+draw\\(", "")
                             .replaceAll("\".*\"", ",")
                             .replaceAll("\\)+$", "")
                             .split(",");


            parameterArray[5] = quotedStrs[0];
            parameterArray[6] = quotedStrs[1];
        }
        else
            parameterArray = parameters
                             .substring(paramStart, paramEnd)
                             .replaceAll("\"", "")
                             .replaceAll("\\(", "")
                             .replaceAll("\\)", "")
                             .split(",");


        switch(parameters.replaceAll("^.*new *", "")
                         .replaceAll(" *\\(.*$", ""))
        {
            case "Circle":
            {
                objectList.add(new Circle(Integer.parseInt(parameterArray[0])));
            }
            break;

            case "Color":
            {
                objectList.add(new Color(Integer.parseInt(parameterArray[0]),
                                         Integer.parseInt(parameterArray[1]),
                                         Integer.parseInt(parameterArray[2])));
            }
            break;

            case "Point":
            {
                objectList.add(new Point(Integer.parseInt(parameterArray[0]),
                                         Integer.parseInt(parameterArray[1])));
            }
            break;

            case "GraphicalDisplayForStationaryTrajectory":
            {
                objectList.add(new GraphicalDisplayForStationaryTrajectory(
                                    (Color) objectList.get(objectList.size() - 1),
                                    Integer.parseInt(parameterArray[1]),
                                    Integer.parseInt(parameterArray[2]),
                                    (FigureShape) objectList.get(objectList.size() - 2),
                                    parameterArray[4],
                                    parameterArray[5]));

                objectList.remove(objectList.size() - 2);
                objectList.remove(objectList.size() - 2);
            }
            break;


            case "GraphicalDisplayForLinearTrajectory":
            {
                objectList.add(new GraphicalDisplayForLinearTrajectory(
                                    (Color) objectList.get(objectList.size() - 1),
                                    Integer.parseInt(parameterArray[1]),
                                    Integer.parseInt(parameterArray[2]),
                                    (FigureShape) objectList.get(objectList.size() - 2),
                                    parameterArray[4],
                                    parameterArray[5],
                                    parameterArray[6],
                                    parameterArray[7]));

                objectList.remove(objectList.size() - 2);
                objectList.remove(objectList.size() - 2);
            }
            break;

            case "GraphicalDisplayForCurvedTrajectory":
            {
                CurveDirection direction;

                if(parameters.contains("CurveDirection.ANTICLOCKWISE"))
                    direction = (CurveDirection.ANTICLOCKWISE);
                else
                    direction = (CurveDirection.CLOCKWISE);


                objectList.add(new GraphicalDisplayForCurvedTrajectory(
                                    (Color) objectList.get(objectList.size() - 1),
                                    Integer.parseInt(parameterArray[1]),
                                    Integer.parseInt(parameterArray[2]),
                                    Integer.parseInt(parameterArray[3]),
                                    Integer.parseInt(parameterArray[4]),
                                    Integer.parseInt(parameterArray[5]),
                                    direction,
                                    (FigureShape) objectList.get(objectList.size() - 2),
                                    parameterArray[8],
                                    parameterArray[9],
                                    parameterArray[10],
                                    parameterArray[11],
                                    parameterArray[12],
                                    parameterArray[13],
                                    parameterArray[14],
                                    parameterArray[15]));

                objectList.remove(objectList.size() - 2);
                objectList.remove(objectList.size() - 2);
            }
            break;

            case "GraphicalDisplayForHighLevelAscription":
            {
                objectList.add(new GraphicalDisplayForHighLevelAscription(
                                    (Color) objectList.get(objectList.size() - 1),
                                    (ArrayList<Point>) objectList.get(objectList.size() - 2),
                                    (FigureShape) objectList.get(objectList.size() - 3),
                                    (ToolTipDetail) objectList.get(objectList.size() - 4)));

                objectList.remove(objectList.size() - 2);
                objectList.remove(objectList.size() - 2);
                objectList.remove(objectList.size() - 2);
                objectList.remove(objectList.size() - 2);
            }
            break;

            case "TooltipForAugmentationOfIntentionToApproachWithIntentionToAvoid":
            {
                objectList.add(new TooltipForAugmentationOfIntentionToApproachWithIntentionToAvoid(
                                                    parameterArray[0],
                                                    parameterArray[1],
                                                    parameterArray[2]));
            }
            break;

            case "TooltipForConjunctionOfForces":
            {
                objectList.add(new TooltipForConjunctionOfForces(
                                                    parameterArray[0],
                                                    parameterArray[1],
                                                    parameterArray[2],
                                                    parameterArray[3]));
            }
            break;

            case "TooltipForConjunctionOfIntentionsToApproachAndAvoid":
            {
                objectList.add(new TooltipForConjunctionOfIntentionsToApproachAndAvoid(
                                                    parameterArray[0],
                                                    parameterArray[1]));
            }
            break;

            case "TooltipForIntentionToApproachOrAvoid":
            {
                objectList.add(new TooltipForIntentionToApproachOrAvoid(
                                                    parameterArray[0],
                                                    parameterArray[1]));
            }
            break;

            case "TooltipForIntentionToBeAtPosition":
            {
                objectList.add(new TooltipForIntentionToBeAtPosition(
                                                    parameterArray[0],
                                                    parameterArray[1]));
            }
            break;

            case "TooltipForNoticing":
            {
                objectList.add(new TooltipForNoticing(
                                                    parameterArray[0]));
            }
            break;

            case "TooltipForSingleForce":
            {
                objectList.add(new TooltipForSingleForce(
                                                    parameterArray[0],
                                                    parameterArray[1],
                                                    parameterArray[2]));
            }
            break;

            case "TooltipForTrajectoryFromWiggle":
            {
                objectList.add(new TooltipForTrajectoryFromWiggle(
                                                    parameterArray[0],
                                                    parameterArray[1],
                                                    parameterArray[2],
                                                    parameterArray[3],
                                                    parameterArray[4]));
            }
            break;

            case "TooltipForWiggle":
            {
                objectList.add(new TooltipForWiggle(
                                                    parameterArray[0]));
            }
            break;

            case "ArrayList<Integer>":
            {
                ArrayList<Integer> list = new ArrayList<>();

                for(String str : parameterArray)
                {
                    list.add(Integer.parseInt(str));
                }

                objectList.add(list);
            }
            break;

            case "ArrayList<Point>":
            {
                ArrayList<Point> list = new ArrayList<>();

                for(String str : parameterArray)
                {
                    list.add((Point) objectList.get(objectList.size() - 1));
                    objectList.remove(objectList.size() - 1);
                }

                objectList.add(list);
            }
            break;

            default:
            {
                EpistemicStatus status;


                if(parameters.contains("EpistemicStatus.OBSERVATION"))
                    status = EpistemicStatus.OBSERVATION;
                else
                    status = EpistemicStatus.PREDICTION;

                drawList.draw(
                        status,
                        Integer.parseInt(parameterArray[1]),
                        (ArrayList<Integer>) objectList.get(objectList.size() - 1),
                        Integer.parseInt(parameterArray[3]),
                        Integer.parseInt(parameterArray[4]),
                        parameterArray[5],
                        parameterArray[6],
                        Long.parseLong(parameterArray[7]),
                        Long.parseLong(parameterArray[8]),
                        Double.parseDouble(parameterArray[9]),
                        (GraphicalDisplay) objectList.get(objectList.size() - 2));


                objectList.clear();
            }
            break;
        }
    }


    /**
     * Returns the parsed draw data.
     *
     * @return DrawList
     */
    public DrawList getDrawList()
    {
        return(drawList);
    }
}
