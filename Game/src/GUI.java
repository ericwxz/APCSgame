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
	private Image bplane; private ImageIcon bplaney;
	private Image bullet; private ImageIcon bullety;
	private Image bbullet; private ImageIcon bbullety;
	private Image muzzflash; private ImageIcon muzzflashy;
	private int steps;

	public GUI(World w)
	{
		super("aerial ace");
		myWorld = w;

		Container container = super.getContentPane();
		container.setLayout(new BorderLayout());
		label = new JLabel("whippedee doo doo");
		layers = new JLayeredPane();
		container.add(layers);
		layers.add(label, JLayeredPane.DEFAULT_LAYER);
		container.add(new JButton("whatupp"), BorderLayout.SOUTH);
		
		planey = new ImageIcon("plane1 (1).gif");
		plane = planey.getImage();
		plane = plane.getScaledInstance(80,80,1);
		
		bplaney = new ImageIcon("baddie plane.gif");
		bplane = bplaney.getImage();
		bplane = bplane.getScaledInstance(80,80,1);
		
		bullety = new ImageIcon("1 bullet.gif");
		bullet = bullety.getImage();
		bullet = bullet.getScaledInstance(50,50,1);
		
		bbullety = new ImageIcon("baddie BULLET.gif");
		bbullet = bbullety.getImage();
		bbullet = bbullet.getScaledInstance(50,50,1);
		
		muzzflashy = new ImageIcon("muzzflash.gif");
		muzzflash = muzzflashy.getImage();
		muzzflash = muzzflash.getScaledInstance(75,75,1);
		
		bgGif = new ImageIcon("i love clouds.gif");
		bg = bgGif.getImage();
		bg = bg.getScaledInstance(350,700,1);
		
	
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	super.setSize(350,700);
    	super.setVisible(true);
    	
    	Timer timer = new javax.swing.Timer(40, this);    
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
		paintBuffer(buffer, myWorld.getList());
		g.drawImage(offImage, 0, 0, null);	
	}
	private void paintBuffer(Graphics g, ArrayList<Collidable> a)
	{
		g.clearRect(0, 0, 350, 700);
		super.paint(g);

		g.drawImage(bg, 0, 0, this);
		for(Collidable c: a)
		{
			switch(c.getType())
			{
				case 1: 
					g.drawImage(plane, c.getLat(), c.getLong(), this);
					break;
				case 2:
					g.drawImage(bullet, c.getLat(), c.getLong(), this);
					break;
				case 3:
					g.drawImage(bplane, c.getLat(), c.getLong(), this);
					break;
				case 4:
					g.drawImage(bbullet, c.getLat(), c.getLong(), this);
					break;
				default:
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		steps++;
		myWorld.move();
		myWorld.act(steps);
		if(steps % 40 == 0)
		{
			myWorld.cleanBounds();
			System.out.println("it's been " + steps + " ticks");
		}
		repaint();

	}
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
	}

}
