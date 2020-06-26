import General.Board;
import General.GameManager;
import Player.Warrior;
import Player.player;
import TileType.Tile;
import enemies.Monster;
import misc.Resource;

public class TESTCLASS {
	public void Main(String[] args)
	{
		player P = new Warrior(0,0,10,10,"yosi",new Resource(5)); //int x, int y, int def, int att, String _name, Resource cooldown
		Monster m = new Monster(1,0,1,1,5,"hadar",'h',10,10); //int x, int y,int vision,int exp,int maxhp, String name, char Character ,int def, int att
		Board currb = Board.GetInstance();
		currb.Board= new Tile[2][1];
		currb.p = P;
		GameManager GM = new GameManager();
		GM.addObserver(P);
		GM.addObserver(m);
	}

}
