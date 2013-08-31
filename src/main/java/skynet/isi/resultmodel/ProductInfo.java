package skynet.isi.resultmodel;

public class ProductInfo {
	private String iccSku;
	private String distySku;
	private String mfrSku;
	private String manufacturer;
	private String desc;
	private String longDesc;
	private String uom;
	private String packSize;
	private String rohsStatus;
	private String dataSheet;
	private String imageURL;
	private String countryOrigin;
	private String store;
	private String oneAmount;
	private String onePrice;

	public ProductInfo() {
		super();
	}

	public ProductInfo(String iccSku, String distySku, String mfrSku,
			String manufacturer, String desc, String longDesc, String uom,
			String packSize, String rohsStatus, String dataSheet,
			String imageURL, String countryOrigin, String store,
			String oneAmount, String onePrice) {
		super();
		this.iccSku = iccSku;
		this.distySku = distySku;
		this.mfrSku = mfrSku;
		this.manufacturer = manufacturer;
		this.desc = desc;
		this.longDesc = longDesc;
		this.uom = uom;
		this.packSize = packSize;
		this.rohsStatus = rohsStatus;
		this.dataSheet = dataSheet;
		this.imageURL = imageURL;
		this.countryOrigin = countryOrigin;
		this.store = store;
		this.oneAmount = oneAmount;
		this.onePrice = onePrice;
	}

	public String getIccSku() {
		return iccSku;
	}

	public void setIccSku(String iccSku) {
		this.iccSku = iccSku;
	}

	public String getDistySku() {
		return distySku;
	}

	public void setDistySku(String distySku) {
		this.distySku = distySku;
	}

	public String getMfrSku() {
		return mfrSku;
	}

	public void setMfrSku(String mfrSku) {
		this.mfrSku = mfrSku;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public String getRohsStatus() {
		return rohsStatus;
	}

	public void setRohsStatus(String rohsStatus) {
		this.rohsStatus = rohsStatus;
	}

	public String getDataSheet() {
		return dataSheet;
	}

	public void setDataSheet(String dataSheet) {
		this.dataSheet = dataSheet;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getCountryOrigin() {
		return countryOrigin;
	}

	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getOneAmount() {
		return oneAmount;
	}

	public void setOneAmount(String oneAmount) {
		this.oneAmount = oneAmount;
	}

	public String getOnePrice() {
		return onePrice;
	}

	public void setOnePrice(String onePrice) {
		this.onePrice = onePrice;
	}

}
