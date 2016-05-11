package com.lift.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取属性文件
 * 
 * @author WuNan2
 * 
 */
public final class ConfigurationUtil {
	private static final Logger logger = Logger.getLogger(ConfigurationUtil.class);
	private Properties p = new Properties();

	/**
	 * 静态读入属性文件到Properties p变量中
	 */
	public ConfigurationUtil(String propertyFileName) {
		InputStream in = null;
		try {
			in = ConfigurationUtil.class.getClassLoader().getResourceAsStream(
					propertyFileName);
			if (in != null)
				p.load(in);
			else
				logger.error("load " + propertyFileName + " into Constants error!");
		} catch (IOException e) {
			logger.error("load " + propertyFileName + " into Constants error!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close " + propertyFileName + " error!");
				}
			}
		}
	}

	/**
	 * 封装了Properties类的getProperty函数,使p变量对子类透明.
	 * 
	 * @param key
	 *            property key.
	 * @param defaultValue
	 *            当使用property key在properties中取不到值时的默认值.
	 */
	public String getProperty(String key, String defaultValue) {
		if(null == p){
			logger.error("Property File Error!");
			return defaultValue;
		}
		return p.getProperty(key, defaultValue);
	}

	/**
	 * 封装了Properties类的getProperty函数,使p变量对子类透明.
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		if(null == p){
			logger.error("Property File Error!");
			return null;
		}
		return p.getProperty(key);
	}
}
