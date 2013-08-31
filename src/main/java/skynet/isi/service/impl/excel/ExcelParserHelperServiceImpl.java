package skynet.isi.service.impl.excel;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.dao.CategoryDao;
import skynet.isi.dao.ManufacturerDao;
import skynet.isi.dao.ProductDao;
import skynet.isi.model.Category;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;
import skynet.isi.service.ExcelParserHelperService;

@Transactional
@Service
public class ExcelParserHelperServiceImpl implements ExcelParserHelperService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ManufacturerDao mfrDao;

	@Autowired
	private CategoryDao categoryDao;

	public Product getProductByIicSku(String iicSku) {
		List<Product> products = productDao.find(Restrictions.eq("iicSku",
				iicSku));
		if (products.size() < 1)
			return null;
		else
			return products.get(0);
	}

	public Manufacturer getMfrByName(String mfrName) {
		List<Manufacturer> mfrs = mfrDao.find(Restrictions.eq("mfrName",
				mfrName));
		if (mfrs.size() < 1)
			return null;
		else
			return mfrs.get(0);
	}

	public Category getCategoryByName(String categoryName) {
		List<Category> categories = categoryDao.find(Restrictions.eq(
				"categoryName", categoryName));
		if (categories.size() < 1)
			return null;
		else
			return categories.get(0);
	}

	public int getMfrMaxId() {
		return mfrDao.getMax("id");
	}

	public int getCategoryMaxId() {
		return categoryDao.getMax("id");
	}

	// public void insertMfrs(Collection<Manufacturer> mfrs) {
	// for (Manufacturer mfr : mfrs)
	// mfrDao.batchExecute(mfr);
	// mfrDao.closeBatch();
	// }
	//
	// public void insertCategories(Collection<Category> categories) {
	// for (Category category : categories)
	// categoryDao.batchExecute(category);
	// categoryDao.closeBatch();
	// }
	//
	// public void insertProducts(Collection<Product> products) {
	// for (Product product : products) {
	// productDao.batchExecute(product);
	// System.out.println(product.getIicSku());
	// }
	// productDao.closeBatch();
	// }

	public void insertCategory(Category category) {
		categoryDao.batchExecute(category);
		categoryDao.closeBatch();
	}

	public void insertMfr(Manufacturer mfr) {
		mfrDao.batchExecute(mfr);
		mfrDao.closeBatch();
	}

	public void insertProduct(Product product) {
		productDao.batchExecute(product);
		System.out.println(product.getIicSku());
		productDao.closeBatch();
	}

	public Manufacturer getMfrById(int id) {
		return mfrDao.get(id);
	}

	public Category getCategoryById(int id) {
		return categoryDao.get(id);
	}
}
