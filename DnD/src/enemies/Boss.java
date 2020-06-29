package enemies;

import TileType.HeroicUnit;
import misc.Resource;

public class Boss extends Monster implements HeroicUnit {
	Resource abilityfrequency;
	public Boss(int x, int y, String name, int maxHP, int att, int def, int exp, int visionRange, char Char, int abilityfreq) {
		super(x, y, name, maxHP, att, def, exp, visionRange, Char);
		abilityfrequency = new Resource(abilityfreq);
	}

	@Override
	public void SpecialAbility() {
		if(!abilityfrequency.isAvailable())
			return;
		if(currBoard.Range(this, currBoard.p)<this.visionRange)
		{
			eh.HandleEvent(this.name+" fired an arrow at "+currBoard.p.name);
			abilityfrequency.Reset();
			int defroll = currBoard.p.RollDefense();
			int damage = Math.max(0, this.attackPoints-defroll);
			eh.HandleEvent(this.name+" hit "+currBoard.p+" for "+damage+" ability damage.");
			if(currBoard.p.healthPool.ReduceCurr(damage))
			{
				currBoard.p.Char = 'X';
				eh.HandleEvent("You Lost.");
			}
			abilityfrequency.Reset();
		}
		
	}
	
	@Override
	public void Update()
	{
		if(healthPool.current <= 0)
			return; // if dead do nothing
		if(currBoard.Range(this, currBoard.getPlayer())<visionRange)
		{
			if(this.abilityfrequency.isAvailable())
			{
				this.SpecialAbility();
				return;
			}
			int dx = currBoard.getPlayer().x-this.x;
			int dy = currBoard.getPlayer().y-this.y;
			if(Math.abs(dx)>Math.abs(dy))
			{
				if(dx>0)
					Interact(currBoard.GetTile(this.x+1, this.y));
				else
					Interact(currBoard.GetTile(this.x-1, this.y));
			}
			else
			{
				if(dy>0)
					Interact(currBoard.GetTile(this.x, this.y+1));
				else
					Interact(currBoard.GetTile(this.x, this.y-1));
			}
		}
		else
		{
			int r = (int)(Math.random()*100);
			if (r<=20)
				Interact(currBoard.GetTile(this.x+1, this.y));
			else if (r<=40)
				Interact(currBoard.GetTile(this.x-1, this.y));
			else if (r<=60)
				Interact(currBoard.GetTile(this.x, this.y+1));
			else if (r<=80)
				Interact(currBoard.GetTile(this.x, this.y-1));
		}
		this.abilityfrequency.Tick();
	}
}