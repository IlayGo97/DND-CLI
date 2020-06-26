package TileType;

public abstract class Tile {
	
	public int x = 0;
	public int y = 0;
	public char Char = ' ';
	
	public Tile(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public abstract void Accept(Unit u);
	
	@Override
	public String toString()
	{
		return Character.toString(Char);
	}
}