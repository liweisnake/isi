package skynet.isi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;
import skynet.isi.common.Enums;
import skynet.isi.exception.MailFailException;
import skynet.isi.service.EmailTemplateService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class EmailTemplateServiceTest extends DBEnablerTestBase {

	@Autowired
	EmailTemplateService service;

	@Test
	public void testSendEmail() throws MailFailException {
		String email = "liwei_snake@yahoo.com.cn";
		List<String> list = new ArrayList<String>();
		list.add(email);
		String loginName = "levi";
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginName", loginName);
		service.send(Enums.EMAIL_TEMPLATE.REGISTER, list, params);

//		params.put("password", "123");
//		service.send(Enums.EMAIL_TEMPLATE.FIND_PASSWORD, list, params);
	}

	@Override
	public String dataSetFileName() {
		return "data_init.xml";
	}

}
