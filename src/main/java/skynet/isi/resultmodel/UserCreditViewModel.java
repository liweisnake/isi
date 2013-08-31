package skynet.isi.resultmodel;

import java.util.Date;

public class UserCreditViewModel {

	private int id;

	private String loginName;

	private String email;

	private int lastLeftCredit;

	private long totalUsedCredit;

	private int totalLeftCredit;

	private Date lastUseDate;

	public UserCreditViewModel() {
		super();
	}

	public UserCreditViewModel(int id, String loginName, String email,
			long totalUsedCredit, int totalLeftCredit, Date lastUseDate) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.email = email;
		this.totalUsedCredit = totalUsedCredit;
		this.totalLeftCredit = totalLeftCredit;
		this.lastUseDate = lastUseDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getLastLeftCredit() {
		return lastLeftCredit;
	}

	public void setLastLeftCredit(int lastLeftCredit) {
		this.lastLeftCredit = lastLeftCredit;
	}

	public long getTotalUsedCredit() {
		return totalUsedCredit;
	}

	public void setTotalUsedCredit(long totalUsedCredit) {
		this.totalUsedCredit = totalUsedCredit;
	}

	public int getTotalLeftCredit() {
		return totalLeftCredit;
	}

	public void setTotalLeftCredit(int totalLeftCredit) {
		this.totalLeftCredit = totalLeftCredit;
	}

	public Date getLastUseDate() {
		return lastUseDate;
	}

	public void setLastUseDate(Date lastUseDate) {
		this.lastUseDate = lastUseDate;
	}

}
