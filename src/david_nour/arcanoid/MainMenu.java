package david_nour.arcanoid;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainMenu extends JFrame implements ActionListener, ItemListener{
	private int selectedGameMode;
	
	
	public MainMenu() {
		super("Arkanoid");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(new FlowLayout());
		
		JButton play = new JButton("Jouer");
		play.addActionListener(this);
		JRadioButton onePlayer = new JRadioButton("1 Joueur");
		JRadioButton twoPlayer = new JRadioButton("2 Joueurs");
		onePlayer.isSelected();
		onePlayer.addItemListener(this);
		twoPlayer.addItemListener(this);
		ButtonGroup gameMode = new ButtonGroup();
		gameMode.add(onePlayer);
		gameMode.add(twoPlayer);
		JComboBox levelChoice = new JComboBox();
		levelChoice.addItem("Niveau 1");
		levelChoice.addItem("Niveau 2");
		levelChoice.addItem("Personalisé NYI");
		
		JLabel logo = new JLabel();
		ImageIcon icon = new ImageIcon("img/logo.png");
		logo.setIcon(icon);
		
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(onePlayer);
		topPanel.add(twoPlayer);
		topPanel.add(levelChoice);
		topPanel.add(play);
		
		Container cp = getContentPane();
		cp.add(topPanel, BorderLayout.NORTH);
		cp.add(logo, BorderLayout.CENTER);
			
		setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//this.setVisible(false);
		this.dispose();
		Level lvl = new Level(1,2);
		
	}



	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
