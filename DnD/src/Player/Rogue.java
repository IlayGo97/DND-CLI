package Player;
import java.util.ArrayList;

import enemies.Enemy;
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
		currentEnergy = 100;
		attackPoints += 3 * PlayerLevel;
	}
	
	@Override
	public void SpecialAbility() // FAN OF KNIVES
	{
		if(currentEnergy < cost)
			eh.HandleEvent(this.name+" didn't have enough energy to cast fan of knives");
		else {
			currentEnergy -= cost;
			ArrayList<Enemy> CloseEnemies = currBoard.getAllCloseEnemies(this, 2);
			for(Enemy e : CloseEnemies) {
				int DamageDone = this.attackPoints - e.RollDefense();
				if(DamageDone < 0)
					DamageDone = 0;
				Boolean killed = e.healthPool.ReduceCurr(DamageDone);
				eh.HandleEvent(this.name+" hit "+e.name+" for "+DamageDone+" ability Damage.");
				if (killed)
					Slay(e);
			}
		}
	}
	
	@Override
	public String Describe() {
		// TODO Auto-generated method stub
		return null;
	}
}