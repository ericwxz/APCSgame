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
		list.add(new Plane(70,50,3,this,0));
		list.add(new Plane(200,50,3,this,0));
		
	}
	
	public void setGui(GUI g)
	{
		myG = g;
	}
	
	public void add(Collidable c)
	{
		list.add(c);
	}
	
	//removes the exact instance of Collidable c from the world... nothin personnel kid
	public void removeEntity(Collidable c)
	{
		for (int n = 0; n < list.size(); n++)
		{
			if (list.get(n) == c)
				list.remove(n);
		}
	}
	
	//collidables outside of the frame are banished forver into cyberspace
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
	
	//everybody jives and then we see if anybodys touching
	public ArrayList<Collidable> move()
	{
		ArrayList<Collidable> tempList = new ArrayList<Collidable>();
		for(Collidable c: list)
		{
			tempList.add(c);
		}
		
		for (Collidable c : tempList)
		{
			c.move();
			for (Collidable k : tempList)
					if (c.checkCollision(k) && c != k)
					{
						c.hitResult(k);
					}
		}
		return list;
	}
	
	//we banish all the dead things on the field (mostly planes) bye bye
	public void sweepField()
	{
		
	}
	
	//all the enemy planes fire at a 160ms interval from their time of birth
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
	
	//whos out of the screen completely?? check those bounds
	private boolean isValid(Collidable c)
	{
		return (-40 <= c.getLong() && c.getLong() <= 740 && -40 <= c.getLat() && c.getLat() <= 390);
	}
	
	
	public ArrayList<Collidable> getList()
	{
		return list;
	}
	
}
