import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
				GraphicOrganizer.clearPanel();
				GraphicOrganizer.newNoteDetails();
				GraphicOrganizer.screens.push(6);
				GraphicOrganizer.index = numberIndex;
				GraphicOrganizer.actionButton.setText("Remove Note");
				
				GraphicOrganizer.noteDateMonth.setText(month);
				GraphicOrganizer.noteDateMonth.setEnabled(false);
				GraphicOrganizer.noteDateMonth.setDisabledTextColor(Color.BLACK);
				
				GraphicOrganizer.noteDateDay.setText(day);
				GraphicOrganizer.noteDateDay.setEnabled(false);
				GraphicOrganizer.noteDateDay.setDisabledTextColor(Color.BLACK);
				
				GraphicOrganizer.noteDateYear.setText(year);
				GraphicOrganizer.noteDateYear.setEnabled(false);
				GraphicOrganizer.noteDateYear.setDisabledTextColor(Color.BLACK);
				
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
}
