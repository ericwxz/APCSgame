
public class Projectile extends Collidable
{
	private int damage;
	private boolean enemyBullet;

	public Projectile(int xinit, int yinit, int type, World world, int livesLost, int tick)
	{

		//type = 2 means friendly bullet, type = 4 means enemybullet
		super (xinit, yinit, type, world, tick);

		damage = livesLost;
		if (type == 4)
			enemyBullet = true;
		else
			enemyBullet = false;
	}
	
	public void hitResult(Collidable other)
	{
		//type 1 = friendly projectile, 2 = friendly bullet, 3 = enemy projectile, 4 = enemy bullet
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
	}

	public void destroy()
	{
		getWorld().removeEntity(this);
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void move()
	{
		if(this.getType() == 4)
			super.moveHelper(0,-6);
		else
			super.moveHelper(0,-6);
	}

}
