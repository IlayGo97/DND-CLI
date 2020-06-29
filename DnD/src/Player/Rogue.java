package Player;
import java.util.ArrayList;
import enemies.Enemy;
import misc.Pool;

public class Rogue extends player {
	
	private int cost;
	private Pool Energy;
	
	public Rogue(int x, int y, String name, int maxHP, int att, int def, int cost)
	{
		super(x, y, name, maxHP, att, def);
		this.cost = cost;
		Energy = new Pool(100);
	}
	
	@Override
	public void Update() {
		Energy.Add(10);
	}
	
	@Override
	protected void ClassLevelup() {
		Energy.Fill();
		attackPoints += 3 * PlayerLevel;
	}
	
	@Override
	public void SpecialAbility() // FAN OF KNIVES
	{
		if(Energy.current < cost)
			eh.HandleEvent(this.name+" didn't have enough energy to cast fan of knives");
		else {
			eh.HandleEvent(this.name+" cast Fan of Knives.");
			Energy.ReduceCurr(cost);
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
		return this.name+"	Health: "+this.healthPool.current+"/"+this.healthPool.max+"		Attack: "+this.attackPoints+"		Defense: "+this.defensePoints+"		Level: "+this.PlayerLevel+"		Experience: "+this.experience.current+"/"+this.experience.max+"		Energy: "+this.Energy.current+"/"+this.Energy.max;
	}
}