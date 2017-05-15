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
		list.add(new Plane(135,700,1,this,20));
		list.add(new Plane(135,100,3,this,0));
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
	
	public ArrayList<Collidable> act(int step)
	{
		ArrayList<Collidable> tempList = new ArrayList<Collidable>();
		for(Collidable c: list)
		{
			tempList.add(c);
		}
		
		for (Collidable c : tempList)
		{
			if(c.getType() == 3 && (step - c.getBirth()) % 40 == 0)
			{
				Plane plane = (Plane) c;
				plane.fire(step);
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
