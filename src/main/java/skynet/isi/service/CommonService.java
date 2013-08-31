package skynet.isi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import skynet.isi.model.Promotion;

public interface CommonService {
	public String format(double d);

	public String getSystemConfigStringProperty(String key, String defaultValue);

	public int getSystemConfigIntProperty(String key, int defaultValue);

	public long getSystemConfigLongProperty(String key, long defaultValue);

	public double getSystemConfigDoubleProperty(String key, double defaultValue);

	public BigDecimal getSystemConfigBigDecimalProperty(String key,
			BigDecimal defaultValue);

	public Date getSystemConfigDateProperty(String key, Date defaultValue);

	public boolean getSystemConfigBooleanProperty(String key,
			boolean defaultValue);

	public void setSystemConfigStringProperty(String key, String defaultValue);
	
	public void checkPagingModel(PagingModel model);
	
	public List<Promotion> getPromotionList();
}
