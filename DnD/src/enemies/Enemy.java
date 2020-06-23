package enemies;
import Player.player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import TileType.Wall;

public class Enemy extends Unit
{
	
	public int experienceValue;

	@Override
	public void Accept(player p) {
		p.Visit(this);
	}

	@Override
	public void Accept(Enemy e) {
		//do nothing
		return;
	}


	@Override
	public void Accept(Unit u) {
		u.Accept(this);
	}

	@Override
	public void Visit(Tile t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Visit(player p) {
		p.Accept(this);
		
	}

	@Override
	public void Visit(Enemy e) {
		//do nothing
		return;
	}
}