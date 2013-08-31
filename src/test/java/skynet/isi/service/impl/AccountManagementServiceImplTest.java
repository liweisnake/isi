package skynet.isi.service.impl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;
import skynet.isi.common.Enums.RESULT;
import skynet.isi.model.User;
import skynet.isi.service.AccountManager;
import skynet.isi.service.AccountService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class AccountManagementServiceImplTest extends DBEnablerTestBase {

	@Autowired
	AccountService accountService;

	@Autowired
	AccountManager accountManagementService;

	@Override
	public String dataSetFileName() {
		return "data_init.xml";
	}

	@Test
	public void testModifyUserBaseInfo() {
		User user = new User();
		user.setAddress("new address");
		user.setEmail("new email");
		user.setPhone("12345678");
		RESULT result = accountManagementService.modifyUserBaseInfo("hong", user);
		Assert.assertEquals(RESULT.SUCESS, result);

		User newUser = accountService.getUserByLoginName("hong");
		Assert.assertEquals("new address", newUser.getAddress());
		Assert.assertEquals("new email", newUser.getEmail());
		Assert.assertEquals("12345678", newUser.getPhone());
	}


	@Test
	public void testGetUserCredit() {
		int credit = accountManagementService.getUserCredit("hong");
		Assert.assertEquals(2, credit);
	}

}
