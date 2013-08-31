package skynet.isi.service.impl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;
import skynet.isi.common.Enums.RESULT;
import skynet.isi.dao.Page;
import skynet.isi.model.Product;
import skynet.isi.service.PagingModel;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ShoppingCartServiceImplTest extends DBEnablerTestBase {

	@Autowired
	ShoppingCartServiceImpl shoppingCartService;

	@Override
	public String dataSetFileName() {
		return "data_init.xml";
	}

	@Test
	public void testGetFavorite() {
		Page<Product> products = shoppingCartService.getFavorite(
				new PagingModel(1, 0, 0), "levi");
		Assert.assertEquals(4, products.getResult().size());
		System.out.println(products.getResult().get(0).getId());
		System.out.println(products.getResult().get(1).getId());
		System.out.println(products.getResult().get(2).getId());
		System.out.println(products.getResult().get(3).getId());

		Page<Product> products2 = shoppingCartService.getFavorite(
				new PagingModel(1, 0, 0), "hong");
		Assert.assertEquals(2, products2.getResult().size());
		System.out.println(products2.getResult().get(0).getId());
		System.out.println(products2.getResult().get(1).getId());
	}

	@Test
	public void testGetShoppingCart() {
		// Page<ShoppingListModel> products =
		// shoppingCartService.getShoppingList(
		// new PagingModel(1, 0, 0), "levi");
		// Assert.assertEquals(4, products.getResult().size());
		// System.out.println(products.getResult().get(0));
		// System.out.println(products.getResult().get(1));
		// System.out.println(products.getResult().get(2));
		// System.out.println(products.getResult().get(3));
		//
		// Page<ShoppingListModel> products2 = shoppingCartService
		// .getShoppingList(new PagingModel(1, 0, 0), "hong");
		// Assert.assertEquals(2, products2.getResult().size());
		// System.out.println(products2.getResult().get(0));
		// System.out.println(products2.getResult().get(1));
	}

	@Test
	public void testAddToFavorite() {
		RESULT result = shoppingCartService.addToFavorite("levi", 1);
		Assert.assertEquals(RESULT.ALREADY_EXIST, result);

		Page<Product> products1 = shoppingCartService.getFavorite(
				new PagingModel(1, 0, 0), "hong");
		Assert.assertEquals(2, products1.getResult().size());

		RESULT result1 = shoppingCartService.addToFavorite("hong", 2);
		Assert.assertEquals(RESULT.SUCESS, result1);

		Page<Product> products2 = shoppingCartService.getFavorite(
				new PagingModel(1, 0, 0), "hong");
		Assert.assertEquals(3, products2.getResult().size());
	}

	@Test
	public void testDeleteFavorite() {
		RESULT result = shoppingCartService.deleteFavorite("hong", 2);
		Assert.assertEquals(RESULT.NOT_EXIST, result);

		Page<Product> products1 = shoppingCartService.getFavorite(
				new PagingModel(1, 0, 0), "hong");
		Assert.assertEquals(2, products1.getResult().size());
		RESULT result1 = shoppingCartService.deleteFavorite("hong", 1);
		Assert.assertEquals(RESULT.SUCESS, result1);

		Page<Product> products2 = shoppingCartService.getFavorite(
				new PagingModel(1, 0, 0), "hong");
		Assert.assertEquals(1, products2.getResult().size());
	}

	@Test
	public void testAddToCart() {
		// AddToCartInfo info = new AddToCartInfo("levi", 1, new Integer(1),
		// new Long(1));
		// RESULT result = shoppingCartService.addToCart(info);
		// Assert.assertEquals(RESULT.ALREADY_EXIST, result);
		//
		// Page<ShoppingListModel> products1 = shoppingCartService
		// .getShoppingList(new PagingModel(1, 0), "hong");
		// Assert.assertEquals(2, products1.getResult().size());
		//
		// AddToCartInfo info1 = new AddToCartInfo("hong", 2, new Integer(1),
		// new Long(1));
		// RESULT result1 = shoppingCartService.addToCart(info1);
		// Assert.assertEquals(RESULT.SUCESS, result1);
		//
		// Page<ShoppingListModel> products2 = shoppingCartService
		// .getShoppingList(new PagingModel(1, 0), "hong");
		// Assert.assertEquals(3, products2.getResult().size());
	}

	@Test
	public void testDeleteCart() {
		// RESULT result = shoppingCartService.deleteCart("hong", 2);
		// Assert.assertEquals(RESULT.NOT_EXIST, result);
		//
		// Page<ShoppingListModel> products1 = shoppingCartService
		// .getShoppingList(new PagingModel(1, 0, 0), "hong");
		// Assert.assertEquals(2, products1.getResult().size());
		//
		// RESULT result1 = shoppingCartService.deleteCart("hong", 1);
		// Assert.assertEquals(RESULT.SUCESS, result1);
		//
		// Page<ShoppingListModel> products2 = shoppingCartService
		// .getShoppingList(new PagingModel(1, 0, 0), "hong");
		// Assert.assertEquals(1, products2.getResult().size());
	}
}
