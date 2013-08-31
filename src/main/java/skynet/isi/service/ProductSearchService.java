package skynet.isi.service;

import java.util.List;
import java.util.Map;

import skynet.isi.dao.Page;
import skynet.isi.model.Category;
import skynet.isi.model.Comment;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;
import skynet.isi.resultmodel.CategoryModel;

public interface ProductSearchService {

	public String getMfrNameById(int id);
	
	// 模糊搜索
	public Page<Product> fuzzySearch(PagingModel pagingModel, String searchStr);

	// 分类层级搜索
	public List<Category> getTopLevelCategory();

	public List<CategoryModel> getChildCategory(int categoryId);

	public int getResultNum(List<CategoryModel> categories);

	public String getCategoryName(int categoryId);

	public boolean hasChildCategory(int categoryId);

	// public List<Category> getAllLevelCategory();

	public Page<Product> getProductByCategory(PagingModel pagingModel,
			int categoryId);

	// public List<Category> searchCategoryByString(String searchStr);

	// 品牌索引
	public Map<String, List<Manufacturer>> getAllManufacturers();

	public List<CategoryModel> getCategoryByManufacturer(int manufacturerId);

	public Page<Product> getProductByManufacturerAndCategory(
			PagingModel pagingModel, int manufacurerId, int categoryId);

	// 产品展示
	public Product getProductInfoByProductId(int productId);
	
	public int getProductIdByShoppingCartId(int shoppingCartId);

	// 添加评论
	public void addComment(int productId, String comment);
	
	public List<Comment> getComment(int productId);
}
