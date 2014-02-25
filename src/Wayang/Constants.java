                                    /*OHM*/


/* Package */
package Wayang;


/* Imports */
/* Import - Standard */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.HashMap;




/**
 * Constants for <code>Wayang</code>.
 *
 * @author kumaresh
 * @version : "%I%, %G%"
 */
public interface Constants
{
    /* Constants */
    /* Constants - Pathnames */
    /** Pathname: Base resource directory. */
    static final String RESOURCE_BASE = "/Wayang/Resources";
    /** Pathname: Image resource directory. */
    static final String RESOURCE_IMAGE = RESOURCE_BASE + "/Images";
    /** Pathname: SWF resource directory. */
    static final String RESOURCE_SWF = RESOURCE_BASE + "/SWF";
    /** Pathname: Data resource directory. */
    static final String RESOURCE_DATA = RESOURCE_BASE + "/Data";

    /** Pathname: SWF icon. */
    static final String SWF_ICON_PATH = RESOURCE_IMAGE + "/SWF.png";
    /** Pathname: Play icon. */
    static final String PLAY_ICON_PATH = RESOURCE_IMAGE + "/Play.png";
    /** Pathname: Pause Icon. */
    static final String PAUSE_ICON_PATH = RESOURCE_IMAGE + "/Pause.png";
    /** Pathname: Stop Icon. */
    static final String STOP_ICON_PATH = RESOURCE_IMAGE + "/Stop.png";
    /** Pathname: Skip Backward Icon. */
    static final String SKIP_BACK_ICON_PATH = RESOURCE_IMAGE +
                                              "/Skip Backward.png";
    /** Pathname: Skip Forward Icon. */
    static final String SKIP_FORWARD_ICON_PATH = RESOURCE_IMAGE +
                                                 "/Skip Forward.png";
    /** Pathname: Seek Backward Icon. */
    static final String SEEK_BACK_ICON_PATH = RESOURCE_IMAGE +
                                              "/Seek Backward.png";
    /** Pathname: Seek Forward Icon. */
    static final String SEEK_FORWARD_ICON_PATH = RESOURCE_IMAGE +
                                                 "/Seek Forward.png";
    /** Pathname: Loop Icon. */
    static final String LOOP_ICON_PATH = RESOURCE_IMAGE + "/Loop.png";
    /** Pathname: Predict Icon. */
    static final String PREDICT_ICON_PATH = RESOURCE_IMAGE + "/Predict.png";
    /** Pathname: Tree Icon. */
    static final String TREE_ICON_PATH = RESOURCE_IMAGE + "/Tree.png";
    /** Pathname: Reel Icon. */
    static final String REEL_ICON_PATH = RESOURCE_IMAGE + "/Reel.png";

    /** Pathname: Startup Logo. */
    static final String LOGO_IMAGE = RESOURCE_IMAGE + "/Splash.jpg";
    /** Pathname: Startup SWF. */
    static final String LOGO_SWF = RESOURCE_SWF + "/Splash.swf";

    /** Data File. */
    static final String DRAW_INSTRUCTIONS = RESOURCE_DATA +
                                            "/Draw_Instructions.txt";


    /* Constants - User Interface */
    /** The relative point of origin. */
    final static Point POINT_OF_ORIGIN = new Point(0,0);
    /** Popup menu X-axis offset for display. */
    static final int POPUP_X_OFFSET = 50;
    /** Borderless <code>Insets</code>. */
    final static Insets BORDERLESS_INSETS = new Insets(0,0,0,0);
    /** Double click count for <code>MouseEvent</code>. */
    final static int DOUBLE_CLICK = 2;


    /* Constants - Graphics */
    /** Color: Transparent. */
    static final Color COLOR_TRANSPARENT = new Color(0,0,0,0);
    static final Color COLOR_TRANSPARENT_FILL = new Color(0,0,0,1);
    static final int COLOR_MAX_INT = 200;
    static final int COLOR_MAX_PERCENT = 100;
    static final double COLOR_TRANSLUCENCY_INCREMENT_PERCENT = 5;
    static final Color COLOR_TOOLTIP_BACKGROUND = new Color(255,255,160);

    static final DecimalFormat GRAPHIC_NUMBER_FORMAT = new DecimalFormat
                                                           ("####E0");

    /* Constants - Player */
    /** Start frame of Flash animation. */
    static final long FLASHPLAYER_START = 1;
    /** Offset to align frame representation between components. */
    static final long FLASHPLAYER_OFFSET = 1;


    /* Constants - Wayang */
    static final String MOTIONLESS = "0";

    static final String UNKNOWN_ASCRIPTION = "Unknown Ascription";
    static final int UNKNOWN_ASCRIPTION_LEVEL = -1;

    static final String PRECEDENCE_INDICATOR = "*";


    //TODO: From Config File
    static final int OVERLAY_SCREEN_EXPAND = 100;


    static final int TOOLTIP_INITAL_DELAY = 250;


    static final double ARROW_ANGLE = 60.0;
    static final double ARROW_LENGTH = 10.0f;


    static final double ENVELOPE_PADDING = 40.0;
    static final float ENVELOPE_THICKNESS = 6.0f;
    static final float ENVELOPE_ALPHA = 0.4f;
    static final boolean ENVELOPE_BORDER = false;


    static final double TRAIL_FACTOR = 240.0;
    static final float TRAIL_THICKNESS = 2.4f;
    static final BasicStroke OBSERVED_TRAIL_STROKE =
                             new BasicStroke(TRAIL_THICKNESS,
                                             BasicStroke.CAP_BUTT,
                                             BasicStroke.JOIN_ROUND);


    static final double FIGURE_PADDING = 14.0;
    static final float FIGURE_THICKNESS = 4.0f;
    static final BasicStroke OBSERVED_FIGURE_STROKE =
                             new BasicStroke(FIGURE_THICKNESS,
                                             BasicStroke.CAP_BUTT,
                                             BasicStroke.JOIN_ROUND);


    static final Color PREDICTION_COLOR = Color.GREEN;
    static final float PREDICTION_DASH[] = {6.0f};
    static final BasicStroke PREDICTION_TRAIL_STROKE =
                             new BasicStroke(TRAIL_THICKNESS,
                                 BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
                                 10.0f, PREDICTION_DASH, 0.0f);
    static final BasicStroke PREDICTION_FIGURE_STROKE =
                             new BasicStroke(FIGURE_THICKNESS,
                                 BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
                                 10.0f, PREDICTION_DASH, 0.0f);


    static final double TEXT_BOX_PADDING = 6.0;
    static final float TEXT_BOX_THICKNESS = 1.0f;
    static final float TEXT_BOX_ALPHA = ENVELOPE_ALPHA;
    static final BasicStroke TEXT_BOX_STROKE =
                                new BasicStroke(TEXT_BOX_THICKNESS,
                                                BasicStroke.CAP_BUTT,
                                                BasicStroke.JOIN_ROUND);


    static final HashMap<String, Integer> ascriptions =
                 new HashMap<String, Integer>()
                 {{
                    put("intendAugmented", 4);
                    put("intendCombined", 4);
                    put("intend", 4);
                    put("exertForceOnCombined", 4);
                    put("exertForceOn", 4);
                    put("notice", 4);
                    put("figureHasTrajectoryFromWiggle", 3);
                    put("wiggle", 2);
                    put("figureHasTrajectory", 1);
                 }};

}
