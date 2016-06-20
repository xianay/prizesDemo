package com.tyc.lottery.award.prizes.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

interface PropertiesFile{
	String CONFIG_FILE = "config.properties";
}

/**
 * 属性文件
 * @author Administrator
 *
 */
public enum PropertiesUtils implements PropertiesFile{	
	CONFIG(CONFIG_FILE);
	private final Map<Object,Object> map = new HashMap<Object,Object>();	
	private PropertiesUtils(String file){
		Properties properties = new Properties();	
		InputStream in = null;
		try {
			in=PropertiesUtils.class.getClassLoader().getResourceAsStream(file);	
			properties.load(in);
			for(Map.Entry<?, ?> entry : properties.entrySet())
				map.put(entry.getKey(), entry.getValue());			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 获得 properties的值
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T>T get(String key){
		return (T) map.get(key);
	}
	
	public Map<Object,Object> getMap(){
		return map;
	}

}




   