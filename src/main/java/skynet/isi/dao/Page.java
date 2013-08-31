package skynet.isi.dao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class Page<T> {
	// public variable
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	// page parameter
	protected int pageNo = 1;
	protected int pageSize = 1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	// return result
	protected List<T> result = Collections.emptyList();
	protected long totalCount = -1;

	/**
	 * Construct
	 */
	public static <T> Page<T> getInstance(int start, int limit) {
		if (limit <= 0) {
			return new Page<T>(1, 0);
		} else {
			return new Page<T>(start / limit + 1, limit);
		}
	}

	/**
	 * Construct
	 * 
	 * @param pageSize
	 */
	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * get page no
	 * 
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * set page no
	 * 
	 * @param pageNo
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * page no
	 * 
	 * @param thePageNo
	 * @return
	 */
	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * get page size (default = 1)
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * set page size
	 * 
	 * @param pageSize
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * page size
	 * 
	 * @param thePageSize
	 * @return
	 */
	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * get the position in all records with pageNo and pageSize
	 * 
	 * @return
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * get order field. mutli field seperated with ','
	 * 
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * set order field. mutli field seperated with ','
	 * 
	 * @return
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * set order field
	 * 
	 * @param theOrderBy
	 * @return
	 */
	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * get order direct
	 * 
	 * @return
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * set order direct
	 * 
	 * @param order
	 *            (desc or asc) mutli field seperated with ','
	 */
	public void setOrder(final String order) {
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr)
					&& !StringUtils.equals(ASC, orderStr))
				throw new IllegalArgumentException("Paramter order:" + orderStr
						+ " is no valid.");
		}

		this.order = StringUtils.lowerCase(order);
	}

	/**
	 * set Order
	 * 
	 * @param theOrder
	 * @return
	 */
	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * is order or not
	 * 
	 * @return
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils
				.isNotBlank(order));
	}

	/**
	 * auto count the count of all records (default = false)
	 * 
	 * @return
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * set autoCount
	 * 
	 * @param autoCount
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * set autoCount
	 * 
	 * @param theAutoCount
	 * @return
	 */
	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	/**
	 * get record list in current page
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * set record list in current page
	 * 
	 * @param result
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * get count of all records (default = -1)
	 * 
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * set count of all records
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * getTotalPages (default = -1)
	 * 
	 * @return
	 */
	public long getTotalPages() {
		if (totalCount < 0)
			return -1;

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * isHasNext
	 * 
	 * @return
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * getNextPage
	 * 
	 * @return
	 */
	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * isHasPre
	 * 
	 * @return
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * getPrePage
	 * 
	 * @return
	 */
	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}

	public String getPageToolBar1(String pageAction) {
		String str = "";
		if (pageNo > 0)
			str += "<a href='" + pageAction + "?start=" + (pageNo - 1)
					+ "&limit=" + pageSize + "'>上一页</a> ";
		if (getTotalPages() >= pageNo + 1)
			str += "<a href='" + pageAction + "?start=" + (pageNo - 1)
					+ "&limit=" + pageSize + "'>下一页</a>";
		return str;
	}

	public String getPageToolBar2(String pageAction) {
		String str = "";
		int pageSplit = (pageNo / 5) * 5;
		for (int i = pageSplit - 1; i < (pageSplit + 6); i++) {
			if (i <= 0) {
			} else if (pageNo == i) {
				str += "<span style=color:#FF6600>" + i + "</span>";
			} else if (i > getTotalPages()) {
			} else {
				if (pageAction.contains("pageNo")
						&& pageAction.contains("pageSize")) {
					pageAction = StringUtils.substringBefore(pageAction,
							"&pageNo=");
				}
				str += "<a href='" + pageAction
						+ (!pageAction.contains("?") ? "?" : "") + "&pageNo="
						+ i + "&pageSize=" + pageSize + "'>" + i + "</a>&nbsp;"
						+ " ";
			}
		}
		return str;
	}

	public String getFirstPageLink(String pageAction) {
		return pageAction + (!pageAction.contains("?") ? "?" : "") + "&pageNo=1&pageSize=" + pageSize;
	}

	public String getLastPageLink(String pageAction) {
		return pageAction + (!pageAction.contains("?") ? "?" : "") + "&pageNo=" + getTotalPages() + "&pageSize="
				+ pageSize;
	}

	public String getPageToolBar3(String pageAction) {
		String str = "";
//		str += "<td width='8%'><a href='"
//				+ pageAction
//				+ "?&pageNo=1&pageSize="
//				+ pageSize
//				+ "'>"
//				+ "<img src='${ctx}/image/search/search-10-l.jpg' width='21' height='20'></a></td>";


		if (pageNo < 100)
			str += "&nbsp;&nbsp;&nbsp;";
		
		if (pageNo > 3)
			str += "<span style=color:#FF6600>&nbsp;...&nbsp;</span>";
		
		if (pageNo <100)
			str += "&nbsp;";

		long pageSplit = pageNo;
		if (pageSplit < 3)
			pageSplit = 3;
		else if (pageSplit > getTotalPages() - 2)
			pageSplit = getTotalPages() - 2;
		for (long i = pageSplit - 2; i < (pageSplit + 3); i++) {
			if (i <= 0) {
			} else if (pageNo == i) {
				str += "<span style=color:#FF6600>" + i + "</span>&nbsp;";
			} else if (i > getTotalPages()) {
			} else {
				if (pageAction.contains("pageNo")
						&& pageAction.contains("pageSize")) {
					pageAction = StringUtils.substringBefore(pageAction,
							"&pageNo=");
				}
				str += "<a href='" + pageAction
						+ (!pageAction.contains("?") ? "?" : "") + "&pageNo="
						+ i + "&pageSize=" + pageSize + "'>" + i + "</a>&nbsp;";
			}
			if (i < 100)
				str += "&nbsp;&nbsp;";
		}

		if (pageNo < getTotalPages() - 2)
			str += "<span style=color:#FF6600>&nbsp;...&nbsp;</span>";

//		str += "<td width='8%' align='right'><a href='"
//				+ pageAction
//				+ "?&pageNo="
//				+ getTotalPages()
//				+ "&pageSize="
//				+ pageSize
//				+ "'>"
//				+ "<img src='${ctx}/image/search/search-10-r.jpg' width='21' height='20'></a></td>";

		return str;
	}
}
