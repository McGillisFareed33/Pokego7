package game_project;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CustomButton extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4680561106938103324L;
	JButton button;
	
	public CustomButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomButton(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public CustomButton(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public CustomButton(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	void initButton(ActionListener listener)
	{
		button = new JButton("test");
		button.setVerticalAlignment(SwingConstants.CENTER);
		button.setFont(new Font("Showcard Gothic",Font.PLAIN,15));
		button.addActionListener(listener);
		button.setOpaque(false);
		this.setOpaque(false);
		this.add(button);
		this.setPreferredSize(new Dimension(190,30));
		this.setMinimumSize(getPreferredSize());
		button.setPreferredSize(new Dimension(170,25));
	}
	void disableButton()
	{
		button.setVisible(false);
	}
	
	void enableButton()
	{
		button.setVisible(true);
	}
	
	
	
}
