import java.util.*;


public class World 
{
	private final int MAX_WIDTH = 500;
	private final int MAX_HEIGHT = 1000;
	private ArrayList<Collidable> list;
	private GUI myG;
	public World()
	{
		list = new ArrayList<Collidable>();
		list.add(new Plane(135,625,1,this));
		list.add(new Plane(95,650,1,this));
		list.add(new Plane(190,540,1,this));
	}
	public void setGui(GUI g)
	{
		myG = g;
	}
	public void add(Collidable c)
	{
		list.add(c);
	}
	public void removeEntity(Collidable c)
	{
		for (int n = 0; n < list.size(); n++)
		{
			if (list.get(n) == c)
				list.remove(n);
		}
	}
	public void cleanBounds()
	{
		ArrayList<Collidable> tempList = new ArrayList<Collidable>();
		for(Collidable c: list)
		{
			tempList.add(c);
		}
		for (Collidable c : tempList)
		{
			if(!isValid(c))
			{
				removeEntity(c);
			}
		}
	}
	
	public ArrayList<Collidable> move()
	{
		for (Collidable c : list)
		{
			c.move();
			for (Collidable k : list)
				if (!c.equals(k))
				{
					if (c.checkCollision(k))
					{
						c.hitResult(k);
					}
				}
		}
		return list;
	}
	
	public ArrayList<Collidable> act()
	{
		ArrayList<Collidable> tempList = new ArrayList<Collidable>();
		for(Collidable c: list)
		{
			tempList.add(c);
		}
		
		for (Collidable c : tempList)
		{
			if(c.getType() == 1)
			{
				Plane plane = (Plane) c;
				plane.fire();
			}
		}
		return list;
	}
	
	private boolean isValid(Collidable c)
	{
		return (-40 <= c.getLong() && c.getLong() <= 740 && -40 <= c.getLat() && c.getLat() <= 390);
	}
	public ArrayList<Collidable> getList()
	{
		return list;
	}
	
}
