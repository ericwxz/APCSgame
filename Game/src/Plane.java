public class Plane extends Collidable
{
	private int life;
	private int type;
	
	public Plane(int initX, int initY, int type)
	{
		super(initX,initY,type);
		life = 3;
	}
	
	public int hitResult(Collidable other)
	{
		switch(other.getType())
		{
			case 3:
				life--;
				break;
			case 4:
				life--;
				break;
			default:
		}
	}
}
