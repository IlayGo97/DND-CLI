package Player;
import java.util.ArrayList;
import enemies.Enemy;

public class Hunter extends player {
	
	private int range;
	private int arrowsCount;
	private int ticksCount;
	
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
		arrowsCount += 10 * PlayerLevel;
		attackPoints += 2 * PlayerLevel;
		defensePoints +=  PlayerLevel;
	}
	
	@Override
	public void SpecialAbility() // SHOOT
	{
		if(arrowsCount == 0)
			eh.HandleEvent(this.name+" didn't have enough arrows to cast shoot");
		else {
			if(currBoard.getAllCloseEnemies(this, range).isEmpty())
				eh.HandleEvent(this.name+" tried to shoot an arrow but there were no enemies in range.");
			else {
				arrowsCount--;
				for(int min=1;min<=range;min++) {
					ArrayList<Enemy> CloseEnemies = currBoard.getAllCloseEnemies(this, min);
					if(!CloseEnemies.isEmpty()) {
						Enemy closest = CloseEnemies.get(0);
						eh.HandleEvent(this.name+" fired an arrow at "+closest.name);
						int DamageDone = this.attackPoints - closest.RollDefense();
						if(DamageDone < 0)
							DamageDone = 0;
						Boolean killed = closest.healthPool.ReduceCurr(DamageDone);
						eh.HandleEvent(this.name+" hit "+closest.name+" for "+DamageDone+" ability Damage.");
						if (killed)
							Slay(closest);
						return;
					}
				}
			}
		}
	}
	
	@Override
	public String Describe() {
		return this.name+"	Health: "+this.healthPool.current+"/"+this.healthPool.max+"		Attack: "+this.attackPoints+"		Defense: "+this.defensePoints+"		Level: "+this.PlayerLevel+"		Experience: "+this.experience.current+"/"+this.experience.max+"		Arrows: "+this.arrowsCount+"		Range: "+this.range;
	}
}