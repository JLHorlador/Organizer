import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.ArrayList;

public class GraphicOrganizer {
	private JFrame organizerFrame;	//the main organizer window
	private JPanel topPanel;		//the panel that will contain the save button
	private JPanel buttonPanel;		//the panel that will contain the class notes, activities, and schedule buttons
	private JPanel flexiblePanel;	//the panel that will change according to what button is pressed
	private static JPanel actionPanel;		//the panel that contains the action button and back button
	private static JPanel infoPanel;		//the panel that contains the inputs 
	
	private JButton saveButton;			//the save button
	private JButton classButton;		//the class notes button
	private JButton activitiesButton;	//the activities button
	private JButton scheduleButton;		//the schedule button
	public static JButton actionButton;		//the button on top of the flexible panel (name to be determined)
	public static JButton backButton;			//the button to return to the previous screen
	private JButton mondayButton;		//monday button for the schedule
	private JButton tuesdayButton;		//tuesday button for the schedule
	private JButton wednesdayButton;	//wednesday button for the schedule
	private JButton thursdayButton;		//thursday button for the schedule
	private JButton fridayButton;		//friday button for the schedule
	
	private JTextField saveField;			//the JTextField that will display whether the user has saved their progress
	
	private static JTextField noteDate;			//displays where the note's date input boxes are
	public static JTextField noteDateMonth;		//the JTextField that will take the input for the note's month date
	public static JTextField noteDateDay;			//the JTextField that will take the input for the note's day date
	public static JTextField noteDateYear;		//the JTextField that will take the input for the note's year date
	public static JTextField noteTitle;			//the JTextField that will take the input for the note's title
	public static JTextArea noteInfo;				//the JTextField that will take the input for the note's information
	
	private static JTextField activityFrom;		//displays where the activity's start date input boxes are
	public static JTextField activityFromMonth;	//the JTextField that will take input for the month the activity starts
	public static JTextField activityFromDay;		//the JTextField that will take input for the day the activity starts
	public static JTextField activityFromYear;	//the JTextField that will take input for the year the activity starts
	
	private static JTextField activityTo;			//displays where the activity's end date input boxes are
	public static JTextField activityToMonth;		//the JTextField that will take the input for the month the activity ends
	public static JTextField activityToDay;		//the JTextField that will take the input for the day the activity ends
	public static JTextField activityToYear;		//the JTextField that will take the input for the year the activity ends
	
	private static JTextField activityStart;				//displays where the activity's beginning time input boxes are
	public static JTextField activityStartHour;			//the JTextField that will take the input for the hour the activity starts
	public static JTextField activityStartMinute;			//the JTextField that will take the input for the minute the activity starts
	public static JComboBox<String> activityStartTOD;		//the drop down box that will take the input for the time of day the activity starts
	
	private static JTextField activityEnd;					//displays where the activity's end time input boxes are
	public static JTextField activityEndHour;				//the JTextField that will take the input for the hour the activity ends
	public static JTextField activityEndMinute;			//the JTextField that will take the input for the minute the activity ends
	public static JComboBox<String> activityEndTOD;		//the drop down box that will take the input for the time of day the activity ends
	
	public static JTextField activityTitle;		//the JTextField that will take the input for the activity's title
	public static JTextArea activityInfo;			//the JTextField that will take the input for the activity's info
	
	private JTextField classSchedule;		//the JTextField to indicate that the screen shows the class days
	
	public static JTextField classStart;				//indicates where the class's beginning time input boxes are
	public static JTextField classStartHour;			//the JTextField that will take the input for the hour the class starts
	public static JTextField classStartMinute;		//the JTextField that will take the input for the minute the class starts
	public static JComboBox<String> classStartTOD;	//the drop down box to choose what time of day the class takes place
	
	public static JTextField classEnd;				//indicates where the class's end time input boxes are
	public static JTextField classEndHour;			//the JTextField that will take the input for the hour the class ends
	public static JTextField classEndMinute;			//the JTextField that will take the input for the minute the class ends
	public static JComboBox<String> classEndTOD;		//the drop down box to choose what time of day the class takes place
	
