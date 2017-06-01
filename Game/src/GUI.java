import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

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
	private Image dbullet;
	private Image blaser;
	private Image explo;
	private Image bexplo;
	private Image hurtplane;
	private Image hurtbplane;
	private Image hurttplane;
	private Image hurtjplane;
	private Image hurtlplane;
	private Image hurtbehemoth;
	private Image hp;
	private Image gun;
	private Image healthup;
	private Image fastfire;
	private Image damageup;
	private int steps;
	private int timedDisplay;
	private int restartDelay;
	private double distance;
	private boolean inMenu;
	private boolean inGameOver;
	private JButton start; private JButton exit; private JButton help;
	private Timer myTime;
	private Font font;
	
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
		myWorld.setGui(this);
		
		Container container = super.getContentPane();
		container.setLayout(new BorderLayout());
		inMenu = true; 
		inGameOver = false;
		
		InputStream stream = cldr.getResourceAsStream("prstartk.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(10f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setFont(font);
		
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
		
		ImageIcon hurttplaney = new ImageIcon(cldr.getResource("trackingHurt.gif"));
		hurttplane = hurttplaney.getImage();
		hurttplane = hurttplane.getScaledInstance(80,80,1);
		
		ImageIcon hurtjplaney = new ImageIcon(cldr.getResource("jetHurt.gif"));
		hurtjplane = hurtjplaney.getImage();
		hurtjplane = hurtjplane.getScaledInstance(80,80,1);
		
		ImageIcon hurtlplaney = new ImageIcon(cldr.getResource("laserHurt.gif"));
		hurtlplane = hurtlplaney.getImage();
		hurtlplane = hurtlplane.getScaledInstance(80,80,1);
		
		ImageIcon hurtbehemothy = new ImageIcon(cldr.getResource("behemothHurt.gif"));
		hurtbehemoth = hurtbehemothy.getImage();
		hurtbehemoth = hurtbehemoth.getScaledInstance(90,90,1);
		
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
		
		ImageIcon dbully = new ImageIcon(cldr.getResource("damageBullet.gif"));
		dbullet = dbully.getImage();
		dbullet = dbullet.getScaledInstance(50,50,1);
		
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
		
		ImageIcon firey = new ImageIcon(cldr.getResource("fireUp.gif"));
		fastfire = firey.getImage();
		fastfire = fastfire.getScaledInstance(80, 80, 1);
		
		ImageIcon hurty = new ImageIcon(cldr.getResource("damageUp.gif"));
		damageup = hurty.getImage();
		damageup = damageup.getScaledInstance(80, 80, 1);
		
		ImageIcon bgGif = new ImageIcon(cldr.getResource("backgroundImg.gif"));
		bg = bgGif.getImage();
		bg = bg.getScaledInstance(350,1050,1);
		
		ImageIcon goGif = new ImageIcon(cldr.getResource("winstonChurchill.gif"));
		go = goGif.getImage();
		go = go.getScaledInstance(350,700,1);
		
		start = new JButton("Start Game"); 
		exit = new JButton("Exit Game"); 
		help = new JButton("How To Play"); 
		
		super.setContentPane(new JPanel() {public void paintComponent(Graphics g)
			{ super.paintComponent(g); g.drawImage(bg, 0, 0, this);}});
 
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
		distance = 0;
 
		
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
  		super.setFont(font);
		inMenu = false; 
		myTime = new javax.swing.Timer(20, this); 
		myTime.start(); 
		steps = 0; 
		this.requestFocus();
	}
	
	public void paint(Graphics g)
	{
		
		if (inMenu == false && inGameOver == false)
		{
			g.setFont(font);
			Image offImage = createImage(350,700);
			Graphics buffer = offImage.getGraphics();
			paintBuffer(buffer, myWorld.getList());
			g.drawImage(offImage, 0, 0, null);
			g.drawString("SCORE: " + myWorld.getScore(), 20, 50);
			distance = steps * 0.005;
			String formatTest = String.format("DISTANCE: %5.2f km", distance);
			g.drawString(formatTest, 20, 60);
		}
		else if (inGameOver == true)
		{
			super.setFont(font.deriveFont(20f));
			Image offImage = createImage(350,700);
			Graphics buffer = offImage.getGraphics();
			paintBuffer(buffer, myWorld.getList());
			g.drawImage(go, 0, 0, null);
			g.drawString("Final Score:",65,75);
			g.drawString("" + myWorld.getScore(), 140, 95);
			g.drawString("Final Distance:",40,115);
			g.drawString(String.format("%5.2f", steps * .005) + "km", 100, 135);
			g.drawString("'We Shall",76,215);
			g.drawString("Never Surrender!'",10,235);
		}
	}
	private void paintBuffer(Graphics g, ArrayList<Collidable> a)
	{
		
		g.clearRect(0, 0, 350, 700);
		super.paint(g);
 
		if (inGameOver == true)
		{
			g.drawImage(go, 0, 0, null);
			super.setFont(super.getFont().deriveFont(20f));
			g.drawString("Final Score:",65,75);
			g.drawString(myWorld.getScore() + "", 130, 95);
			g.drawString("Final Distance:",40,115);
			g.drawString(myWorld.getScore() + "km", 120, 135);
		}
		else
		{
			g.drawImage(bg, 0, -265 +(steps % 254), this);
			g.drawString("SCORE: " + myWorld.getScore(), 20, 50);
			String formatTest = String.format("DISTANCE: %5.2f km", distance);
			g.drawString(formatTest, 20, 60);
			
			
			
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
						Plane t = (Plane) c;
						switch(t.getImageState())
						{
							case 3:
								g.drawImage(tplane, c.getLat(), c.getLong(), this);
								break;
							case 0:
								if(timedDisplay >= 0)
								{
									t.setImage(0);
									timedDisplay--;
								}
								else
								{
									timedDisplay = 20;
									t.setImage(3);
								}
								g.drawImage(hurttplane, c.getLat(), c.getLong(), this);
								break;
						}
						break;
					case 6:
						g.drawImage(brocket, c.getLat(), c.getLong(), this);
						break;
					case 7:
						Plane j = (Plane) c;
						switch(j.getImageState())
						{
							case 3:
								g.drawImage(jplane, c.getLat(), c.getLong(), this);
								break;
							case 0:
								if(timedDisplay >= 0)
								{
									j.setImage(0);
									timedDisplay--;
								}
								else
								{
									timedDisplay = 20;
									j.setImage(3);
								}
								g.drawImage(hurtjplane, c.getLat(), c.getLong(), this);
								break;
						}
						break;
					case 9:
						Plane z = (Plane) c;
						switch(z.getImageState())
						{
							case 3:
								g.drawImage(behemoth, c.getLat(), c.getLong(), this);
								break;
							case 0:
								if(timedDisplay >= 0)
								{
									z.setImage(0);
									timedDisplay--;
								}
								else
								{
									timedDisplay = 20;
									z.setImage(3);
								}
								g.drawImage(hurtbehemoth, c.getLat(), c.getLong(), this);
								break;
						}
						break;
					case 10:
						g.drawImage(bplasma, c.getLat(), c.getLong(), this);
						break;
					case 11:
						Plane l = (Plane) c;
						switch(l.getImageState())
						{
							case 3:
								g.drawImage(lplane, c.getLat(), c.getLong(), this);
								break;
							case 0:
								if(timedDisplay >= 0)
								{
									l.setImage(0);
									timedDisplay--;
								}
								else
								{
									timedDisplay = 20;
									l.setImage(3);
								}
								g.drawImage(hurtlplane, c.getLat(), c.getLong(), this);
								break;
						}
						break;
					case 12:
						g.drawImage(blaser, c.getLat(), c.getLong(), this);
						break;
					case 13:
						g.drawImage(healthup, c.getLat(), c.getLong(), this);
						break;
					case 14:
						g.drawImage(fastfire, c.getLat(), c.getLong(), this);
						break;
					case 15:
						g.drawImage(damageup, c.getLat(), c.getLong(), this);
						break;
					case 17:
						g.drawImage(dbullet, c.getLat(), c.getLong(), this);
						break;
					default:
				}
			} 
			g.drawImage(hp, 5, 640, this);
			g.drawImage(gun, 262, 637, this);
			int hpGone = (5-myWorld.getPlayer().getLife());
			g.fillRect(80 - hpGone * 10, 674, hpGone * 10, 10);
		}
	}
	
	public int getSteps()
	{
		return steps;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(myWorld.getGameOver() == true)
		{
			if(restartDelay == 0) {
				inGameOver = true;
				myWorld.setGameOver(true);
				myTime.restart();
				myTime.setDelay(1000);
			}
			restartDelay++;
			if (restartDelay == 5) {
				myTime.setDelay(40);
				confirmReplay();
			}
		}
		else
		{
			if (!myWorld.contains(0) && !myWorld.contains(1))
				myWorld.setGameOver(true);
			steps++;
			myWorld.move(steps);
			myWorld.act(steps);
			myWorld.cleanBounds(steps);
			if(steps % 60 == 0)
			{
				myWorld.spawnWave(steps);
			}
			if(steps * .005 % 2 == 0)
			{
				myWorld.harder();
			}
		}
		
		repaint();
	}
	
	private void confirmReplay()
	{
		Object[] options = {"I'm ready!", "Not yet..."};
        int n = JOptionPane.showOptionDialog(this, "Go on a new mission?", "End game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (n == JOptionPane.YES_OPTION)
        {
        	myTime.stop();
        	World ww = new World();
    		myWorld = ww;
    		ww.setGui(this);
    		playerPlane = myWorld.getPlayer();
    		restartDelay = 0;
    		inGameOver = false;
    		this.requestFocus();
    		super.setVisible(true);
    		startGame();
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
					+ "must be aborted. If you run into a health drop, it will magically fix up your plane by 2 life."
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
 
 
}
 
 
 
 
 
 
 
