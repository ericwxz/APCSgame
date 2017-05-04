public class Collidable
{
	private int entWidth;
	private int entLength;
	
	private int[] xCoords;
	private int[] yCoords;
	private int type;
	
	public Collidable(int xinit, int yinit, int type)
	{
		xCoords = new int[4];
		yCoords = new int[4];
		switch(type)
		{
			case 1:
				xCoords[0] = xinit - entWidth / 2;
				yCoords[0] = yinit - entLength / 2;
				xCoords[1] = xinit + entWidth / 2;
				yCoords[1] = yinit - entLength / 2;
				xCoords[2] = xinit - entWidth / 2;
				yCoords[2] = yinit + entLength / 2;
				xCoords[3] = xinit + entWidth / 2;
				yCoords[3] = yinit + entLength / 2;
			break;
			case 2:
				xCoords[0] = xinit;
				yCoords[0] = yinit - entLength / 2;
				xCoords[1] = xinit + entWidth / 2;
				yCoords[1] = yinit;
				xCoords[2] = xinit - entWidth / 2;
				yCoords[2] = yinit;
				xCoords[3] = xinit;
				yCoords[3] = yinit + entLength / 2;
			break;
		}
	}
}