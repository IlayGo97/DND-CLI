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
	public void Accept(Unit u) {
		u.Visit(this);
	}
	@Override
	public void Visit(player p)
	{
		int attackroll = this.RollAttack();
		int defenseroll = p.RollDefense();
		int Damage = Math.max(0, attackroll-defenseroll);
		eh.HandleEvent(name+" dealt "+Damage+" to "+p.name);
		if(p.healthPool.ReduceCurr(Damage))
		{
			//TODO kill player and end the game.
		}
	}
	@Override
	public void Visit(Empty e) {
		currBoard.SwapPlaces(this, e);
	}
	
	@Override
	public void Visit(Enemy e) {
		//do nothing
	}
}