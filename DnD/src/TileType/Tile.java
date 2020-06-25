package TileType;

public abstract class Tile {
	public int x = 0;
	public int y = 0;
	public char Char = ' ';
	public abstract void Accept(Unit u);
}