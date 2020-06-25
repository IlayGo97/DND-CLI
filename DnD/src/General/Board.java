package General;
import Player.player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;

public class Board {
	
	public Tile[][] Board;
	private player p;
	private static Board _Board = null;
	
	private Board (String board)
	{
		//TODO create board and update player field
	}
	
	public static Board setUp(String board)
	{
		if(_Board == null)
			return new Board(board);
		else return _Board;
	}
	
	public static Board GetInstance()
	{
		return _Board;
	}
	
	public Tile GetTile(int x, int y)
	{
		return Board[x][y];
	}
	
	public int Range(Tile t, Tile e)
	{
		return (int) Math.sqrt((t.x-e.x)*(t.x-e.x)+(t.y-e.y)*(t.y-e.y));
	}
	
	public Tile[] allTilesInRange(Tile t, int x)
	{
		Tile[] output = new Unit[(2*x+1)*(2*x+1)];
		int counter = 0;
		for(int x1 = Math.max(0, t.x-x); x1<=Math.min(Board[0].length,t.x+x);x1++)
		{
			for(int y1 = Math.max(0, t.y-x); y1<=Math.min(Board.length,t.y+x);y1++)
			{
				output[counter] = Board[x1][y1];
				counter++;
			}
		}
		return output;
	}
	
	public player getPlayer() {
		return p;
	}
	
	public void SwapPlaces(Tile t, Tile t1)
	{
		Tile temp = t;
		Board[t.x][t.y] = t1;
		Board[t1.x][t1.y] = temp;
		t1.x += t.x;
		t.x = t1.x - t.x;
		t1.x = t1.x - t.x;
		t1.y += t.y;
		t.y = t1.y - t.y;
		t1.y = t1.y - t.y;
	}
	public void Kill(Unit t)
	{
		Board[t.x][t.y]= new Empty(t.x,t.y);
	}
}