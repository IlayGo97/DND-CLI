package TileType;
import Player.player;
import enemies.Enemy;
import misc.Pool;

public abstract class Unit extends Tile {
	
	String name;
	Pool healthPool;
	int attackPoints;
	int defensePoints;
	
	public abstract void Accept(player p);
	public abstract void Accept(Enemy e);
}