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
	private Image hurtplane; private ImageIcon hurtplaney;
	private Image bplane; private ImageIcon bplaney;
	private Image bullet; private ImageIcon bullety;
	private Image bbullet; private ImageIcon bbullety;
	private Image explo; private ImageIcon exploy;
	private int steps;
	private int timedDisplay;
	private boolean inMenu;
	private JButton start; private JButton exit; private JButton help;

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
		
		hurtplaney = new ImageIcon("ouch (3).gif");
		hurtplane = hurtplaney.getImage();
		hurtplane = hurtplane.getScaledInstance(80,80,1);
		
		bplaney = new ImageIcon("baddie plane.gif");
		bplane = bplaney.getImage();
		bplane = bplane.getScaledInstance(80,80,1);
		
		bullety = new ImageIcon("1 bullet.gif");
		bullet = bullety.getImage();
		bullet = bullet.getScaledInstance(50,50,1);
		
		bbullety = new ImageIcon("baddie BULLET.gif");
		bbullet = bbullety.getImage();
		bbullet = bbullet.getScaledInstance(50,50,1);
		
		exploy = new ImageIcon("explosion (1).gif");
		explo = exploy.getImage();
		explo = explo.getScaledInstance(80,80,1);
		
		bgGif = new ImageIcon("i love clouds.gif");
		bg = bgGif.getImage();
		bg = bg.getScaledInstance(350,700,1);
		
	
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	super.setSize(350,700);
    	super.setVisible(true);
    	
    	Timer timer = new javax.swing.Timer(40, this);    
    	timer.start(); 
    	steps = 0;
    	timedDisplay = 20;
	}

	public void startGame()
	{
		//check to see if menu is clicked
		inMenu = true;
		Container menu = super.getContentPane();
		menu.setLayout(new BoxLayout(menu, 3));
		start = new JButton("Start Game");
		exit = new JButton("Exit Game");
		help = new JButton("How To Play");
		menu.add(start);
		menu.add(exit);
		menu.add(help);
		start.addActionListener(this);
		help.requestFocus();
		//if (start is clicked)
		//	planeLabel.requestFocusInWindow()
	}
	public void paint(Graphics g)
	{
		if (inMenu == false)
		{
			Image offImage = createImage(350,700);
			Graphics buffer = offImage.getGraphics();
			paintBuffer(buffer, myWorld.getList());
			g.drawImage(offImage, 0, 0, null);	
		}
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
					Plane p = (Plane) c;
					switch(p.getImageState())
					{
						case 3:
							g.drawImage(plane, c.getLat(), c.getLong(), this);
							break;
						case 0:
							if(timedDisplay >= 0)
							{
								p.setImage(0);
								timedDisplay--;
							}
							else
							{
								timedDisplay = 20;
								p.setImage(3);
							}
							g.drawImage(hurtplane, c.getLat(), c.getLong(), this);
							break;
					}
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
				case 5:
					g.drawImage(explo, c.getLat(), c.getLong(), this);
					break;
				default:
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		steps++;
		myWorld.move(steps);
		myWorld.act(steps);
		myWorld.cleanBounds(steps);
		if(steps % 50 == 0)
		{
			System.out.println("it's been " + steps + " ticks");
		}
		repaint();
		//detect which button is cliked in the menu
//		if (start.isSelected())
//		{
//			
//		}
//		else if (help.isSelected())
//		{
//			
//		}
//		else
//		{
//			
//		}
	}
	private class MenuStartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			inMenu = false;
		}
	}
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
	}

}
