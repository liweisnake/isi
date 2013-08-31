package skynet.isi.service.impl;

import java.io.FileWriter;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.dao.ProductDao;
import skynet.isi.model.Product;

@Transactional
@Service
public class CheckServiceImpl {

	@Autowired
	ProductDao productDao;

	public void checkProduct() {
		FileWriter fw = null;
		try {
			fw = new FileWriter("check");
			for (int i = 1000001; i < 1012656; i++) {
				List<Product> products = productDao.find(Restrictions.eq(
						"iicSku", i + ""));
				if (products.size() < 1)
					fw.write(i + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
