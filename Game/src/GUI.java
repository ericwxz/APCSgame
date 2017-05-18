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

	private Image bg;
	private Image plane;
	private Image bplane;
	private Image bullet;
	private Image bbullet;
	private Image muzzflash;
	private Image explo;

	private int steps;
	private boolean inMenu;
	private JButton start; private JButton exit; private JButton help;
	private JTextArea howToPlay;
	private Image arrowKeys;
	private Image spaceKey;
	private Image escapeKey;

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
		
		ImageIcon planey = new ImageIcon("plane1 (1).gif");
		plane = planey.getImage();
		plane = plane.getScaledInstance(80,80,1);
		
		ImageIcon bplaney = new ImageIcon("baddie plane.gif");
		bplane = bplaney.getImage();
		bplane = bplane.getScaledInstance(80,80,1);
		
		ImageIcon bullety = new ImageIcon("1 bullet.gif");
		bullet = bullety.getImage();
		bullet = bullet.getScaledInstance(50,50,1);
		
		ImageIcon bbullety = new ImageIcon("baddie BULLET.gif");
		bbullet = bbullety.getImage();
		bbullet = bbullet.getScaledInstance(50,50,1);
		
		ImageIcon exploy = new ImageIcon("explosion (1).gif");
		explo = exploy.getImage();
		explo = explo.getScaledInstance(100,100,1);
		
		ImageIcon bgGif = new ImageIcon("i love clouds.gif");
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
		//check to see if menu is selected
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
/*		if(steps % 50 == 0)
		{
			System.out.println("it's been " + steps + " ticks");
		}
		repaint();
		//detect which button is cliked in the menu
		if (start.isSelected())
		{
			planeLabel.requestFocusInWindow();
		}
		else if (help.isSelected())
		{
			Container directions = super.getContentPane();

			howToPlay = new JTextArea(100,50);
			
			ImageIcon arrowIcon = new ImageIcon("i love clouds.gif");
			arrowKeys = arrowIcon.getImage();
			arrowKeys = arrowKeys.getScaledInstance(100,100,1);
			
			ImageIcon spaceIcon = new ImageIcon("i love clouds.gif");
			spaceKey = spaceIcon.getImage();
			spaceKey = spaceKey.getScaledInstance(100,100,1);

			ImageIcon escapeIcon = new ImageIcon("i love clouds.gif");
			escapeKey = escapeIcon.getImage();
			escapeKey = escapeKey.getScaledInstance(100,100,1);

			directions.add(howToPlay);

			String playerHelp = "How To Play: " + "\n" + "Welcome to Aerial Ace! Your mission "
								+ "is to defeat as many enemy planes as possible. You have 5 lives"
								+ " initially, and every time you get hit by an enemy bullet, you"
								+ " lose 1 life. If you collide with an enemy plane... ouch!" +
								" That's 3 lives gone! When you are out of lives, your mission "
								+ "must be aborted. Good luck, soldier! We have faith in you."
								+ "\n" + "Use arrow keys to change your plane's position"
							    + "\n" + "Press space to fire" + "\n" +	"Press Esc to exit";
								
			howToPlay.setText(playerHelp);
		}
		else
		{
			System.exit(0);
		}*/
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
