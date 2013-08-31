package skynet.isi.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import skynet.isi.common.Enums.RESULT;
import skynet.isi.dao.Page;
import skynet.isi.model.Product;
import skynet.isi.resultmodel.OrderAmount;
import skynet.isi.resultmodel.ShoppingListModel;
import skynet.isi.service.PagingModel;
import skynet.isi.service.ProductSearchService;
import skynet.isi.service.ShoppingCartService;
import skynet.isi.utils.Utils;

@Controller
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	ProductSearchService productSearchService;

	@RequestMapping(value = "/frontend/addToFavorite")
	public void addToFavorite(int productId, HttpServletResponse response) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		String msg = "添加失败！";
		if (StringUtils.isEmpty(loginName)) {
			msg = "请先登录！";
		} else {
			RESULT result = shoppingCartService.addToFavorite(loginName,
					productId);
			if (result == RESULT.SUCESS) {
				msg = "添加成功！";
			} else if (result == RESULT.ALREADY_EXIST) {
				msg = "收藏夹中已存在该商品！";
			}
		}
		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/deleteFavorite")
	public void deleteFavorite(PagingModel pagingModel,
			HttpServletRequest request, Model model,
			HttpServletResponse response) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		String msg = "删除失败！";
		RESULT result = null;
		int rowNum = 1;
		Map<String, String[]> requestMap = request.getParameterMap();
		String[] productId = requestMap.get("productId" + rowNum);
		while (productId != null && StringUtils.isNotEmpty(productId[0])) {
			if (requestMap.get("checkbox" + rowNum) != null)
				result = shoppingCartService.deleteFavorite(loginName,
						Integer.parseInt(productId[0]));
			rowNum++;
			productId = requestMap.get("productId" + rowNum);
		}
		if (result == RESULT.SUCESS) {
			msg = "删除成功！";
		} else if (result == RESULT.NOT_EXIST) {
			msg = "收藏夹中不存在该商品！";
		}
		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/getFavorite")
	public String getFavorite(PagingModel pagingModel, Model model) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		Page<Product> result = shoppingCartService.getFavorite(pagingModel,
				loginName);
		model.addAttribute("searchResultPage", result);
		model.addAttribute("action", "/frontend/getFavorite");
		return "account/favorite";
	}

	@RequestMapping(value = "/frontend/addToCart")
	public void addToCart(String searchStr, PagingModel pagingModel,
			HttpServletRequest request, Model model,
			HttpServletResponse response) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		String msg = "添加失败！";
		if (StringUtils.isEmpty(loginName)) {
			msg = "请先登录！";
		} else {
			RESULT result = null;
			int rowNum = 1;
			Map<String, String[]> requestMap = request.getParameterMap();
			String[] productId = requestMap.get("productId" + rowNum);
			while (productId != null && StringUtils.isNotEmpty(productId[0])) {
				if (requestMap.get("checkbox" + rowNum) != null)
					result = shoppingCartService.addToCart(loginName,
							Integer.parseInt(productId[0]), 1);
				rowNum++;
				productId = requestMap.get("productId" + rowNum);
			}
			if (result == RESULT.SUCESS) {
				msg = "添加成功！";
			} else if (result == RESULT.ALREADY_EXIST) {
				msg = "购物车中已存在该商品！";
			}
		}
		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/addToCartForOneProduct")
	public void addToCartForOneProduct(int productId, int amount, Model model,
			HttpServletResponse response) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		if (StringUtils.isEmpty(loginName)) {
			return;
		}
		String msg = "添加失败！";
		RESULT result = null;
		result = shoppingCartService.addToCart(loginName, productId, amount);
		if (result == RESULT.SUCESS) {
			msg = "添加成功！";
		} else if (result == RESULT.ALREADY_EXIST) {
			msg = "购物车中已存在该商品！";
		}
		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/getShoppingCart")
	public String getShoppingCart(Model model) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		// if (StringUtils.isEmpty(loginName)){
		// return "login";
		// }
		List<ShoppingListModel> shoppingList = shoppingCartService
				.getShoppingList(loginName);
		OrderAmount amount = shoppingCartService.getOrderAmount(shoppingList);
		model.addAttribute("searchResultPage", shoppingList);
		model.addAttribute("orderAmount", amount);
		model.addAttribute("action", "/frontend/getShoppingCart");
		return "buy/shoppingCart";
	}

	@RequestMapping(value = "/frontend/deleteCart")
	public void deleteCart(PagingModel pagingModel, HttpServletRequest request,
			Model model, HttpServletResponse response) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		String msg = "删除失败！";
		RESULT result = null;
		int rowNum = 1;
		Map<String, String[]> requestMap = request.getParameterMap();
		String[] cartId = requestMap.get("shoppingCartId" + rowNum);
		while (cartId != null && StringUtils.isNotEmpty(cartId[0])) {
			if (requestMap.get("checkbox" + rowNum) != null)
				result = shoppingCartService.deleteCart(loginName,
						Integer.parseInt(cartId[0]));
			rowNum++;
			cartId = requestMap.get("shoppingCartId" + rowNum);
		}
		if (result == RESULT.SUCESS) {
			msg = "删除成功！";
		} else if (result == RESULT.NOT_EXIST) {
			msg = "购物车中不存在该商品！";
		}
		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/updateCart")
	public void updateCart(PagingModel pagingModel, HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		String msg = "更新失败！";
		RESULT result = null;
		int rowNum = 1;
		Map<String, String[]> requestMap = request.getParameterMap();
		String[] cartId = requestMap.get("shoppingCartId" + rowNum);
		while (cartId != null && StringUtils.isNotEmpty(cartId[0])) {
			result = shoppingCartService.updateCart(loginName,
					Integer.parseInt(cartId[0]),
					Integer.parseInt(requestMap.get("intentNum" + rowNum)[0]));
			rowNum++;
			cartId = requestMap.get("shoppingCartId" + rowNum);
		}
		if (result == RESULT.SUCESS) {
			msg = "更新成功！";
		} else if (result == RESULT.NOT_EXIST) {
			msg = "购物车中不存在该商品！";
		}
		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/continueShopping")
	public String continueShopping(Model model) {
		return "index";
	}
}
