
public class Powerup extends Collidable
{
	
	public Powerup(int xinit, int yinit, int powerType, World world, int tick)
	{
		//9 = invincibility, 11 = stronger bullets, 13 = add one life;
		super(xinit, yinit, powerType, world, tick);
	}
}
