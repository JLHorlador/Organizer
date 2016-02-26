import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.UIManager;

public class NewNoteObject 
{
	String month;
	String day;
	String year;
	String title;
	String info;
	int numberIndex;
	
	public NewNoteObject(String m, String d, String y, String t, String i)
	{
		month = m;
		day = d;
		year = y;
		title = t;
		info = i;
		numberIndex = 0;
	}
	
	public void setIndex(int number)
	{
		numberIndex = number;
	}
	
	public JButton createButton()
	{
		JButton newNoteButton = new JButton(title);
		newNoteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				UIManager.put("ComboBox.disabledForeground", Color.BLACK);
				
				GraphicOrganizer.clearPanel();
				GraphicOrganizer.newNoteDetails();
				GraphicOrganizer.screens.push(6);
				GraphicOrganizer.index = numberIndex;
				GraphicOrganizer.actionButton.setText("Remove Note");
				
				GraphicOrganizer.noteDateMonth.setSelectedItem(month);
				GraphicOrganizer.noteDateMonth.setEnabled(false);
				
				GraphicOrganizer.noteDateDay.setSelectedItem(day);
				GraphicOrganizer.noteDateDay.setEnabled(false);
				
				GraphicOrganizer.noteDateYear.setSelectedItem(year);
				GraphicOrganizer.noteDateYear.setEnabled(false);
				
				GraphicOrganizer.noteTitle.setText(title);
				GraphicOrganizer.noteTitle.setEnabled(false);
				GraphicOrganizer.noteTitle.setDisabledTextColor(Color.BLACK);
				
				GraphicOrganizer.noteInfo.setText(info);
				GraphicOrganizer.noteInfo.setEnabled(false);
				GraphicOrganizer.noteInfo.setDisabledTextColor(Color.BLACK);
			}
		});
		
		return newNoteButton;
	}
	
	public String getMonth()
	{
		return month;
	}
	
	public String getDay()
	{
		return day;
	}
	
	public String getYear()
	{
		return year;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getInfo()
	{
		return info;
	}
	
}
