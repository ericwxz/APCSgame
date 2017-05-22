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
	private Image explo;
	private Image hurtplane;
	private Image hp;
	private Image gun;
	private int steps;
	private int timedDisplay;
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
		inMenu = true; 
		label = new JLabel("whippedee doo doo");
		layers = new JLayeredPane();
		container.add(layers);
		layers.add(label, JLayeredPane.DEFAULT_LAYER);
		
		ImageIcon planey = new ImageIcon("playerPlane.gif");
		plane = planey.getImage();
		plane = plane.getScaledInstance(80,80,1);
		
		ImageIcon hurtplaney = new ImageIcon("planeDamage.gif");
		hurtplane = hurtplaney.getImage();
		hurtplane = hurtplane.getScaledInstance(80,80,1);
		
		ImageIcon bplaney = new ImageIcon("enemyPlane.gif");
		bplane = bplaney.getImage();
		bplane = bplane.getScaledInstance(80,80,1);
		
		ImageIcon bullety = new ImageIcon("playerBullet.gif");
		bullet = bullety.getImage();
		bullet = bullet.getScaledInstance(50,50,1);
		
		ImageIcon bbullety = new ImageIcon("enemyBullet.gif");
		bbullet = bbullety.getImage();
		bbullet = bbullet.getScaledInstance(50,50,1);
		
		ImageIcon exploy = new ImageIcon("explosion.gif");
		explo = exploy.getImage();
		explo = explo.getScaledInstance(80,80,1);
		
		ImageIcon hpy = new ImageIcon("hpBar.gif");
		hp = hpy.getImage();
		hp = hp.getScaledInstance(80,80,1);
		
		ImageIcon guny = new ImageIcon("weapon1.gif");
		gun = guny.getImage();
		gun = gun.getScaledInstance(80,80,1);
		
		ImageIcon bgGif = new ImageIcon("backgroundImg.gif");
		bg = bgGif.getImage();
		bg = bg.getScaledInstance(350,700,1);
		
		start = new JButton("Start Game"); 
		exit = new JButton("Exit Game"); 
		help = new JButton("How To Play"); 
		super.setContentPane(new JPanel() {public void paintComponent(Graphics g)
			{ super.paintComponent(g); g.drawImage(bg, 0, 0, this);}});
		super.add(start); super.add(help); super.add(exit); 
		start.setOpaque(true); help.setOpaque(true); exit.setOpaque(true); 
		
		start.addActionListener(new MenuStartListener()); 
		help.requestFocus(); 

		
	
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	super.setSize(350,700);
    	super.setVisible(true);
    	

	}

	public void startGame()
	{
		start.setVisible(false); 
		help.setVisible(false); 
		exit.setVisible(false); 
		inMenu = false; 
		Timer timer = new javax.swing.Timer(40, this); 
		timer.start(); 
		steps = 0; 
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
		g.drawImage(hp, 5, 640, this);
		g.drawImage(gun, 262, 637, this);
		int hpGone = (5-myWorld.getPlayer().getLife());
		g.fillRect(80 - hpGone * 10, 674, hpGone * 10, 10);
		
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
		if(steps % 70 == 0)
		{
			myWorld.spawnWave(steps);
		}
		repaint();
		/*
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
			startGame();
		}
	}
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
	}

}