	public static JTextField classLocation;		//the JTextField that takes the input for the class location
	public static JTextField classTitle;			//the JTextField that takes the input for the name of the class
	
	private JLabel classDay;				//JLabel that displays the chosen day to display classes

	private static String classDayText = "";		//contains the word of the chosen day in the schedule
	
	String[] timeOfDay = {"AM", "PM"};
	
	ArrayList<NewNoteObject> noteObject = new ArrayList<NewNoteObject>();
	ArrayList<NewActivityObject> activityObject = new ArrayList<NewActivityObject>();
	ArrayList<NewClassScheduleObject> mondayClasses = new ArrayList<NewClassScheduleObject>();
	ArrayList<NewClassScheduleObject> tuesdayClasses = new ArrayList<NewClassScheduleObject>();
	ArrayList<NewClassScheduleObject> wednesdayClasses = new ArrayList<NewClassScheduleObject>();
	ArrayList<NewClassScheduleObject> thursdayClasses = new ArrayList<NewClassScheduleObject>();
	ArrayList<NewClassScheduleObject> fridayClasses = new ArrayList<NewClassScheduleObject>();
	
	public static int index = 0;
	
	GridBagConstraints GBLayout = new GridBagConstraints();	//create the GridBagConstraints
	
	static Stack<Integer> screens;		//keeps track of what screens the user had visited before
	
