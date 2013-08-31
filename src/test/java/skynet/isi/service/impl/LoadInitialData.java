package skynet.isi.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class LoadInitialData extends DBEnablerTestBase {

	// @Before
	// public void setUp() throws Exception {
	// super.setUp();
	// }
	@Override
	public String dataSetFileName() {
		return "data_init.xml";
	}

	@Test
	public void testLoad() {
		assertTrue(true);
	}

}
