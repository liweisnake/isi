package skynet.isi.service.impl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.resultmodel.PromotionInfo;
import skynet.isi.service.AdminService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class AdminServiceUpdatePromotionTest {

	@Autowired
	AdminService service;
	Date date = new Date();

	@Test
	public void testUpdatePromotion_update() throws Exception {
		System.out.println(date.getTime());
		PromotionInfo p = service.getPromotionList().get(0);
		p.setStartTime("1000-10-10");
		service.updatePromotion(p);
	}

	@Test
	public void testUpdatePromotion_validate() {
		List<PromotionInfo> ps1 = service.getPromotionList();
		for (PromotionInfo p : ps1) {
			Assert.assertEquals("1000-10-10", p.getStartTime().toString());
		}
	}
}
