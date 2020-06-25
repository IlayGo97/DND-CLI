package enemies;

import Player.player;
import TileType.Empty;
import TileType.Unit;

public class Monster extends Enemy  {
	
	public int visionRange;
	
	public void Update()
	{
		if(currBoard.Range(this, currBoard.getPlayer())<=visionRange)
		{
			int dx = currBoard.getPlayer().x-this.x;
			int dy = currBoard.getPlayer().y-this.y;
			if(Math.abs(dx)>Math.abs(dy))
			{
				if(dx>0)
					Interact(currBoard.GetTile(this.x+1, this.y));
				else
					Interact(currBoard.GetTile(this.x-1, this.y));
			}
			else
				if(dy>0)
					Interact(currBoard.GetTile(this.x, this.y+1));
				else
					Interact(currBoard.GetTile(this.x, this.y-1));
		}
		else
		{
			int r = (int)Math.random()*100;
			if(r<=20)
			{
				//do nothing
			}
			else if (r<=40)
			{
				Interact(currBoard.GetTile(this.x+1, this.y));
			}
			else if (r<=60)
			{
				Interact(currBoard.GetTile(this.x-1, this.y));
			}
			else if(r<=80)
			{
				Interact(currBoard.GetTile(this.x, this.y+1));
			}
			else 
			{
				Interact(currBoard.GetTile(this.x, this.y-1));
			}
		}
	}

	@Override
	public void Visit(Empty e) {
		currBoard.SwapPlaces(this, e);
	}
	
	@Override
	public void Visit(player p) {
		int attackroll = (int)Math.random()*attackPoints;
		int defenseroll = (int)Math.random()*p.defensePoints;
		int Damage = Math.max(0, attackroll-defenseroll);
		if(p.healthPool.ReduceCurr(Damage))
		{
			//TODO kill player
		}
	}
	
	@Override
	public void Visit(Enemy e) {
		//do nothing
	}
	
	@Override
	public void Accept(Unit u) {
		// TODO Auto-generated method stub
	}
}