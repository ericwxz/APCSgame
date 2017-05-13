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
	private Image bg; private ImageIcon bgGif;
	private Image plane; private ImageIcon planey;
	private Image bullet; private ImageIcon bullety;
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
		plane = plane.getScaledInstance(80,80,1);
		
		bullety = new ImageIcon("1 bullet.gif");
		bullet = bullety.getImage();
		bullet = bullet.getScaledInstance(50,50,1);
		
		bgGif = new ImageIcon("i love clouds.gif");
		bg = bgGif.getImage();
		bg = bg.getScaledInstance(350,700,1);
		
	
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	super.setSize(350,700);
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
		Image offImage = createImage(350,700);
		Graphics buffer = offImage.getGraphics();
		paintBuffer(buffer, myWorld.act());
		g.drawImage(offImage, 0, 0, null);	
	}
	private void paintBuffer(Graphics g, ArrayList a)
	{
		g.clearRect(0, 0, 350, 700);
		
		super.paint(g);
		
		g.drawImage(bg, 0, 0, this);
		for(Collidable c: myWorld.act())
		{
			switch(c.getType())
			{
				case 1: 
					g.drawImage(plane, c.getLat(), c.getLong(), this);
					break;
				case 2:
					g.drawImage(plane, c.getLat(), c.getLong(), this);
					break;
				case 3:
					g.drawImage(bullet, c.getLat(), c.getLong(), this);
					break;
				case 4:
					g.drawImage(bullet, c.getLat(), c.getLong(), this);
					break;
				default:
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{

		steps++;
		System.out.println("it's been " + steps + "ticks");
		if(steps % 4 == 0)
			 repaint();

	}
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
	}

}
