public class Plane extends Collidable
{
	private int life;
	private int imageState;
	private int speed;
	private int cooldown;
	private int cooldownBuf;
	private boolean movingLeft, movingRight, movingUp, movingDown, shootNext;
	private World myWorld;
	
	public Plane(int initX, int initY, int type, World world, int born)
	{
		super(initX,initY,type,world, born);
		//type = 1 means friendly plane, type = 3 means normal plane, type 5 = tracking plane, type 7 = jet,
		//type 9 = behemoth, type 11 = laser
		imageState = 3;
		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		shootNext = false; 
		myWorld = world;
		switch(type)
		{
			case 1:
				cooldownBuf = 5;
				life = 5;
			break;
			case 3:
				cooldownBuf = (int)(Math.random() * 30) + 20;
				speed = (int)(Math.random() * 4) + 3;
				life = 3;
			break;
			case 5:
				cooldownBuf = (int)(Math.random() * 30) + 40;
				speed = (int)(Math.random() * 4) + 2;
				life = 4;
			break;
			case 7:
				cooldownBuf = 0;
				speed = (int)(Math.random() * 5) + 15;
				life = 2;
			break;
			case 9:
				cooldownBuf = 30;
				speed = 2;
				life = 8;
			break;
			case 11:
				cooldownBuf = 60;
				speed = 3;
				life = 5;
			break;
		}
		cooldown = cooldownBuf;
	}
	
	//who's hitting me.... and who am i??? important questions for what happens
	public void hitResult(Collidable other)
	{
		if(!other.collided())
		{
			switch(other.getType())
			{
				case 0:
				break;
				case 1:
					if(getType() == 3 || getType() == 5)
					{
						Plane  playerPlane = (Plane) other;
						playerPlane.hurt(3);
						hurt(6);
						setCollide(true);
					}
					else if (getType() == 7)
					{
						Plane  playerPlane = (Plane) other;
						playerPlane.hurt(5);
						hurt(5);
					}
					break;
				case 3:
				case 5:
				case 11:
					if(getType() == 1)
					{
						Plane enemyPlane = (Plane) other;
						life -= 3;
						enemyPlane.hurt(5);
						setCollide(true);
						myWorld.addScore(500);
					}
					break;
				case 7:
				case 9:
					if(getType() == 1)
					{
						Plane enemyPlane = (Plane) other;
						life-=5;
						enemyPlane.hurt(5);
						setCollide(true);
						myWorld.addScore(500);
					}
					break;
				case 13:
					if (getType() == 1)
					{
						other.destroy();
						myWorld.getPlayer().hurt(-2);
						if (myWorld.getPlayer().getLife() > 5)
							myWorld.getPlayer().setLifeMax();
						setCollide(true);
					}
					break;
			}
		}
	}
	
	public void destroy()
	{
		if(getType() == 1)
		{
			System.out.println("you'll live on in our hearts, trooper");
		}
		else
			myWorld.addScore(100);
		getWorld().removeEntity(this);
	}
	
	//yeowch deduct a bullet's worth of HP from the plane
	public void hurt(int damage)
	{
		life -= damage;
		setImage(0);
		if (getType() == 1 && damage > 0)
			 myWorld.addScore(-250);
	}
	
	public void move()
	{
		setCollide(false);
		if(this.getType() == 3 || getType() == 5 || getType() == 7 || getType() == 9 || getType() == 11)
		{	
			super.moveHelper(0, speed);
		}
		else
		{
			int s;
			if(shootNext == true)
				s = 4;
			else
				s = 7;
			
			if(movingLeft)
				super.moveHelper(-s, 0);
			else if (movingRight)
				super.moveHelper(s, 0);
			else if(movingUp)
				super.moveHelper(0, -s);
			else if (movingDown)
				super.moveHelper(0, s);
		}
	}
	
	//give birth to a bullet a little bit in front of u
	public void fire(int step)
	{
		if(cooldown <= 0)
		{
			if(getType() == 1)
				getWorld().add(new Projectile(getLat() + 15, getLong(), 2, getWorld(), step));
			else if (getType() == 3)
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 4, getWorld(), step));
			else if (getType() == 5)
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 6, getWorld(), step));
			else if (getType() == 9)
			{
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 10, getWorld(), step));
			}
			else if (getType() == 11)
				getWorld().add(new Projectile(getLat() + 30, getLong() + 69, 12, getWorld(), step-1));
			else{}
			
			cooldown = cooldownBuf;
		}
		else
			cooldown--;
	}
	
	public int getLife()
	{
		if(life >= 0)
		{
			return life;	
		}
		else 
			return 0;
	}
	
	//to be implemented when i have damaged plane sprites
	public int getImageState()
	{
		return imageState;
	}
	
	public void setImage(int img)
	{
		imageState = img;
	}
	
	public void clearMoveState()
    {
    	movingLeft = false;
    	movingRight = false;
    	movingUp = false;
    	movingDown = false;
    }
    
    public void setLeftMovement(boolean state)
    {
    	movingLeft = state;
    }
    
    public void setRightMovement(boolean state)
    {
    	movingRight =  state;
    }
    
    public void setUpwardsMovement(boolean state)
    {
    	movingUp =  state;
    }
    
    public void setDownwardsMovement(boolean state)
    {
    	movingDown =  state;
    }
    
    public boolean getShootState()
    {
    	return shootNext;
    }
    
    public void setShootState(boolean state)
    {
    	shootNext =  state;
    }
    public void setLifeMax()
    {
    	life = 5;
    }
    public double compareTo(Plane other)
    {
    	return other.getType() - getType();
    }
}


