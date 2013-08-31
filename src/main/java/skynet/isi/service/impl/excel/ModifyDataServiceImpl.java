package skynet.isi.service.impl.excel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.model.Category;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;
import skynet.isi.resultmodel.ProductInfo;
import skynet.isi.service.ModifyDataService;

@Transactional
@Service
// @Profile(value = "test")
// @Profile(value = "production")
public class ModifyDataServiceImpl extends BaseDataServiceImpl implements
		ModifyDataService {

	public void adapterAndExcute(ExcelDataModel excelModel) {

		String mfrName = excelModel.getManufacturer();
		String parentCategoryName = excelModel.getFatherCategory();
		String middleCategoryName = excelModel.getMiddleCategory();
		String childCategoryName = excelModel.getCategory();
		String iicSku = excelModel.getIccSku();

		Manufacturer mfr = getMfr(mfrName);
		Category parentCategory = getCategory(parentCategoryName);
		Category middleCategory = getCategory(middleCategoryName);
		Category childCategory = getCategory(childCategoryName);
		Product product = getProduct(iicSku);

		// 创建manufacturer
		if (mfr == null) {
			mfr = createManufacturer(mfrName);
			// DataCacheMaps.mfrs.put(mfrName, mfr);
			executeMfr(mfr);
		}

		// 创建category
		if (parentCategory == null) {
			parentCategory = createParentCategory(parentCategoryName);
			// DataCacheMaps.categories.put(parentCategoryName, parentCategory);
			executeCategory(parentCategory);
		}
		if (middleCategory == null) {
			middleCategory = createChildCategory(middleCategoryName,
					parentCategory);
			executeCategory(middleCategory);
			addCount(parentCategoryName);
		}
		if (childCategory == null) {
			childCategory = createChildCategory(childCategoryName,
					middleCategory);
			// DataCacheMaps.categories.put(childCategoryName, childCategory);
			executeCategory(childCategory);
			addCount(middleCategoryName);
		} 

		// 创建product
		// if (product == null)
		// DataCacheMaps.products.put(excelModel.getIccSku(),
		// createProduct(excelModel, mfr, childCategory));
		boolean isNew = false;
		if (product == null) {
			product = new Product();
			isNew = true;
		}
		executeProduct(fillProduct(excelModel, mfr, childCategory, product));

		// addCount(childCategory);
		if (isNew)
			addCount(childCategoryName);
	}

	public void update(ProductInfo info) {
		String mfrName = info.getManufacturer();
		String iicSku = info.getIccSku();

		Manufacturer mfr = getMfr(mfrName);
		Product product = getProduct(iicSku);

		// 创建manufacturer
		if (mfr == null) {
			mfr = createManufacturer(mfrName);
			executeMfr(mfr);
		}

		executeProduct(fillProduct(info, mfr, product));
	}

	public ProductInfo getInfo(String iccSku) {
		Product product = getProduct(iccSku);
		if (product != null) {
			Manufacturer mfr = product.getManufacturer();

			ProductInfo info = new ProductInfo(filter(iccSku),
					filter(product.getDistySku()), filter(product.getMfrSku()),
					filter(mfr.getMfrName()), filter(product.getDescription()),
					filter(product.getLongDesc()), filter(product.getUom()),
					filter(product.getPackSize() + ""),
					filter(product.getRohsStatus()),
					filter(product.getDataSheet()),
					filter(product.getImageUrl()),
					filter(product.getCountryOrigin()),
					filter(product.getStore()), filter(product.getOneAmount()
							+ ""), filter(product.getOnePrice() + ""));

			return info;
		} else
			return null;
	}

}
