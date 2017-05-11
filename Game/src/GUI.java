import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

public class GUI implements ActionListener
{
	private JLabel label;
	private JLayeredPane layers; //set db to true
	private World myWorld;
	private JFrame framey;
	private Image plane;
	private ImageIcon planey;
	private Image bullet;
	private ImageIcon bullety;

	public GUI(World w)
	{
		myWorld = w;
	}

	public void startGame()
	{
		
	}
	public void initFrame(JFrame j)
	{
		Container container = j.getContentPane();
		container.setLayout(new BorderLayout());
		label = new JLabel("whippedee doo doo");
		layers = new JLayeredPane();
		container.add(layers);
		layers.add(label, JLayeredPane.DEFAULT_LAYER);
		container.add(new JButton("shucks"), BorderLayout.SOUTH);
		planey = new ImageIcon("--C.png");
		plane = planey.getImage();
		
	
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	j.setSize(500,1000);
    	j.setVisible(true);
    	
    	Timer timer = new javax.swing.Timer(1000, this);    
    	timer.start(); 
	}
	public void paint(Graphics g)
	{
		framey.paint(g);
		for (Collidable c: myWorld.getList())
		{
			int type = c.getType();
			switch(type)
			{
			 case 1:
				 g.drawImage(plane, c.getLat(), c.getLong(), framey);
			}
		}
	}
	private void paintBuffer(ArrayList a){}
	public void actionPerformed(ActionEvent e)
	{
		framey.repaint();
	}
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
		JFrame f = new JFrame("Carrot carrots");
		gg.initFrame(f);
	}

}