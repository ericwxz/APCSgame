import java.awt.*;

public class Collidable
{
	private Polygon hitBox;
	private int myx;
	private int myy;
	private World myWorld;
	private int myType;
	
	public Collidable(int xinit, int yinit, int type, World world)
	{
		myx = xinit;
		myy = yinit;
		hitBox = new Polygon();
		myWorld = world;
		myType = type;
		switch(type)
		{
			// create hitbox based on entity
			case 1:
				hitBox.addPoint(myx-10, myy-10);
				hitBox.addPoint(myx-10, myy +10);
				hitBox.addPoint(myx+10, myy-10);
				hitBox.addPoint(myx+10,myy+10);
			break;
			case 2:
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
	public Rectangle getRekt()
	{
		return hitBox.getBounds();
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
}
