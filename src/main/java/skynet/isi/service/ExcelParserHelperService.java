package skynet.isi.service;

import skynet.isi.model.Category;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;

public interface ExcelParserHelperService {

	public Product getProductByIicSku(String iicSku);

	public Manufacturer getMfrByName(String mfrName);

	public Category getCategoryByName(String categoryName);

	public int getMfrMaxId();

	public int getCategoryMaxId();

	// public void insertMfrs(Collection<Manufacturer> mfrs);
	//
	// public void insertCategories(Collection<Category> collection);
	//
	// public void insertProducts(Collection<Product> products);

	public void insertCategory(Category category);

	public void insertMfr(Manufacturer mfr);

	public void insertProduct(Product product);

	public Manufacturer getMfrById(int id);

	public Category getCategoryById(int id);

}
