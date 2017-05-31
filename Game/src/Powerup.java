
public class Powerup extends Collidable
{
	
	public Powerup(int xinit, int yinit, int powerType, World world, int tick)
	{
		//14 = increased fire rate, 15 = stronger bullets, 13 = add one life;
		super(xinit, yinit, powerType, world, tick);
	}
	public void move()
	{
		super.moveHelper(0, 3);
	}
}
