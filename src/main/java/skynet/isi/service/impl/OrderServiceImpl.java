package skynet.isi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import skynet.isi.common.Enums.EMAIL_TEMPLATE;
import skynet.isi.dao.OrderDao;
import skynet.isi.dao.OrderItemDao;
import skynet.isi.dao.ShoppingCartDao;
import skynet.isi.exception.MailFailException;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Order;
import skynet.isi.model.OrderItem;
import skynet.isi.model.Product;
import skynet.isi.model.ShoppingCart;
import skynet.isi.model.User;
import skynet.isi.resultmodel.OrderAmount;
import skynet.isi.resultmodel.ShoppingListModel;
import skynet.isi.service.AccountService;
import skynet.isi.service.CommonService;
import skynet.isi.service.EmailTemplateService;
import skynet.isi.service.OrderInfo;
import skynet.isi.service.OrderService;
import skynet.isi.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Autowired
	private EmailTemplateService emailService;

	@Autowired
	private ShoppingCartService shoppingCartServie;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CommonService commonService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * <pre>
	 * 		1. save order to db
	 * 		2. send notification email to customer and sales
	 * 		3. forward page to taobao
	 * </pre>
	 * @throws MailFailException 
	 */
	public int sumbitOrder(OrderInfo orderInfo,
			List<ShoppingListModel> shoppingList, String loginName) throws MailFailException {
		Order order = getOrder(orderInfo, shoppingList, loginName);
		orderDao.save(order);

		Set<OrderItem> items = new HashSet<OrderItem>();

		for (ShoppingListModel model : shoppingList) {
			OrderItem item = getOrderItem(model.getShoppingCartId());
			item.setOrder(order);
			orderItemDao.save(item);
			items.add(item);
		}

		deleteShoppingCart(shoppingList);

		order.setOrderItems(items);
		
		Map<String,String> params = prepareParam(order);
		sendSalesEmail(params);
		return order.getId();
	}
	
	public void sendCustmerEmail(int orderId) throws MailFailException{
		Order order = orderDao.get(orderId);
		Map<String,String> params = prepareParam(order);
		sendCustomerEmail(params, order);
	}
	
	private Map<String, String> prepareParam(Order order){
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginName", order.getUser().getLoginName());
		params.put("table", generatOrderItemTable(order));
		params.put("totalBeforeTax", "" + order.getPreTaxPrice());
		params.put("freight", "" + order.getFreight());
		params.put("tax", "" + order.getValueAddedTax());
		params.put("total", "" + order.getTotalPrice());
		params.put("orderDate", sdf.format(order.getOrderDate()));
		params.put("paymentType", order.getPayMethod());
		params.put("invoiceType", order.getInvoiceType());
		params.put("receiver", order.getReceiver());
		params.put("receiveAddr",
				(order.getAddressRegion() == null?"":order.getAddressRegion()) + order.getAddressDetail());
		params.put("mobile", order.getMobile());
		params.put("phone", order.getTelephone());
		User usr = accountService.getUserByLoginName(order.getUser().getLoginName());
		params.put("email", usr.getEmail() == null ? "": usr.getEmail());
		return params;
	}

	public Order getOrder(OrderInfo orderInfo,
			List<ShoppingListModel> shoppingList, String loginName) {

		OrderAmount amount = shoppingCartServie.getOrderAmount(shoppingList);

		Order order = new Order();
		order.setAccount(orderInfo.getAccount());
		order.setAddressDetail(orderInfo.getAddressDetail());
		order.setAddressLabel(orderInfo.getAddressLabel());
		order.setAddressRegion((orderInfo.getRegion1() == null?"":orderInfo.getRegion1()) + (orderInfo.getRegion2() == null?"":orderInfo.getRegion2())
				+ (orderInfo.getRegion3() == null?"":orderInfo.getRegion3()));
		order.setBank(orderInfo.getBank());
		order.setFreight(Double.parseDouble(amount.getFreight()));
		order.setInvoiceAddress(orderInfo.getInvoiceAddress());
		order.setInvoicePhone(orderInfo.getInvoiceRegionNo()
				+ orderInfo.getInvoiceTelephone()
				+ orderInfo.getInvoiceExtensionNo());
		order.setInvoiceTitle(orderInfo.getInvoiceTitle());
		order.setInvoiceType(orderInfo.getInvoiceType());
		order.setMobile(orderInfo.getMobile());
		order.setOrderDate(new Date());
		order.setPayMethod(orderInfo.getPayMethod());
		order.setPreTaxPrice(Double.parseDouble(amount.getPreTaxPrice()));
		order.setReceiver(orderInfo.getReceiver());
		order.setShippingMethod(orderInfo.getExpressDelivery());
		order.setTaxpayerNo(orderInfo.getTaxpayerNo());
		order.setTelephone(orderInfo.getTelephone());
		order.setTotalPrice(Double.parseDouble(amount.getTotalPrice()));
		order.setUser(accountService.getUserByLoginName(loginName));
		order.setValueAddedTax(Double.parseDouble(amount.getValueAddedTax()));
		order.setZipCode(orderInfo.getZipCode());

		return order;
	}

	public OrderItem getOrderItem(int cartId) {
		ShoppingCart sc = shoppingCartDao.get(cartId);
		OrderItem item = new OrderItem();
		item.setOrderNum(sc.getIntentNum());
		item.setProduct(sc.getProduct());
		item.setUnitPrice(sc.getProduct().getOnePrice());
		return item;
	}

	public void deleteShoppingCart(List<ShoppingListModel> shoppingList) {
		for (ShoppingListModel sc : shoppingList) {
			shoppingCartDao.delete(sc.getShoppingCartId());
		}
	}
	
	public void sendSalesEmail(Map<String,String> params) throws MailFailException{
		List<String> toAdminList = new ArrayList<String>();
		String adminEmail = "sales@iic.net.cn";
		toAdminList.add(adminEmail);
		emailService.send(EMAIL_TEMPLATE.RECEIVE_ORDER, toAdminList, params);
	}

	public void sendCustomerEmail(Map<String,String> params, Order order) throws MailFailException {
		String customerEmail = order.getUser().getEmail();
		params.put("email", customerEmail);
		List<String> toCustomerList = new ArrayList<String>();
		toCustomerList.add(customerEmail);
		emailService.send(EMAIL_TEMPLATE.CONFIRM_ORDER, toCustomerList, params);
	}

	private String generatOrderItemTable(Order order) {
		String table = "";
		Set<OrderItem> items = order.getOrderItems();
		for (OrderItem item : items) {
			Product product = item.getProduct();
			Manufacturer mf = product.getManufacturer();
			double unitPrice = product.getOnePrice();
			double num = item.getOrderNum();
			double totalPrice = unitPrice * num;
			table += "<tr><td>" + product.getIicSku() + "</td><td>"
					+ mf.getMfrName() + "</td><td>" + product.getMfrSku()
					+ "</td><td>" + (product.getDescription() == null?"":product.getDescription()) + "</td><td>"
					+ unitPrice + "</td><td>" + num + "</td><td>" + totalPrice + "</td></tr>";
		}
		return table;
	}
	
	public static void main(String [] args){
		List<String> test = new ArrayList<String>();
		test.add(null);
		
	}
}
