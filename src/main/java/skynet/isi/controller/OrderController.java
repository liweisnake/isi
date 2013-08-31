package skynet.isi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import skynet.isi.exception.MailFailException;
import skynet.isi.resultmodel.ShoppingListModel;
import skynet.isi.service.OrderInfo;
import skynet.isi.service.OrderService;
import skynet.isi.service.PagingModel;
import skynet.isi.service.ShoppingCartService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@RequestMapping(value = "/frontend/checkout")
	public String checkout(PagingModel pagingModel, HttpServletRequest request,
			Model model) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		List<ShoppingListModel> shoppingList = new ArrayList<ShoppingListModel>();

		int rowNum = 1;
		Map<String, String[]> requestMap = request.getParameterMap();
		String[] cartId = requestMap.get("shoppingCartId" + rowNum);
		while (cartId != null && StringUtils.isNotEmpty(cartId[0])) {
			shoppingCartService.updateCart(loginName,
					Integer.parseInt(cartId[0]),
					Integer.parseInt(requestMap.get("intentNum" + rowNum)[0]));
			if (requestMap.get("checkbox" + rowNum) != null)
				shoppingList.add(shoppingCartService
						.getShoppingListModel(Integer.parseInt(cartId[0])));
			rowNum++;
			cartId = requestMap.get("shoppingCartId" + rowNum);
		}

		model.addAttribute("shoppingList", shoppingList);
		model.addAttribute("orderAmount",
				shoppingCartService.getOrderAmount(shoppingList));
		return "buy/fillOrder";
	}

	@RequestMapping(value = "/frontend/submitOrder")
	public String submitOrder(PagingModel pagingModel, OrderInfo info,
			HttpServletRequest request, Model model) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		List<ShoppingListModel> shoppingList = new ArrayList<ShoppingListModel>();

		int rowNum = 1;
		Map<String, String[]> requestMap = request.getParameterMap();
		String[] cartId = requestMap.get("shoppingCartId" + rowNum);
		while (cartId != null && StringUtils.isNotEmpty(cartId[0])) {
			shoppingList.add(shoppingCartService.getShoppingListModel(Integer
					.parseInt(cartId[0])));
			rowNum++;
			cartId = requestMap.get("shoppingCartId" + rowNum);
		}

		int orderId = 0;
		try {
			orderId = orderService.sumbitOrder(info, shoppingList, loginName);
			model.addAttribute("msg", "感谢您提交订单,我们正在处理您的订单并会在第一时间和您联系。");
		} catch (MailFailException e) {
			model.addAttribute("msg", "订单已提交,但是您填写的邮箱可能因不正确而导致订单发送失败并影响您订单的处理速度，订单咨询请联系021-54370611.");
		}
		if(orderId > 0){
			try {
				orderService.sendCustmerEmail(orderId);
				model.addAttribute("msg", "感谢您提交订单.我们正在处理您的订单并会在第一时间和您联系。");
			} catch (MailFailException e) {
				model.addAttribute("msg", "订单已提交。但是您填写的邮箱可能因不正确而导致订单发送失败并影响您订单的处理速度，订单咨询请联系021-54370611.");
			}
		}
		return "buy/orderSuccess";
	}
}
