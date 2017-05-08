
public class Powerup extends Collidable
{
	private int effect;
	
	public Powerup(int powers)
	{
		effect = powers;
	}
	
	public int collideEffect()
	{
		if (plane collided)
			return effect;
	}
}
