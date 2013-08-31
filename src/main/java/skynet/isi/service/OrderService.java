package skynet.isi.service;

import java.util.List;

import skynet.isi.exception.MailFailException;
import skynet.isi.model.Order;
import skynet.isi.model.OrderItem;
import skynet.isi.resultmodel.ShoppingListModel;

public interface OrderService {

	public int sumbitOrder(OrderInfo orderInfo,
			List<ShoppingListModel> shoppingList, String loginName) throws MailFailException;
	
	public void sendCustmerEmail(int orderId) throws MailFailException;

	public Order getOrder(OrderInfo orderInfo,
			List<ShoppingListModel> shoppingList, String loginName);

	public OrderItem getOrderItem(int cartId);

	public void deleteShoppingCart(List<ShoppingListModel> shoppingList);

}
