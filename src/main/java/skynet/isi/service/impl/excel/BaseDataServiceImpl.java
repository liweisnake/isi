package skynet.isi.service.impl.excel;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.model.Category;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;
import skynet.isi.resultmodel.ProductInfo;
import skynet.isi.service.ExcelParserHelperService;

@Transactional
@Service
public abstract class BaseDataServiceImpl extends HxlsAbstract {

	static Logger logger = LoggerFactory.getLogger(BaseDataServiceImpl.class);

	// private int mfrCount = 0;
	// private int categoryCount = 0;

	@Autowired
	ExcelParserHelperService excelParserHelperService;

	// RESULT execute() {
	// RESULT result = RESULT.FAILED;
	// try {
	// excelParserHelperService.insertCategories(DataCacheMaps.categories
	// .values());
	// excelParserHelperService.insertMfrs(DataCacheMaps.mfrs.values());
	// excelParserHelperService.insertProducts(DataCacheMaps.products
	// .values());
	//
	// DataCacheMaps.clear();
	//
	// initCount();
	// result = RESULT.SUCESS;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return result;
	// }

	void executeCategory(Object object) {
		excelParserHelperService.insertCategory((Category) object);
	}

	void executeMfr(Object object) {
		excelParserHelperService.insertMfr((Manufacturer) object);
	}

	void executeProduct(Object object) {
		excelParserHelperService.insertProduct((Product) object);
	}

	public void after() {
		// execute();
		logger.info("Insert or update data successfully!");
	}

	public void before() {
		logger.debug("Read excel file and parser data to model.");
	}

	Manufacturer createManufacturer(String mfrName) {
		Manufacturer mfr = new Manufacturer();
		// mfr.setId(mfrCount++);
		// mfr.setId(nextMfrId());
		mfr.setMfrName(mfrName);

		return mfr;
	}

	Category createParentCategory(String name) {
		Category parentCategory = new Category();
		// parentCategory.setId(categoryCount++);
		// parentCategory.setId(nextCategoryId());
		parentCategory.setCategory(null);
		parentCategory.setCategoryName(name);
		parentCategory.setChildCount(0);

		return parentCategory;
	}

	Category createChildCategory(String name, Category parent) {
		Category childCategory = new Category();
		// childCategory.setId(categoryCount++);
		// childCategory.setId(nextCategoryId());
		childCategory.setCategory(parent);
		childCategory.setCategoryName(name);
		childCategory.setChildCount(0);

		// addCount(parent);

		return childCategory;
	}

	Product fillProduct(ExcelDataModel model, Manufacturer mfr,
			Category category, Product product) {
		product.setCategory(category);
		product.setClassCode(filter(model.getClassCode()));
		product.setCountryOrigin(filter(model.getCountryOrigin()));
		product.setDataSheet(filter(model.getDataSheet()));
		product.setDescription(filter(model.getDesc()));
		product.setDistySku(filter(model.getDistySku()));
		product.setIicSku(filter(model.getIccSku()));
		product.setImageUrl(filter(model.getImageURL()));
		product.setLongDesc(filter(model.getLongDesc()));
		product.setManufacturer(mfr);
		product.setMfrSku(filter(model.getMfrSku()));
		product.setPackSize(stringToDouble(filter(model.getPackSize())));
		product.setRohsStatus(filter(model.getRohsStatus()));
		product.setUom(filter(model.getUom()));
		product.setStore(filter(model.getStore()));
		product.setWeight(stringToDouble(filter(model.getWeight())));
		product.setOneAmount(stringToDouble(filter(model.getOneAmount())));
		product.setOnePrice(stringToDouble(filter(model.getOnePrice())));
		product.setThreeAmount(stringToDouble(filter(model.getThreeAmount())));
		product.setThreePrice(stringToDouble(filter(model.getThreePrice())));
		product.setTwoAmount(stringToDouble(filter(model.getTwoAmount())));
		product.setTwoPrice(stringToDouble(filter(model.getTwoPrice())));

		return product;
	}

	Product fillProduct(ProductInfo info, Manufacturer mfr, Product product) {
		product.setCountryOrigin(filter(info.getCountryOrigin()));
		product.setDataSheet(filter(info.getDataSheet()));
		product.setDescription(filter(info.getDesc()));
		product.setDistySku(filter(info.getDistySku()));
		product.setIicSku(filter(info.getIccSku()));
		product.setImageUrl(filter(info.getImageURL()));
		product.setLongDesc(filter(info.getLongDesc()));
		product.setManufacturer(mfr);
		product.setMfrSku(filter(info.getMfrSku()));
		product.setPackSize(stringToDouble(filter(info.getPackSize())));
		product.setRohsStatus(filter(info.getRohsStatus()));
		product.setUom(filter(info.getUom()));
		product.setStore(filter(info.getStore()));
		product.setOneAmount(stringToDouble(filter(info.getOneAmount())));
		product.setOnePrice(stringToDouble(filter(info.getOnePrice())));

		return product;
	}

	double stringToDouble(String string) {
		double i = 0;
		if (StringUtils.isEmpty(string)) {
			return 0;
		} else {
			i = Double.parseDouble(string);
		}
		return i;
	}

	String filter(String str) {
		if (str == null || "null".equals(str))
			return "";
		else
			return str;
	}

	void addCount(Category category) {
		int count = category.getChildCount();
		category.setChildCount(count + 1);
	}

	void addCount(String categoryName) {
		Category parent = excelParserHelperService
				.getCategoryByName(categoryName);
		if (parent != null) {
			addCount(parent);
			executeCategory(parent);
		}
	}

	int nextMfrId() {
		return excelParserHelperService.getMfrMaxId() + 1;
	}

	int nextCategoryId() {
		return excelParserHelperService.getCategoryMaxId() + 1;
	}

	// void initCount() {
	// mfrCount = excelParserHelperService.getMfrMaxId() + 1;
	// categoryCount = excelParserHelperService.getCategoryMaxId() + 1;
	// }

	Category getCategory(String categoryName) {
		// Category category = DataCacheMaps.categories.get(categoryName);
		// if (category == null) {
		// category = DataCacheMaps.categoriesTemp.get(categoryName);
		// }
		// if (category == null) {
		// category = excelParserHelperService.getCategoryByName(categoryName);
		// if (category != null)
		// DataCacheMaps.categoriesTemp.put(categoryName, category);
		// }
		// return category;

		return excelParserHelperService.getCategoryByName(categoryName);
	}

	Manufacturer getMfr(String mfrName) {
		// Manufacturer mfr = DataCacheMaps.mfrs.get(mfrName);
		// if (mfr == null) {
		// mfr = DataCacheMaps.mfrsTemp.get(mfrName);
		// }
		// if (mfr == null) {
		// mfr = excelParserHelperService.getMfrByName(mfrName);
		// if (mfr != null)
		// DataCacheMaps.mfrsTemp.put(mfrName, mfr);
		// }
		// return mfr;

		return excelParserHelperService.getMfrByName(mfrName);
	}

	Product getProduct(String productName) {
		// Product product = DataCacheMaps.products.get(productName);
		// if (product == null) {
		// product = DataCacheMaps.productsTemp.get(productName);
		// }
		// if (product == null) {
		// product = excelParserHelperService.getProductByIicSku(productName);
		// if (product != null)
		// DataCacheMaps.productsTemp.put(productName, product);
		// }
		// return product;

		return excelParserHelperService.getProductByIicSku(productName);
	}

	// ExcelDataModel createExcelDataModel(){
	//
	// }
}
