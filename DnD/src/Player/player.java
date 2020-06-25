package Player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import TileType.Wall;
import enemies.Enemy;
import misc.*;

public abstract class player extends Unit
{
	
	Pool experience;
	int PlayerLevel;

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
		//TODO FIGHT
	}
	
	@Override
	public void Visit(Empty e)
	{
		//TODO Swap places
	}
}