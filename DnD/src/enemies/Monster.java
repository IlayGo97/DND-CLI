package enemies;
import Player.player;
import TileType.Empty;
import TileType.Unit;
import misc.Pool;

public class Monster extends Enemy  {
	
	public int visionRange;
	
	public Monster (int x, int y,int vision,int exp,int maxhp, String name, char Character ,int def, int att)
	{
		this.defensePoints= def;
		this.attackPoints = att;
		this.experienceValue= exp;
		this.x =x;
		this.y= y;
		this.name = name;
		this.Char = Character;
		this.visionRange = vision;
		this.healthPool = new Pool(maxhp);
	}
	
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
			{
				if(dy>0)
					Interact(currBoard.GetTile(this.x, this.y+1));
				else
					Interact(currBoard.GetTile(this.x, this.y-1));
			}
		}
		else
		{
			int r = (int)Math.random()*100;
			if (r<=20)
				Interact(currBoard.GetTile(this.x+1, this.y));
			else if (r<=40)
				Interact(currBoard.GetTile(this.x-1, this.y));
			else if (r<=60)
				Interact(currBoard.GetTile(this.x, this.y+1));
			else if (r<=80)
				Interact(currBoard.GetTile(this.x, this.y-1));
		}
	}
	
	@Override
	public String Describe() {
		// TODO Auto-generated method stub
		return null;
	}
}