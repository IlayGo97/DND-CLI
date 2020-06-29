package misc;

public class Resource {
	
	public int coolDown;
	public int remainingCoolDown;
	
	public Resource(int _maxcd)
	{
		coolDown = _maxcd;
		remainingCoolDown = 0;
	}
	
	public boolean isAvailable()
	{
		return remainingCoolDown == 0;
	}
	
	public void Tick()
	{
		if(remainingCoolDown>0)
			remainingCoolDown--;
	}
	
	public void Reset()
	{
		remainingCoolDown = coolDown + 1;
	}
}