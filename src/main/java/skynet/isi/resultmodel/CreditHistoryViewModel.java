package skynet.isi.resultmodel;

import java.util.Date;

public class CreditHistoryViewModel {

	private String loginName;

	private String email;

	private int usedCredit;

	private Date useDate;

	private int leftCredit;

	public CreditHistoryViewModel(String loginName, String email, Date useDate,
			int usedCredit, int leftCredit) {
		super();
		this.loginName = loginName;
		this.email = email;
		this.usedCredit = usedCredit;
		this.useDate = useDate;
		this.leftCredit = leftCredit;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUsedCredit() {
		return usedCredit;
	}

	public void setUsedCredit(int usedCredit) {
		this.usedCredit = usedCredit;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public int getLeftCredit() {
		return leftCredit;
	}

	public void setLeftCredit(int leftCredit) {
		this.leftCredit = leftCredit;
	}

}
