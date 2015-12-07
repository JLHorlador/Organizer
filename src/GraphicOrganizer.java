import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class GraphicOrganizer {
	private JFrame organizerFrame;	//the main organizer window
	private JPanel topPanel;	//the panel that will contain the save button
	private JPanel buttonPanel;		//the panel that will contain the class notes, activities, and schedule buttons
	private JPanel flexiblePanel;	//the panel that will change according to what button is pressed
	private JPanel actionPanel;		//the panel that contains the action button and back button
	
	private JButton saveButton;		//the save button
	private JButton classButton;	//the class notes button
	private JButton activitiesButton;	//the activities button
	private JButton scheduleButton;		//the schedule button
	private JButton actionButton;		//the button on top of the flexible panel (name to be determined)
	private JButton backButton;			//the button to return to the previous screen
	
	private JTextField saveField;		//the JTextField that will display whether the user has saved their progress
	
	GridBagConstraints GBLayout = new GridBagConstraints();	//create the GridBagConstraints
	
	Stack<Integer> screens;		//keeps track of what screens the user had visited before
	
	public GraphicOrganizer()
	{
		screens = new Stack<Integer>();
		
		organizerFrame = new JFrame("Graphic Organizer");	//create a new window titled "Graphic Organizer"
		organizerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//create the panels that will be in the window
		//create the top panel to hold save feature
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		
		//create the panel to hold the three buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//create the flexible panel
		flexiblePanel = new JPanel();
		flexiblePanel.setLayout(new BorderLayout());
		flexiblePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//create the action panel
		actionPanel = new JPanel();
		actionPanel.setLayout(new GridBagLayout());
		//actionPanel.setBorder(BorderFactory.createMatteBorder(0,  0,  1, 0, Color.BLACK));
		flexiblePanel.add(actionPanel, BorderLayout.PAGE_START);
		
		//create the save button
		saveButton = new JButton("Save");
		topPanel.add(saveButton);
		
		//create the "progress has been saved" textfield
		saveField = new JTextField("Progress has been saved.");
		saveField.setEnabled(false);	//disables input
		saveField.setHorizontalAlignment(JTextField.RIGHT);		//displays message to the right of the field
		saveField.setDisabledTextColor(new Color(0, 0, 0));		//sets text color to black
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
					screens.push(3);
					newNoteDetails();
				}
				else if (actionButton.getText().equals("Add Activity"))
				{
					screens.push(4);
					newActivityDetails();
				}
			}
		});
		actionButton.setVisible(false);
		
		//add class notes button
		classButton = new JButton("Class Notes");
		classButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				screens.push(1);
				showClassNotes();
			}
		});
		GBLayout.ipady = 40;	//makes buttons thicker
		GBLayout.fill = GridBagConstraints.HORIZONTAL;	//makes buttons equal in width
		GBLayout.gridx = 0;		//sets x-coordinate to 0
		GBLayout.gridy = 0;		//sets y-coordinate to 0
		GBLayout.insets = new Insets(0, 10, 40, 10);
		buttonPanel.add(classButton, GBLayout);
		
		//add activities button
		activitiesButton = new JButton("Activities");
		activitiesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screens.push(2);
				showActivities();
			}
		});
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		GBLayout.insets = new Insets(20, 10, 20, 10);
		buttonPanel.add(activitiesButton, GBLayout);
		
		//add schedule button
		scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showStack();
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
	
	public void showClassNotes()
	{
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1.7;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.weightx = 2;
		actionPanel.add(actionButton, GBLayout);
		
		actionButton.setText("Add Note");
		backButton.setVisible(true);
		actionButton.setVisible(true);
	}
	
	public void showActivities()
	{
		GBLayout.anchor = GridBagConstraints.WEST;
		GBLayout.weightx = 1.7;
		actionPanel.add(backButton, GBLayout);
		
		GBLayout.anchor = GridBagConstraints.CENTER;
		GBLayout.weightx = 2;
		actionPanel.add(actionButton, GBLayout);
		
		actionButton.setText("Add Activity");
		backButton.setVisible(true);
		actionButton.setVisible(true);
	}
	
	public void newNoteDetails()
	{
		actionButton.setText("Add New Note");
	}
	
	public void newActivityDetails()
	{
		actionButton.setText("Add New Activity");
	}
	
	public void returnPreviousScreen()
	{
		if (!screens.isEmpty())
			screens.pop();
		
		
		if (screens.isEmpty())
		{
			actionButton.setVisible(false);
			backButton.setVisible(false);
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
	}
	
	public void showStack()		//experimental
	{
		System.out.println("Stack: " + screens);
	}
	
	public static void main(String[] args)
	{
		GraphicOrganizer theOrganizer = new GraphicOrganizer();
	}
}
