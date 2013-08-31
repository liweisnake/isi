package skynet.isi.resultmodel;

public class ShoppingListModel {

	private Integer shoppingCartId;

	private String iicSku;

	private int intentNum;

	private String mfrSku;

	private String description;

	private String rohsStatus;

	private String store;

	private Double onePrice;

	private String totalPrice;

	public ShoppingListModel() {
		super();
	}

	public ShoppingListModel(Integer shoppingCartId, String iicSku,
			int intentNum, String mfrSku, String description,
			String rohsStatus, String store, Double onePrice, String totalPrice) {
		super();
		this.shoppingCartId = shoppingCartId;
		this.iicSku = iicSku;
		this.intentNum = intentNum;
		this.mfrSku = mfrSku;
		this.description = description;
		this.rohsStatus = rohsStatus;
		this.store = store;
		this.onePrice = onePrice;
		this.totalPrice = totalPrice;
	}

	public Integer getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getIicSku() {
		return iicSku;
	}

	public void setIicSku(String iicSku) {
		this.iicSku = iicSku;
	}

	public int getIntentNum() {
		return intentNum;
	}

	public void setIntentNum(int intentNum) {
		this.intentNum = intentNum;
	}

	public String getMfrSku() {
		return mfrSku;
	}

	public void setMfrSku(String mfrSku) {
		this.mfrSku = mfrSku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRohsStatus() {
		return rohsStatus;
	}

	public void setRohsStatus(String rohsStatus) {
		this.rohsStatus = rohsStatus;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Double getOnePrice() {
		return onePrice;
	}

	public void setOnePrice(Double onePrice) {
		this.onePrice = onePrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
