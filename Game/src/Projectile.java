
public class Projectile extends Collidable
{
	private int damage;
	private boolean enemyBullet;
	
	public Projectile(int xinit, int yinit, int type, World world)
	{
		super (xinit, yinit, type, world);
		//type = 2 means friendly bullet, type = 4 means enemybullet
	}
	
	public int hitResult(Collidable other)
	{
		switch(other.getType())
		{
			case 1:
				if(enemyBullet)
				{
					Plane player = (Plane) other;
					player.hurt(getDamage());
					destroy();
				}
				break;
			case 2:
				if(enemyBullet)
				{
					Projectile playerProj = (Projectile) other;
					playerProj.destroy();
					destroy();
				}
				break;
			case 3:
				if(!enemyBullet)
				{
					Plane enemy = (Plane) other;
					enemy.hurt(getDamage());
					destroy();
				}
			case 4:
				if(!enemyBullet)
				{
					Projectile enemyProj = (Projectile) other;
					enemyProj.destroy();
					destroy();
				}
				break;
		}
		return 0;
	}
	
	public void destroy()
	{
		getWorld().removeEntity(this);
	}
	
	public int getDamage()
	{
		return damage;
	}

}
