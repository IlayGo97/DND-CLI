package misc;

public class Pool {
	
	int current;
	int max;
	
	public Boolean Add(int amount) {
		current=Math.max(current+amount, max);
		return true;
	}
	
	public Boolean IncreaseMax(int amount) {
		max+=amount;
		return true;
	}
	
	public Boolean ReduceCurr(int amount) {
		current=Math.min(current-amount, 0);
		return true;
	}
	
	public Boolean Fill() {
		current=max;
		return true;
	}
}