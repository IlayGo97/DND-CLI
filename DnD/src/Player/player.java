package Player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import TileType.Wall;
import enemies.Enemy;
import misc.*;

public abstract class player extends Unit
{
	
	protected Pool experience;
	protected int PlayerLevel;

	@Override
	public void Accept(Unit u)
	{
		u.Visit(this);
	}
	
	public void Levelup()
	{
		experience.Empty();
		PlayerLevel++;
		healthPool.IncreaseMax(healthPool.max+10*PlayerLevel);
		healthPool.Fill();
		this.attackPoints=attackPoints+4*PlayerLevel;
		this.defensePoints=defensePoints+PlayerLevel;
		ClassLevelup();
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
		int attackroll = this.RollAttack();
		int defenseroll = e.RollDefense();
		int Damage = Math.max(0, attackroll-defenseroll);
		eh.HandleEvent(name+ "dealt "+Damage+" to "+e.name);
		if(e.healthPool.ReduceCurr(Damage))
		{
			//e.KillThis();
			currBoard.Board[e.x][e.y]=new Empty(e.x,e.y);
			eh.HandleEvent(e.name+" died. "+this.name+" gained "+e.experienceValue+" experience");
			if(this.experience.Add(e.experienceValue))
				this.Levelup();
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
}