public class Plane extends Collidable
{
	private int life;
	private int type;
	
	public Plane(int initX, int initY, int type, World world)
	{
		super(initX,initY,type, world);
		//type = 1 means friendly plane, type = 3 means enemy plane
		life = 5;
	}
	
	public int hitResult(Collidable other)
	{
		switch(other.getType())
		{
			case 3:
				life--;
				Plane enemyPlane = (Plane) other;
				enemyPlane.hurt();
				break;
			case 4:
				Projectile enemyProjectile = (Projectile) other;
				life -= enemyProjectile.getDamage();
				enemyProjectile.destroy();
				break;
			default:
		}
		return life;
	}
	
	public void hurt()
	{
		life--;
	}
}
