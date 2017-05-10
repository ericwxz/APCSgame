
public class Projectile extends Collidable
{
	private int damage;
	private boolean enemyBullet;
	
	public Projectile(int xinit, int yinit, int type)
	{
		super (xinit, yinit, type);
		//type = 2 means friendly bullet, type = 4 means enemybullet
	}
	
	public 
	
	public void destroy()
	{
		getWorld().removeEntity()
	}
	
	public int getDamage()
	{
		
	}

}
