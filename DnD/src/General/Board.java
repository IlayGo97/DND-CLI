package General;
import java.util.ArrayList;

import Player.player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import enemies.Enemy;

public class Board {
	
	public Tile[][] Board;
	public player p;
	private static Board _Board = null;
	public ArrayList<Enemy> EnemyList;
	
	private Board (String board)
	{
		//TODO create board and update player field don't forget to get player location and enemies into enemy list
	}
	
	public static Board setUp(String board)
	{
		if(_Board == null)
			{
				_Board = new Board (board);
				return _Board;
			}
		else return _Board;
	}
	
	public static Board GetInstance()
	{
		return _Board;
	}
	
	public Tile GetTile(int x, int y)
	{
		int outputx=0;
		int outputy=0;
		if(x>Board.length)
			outputx = Board.length-1;
		else if(x<0)
			outputx = 0;
		if(y>Board[outputx].length)
			outputy = Board[outputx].length-1;
		else if(y<0)
			outputy = 0;
		return Board[outputx][outputy];
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
	
	public ArrayList<Enemy> getAllEnemies()
	{
		return this.EnemyList;
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
	public void KillEnemy(Enemy t)
	{
		EnemyList.remove(t);
		Board[t.x][t.y]= new Empty(t.x,t.y);
	}
}