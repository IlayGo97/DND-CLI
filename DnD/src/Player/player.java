package Player;
import TileType.Empty;
import TileType.Unit;
import TileType.Wall;
import enemies.Enemy;
import misc.*;

public abstract class player extends Unit
{
	
	Pool experience;
	int PlayerLevel;
	public  void Visit(Empty E)
	{
		//TODO swap places function
	}
	public void Visit(Enemy e)
	{
		//FIGHT
	}
	protected abstract void ClassLevelup();
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
}