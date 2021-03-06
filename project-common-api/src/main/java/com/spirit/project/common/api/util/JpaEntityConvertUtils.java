package com.spirit.project.common.api.util;

import java.lang.reflect.Constructor;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * Jpa原生SQL转换工具类
 * 
 * @author dante
 *
 */
public class JpaEntityConvertUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JpaEntityConvertUtils.class);
	
	private JpaEntityConvertUtils() {
		throw new IllegalAccessError("JpaEntityConvertUtils 工具类，不能实例化！");
	}
	
	/**
	 * 根据查询结果，在返回类中定义一个构造函数，字段顺序要和查询顺序完全一致
	 * 
	 * @param list
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) {  
        List<T> returnList = Lists.newArrayList();   
        try {
        	if(CollectionUtils.isEmpty(list)) {
        		return returnList;
        	}
        	Object[] co = list.get(0);    
        	Class<?>[] c2 = new Class[co.length];
            for(int i = 0; i < co.length; i++){  
            	c2[i] = Object.class;  
            }    
            for(Object[] o : list){    
                Constructor<T> constructor = clazz.getConstructor(c2);    
                returnList.add(constructor.newInstance(o));    
            }
        } catch (Exception e) {
        	LOGGER.error("JpaEntityConvertUtils castEntity error.", e);
		}
        return returnList;    
    } 
	
}
