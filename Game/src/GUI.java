import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI implements ActionListener
{
	private JLabel label;
	private JLayeredPane layers; //set db to true
	private World myWorld;
	private JFrame framey;

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
		layers.add(container, JLayeredPane.DEFAULT_LAYER);
		container.add(new JButton("shucks"), BorderLayout.SOUTH);
		
	}
	public void paint(ArrayList a){}
	private void paintBuffer(ArrayList a){}
	public void actionPerformed(ActionEvent e){}
	public static void main(String[] args)
	{
		World w = new World();
		GUI gg = new GUI(w);
		JFrame f = new JFrame("Carrot carrots");
		gg.initFrame(f);
	}

}