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
		mana.Add(mana.max/4);
		spellPower += 10 * PlayerLevel;
		eh.HandleEvent("                +"+25*PlayerLevel+" maximum mana, "+10*PlayerLevel+" spell power");
	}
	
	@Override
	public void Levelup() //Overriding because events are in different order.
	{
		experience.IncreaseMax(50);
		int oldDef = this.defensePoints;
		int oldAttack = this.attackPoints;
		int oldMaxhp = this.healthPool.max;
		experience.Empty();
		PlayerLevel++;
		healthPool.IncreaseMax(10*PlayerLevel);
		healthPool.Fill();
		this.attackPoints=attackPoints+4*PlayerLevel;
		this.defensePoints=defensePoints+PlayerLevel;
		eh.HandleEvent(this.name+" reached level "+this.PlayerLevel+": +"+(healthPool.max-oldMaxhp)+" Health, +"+(attackPoints-oldAttack)+" Attack, +"+(defensePoints-oldDef)+" Defense");
		ClassLevelup();
	}
	
	@Override
	public void SpecialAbility() // BLIZZARD
	{
		if(mana.current < manaCost)
			eh.HandleEvent(this.name+" didn't have enough mana to cast blizzard");
		else {
			eh.HandleEvent(this.name+" cast Blizzard.");
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