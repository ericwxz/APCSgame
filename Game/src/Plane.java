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
		//type = 1 means friendly plane, type = 3 means normal plane, type 5 = tracking plane, type 7 = jet 
		life = 5;
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
				cooldownBuf = 4;
			break;
			case 3:
				cooldownBuf = (int)(Math.random() * 20) + 30;
				speed = (int)(Math.random() * 3) + 2;
			break;
			case 5:
				cooldownBuf = 70;
				speed = 4;
			break;
			case 7:
				cooldownBuf = 0;
				speed = 15;
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
				case 1:
					if(getType() == 3 || getType() == 5)
					{
						Plane  playerPlane = (Plane) other;
						playerPlane.hurt(3);
						hurt(5);
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
					if(getType() == 1)
					{
						Plane enemyPlane = (Plane) other;
						life-=3;
						enemyPlane.hurt(5);
						setCollide(true);
						myWorld.addScore(500);
					}
					break;
				case 7:
					if(getType() == 1)
					{
						Plane enemyPlane = (Plane) other;
						life-=5;
						enemyPlane.hurt(5);
						setCollide(true);
						myWorld.addScore(500);
					}
					break;
				case 10:
					if (getType() == 1)
					{
						other.destroy();
						myWorld.getPlayer().hurt(-2);
						if (myWorld.getPlayer().getLife() > 5)
							myWorld.getPlayer().setLifeMax();
						setCollide(true);
					}
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
			myWorld.addScore(500);
		getWorld().removeEntity(this);
	}
	
	//yeowch deduct a bullet's worth of HP from the plane
	public void hurt(int damage)
	{
		life -= damage;
		setImage(0)  ;
		if (getType() == 1 && damage > 0)
			 myWorld.addScore(-500);
	}
	
	public void move()
	{
		if(this.getType() == 3 || getType() == 5 || getType() == 7)
		{	
			super.moveHelper(0, speed);
		}
		else
		{
			if(movingLeft)
				super.moveHelper(-5, 0);
			else if (movingRight)
				super.moveHelper(5, 0);
			else if(movingUp)
				super.moveHelper(0, -5);
			else if (movingDown)
				super.moveHelper(0, 5);
		}
	}
	
	//give birth to a bullet a little bit in front of u
	public void fire(int step)
	{
		if(cooldown <= 0)
		{
			if(getType() == 1)
				getWorld().add(new Projectile(getLat() + 15, getLong(), 2, getWorld(), 1, step));
			else if (getType() == 3)
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 4, getWorld(), 1, step));
			else if (getType() == 5)
				getWorld().add(new Projectile(getLat() + 15, getLong()+50, 6, getWorld(), 3, step));
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
}


