package Player;
import misc.Resource;

public class Rogue extends player {
	
	int cost;
	int currentEnergy;
	
	public Rogue(int x, int y, String name, int maxHP, int att, int def, int cost)
	{
		super(x, y, name, maxHP, att, def);
		this.cost = cost;
		this.currentEnergy = 100;
	}
	
	@Override
	public void Update() {
		currentEnergy = Math.min(currentEnergy + 10, 100);
	}
	
	@Override
	protected void ClassLevelup() {
		PlayerLevel++;
		currentEnergy = 100;
		attackPoints += 3 * PlayerLevel;
	}
	
	@Override
	public void SpecialAbility() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String Describe() {
		// TODO Auto-generated method stub
		return null;
	}
}