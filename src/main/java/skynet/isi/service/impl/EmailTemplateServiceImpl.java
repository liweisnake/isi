package skynet.isi.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import skynet.isi.common.Enums.EMAIL_TEMPLATE;
import skynet.isi.common.SystemConfigConstant;
import skynet.isi.exception.MailFailException;
import skynet.isi.service.CommonService;
import skynet.isi.service.EmailTemplateService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Transactional
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

	protected static final Logger log = LogManager
			.getLogger(EmailTemplateServiceImpl.class.getName());

	@Autowired
	private FreeMarkerConfigurer mailTemplateEngine = null;

	@Autowired
	protected JavaMailSender mailSender;

	@Autowired
	private CommonService commonService;

	public void send(EMAIL_TEMPLATE template, List<String> toList,
			Map<String, String> params) throws MailFailException {
		boolean isAllNull = true;
		for (String to : toList) {
			if (StringUtils.isNotEmpty(to)) {
				isAllNull = false;
				break;
			}
		}
		if (isAllNull) {
			log.info("Email address is null, cancel");
		} else {
			String from = commonService.getSystemConfigStringProperty(
					SystemConfigConstant.ADMIN_MAIL_ACCOUNT,
					"postmaster@iic.net.cn");
			try {
				String content = generateEmailContent(
						template.getTemplateName(), params);
				log.info("send email for:" + template.getTemplateName()
						+ " to:" + toList);
				doSend(content, from, template.getTitle(), toList);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
				throw new MailFailException(ex.getMessage(), ex);
			}
		}
	}

	private void doSend(String content, String from, String title,
			List<String> toList) throws MessagingException,
			InterruptedException, ExecutionException, TimeoutException {
		final MimeMessage mimeMsg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, "utf-8");
		helper.setTo(toList.toArray(new String[1]));
		helper.setSubject(title);
		helper.setFrom(from);
		helper.setText(content, true);
		// mailSender.send(mimeMsg);

		FutureTask<Object> future = new FutureTask<Object>(new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				mailSender.send(mimeMsg);
				return true;
			}

		});
		Thread thread = new Thread(future);
		thread.start();
		
		//future.get(3, TimeUnit.SECONDS);
	}

	private String generateEmailContent(String templateName,
			Map<String, String> map) {
		try {
			Template t = mailTemplateEngine.getConfiguration().getTemplate(
					templateName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
		} catch (TemplateException e) {
			log.error("Error while processing FreeMarker template ", e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("Error while open template file ", e);
		} catch (IOException e) {
			log.error("Error while generate Email Content ", e);
		}
		return null;
	}

}
