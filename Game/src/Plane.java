public class Plane extends Collidable
{
	private int life;
	private int imageState;
	private int speed;
	private int cooldown;
	private int cooldownBuf;
	private int powerStep;
	private boolean movingLeft, movingRight, movingUp, movingDown, shootNext;
	private World myWorld;
	private boolean damageUp;
	private int damageCooldown;
	private int damageStep;
	
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
		damageUp = false;
		myWorld = world;
		switch(type)
		{
			case 1:
				cooldownBuf = 5;
				life = 5;
				damageCooldown = 5;
			break;
			case 3:
				cooldownBuf = (int)(Math.random() * 30) + 30;
				speed = (int)(Math.random() * 3) + 3;
				life = 3;
			break;
			case 5:
				cooldownBuf = (int)(Math.random() * 30) + 50;
				speed = (int)(Math.random() * 3) + 2;
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
				life = 7;
			break;
			case 11:
				cooldownBuf = 60;
				speed = 2;
				life = 3;
			break;
		}
		cooldown = cooldownBuf;
	}
	
	//who's hitting me.... and who am i??? important questions for what happens
	public boolean hitResult(Collidable other)
	{
		switch(other.getType())
		{
			case 0:
				return false;
			case 1:
				if(getType() == 3 || getType() == 5)
				{
					Plane  playerPlane = (Plane) other;
					playerPlane.hurt(3);
					hurt(6);
					setCollide(true);
					return true;
				}
				else if (getType() == 7)
				{
					Plane  playerPlane = (Plane) other;
					playerPlane.hurt(5);
					hurt(5);
					return true;
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
					return true;
				}
				break;

			case 7:
			case 9:
				if(getType() == 1)
				{
					Plane enemyPlane = (Plane) other;
					destroy();
					enemyPlane.hurt(5);
					setCollide(true);
					myWorld.addScore(500);
					return true;
				}
				break;
			
			case 13:
				if (getType() == 1)
				{
					other.destroy();
					myWorld.getPlayer().hurt(-2);
					if (myWorld.getPlayer().getLife() > 5)
						myWorld.getPlayer().setLifeMax();
					return true;
				}
				break;
			case 14:
				if (getType() == 1)
				{
					other.destroy();
					myWorld.getPlayer().setCool(3, myWorld.getGui().getSteps());
					return true;
				}
				break;
			case 15:
				if (getType() == 1)
				{
					Powerup plane = (Powerup) other;
					other.destroy();
					myWorld.getPlayer().setCool(3,  myWorld.getGui().getSteps());
					damageUp = true;
				}
				break;
		}
		return false;
	}
	
	public void destroy()
	{
		if(getType() == 1)
		{}
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
			{
				if((step - powerStep) % 40 == 0)
				{
					clearPowers();
				}
				if (damageUp)
				{
					getWorld().add(new Projectile(getLat() + 15, getLong(), 17, getWorld(), step));
					if((step-damageStep) % 40 != 0)
						clearPowers();
				}
				else
					getWorld().add(new Projectile(getLat() + 15, getLong(), 2, getWorld(), step));
			}
			else if (getType() == 3)
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 4, getWorld(), step));
			else if (getType() == 5)
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 6, getWorld(), step));
			else if (getType() == 9)
			{
				getWorld().add(new Projectile(getLat() + 8, getLong()+50, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 11, getLong()+52, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 15, getLong()+53, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 19, getLong()+52, 10, getWorld(), step));
				getWorld().add(new Projectile(getLat() + 22, getLong()+50, 10, getWorld(), step));
			}
			else if (getType() == 11)
				getWorld().add(new Projectile(getLat() + 27, getLong() + 69, 12, getWorld(), step-1));
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
    
    public void setCool(int i, int step)
    {
    	clearPowers();
    	cooldownBuf = i;
    	powerStep = step;
    }
    public void setDamageCool(int step)
    {
    	clearPowers();
    	damageStep = step;
    }
    public void clearPowers()
    {
    	damageUp = false;
    	cooldownBuf = 7;
    }
}


