package misc;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



@SuppressWarnings("deprecation")
public class miscTests extends TestCase {
	Pool testingpool;
	Resource testingR;
	@BeforeAll
	protected void setUp() throws Exception {
		super.setUp();
		testingpool = new Pool(10);
		testingR = new Resource(5);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testdecrease()
	{
		Assert.assertTrue(testingpool.ReduceCurr(100));
	}
	@Test
	public void testAdd() {
		testingpool.Add(40);
		Assert.assertEquals(10, testingpool.current);
	}
	@Test
	public void testAv() {
		testingR.Reset();
		for(int i =0; i<7; i++)
			testingR.Tick();
		Assert.assertTrue(testingR.isAvailable());
	}
	@Test
	public void testav2()
	{
		testingR.Reset();
		for(int i =0; i<2; i++)
			testingR.Tick();
		Assert.assertEquals(false, testingR.isAvailable());
	}

}
