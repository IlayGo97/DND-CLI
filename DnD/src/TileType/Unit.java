package TileType;
import General.Observer;
import Player.player;
import enemies.Enemy;
import misc.Pool;

public abstract class Unit extends Tile implements Observer {
	
	public String name;
	public Pool healthPool;
	public int attackPoints;
	public int defensePoints;
	public abstract void Visit(Empty e);
	public void Visit(Wall e)
	{
		//do nothing
		return;
	}
	public abstract void Interact(Tile t);
	public abstract void Visit(player p);
	public abstract void Visit(Enemy e);
	public abstract void Accept(Unit u);
}