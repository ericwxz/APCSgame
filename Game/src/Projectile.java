
public class Projectile extends Collidable
{
	private int damage;
	
	public Projectile(int xinit, int yinit, int type, int livesLost)
	{
		super (xinit, yinit, type);
		damage = livesLost;
	}
	
	public boolean enemyBullet()
	{
		boolean hit = false;
		if (it is enemy bullet)
			hit = true;
		return hit;
	}
	
	public void collideEffect()
	{
		if (collided)
			disappears;
	}

}
