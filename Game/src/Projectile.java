
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
	

	//who's hitting me.... and who am i??? important questions for what happens
	public void hitResult(Collidable other)
	{
		setCollide(true);
		if(!other.collided())
		{
			switch(other.getType())
			{
				case 1:
					if(enemyBullet)
					{
						Plane player = (Plane) other;
						player.hurt(getDamage());
						System.out.println("ouch");
						destroy();
						setCollide(true);
					}
					break;
				case 2:
					if(enemyBullet)
					{
						Projectile playerProj = (Projectile) other;
						playerProj.destroy();
						destroy();
						setCollide(true);
					}
					break;
				case 3:
					if(!enemyBullet)
					{
						Plane enemy = (Plane) other;
						enemy.hurt(getDamage());
						destroy();
						setCollide(true);
					}
					break;
				case 4:
					if(!enemyBullet)
					{
						Projectile enemyProj = (Projectile) other;
						enemyProj.destroy();
						destroy();
						setCollide(true);
					}
					break;
				case 5:
					break;
			}
		}
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void move()
	{
		if(this.getType() == 4)
			super.moveHelper(0, 6);
		else
			super.moveHelper(0,-10);
	}

}
