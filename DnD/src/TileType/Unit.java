package TileType;
import General.Board;
import General.EventCreator;
import General.Observer;
import Player.player;
import UIandMain.EventHandler;
import enemies.Enemy;
import misc.Pool;

public abstract class Unit extends Tile implements Observer, EventCreator {
	
	protected Board currBoard = Board.GetInstance();
	public String name;
	public Pool healthPool;
	public int attackPoints;
	public int defensePoints;
	
	public Unit(int x, int y, String name, int maxHP, int att, int def)
	{
		super(x, y);
		this.name = name;
		this.healthPool = new Pool(maxHP);
		this.attackPoints = att;
		this.defensePoints = def;
	}
	
	public void ReloadBoard()
	{
		currBoard = Board.GetInstance();
	}
	
	public abstract String Describe();
	
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
	
	public int RollAttack()
	{
		int output = (int)(Math.random()*attackPoints);
		eh.HandleEvent(name+" rolled "+output+" attack points.");
		return output;
	}
	
	public int RollDefense()
	{
		int output =(int)(Math.random()*defensePoints);
		eh.HandleEvent(name+" rolled "+output+" defense points.");
		return output;
	}
}