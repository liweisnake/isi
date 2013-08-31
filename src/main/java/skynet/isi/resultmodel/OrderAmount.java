package skynet.isi.resultmodel;

public class OrderAmount {
	private String preTaxPrice;
	private String valueAddedTax;
	private String freight;
	private String totalPrice;

	public OrderAmount() {
		super();
	}

	public OrderAmount(String preTaxPrice, String valueAddedTax,
			String freight, String totalPrice) {
		super();
		this.preTaxPrice = preTaxPrice;
		this.valueAddedTax = valueAddedTax;
		this.freight = freight;
		this.totalPrice = totalPrice;
	}

	public String getPreTaxPrice() {
		return preTaxPrice;
	}

	public void setPreTaxPrice(String preTaxPrice) {
		this.preTaxPrice = preTaxPrice;
	}

	public String getValueAddedTax() {
		return valueAddedTax;
	}

	public void setValueAddedTax(String valueAddedTax) {
		this.valueAddedTax = valueAddedTax;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
