package misc;

public class Pool {
	
	public int current;
	public int max;
	
	public Pool(int max)
	{
		this.max = max;
		this.Fill();
	}
	
	public Boolean Add(int amount) {
		int oldcur = current;
		current = Math.min(current+amount, max);
		if(oldcur+amount>=max)
			return true; // if leveled
		else return false;
	}
	
	public Boolean IncreaseMax(int amount) {
		max += amount;
		return true;
	}
	
	public Boolean ReduceCurr(int amount) {
		int oldcur = current;
		current = Math.max(current-amount, 0);
		if(oldcur - amount <= 0)
			return true; // if died
		else return false;
	}
	
	public Boolean Fill() {
		current = max;
		return true;
	}
	
	public void Empty()
	{
		current = 0;
	}
}