package enemies;
import Player.player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import TileType.Wall;

public abstract class Enemy extends Unit
{
	
	public int experienceValue;
	
	public Enemy(int x, int y, String name, int maxHP, int att, int def, int exp) {
		super(x, y, name, maxHP, att, def);
		experienceValue = exp;
	}
	
	@Override
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
		eh.HandleEvent(this.name + " engaged in combat with " + p.name + ".");
		eh.HandleEvent(this.Describe());
		eh.HandleEvent(p.Describe());
		int attackroll = this.RollAttack();
		int defenseroll = p.RollDefense();
		int Damage = Math.max(0, attackroll - defenseroll);
		eh.HandleEvent(name+" dealt "+Damage+" to "+p.name);
		if(p.healthPool.ReduceCurr(Damage))
		{
			p.Char = 'X';
			eh.HandleEvent("You Lost.");
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
	
	public void KillThis()
	{
		currBoard.KillEnemy(this);
	}
}