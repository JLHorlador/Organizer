import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GraphicOrganizer {
	private JFrame organizerFrame;	//the main organizer window
	private JPanel topPanel;	//the panel that will contain the save button
	private JPanel buttonPanel;		//the panel that will contain the class notes, activities, and schedule buttons
	private JPanel flexiblePanel;	//the panel that will change according to what button is pressed
	private JButton saveButton;		//the save button
	private JButton classButton;	//the class notes button
	private JButton activitiesButton;	//the activities button
	private JButton scheduleButton;		//the schedule button
	private JTextField saveField;		//the JTextField that will display whether the user has saved their progress
	
	public GraphicOrganizer()
	{
		organizerFrame = new JFrame("Graphic Organizer");	//create a new window titled "Graphic Organizer"
		organizerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create the GridBagConstraints
		GridBagConstraints GBLayout = new GridBagConstraints();

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
		flexiblePanel.setLayout(new GridBagLayout());
		flexiblePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
		
		//shared properties of the buttons in the buttonPanel
		GBLayout.ipady = 40;	//makes buttons thicker
		GBLayout.fill = GridBagConstraints.HORIZONTAL;	//makes buttons equal in width
		
		classButton = new JButton("Class Notes");
		GBLayout.gridx = 0;		//sets x-coordinate to 0
		GBLayout.gridy = 0;		//sets y-coordinate to 0
		GBLayout.insets = new Insets(0, 10, 40, 10);
		buttonPanel.add(classButton, GBLayout);
		
		activitiesButton = new JButton("Activities");
		GBLayout.gridx = 0;
		GBLayout.gridy = 1;
		GBLayout.insets = new Insets(20, 10, 20, 10);
		buttonPanel.add(activitiesButton, GBLayout);
		
		scheduleButton = new JButton("Schedule");
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
	
	public static void main(String[] args)
	{
		GraphicOrganizer theOrganizer = new GraphicOrganizer();
	}
}
