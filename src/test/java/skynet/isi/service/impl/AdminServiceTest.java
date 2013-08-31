package skynet.isi.service.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;
import skynet.isi.dao.Page;
import skynet.isi.resultmodel.CreditHistoryViewModel;
import skynet.isi.resultmodel.PromotionInfo;
import skynet.isi.resultmodel.UserCreditViewModel;
import skynet.isi.service.AdminService;
import skynet.isi.service.PagingModel;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class AdminServiceTest extends DBEnablerTestBase {

	@Autowired
	AdminService service;

	@Test
	public void testGetPromotionList() {
		List<PromotionInfo> list = service.getPromotionList();
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void testGetCreditHistoryViewModel() {
		PagingModel model = new PagingModel(1, 0, 0);
		Page<CreditHistoryViewModel> list = service.getCreditHistoryViewModel(
				model, "levi");
		Assert.assertEquals(2, list.getResult().size());
	}

	@Test
	public void testGetUserCreditViewModel() {
		UserCreditViewModel model = service.getUserCreditViewModel("levi");
		Assert.assertEquals(1000, model.getTotalLeftCredit());
		Assert.assertEquals(300, model.getTotalUsedCredit());
		Assert.assertEquals("2011-11-11", model.getLastUseDate().toString());
		Assert.assertEquals(700, model.getLastLeftCredit());
	}

	@Override
	public String dataSetFileName() {
		return "data_init.xml";
	}

}
