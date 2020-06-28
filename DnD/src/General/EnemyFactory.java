package General;
import enemies.*;

public class EnemyFactory {
	
	public static Enemy Create(char c)
	{
		Enemy e = null;
		switch(c) {
			case 's':
				e = new Monster(0, 0, "Lannister Solider", 80, 8, 3, 25, 3, c);
				break;
			case 'k':
				e = new Monster(0, 0, "Lannister Knight", 200, 14, 8, 50, 4, c);
				break;
			case 'q':
				e = new Monster(0, 0, "Queen�s Guard", 400, 20, 15, 100, 5, c);
				break;
			case 'z':
				e = new Monster(0, 0, "Wright", 600, 30, 15, 100, 3, c);
				break;
			case 'b':
				e = new Monster(0, 0, "Bear-Wright", 1000, 75, 30, 250, 4, c);
				break;
			case 'g':
				e = new Monster(0, 0, "Giant-Wright", 1500, 100, 40, 500, 5, c);
				break;
			case 'w':
				e = new Monster(0, 0, "White Walker", 2000, 150, 50, 1000, 6, c);
				break;
			case 'M':
				e = new Monster(0, 0, "The Mountain", 1000, 60, 25, 500, 6, c);
				break;
			case 'C':
				e = new Monster(0, 0, "Queen Cersei", 100, 10, 10, 1000, 1, c);
				break;
			case 'K':
				e = new Monster(0, 0, "Night�s King", 5000, 300, 150, 5000, 8, c);
				break;
		}
		return e;
	}
}