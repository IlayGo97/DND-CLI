package TileType;

public class Wall extends Tile {

	public Wall (int _x, int _y)
	{
		x = _x;
		y = _y;
		Char = '#';
	}
	

	@Override
	public void Accept(Unit u) {
		//do nothing
		return;
	}	
}