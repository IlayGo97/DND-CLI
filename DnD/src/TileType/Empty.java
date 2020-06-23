package TileType;

public class Empty extends Tile {

	public Empty(int _x, int _y)
	{
		x= _x;
		y= _y;
		Char= '.';
	}
	

	@Override
	public String toString() {
		return Character.toString(Char);
	}

	@Override
	public void Accept(Unit u) {
		// TODO Swap Places
		
	}
}