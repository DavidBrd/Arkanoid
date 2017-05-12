package david_nour.arcanoid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CustomLevel extends JFrame implements MouseListener, ActionListener, ItemListener{
	
	private JPanel mainPanel;
	private JPanel topPanel;
	private ArrayList bricksCustom;
	private int gameMode;
	private JRadioButton weakBrick;
	private JRadioButton resistantBrick;
	private JRadioButton veryResistantBrick;
	private int tapToDeath = 1;
	private int nbBricksCustom;
	
	public CustomLevel(int gameMode) {
		
		super("Editeur de niveau");
		this.setSize(800, 750);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.bricksCustom = new ArrayList<Brick>();
		this.gameMode = gameMode;
		this.nbBricksCustom = 0;
		
		JButton save = new JButton("Jouer le niveau");
		this.weakBrick = new JRadioButton("Brique fragile");
		this.resistantBrick = new JRadioButton("Brique résistante");
		this.veryResistantBrick = new JRadioButton("Brique très résistante");
		ButtonGroup brickType = new ButtonGroup();
		brickType.add(weakBrick);
		brickType.add(resistantBrick);
		brickType.add(veryResistantBrick);
		save.addActionListener(this);
		weakBrick.addItemListener(this);
		resistantBrick.addItemListener(this);
		veryResistantBrick.addItemListener(this);
		
		Container cp = this.getContentPane();
		JPanel mainPanel = new JPanel();
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.setLayout(new FlowLayout());
		topPanel.add(weakBrick);
		topPanel.add(resistantBrick);
		topPanel.add(veryResistantBrick);
		topPanel.add(save);
		
		this.mainPanel = mainPanel;
		this.topPanel = topPanel;
		
		cp = this.getContentPane();
		cp.add(mainPanel, BorderLayout.CENTER);
		cp.add(topPanel, BorderLayout.NORTH);
		
		mainPanel.addMouseListener(this);
		
		this.setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Graphics g = getGraphics();
		if(arg0.getX()+3 < this.mainPanel.getWidth() - Main.BRICK_WIDTH && arg0.getY()+68 < this.mainPanel.getHeight() - Main.BRICK_HEIGHT ) {
			Brick b = new Brick(arg0.getX()+3, arg0.getY()+68, this.tapToDeath);
			this.bricksCustom.add(b);
			this.nbBricksCustom++;
			System.out.println("[Création Briques] -> mouse x : " + arg0.getX() + " mouse y : " + arg0.getY());
			b.paintBrick(g);
		}		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(((JButton)arg0.getSource()).getText().equals("Jouer le niveau")) {
			this.setVisible(false);
			Level lvl = new Level(this.gameMode, this.bricksCustom, this.nbBricksCustom);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(this.weakBrick.isSelected()) {
			this.tapToDeath = 1;
		}
		else if(this.resistantBrick.isSelected()) {
			this.tapToDeath = 2;
		}
		else if(this.veryResistantBrick.isSelected()){
			this.tapToDeath = 3;
		}
	}
	
}
