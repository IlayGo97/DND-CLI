package TileType;

public abstract class Tile {
	
	public int x = 0;
	public int y = 0;
	public char Char = ' ';
	public abstract void Visit(Unit T);
	public abstract void Visit(Wall W);
	public abstract void Visit(Empty E);
	public abstract void Accept(Unit u);
}