	public GraphicOrganizer()
	{
		screens = new Stack<Integer>();
		
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
		flexiblePanel.add(infoPanel, BorderLayout.CENTER);
		
		//create the save button
		saveButton = new JButton("Save");
		topPanel.add(saveButton);
		
		//create the "progress has been saved" textfield
		saveField = new JTextField("Progress has been saved.");
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
				returnPreviousScreen();
			}
		});
		backButton.setVisible(false);
		
		//add action button
		actionButton = new JButton();
		actionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (actionButton.getText().equals("Add Note"))
				{
					if (screens.peek() != 3)	//checks if the previous screen is not the same as the current screen being displayed
					{
						screens.push(3);		//3 indicates that the previous screen displays the class notes
						clearPanel();
						newNoteDetails();
					}
				}
				else if (actionButton.getText().equals("Add Activity"))
				{
					if (screens.peek() != 4)	//checks if the previous screen is not the same as the current screen
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
						screens.push(13);
						clearPanel();
						newClassDetails();
						System.out.println(screens);
					}
				}
				else if (actionButton.getText().equals("Add New Note"))
				{
					createNewNoteObject();
					returnPreviousScreen();
				}
				else if (actionButton.getText().equals("Add New Activity"))
				{
					createNewActivityObject();
					returnPreviousScreen();
				}
				else if (actionButton.getText().equals("Add " + classDayText + " Class"))
				{
					createNewClassObject();
					returnPreviousScreen();
				}
				else if (actionButton.getText().equals("Remove Note"))
				{
					removeNote();
					clearStack();
					screens.push(1);		//1 indicates that the screen will display class notes
					clearPanel();
					showClassNotes();		//display class notes
				}
				else if (actionButton.getText().equals("Remove Activity"))
				{
					removeActivity();
					clearStack();
					screens.push(2);		//2 indicates that the screen displays activities
					clearPanel();
					showActivities();		//show activities
				}
				else if (actionButton.getText().equals("Remove Class"))
				{
					removeClass();
					returnPreviousScreen();
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
		
		noteDateMonth = new JTextField("MM");						//displays two large M's
		noteDateMonth.setHorizontalAlignment(JTextField.CENTER);	//centers text
		
		noteDateDay = new JTextField("DD");							//displays two large D's
		noteDateDay.setHorizontalAlignment(JTextField.CENTER);		//centers text
		
		noteDateYear = new JTextField("YYYY");						//displays four large Y's
		noteDateYear.setHorizontalAlignment(JTextField.CENTER);		//centers text
		
		noteTitle = new JTextField("Enter title of note here");				//displays where to enter the title
		noteTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));	//sets a black border around the field
		noteTitle.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
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
					clearStack();
					screens.push(1);		//1 indicates that the screen will display class notes
					clearPanel();
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
		
		activityFromMonth = new JTextField("MM");						//displays two M's
		activityFromMonth.setHorizontalAlignment(JTextField.CENTER);	//centers text
		
		activityFromDay = new JTextField("DD");							//displays two D's
		activityFromDay.setHorizontalAlignment(JTextField.CENTER);		//centers text
		
		activityFromYear = new JTextField("YYYY");						//displays four Y's
		activityFromYear.setHorizontalAlignment(JTextField.CENTER);
		
		activityTo = new JTextField("To: ");
		activityTo.setOpaque(false);
		activityTo.setDisabledTextColor(Color.BLACK);
		activityTo.setBorder(null);;
		activityTo.setEnabled(false);
		
		activityToMonth = new JTextField("MM");
		activityToMonth.setHorizontalAlignment(JTextField.CENTER);
		
		activityToDay = new JTextField("DD");
		activityToDay.setHorizontalAlignment(JTextField.CENTER);
		
		activityToYear = new JTextField("YYYY");
		activityToYear.setHorizontalAlignment(JTextField.CENTER);
		
		activityStart = new JTextField("Start: ");
		activityStart.setOpaque(false);
		activityStart.setDisabledTextColor(Color.BLACK);
		activityStart.setBorder(null);
		activityStart.setEnabled(false);
		
		activityStartHour = new JTextField("HH");
		activityStartHour.setHorizontalAlignment(JTextField.CENTER);
		
		activityStartMinute = new JTextField("MM");
		activityStartMinute.setHorizontalAlignment(JTextField.CENTER);
		
		activityStartTOD = new JComboBox<String>(timeOfDay);		//time of day drop box for activity start
		
		activityEnd = new JTextField("End: ");
		activityEnd.setOpaque(false);
		activityEnd.setDisabledTextColor(Color.BLACK);
		activityEnd.setBorder(null);
		activityEnd.setEnabled(false);
		
		activityEndHour = new JTextField("HH");
		activityEndHour.setHorizontalAlignment(JTextField.CENTER);
		
		activityEndMinute = new JTextField("MM");
		activityEndMinute.setHorizontalAlignment(JTextField.CENTER);
		
		activityEndTOD = new JComboBox<String>(timeOfDay);			//time of day drop box for activity end
		
		activityTitle = new JTextField("Enter title of activity here");
		activityTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		activityTitle.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
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
					clearStack();
					screens.push(2);		//2 indicates that the screen displays activities
					clearPanel();
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
				screens.push(8);
				classDayText = "Monday";
				clearPanel();
				showClasses();
			}
		});
		mondayButton.setPreferredSize(new Dimension(150, 60));
		
		tuesdayButton = new JButton("Tuesday");
		tuesdayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);
				classDayText = "Tuesday";
				clearPanel();
				showClasses();
			}
		});
		tuesdayButton.setPreferredSize(new Dimension(150, 60));
		
		wednesdayButton = new JButton("Wednesday");
		wednesdayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);
				classDayText = "Wednesday";
				clearPanel();
				showClasses();
			}
		});
		wednesdayButton.setPreferredSize(new Dimension(150, 60));
		
		thursdayButton = new JButton("Thursday");
		thursdayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);
				classDayText = "Thursday";
				clearPanel();
				showClasses();
			}
		});
		thursdayButton.setPreferredSize(new Dimension(150, 60));
		
		fridayButton = new JButton("Friday");
		fridayButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(8);
				classDayText = "Friday";
				clearPanel();
				showClasses();
			}
		});
		fridayButton.setPreferredSize(new Dimension(150, 60));
		
		classStart = new JTextField("Start: ");
		classStart.setOpaque(false);
		classStart.setDisabledTextColor(Color.BLACK);
		classStart.setBorder(null);
		classStart.setEnabled(false);
		
		classStartHour = new JTextField("HH");
		classStartHour.setHorizontalAlignment(JTextField.CENTER);
		classStartHour.setDisabledTextColor(Color.BLACK);
		
		classStartMinute = new JTextField("MM");
		classStartMinute.setHorizontalAlignment(JTextField.CENTER);
		classStartMinute.setDisabledTextColor(Color.BLACK);
		
		classStartTOD = new JComboBox<String>(timeOfDay);
		
		classEnd = new JTextField("End: ");
		classEnd.setOpaque(false);
		classEnd.setDisabledTextColor(Color.BLACK);
		classEnd.setBorder(null);
		classEnd.setEnabled(false);
		
		classEndHour = new JTextField("HH");
		classEndHour.setHorizontalAlignment(JTextField.CENTER);
		classEndHour.setDisabledTextColor(Color.BLACK);
		
		classEndMinute = new JTextField("MM");
		classEndMinute.setHorizontalAlignment(JTextField.CENTER);
		classEndMinute.setDisabledTextColor(Color.BLACK);
		
		classEndTOD = new JComboBox<String>(timeOfDay);
		
		classLocation = new JTextField("Enter class location here");
		classLocation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		classLocation.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (classLocation.getText().equals("Enter class location here"))
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
				if (classTitle.getText().equals("Enter class name here"))
					classTitle.setText("");
			}
		});
		classTitle.setDisabledTextColor(Color.BLACK);
		
		classSchedule = new JTextField("Class Schedule");
		classSchedule.setOpaque(false);
		classSchedule.setDisabledTextColor(Color.BLACK);
		classSchedule.setBorder(null);
		classSchedule.setEnabled(false);
		
		//add schedule button
		scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (screens.isEmpty() || screens.peek() != 5)
				{
					clearStack();
					screens.push(5);
					clearPanel();
					showClassDays();
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
		
		organizerFrame.add(topPanel, BorderLayout.PAGE_START);
		organizerFrame.add(buttonPanel, BorderLayout.LINE_START);
		organizerFrame.add(flexiblePanel, BorderLayout.CENTER);
		organizerFrame.pack();
		organizerFrame.setVisible(true);
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
		
		GBLayout.anchor = GridBagConstraints.NORTH;
		GBLayout.fill = GridBagConstraints.HORIZONTAL;
		GBLayout.insets = new Insets(10, 10, 10, 10);
		
		for (int i = 0; i < noteObject.size(); i++)
		{
			GBLayout.gridy = i;
			noteObject.get(i).setIndex(i);
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
		
		GBLayout.anchor = GridBagConstraints.NORTH;
		GBLayout.fill = GridBagConstraints.HORIZONTAL;
		GBLayout.insets = new Insets(10, 10, 10, 10);
		
		for (int i = 0; i < activityObject.size(); i++)
		{
			GBLayout.gridy = i;
			activityObject.get(i).setIndex(i);
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
		
		GBLayout.anchor = GridBagConstraints.NORTH;
		GBLayout.fill = GridBagConstraints.HORIZONTAL;
		GBLayout.insets = new Insets(10, 10, 10, 10);
		
		if (classDayText.equals("Monday"))
		{
			classDay.setText("Monday");
			if (!mondayClasses.isEmpty())
			{
				for (int i = 0; i < mondayClasses.size(); i++)
				{
					GBLayout.gridy = i;
					mondayClasses.get(i).setIndex(i);
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
		noteDateMonth.setText("MM");	//reset textfield
		noteDateMonth.setEnabled(true);
		infoPanel.add(noteDateMonth, GBLayout);
		
		GBLayout.gridx = 2;		//place two cells right of the noteDate
		noteDateDay.setText("DD");
		noteDateDay.setEnabled(true);
		infoPanel.add(noteDateDay, GBLayout);
		
		GBLayout.gridx = 3;		//place three cells to the right of the noteDate
		noteDateYear.setText("YYYY");
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
		activityFromMonth.setText("MM");
		activityFromMonth.setEnabled(true);
		infoPanel.add(activityFromMonth, GBLayout);
		
		GBLayout.gridx = 2;
		activityFromDay.setText("DD");
		activityFromDay.setEnabled(true);
		infoPanel.add(activityFromDay, GBLayout);
		
		GBLayout.gridx = 3;
		activityFromYear.setText("YYYY");
		activityFromYear.setEnabled(true);
		infoPanel.add(activityFromYear, GBLayout);
		
		//add activity end date
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		GBLayout.weighty = 0;
		infoPanel.add(activityTo, GBLayout);
		
		GBLayout.gridx = 1;
		activityToMonth.setText("MM");
		activityToMonth.setEnabled(true);
		infoPanel.add(activityToMonth, GBLayout);
		
		GBLayout.gridx = 2;
		activityToDay.setText("DD");
		activityToDay.setEnabled(true);
		infoPanel.add(activityToDay, GBLayout);
		
		GBLayout.gridx = 3;
		activityToYear.setText("YYYY");
		activityToYear.setEnabled(true);
		infoPanel.add(activityToYear, GBLayout);
		
		//add activity time start
		GBLayout.gridx = 0;
		GBLayout.gridy = 2;
		infoPanel.add(activityStart, GBLayout);
		
		GBLayout.gridx = 1;
		activityStartHour.setText("HH");
		activityStartHour.setEnabled(true);
		infoPanel.add(activityStartHour, GBLayout);
		
		GBLayout.gridx = 2;
		activityStartMinute.setText("MM");
		activityStartMinute.setEnabled(true);
		infoPanel.add(activityStartMinute, GBLayout);
		
		GBLayout.gridx = 3;
		activityStartTOD.setEnabled(true);
		infoPanel.add(activityStartTOD, GBLayout);
		
		//add activity time end
		GBLayout.gridx = 0;
		GBLayout.gridy = 3;
		infoPanel.add(activityEnd, GBLayout);
		
		GBLayout.gridx = 1;
		activityEndHour.setText("HH");
		activityEndHour.setEnabled(true);
		infoPanel.add(activityEndHour, GBLayout);
		
		GBLayout.gridx = 2;
		activityEndMinute.setText("MM");
		activityEndMinute.setEnabled(true);
		infoPanel.add(activityEndMinute, GBLayout);
		
		GBLayout.gridx = 3;
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
		classStartHour.setText("HH");
		classStartHour.setEnabled(true);
		infoPanel.add(classStartHour, GBLayout);
		
		GBLayout.gridx = 2;
		classStartMinute.setText("MM");
		classStartMinute.setEnabled(true);
		infoPanel.add(classStartMinute, GBLayout);
		
		GBLayout.gridx = 3;
		classStartTOD.setEnabled(true);
		infoPanel.add(classStartTOD, GBLayout);
		
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		infoPanel.add(classEnd, GBLayout);
		
		GBLayout.gridx = 1;
		classEndHour.setText("HH");
		classEndHour.setEnabled(true);
		infoPanel.add(classEndHour, GBLayout);
		
		GBLayout.gridx = 2;
		classEndMinute.setText("MM");
		classEndMinute.setEnabled(true);
		infoPanel.add(classEndMinute, GBLayout);
		
		GBLayout.gridx = 3;
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
	
	public void createNewNoteObject()
	{
		noteObject.add(new NewNoteObject(noteDateMonth.getText(), noteDateDay.getText(), noteDateYear.getText(), 
				noteTitle.getText(), noteInfo.getText()));
	}
	
	public void createNewActivityObject()
	{
		activityObject.add(new NewActivityObject(activityFromMonth.getText(), activityFromDay.getText(), 
				activityFromYear.getText(), activityToMonth.getText(), activityToDay.getText(), 
				activityToYear.getText(), activityStartHour.getText(), activityStartMinute.getText(), 
				activityStartTOD.getSelectedItem(), activityEndHour.getText(), activityEndMinute.getText(),
				activityEndTOD.getSelectedItem(), activityTitle.getText(), activityInfo.getText()));
	}
	
	public void createNewClassObject()
	{
		if (classDayText.equals("Monday"))
			mondayClasses.add(new NewClassScheduleObject(classStartHour.getText(), classStartMinute.getText(),
					classStartTOD.getSelectedItem(), classEndHour.getText(), classEndMinute.getText(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Tuesday"))
			tuesdayClasses.add(new NewClassScheduleObject(classStartHour.getText(), classStartMinute.getText(),
					classStartTOD.getSelectedItem(), classEndHour.getText(), classEndMinute.getText(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Wednesday"))
			wednesdayClasses.add(new NewClassScheduleObject(classStartHour.getText(), classStartMinute.getText(),
					classStartTOD.getSelectedItem(), classEndHour.getText(), classEndMinute.getText(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Thursday"))
			thursdayClasses.add(new NewClassScheduleObject(classStartHour.getText(), classStartMinute.getText(),
					classStartTOD.getSelectedItem(), classEndHour.getText(), classEndMinute.getText(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		else if (classDayText.equals("Friday"))
			fridayClasses.add(new NewClassScheduleObject(classStartHour.getText(), classStartMinute.getText(),
					classStartTOD.getSelectedItem(), classEndHour.getText(), classEndMinute.getText(), 
					classEndTOD.getSelectedItem(), classLocation.getText(), classTitle.getText()));
		/*
		System.out.println("Monday: " + mondayClasses + "\n" +
					"Tuesday: " + tuesdayClasses + "\n" + 
					"Wednesday: " + wednesdayClasses + "\n" + 
					"Thursday: " + thursdayClasses + "\n" + 
					"Friday: " + fridayClasses + "\n");
		*/
	}
	
	private void returnPreviousScreen()
	{
		if (!screens.isEmpty())
			screens.pop();
		//System.out.println(screens);
		
		clearPanel();
		
		if (screens.isEmpty())
		{
			//actionButton.setVisible(false);
			//backButton.setVisible(false);
			clearPanel();
		}
		else if (screens.peek() == 1)
		{
			showClassNotes();
		}
		else if (screens.peek() == 2)
		{
			showActivities();
		}
		else if (screens.peek() == 3)
		{
			newNoteDetails();
		}
		else if (screens.peek() == 4)
		{
			newActivityDetails();
		}
		else if (screens.peek() == 5)
		{
			showClassDays();
		}
		else if (screens.peek() == 8)
		{
			showClasses();
		}
	}
	
	public static void clearPanel()
	{
		infoPanel.removeAll();
		infoPanel.revalidate();
		infoPanel.repaint();

		actionPanel.removeAll();
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	private void clearStack()
	{
		while (!screens.isEmpty())
			screens.pop();
	}
	
	private void removeNote()
	{
		noteObject.remove(index);
		index = 0;
	}
	
	private void removeActivity()
	{
		activityObject.remove(index);
		index = 0;
	}
	
	private void removeClass()
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
	
	/*
	private static void resetGridBagConstraints()
	{
		GridBagConstraints GBLayout = new GridBagConstraints();
		GBLayout.gridx = 0;
		GBLayout.gridy = 0;
		GBLayout.insets = new Insets(0,0,0,0);
		GBLayout.fill = 0;
		GBLayout.ipady = 0;
		GBLayout.anchor = 0;
		GBLayout.gridwidth = 0;
		GBLayout.gridheight = 0;
		GBLayout.weightx = 0;
		GBLayout.weighty = 0;
		
	}
	*/
	
	public static void main(String[] args)
	{
		GraphicOrganizer theOrganizer = new GraphicOrganizer();
	}
}
