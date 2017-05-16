import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Collidable
{
	private Polygon hitBox;
	private int myx;
	private int myy;
	private World myWorld;
	private int myType;
	private int birthTick;
	
	public Collidable(int xinit, int yinit, int type, World world, int tick)
	{
		myx = xinit;
		myy = yinit;
		hitBox = new Polygon();
		myWorld = world;
		myType = type;
		birthTick = tick;
		switch(type)
		{
			// create hitbox based on entity
			case 1:
				hitBox.addPoint(myx + 40, myy - 40);
				hitBox.addPoint(myx + 70, myy - 40);
				hitBox.addPoint(myx + 10, myy + 40);
				hitBox.addPoint(myx + 40, myy - 10);
				hitBox.addPoint(myx + 40, myy - 70);
			break;
			case 4:
				hitBox.addPoint(myx + 20, myy- 15);
				hitBox.addPoint(myx + 30, myy- 15);
				hitBox.addPoint(myx + 20, myy- 35);
				hitBox.addPoint(myx + 30, myy- 35);
			break;
			case 3:
				hitBox.addPoint(myx-10, myy-10);
				hitBox.addPoint(myx-10, myy +10);
				hitBox.addPoint(myx+10, myy-10);
				hitBox.addPoint(myx+10,myy+10);
			break;
		}
		
	}
	
	public boolean checkCollision(Collidable other)
	{
		if (hitBox.intersects(other.getRekt()))
			return true;
		return false;
		
	}
	public Rectangle2D getRekt()
	{
		return hitBox.getBounds2D();
	}
	
	public void hitResult(Collidable other)
	{}
	
	public void move()
	{
		moveHelper(0,0);
	}
	
	public void moveHelper(int x, int y)
	{
		for (int xx: hitBox.xpoints)
			xx+=x;
		for (int yy: hitBox.ypoints)
			yy+=y;
		myy += y;
		myx += x;
	}
	
	public int getType()
	{
		return myType;
	}
	
	public int getLat()
	{
		return myx;
	}
	
	public int getLong()
	{
		return myy;
	}
	
	public World getWorld()
	{
		return myWorld;
	}
	
	public int getBirth()
	{
		return birthTick;
	}
}
