package skynet.isi.common;

public class Enums {

	public enum AUTH_RESULT {
		SUCCESS, FAIL, INPUT_ILLEGAL
	}

	public enum EMAIL_TEMPLATE {

		REGISTER("register.ftl", "艾矽电子-激活邮箱"), FIND_PASSWORD("findpassword.ftl", "艾矽电子-找回密码"), CONFIRM_ORDER("confirmorder.ftl", "艾矽电子-订单确认"), RECEIVE_ORDER("receiveorder.ftl", "艾矽电子-新订单");

		private String title;

		private String templateName;

		private EMAIL_TEMPLATE(String templateName, String title) {
			this.templateName = templateName;
			this.title = title;
		}

		public String getTemplateName() {
			return templateName;
		}

		public String getTitle() {
			return title;
		}

	}
	
	public enum REGION_ID{
		EAST, SOUTH, NORTH, CENTER_WEST
	}
	
	public enum CUSTOMER_TYPE{
		INTER, TERMINAL
	}

	public enum RESULT {
		SUCESS, PASSWORD_ERROR, FAILED, ALREADY_EXIST, NOT_EXIST
	}
}
