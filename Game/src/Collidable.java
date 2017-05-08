public class Collidable
{
	private int[] xCoords;
	private int[] yCoords;
	private final int hitboxPts = 5;
	private int type;
	
	public Collidable(int xinit, int yinit, int type)
	{
		xCoords = new int[hitboxPts];
		yCoords = new int[hitboxPts];
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
}