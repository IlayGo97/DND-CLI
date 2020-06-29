package misc;

import junit.framework.TestCase;

public class miscTests extends TestCase {
	Pool testingpool;
	protected void setUp() throws Exception {
		super.setUp();
		testingpool = new Pool(10);
	}

}
