package Player;
import misc.*;

import java.util.ArrayList;

import enemies.Enemy;
public class Warrior extends player {
	
	Resource cooldown;

	public Warrior(int x, int y, String name, int maxHP, int att, int def, int cooldown)
	{
		super(x, y, name, maxHP, att, def);
		this.cooldown = new Resource(cooldown);
	}

	@Override
	public void Update() {
		cooldown.Tick();
	}

	@Override
	protected void ClassLevelup() {
		cooldown.Reset();
		healthPool.IncreaseMax(5*this.PlayerLevel);
		attackPoints = attackPoints+2*PlayerLevel;
		defensePoints = defensePoints +PlayerLevel;
	}

	@Override
	public String Describe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SpecialAbility() // AVENGER'S SHIELD MURHAHA
	{
		if(cooldown.isAvailable())
			eh.HandleEvent(this.name+" couldn't cast avenger's shield");
		else {
			//heal first
			int healAmount = 10* this.defensePoints;
			this.healthPool.Add(healAmount);
			eh.HandleEvent(this.name+" used Avenger's Shield, healing for "+healAmount);
			ArrayList<Enemy> CloseEnemies = currBoard.getAllCloseEnemies(this, 3);
			if(CloseEnemies.size() > 0) {
				int chosen = (int)(Math.random()*CloseEnemies.size());
				Enemy Hit = CloseEnemies.get(chosen);
				int DamageDone = (int)(healthPool.max*0.1) - Hit.RollDefense();
				if(DamageDone < 0)
					DamageDone = 0;
				Boolean killed = Hit.healthPool.ReduceCurr(DamageDone);
				eh.HandleEvent(this.name+" hit "+Hit.name+" for "+DamageDone+" ability Damage.");
				if (killed)
					Slay(Hit);
			}
		}
	}
}