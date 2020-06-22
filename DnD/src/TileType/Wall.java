package TileType;

public class Wall extends Tile {

	public Wall (int _x, int _y)
	{
		x = _x;
		y = _y;
		Char = '#';
	}
	
	@Override
	public void Visit(Unit T) {
		//do nothing
		return;
	}

	@Override
	public void Visit(Wall W) {
		//do nothing
		return;
	}

	@Override
	public void Visit(Empty E) {
		//do nothing
		return;
	}	
}