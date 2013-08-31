package skynet.isi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.common.Enums.RESULT;
import skynet.isi.dao.FavoriteDao;
import skynet.isi.dao.Page;
import skynet.isi.dao.ProductDao;
import skynet.isi.dao.ShoppingCartDao;
import skynet.isi.model.Favorite;
import skynet.isi.model.Product;
import skynet.isi.model.ShoppingCart;
import skynet.isi.model.User;
import skynet.isi.resultmodel.OrderAmount;
import skynet.isi.resultmodel.ShoppingListModel;
import skynet.isi.service.AccountService;
import skynet.isi.service.CommonService;
import skynet.isi.service.PagingModel;
import skynet.isi.service.ShoppingCartService;

@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	protected static final Logger log = LogManager
			.getLogger(ShoppingCartServiceImpl.class.getName());

	@Autowired
	private ProductDao productDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AccountService accountService;

	public Page<Product> getFavorite(PagingModel pagingModel, String loginName) {
		User user = accountService.getUserByLoginName(loginName);

		commonService.checkPagingModel(pagingModel);

		Page<Favorite> favoritePage = Page.getInstance(pagingModel.getStart(),
				pagingModel.getLimit());
		favoritePage.setPageNo(pagingModel.getPageNo());

		Page<Favorite> favorites = favoriteDao.findPage(favoritePage,
				Order.asc("id"), Restrictions.eq("user.id", user.getId()));
		List<Product> products = new ArrayList<Product>();
		for (Favorite fav : favorites.getResult()) {
			products.add(fav.getProduct());
		}

		Page<Product> productPage = Page.getInstance(pagingModel.getStart(),
				pagingModel.getLimit());
		productPage.setPageNo(pagingModel.getPageNo());

		productPage.setResult(products);
		productPage.setTotalCount(favorites.getTotalCount());

		return productPage;
	}

	public ShoppingListModel getShoppingListModel(int shoppingCartId) {
		ShoppingCart sc = shoppingCartDao.get(shoppingCartId);
		return getShoppingListModel(sc);
	}

	public ShoppingListModel getShoppingListModel(ShoppingCart sc) {
		Product product = sc.getProduct();
		ShoppingListModel cartModel = new ShoppingListModel();
		cartModel.setShoppingCartId(sc.getId());
		cartModel.setDescription(product.getDescription());
		cartModel.setIicSku(product.getIicSku());
		cartModel.setIntentNum(sc.getIntentNum());
		cartModel.setMfrSku(product.getMfrSku());
		cartModel.setOnePrice(product.getOnePrice());
		cartModel.setRohsStatus(product.getRohsStatus());
		cartModel.setTotalPrice(commonService.format(product.getOnePrice()
				* sc.getIntentNum()));
		cartModel.setStore(product.getStore());

		return cartModel;
	}

	public List<ShoppingListModel> getShoppingList(String loginName) {

		List<ShoppingCart> shoppingCarts = getShoppingCart(loginName);

		List<ShoppingListModel> cartModels = new ArrayList<ShoppingListModel>();
		for (ShoppingCart sc : shoppingCarts) {
			cartModels.add(getShoppingListModel(sc));
		}

		return cartModels;
	}

	public List<ShoppingCart> getShoppingCart(String loginName) {
		User user = accountService.getUserByLoginName(loginName);

		List<ShoppingCart> result = shoppingCartDao.find(Order.asc("id"),
				Restrictions.eq("user.id", user.getId()));

		return result;
	}

	public OrderAmount getOrderAmount(List<ShoppingListModel> products) {
		OrderAmount orderAmount = new OrderAmount();
		double totalAmount = 0;
		for (ShoppingListModel sc : products) {
			totalAmount += sc.getIntentNum() * sc.getOnePrice();
		}
		double tax = totalAmount * 0.17;
		double freight = 0;
		if (totalAmount < 500)
			freight = 15;
		orderAmount.setPreTaxPrice(commonService.format(totalAmount));
		orderAmount.setValueAddedTax(commonService.format(tax));
		orderAmount.setFreight(commonService.format(freight));
		orderAmount.setTotalPrice(commonService.format(totalAmount + tax
				+ freight));
		return orderAmount;
	}

	public OrderAmount getOrderAmountByIds(List<Integer> cartIds) {
		List<ShoppingListModel> carts = new ArrayList<ShoppingListModel>();
		for (Integer cartId : cartIds)
			carts.add(getShoppingListModel(cartId));
		return getOrderAmount(carts);
	}

	public RESULT addToFavorite(String loginName, int productId) {
		if (getFavoriteList(loginName, productId).size() > 0) {
			return RESULT.ALREADY_EXIST;
		}
		User user = accountService.getUserByLoginName(loginName);
		Product product = productDao.get(productId);
		Favorite fav = new Favorite();
		fav.setProduct(product);
		fav.setUser(user);
		favoriteDao.save(fav);
		log.info("User " + user.getLoginName() + " add " + productId
				+ " to favorite.");
		return RESULT.SUCESS;
	}

	public RESULT deleteFavorite(String loginName, int productId) {
		List<Favorite> list = getFavoriteList(loginName, productId);
		if (list.size() > 0) {
			favoriteDao.delete(list.get(0).getId());
			log.info("User " + loginName + " delete " + productId
					+ " from favorite.");
			return RESULT.SUCESS;
		} else {
			return RESULT.NOT_EXIST;
		}
	}

	public RESULT addToCart(String loginName, int productId, int amount) {

		User user = accountService.getUserByLoginName(loginName);
		Product product = productDao.get(productId);
		ShoppingCart cart = null;
		List<ShoppingCart> list = getShoppingCartList(loginName, productId);
		if (list.size() > 0) {
			cart = list.get(0);
			cart.setIntentNum(cart.getIntentNum() + amount);
		} else {
			cart = new ShoppingCart();
			cart.setIntentNum(amount);
			cart.setProduct(product);
			cart.setUser(user);
		}
		shoppingCartDao.save(cart);
		log.info("User " + user.getLoginName() + " add " + productId
				+ " to shopping cart.");
		return RESULT.SUCESS;
	}

	public RESULT deleteCart(String loginName, int cartId) {
		shoppingCartDao.delete(cartId);
		log.info("User " + loginName + " delete " + cartId
				+ " from shopping cart.");
		return RESULT.SUCESS;
	}

	public RESULT updateCart(String loginName, int cartId, int intentNum) {
		ShoppingCart sc = shoppingCartDao.get(cartId);
		sc.setIntentNum(intentNum);
		shoppingCartDao.save(sc);
		log.info("User " + loginName + " update " + cartId
				+ " from shopping cart.");
		return RESULT.SUCESS;
	}

	private List<Favorite> getFavoriteList(String loginName, int productId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "u");
		map.put("product", "p");

		return favoriteDao.find(map, Restrictions.and(
				Restrictions.eq("u.loginName", loginName),
				Restrictions.eq("p.id", productId)));
	}

	private List<ShoppingCart> getShoppingCartList(String loginName,
			int productId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "u");
		map.put("product", "p");

		return shoppingCartDao.find(map, Restrictions.and(
				Restrictions.eq("u.loginName", loginName),
				Restrictions.eq("p.id", productId)));
	}

	// private int getCartByFK(String loginName, int productId) {
	// List<ShoppingCart> carts = getShoppingCart(loginName, productId);
	// if (carts.size() < 1)
	// return -1;
	// else
	// return carts.get(0).getId();
	// }
}
