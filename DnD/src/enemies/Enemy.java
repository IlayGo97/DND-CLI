package enemies;
import Player.player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import TileType.Wall;

public abstract class Enemy extends Unit
{
	public int experienceValue;
	public void Interact(Tile t)
	{
		t.Accept(this);
	}
	
	@Override
	public void Visit(player p) {
		int attackroll = (int)Math.random()*attackPoints;
		int defenseroll = (int)Math.random()*p.defensePoints;
		int Damage = Math.max(0, attackroll-defenseroll);
		if(p.healthPool.ReduceCurr(Damage))
		{
			p.Char='X';
		}
	}

	@Override
	public void Visit(Enemy e) {
		//do nothing
	}
}