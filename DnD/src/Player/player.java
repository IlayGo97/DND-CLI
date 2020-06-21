package Player;
import TileType.Empty;
import TileType.Unit;
import TileType.Wall;
import enemies.Enemy;
import misc.Pool;

public abstract class player extends Unit
{
	
	Pool experience;
	int PlayerLevel;
	public void Accept(player p)
	{
		//do nothing will never happen
	}
	public  void Visit(Empty E)
	{
		//TODO swap places function
	}
	protected abstract void Levelup();
}