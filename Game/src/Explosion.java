
public class Explosion extends Collidable
{
	boolean isPlaneExplo;
	public Explosion(int initX, int initY, int type, World world, int born, boolean style)
	{
		super(initX,initY,type,world, born);
		isPlaneExplo = style;
	}
	public void move()
	{
		moveHelper(0,0);
	}
	public void setStyle(boolean plane)
	{
		isPlaneExplo = plane;
	}
	public boolean getStyle()
	{
		return isPlaneExplo;
	}
}
