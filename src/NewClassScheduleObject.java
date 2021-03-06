import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class NewClassScheduleObject 
{
	String startHour;
	String startMinute;
	Object startTOD;
	String endHour;
	String endMinute;
	Object endTOD;
	String location;
	String title;
	int numberIndex;
	
	public NewClassScheduleObject(String sHour, String sMinute, Object sTOD, String eHour, String eMinute,
			Object eTOD, String classLocation, String classTitle)
	{
		startHour = sHour;
		startMinute = sMinute;
		startTOD = sTOD;
		endHour = eHour;
		endMinute = eMinute;
		endTOD = eTOD;
		location = classLocation;
		title = classTitle;
		numberIndex = 0;
	}
	
	public void setIndex(int number)
	{
		numberIndex = number;
	}
	
	public JLabel createLabel()
	{
		JLabel durationLabel = new JLabel(startHour + ":" + startMinute + " " + startTOD + " - " + endHour + ":" + endMinute + " " + endTOD);
		return durationLabel;
	}
	
	public JButton createButton()
	{
		JButton newClassButton = new JButton(title);
		newClassButton.setPreferredSize(new Dimension(570, 40));
		newClassButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				UIManager.put("ComboBox.disabledForeground", Color.BLACK);
				
				GraphicOrganizer.clearPanel();
				GraphicOrganizer.newClassDetails();
				GraphicOrganizer.screens.push(14);
				GraphicOrganizer.index = numberIndex;
				GraphicOrganizer.actionButton.setText("Remove Class");
				
				GraphicOrganizer.classStartHour.setSelectedItem(startHour);
				GraphicOrganizer.classStartHour.setEnabled(false);
				
				GraphicOrganizer.classStartMinute.setSelectedItem(startMinute);
				GraphicOrganizer.classStartMinute.setEnabled(false);
				
				GraphicOrganizer.classStartTOD.setSelectedItem(startTOD);
				GraphicOrganizer.classStartTOD.setEnabled(false);
				
				GraphicOrganizer.classEndHour.setSelectedItem(endHour);
				GraphicOrganizer.classEndHour.setEnabled(false);
				
				GraphicOrganizer.classEndMinute.setSelectedItem(endMinute);
				GraphicOrganizer.classEndMinute.setEnabled(false);
				
				GraphicOrganizer.classEndTOD.setSelectedItem(endTOD);
				GraphicOrganizer.classEndTOD.setEnabled(false);
				
				GraphicOrganizer.classLocation.setText(location);
				GraphicOrganizer.classLocation.setEnabled(false);
				
				GraphicOrganizer.classTitle.setText(title);
				GraphicOrganizer.classTitle.setEnabled(false);
			}
		});
		
		return newClassButton;
	}
	
	public String getStartHour()
	{
		return startHour;
	}
	
	public String getStartMinute()
	{
		return startMinute;
	}
	
	public String getStartTOD()
	{
		return startTOD.toString();
	}
	
	public String getEndHour()
	{
		return endHour;
	}
	
	public String getEndMinute()
	{
		return endMinute;
	}
	
	public String getEndTOD()
	{
		return endTOD.toString();
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public String getTitle()
	{
		return title;
	}
}
