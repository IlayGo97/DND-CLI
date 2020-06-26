package Player;

public class Hunter extends player {
	
	int range;
	int arrowsCount;
	int ticksCount;
	
	public Hunter(int x, int y, String name, int maxHP, int att, int def, int range)
	{
		super(x, y, name, maxHP, att, def);
		this.range = range;
		this.arrowsCount = PlayerLevel * 10;
		this.ticksCount = 0;
	}

	@Override
	public void Update() {
		if(ticksCount == 10) {
			arrowsCount += PlayerLevel;
			ticksCount = 0;
		}
		else
			ticksCount++;
	}

	@Override
	protected void ClassLevelup() {
		PlayerLevel++;
		arrowsCount += 10 * PlayerLevel;
		attackPoints += 2 * PlayerLevel;
		defensePoints +=  PlayerLevel;
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