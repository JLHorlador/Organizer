import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.UIManager;

public class NewActivityObject 
{
	String fromMonth;
	String fromDay;
	String fromYear;
	String toMonth;
	String toDay;
	String toYear;
	String startHour;
	String startMinute;
	Object startTOD;
	String endHour;
	String endMinute;
	Object endTOD;
	String title;
	String info;
	int numberIndex;
	
	public NewActivityObject(String fMonth, String fDay, String fYear, String tMonth, String tDay, 
			String tYear, String sHour, String sMinute, Object sTOD, String eHour, String eMinute, Object eTOD,
			String aTitle, String aInfo)
	{
		fromMonth = fMonth;
		fromDay = fDay;
		fromYear = fYear;
		toMonth = tMonth;
		toDay = tDay;
		toYear = tYear;
		startHour = sHour;
		startMinute = sMinute;
		startTOD = sTOD;
		endHour = eHour;
		endMinute= eMinute;
		endTOD = eTOD;
		title = aTitle;
		info = aInfo;
		numberIndex = 0;
	}
	
	public JButton createButton()
	{
		JButton newActivityButton = new JButton(title);
		newActivityButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				UIManager.put("ComboBox.disabledForeground", Color.BLACK);
				
				GraphicOrganizer.clearPanel();
				GraphicOrganizer.newActivityDetails();
				GraphicOrganizer.screens.push(7);
				GraphicOrganizer.index = numberIndex;
				GraphicOrganizer.actionButton.setText("Remove Activity");
				
				GraphicOrganizer.activityFromMonth.setSelectedItem(fromMonth);
				GraphicOrganizer.activityFromMonth.setEnabled(false);
				
				GraphicOrganizer.activityFromDay.setSelectedItem(fromDay);
				GraphicOrganizer.activityFromDay.setEnabled(false);
				
				GraphicOrganizer.activityFromYear.setSelectedItem(fromYear);
				GraphicOrganizer.activityFromYear.setEnabled(false);
				
				GraphicOrganizer.activityToMonth.setSelectedItem(toMonth);
				GraphicOrganizer.activityToMonth.setEnabled(false);
				
				GraphicOrganizer.activityToDay.setSelectedItem(toDay);
				GraphicOrganizer.activityToDay.setEnabled(false);
				
				GraphicOrganizer.activityToYear.setSelectedItem(toYear);
				GraphicOrganizer.activityToYear.setEnabled(false);
				
				GraphicOrganizer.activityStartHour.setSelectedItem(startHour);
				GraphicOrganizer.activityStartHour.setEnabled(false);
				
				GraphicOrganizer.activityStartMinute.setSelectedItem(startMinute);
				GraphicOrganizer.activityStartMinute.setEnabled(false);
				
				GraphicOrganizer.activityStartTOD.setSelectedItem(startTOD);
				GraphicOrganizer.activityStartTOD.setEnabled(false);
				GraphicOrganizer.activityStartTOD.setForeground(Color.BLACK);
				
				GraphicOrganizer.activityEndHour.setSelectedItem(endHour);
				GraphicOrganizer.activityEndHour.setEnabled(false);
				
				GraphicOrganizer.activityEndMinute.setSelectedItem(endMinute);
				GraphicOrganizer.activityEndMinute.setEnabled(false);
				
				GraphicOrganizer.activityEndTOD.setSelectedItem(endTOD);
				GraphicOrganizer.activityEndTOD.setEnabled(false);
				GraphicOrganizer.activityEndTOD.setForeground(Color.BLACK);
				
				GraphicOrganizer.activityTitle.setText(title);
				GraphicOrganizer.activityTitle.setEnabled(false);
				GraphicOrganizer.activityTitle.setDisabledTextColor(Color.BLACK);
				
				GraphicOrganizer.activityInfo.setText(info);
				GraphicOrganizer.activityInfo.setEnabled(false);
				GraphicOrganizer.activityInfo.setDisabledTextColor(Color.BLACK);
			}
		});
		
		return newActivityButton;
	}
	
	public String getFromMonth()
	{
		return fromMonth;
	}
	
	public String getFromDay()
	{
		return fromDay;
	}
	
	public String getFromYear()
	{
		return fromYear;
	}
	
	public String getToMonth()
	{
		return toMonth;
	}
	
	public String getToDay()
	{
		return toDay;
	}
	
	public String getToYear()
	{
		return toYear;
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
	
	public String getTitle()
	{
		return title;
	}
	
	public String getInfo()
	{
		return info;
	}
	
	public void setIndex(int number)
	{
		numberIndex = number;
	}
}
