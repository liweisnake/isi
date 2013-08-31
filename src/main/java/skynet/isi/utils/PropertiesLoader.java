package skynet.isi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Properties文件载入工具�? 可载入多个properties文件, 相同的属性在�?��载入的文件中的�?将会覆盖之前的�?.
 * 
 */
public class PropertiesLoader {

	private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private Properties properties;

	public PropertiesLoader(String... resourcesPaths) {
		properties = loadProperties(resourcesPaths);
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * 取出Property，但以System的Property优先.
	 */
	public String getProperty(String key) {
		String result = System.getProperty(key);
		if (result != null) {
			return result;
		}
		return properties.getProperty(key);
	}

	/**
	 * 取出Property，但以System的Property优先.
	 */
	public String getProperty(String key, String defaultValue) {
		String result = getProperty(key);
		if (result != null) {
			return result;
		} else {
			return defaultValue;
		}
	}

	/**
	 * 取出Property，但以System的Property优先.
	 */
	public Integer getInteger(String key) {
		return Integer.valueOf(getProperty(key));
	}

	/**
	 * 取出Property，但以System的Property优先.
	 */
	public Integer getInteger(String key, Integer defaultValue) {
		return Integer.valueOf(getProperty(key, String.valueOf(defaultValue)));
	}

	/**
	 * 取出Property，但以System的Property优先.
	 */
	public Boolean getBoolean(String key) {
		return Boolean.valueOf(getProperty(key));
	}

	/**
	 * 取出Property，但以System的Property优先.
	 */
	public Boolean getBoolean(String key, boolean defaultValue) {
		return Boolean.valueOf(getProperty(key, String.valueOf(defaultValue)));
	}

	/**
	 * 载入多个文件, 文件路径使用Spring Resource格式.
	 */
	private Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {

			logger.debug("Loading properties file from:" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				props.load(is);
			} catch (IOException ex) {
				logger.info("Could not load properties from path:" + location + ", " + ex.getMessage());
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		return props;
	}
}
