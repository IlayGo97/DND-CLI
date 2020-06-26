package Player;
import misc.*;

public class Warrior extends player {
	
	Resource cooldown;

	public Warrior (int x, int y, int def, int att, String _name, Resource cooldown)
	{
		this.x = x;
		this.y = y;
		this.defensePoints = def;
		this.attackPoints = att;
		this.name = _name;
		this.experience = new Pool(50);
		this.healthPool = new Pool(100);
		// this.cooldown = cooldown;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ClassLevelup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String Describe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SpecialAbility() {
		// TODO Auto-generated method stub
		
	}
}