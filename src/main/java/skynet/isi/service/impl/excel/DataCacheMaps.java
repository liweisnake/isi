package skynet.isi.service.impl.excel;

import java.util.LinkedHashMap;
import java.util.Map;

import skynet.isi.model.Category;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;

public class DataCacheMaps {

	// iccSku, product
	static Map<String, Product> products = new LinkedHashMap<String, Product>();
	// category name, category
	static Map<String, Category> categories = new LinkedHashMap<String, Category>();
	// mfr name, mfr
	static Map<String, Manufacturer> mfrs = new LinkedHashMap<String, Manufacturer>();

	// iccSku, product
	static Map<String, Product> productsTemp = new LinkedHashMap<String, Product>();
	// category name, category
	static Map<String, Category> categoriesTemp = new LinkedHashMap<String, Category>();
	// mfr name, mfr
	static Map<String, Manufacturer> mfrsTemp = new LinkedHashMap<String, Manufacturer>();

	static void clear() {
		categories.clear();
		mfrs.clear();
		products.clear();
		categoriesTemp.clear();
		mfrsTemp.clear();
		productsTemp.clear();
	}
}
