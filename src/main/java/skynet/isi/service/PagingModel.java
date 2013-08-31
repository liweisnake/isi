package skynet.isi.service;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

public class PagingModel {

	@NotEmpty
	@Min(value = 1)
	private int start;

	private int limit;

	private int pageNo;

	public PagingModel() {
		super();
	}

	public PagingModel(int start, int limit, int pageNo) {
		super();
		this.start = start;
		this.limit = limit;
		this.pageNo = pageNo;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
