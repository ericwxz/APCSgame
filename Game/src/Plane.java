public class Plane extends Collidable
{
	private int life;
	private int imageState;
	
	public Plane(int initX, int initY, int type, World world, int born)
	{
		super(initX,initY,type,world, born);
		//type = 1 means friendly bullet, type = 3 means enemybullet
		life = 5;
	}
	
	//who's hitting me.... and who am i??? important questions for what happens
	public void hitResult(Collidable other)
	{
		switch(other.getType())
			{
			case 1:
				if(getType() == 3)
				{
					life-=2;
					Plane playerPlane = (Plane) other;
					playerPlane.hurt(3);
				}
				break;
			case 2: 
				if(getType() == 3)
				{
					Projectile playerProjectile = (Projectile) other;
					hurt(playerProjectile.getDamage());
					other.destroy();
				}
				break;	
			case 3:
				if(getType() == 1)
				{
					life-=2;
					Plane enemyPlane = (Plane) other;
					enemyPlane.hurt(3);
				}
				break;
			case 4: 
				if(getType() == 1)
				{
					Projectile enemyProjectile = (Projectile) other;
					hurt(enemyProjectile.getDamage());
					other.destroy();
				}
				break;
			default:
		}
	}
	
	//yeowch deduct a bullet's worth of HP from the plane
	public void hurt(int damage)
	{
		life -= damage;
		System.out.println("ouch ya got hit ya dinkus");
	}
	
	public void move()
	{
		if(this.getType() == 3)
		{	
			super.moveHelper(0, 2);
		}
		else
		{
			super.moveHelper(0, -2); //replace with the results from keylistener input
		}
	}
	
	//give birth to a bullet a little bit in front of u
	public void fire(int step)
	{
		if(getType() == 1)
			getWorld().add(new Projectile(getLat() + 15, getLong() - 20, 2, getWorld(), 3, step));
		else
			getWorld().add(new Projectile(getLat() + 15, getLong() + 70, 4, getWorld(), 3, step));
	}
	
	//to be implemented when i have damaged plane sprites
	public int getImageState()
	{
		return 3;
	}
}
