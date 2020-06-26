package TileType;

public class Wall extends Tile {

	public Wall(int x, int y) {
		super(x, y);
		Char = '#';
	}

	@Override
	public void Accept(Unit u) {
		//do nothing
		return;
	}	
}