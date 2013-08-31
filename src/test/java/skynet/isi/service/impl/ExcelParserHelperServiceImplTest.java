package skynet.isi.service.impl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;
import skynet.isi.service.ExcelParserHelperService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ExcelParserHelperServiceImplTest extends DBEnablerTestBase {

	@Autowired
	ExcelParserHelperService dataParserService;

	@Override
	public String dataSetFileName() {
		return "data_init.xml";
	}

	@Test
	public void testGetMfrMaxId() {
		Assert.assertEquals(3, dataParserService.getMfrMaxId());
	}

	@Test
	public void testGetCategoryMaxId() {
		Assert.assertEquals(6, dataParserService.getCategoryMaxId());
	}

}
