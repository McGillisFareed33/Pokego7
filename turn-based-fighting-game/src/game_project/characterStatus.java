package game_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class characterStatus extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8284946277427890738L;
	public JLabel nameTag;
	public JLabel SpeedTag;
	public JLabel characterPhoto;
	public JProgressBar hp;
	public Character displayedChar;
	
	public void charSetup(Character chr)
	{
		characterPhoto = new JLabel();
		displayedChar = chr;
		StringBuilder Name = new StringBuilder(displayedChar.Name);
		while(Name.length() < 25)
		{
			Name.append(' ');
		}
		nameTag = new JLabel(Name.toString());
		nameTag.setFont(new Font("Showcard Gothic",Font.PLAIN,15));
		SpeedTag = new JLabel();
		
		SpeedTag.setText("Speed : "+(displayedChar.SPD + displayedChar.StatMods[4]));
		SpeedTag.setFont(new Font("Showcard Gothic",Font.PLAIN,15));

		hp = new JProgressBar(0, chr.HP);
		hp.setValue(displayedChar.HPCurrent);
		hp.setString("" + hp.getValue());
		hp.setFont(new Font("Showcard Gothic",Font.PLAIN,15));
		hp.setStringPainted(true);  
		hp.setForeground(new Color(30,255,30));
		characterPhoto.setIcon(displayedChar.characterIcon);
		this.add(characterPhoto);
		this.add(nameTag);
		

		this.add(hp);
		this.add(SpeedTag);
		this.setBorder(BorderFactory.createRaisedBevelBorder());

		this.setPreferredSize(new Dimension(520, 70));
		this.setMinimumSize(getPreferredSize());
	}
	public void adjustStatus()
	{
		hp.setValue(displayedChar.HPCurrent);
		hp.setString("" + hp.getValue());
		SpeedTag.setText("Speed : "+(displayedChar.SPD + displayedChar.StatMods[4]));
		if(hp.getMaximum() > hp.getValue() * 4)
		{
			hp.setForeground(new Color(255,30,30));
		}
		else
		{
			hp.setForeground(new Color(30,255,30));
		}
		if(hp.getValue() == 0) {
			characterPhoto.setIcon(displayedChar.characterDeathIcon);
		}
	}
	public void adjustStatus(int amount)
	{
		hp.setValue(Math.max(hp.getValue()+amount, 0));
		hp.setString("" + hp.getValue());
		if(hp.getMaximum() > hp.getValue() * 4)
		{
			hp.setForeground(new Color(255,30,30));
		}
		else
		{
			hp.setForeground(new Color(30,255,30));
		}
		if(hp.getValue() == 0) {
			characterPhoto.setIcon(displayedChar.characterDeathIcon);
		}
	}
	
	
	
	public characterStatus() {
		// TODO Auto-generated constructor stub
		
	}

	public characterStatus(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public characterStatus(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public characterStatus(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
