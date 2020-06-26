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
		player P = new Warrior(0,0,"Ilay",0,0,3,300); // int x, int y, String name, int maxHP, int att, int def, int cooldown
		Monster m = new Monster(1,0,"hadar",30,30,32,1,1,'h'); // (int x, int y, String name, int maxHP, int att, int def, int exp, int visionRange, char Char)
		currb.Board = new Tile[2][1];
		currb.EnemyList = new ArrayList<Enemy>();
		currb.EnemyList.add(m);
		currb.p = P;
		GameManager GM = new GameManager();
		GM.addObserver(P);
		GM.addObserver(m);
		P.SpecialAbility();
	}

}
