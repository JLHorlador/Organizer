import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class GraphicOrganizer {
	private JFrame organizerFrame;	//the main organizer window
	private JPanel topPanel;		//the panel that will contain the save button
	private JPanel buttonPanel;		//the panel that will contain the class notes, activities, and schedule buttons
	private JPanel flexiblePanel;	//the panel that will change according to what button is pressed
	private static JPanel actionPanel;		//the panel that contains the action button and back button
	private static JPanel infoPanel;		//the panel that contains the inputs 
	private static JScrollPane infoPanelScroll;
	
	private JButton saveButton;			//the save button
	private JButton classButton;		//the class notes button
	private JButton activitiesButton;	//the activities button
	private JButton scheduleButton;		//the schedule button
	public static JButton actionButton;		//the button on top of the flexible panel 
	public static JButton backButton;			//the button to return to the previous screen
	private JButton mondayButton;		//monday button for the schedule
	private JButton tuesdayButton;		//tuesday button for the schedule
	private JButton wednesdayButton;	//wednesday button for the schedule
	private JButton thursdayButton;		//thursday button for the schedule
	private JButton fridayButton;		//friday button for the schedule
	
	private JTextField saveField;			//the JTextField that will display whether the user has saved their progress
	
	private static JTextField noteDate;			//the label that displays where the note's date input boxes are
	public static JComboBox<String> noteDateMonth;		//the drop down box that will take the input for the note's month date
	public static JComboBox<String> noteDateDay;			//the drop down box that will take the input for the note's day date
	public static JComboBox<String> noteDateYear;		//the drop down box that will take the input for the note's year date
	public static JTextField noteTitle;			//the JTextField that will take the input for the note's title
	public static JTextArea noteInfo;				//the JTextField that will take the input for the note's information
	
	private static JTextField activityFrom;		//the label that displays where the activity's start date input boxes are
	public static JComboBox<String> activityFromMonth;	//the drop down box that will take input for the month the activity starts
	public static JComboBox<String> activityFromDay;		//the drop down box that will take input for the day the activity starts
	public static JComboBox<String> activityFromYear;	//the drop down box that will take input for the year the activity starts
	
	private static JTextField activityTo;			//the label that displays where the activity's end date input boxes are
	public static JComboBox<String> activityToMonth;		//the drop down box that will take the input for the month the activity ends
	public static JComboBox<String> activityToDay;		//the drop down box that will take the input for the day the activity ends
	public static JComboBox<String> activityToYear;		//the drop down box that will take the input for the year the activity ends
	
	private static JTextField activityStart;				//the label that displays where the activity's beginning time input boxes are
	public static JComboBox<String> activityStartHour;			//the drop down box that will take the input for the hour the activity starts
	public static JComboBox<String> activityStartMinute;			//the drop down box that will take the input for the minute the activity starts
	public static JComboBox<String> activityStartTOD;		//the drop down box that will take the input for the time of day the activity starts
	
	private static JTextField activityEnd;					//the label that displays where the activity's end time input boxes are
	public static JComboBox<String> activityEndHour;				//the drop down box that will take the input for the hour the activity ends
	public static JComboBox<String> activityEndMinute;			//the drop down box that will take the input for the minute the activity ends
	public static JComboBox<String> activityEndTOD;		//the drop down box that will take the input for the time of day the activity ends
	
	public static JTextField activityTitle;		//the JTextField that will take the input for the activity's title
	public static JTextArea activityInfo;			//the JTextField that will take the input for the activity's info
	
	private JTextField classSchedule;		//the JTextField to indicate that the screen shows the class days
	
	public static JTextField classStart;				//the label that indicates where the class's beginning time input boxes are
	public static JComboBox<String> classStartHour;			//the drop down box that will take the input for the hour the class starts
	public static JComboBox<String> classStartMinute;		//the drop down box that will take the input for the minute the class starts
	public static JComboBox<String> classStartTOD;	//the drop down box to choose what time of day the class takes place
	
	public static JTextField classEnd;				//the label that indicates where the class's end time input boxes are
	public static JComboBox<String> classEndHour;			//the drop down box that will take the input for the hour the class ends
	public static JComboBox<String> classEndMinute;			//the drop down box that will take the input for the minute the class ends
	public static JComboBox<String> classEndTOD;		//the drop down box to choose what time of day the class takes place
	
	public static JTextField classLocation;		//the JTextField that takes the input for the class location
	public static JTextField classTitle;			//the JTextField that takes the input for the name of the class
	
	private JLabel classDay;				//JLabel that displays the chosen day to display classes

	private static String classDayText = "";		//contains the word of the chosen day in the schedule
	
	String[] timeOfDay = {"AM", "PM"};		//time of day options
	String[] months = {"MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};		//months options
	String[] days = {"DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",		//days options
			"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
			"27", "28", "29", "30", "31"};
	String[] years = {"YYYY", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};		//years options
	String[] hours = {"HH", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};		//hours options
	String[] minutes = {"MM", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", 		//minutes options
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
			"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
			"40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
			"50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
	
	ArrayList<NewNoteObject> noteObject = new ArrayList<NewNoteObject>();		//list of created notes
	ArrayList<NewActivityObject> activityObject = new ArrayList<NewActivityObject>();		//list of created activities
	ArrayList<NewClassScheduleObject> mondayClasses = new ArrayList<NewClassScheduleObject>();		//list of monday classes
	ArrayList<NewClassScheduleObject> tuesdayClasses = new ArrayList<NewClassScheduleObject>();		//list of tuesday classes
	ArrayList<NewClassScheduleObject> wednesdayClasses = new ArrayList<NewClassScheduleObject>();		//list of wednesday classes
	ArrayList<NewClassScheduleObject> thursdayClasses = new ArrayList<NewClassScheduleObject>();		//list of thursday classes
	ArrayList<NewClassScheduleObject> fridayClasses = new ArrayList<NewClassScheduleObject>();		//list of friday classes
	
	public static int index = 0;		//used to keep track of the location of a created note/activity/class 
	
	GridBagConstraints GBLayout = new GridBagConstraints();	//create the GridBagConstraints
	
	static Stack<Integer> screens;		//keeps track of what screens the user had visited before
	
	File noteFile;
	File activityFile;
	File mondayFile;
	File tuesdayFile;
	File wednesdayFile;
	File thursdayFile;
	File fridayFile;
	
	//variables used for password input
	private boolean isRightUser;
	private String passwordInput;
	private String passwordMessage;
	private int messageType;
	
	public GraphicOrganizer() throws Exception
	{	
		isRightUser = false;
		passwordMessage = "Input Password:";
		messageType = JOptionPane.WARNING_MESSAGE;
		
		noteFile = new File("NoteList.txt");
		activityFile = new File("ActivityList.txt");
		mondayFile = new File("MondayClasses.txt");
		tuesdayFile = new File("TuesdayClasses.txt");
		wednesdayFile = new File("WednesdayClasses.txt");
		thursdayFile = new File("ThursdayClasses.txt");
		fridayFile = new File("FridayClasses.txt");
		
		while(!isRightUser)
		{
			passwordInput = JOptionPane.showInputDialog(null, passwordMessage, "Password Input", messageType);		//pops up input dialog box
			if (passwordInput == null)		//if the user presses the cancel button or closes hte password input box, shut down the program
				System.exit(1);
			else if (passwordInput.equals("college2016"))		//if the input is the same as the password to get in, then start the program
				isRightUser = true;		//breaks through the loop to continue running the program
			else		//if input is incorrect, prompt the user to enter the correct password again
			{
				passwordMessage = "Invalid Password!\nInput Password:";
				messageType = JOptionPane.ERROR_MESSAGE;
			}
		}
		
		loadOrganizer();		//load any saved changes made in the previous session
		
		screens = new Stack<Integer>();		//keeps track of the previous screen 
		
		organizerFrame = new JFrame("Graphic Organizer");	//create a new window titled "Graphic Organizer"
		organizerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//create the panels that will be in the window
		//create the top panel to hold save feature
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		
		//create the panel to hold the three buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
		
		//create the flexible panel
		flexiblePanel = new JPanel();
		flexiblePanel.setLayout(new BorderLayout());
		//flexiblePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//create the action panel
		actionPanel = new JPanel();
		actionPanel.setLayout(new GridBagLayout());
		actionPanel.setBorder(BorderFactory.createMatteBorder(1,  0,  0, 0, Color.BLACK));
		flexiblePanel.add(actionPanel, BorderLayout.NORTH);
		
		//create the info panel that will contain the text fields to make a new activity/note
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridBagLayout());
		//infoPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));		//display border limits
		
		//implementation of scrolling if the number of notes, activities, or classes in the info panel exceeds the size of the window
		infoPanelScroll = new JScrollPane(infoPanel);
		infoPanel.setAutoscrolls(true);
		infoPanelScroll.setPreferredSize(new Dimension(800, 300));
		
		flexiblePanel.add(infoPanelScroll, BorderLayout.CENTER);		//add the scrolling implementation
		
		//create the save button
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//gives the save button the ability to save
				try
				{
					saveProgress();		//calls the method to save changes
				}
				catch (Exception except)
				{
					except.printStackTrace();
				}
			}
		});
		topPanel.add(saveButton);
		
		//create the "progress has been saved" textfield
		saveField = new JTextField();
		saveField.setEnabled(false);	//disables input
		saveField.setHorizontalAlignment(JTextField.RIGHT);		//displays message to the right of the field
		saveField.setDisabledTextColor(Color.BLACK);
		saveField.setOpaque(false);		//removes white background
		topPanel.add(saveField);
		
		//add back button
		backButton = new JButton("<-");
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//shows the previous screen if the player presses this button
				returnPreviousScreen();
			}
		});
		backButton.setVisible(false);		//hide the button initially
		
		//add action button
		actionButton = new JButton();		//the button that allows the user to add or remove notes, activities, or classes
		actionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//the if statements nested in these if statements prevents the program from displaying the same screen twice if trying to go back
				if (actionButton.getText().equals("Add Note"))
				{
					if (screens.peek() != 3)	//checks if the previous screen is not the same as the current screen
					{
						screens.push(3);		//3 indicates that the previous screen displays the class notes
						clearPanel();
						newNoteDetails();
					}
				}
				else if (actionButton.getText().equals("Add Activity"))
				{
					if (screens.peek() != 4)
					{
						screens.push(4);		//4 indicates that the previous screen displays the activities list
						clearPanel();
						newActivityDetails();
					}
				}
				else if (actionButton.getText().equals("New Class"))
				{
					if (screens.peek() != 13)
					{
						screens.push(13);		//13 indicates that the previous screen displays the list of classes
						clearPanel();
						newClassDetails();
					}
				}
				else if (actionButton.getText().equals("Add New Note"))
				{
					createNewNoteObject();		//calls the method to create a new note
					returnPreviousScreen();		//returns to the note list screen (to show that a new note has been created)
				}
				else if (actionButton.getText().equals("Add New Activity"))
				{
					createNewActivityObject();		//calls the method to create a new activity
					returnPreviousScreen();		//returns to the activity list screen (to show that a new activity has been created)
				}
				else if (actionButton.getText().equals("Add " + classDayText + " Class"))
				{
					createNewClassObject();		//calls the method to create a new class
					returnPreviousScreen();		//returns to the class list screen (to show that a new class has been created)
				}
				else if (actionButton.getText().equals("Remove Note"))
				{
					removeNote();		//removes the currently selected note
					clearStack();		//clears the screens stack
					screens.push(1);		//1 indicates that the screen will display class notes
					clearPanel();		//clears the panel
					showClassNotes();		//display class notes
					saveField.setText("");		//clears the save field text to show that a change has been made
				}
				else if (actionButton.getText().equals("Remove Activity"))
				{
					removeActivity();		//removes the currently selected activity
					clearStack();		//clears the screens stack
					screens.push(2);		//2 indicates that the screen displays activities
					clearPanel();		//clears the panel
					showActivities();		//show activities
					saveField.setText("");		//clears the save field to show that a change has been made
				}
				else if (actionButton.getText().equals("Remove Class"))
				{
					removeClass();		//removes the currently selected class
					returnPreviousScreen();		//returns to the previous screen
					saveField.setText("");		//clears the save field to show that a change has been made
				}
			}
		});
		actionButton.setVisible(false);
		
		//create class note info boxes
		noteDate = new JTextField("Date: ");
		noteDate.setOpaque(false);						//removes white background
		noteDate.setDisabledTextColor(Color.BLACK);		//sets text color to black
		noteDate.setBorder(null);						//removes imprinted border
		noteDate.setEnabled(false);						//disables text field
		
		noteDateMonth = new JComboBox<String>(months);						//displays two large M's
		
		noteDateDay = new JComboBox<String>(days);		//displays two large D's
		
		noteDateYear = new JComboBox<String>(years);						//displays four large Y's
		
		noteTitle = new JTextField("Enter title of note here");				//displays where to enter the title
		noteTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));	//sets a black border around the field
		noteTitle.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//clear the note title text box if no change has been made
				if (noteTitle.isEnabled() && noteTitle.getText().equals("Enter title of note here"))
					noteTitle.setText("");
			}
		});
		
		noteInfo = new JTextArea("Enter note info here");					//indicates where to add note info
		noteInfo.setRows(10);												//sets the box to show 10 rows
		noteInfo.setSize(0, 200);								
		noteInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));	//sets a black border around the field
		noteInfo.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//clear the note info text box if no change has been made
				if (noteInfo.isEnabled() && noteInfo.getText().equals("Enter note info here"))
					noteInfo.setText("");
			}
		});
		
		//add class notes button
		GBLayout = new GridBagConstraints();
		classButton = new JButton("Class Notes");
		classButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (screens.isEmpty() || screens.peek() != 1)	//checks if the previous screen does not display the class notes
				{
					clearStack();		//clears the screens stack
					screens.push(1);		//1 indicates that the screen will display class notes
					clearPanel();		//clears the panel
					showClassNotes();		//display class notes
				}
			}
		});
		GBLayout.ipady = 40;	//makes buttons thicker
		GBLayout.fill = GridBagConstraints.HORIZONTAL;	//makes buttons equal in width
		GBLayout.gridx = 0;		//sets x-coordinate to 0
		GBLayout.gridy = 0;		//sets y-coordinate to 0
		GBLayout.insets = new Insets(0, 10, 40, 10);
		buttonPanel.add(classButton, GBLayout);
		
		activityFrom = new JTextField("From: ");
		activityFrom.setOpaque(false);						//removes white background
		activityFrom.setDisabledTextColor(Color.BLACK);		//sets text color to black, since it will be disabled
		activityFrom.setBorder(null);						//removes border
		activityFrom.setEnabled(false);						//disables field
		
		activityFromMonth = new JComboBox<String>(months);						//displays two M's
		
		activityFromDay = new JComboBox<String>(days);							//displays two D's
		
		activityFromYear = new JComboBox<String>(years);						//displays four Y's
		
		activityTo = new JTextField("To: ");
		activityTo.setOpaque(false);		//removes white box background
		activityTo.setDisabledTextColor(Color.BLACK);		//sets the text color to black if the box is disabled
		activityTo.setBorder(null);		//removes the border
		activityTo.setEnabled(false);		//initially disable it
		
		activityToMonth = new JComboBox<String>(months);
		
		activityToDay = new JComboBox<String>(days);
		
		activityToYear = new JComboBox<String>(years);
		
		activityStart = new JTextField("Start: ");
		activityStart.setOpaque(false);		//removes white box background
		activityStart.setDisabledTextColor(Color.BLACK);		//sets the text color to black if the box is disabled
		activityStart.setBorder(null);		//removes the border
		activityStart.setEnabled(false);		//initially disable it
		
		activityStartHour = new JComboBox<String>(hours);
		
		activityStartMinute = new JComboBox<String>(minutes);
		
		activityStartTOD = new JComboBox<String>(timeOfDay);		//time of day drop box for activity start
		
		activityEnd = new JTextField("End: ");
		activityEnd.setOpaque(false);		//removes white box background
		activityEnd.setDisabledTextColor(Color.BLACK);		//sets the text color to black if the box is disabled
		activityEnd.setBorder(null);		//removes the border
		activityEnd.setEnabled(false);		//initially disable it
		
		activityEndHour = new JComboBox<String>(hours);
		
		activityEndMinute = new JComboBox<String>(minutes);
		
		activityEndTOD = new JComboBox<String>(timeOfDay);			//time of day drop box for activity end
		
		activityTitle = new JTextField("Enter title of activity here");
		activityTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		activityTitle.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//clear the activity title text box if no change has been made
				if (activityTitle.isEnabled() && activityTitle.getText().equals("Enter title of activity here"))
					activityTitle.setText("");
			}
		});
		
		activityInfo = new JTextArea("Enter activity info here");
		activityInfo.setRows(10);
		activityInfo.setSize(0, 200);
		activityInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		activityInfo.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//clear the activity info text box if no change has been made
				if (activityInfo.isEnabled() && activityInfo.getText().equals("Enter activity info here"))
					activityInfo.setText("");
			}
		});
		
		//add activities button
		activitiesButton = new JButton("Activities");
		activitiesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (screens.isEmpty() || screens.peek() != 2)	//checks if the previous screen does not display the activities 
				{
					clearStack();		//clears the screen stack
					screens.push(2);		//2 indicates that the screen displays activities
					clearPanel();		//clears the panel
					showActivities();		//show activities
				}
			}
		});
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		GBLayout.insets = new Insets(20, 10, 20, 10);
		buttonPanel.add(activitiesButton, GBLayout);
		
		classDay = new JLabel();
		
		mondayButton = new JButton("Monday");
		mondayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);		//8 indicates that the screen displays classes
				classDayText = "Monday";		//indicates that the chosen day is monday
				clearPanel();		//clears the panel
				showClasses();		//shows monday classes
			}
		});
		mondayButton.setPreferredSize(new Dimension(150, 60));
		
		tuesdayButton = new JButton("Tuesday");
		tuesdayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);		//8 indicates that the screen displays classes
				classDayText = "Tuesday";		//indicates that the chosen day is tuesday
				clearPanel();		//clears the panel
				showClasses();		//shows tuesday classes
			}
		});
		tuesdayButton.setPreferredSize(new Dimension(150, 60));
		
		wednesdayButton = new JButton("Wednesday");
		wednesdayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);		//8 indicates that the screen displays classes
				classDayText = "Wednesday";		//indicates that the chosen day is wednesday
				clearPanel();		//clears the panel
				showClasses();		//shows wednesday classes
			}
		});
		wednesdayButton.setPreferredSize(new Dimension(150, 60));
		
		thursdayButton = new JButton("Thursday");
		thursdayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);		//8 indicates that the screen displays classes
				classDayText = "Thursday";		//indicates that the chosen day is thursday
				clearPanel();		//clears the panel
				showClasses();		//shows thursday classes
			}
		});
		thursdayButton.setPreferredSize(new Dimension(150, 60));
		
		fridayButton = new JButton("Friday");
		fridayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);		//8 indicates that te screen displays classes
				classDayText = "Friday";		//indicates that the chosen day is friday
				clearPanel();		//clears the panel
				showClasses();		//shows friday classes
			}
		});
		fridayButton.setPreferredSize(new Dimension(150, 60));
		
		classStart = new JTextField("Start: ");
		classStart.setOpaque(false);		//removes white box background
		classStart.setDisabledTextColor(Color.BLACK);		//sets text color to black if the box is disabled
		classStart.setBorder(null);		//removes border
		classStart.setEnabled(false);		//initially disable the text box
		
		classStartHour = new JComboBox<String>(hours);
		
		classStartMinute = new JComboBox<String>(minutes);
		
		classStartTOD = new JComboBox<String>(timeOfDay);
		
		classEnd = new JTextField("End: ");
		classEnd.setOpaque(false);		//removes white box background
		classEnd.setDisabledTextColor(Color.BLACK);		//sets text color to black if the box is disabled
		classEnd.setBorder(null);		//removes borders
		classEnd.setEnabled(false);		//initially disables the text box
		
		classEndHour = new JComboBox<String>(hours);
		
		classEndMinute = new JComboBox<String>(minutes);
		
		classEndTOD = new JComboBox<String>(timeOfDay);
		
		classLocation = new JTextField("Enter class location here");
		classLocation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		classLocation.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//clears the class location text box if no changes were made
				if (classLocation.isEnabled() && classLocation.getText().equals("Enter class location here"))
					classLocation.setText("");
			}
		});
		classLocation.setDisabledTextColor(Color.BLACK);
		
		classTitle = new JTextField("Enter class name here");
		classTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		classTitle.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//clears the class title text box if no changes were made
				if (classTitle.isEnabled() && classTitle.getText().equals("Enter class name here"))
					classTitle.setText("");
			}
		});
		classTitle.setDisabledTextColor(Color.BLACK);
		
		classSchedule = new JTextField("Class Schedule");
		classSchedule.setOpaque(false);		//removes white box background
		classSchedule.setDisabledTextColor(Color.BLACK);		//sets text color to black if the text box is disabled
		classSchedule.setBorder(null);		//removes borders
		classSchedule.setEnabled(false);		//initially disable the text box
		
		//add schedule button
		scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (screens.isEmpty() || screens.peek() != 5)
				{
					clearStack();		//clears the screen stack
					screens.push(5);		//5 indicates that the current screen shows class days
					clearPanel();		//clears the panel
					showClassDays();		//shows the class days
				}
			}
		});
		GBLayout.gridx = 0;
		GBLayout.gridy = 2;
		GBLayout.insets = new Insets(40, 10, 0, 10);
		buttonPanel.add(scheduleButton, GBLayout);
		
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.insets = new Insets(0, 0, 0, 0);
		GBLayout.fill = 0;
		GBLayout.ipady = 0;
		
		organizerFrame.add(topPanel, BorderLayout.PAGE_START);		//adds the top panel at the very top
		organizerFrame.add(buttonPanel, BorderLayout.LINE_START);		//adds the button panel to the left
		organizerFrame.add(flexiblePanel, BorderLayout.CENTER);		//centers the flexible panel
		organizerFrame.pack();
		organizerFrame.setVisible(true);		//shows the window
		organizerFrame.setMinimumSize(new Dimension(800, 450));
	}
	
	private void showClassNotes()		//shows class notes
	{
		GBLayout = new GridBagConstraints();
		
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 2;
		actionPanel.add(actionButton, GBLayout);
		
		actionButton.setText("Add Note");
		backButton.setVisible(true);
		actionButton.setVisible(true);
		
		for (int i = 0; i < noteObject.size(); i++)		//show created notes in order of creation
		{
			GBLayout.gridy = i;
			noteObject.get(i).setIndex(i);
			GBLayout.anchor = GridBagConstraints.WEST;
			GBLayout.insets = new Insets(10, 50, 10, 0);
			infoPanel.add(noteObject.get(i).createLabel(), GBLayout);
			GBLayout.anchor = GridBagConstraints.EAST;
			GBLayout.insets = new Insets(10, 0, 10, 50);
			infoPanel.add(noteObject.get(i).createButton(), GBLayout);
		}
		
		infoPanel.revalidate();
		infoPanel.repaint();
		
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	private void showActivities()		//shows activities
	{
		GBLayout = new GridBagConstraints();
		
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 2;
		actionPanel.add(actionButton, GBLayout);
		
		actionButton.setText("Add Activity");
		backButton.setVisible(true);
		actionButton.setVisible(true);
		
		for (int i = 0; i < activityObject.size(); i++)		//show created activities in order of creation
		{
			GBLayout.gridy = i;
			activityObject.get(i).setIndex(i);
			GBLayout.anchor = GridBagConstraints.WEST;
			GBLayout.insets = new Insets(10, 30, 10, 0);
			infoPanel.add(activityObject.get(i).createLabel(), GBLayout);
			GBLayout.anchor = GridBagConstraints.EAST;
			GBLayout.insets = new Insets(10, 0, 10, 30);
			infoPanel.add(activityObject.get(i).createButton(), GBLayout);
		}
		
		infoPanel.revalidate();
		infoPanel.repaint();
		
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	private void showClassDays()
	{
		GBLayout = new GridBagConstraints();
		
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 2;
		actionPanel.add(classSchedule, GBLayout);
		
		backButton.setVisible(true);
		
		GBLayout.weightx = 0;
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weighty = 1;
		GBLayout.insets = new Insets(0, 0, 0, 30);
		infoPanel.add(mondayButton, GBLayout);
		
		GBLayout.gridx = 1;
		GBLayout.insets = new Insets(0, 30, 0, 0);
		infoPanel.add(tuesdayButton, GBLayout);
		
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		GBLayout.insets = new Insets(0, 0, 0, 30);
		infoPanel.add(wednesdayButton, GBLayout);
		
		GBLayout.gridx = 1;
		GBLayout.insets = new Insets(0, 30, 0, 0);
		infoPanel.add(thursdayButton, GBLayout);
		
		GBLayout.gridx = 0;
		GBLayout.gridy = 2;
		GBLayout.insets = new Insets(0, 0, 0, 0);
		GBLayout.gridwidth = 2;
		infoPanel.add(fridayButton, GBLayout);
		
		infoPanel.revalidate();
		infoPanel.repaint();
		
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	private void showClasses()		//method to show classes in a selected day
	{
		GridBagConstraints GBLayout = new GridBagConstraints();
		
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1;
		backButton.setVisible(true);
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 2;
		actionPanel.add(classDay, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.EAST;
		actionButton.setText("New Class");
		actionButton.setVisible(true);
		actionPanel.add(actionButton, GBLayout);
		
		//show classes of a specific day in order of creation
		if (classDayText.equals("Monday"))
		{
			classDay.setText("Monday");
			if (!mondayClasses.isEmpty())
			{
				for (int i = 0; i < mondayClasses.size(); i++)
				{
					GBLayout.gridy = i;
					mondayClasses.get(i).setIndex(i);
					GBLayout.anchor = GridBagConstraints.WEST;
					GBLayout.insets = new Insets(10, 40, 10, 0);
					infoPanel.add(mondayClasses.get(i).createLabel(), GBLayout);
					GBLayout.anchor = GridBagConstraints.EAST;
					GBLayout.insets = new Insets(10, 0, 10, 30);
					infoPanel.add(mondayClasses.get(i).createButton(), GBLayout);
				}
			}
		}
		else if (classDayText.equals("Tuesday"))
		{
			classDay.setText("Tuesday");
			if (!tuesdayClasses.isEmpty())
			{
				for (int i = 0; i < tuesdayClasses.size(); i++)
				{
					GBLayout.gridy = i;
					tuesdayClasses.get(i).setIndex(i);
					GBLayout.anchor = GridBagConstraints.WEST;
					GBLayout.insets = new Insets(10, 40, 10, 0);
					infoPanel.add(tuesdayClasses.get(i).createLabel(), GBLayout);
					GBLayout.anchor = GridBagConstraints.EAST;
					GBLayout.insets = new Insets(10, 0, 10, 40);
					infoPanel.add(tuesdayClasses.get(i).createButton(), GBLayout);
				}
			}
		}
		else if (classDayText.equals("Wednesday"))
		{
			classDay.setText("Wednesday");
			if (!wednesdayClasses.isEmpty())
			{
				for (int i = 0; i < wednesdayClasses.size(); i++)
				{
					GBLayout.gridy = i;
					wednesdayClasses.get(i).setIndex(i);
					GBLayout.anchor = GridBagConstraints.WEST;
					GBLayout.insets = new Insets(10, 40, 10, 0);
					infoPanel.add(wednesdayClasses.get(i).createLabel(), GBLayout);
					GBLayout.anchor = GridBagConstraints.EAST;
					GBLayout.insets = new Insets(10, 0, 10, 40);
					infoPanel.add(wednesdayClasses.get(i).createButton(), GBLayout);
				}
			}
		}
		else if (classDayText.equals("Thursday"))
		{
			classDay.setText("Thursday");
			if (!thursdayClasses.isEmpty())
			{
				for (int i = 0; i < thursdayClasses.size(); i++)
				{
					GBLayout.gridy = i;
					thursdayClasses.get(i).setIndex(i);
					GBLayout.anchor = GridBagConstraints.WEST;
					GBLayout.insets = new Insets(10, 40, 10, 0);
					infoPanel.add(thursdayClasses.get(i).createLabel(), GBLayout);
					GBLayout.anchor = GridBagConstraints.EAST;
					GBLayout.insets = new Insets(10, 0, 10, 40);
					infoPanel.add(thursdayClasses.get(i).createButton(), GBLayout);
				}
			}
		}
		else if (classDayText.equals("Friday"))
		{
			classDay.setText("Friday");
			if (!fridayClasses.isEmpty())
			{
				for (int i = 0; i < fridayClasses.size(); i++)
				{
					GBLayout.gridy = i;
					fridayClasses.get(i).setIndex(i);
					GBLayout.anchor = GridBagConstraints.WEST;
					GBLayout.insets = new Insets(10, 40, 10, 0);
					infoPanel.add(fridayClasses.get(i).createLabel(), GBLayout);
					GBLayout.anchor = GridBagConstraints.EAST;
					GBLayout.insets = new Insets(10, 0, 10, 40);
					infoPanel.add(fridayClasses.get(i).createButton(), GBLayout);
				}
			}
		}
		
		infoPanel.revalidate();
		infoPanel.repaint();
		
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	public static void newNoteDetails()	//method to display boxes necessary to create new class note
	{
		//reset constraints
		GridBagConstraints GBLayout = new GridBagConstraints();
		
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 2;
		actionButton.setText("Add New Note");
		actionPanel.add(actionButton, GBLayout);
		
		//noteDate will have the origin
		GBLayout.gridx = 0;	
		GBLayout.gridy = 0;	
		GBLayout.weightx = 1;
		infoPanel.add(noteDate, GBLayout);
		
		GBLayout.gridx = 1;		//place to the right of the noteDate
		//noteDateMonth.setText("MM");	//reset textfield
		noteDateMonth.setSelectedIndex(0);
		noteDateMonth.setEnabled(true);
		infoPanel.add(noteDateMonth, GBLayout);
		
		GBLayout.gridx = 2;		//place two cells right of the noteDate
		noteDateDay.setSelectedIndex(0);
		noteDateDay.setEnabled(true);
		infoPanel.add(noteDateDay, GBLayout);
		
		GBLayout.gridx = 3;		//place three cells to the right of the noteDate
		noteDateYear.setSelectedIndex(0);
		noteDateYear.setEnabled(true);
		infoPanel.add(noteDateYear, GBLayout);
		
		GBLayout.gridwidth = 4;		//expand to 4 cells
		GBLayout.gridx = 0;		//set coordinate 1 cell below noteDate
		GBLayout.gridy = 1;
		GBLayout.weighty = 0;
		GBLayout.fill = GridBagConstraints.HORIZONTAL;
		GBLayout.insets = new Insets(10, 10, 10, 10);
		noteTitle.setText("Enter title of note here");
		noteTitle.setEnabled(true);
		infoPanel.add(noteTitle, GBLayout);
		
		GBLayout.gridheight = 2;
		GBLayout.gridx = 0;
		GBLayout.gridy = 2;
		GBLayout.weighty = 0;
		GBLayout.insets = new Insets(10, 10, 0, 10);
		noteInfo.setText("Enter note info here");
		noteInfo.setEnabled(true);
		infoPanel.add(noteInfo, GBLayout);
		
		infoPanel.revalidate();
		infoPanel.repaint();		//refresh the panel
	}
	
	public static void newActivityDetails()		//method to add new activity
	{
		//reset constraints
		GridBagConstraints GBLayout = new GridBagConstraints();
		
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 2;
		actionPanel.add(actionButton, GBLayout);
		
		actionButton.setText("Add New Activity");
		backButton.setVisible(true);
		actionButton.setVisible(true);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		
		//activity start date will have origin
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 1;
		infoPanel.add(activityFrom, GBLayout);
		
		GBLayout.gridx = 1;
		activityFromMonth.setSelectedIndex(0);
		activityFromMonth.setEnabled(true);
		infoPanel.add(activityFromMonth, GBLayout);
		
		GBLayout.gridx = 2;
		activityFromDay.setSelectedIndex(0);
		activityFromDay.setEnabled(true);
		infoPanel.add(activityFromDay, GBLayout);
		
		GBLayout.gridx = 3;
		activityFromYear.setSelectedIndex(0);
		activityFromYear.setEnabled(true);
		infoPanel.add(activityFromYear, GBLayout);
		
		//add activity end date
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		GBLayout.weighty = 0;
		infoPanel.add(activityTo, GBLayout);
		
		GBLayout.gridx = 1;
		activityToMonth.setSelectedIndex(0);
		activityToMonth.setEnabled(true);
		infoPanel.add(activityToMonth, GBLayout);
		
		GBLayout.gridx = 2;
		activityToDay.setSelectedIndex(0);
		activityToDay.setEnabled(true);
		infoPanel.add(activityToDay, GBLayout);
		
		GBLayout.gridx = 3;
		activityToYear.setSelectedIndex(0);
		activityToYear.setEnabled(true);
		infoPanel.add(activityToYear, GBLayout);
		
		//add activity time start
		GBLayout.gridx = 0;
		GBLayout.gridy = 2;
		infoPanel.add(activityStart, GBLayout);
		
		GBLayout.gridx = 1;
		activityStartHour.setSelectedIndex(0);
		activityStartHour.setEnabled(true);
		infoPanel.add(activityStartHour, GBLayout);
		
		GBLayout.gridx = 2;
		activityStartMinute.setSelectedIndex(0);
		activityStartMinute.setEnabled(true);
		infoPanel.add(activityStartMinute, GBLayout);
		
		GBLayout.gridx = 3;
		activityStartTOD.setSelectedIndex(0);
		activityStartTOD.setEnabled(true);
		infoPanel.add(activityStartTOD, GBLayout);
		
		//add activity time end
		GBLayout.gridx = 0;
		GBLayout.gridy = 3;
		infoPanel.add(activityEnd, GBLayout);
		
		GBLayout.gridx = 1;
		activityEndHour.setSelectedIndex(0);
		activityEndHour.setEnabled(true);
		infoPanel.add(activityEndHour, GBLayout);
		
		GBLayout.gridx = 2;
		activityEndMinute.setSelectedIndex(0);
		activityEndMinute.setEnabled(true);
		infoPanel.add(activityEndMinute, GBLayout);
		
		GBLayout.gridx = 3;
		activityEndTOD.setSelectedIndex(0);
		activityEndTOD.setEnabled(true);
		infoPanel.add(activityEndTOD, GBLayout);
		
		GBLayout.gridwidth = 4;
		GBLayout.gridx = 0;
		GBLayout.gridy = 4;
		GBLayout.fill = GridBagConstraints.HORIZONTAL;
		GBLayout.insets = new Insets(10, 10, 10, 10);
		activityTitle.setText("Enter title of activity here");
		activityTitle.setEnabled(true);
		infoPanel.add(activityTitle, GBLayout);
		
		GBLayout.gridy = 5;
		GBLayout.insets = new Insets(10, 10, 0, 10);
		activityInfo.setText("Enter activity info here");
		activityInfo.setEnabled(true);
		infoPanel.add(activityInfo, GBLayout);
		
		infoPanel.revalidate();
		infoPanel.repaint();
	}
	
	public static void newClassDetails()
	{
		GridBagConstraints GBLayout = new GridBagConstraints();
		
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.weightx = 2;
		actionPanel.add(actionButton, GBLayout);
		
		actionButton.setText("Add " + classDayText + " Class");
		backButton.setVisible(true);
		actionButton.setVisible(true);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		
		GBLayout.weightx = 1;
		infoPanel.add(classStart, GBLayout);
		
		GBLayout.gridx = 1;
		classStartHour.setSelectedIndex(0);
		classStartHour.setEnabled(true);
		infoPanel.add(classStartHour, GBLayout);
		
		GBLayout.gridx = 2;
		classStartMinute.setSelectedIndex(0);
		classStartMinute.setEnabled(true);
		infoPanel.add(classStartMinute, GBLayout);
		
		GBLayout.gridx = 3;
		classStartTOD.setSelectedIndex(0);
		classStartTOD.setEnabled(true);
		infoPanel.add(classStartTOD, GBLayout);
		
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		infoPanel.add(classEnd, GBLayout);
		
		GBLayout.gridx = 1;
		classEndHour.setSelectedIndex(0);
		classEndHour.setEnabled(true);
		infoPanel.add(classEndHour, GBLayout);
		
		GBLayout.gridx = 2;
		classEndMinute.setSelectedIndex(0);
		classEndMinute.setEnabled(true);
		infoPanel.add(classEndMinute, GBLayout);
		
		GBLayout.gridx = 3;
		classEndTOD.setSelectedIndex(0);
		classEndTOD.setEnabled(true);
		infoPanel.add(classEndTOD, GBLayout);
		
		GBLayout.gridx = 0;
		GBLayout.gridy = 2;
		GBLayout.gridwidth = 4;
		GBLayout.fill = GridBagConstraints.HORIZONTAL;
		GBLayout.insets = new Insets(10, 10, 10, 10);
		classLocation.setText("Enter class location here");
		classLocation.setEnabled(true);
		infoPanel.add(classLocation, GBLayout);
		
		GBLayout.gridy = 3;
		GBLayout.insets = new Insets(10, 10, 0, 10);
		classTitle.setText("Enter class name here");
		classTitle.setEnabled(true);
		infoPanel.add(classTitle, GBLayout);
		
		infoPanel.revalidate();
		infoPanel.repaint();
		
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	public void createNewNoteObject()		//create a new note
	{
		noteObject.add(new NewNoteObject(noteDateMonth.getSelectedItem().toString(), noteDateDay.getSelectedItem().toString(), 
				noteDateYear.getSelectedItem().toString(), noteTitle.getText(), noteInfo.getText()));
		
		saveField.setText("");
	}
	
	public void createNewNoteObject(String month, String day, String year, String title, String info)	//use for loading notes
	{
		noteObject.add(new NewNoteObject(month, day, year, title, info));
	}
	
	public void createNewActivityObject()		//create a new activity
	{
		activityObject.add(new NewActivityObject(activityFromMonth.getSelectedItem().toString(), activityFromDay.getSelectedItem().toString(), 
				activityFromYear.getSelectedItem().toString(), activityToMonth.getSelectedItem().toString(), activityToDay.getSelectedItem().toString(), 
				activityToYear.getSelectedItem().toString(), activityStartHour.getSelectedItem().toString(), activityStartMinute.getSelectedItem().toString(), 
				activityStartTOD.getSelectedItem(), activityEndHour.getSelectedItem().toString(), activityEndMinute.getSelectedItem().toString(),
				activityEndTOD.getSelectedItem(), activityTitle.getText(), activityInfo.getText()));
		
		saveField.setText("");
	}
	
	public void createNewActivityObject(String fromMonth, String fromDay, String fromYear, String toMonth, 
			String toDay, String toYear, String startHour, String startMinute, String startTOD, String endHour, 
			String endMinute, String endTOD, String title, String info)		//use for loading activities
	{
		activityObject.add(new NewActivityObject(fromMonth, fromDay, fromYear, toMonth, toDay, toYear, startHour, 
				startMinute, startTOD, endHour, endMinute, endTOD, title, info));
	}
	
	public void createNewClassObject()		//create a class on a specific day
	{
		if (classDayText.equals("Monday"))
			mondayClasses.add(new NewClassScheduleObject(classStartHour.getSelectedItem().toString(), classStartMinute.getSelectedItem().toString(),
					classStartTOD.getSelectedItem(), classEndHour.getSelectedItem().toString(), classEndMinute.getSelectedItem().toString(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Tuesday"))
			tuesdayClasses.add(new NewClassScheduleObject(classStartHour.getSelectedItem().toString(), classStartMinute.getSelectedItem().toString(),
					classStartTOD.getSelectedItem(), classEndHour.getSelectedItem().toString(), classEndMinute.getSelectedItem().toString(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Wednesday"))
			wednesdayClasses.add(new NewClassScheduleObject(classStartHour.getSelectedItem().toString(), classStartMinute.getSelectedItem().toString(),
					classStartTOD.getSelectedItem(), classEndHour.getSelectedItem().toString(), classEndMinute.getSelectedItem().toString(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Thursday"))
			thursdayClasses.add(new NewClassScheduleObject(classStartHour.getSelectedItem().toString(), classStartMinute.getSelectedItem().toString(),
					classStartTOD.getSelectedItem(), classEndHour.getSelectedItem().toString(), classEndMinute.getSelectedItem().toString(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Friday"))
			fridayClasses.add(new NewClassScheduleObject(classStartHour.getSelectedItem().toString(), classStartMinute.getSelectedItem().toString(),
					classStartTOD.getSelectedItem(), classEndHour.getSelectedItem().toString(), classEndMinute.getSelectedItem().toString(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		
		saveField.setText("");
		/*
		System.out.println("Monday: " + mondayClasses + "\n" +
					"Tuesday: " + tuesdayClasses + "\n" + 
					"Wednesday: " + wednesdayClasses + "\n" + 
					"Thursday: " + thursdayClasses + "\n" + 
					"Friday: " + fridayClasses + "\n");
		*/
	}
	
	private void createNewClassObject(String startHour, String startMinute, String startTOD, String endHour, 
			String endMinute, String endTOD, String location, String title)		//used for loading classes
	{

		if (classDayText.equals("Monday"))
			mondayClasses.add(new NewClassScheduleObject(startHour, startMinute, startTOD, endHour, endMinute, endTOD,
					location, title));
		else if (classDayText.equals("Tuesday"))
			tuesdayClasses.add(new NewClassScheduleObject(startHour, startMinute, startTOD, endHour, endMinute, endTOD,
					location, title));
		else if (classDayText.equals("Wednesday"))
			wednesdayClasses.add(new NewClassScheduleObject(startHour, startMinute, startTOD, endHour, endMinute, endTOD,
					location, title));
		else if (classDayText.equals("Thursday"))
			thursdayClasses.add(new NewClassScheduleObject(startHour, startMinute, startTOD, endHour, endMinute, endTOD,
					location, title));
		else if (classDayText.equals("Friday"))
			fridayClasses.add(new NewClassScheduleObject(startHour, startMinute, startTOD, endHour, endMinute, endTOD,
					location, title));
			
	}
	
	private void returnPreviousScreen()		//the method to return to the previous screen
	{
		//top of the stack is the current screen, so it is popped off first
		if (!screens.isEmpty())
			screens.pop();
		//System.out.println(screens);		//debugging purposes
		
		clearPanel();		//clears the panel
		
		if (screens.isEmpty())
		{
			//actionButton.setVisible(false);
			//backButton.setVisible(false);
			clearPanel();
		}
		else if (screens.peek() == 1)
		{
			showClassNotes();		//show notes
		}
		else if (screens.peek() == 2)
		{
			showActivities();		//show activities
		}
		else if (screens.peek() == 3)
		{
			newNoteDetails();		//shows the text fields of a selected note
		}
		else if (screens.peek() == 4)
		{
			newActivityDetails();		//shows the text fields of a selected activity
		}
		else if (screens.peek() == 5)
		{
			showClassDays();		//show the class days
		}
		else if (screens.peek() == 8)
		{
			showClasses();		//show the classes
		}
	}
	
	public static void clearPanel()		//clears infoPanel and actionPanel
	{
		infoPanel.removeAll();
		infoPanel.revalidate();
		infoPanel.repaint();

		actionPanel.removeAll();
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	private void clearStack()		//clears the screens stack
	{
		while (!screens.isEmpty())
			screens.pop();
	}
	
	private void removeNote()		//removes the selected note
	{
		noteObject.remove(index);
		index = 0;
	}
	
	private void removeActivity()		//removes the selected activity
	{
		activityObject.remove(index);
		index = 0;
	}
	
	private void removeClass()		//removes the selected class
	{
		if (classDayText.equals("Monday"))
			mondayClasses.remove(index);
		else if (classDayText.equals("Tuesday"))
			tuesdayClasses.remove(index);
		else if (classDayText.equals("Wednesday"))
			wednesdayClasses.remove(index);
		else if (classDayText.equals("Thursday"))
			thursdayClasses.remove(index);
		else if (classDayText.equals("Friday"))
			fridayClasses.remove(index);
		
		index = 0;
	}
	
	public void saveProgress() throws Exception		//saves progress
	{
		saveField.setText("Saving progress...");
		
		PrintWriter noteWriter = new PrintWriter(noteFile);		//creates a new writer to record notes, while it erases the data in NoteList.txt
		
		//record the notes created
		for (int i = 0; i < noteObject.size(); i++)
		{
			//write down the parts of the note, each part being separated by the word <Space>
			noteWriter.write(noteObject.get(i).getMonth());		//records month
			noteWriter.write("<Space>");
			noteWriter.write(noteObject.get(i).getDay());		//records the day
			noteWriter.write("<Space>");
			noteWriter.write(noteObject.get(i).getYear());		//records the year
			noteWriter.write("<Space>");
			noteWriter.write(noteObject.get(i).getTitle());		//records note title
			noteWriter.write("<Space>");
			noteWriter.write(noteObject.get(i).getInfo());		//records note info
			noteWriter.write("<Space>");
			noteWriter.println();
		}
		noteWriter.close();

		//record the activities created
		PrintWriter activityWriter = new PrintWriter(activityFile);		//creates a new writer to record activities, while it erases the data in ActivityList.txt
		
		for (int i = 0; i < activityObject.size(); i++)
		{
			activityWriter.write(activityObject.get(i).getFromMonth());		//records starting month
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getFromDay());		//records starting day
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getFromYear());		//records starting year
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getToMonth());		//records ending month
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getToDay());		//records ending day
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getToYear());		//records ending year
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getStartHour());		//records starting hour
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getStartMinute());		//records starting minute
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getStartTOD());		//records starting time of day
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getEndHour());		//records ending hour
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getEndMinute());		//records ending minute
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getEndTOD());		//records ending time of day
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getTitle());		//records title of activity
			activityWriter.write("<Space>");
			activityWriter.write(activityObject.get(i).getInfo());		//records activity info
			activityWriter.write("<Space>");
			activityWriter.println();
		}
		activityWriter.close();
		
		//record the classes for monday
		PrintWriter classWriter = new PrintWriter(mondayFile);
		
		for (int i = 0; i < mondayClasses.size(); i++)
		{
			classWriter.write(mondayClasses.get(i).getStartHour());
			classWriter.write("<Space>");
			classWriter.write(mondayClasses.get(i).getStartMinute());
			classWriter.write("<Space>");
			classWriter.write(mondayClasses.get(i).getStartTOD());
			classWriter.write("<Space>");
			classWriter.write(mondayClasses.get(i).getEndHour());
			classWriter.write("<Space>");
			classWriter.write(mondayClasses.get(i).getEndMinute());
			classWriter.write("<Space>");
			classWriter.write(mondayClasses.get(i).getEndTOD());
			classWriter.write("<Space>");
			classWriter.write(mondayClasses.get(i).getLocation());
			classWriter.write("<Space>");
			classWriter.write(mondayClasses.get(i).getTitle());
			classWriter.write("<Space>");
			classWriter.println();
		}
		classWriter.close();
		
		classWriter = new PrintWriter(tuesdayFile);
		
		for (int i = 0; i < tuesdayClasses.size(); i++)
		{
			classWriter.write(tuesdayClasses.get(i).getStartHour());
			classWriter.write("<Space>");
			classWriter.write(tuesdayClasses.get(i).getStartMinute());
			classWriter.write("<Space>");
			classWriter.write(tuesdayClasses.get(i).getStartTOD());
			classWriter.write("<Space>");
			classWriter.write(tuesdayClasses.get(i).getEndHour());
			classWriter.write("<Space>");
			classWriter.write(tuesdayClasses.get(i).getEndMinute());
			classWriter.write("<Space>");
			classWriter.write(tuesdayClasses.get(i).getEndTOD());
			classWriter.write("<Space>");
			classWriter.write(tuesdayClasses.get(i).getLocation());
			classWriter.write("<Space>");
			classWriter.write(tuesdayClasses.get(i).getTitle());
			classWriter.write("<Space>");
			classWriter.println();
		}
		classWriter.close();
		
		classWriter = new PrintWriter(wednesdayFile);
		
		for (int i = 0; i < wednesdayClasses.size(); i++)
		{
			classWriter.write(wednesdayClasses.get(i).getStartHour());
			classWriter.write("<Space>");
			classWriter.write(wednesdayClasses.get(i).getStartMinute());
			classWriter.write("<Space>");
			classWriter.write(wednesdayClasses.get(i).getStartTOD());
			classWriter.write("<Space>");
			classWriter.write(wednesdayClasses.get(i).getEndHour());
			classWriter.write("<Space>");
			classWriter.write(wednesdayClasses.get(i).getEndMinute());
			classWriter.write("<Space>");
			classWriter.write(wednesdayClasses.get(i).getEndTOD());
			classWriter.write("<Space>");
			classWriter.write(wednesdayClasses.get(i).getLocation());
			classWriter.write("<Space>");
			classWriter.write(wednesdayClasses.get(i).getTitle());
			classWriter.write("<Space>");
			classWriter.println();
		}
		classWriter.close();
		
		classWriter = new PrintWriter(thursdayFile);
		
		for (int i = 0; i < thursdayClasses.size(); i++)
		{
			classWriter.write(thursdayClasses.get(i).getStartHour());
			classWriter.write("<Space>");
			classWriter.write(thursdayClasses.get(i).getStartMinute());
			classWriter.write("<Space>");
			classWriter.write(thursdayClasses.get(i).getStartTOD());
			classWriter.write("<Space>");
			classWriter.write(thursdayClasses.get(i).getEndHour());
			classWriter.write("<Space>");
			classWriter.write(thursdayClasses.get(i).getEndMinute());
			classWriter.write("<Space>");
			classWriter.write(thursdayClasses.get(i).getEndTOD());
			classWriter.write("<Space>");
			classWriter.write(thursdayClasses.get(i).getLocation());
			classWriter.write("<Space>");
			classWriter.write(thursdayClasses.get(i).getTitle());
			classWriter.write("<Space>");
			classWriter.println();
		}
		classWriter.close();
		
		classWriter = new PrintWriter(fridayFile);
		
		for (int i = 0; i < fridayClasses.size(); i++)
		{
			classWriter.write(fridayClasses.get(i).getStartHour());
			classWriter.write("<Space>");
			classWriter.write(fridayClasses.get(i).getStartMinute());
			classWriter.write("<Space>");
			classWriter.write(fridayClasses.get(i).getStartTOD());
			classWriter.write("<Space>");
			classWriter.write(fridayClasses.get(i).getEndHour());
			classWriter.write("<Space>");
			classWriter.write(fridayClasses.get(i).getEndMinute());
			classWriter.write("<Space>");
			classWriter.write(fridayClasses.get(i).getEndTOD());
			classWriter.write("<Space>");
			classWriter.write(fridayClasses.get(i).getLocation());
			classWriter.write("<Space>");
			classWriter.write(fridayClasses.get(i).getTitle());
			classWriter.write("<Space>");
			classWriter.println();
		}
		classWriter.close();

		saveField.setText("Progress has been saved.");
		JOptionPane.showMessageDialog(null, "Progress has been saved.", null, JOptionPane.INFORMATION_MESSAGE);		//make a pop up window when saved
	}
	
	private void loadOrganizer() throws Exception
	{
		if (!noteFile.exists())
			noteFile.createNewFile();
		else
		{
			//read from the file
			BufferedReader noteReader = new BufferedReader(new FileReader(noteFile));
			
			String noteLine;
			String[] noteArray;
			while ((noteLine = noteReader.readLine()) != null)
			{
				noteArray = new String[0];
				noteArray = noteLine.split("<Space>");
				createNewNoteObject(noteArray[0], noteArray[1], noteArray[2], noteArray[3], noteArray[4]);
			}
			noteReader.close();
		}
		
		if (!activityFile.exists())
			activityFile.createNewFile();
		else
		{
			//read from the file
			BufferedReader activityReader = new BufferedReader(new FileReader(activityFile));
			
			String activityLine;
			String[] activityArray;
			while ((activityLine = activityReader.readLine()) != null)
			{
				activityArray = new String[0];
				activityArray = activityLine.split("<Space>");
				createNewActivityObject(activityArray[0], activityArray[1], activityArray[2], activityArray[3],
						activityArray[4], activityArray[5], activityArray[6], activityArray[7], activityArray[8], 
						activityArray[9], activityArray[10], activityArray[11], activityArray[12], activityArray[13]);
			}
			activityReader.close();
		}
		
		if (!mondayFile.exists())
			mondayFile.createNewFile();

		if (!tuesdayFile.exists())
			tuesdayFile.createNewFile();
		
		if (!wednesdayFile.exists())
			wednesdayFile.createNewFile();
		
		if (!thursdayFile.exists())
			thursdayFile.createNewFile();
		
		if (!fridayFile.exists())
			fridayFile.createNewFile();
		
		//read from the file
		BufferedReader classReader;
			
		String classLine;
		String[] classArray;
		
		classReader = new BufferedReader(new FileReader(mondayFile));
		
		classDayText = "Monday";
		while ((classLine = classReader.readLine()) != null)
		{
			classArray = new String[0];
			classArray = classLine.split("<Space>");
			createNewClassObject(classArray[0], classArray[1], classArray[2], classArray[3], 
					classArray[4], classArray[5], classArray[6], classArray[7]);
		}
		classReader.close();
		
		classReader = new BufferedReader(new FileReader(tuesdayFile));
		classDayText = "Tuesday";
		while ((classLine = classReader.readLine()) != null)
		{
			classArray = new String[0];
			classArray = classLine.split("<Space>");
			createNewClassObject(classArray[0], classArray[1], classArray[2], classArray[3], 
					classArray[4], classArray[5], classArray[6], classArray[7]);
		}
		classReader.close();
		
		classReader = new BufferedReader(new FileReader(wednesdayFile));
		classDayText = "Wednesday";
		while ((classLine = classReader.readLine()) != null)
		{
			classArray = new String[0];
			classArray = classLine.split("<Space>");
			createNewClassObject(classArray[0], classArray[1], classArray[2], classArray[3], 
					classArray[4], classArray[5], classArray[6], classArray[7]);
		}
		classReader.close();
		
		classReader = new BufferedReader(new FileReader(thursdayFile));
		classDayText = "Thursday";
		while ((classLine = classReader.readLine()) != null)
		{
			classArray = new String[0];
			classArray = classLine.split("<Space>");
			createNewClassObject(classArray[0], classArray[1], classArray[2], classArray[3], 
					classArray[4], classArray[5], classArray[6], classArray[7]);
		}
		classReader.close();
		
		classReader = new BufferedReader(new FileReader(fridayFile));
		classDayText = "Friday";
		while ((classLine = classReader.readLine()) != null)
		{
			classArray = new String[0];
			classArray = classLine.split("<Space>");
			createNewClassObject(classArray[0], classArray[1], classArray[2], classArray[3], 
					classArray[4], classArray[5], classArray[6], classArray[7]);
		}
		classReader.close();
	}
	
	public static void main(String[] args) throws Exception
	{
		GraphicOrganizer theOrganizer = new GraphicOrganizer();
	}
}
