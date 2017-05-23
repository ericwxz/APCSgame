import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

public class GUI extends JFrame implements ActionListener, KeyListener
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
	private Image hurtbplane;
	private Image hp;
	private Image gun;
	private int steps;
	private int timedDisplay;
	private boolean inMenu;
	private JButton start; private JButton exit; private JButton help;
	private Image arrowKeys;
	private Image spaceKey;
	private Image escapeKey;
	
	private Plane playerPlane;

	public GUI(World w)
	{
		super("aerial ace");
		myWorld = w;
		
		ClassLoader cldr = this.getClass().getClassLoader();
		
		Container container = super.getContentPane();
		container.setLayout(new BorderLayout());
		inMenu = true; 
		label = new JLabel("whippedee doo doo");
		layers = new JLayeredPane();
		container.add(layers);
		layers.add(label, JLayeredPane.DEFAULT_LAYER);
		
		ImageIcon planey = new ImageIcon(cldr.getResource("playerPlane.gif"));
		plane = planey.getImage();
		plane = plane.getScaledInstance(80,80,1);
		
		ImageIcon hurtplaney = new ImageIcon(cldr.getResource("planeDamage.gif"));
		hurtplane = hurtplaney.getImage();
		hurtplane = hurtplane.getScaledInstance(80,80,1);
		
		ImageIcon bplaney = new ImageIcon(cldr.getResource("enemyPlane.gif"));
		bplane = bplaney.getImage();
		bplane = bplane.getScaledInstance(80,80,1);
		
		ImageIcon hurtbplaney = new ImageIcon(cldr.getResource("enemyDamage.gif"));
		hurtbplane = hurtbplaney.getImage();
		hurtbplane = hurtbplane.getScaledInstance(80,80,1);
		
		ImageIcon bullety = new ImageIcon(cldr.getResource("playerBullet.gif"));
		bullet = bullety.getImage();
		bullet = bullet.getScaledInstance(50,50,1);
		
		ImageIcon bbullety = new ImageIcon(cldr.getResource("enemyBullet.gif"));
		bbullet = bbullety.getImage();
		bbullet = bbullet.getScaledInstance(50,50,1);
		
		ImageIcon exploy = new ImageIcon(cldr.getResource("explosion.gif"));
		explo = exploy.getImage();
		explo = explo.getScaledInstance(80,80,1);
		
		ImageIcon hpy = new ImageIcon(cldr.getResource("hpBar.gif"));
		hp = hpy.getImage();
		hp = hp.getScaledInstance(80,80,1);
		
		ImageIcon guny = new ImageIcon(cldr.getResource("weapon1.gif"));
		gun = guny.getImage();
		gun = gun.getScaledInstance(80,80,1);
		
		ImageIcon bgGif = new ImageIcon(cldr.getResource("backgroundImg.gif"));
		bg = bgGif.getImage();
		bg = bg.getScaledInstance(350,700,1);
		
		start = new JButton("Start Game"); 
		exit = new JButton("Exit Game"); 
		help = new JButton("How To Play"); 
		super.setContentPane(new JPanel() {public void paintComponent(Graphics g)
			{ super.paintComponent(g); g.drawImage(bg, 0, 0, this);}});
		super.add(start); super.add(help); super.add(exit); 
		start.setOpaque(true); help.setOpaque(true); exit.setOpaque(true); 
		
		playerPlane = myWorld.getPlayer();
		
		super.addKeyListener(this);
		MenuStartListener menuHandler = new MenuStartListener(this);
		start.addActionListener(menuHandler);
		help.addActionListener(menuHandler);
		exit.addActionListener(menuHandler);
		start.requestFocus(); 


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
		this.requestFocus();
	}
	public void paint(Graphics g)
	{
		if (inMenu == false)
		{
			Image offImage = createImage(350,700);
			Graphics buffer = offImage.getGraphics();
			paintBuffer(buffer, myWorld.getList());
			g.drawImage(offImage, 0, 0, null);
			g.drawString("SCORE: " + myWorld.getScore(), 50, 50);
		}
	}
	private void paintBuffer(Graphics g, ArrayList<Collidable> a)
	{
		g.clearRect(0, 0, 350, 700);
		super.paint(g);

		g.drawImage(bg, 0, 0, this);
		g.drawImage(hp, 5, 640, this);
		g.drawImage(gun, 262, 637, this);
		g.drawString("SCORE: " + myWorld.getScore(), 50, 50);
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
					Plane b = (Plane) c;
					switch(b.getImageState())
					{
						case 3:
							g.drawImage(bplane, c.getLat(), c.getLong(), this);
							break;
						case 0:
							if(timedDisplay >= 0)
							{
								b.setImage(0);
								timedDisplay--;
							}
							else
							{
								timedDisplay = 20;
								b.setImage(3);
							}
							g.drawImage(hurtbplane, c.getLat(), c.getLong(), this);
							break;
					}
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
		private JFrame myFrame;
		private String playerHelp;
		public MenuStartListener(JFrame frame)
		{
			myFrame = frame;
			playerHelp = "How To Play: " + "\n" + "Welcome to Aerial Ace! Your mission "
					+ "is to defeat as many enemy planes as possible. You have 5 lives"
					+ " initially, and every time you get hit by an enemy bullet, you"
					+ " lose 1 life. If you collide with an enemy plane... ouch!" +
					" That's 3 lives gone! When you are out of lives, your mission "
					+ "must be aborted. Good luck, soldier! We have faith in you."
					+ "\n" + "Use arrow keys to change your plane's position"
				    + "\n" + "Press space to fire" + "\n" +	"Press Esc to exit";
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == start) {
			inMenu = false;
			startGame();
			}
			else if (e.getSource() == help)
			{
				JOptionPane.showMessageDialog(myFrame, playerHelp);
			}
			else if (e.getSource() == exit) {
				escapeExit();
			}
		}

	}
	
    public void keyPressed(KeyEvent e)
    {
        //info from these methods needs to be passed to move method in plane
        //probably with if statement determining if plane is player plane
        playerPlane.clearMoveState();
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            playerPlane.setLeftMovement(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	playerPlane.setRightMovement(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
        	playerPlane.setUpwardsMovement(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	playerPlane.setDownwardsMovement(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	playerPlane.setShootState(true); 
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escapeExit();
        }
    }

    private void escapeExit()
    {
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(this, "Are you sure you want to exit?", "End game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (n == JOptionPane.YES_OPTION)
        {
        	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        //check if it works on layered pane?
    }

    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            playerPlane.setLeftMovement(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	playerPlane.setRightMovement(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
        	playerPlane.setUpwardsMovement(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	playerPlane.setDownwardsMovement(false);
        }
    }

    public void keyTyped(KeyEvent e) { }
	
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
	}

}






