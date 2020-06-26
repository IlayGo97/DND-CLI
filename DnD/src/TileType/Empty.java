package TileType;

public class Empty extends Tile {

	public Empty(int x, int y) {
		super(x, y);
		Char = '.';
	}

	@Override
	public String toString() {
		return Character.toString(Char);
	}

	@Override
	public void Accept(Unit u) {
		u.Visit(this); // swapאפשר גם כאן לעשות את ה
	}
}