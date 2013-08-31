package skynet.isi.service.impl;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.resultmodel.ProductInfo;
import skynet.isi.service.ExcelParserHelperService;
import skynet.isi.service.ModifyDataService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UpdateDataServiceImplTest {

	@Autowired
	ModifyDataService update;

	@Autowired
	ExcelParserHelperService excelParserHelperService;

	@Test
	public void testGetInfo() {
		ProductInfo model = update.getInfo("1000001");
		Assert.assertEquals("1000001", model.getIccSku());
		Assert.assertEquals("1000208", model.getDistySku());
		Assert.assertEquals("4194", model.getMfrSku());
		Assert.assertEquals("JOHANSON MANUFACTURING", model.getManufacturer());
		Assert.assertEquals("ADJUSTMENT TOOL TRIMMING POT; Accessory",
				model.getDesc());
		Assert.assertEquals("ADJUSTMENT TOOL TRIMMING POT; Accessory",
				model.getLongDesc());
		Assert.assertEquals("EA", model.getUom());
		Assert.assertEquals("0", model.getPackSize());
		Assert.assertEquals("YES", model.getRohsStatus());
		Assert.assertEquals("http://www.johansonmfg.com/Catalog/Thin-Trim.pdf",
				model.getDataSheet());
		Assert.assertEquals("", model.getImageURL());
		Assert.assertEquals("DO", model.getCountryOrigin());
		Assert.assertEquals("1.0", model.getOneAmount());
		Assert.assertEquals("85.8", model.getOnePrice());

		model.setManufacturer("WIHA");
		model.setOnePrice("50");
		update.update(model);
	}

	@Test
	public void testUpdate() {
		ProductInfo model = update.getInfo("1000001");

		Assert.assertEquals("WIHA", model.getManufacturer());
		Assert.assertEquals("50.0", model.getOnePrice());
	}

	@Test
	public void testParser() throws IOException {
		update.process("update.xls", false);
		ProductInfo model = update.getInfo("1000001");
		Assert.assertEquals("JOHANSON MANUFACTURING", model.getManufacturer());
		Assert.assertEquals("80.0", model.getOnePrice());
	}

}
