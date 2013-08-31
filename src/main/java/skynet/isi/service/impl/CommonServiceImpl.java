package skynet.isi.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import skynet.isi.common.SystemConfigConstant;
import skynet.isi.dao.PromotionDao;
import skynet.isi.dao.SystemConfigDao;
import skynet.isi.model.Promotion;
import skynet.isi.model.SystemConfig;
import skynet.isi.service.CommonService;
import skynet.isi.service.PagingModel;

@Service
public class CommonServiceImpl implements CommonService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Autowired
	private SystemConfigDao systemConfigDao;
	
	@Autowired
	private PromotionDao promotionDao;
	
	public String format(double d){
		DecimalFormat df=new DecimalFormat("#.## "); 
        return df.format(d);
	}

	public int getSystemConfigIntProperty(String key, int defaultValue) {
		String result = getSystemConfigStringProperty(key, defaultValue + "");
		return Integer.parseInt(result);
	}

	public long getSystemConfigLongProperty(String key, long defaultValue) {
		String result = getSystemConfigStringProperty(key, defaultValue + "");
		return Long.parseLong(result);
	}

	public BigDecimal getSystemConfigBigDecimalProperty(String key,
			BigDecimal defaultValue) {
		String result = getSystemConfigStringProperty(key, defaultValue + "");
		return new BigDecimal(result);
	}

	public Date getSystemConfigDateProperty(String key, Date defaultValue) {
		String result = getSystemConfigStringProperty(key, defaultValue + "");
		try {
			return sdf.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean getSystemConfigBooleanProperty(String key,
			boolean defaultValue) {
		String result = getSystemConfigStringProperty(key, defaultValue + "");
		return Boolean.parseBoolean(result);
	}

	public double getSystemConfigDoubleProperty(String key, double defaultValue) {
		String result = getSystemConfigStringProperty(key, defaultValue + "");
		return Double.parseDouble(result);
	}

	public String getSystemConfigStringProperty(String key, String defaultValue) {
		assert !StringUtils.isEmpty(key);
		assert defaultValue != null;
		List<SystemConfig> result = systemConfigDao.findBy("configKey", key);
		if (result.isEmpty()) {
			SystemConfig config = new SystemConfig();
			config.setConfigKey(key);
			config.setConfigValue(defaultValue);
			systemConfigDao.save(config);
			return defaultValue;
		} else {
			return result.get(0).getConfigValue();
		}
	}

	public void setSystemConfigStringProperty(String key, String value) {
		List<SystemConfig> result = systemConfigDao.findBy("configKey", key);
		if (!result.isEmpty()) {
			SystemConfig entity = result.get(0);
			entity.setConfigValue(value);
			systemConfigDao.save(entity);
		}
	}
	
	public void checkPagingModel(PagingModel model) {
		if (model.getLimit() <= 0) {
			model.setLimit(getSystemConfigIntProperty(
					SystemConfigConstant.PRODUCT_PAGE_COUNT, 10));
		}
	}
	
	public List<Promotion> getPromotionList(){
		return promotionDao.getAll();
	}
}
