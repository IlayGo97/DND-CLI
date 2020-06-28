package General;
import java.util.ArrayList;
import java.util.List;

import Player.player;
import TileType.Empty;
import TileType.Tile;
import TileType.Unit;
import TileType.*;
import enemies.Enemy;

public class Board {
	
	public Tile[][] Board;
	public player p;
	private static Board _Board = null;
	public ArrayList<Enemy> EnemyList;
	
	private Board (List<String> board, player p) throws Exception
	{
		EnemyList = new ArrayList<Enemy>();
		int currX =0;
		int currY =0;
		Board = new Tile[board.size()][board.get(0).length()];
		for(String Row : board)
		{
			for(char c : Row.toCharArray())
			{
				switch(c)
				{
					case '#':
						Wall w = new Wall(currY,currX);
						Board[currY][currX] = w;
						break;
					case '.':
						Empty e = new Empty(currY,currX);
						Board[currY][currX] = e;
						break;
					case '@':
						this.p = p;
						p.x=currY;
						p.y=currX;
						Board[currY][currX] = p;
						break;
					case ' ':
						break;
					default:
						Enemy enemy = EnemyFactory.Create(c);
						enemy.x=currY;
						enemy.y=currX;
						Board[currY][currX] = enemy;
						EnemyList.add(enemy);
						break;
				}
				currX++;
			}
			currY++;
			currX = 0;
		}
	}
	
	public static Board setUp(List<String> board, player p) throws Exception
	{
		_Board = new Board(board, p);
		return _Board;
	}
	
	public static Board GetInstance()
	{
		return _Board;
	}
	
	public Tile GetTile(int x, int y)
	{
		int outputx = x;
		int outputy = y;
		if(x>=Board.length)
			outputx = Board.length-2;
		else if(x<0)
			outputx = 0;
		if(y>Board[outputx].length)
			outputy = Board[outputx].length-2;
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
	
	public ArrayList<Enemy> getAllCloseEnemies(Tile t, int range)
	{
		ArrayList<Enemy> CloseEnemies = new ArrayList<Enemy>();
		for(Enemy e : EnemyList)
		if(this.Range(t, e)<range)
			CloseEnemies.add(e);
		return CloseEnemies;
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
		Board[t.x][t.y] = new Empty(t.x,t.y);
	}
	
	@Override
	public String toString() {
		String output ="";
		for(Tile[] T: Board)
		{
			for(Tile t: T)
			{
				output = output+t;
			}
			output = output+"\r\n";
		}
		return output;
	}
}