import java.util.*;
import java.awt.*;
import java.awt.event.*;
 
import javax.swing.*;
import javax.swing.Timer;
 
 
public class GUI extends JFrame implements ActionListener, KeyListener
{
	private World myWorld;
 
	private Image bg;
	private Image go;
	private Image plane;
	private Image bplane;
	private Image tplane;
	private Image jplane;
	private Image behemoth;
	private Image lplane;
	private Image bullet;
	private Image bbullet;
	private Image brocket;
	private Image bplasma;
	private Image blaser;
	private Image explo;
	private Image bexplo;
	private Image hurtplane;
	private Image hurtbplane;
	private Image hp;
	private Image gun;
	private Image healthup;
	private int steps;
	private int timedDisplay;
	private int restartDelay;
	private boolean inMenu;
	private boolean inGameOver;
	private JButton start; private JButton exit; private JButton help;
	private Timer myTime;
	private Font font;
	private KPanel ppanel;
	private Plane playerPlane;
 
	
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
	}
	
	public GUI(World w)
	{
		super("aerial ace");
		myWorld = w;
		ClassLoader cldr = this.getClass().getClassLoader();
		
		Container container = super.getContentPane();
		container.setLayout(new BorderLayout());
		inMenu = true; 
		inGameOver = false;
		
		ImageIcon planey = new ImageIcon(cldr.getResource("playerPlane.gif"));
		plane = planey.getImage();
		plane = plane.getScaledInstance(80,80,1);
		
		ImageIcon hurtplaney = new ImageIcon(cldr.getResource("planeDamage.gif"));
		hurtplane = hurtplaney.getImage();
		hurtplane = hurtplane.getScaledInstance(80,80,1);
		
		ImageIcon bplaney = new ImageIcon(cldr.getResource("enemyPlane.gif"));
		bplane = bplaney.getImage();
		bplane = bplane.getScaledInstance(80,80,1);
		
		ImageIcon tplaney = new ImageIcon(cldr.getResource("trackingEnemy.gif"));
		tplane = tplaney.getImage();
		tplane = tplane.getScaledInstance(80,80,1);
		
		ImageIcon jplaney = new ImageIcon(cldr.getResource("jetEnemy.gif"));
		jplane = jplaney.getImage();
		jplane = jplane.getScaledInstance(80,80,1);
		
		ImageIcon splaney = new ImageIcon(cldr.getResource("behemothEnemy.gif"));
		behemoth = splaney.getImage();
		behemoth = behemoth.getScaledInstance(90,90,1);
		
		ImageIcon lplaney = new ImageIcon(cldr.getResource("laserEnemy.gif"));
		lplane = lplaney.getImage();
		lplane = lplane.getScaledInstance(80,80,1);
		
		ImageIcon hurtbplaney = new ImageIcon(cldr.getResource("enemyDamage.gif"));
		hurtbplane = hurtbplaney.getImage();
		hurtbplane = hurtbplane.getScaledInstance(80,80,1);
		
		ImageIcon bullety = new ImageIcon(cldr.getResource("playerBullet.gif"));
		bullet = bullety.getImage();
		bullet = bullet.getScaledInstance(50,50,1);
		
		ImageIcon bbullety = new ImageIcon(cldr.getResource("enemyBullet.gif"));
		bbullet = bbullety.getImage();
		bbullet = bbullet.getScaledInstance(50,50,1);
		
		ImageIcon brockety = new ImageIcon(cldr.getResource("enemyBomb.gif"));
		brocket = brockety.getImage();
		brocket = brocket.getScaledInstance(50,50,1);
		
		ImageIcon bplasmay = new ImageIcon(cldr.getResource("plasmaBullet.gif"));
		bplasma = bplasmay.getImage();
		bplasma = bplasma.getScaledInstance(50,50,1);
		
		ImageIcon blasery = new ImageIcon(cldr.getResource("laser.gif"));
		blaser = blasery.getImage();
		blaser = blaser.getScaledInstance(21, 1120, 1);
		
		ImageIcon exploy = new ImageIcon(cldr.getResource("explosion.gif"));
		explo = exploy.getImage();
		explo = explo.getScaledInstance(80,80,1);
		
		ImageIcon exp = new ImageIcon(cldr.getResource("bulletExplosion.gif"));
		bexplo = exp.getImage();
		bexplo = bexplo.getScaledInstance(50,50,1);
		
		ImageIcon hpy = new ImageIcon(cldr.getResource("hpBar.gif"));
		hp = hpy.getImage();
		hp = hp.getScaledInstance(80,80,1);
		
		ImageIcon guny = new ImageIcon(cldr.getResource("weapon1.gif"));
		gun = guny.getImage();
		gun = gun.getScaledInstance(80,80,1);
		
		ImageIcon healthy = new ImageIcon(cldr.getResource("hpPack.gif"));
		healthup = healthy.getImage();
		healthup = healthup.getScaledInstance(80, 80, 1);
		
		ImageIcon bgGif = new ImageIcon(cldr.getResource("backgroundImg.gif"));
		bg = bgGif.getImage();
		bg = bg.getScaledInstance(350,1050,1);
		
		ImageIcon goGif = new ImageIcon(cldr.getResource("winstonChurchill.gif"));
		go = goGif.getImage();
		go = go.getScaledInstance(350,700,1);
		
		start = new JButton("Start Game"); 
		exit = new JButton("Exit Game"); 
		help = new JButton("How To Play"); 
		ppanel = new KPanel();
		super.setContentPane(ppanel);
 
		super.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		super.add(Box.createRigidArea(new Dimension(0,280)));
		super.add(start); 
		super.add(Box.createVerticalGlue());
		super.add(help); 
		super.add(Box.createVerticalGlue());
		super.add(exit); 
		super.add(Box.createRigidArea(new Dimension(0,280)));
		
		
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		help.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		start.setOpaque(true); help.setOpaque(true); exit.setOpaque(true);
 
		playerPlane = myWorld.getPlayer();
		
		super.addKeyListener(this);
		MenuStartListener menuHandler = new MenuStartListener(this);
		start.addActionListener(menuHandler);
		help.addActionListener(menuHandler);
		exit.addActionListener(menuHandler);
 
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	super.setSize(350,700);
    	super.setVisible(true);
    	
		font = Font.getFont("prstartk.ttf");
	}
 
	public void showMenu()
	{
		inMenu = true;
		ppanel.setVisible(true);
		start.setVisible(true); 
		help.setVisible(true); 
  		exit.setVisible(true); 
		start.requestFocus();
	}
	
	public void startGame()
	{
		ppanel.setVisible(false);
		start.setVisible(false); 
		help.setVisible(false); 
  		exit.setVisible(false); 
		inMenu = false; 
		myTime = new javax.swing.Timer(40, this); 
		myTime.start(); 
		steps = 0; 
		this.requestFocus();
	}
	
	public void paint(Graphics g)
	{
		if (inMenu == true)
		{
			ppanel.paintComponent(g);
			
		}
		else if (inMenu == false && inGameOver == false)
		{
			g.setFont(font);
			Image offImage = createImage(350,700);
			Graphics buffer = offImage.getGraphics();
			paintBuffer(buffer, myWorld.getList());
			g.drawImage(offImage, 0, 0, null);
			g.drawString("SCORE: " + myWorld.getScore(), 50, 50);
			double distance = steps * 0.005;
			String formatTest = String.format("DISTANCE: %5.2f km", distance);
			g.drawString(formatTest, 50, 60);
		}
		else if (inGameOver == true)
		{
			g.drawImage(go, 0, 0, null);
		}
	}
	private void paintBuffer(Graphics g, ArrayList<Collidable> a)
	{
		g.clearRect(0, 0, 350, 700);
		super.paint(g);
 
		g.drawImage(bg, 0, -265 +(steps % 254), this);
		g.drawImage(hp, 5, 640, this);
		g.drawImage(gun, 262, 637, this);
		g.drawString("SCORE: " + myWorld.getScore(), 50, 50);
		double distance = steps * 0.005;
		String formatTest = String.format("DISTANCE: %5.2f km", distance);
		g.drawString(formatTest, 50, 60);
		int hpGone = (5-myWorld.getPlayer().getLife());
		g.fillRect(80 - hpGone * 10, 674, hpGone * 10, 10);
		
		for(Collidable c: a)
		{
			switch(c.getType())
			{
				case 0:	
					Explosion x = (Explosion) c;
					if(x.getStyle() == true)
						g.drawImage(explo, c.getLat(), c.getLong(), this);
					else
						g.drawImage(bexplo, c.getLat(), c.getLong(), this);
					break;
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
					g.drawImage(tplane, c.getLat(), c.getLong(), this);
					break;
				case 6:
					g.drawImage(brocket, c.getLat(), c.getLong(), this);
					break;
				case 7:
					g.drawImage(jplane, c.getLat(), c.getLong(), this);
					break;
				case 9:
					g.drawImage(behemoth, c.getLat(), c.getLong(), this);
					break;
				case 10:
					g.drawImage(bplasma, c.getLat(), c.getLong(), this);
					break;
				case 11:
					g.drawImage(lplane, c.getLat(), c.getLong(), this);
					break;
				case 12:
					g.drawImage(blaser, c.getLat(), c.getLong(), this);
					break;
				case 13:
					g.drawImage(healthup, c.getLat(), c.getLong(), this);
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
		if(steps % 60 == 0)
		{
			myWorld.spawnWave(steps);
		}
		if(steps * .005 % 2 == 0)
		{
			myWorld.harder();
		}
		if(myWorld.getGameOver() == true)
		{
			if(restartDelay == 0) { 
				inGameOver = true;
				myTime.restart();
				myTime.setDelay(1000);
			}
			restartDelay++;
			System.out.println("it's been " + restartDelay + " ticks");
			if (restartDelay == 5) {
				myTime.setDelay(40);
				confirmReplay();
			}
		}
		repaint();
	}
	
	private void confirmReplay()
	{
		Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(this, "Play again?", "Mission terminated", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (n == JOptionPane.YES_OPTION)
        {
        	
        	myTime.stop();
    		myWorld = new World();
    		playerPlane = myWorld.getPlayer();
    		inGameOver = false;
    		showMenu();
        }
        else
        	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	private class MenuStartListener implements ActionListener
	{
		private JFrame myFrame;
		private String playerHelp;
		public MenuStartListener(JFrame frame)
		{
			myFrame = frame;
			playerHelp = "How To Play: " + "\n" + "Welcome to Aerial Ace! Your mission "
					+ "is to defeat as many enemy planes as possible and minimize the number of enemy planes \n getting past you. \nYou have 5 lives"
					+ " initially, and every time you get hit by an enemy bullet, you"
					+ " lose 1 life. \nIf you collide with a regular enemy plane... ouch!" 
					+ " That's 3 lives gone! Bomber planes release tracking bombs that \n punch more than bullets, and occasional"
					+ " kamikaze pilots will explode you. When you are out of lives, \nyour mission "
					+ "must be aborted. If you run into a health drop, it will magically fix up your plane by 2 lifes."
					+ "\n \n We have gamified war by assigning worthless points for certain actions. For each \n enemy"
					+ "plane you destroy, you get +100 points. For each plane hat gets past your \n"
					+ "defenses and reaches mother base, you get -1000 points."
					+ "\n \n" + "Good luck, soldier! We have faith in you."
					+ "\n\n" + "Directions: \n" + "Change your plane's position with the arrow keys, hit space to fire," 
					+ " and press Esc to exit."
					+ "\n \n War is heck, kiddo.";
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == start) {
			inMenu = false;
			inGameOver = false;
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
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        	playerPlane.clearMoveState();
            playerPlane.setLeftMovement(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	playerPlane.clearMoveState();
        	playerPlane.setRightMovement(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
        	playerPlane.clearMoveState();
        	playerPlane.setUpwardsMovement(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	playerPlane.clearMoveState();
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
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	playerPlane.setShootState(false); 
        }
    }
 
    public void keyTyped(KeyEvent e) { }
    
    public class KPanel extends JPanel
    {
    	public void paintComponent(Graphics g)
		{ super.paintComponent(g); g.drawImage(bg, 0, 0, this);}
    }
 
}
 
 
 
 
 
 
 
 
