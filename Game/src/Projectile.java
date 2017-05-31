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

		//type = 2 means friendly bullet, type = 4 means enemybullet, type 6 = trackingbullet, type 17 = increased damage
		super (xinit, yinit, type, world, tick);
		myWorld = world;
		
		switch(type)
		{
			case 2:
				damage = 1;
				enemyBullet = false;
				yspeed = -15;
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
				yspeed = 5;
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
					yspeed = 4;
				else
					yspeed = -1 * Math.abs(3 - setNum) + 5;
				xspeed = -3 + 1 * setNum;
				break;
			case 12:
				damage = 5;
				enemyBullet = true;
				yspeed = 2;
				xspeed = 0;
				break;
			case 17:
				damage = 3;
				enemyBullet = false;
				yspeed = -15;
				xspeed = 0;
				break;
		}
		
	}
	

	//who's hitting me.... and who am i??? important questions for what happens
	public boolean hitResult(Collidable other)
	{
		switch(other.getType())
		{
			case 1:
				if(enemyBullet)
				{
					Plane player = (Plane) other;
					player.hurt(getDamage());
					destroy();
					return true;
				}
				break;
			case 2:
				if(enemyBullet)
				{
					Projectile playerProj = (Projectile) other;
					playerProj.destroy();
					destroy();
					return true;
				}
				break;
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
			case 17:
				if(!enemyBullet)
				{
					Plane enemy = (Plane) other;
					enemy.hurt(getDamage());
					destroy();
					return true;
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
					return true;
				}
		}
		return false;
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
		setCollide(false);
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
