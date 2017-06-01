import java.util.*;


public class World 
{
	private final int MAX_WIDTH = 500;
	private final int MAX_HEIGHT = 1000;
	private ArrayList<Collidable> list;
	private GUI myG;
	private Plane player;
	private int score;
	private boolean inGameOver;
	private int difficulty;
	public World()
	{
		list = new ArrayList<Collidable>();
		player = new Plane(135,500,1,this,20);
		list.add(player);
		score = 0;
		difficulty = 1;
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
	
	public void harder()
	{
		difficulty++;
	}
	
	public void spawnWave(int step)
	{
		int enemies = (int)(Math.random()*(1+ difficulty)) + (difficulty +1)/ 2;
		for(int k = 0; k < enemies; k++)
		{
			int j = (int) (Math.random() * 25);
			switch (j)
			{
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					add(new Plane(15 + (int) (Math.random() * 250), -39, 3, this, step));
					break; 
				case 7:
				case 8:
				case 9:
				case 10:
					if(difficulty > 1)
						add(new Plane(15 + (int)(250*Math.random()), -39, 5, this, step));
					else
						add(new Plane(15 + (int)(250*Math.random()),-39,3,this,step));
					break;
				case 11:
				case 12:
					if(difficulty > 2)
						add(new Plane(15 + (int)(250*Math.random()), -39, 7, this, step));
					else if(difficulty > 1)
						add(new Plane(15 + (int)(250*Math.random()), -39, 5, this, step));
					else
						add(new Plane(15 + (int)(250*Math.random()),-39,3,this,step));
					break;
				case 13:
					if(difficulty > 3 && !contains(9))
						add(new Plane(15 + (int)(250*Math.random()), -39, 9, this, step));
					else if(difficulty > 2)
						add(new Plane(15 + (int)(250*Math.random()), -39, 7, this, step));
					else if(difficulty > 1)
						add(new Plane(15 + (int)(250*Math.random()), -39, 5, this, step));
					else
						add(new Plane(15 + (int)(250*Math.random()),-39,3,this,step));
					break;
				case 14:
					if(difficulty > 3)
						add(new Plane(15 + (int)(250*Math.random()), -39, 11, this, step));
					else if(difficulty > 2)
						add(new Plane(15 + (int)(250*Math.random()), -39, 7, this, step));
					else if(difficulty > 1)
						add(new Plane(15 + (int)(250*Math.random()), -39, 5, this, step));
					else
						add(new Plane(15 + (int)(250*Math.random()),-39,3,this,step));
					break;
				case 15:
				case 16:
					if(player.getLife() < 5 && !contains(13) && !contains(14) && !contains(15))
						add(new Powerup(15 + (int) (Math.random() * 250), -39, 13, this, step));
					else if (difficulty > 1)
						add(new Plane(player.getLat(), -39, 7, this, step));
					else{}
					break;
				case 17:
				case 18:
				case 19:
				case 20:
					if(difficulty > 1 && !contains(13) && !contains(14) && !contains(15) && !player.hasPower())
						add(new Powerup(15 + (int) (Math.random() * 250), -39, 14 + (int)(Math.random() * 2), this, step));
					break;
				case 0:
				
			}
		}
	}
	
	//collidables outside of the frame are banished forver into cyberspace. also dead planes
	public void cleanBounds(int steps)
	{
		ArrayList<Collidable> tempList = new ArrayList<Collidable>();
		for(Collidable c: list)
		{
			tempList.add(c);
		}
		for (Collidable c : tempList)
		{
			if(!isValid(c) && c.getType() != 0)
			{
					removeEntity(c);
					list.add(new Explosion(c.getLat(), c.getLong(), 0, this, steps-1, false));
			}
			else if(c.getType() == 1 || c.getType() == 3 || c.getType() == 5 || c.getType() == 7 || 
					c.getType() == 9 || c.getType() == 11)
			{
				Plane pl = (Plane) c;
				if(pl.getLife() <= 0)
				{
					pl.destroy();
					list.add(new Explosion(c.getLat(), c.getLong(), 0, this, steps-1, true));
				}
			}
			else if(c.getType() == 12 && (steps - c.getBirth()) % 10  == 0)
			{
				removeEntity(c);
			}
			else if(c.getType() == 0 && (steps - c.getBirth()) % 20  == 0)
			{
				removeEntity(c);
			}
			if(!contains(1) && !contains(2))
			{
				setGameOver(true);
			}
		}
	}
	
	//everybody jives and then we see if anybodys touching
	public ArrayList<Collidable> move(int steps)
	{
		ArrayList<Collidable> tempList = new ArrayList<Collidable>();
		for(Collidable c: list)
		{ 
			tempList.add(c);
			c.setCollide(false);
		}
		for (Collidable c : tempList)
		{
			c.move();
			for (Collidable k : tempList)
					if (c.checkCollision(k) && c != k && !k.collided())
					{
						c.setCollide(c.hitResult(k));
					}
		}
		return list;
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
			if (c.getType() == 1)
			{
				if (player.getShootState() == true)
				{
					player.fire(step);
				}
				
			}
			else if ((c.getType() == 3) || c.getType() == 5 || c.getType() == 7 
					|| c.getType() == 9 || c.getType() == 11)
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
	
	public Plane getPlayer()
	{
		return player;
	}
	public GUI getGui()
	{
		return myG;
	}
	public int getScore()
	{
		return score;
	}
	public void addScore(int addy)
	{
		score += addy;
	}
	
	public boolean getGameOver()
	{
		return inGameOver;
	}
	
	public void setGameOver(boolean bool)
	{
		inGameOver = bool;
	}
	
	public boolean contains(int c)
	{
		for(Collidable d : list)
		{
			if(d.getType() == c)
				return true;
		}
		return false;
	}
	

}

