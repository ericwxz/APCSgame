
public class Powerup extends Collidable
{
	public Powerup(int xinit, int yinit, int type, World world, int tick)
	{
		//type 10 = health, type 11 = ?
		super(xinit, yinit, type, world, tick);
	}
	public void move()
	{
		super.moveHelper(0, 6);
	}
}
