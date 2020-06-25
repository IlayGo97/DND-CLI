package enemies;
import Player.player;
import TileType.Empty;

public class Trap extends Enemy {
	
	private int visibilityTime;
	private int invisibilityTime;
	private int ticksCount;
	private Boolean visible;
	
	public Trap(int x, int y,int def, int att, int maxHP, int exp, String _name, int VisTime, int InvisTime, char Chara)
	{
		ticksCount = 0;
		visibilityTime = VisTime;
		invisibilityTime = InvisTime;
		this.defensePoints = def;
		this.attackPoints = att;
		visible = true;
		this.experienceValue = exp;
		this.Char = Chara;
		this.x = x;
		this.y = y;
		this.name = _name;
	}
	
	@Override
	public void Update() {
		ticksCount++;
		if(visible & ticksCount>= visibilityTime)
		{
			visible = false;
			ticksCount = 0;
		}
		if(!visible & ticksCount>= invisibilityTime)
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