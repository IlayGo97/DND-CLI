package Player;

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
	public void SpecialAbility() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String Describe() {
		// TODO Auto-generated method stub
		return null;
	}
}