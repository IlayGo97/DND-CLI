package General;
import static org.junit.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Player.player;
import junit.framework.Assert;

class BoardTests {
	Board currBoard;
	player p;
	
	@BeforeEach
	void setUp() throws Exception {
		List<String> board = new ArrayList<String>();
		board.add("####");
		board.add("#@k#");
		board.add("####");
		p = GameManager.Ygritte;
		Board.setUp(board, p);
		p.ReloadBoard();
		currBoard = Board.GetInstance();
	}

	@Test
	void testAbility() {
		p.SpecialAbility();
		Assert.assertNotSame(currBoard.EnemyList.get(0).healthPool.current, currBoard.EnemyList.get(0).healthPool.max);
	}
	
	@Test
	void testSwap() {
		currBoard.SwapPlaces(p, currBoard.GetTile(p.x, p.y+1));
		System.out.println(currBoard);
	}
	
	@Test
	void testWall() {
		int prevx = p.x;
		int prevy = p.y;
		p.InteractLeft();
		boolean actual = p.x == prevx & p.y == prevy;
		Assert.assertEquals(true, actual);	
	}
}
