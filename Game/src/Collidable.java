public class Collidable
{
	private int[] xCoords;
	private int[] yCoords;
	private final int hitboxPts = 7;
	private int type;
	
	public Collidable(int xinit, int yinit, int type)
	{
		xCoords = new int[hitboxPts];
		yCoords = new int[hitboxPts];
		
		switch(type)
		{
			case 1:
				xCoords[0] = xinit; yCoords[0] = yinit;
				xCoords[1] = xinit; yCoords[1] = yinit + 50;
				xCoords[2] = xinit; yCoords[2] = yinit - 50;
				xCoords[3] = xinit + 50; yCoords[3] = yinit;
				xCoords[4] = xinit - 50; yCoords[4] = yinit;
				xCoords[5] = xinit + 25; yCoords[5] = yinit;
				xCoords[6] = xinit - 25; yCoords[6] = yinit;
			break;
			case 1:
				xCoords[0] = xinit; yCoords[0] = yinit;
				xCoords[1] = xinit; yCoords[1] = yinit + 50;
				xCoords[2] = xinit; yCoords[2] = yinit - 50;
				xCoords[3] = xinit + 50; yCoords[3] = yinit;
				xCoords[4] = xinit - 50; yCoords[4] = yinit;
				xCoords[5] = xinit + 25; yCoords[5] = yinit;
				xCoords[6] = xinit - 25; yCoords[6] = yinit;
			break;
				
		}
	}
	
	public boolean compareTo(Collidable other)
	{
		for(int k = 0; k < hitboxPts; k++)
		{
			for(int j = 0; j < hitboxPts; j++)
			{
				if(xCoords[k] == other.xCoords[k] && yCoords[k] == other.yCoords[k])
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void move(int x, int y)
	{
		xCoords[0] += x;
		yCoords[0] += y;
	}
}
