import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
	
	public JButton createButton()
	{
		JButton newClassButton = new JButton(title);
		newClassButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GraphicOrganizer.clearPanel();
				GraphicOrganizer.newClassDetails();
				GraphicOrganizer.screens.push(14);
				GraphicOrganizer.index = numberIndex;
				GraphicOrganizer.actionButton.setText("Remove Class");
				
				GraphicOrganizer.classStartHour.setText(startHour);
				GraphicOrganizer.classStartHour.setEnabled(false);
				
				GraphicOrganizer.classStartMinute.setText(startMinute);
				GraphicOrganizer.classStartMinute.setEnabled(false);
				
				GraphicOrganizer.classStartTOD.setSelectedItem(startTOD);
				GraphicOrganizer.classStartTOD.setEnabled(false);
				
				GraphicOrganizer.classEndHour.setText(endHour);
				GraphicOrganizer.classEndHour.setEnabled(false);
				
				GraphicOrganizer.classEndMinute.setText(endMinute);
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
}
