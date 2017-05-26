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
	private boolean collided;
	
	public Collidable(int xinit, int yinit, int type, World world, int tick)
	{
		myx = xinit;
		myy = yinit;
		hitBox = new Polygon();
		myWorld = world;
		myType = type;
		birthTick = tick;
		collided = false;
		clonePoly();
		
	}
	
	public boolean checkCollision(Collidable other)
	{
		return getRekt().intersects(other.getRekt());
		
	}
	public Rectangle getRekt()
	{
		return hitBox.getBounds();
	}
	
	//what happens when things collide... implemented elsewhere
	public void hitResult(Collidable other)
	{}
	
	//easy-to-call method for world... implemented elsewhere
	public void move()
	{
		moveHelper(0,0);
	}
	
	//helpful, moves hitbox's center and bounds
	public void moveHelper(int x, int y)
	{
		myy += y;
		myx += x;
		hitBox = new Polygon();
		clonePoly();
	}
	
	//lets planes and bullets destroy each other, war is heck
	public void destroy()
	{
		getWorld().removeEntity(this);
	}
	
	public void setCollide(boolean coll)
	{
		collided = coll;
	}
	
	//create a new instance of hitbox around the new centre, how hitboxes 'move'
	public void clonePoly()
	{
		switch(getType())
		{
			// create hitbox based on entity type - 1&3&7 are plane sized boxes, 2&4&6 for projectiles
			case 1:
			case 3:
			case 5:
			case 7:
				hitBox.addPoint(myx + 65, myy + 15);
				hitBox.addPoint(myx + 15, myy + 65);
				hitBox.addPoint(myx + 65, myy + 65);
				hitBox.addPoint(myx + 15, myy + 15);
			break;
			case 4:
			case 2:
			case 6:
			case 10:
				hitBox.addPoint(myx + 45, myy + 15);
				hitBox.addPoint(myx + 15, myy + 15);
				hitBox.addPoint(myx + 45, myy + 45);
				hitBox.addPoint(myx + 15, myy + 45);
			break;
			case 9:
				hitBox.addPoint(myx + 90, myy + 10);
				hitBox.addPoint(myx + 10, myy + 10);
				hitBox.addPoint(myx + 90, myy + 90);
				hitBox.addPoint(myx + 10, myy + 90);
				break;
		}
	}
	
	public Polygon getHitB()
	{
		return hitBox;
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
	
	public boolean collided()
	{
		return collided;
	}
}
