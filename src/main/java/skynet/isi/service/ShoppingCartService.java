package skynet.isi.service;

import java.util.List;

import skynet.isi.common.Enums.RESULT;
import skynet.isi.dao.Page;
import skynet.isi.model.Product;
import skynet.isi.model.ShoppingCart;
import skynet.isi.resultmodel.OrderAmount;
import skynet.isi.resultmodel.ShoppingListModel;

public interface ShoppingCartService {
	/***
	 * 获得收藏夹里产品
	 * 
	 * @param loginName
	 * @return
	 */
	public Page<Product> getFavorite(PagingModel pagingModel, String loginName);

	/***
	 * 获得购物车里产品
	 * 
	 * @param pagingModel
	 * @param loginName
	 * @return
	 */
	public List<ShoppingListModel> getShoppingList(String loginName);

	public List<ShoppingCart> getShoppingCart(String loginName);

	public ShoppingListModel getShoppingListModel(ShoppingCart sc);

	public ShoppingListModel getShoppingListModel(int shoppingCartId);

	public OrderAmount getOrderAmount(List<ShoppingListModel> products);

	public OrderAmount getOrderAmountByIds(List<Integer> cartIds);

	/***
	 * 加入收藏夹
	 * 
	 * @param productId
	 * @return
	 */
	public RESULT addToFavorite(String loginName, int productId);

	/***
	 * 删除收藏夹里产品
	 * 
	 * @param loginName
	 * @param productId
	 * @return
	 */
	public RESULT deleteFavorite(String loginName, int productId);

	/***
	 * 加入购物车
	 * 
	 * @param info
	 * @return
	 */
	public RESULT addToCart(String loginName, int productId, int amount);

	/***
	 * 删除收藏夹里产品
	 * 
	 * @param loginName
	 * @param productId
	 * @return
	 */
	public RESULT deleteCart(String loginName, int productId);

	public RESULT updateCart(String loginName, int cartId, int intentNum);
}
