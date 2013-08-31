package skynet.isi.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;
import skynet.isi.service.ModifyDataService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ImportDataServiceImplTest extends DBEnablerTestBase {

	@Autowired
	ModifyDataService update;

	@Override
	public String dataSetFileName() {
		return "clear.xml";
	}

	@Test
	public void testImport() {
		try {
			int t1 = (int) System.currentTimeMillis();
			List<String> failed = update.process("65-3.xls", false);
			int t2 = (int) System.currentTimeMillis();
			System.out.println("Total time is: " + (t2 - t1));
			for (String msg : failed)
				System.out.println(msg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
