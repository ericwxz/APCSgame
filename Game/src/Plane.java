public class Plane extends Collidable
{
	private int life;
	private int imageState;
	private int speed;
	private boolean movingLeft, movingRight, movingUp, movingDown, shootNext;
	
	public Plane(int initX, int initY, int type, World world, int born)
	{
		super(initX,initY,type,world, born);
		//type = 1 means friendly bullet, type = 3 means enemybullet
		life = 5;
		imageState = 3;
		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		shootNext = false; 
	}
	
	//who's hitting me.... and who am i??? important questions for what happens
	public void hitResult(Collidable other)
	{
		if(!other.collided())
		{
			setCollide(true);
			switch(other.getType())
			{
				case 1:
					if(getType() == 3)
					{
						Plane playerPlane = (Plane) other;
						playerPlane.hurt(3);
						hurt(5);
						System.out.println("enemy plane destroyed");
					}
					break;
				case 3:
					if(getType() == 1)
					{
						Plane enemyPlane = (Plane) other;
						life-=3;
						enemyPlane.hurt(5);
						System.out.println("enemy plane destroyed");
					}
					break;
				case 5:
					break;
			}
		}		
	}
	
	public void destroy()
	{
		if(getType() == 1)
			System.out.println("you'll live on in our hearts, trooper");
		getWorld().removeEntity(this);
	}
	
	//yeowch deduct a bullet's worth of HP from the plane
	public void hurt(int damage)
	{
		life -= damage;
	}
	
	public void move()
	{
		if(this.getType() == 3)
		{	
			super.moveHelper(0, 2);
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
		if(getType() == 1)
			getWorld().add(new Projectile(getLat() + 15, getLong(), 2, getWorld(), 1, step));
		else
			getWorld().add(new Projectile(getLat() + 15, getLong()+50, 4, getWorld(), 1, step));
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
}


