package skynet.isi.service;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

public class ModifyPasswordInfo {

	@NotEmpty(message = "登录超时，请重新登录！")
	private String loginName;

	@NotEmpty(message = "旧密码不能为空！")
	private String oldPwd;

	@NotEmpty(message = "新密码不能为空！")
	private String newPwd;

	public ModifyPasswordInfo(String loginName, String oldPwd, String newPwd) {
		super();
		this.loginName = loginName;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
