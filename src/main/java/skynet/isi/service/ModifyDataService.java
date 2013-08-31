package skynet.isi.service;

import java.io.IOException;
import java.util.List;

import skynet.isi.resultmodel.ProductInfo;
import skynet.isi.service.impl.excel.ExcelDataModel;

public interface ModifyDataService {

	public List<String> process(String fileName, boolean isAbsolutePath)
			throws IOException;

	public void adapterAndExcute(ExcelDataModel excelModel);

	public void update(ProductInfo info);

	public ProductInfo getInfo(String iccSku);

}
