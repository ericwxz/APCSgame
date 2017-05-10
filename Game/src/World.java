import java.util.*;


public class World 
{
	private final int maxWidth = 500;
	private final int maxHeight = 1000;
	private ArrayList<Collidable> list;
	private GUI myG;
	public World(GUI g)
	{
		list = new ArrayList<Collidable>();
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
	public ArrayList<Collidable> act()
	{
		for (Collidable c : list)
		{
			for (Collidable k : list)
				if (!c.equals(k))
				{
					if (c.checkCollision(k))
					{
						int whatHappened = c.hitResult(k);
						myG.updateState(whatHappened);
					}
				}
		}
		return list;
	}
	private boolean isValid(Collidable c)
	{
		return (0 <= c.getLong() && c.getLong() <= 1000 && 0 <= c.getLat() && c.getLat() <= 500);
	}
	
}
