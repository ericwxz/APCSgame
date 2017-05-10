import java.awt.*;

public class Collidable
{
	private Polygon hitBox;
	private int myx;
	private int myy;
	public Collidable(int xinit, int yinit, int type)
	{
		myx = xinit;
		myy = yinit;
		hitBox = new Polygon();
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
	public void move(int x, int y)
	{
		for (int xx: hitBox.xpoints)
			xx+=x;
		for (int yy: hitBox.ypoints)
			yy+=y;
	}
	public int getType()
	{
		return 0;
	}
}
