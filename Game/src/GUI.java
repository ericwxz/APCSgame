import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

public class GUI extends JFrame implements ActionListener
{
	private JLabel label;
	private JLayeredPane layers; //set db to true
	private World myWorld;
	private Image plane;
	private ImageIcon planey;
	private Image bullet;
	private ImageIcon bullety;
	private int steps;

	public GUI(World w)
	{
		super("ur mom");
		myWorld = w;

		Container container = super.getContentPane();
		container.setLayout(new BorderLayout());
		label = new JLabel("whippedee doo doo");
		layers = new JLayeredPane();
		container.add(layers);
		layers.add(label, JLayeredPane.DEFAULT_LAYER);
		container.add(new JButton("whatupp"), BorderLayout.SOUTH);
		planey = new ImageIcon("plane1.gif");
		plane = planey.getImage();
		plane = plane.getScaledInstance(100,100,1);
		
	
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	super.setSize(500,1000);
    	super.setVisible(true);
    	
    	Timer timer = new javax.swing.Timer(1000, this);    
    	timer.start(); 
    	steps = 0;
	}

	public void startGame()
	{
		
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(Color.red);
     	g.drawLine(5, 30, 350, 30);
     	
		for (Collidable c: myWorld.getList())
		{
			int type = c.getType();
			g.drawImage(plane, c.getLat(), c.getLong(), this);
		}
	}
	private void paintBuffer(ArrayList a){}
	
	public void actionPerformed(ActionEvent e)
	{

		steps++;
		System.out.println("it's been " + steps  + " steps");
		if(steps % 4 == 0)
			 repaint();

	}
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
	}

}
