package game_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

public class guitestClass {

	JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel,leftFightPanel,rightFightPanel,panel,infoPanel;
	JButton startButton; // wont be necessary
	CustomButton[] battlebuttons = null;
	JLabel imageLabel1,imageLabel2,imageLabel3,titleNameLabel,infoLabel;
    ScreenHandlernew sHandler = new ScreenHandlernew();
    Action[] actions;
	Container battleContainer;
	battleObjectGui battleManager = null;
	ArrayList<characterStatus> statuses = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new guitestClass();
		 

	}
	
	public guitestClass() {
		
		
		
		
		
		window = new JFrame();
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("/ball.png")));
		window.setSize(700,600);
		window.setPreferredSize(window.getSize());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//window.getContentPane().setBackground(Color.gray);
		
		Image pokemonPNG = new ImageIcon(this.getClass().getResource("arena2.png")).getImage();
		imageLabel1 = new JLabel();
		imageLabel1.setIcon(new ImageIcon(pokemonPNG));
		//imageLabel1.setBounds(0,42,1093,750);
		imageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel1.setVisible(true);
		window.add(imageLabel1);
		imageLabel1.setLayout(new GridBagLayout());
		window.setVisible(true);
		window.setResizable(true);
        window.setMinimumSize(new Dimension(900, 700));

		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.NONE;

		
		con = imageLabel1;
		
		titleNamePanel = new JPanel();
		titleNameLabel = new JLabel("Arena Fight");
		titleNamePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		titleNameLabel.setForeground(Color.black);
		titleNameLabel.setFont(new Font("Showcard Gothic",Font.BOLD,31));
		//titleNamePanel.setBounds(0,0,1093,45);
		titleNamePanel.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridwidth= 3;
		titleNamePanel.add(titleNameLabel,c);
		con.add(titleNamePanel,c);

		
		imageLabel2 = new JLabel();
		Image pokemonBallPNG = new ImageIcon(this.getClass().getResource("ball.png")).getImage();
		imageLabel2.setIcon(new ImageIcon(pokemonBallPNG));
		//imageLabel2.setBounds(951,56,110,181);
		imageLabel2.setVisible(true);
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		//startButtonPanel.setBounds(459, 483, 140, 45);
		startButtonPanel.setBackground(Color.white);
		c.insets = new Insets(0,5,0,5);
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.anchor = GridBagConstraints.CENTER;


		startButton = new JButton("PLAY");
		startButtonPanel.add(startButton);
		startButton.setFont(new Font("Showcard Gothic",Font.BOLD,38));
		startButton.setBackground(Color.white);
		startButton.setForeground(Color.black);
		startButton.setFocusable(false);
		startButton.addActionListener(sHandler);
		con.add(startButtonPanel,c);
		//con.add(imageLabel2);
		

	
		//con.add(imageLabel1);

		
		
	
	}
	
	
	
	
	public void adjustButtonText()
	{

		for (CustomButton button : battlebuttons) {
			button.enableButton();
		}

		switch(battleManager.BattleState)
		{
		case 0:
			for (int j = 0 ; j < 4; j++ ) {
				battlebuttons[j].button.setText(battleManager.side1.get(battleManager.currentCharacter).Moveset[j].Name);
			}
			battlebuttons[4].button.setText("Back");
			break;
		case 1:
			switch(battleManager.actionTargetsEnemy)
			{
			case 1:
				for (int j = 0 ; j < 4; j++ ) 
				{
					if(battleManager.side2.size() <= j)
					{
						battlebuttons[j].button.setText("Noone");
						battlebuttons[j].disableButton();
					}
					else
					{
						battlebuttons[j].button.setText(battleManager.side2.get(j).Name);
					}
					battlebuttons[4].button.setText("Back");
				}
				break;
			case 0:
					battlebuttons[0].button.setText("All Allies");
					battlebuttons[1].disableButton();
					battlebuttons[2].disableButton();
					battlebuttons[3].disableButton();
					battlebuttons[4].button.setText("Back");
				break;
			case 2:
					battlebuttons[0].button.setText("All Enemies");
					battlebuttons[1].disableButton();
					battlebuttons[2].disableButton();
					battlebuttons[3].disableButton();
					battlebuttons[4].button.setText("Back");
					break;
			case -1:
				battlebuttons[0].button.setText("Self");
				battlebuttons[1].disableButton();
				battlebuttons[2].disableButton();
				battlebuttons[3].disableButton();
				battlebuttons[4].button.setText("Back");
				break;
			}
			break;
		default:
				for (int j = 0 ; j < 5; j++ ) {
					if(j < 4)
					{
						battlebuttons[j].disableButton();
						continue;
					}
					if((battleManager.state != turnState.defeated && battleManager.state != turnState.victorious) || !battleObjectGui.messages.isEmpty())
					{
					battlebuttons[j].button.setText("next");
					}
					else
					{
					battlebuttons[j].button.setText("end");
					battlebuttons[j].button.setEnabled(false);
					}
				}
			break;
		}
	}
	
	
	
	public void setupBattleScreen(ArrayList<Character> side1, ArrayList<Character> side2)
	{
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.weightx = 0.5;
		c2.weighty =  0.5;
		
		
		//battleContainer = imageLabel3;//burasını daha sonra tekrar yapmaya çalışıcam

		
		JLabel battlepanel = new JLabel();
		battlepanel.setLayout(new GridBagLayout());
		
		
		leftFightPanel = new JPanel();
		leftFightPanel.setLayout(new BoxLayout(leftFightPanel, BoxLayout.PAGE_AXIS));
		for (Character character : side1) {
			characterStatus status = new characterStatus();
			statuses.add(status);
			status.charSetup(character);
			leftFightPanel.add(status);
		}
		rightFightPanel = new JPanel();
		rightFightPanel.setLayout(new BoxLayout(rightFightPanel, BoxLayout.PAGE_AXIS));
		for(Character character2 : side2) {
			characterStatus status2 = new characterStatus();
				statuses.add(status2);
				status2.charSetup(character2);
				rightFightPanel.add(status2);
		}
		
		c2.anchor=GridBagConstraints.FIRST_LINE_START;
		c2.gridx = 0;
		c2.gridy = 0;
		c2.weightx = 0.5;
		c2.weighty = 0.5;
		c2.insets.top = 30;
		c2.insets.left = 0;

		battlepanel.add(leftFightPanel,c2);
		c2.anchor = GridBagConstraints.FIRST_LINE_END;
		c2.gridx = 1;
		c2.insets.right = 0;
		c2.insets.left = 0;
		battlepanel.add(rightFightPanel,c2);

		
		infoLabel = new JLabel("TestInput");
		infoLabel.setFont(new Font("Showcard Gothic",Font.PLAIN,20));
		infoLabel.setForeground(Color.black);
		infoPanel = new JPanel();
		infoPanel.setBackground(Color.white);
		infoPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		infoPanel.add(infoLabel);
		
		//c2.fill=GridBagConstraints.HORIZONTAL;
		c2.anchor=GridBagConstraints.LAST_LINE_START;

		c2.gridx = 0;
		c2.gridy = 1;
		c2.weightx = 0.5;
		c2.weighty = 0.2;
		c2.insets.left = 20;
		c2.insets.top = 25;

		battlepanel.add(infoPanel,c2);
		
		
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setOpaque(false);
		buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.X_AXIS));
		battleManager = new battleObjectGui(this);
		battlebuttons = new CustomButton[5];
		 for(int i = 0; i < 4; i++)
		 {
			 int a = i;
			 CustomButton button = new CustomButton();
			 button.initButton(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					battleManager.buttonPressed(a);
					adjustButtonText();
				}
			}) ;
			 battlebuttons[i]= button;
			 battlebuttons[i].setFont(new Font("Showcard Gothic",Font.PLAIN,15));
		 }
		 CustomButton button = new CustomButton();
		 button.initButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				battleManager.cancelMove();
				adjustButtonText();
			}
		}) ;
		 battlebuttons[4]= button;
		 battlebuttons[4].setFont(new Font("Showcard Gothic",Font.PLAIN,15));
		 for (CustomButton customButton : battlebuttons) {
			 buttonpanel.add(customButton);
		}
		 
			c2.anchor=GridBagConstraints.FIRST_LINE_START;
			c2.gridx = 0;
			c2.gridy = 2;
			c2.insets.left = 5;
			c2.weightx = 1;
			c2.gridwidth = 2;
			c2.weighty = 0.1;
			c2.insets.bottom = 30;
			
			battlepanel.add(buttonpanel,c2);
			
			imageLabel3 = new JLabel();
			Image arena = new ImageIcon(this.getClass().getResource("arena2.png")).getImage();
			battlepanel.setIcon(new ImageIcon(arena));
			//battlepanel.setHorizontalAlignment(SwingConstants.CENTER);
			
			
			battleManager.startBattle(side1, side2);
			adjustButtonText();
			window.setMinimumSize(new Dimension(1100, 700));
			window.setMaximumSize(window.getMinimumSize());
			window.setResizable(false);
			window.setPreferredSize(new Dimension(1100, 700));
			battlepanel.setPreferredSize(new Dimension(1100, 800));
			window.setContentPane(battlepanel);
		
		
		
		
		
		
		
		window.pack();
		window.setVisible(true);
		window.toFront();

		//yeni deneme sonu
	}
	
	
	public class ScreenHandlernew implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			
			Action[] casterActions = new Action[4];
			casterActions[0] = new Dekunda();
			casterActions[1] = new ChantOfDestruction();
			casterActions[2] = new DarkPulse();
			casterActions[3] = new EmotionalDamage();
			
			Action[] knightActions = new Action[4];
			knightActions[0] = new Dash();
			knightActions[1] = new Bruise();
			knightActions[2] = new ShieldBreak();
			knightActions[3] = new Rally();
			
			Action[] rangerActions = new Action[4]; 
			
			rangerActions[0] = new Beartrap();
			rangerActions[1] = new Volley();
			rangerActions[2] = new Awareness();
			rangerActions[3] = new PreciseShot();
			
			
			Action[] enchanterActions = new Action[4]; 
			enchanterActions[0] = new Protect();
			enchanterActions[1] = new WeightOfTheWorld();
			enchanterActions[2] = new Firebolt();
			enchanterActions[3] = new SongOfPeace();
			
			Action[] enemyActions = new Action[4];
			enemyActions[0] = new HealSpell();
			enemyActions[1] = new ChantOfDestruction();
			enemyActions[2] = new Dash();
			enemyActions[3] = new Firebolt();
			
			Action[] soulactions = new Action[4];
			soulactions[0] = new HealSpell();
			soulactions[1] = new HealSpell();
			soulactions[2] = new Firebolt();
			soulactions[3] = new EmotionalDamage();
			
			CharacterTemplate caster = new CharacterTemplate(0,10, 10, 10, 25, 20, 25);
			CharacterTemplate bruiser = new CharacterTemplate(1,30, 20, 15, 5, 5, 10);
			CharacterTemplate ranger = new CharacterTemplate(2,20, 15, 15, 5, 10, 5);
			CharacterTemplate enchanter = new CharacterTemplate(3, 10, 10, 10, 20, 10, 5);
			CharacterTemplate boss = new CharacterTemplate(-1,40, 10, 10, 10, 10, 10);
			CharacterTemplate lSoul = new CharacterTemplate(-2, 10, 10, 10, 20, 10, 5);
			Character char1 = new Character("Natalia, Bane of Cookies", 20,caster,new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("char1.png"))),new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("chardeath1.png"))));
			char1.setupMoves(casterActions);
			Character char2 = new Character("Gary The Magnificent", 20, bruiser,new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("char2.png"))),new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("chardeath2.png"))));
			char2.setupMoves(knightActions);
			
			Character char3 = new Character("Theoden The Not Knight",20, ranger, new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("char_4.png"))),new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("char4dead.png"))));
			char3.setupMoves(rangerActions);
		
			Character char4 = new Character("Clara, One Without Shoes",20, enchanter,new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("char3.png"))),new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("chardeath3.png"))));
			char4.setupMoves(enchanterActions);

			
			
			Character bossChar = new Character("Plum, God of Donuts",40, boss,new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("monster.png"))),new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("impdead.png"))));
			bossChar.setupMoves(enemyActions);

			Character  lSoul1 = new Character("Lost Soul",20, lSoul,new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("lostsoul.png"))),new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("lostsoulDead.png"))));
			lSoul1.setupMoves(soulactions);
			
			Character  lSoul2 = new Character("Lost Soul",20, lSoul,new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("lostsoul.png"))),new ImageIcon (Toolkit.getDefaultToolkit().getImage(guitestClass.class.getResource("lostsoulDead.png"))));
			lSoul2.setupMoves(soulactions);
			
			
			ArrayList<Character> team1 = new ArrayList<Character>();
			team1.add(char1);
			team1.add(char2);
			team1.add(char3);
			team1.add(char4);
			ArrayList<Character> team2 = new ArrayList<Character>();
			team2.add(lSoul1);
			team2.add(bossChar);
			team2.add(lSoul2);

			
			
			
			setupBattleScreen(team1,team2);
		}
		
		
		
	}
	
	
	
}
