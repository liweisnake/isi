package skynet.isi.service;

import java.util.List;
import java.util.Map;

import skynet.isi.common.Enums;
import skynet.isi.exception.MailFailException;


public interface EmailTemplateService {

	public void send(Enums.EMAIL_TEMPLATE template, List<String> toList, Map<String, String> params) throws MailFailException;
	
}
