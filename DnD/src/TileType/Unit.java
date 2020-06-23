package TileType;
import Player.player;
import enemies.Enemy;
import misc.Pool;

public abstract class Unit extends Tile {
	
	public String name;
	public Pool healthPool;
	public int attackPoints;
	public int defensePoints;
	public abstract void Visit(Tile t);
	public abstract void Visit(player p);
	public abstract void Visit(Enemy e);
	public abstract void Accept(player p);
	public abstract void Accept(Enemy e);
}