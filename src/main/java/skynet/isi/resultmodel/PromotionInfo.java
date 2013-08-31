package skynet.isi.resultmodel;

public class PromotionInfo {
	private Integer banner;
	private String titlePicUrl;
	private String picUrl;
	private String description;
	private String link;
	private String startTime;
	private String endTime;

	public PromotionInfo() {
		super();
	}

	public PromotionInfo(Integer banner, String titlePicUrl, String picUrl,
			String description, String link, String startTime, String endTime) {
		super();
		this.banner = banner;
		this.titlePicUrl = titlePicUrl;
		this.picUrl = picUrl;
		this.description = description;
		this.link = link;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Integer getBanner() {
		return banner;
	}

	public void setBanner(Integer banner) {
		this.banner = banner;
	}

	public String getTitlePicUrl() {
		return titlePicUrl;
	}

	public void setTitlePicUrl(String titlePicUrl) {
		this.titlePicUrl = titlePicUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
