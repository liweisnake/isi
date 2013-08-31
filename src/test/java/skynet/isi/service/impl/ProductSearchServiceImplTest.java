package skynet.isi.service.impl;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import skynet.isi.DBEnablerTestBase;
import skynet.isi.dao.Page;
import skynet.isi.model.Category;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;
import skynet.isi.resultmodel.CategoryModel;
import skynet.isi.service.PagingModel;
import skynet.isi.service.ProductSearchService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ProductSearchServiceImplTest extends DBEnablerTestBase {

	@Autowired
	ProductSearchService productService;

	@Override
	public String dataSetFileName() {
		return "data_init.xml";
	}

	@Test
	public void testFuzzySearch() {
		Page<Product> products = productService.fuzzySearch(new PagingModel(1,
				0, 0), "a");
		Assert.assertEquals(2, products.getTotalCount());
		Assert.assertEquals(new Integer(1), products.getResult().get(0).getId());
		Assert.assertEquals(new Integer(3), products.getResult().get(1).getId());

		Page<Product> products2 = productService.fuzzySearch(new PagingModel(1,
				5, 0), "");
		Assert.assertEquals(4, products2.getTotalCount());
		Assert.assertEquals(new Integer(1), products2.getResult().get(0)
				.getId());
		Assert.assertEquals(new Integer(2), products2.getResult().get(1)
				.getId());
		Assert.assertEquals(new Integer(3), products2.getResult().get(2)
				.getId());
		Assert.assertEquals(new Integer(4), products2.getResult().get(3)
				.getId());

		Page<Product> products1 = productService.fuzzySearch(new PagingModel(1,
				0, 0), "an  s");
		Assert.assertEquals(3, products1.getTotalCount());
	}

	@Test
	public void testTopLevelCategory() {
		List<Category> categoties = productService.getTopLevelCategory();
		Assert.assertEquals(2, categoties.size());
		Assert.assertEquals(new Integer(1), categoties.get(0).getId());
		Assert.assertEquals(new Integer(2), categoties.get(1).getId());
	}

	@Test
	public void testGetChildCategory() {
		List<CategoryModel> categoties1 = productService.getChildCategory(1);
		Assert.assertEquals(2, categoties1.size());

		List<CategoryModel> categoties2 = productService.getChildCategory(2);
		Assert.assertEquals(2, categoties2.size());

		List<CategoryModel> categoties3 = productService.getChildCategory(4);
		Assert.assertEquals(0, categoties3.size());
	}

	@Test
	public void testHasChildCategory() {
		Assert.assertTrue(productService.hasChildCategory(1));
		Assert.assertTrue(productService.hasChildCategory(2));
		Assert.assertFalse(productService.hasChildCategory(3));
		Assert.assertFalse(productService.hasChildCategory(4));
		Assert.assertFalse(productService.hasChildCategory(5));
		Assert.assertFalse(productService.hasChildCategory(6));
	}

	@Test
	public void testGetProductByCategory() {
		Page<Product> products = productService.getProductByCategory(
				new PagingModel(1, 0, 0), 4);
		Assert.assertEquals(2, products.getTotalCount());
	}

	@Test
	public void testGetAllManufacturers() {
		// Page<Manufacturer> manufacturers = productService
		// .getAllManufacturers(new PagingModel(1, 0));
		// Assert.assertEquals(3, manufacturers.getTotalCount());
	}

	@Test
	public void testGetCategoryByManufacturer() {
		// Map<String, List<Category>> categoties = productService
		// .getCategoryByManufacturer(2);
		// Assert.assertEquals(2, categoties.size());
		// Assert.assertEquals(1, categoties.get("1").size());
		// Assert.assertEquals(new Integer(4),
		// categoties.get("1").get(0).getId());
		// Assert.assertEquals(new Integer(1), categoties.get("1").get(0)
		// .getChildCount());
		// Assert.assertEquals(1, categoties.get("2").size());
		// Assert.assertEquals(new Integer(6),
		// categoties.get("2").get(0).getId());
		// Assert.assertEquals(new Integer(1), categoties.get("2").get(0)
		// .getChildCount());
	}

	@Test
	public void testGetProductByManufacturerAndCategory() {
		Page<Product> products = productService
				.getProductByManufacturerAndCategory(new PagingModel(1, 0, 0),
						2, 4);
		Assert.assertEquals(1, products.getTotalCount());
		Assert.assertEquals(new Integer(2), products.getResult().get(0).getId());
	}
}
