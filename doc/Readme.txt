Steps to import and configure the project in Eclipse.
Otherwise explicitly stated, use default settings.

Part 1: Setup
-------------------------------------------------------------------------------
Import the project to Eclipse IDE
i1. Right click on the 'Package Explorer' and select 'import'
i2. Import from 'SVN' and checkout a project
i3. Select 'Wayang_Visualizer'
i4. Project Name: 'Wayang_Visualizer'
i5. Finish

Configure Eclipse IDE to run the project
c1. Right click on the project in the 'Package Explorer'
     and select 'Run As|Run Configurations...'
c2. Under 'Java Application', select the 'Main' tab
     - Main Class: 'Wayang - Wayang'
c3. Select the 'Arguments' tab
     - VM arguments: -Xms1024m -Xmx1024m
c4. Tap the Close button in the lower right
-------------------------------------------------------------------------------

Part 2: Demo
-------------------------------------------------------------------------------
v1. Right-click on Wayang_Visualizer in Package Explorer, and choose
    Run As | Java Application.
    
v2. If a list dialog appears that include Wayang, select Wayang.
    
    A dialog titled 'Wayang' should open that shows the IHPC logo (with colors
     reversed for an unknown reason), and standard video playback controls
     underneath, plus some extras.
 
v3. Tap the button in the lower-right that, on hover, is labelled
    'Show/Hide Overlay'. (If you don't select it, the SWF will play as if in
    a normal Flash player with no Wayang analysis overlaid.) A 'Loading...'
    label might appear in the upper-right, but you don't have to wait for it
    to reach 100%.
    
v4. Unless you want the SWF to loop, make sure the button in the lower-right
	that, on hover, is labelled 'Loop SWF Animation' is NOT selected.
    
v5. Click the movie-reel button in the lower-left (on hover, it says
     'Load A New SWF File')
     
v6. Navigate to "[your Eclipse workspace folder]/Wayang_Visualizer/src/Wayang/Resources/Data/Animation.swf"

    The application will assume that any file named 'Draw_Instructions.txt' in 
    the same folder contains the Wayang project's analysis of the SWF you just
    selected.
    
v7. You can use the playback controls to pause, resume, or scrub to a specific
    starting point.
    
v8. The app can be exited by tapping the red X button in the chrome area of
	the window.
 
To test other SWFs, run them through Wayang first to generate an analysis txt
 file, then overwrite Draw_Instructions.txt (but don't checkin) at
 "[your Eclipse workspace folder]/Wayang_Visualizer/src/Wayang/Resources/Data/".
-------------------------------------------------------------------------------

Part 3: Runnable JAR Archive
-------------------------------------------------------------------------------
J1.  Right click on the project in the 'Package Explorer' and select 'export'
J2.  Select 'Java|Runnable JAR File'
J3.  For Export Destination: '[Path to Export the JAR File]/[JAR File].jar'
J4.  Select 'Package required libraries into generated JAR'
J5.  'Finish'
J6.  Right click on the project in the 'Package Explorer' and select 'export'
J7.  Select 'General|File System'
J8.  Unselect all directories on the left panel
J9.  Select all the dll files only via the right panel
J10. For To directory: '[Path to Export the JAR File]'
J11. Select 'Create only selected directories'
J12. 'Finish'
J13. Right click on the project in the 'Package Explorer' and select 'export'
J14. Select 'General|File System'
J15. Unselect all directories on the left panel
J16. Select the '[Project]/src/Wayang/Resources' folder only
J17. For To directory: '[Path to Export the JAR File]/Wayang'
J18. Select 'Create only selected directories'
J19. 'Finish'
-------------------------------------------------------------------------------
END