package TileType;

public class Empty extends Tile {

	public Empty(int _x, int _y)
	{
		x= _x;
		y= _y;
		Char= '.';
	}
	@Override
	public void Visit(Unit T) {
		// TODO swap places
		
	}

	@Override
	public void Visit(Wall W) {
		//do nothing
		
	}

	@Override
	public void Visit(Empty E) {
		//do nothing
		
	}
	@Override
	public String toString() {
		return Character.toString(Char);
	}
	
	
}