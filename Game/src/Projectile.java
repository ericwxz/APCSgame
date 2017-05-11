
public class Projectile extends Collidable
{
	private int damage;
	private boolean enemyBullet;
	
	public Projectile(int xinit, int yinit, int type, World world, int livesLost, boolean enemyBullet)
	{
		//type = 2 means friendly bullet, type = 4 means enemybullet
		super (xinit, yinit, type, world);
		damage = livesLost;
		if (type == 4)
			enemyBullet = true;
		else
			enemyBullet = false;
	}
	 
	public 
	
	public void destroy()
	{
		getWorld().removeEntity()
	}
	
	public int getDamage()
	{
		return damage;
	}

}
