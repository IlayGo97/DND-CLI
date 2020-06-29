package Player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import TileType.Wall;
import enemies.Enemy;
import misc.*;

public abstract class player extends Unit
{
	
	protected Pool experience = new Pool(50);
	protected int PlayerLevel;
	
	public player(int x, int y, String name, int maxHP, int att, int def)
	{
		super(x, y, name, maxHP, att, def);
		Char = '@';
		PlayerLevel = 1;
	}
	
	@Override
	public void Accept(Unit u)
	{
		u.Visit(this);
	}
	
	public void Levelup()
	{
		int oldDef = this.defensePoints;
		int oldAttack = this.attackPoints;
		int oldMaxhp = this.healthPool.max;
		experience.Empty();
		PlayerLevel++;
		healthPool.IncreaseMax(10*PlayerLevel);
		healthPool.Fill();
		this.attackPoints=attackPoints+4*PlayerLevel;
		this.defensePoints=defensePoints+PlayerLevel;
		ClassLevelup();
		eh.HandleEvent(this.name+" reached level "+this.PlayerLevel+": +"+(healthPool.max-oldMaxhp)+" Health, +"+(attackPoints-oldAttack)+" Attack, +"+(defensePoints-oldDef)+" Defense");
	}
	
	protected abstract void ClassLevelup();
	
	public void Interact(Tile t)
	{
		t.Accept(this);
	}
	
	@Override
	public void Visit(Enemy e)
	{
		eh.HandleEvent(this.name+" engaged in combat with "+e.name);
		eh.HandleEvent(this.Describe());
		eh.HandleEvent(e.Describe());
		int attackroll = this.RollAttack();
		int defenseroll = e.RollDefense();
		int Damage = Math.max(0, attackroll-defenseroll);
		eh.HandleEvent(name+ " dealt "+Damage+" Damage to "+e.name);
		if(e.healthPool.ReduceCurr(Damage))
		{
			currBoard.SwapPlaces(this, e);
			Slay(e);
		}
	}
	
	@Override
	public void Visit(Empty e)
	{
		currBoard.SwapPlaces(this, e);
	}
	
	@Override
	public void Visit(player p) {
		// do nothing
	}
	
	public void Slay(Enemy e)
	{
		e.KillThis();
		eh.HandleEvent(e.name+" died. "+this.name+" gained "+e.experienceValue+" experience");
		if(this.experience.Add(e.experienceValue))
			this.Levelup();
	}
	
	public abstract void SpecialAbility();
	
	public void InteractRight()
	{
		this.Interact(currBoard.GetTile(this.x, this.y+1));
	}
	
	public void InteractLeft()
	{
		this.Interact(currBoard.GetTile(this.x, this.y-1));
	}
	
	public void InteractUp()
	{
		this.Interact(currBoard.GetTile(this.x-1, this.y));
	}
	
	public void InteractDown()
	{
		this.Interact(currBoard.GetTile(this.x+1, this.y));
	}
}