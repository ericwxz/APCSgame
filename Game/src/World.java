import java.util.*;


public class World 
{
	private final int width = 500;
	private final int height = 1000;
	private ArrayList<Collidable> list;
	public World()
	{
		list = new ArrayList<Collidable>();
	}
	public void add(Collidable c)
	{
		list.add(c);
	}
	public void remove(Collidable c)
	{
		list.remove(c);
	}
	public ArrayList<Collidable> act()
	{
		for (Collidable c : list)
		{
			for (Collidable k : list)
				if (!c.equals(k))
				{
					if (c.compareTo(k))
					{
						c.act();
						k.act();
					}
				}
		}
		return list;
	}
	
}
