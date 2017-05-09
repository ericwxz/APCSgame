import java.awt.*;

public class Collidable
{
	private Polygon hitBox;
	
	public Collidable(int xinit, int yinit, int type)
	{
		hitBox = new Polygon();
		switch(type)
		{
			// create hitbox based on entity
			case 1:
				hitBox.addPoint(xinit, yinit);
			break;
			case 2:
				hitBox.addPoint(xinit, yinit);
			break;
		}
	}
	
	public boolean compareTo(Collidable other)
	{
		
	}
	
	public void move(int x, int y)
	{
	}
}
