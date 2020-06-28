package Player;
import java.util.ArrayList;

import enemies.Enemy;
import misc.Pool;

public class Mage extends player {
	
	private Pool mana;
	public int manaCost;
	public int spellPower;
	public int hitsCount;
	public int abilityRange;
	
	public Mage(int x, int y, String name, int maxHP, int att, int def, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange)
	{
		super(x, y, name, maxHP, att, def);
		this.mana = new Pool(manaPool);
		mana.current = manaPool/4;
		this.manaCost = manaCost;
		this.spellPower = spellPower;
		this.hitsCount = hitsCount;
		this.abilityRange = abilityRange;
	}
	
	@Override
	public void Update() {
		mana.Add(PlayerLevel);
	}
	
	@Override
	protected void ClassLevelup() {
		mana.IncreaseMax(25 * PlayerLevel);
		mana.IncreaseMax(mana.max/4);
		spellPower += 10 * PlayerLevel;
	}
	
	@Override
	public void SpecialAbility() // BLIZZARD
	{
		if(mana.current < manaCost)
			eh.HandleEvent(this.name+" didn't have enough mana to cast blizzard");
		else {
			mana.ReduceCurr(manaCost);
			ArrayList<Enemy> CloseEnemies = currBoard.getAllCloseEnemies(this, abilityRange);
			int hits = 0;
			while(hits < hitsCount & CloseEnemies.size() > 0) {
				int chosen = (int)(Math.random()*CloseEnemies.size());
				Enemy Hit = CloseEnemies.get(chosen);
				int DamageDone = spellPower - Hit.RollDefense();
				if(DamageDone < 0)
					DamageDone = 0;
				Boolean killed = Hit.healthPool.ReduceCurr(DamageDone);
				eh.HandleEvent(this.name+" hit "+Hit.name+" for "+DamageDone+" ability Damage.");
				if (killed)
				{
					Slay(Hit);
				}
				CloseEnemies.remove(chosen);
				hits++;
			}
		}
	}
	
	@Override
	public String Describe() {
		return this.name+"	Health: "+this.healthPool.current+"/"+this.healthPool.max+"		Attack: "+this.attackPoints+"		Defense: "+this.defensePoints+"		Level: "+this.PlayerLevel+"		Experience: "+this.experience.current+"/"+this.experience.max+"		Mana: "+this.mana.current+"/"+this.mana.max+"	Spell Power: "+this.spellPower;
	}
}