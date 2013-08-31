package skynet.isi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import skynet.isi.dao.Page;
import skynet.isi.model.Product;
import skynet.isi.resultmodel.CategoryModel;
import skynet.isi.resultmodel.OrderAmount;
import skynet.isi.resultmodel.ShoppingListModel;
import skynet.isi.service.CommonService;
import skynet.isi.service.PagingModel;
import skynet.isi.service.ProductSearchService;
import skynet.isi.service.ShoppingCartService;
import skynet.isi.utils.Utils;

@Controller
public class ProductSearchController {

	@Autowired
	ProductSearchService productSearchService;

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/frontend/fuzzySearch")
	public String fuzzySearch(Model model, PagingModel pagingModel,
			String searchStr, HttpServletRequest request) {
		request.getSession().setAttribute("topCategory",
				productSearchService.getTopLevelCategory());
		model.addAttribute("promotionList", commonService.getPromotionList());
		Page<Product> result = productSearchService.fuzzySearch(pagingModel,
				searchStr);
		model.addAttribute("searchStr", searchStr);
		model.addAttribute("searchResultPage", result);
		model.addAttribute("action", "/frontend/fuzzySearch?searchStr="
				+ searchStr);
		return "search/searchResult";
	}

	@RequestMapping(value = "/frontend/getChildCategory")
	public String getChildCategory(Model model, PagingModel pagingModel,
			int categoryId) {
		List<CategoryModel> result = productSearchService
				.getChildCategory(categoryId);
		model.addAttribute("manufacturerId", 0);
		model.addAttribute("result", result);
		model.addAttribute("categoryTitle",
				productSearchService.getCategoryName(categoryId));
		model.addAttribute("resultNum",
				productSearchService.getResultNum(result));
		return "search/childCategory";
	}

	@RequestMapping(value = "/frontend/getProductByCategory")
	public String getProductByCategory(Model model, PagingModel pagingModel,
			int categoryId, int mfrId) {
		Page<Product> result = null;
		if (mfrId == 0) {
			result = productSearchService.getProductByCategory(pagingModel,
					categoryId);
		} else {
			result = productSearchService.getProductByManufacturerAndCategory(
					pagingModel, mfrId, categoryId);
		}
		model.addAttribute("searchResultPage", result);
		model.addAttribute("action",
				"/frontend/getProductByCategory?categoryId=" + categoryId
						+ "&mfrId=" + mfrId);
		return "search/searchResult";
	}

	@RequestMapping(value = "/frontend/getAllManufacturers")
	public String getAllManufacturers(Model model, PagingModel pagingModel, HttpServletRequest request) {
		request.getSession().setAttribute("topCategory",
				productSearchService.getTopLevelCategory());
		model.addAttribute("promotionList", commonService.getPromotionList());
		model.addAttribute("brandCategory",
				productSearchService.getAllManufacturers());
		return "searchBrand";
	}

	@RequestMapping(value = "/frontend/getCategoryByManufacturer")
	public String getCategoryByManufacturer(int manufacturerId, Model model) {
		List<CategoryModel> result = productSearchService
				.getCategoryByManufacturer(manufacturerId);
		model.addAttribute("categoryTitle",
				productSearchService.getMfrNameById(manufacturerId));
		model.addAttribute("manufacturerId", manufacturerId);
		model.addAttribute("result", result);
		model.addAttribute("resultNum",
				productSearchService.getResultNum(result));
		return "search/childCategory";
	}

	@RequestMapping(value = "/frontend/getProductInfo")
	public String getProductInfo(int productId, int shoppingCartId, Model model) {
		if (productId == 0) {
			productId = productSearchService
					.getProductIdByShoppingCartId(shoppingCartId);
		}
		model.addAttribute("product",
				productSearchService.getProductInfoByProductId(productId));
		model.addAttribute("comments",
				productSearchService.getComment(productId));
		return "search/searchDetail";
	}

	@RequestMapping(value = "/frontend/addComment")
	public void addComment(int productId, String myComment,
			HttpServletResponse response) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		String msg = "请先登录！";
		if (!StringUtils.isEmpty(loginName)) {
			productSearchService.addComment(productId, myComment);
			msg = "添加评论成功！";
			Utils.writeMsg(response, msg);
		}
	}

	@RequestMapping(value = "/frontend/buyNow")
	public String buyNow(int productId, int amount, Model model) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		shoppingCartService.addToCart(loginName, productId, amount);
		List<ShoppingListModel> shoppingList = shoppingCartService
				.getShoppingList(loginName);
		OrderAmount orderAmount = shoppingCartService
				.getOrderAmount(shoppingList);
		model.addAttribute("searchResultPage", shoppingList);
		model.addAttribute("orderAmount", orderAmount);
		model.addAttribute("action", "/frontend/getShoppingCart");
		return "buy/shoppingCart";
	}
}
