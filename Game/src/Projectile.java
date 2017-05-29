import java.util.ArrayList;


public class Projectile extends Collidable
{
	private int damage;
	private boolean enemyBullet;
	private World myWorld;
	private int xspeed;
	private int yspeed;

	public Projectile(int xinit, int yinit, int type, World world, int tick)
	{

		//type = 2 means friendly bullet, type = 4 means enemybullet, type 6 = trackingbullet
		super (xinit, yinit, type, world, tick);
		myWorld = world;
		
		switch(type)
		{
			case 2:
				damage = 1;
				enemyBullet = false;
				yspeed = -10;
				xspeed = 0;
				break;
			case 4:
				damage = 1;
				enemyBullet = true;
				yspeed = 7;
				xspeed = 0;
				break;
			case 6:
				damage = 3;
				enemyBullet = true;
				yspeed = 4;
				xspeed = 1;
				break;
			case 10:
				damage = 1;
				enemyBullet = true;
				int setNum = 0;
				for(Collidable c: myWorld.getList())
				{
					if(c.getType() == 10)
						setNum++;
				}
				setNum %= 5;
				setNum++;
				if(setNum == 3)
					yspeed = 3;
				else
					yspeed = -1 * Math.abs(3 - setNum) + 4;
				xspeed = -3 + 1 * setNum;
				break;
		}
		
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
				case 5:
				case 7:
				case 9:
					if(!enemyBullet)
					{
						Plane enemy = (Plane) other;
						enemy.hurt(getDamage());
						destroy();
						setCollide(true);
					}
					break;
				case 4:
				case 6:
				case 10:
					if(!enemyBullet)
					{
						Projectile enemyProj = (Projectile) other;
						enemyProj.destroy();
						destroy();
						setCollide(true);
					}
				
				default:
					break;
			}
		}
	}
	
	public void destroy()
	{
		if(getType() == 6)
			myWorld.add(new Explosion(getLat(), getLong(), 0, myWorld, 1, true));
		else
			myWorld.add(new Explosion(getLat(), getLong(), 0, myWorld, 1, false));
		getWorld().removeEntity(this);
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void move()
	{
		if (this.getType() == 6)
		{
			Plane pplane = myWorld.getPlayer();
			int deltax = 0;
			if (pplane.getLat() < super.getLat())
				deltax = 0-xspeed;
			else if (pplane.getLat() > super.getLat())
				deltax = xspeed;
			else
				deltax = 0;
			super.moveHelper(deltax,yspeed);
		}
		else
			super.moveHelper(xspeed, yspeed);
	}

}
