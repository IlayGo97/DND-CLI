package Player;
import TileType.Empty;
import TileType.Unit;
import TileType.Wall;
import enemies.Enemy;

public abstract class player extends Unit
{
	
	int experience;
	int PlayerLevel;
	public void Accept(player p)
	{
		//do nothing will never happen
	}
	public  void Visit(Empty E)
	{
		//TODO swap places function
	}

}