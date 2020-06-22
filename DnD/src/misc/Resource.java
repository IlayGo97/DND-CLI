package misc;

public class Resource {
	
	private int coolDown;
	private int remainingCoolDown;
	
	public Resource(int _maxcd)
	{
		coolDown = 0;
		remainingCoolDown = _maxcd;
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
		remainingCoolDown=coolDown;
	}
}