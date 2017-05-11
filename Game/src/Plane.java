public class Plane extends Collidable
{
	private int life;
	private int type;
	
	public Plane(int initX, int initY, int type, World world)
	{
		super(initX,initY,type,world);
		//type = 1 means friendly bullet, type = 3 means enemybullet
		life = 5;
	}
	
	public int hitResult(Collidable other)
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
		return life;
	}
	
	public void hurt(int damage)
	{
		life -= damage;
	}
}
