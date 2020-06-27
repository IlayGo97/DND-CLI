package Player;

import java.util.ArrayList;

import enemies.Enemy;

public class Mage extends player {
	
	int manaPool;
	int currentMana;
	int manaCost;
	int spellPower;
	int hitsCount;
	int abilityRange;
	
	public Mage(int x, int y, String name, int maxHP, int att, int def, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange)
	{
		super(x, y, name, maxHP, att, def);
		this.manaPool = manaPool;
		this.currentMana = manaPool/4;
		this.manaCost = manaCost;
		this.spellPower = spellPower;
		this.hitsCount = hitsCount;
		this.abilityRange = abilityRange;
	}

	@Override
	public void Update() {
		currentMana = Math.min(manaPool, currentMana + PlayerLevel);
	}

	@Override
	protected void ClassLevelup() {
		PlayerLevel++;
		manaPool += 25 * PlayerLevel;
		currentMana = Math.min(currentMana + (manaPool)/4, manaPool);
		spellPower += 10 * PlayerLevel;
	}

	@Override
	public void SpecialAbility() // BLIZZARD
	{
		if(currentMana < manaCost)
			eh.HandleEvent(this.name+" didn't have enough mana to cast blizzard");
		else {
			currentMana -= manaCost;
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
		// TODO Auto-generated method stub
		return null;
	}
}