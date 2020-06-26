import java.util.ArrayList;

import General.Board;
import General.GameManager;
import Player.Warrior;
import Player.player;
import TileType.Tile;
import enemies.Enemy;
import enemies.Monster;
import misc.Resource;

public class TESTCLASS {
	public static void main(String[] args)
	{
		Board currb = Board.setUp("");
		player P = new Warrior(0,0,10,10,"Ilay",new Resource(5)); //int x, int y, int def, int att, String _name, Resource cooldown
		Monster m = new Monster(1,0,1,1,1,"hadar",'h',3,3); //int x, int y,int vision,int exp,int maxhp, String name, char Character ,int def, int att
		currb.Board= new Tile[2][1];
		currb.EnemyList = new ArrayList<Enemy>();
		currb.EnemyList.add(m);
		currb.p = P;
		GameManager GM = new GameManager();
		GM.addObserver(P);
		GM.addObserver(m);
		P.Interact(m);
	}

}
