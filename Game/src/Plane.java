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
	
	public void hitResult(Collidable other)
	{
		switch(other.getType())
		{
			case 3:
				life-=2;
				Plane enemyPlane = (Plane) other;
				enemyPlane.hurt(3);
				break;
			case 4: 
				Projectile enemyProjectile = (Projectile) other;
				hurt(enemyProjectile.getDamage());
				enemyProjectile.destroy();
				break;
			default:
		}
	}
	
	public void hurt(int damage)
	{
		life -= damage;
		imageState--;
	}
	
	public void move()
	{
		if(this.getType() == 3)
		{	
			super.moveHelper(0, 4);
		}
		else
		{
			super.moveHelper(0, -2); //replace with the results from keylistener input
		}
	}
	
	public void fire(int step)
	{
		getWorld().add(new Projectile(getLat() + 15, 
				getLong() - 20, getType() + 1, getWorld(), 3, step));
	}
	
	public int getImageState()
	{
		return 3;
	}
}
