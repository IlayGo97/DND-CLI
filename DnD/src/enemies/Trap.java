package enemies;
import Player.player;
import TileType.Empty;

public class Trap extends Enemy {
	
	private int visibilityTime;
	private int invisibilityTime;
	private int ticksCount;
	private Boolean visible;
	
	public Trap(int x, int y, String name, int maxHP, int att, int def, int exp, int VisTime, int InvisTime, char Chara) {
		super(x, y, name, maxHP, att, def, exp);
		visibilityTime = VisTime;
		invisibilityTime = InvisTime;
		this.Char = Chara;
		ticksCount = 0;
		visible = true;
	}
	
	@Override
	public void Update() {
		if(healthPool.current <= 0)
			return; // if dead do nothing
		ticksCount++;
		if(visible & ticksCount >= visibilityTime)
		{
			visible = false;
			ticksCount = 0;
		}
		if(!visible & ticksCount >= invisibilityTime)
		{
			visible = true;
			ticksCount = 0;
		}
		if(currBoard.Range(this, currBoard.getPlayer())<2)
			this.Visit(currBoard.getPlayer());
	}
	
	@Override
	public String toString() {
		if(visible)
			return Character.toString(Char);
		else return Character.toString('.');
	}
	
	@Override
	public String Describe() {
		String output;
		output = this.name+"	Health: "+this.healthPool.current+"/"+this.healthPool.max+"		Attack: "+this.attackPoints+"		Defense: "+this.defensePoints+"		Experience Value: "+this.experienceValue;
		return output;
	}
}