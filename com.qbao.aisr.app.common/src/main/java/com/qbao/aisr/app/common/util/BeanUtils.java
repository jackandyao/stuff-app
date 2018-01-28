/**
 * 
 */
package com.qbao.aisr.app.common.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shuaizhihu
 *
 * $LastChangedDate: 2016-06-27 09:15:54 +0800 (周一, 27 六月 2016) $
 * $LastChangedRevision: 36 $
 * $LastChangedBy: shuaizhihu $
 */
public class BeanUtils {
    
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }
    
    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
    
	@SuppressWarnings("rawtypes")
	public static Map<Object,List> group(List list, String property){
		Map<Object,List> map = new LinkedHashMap<Object,List>();
		for(Object bean : list){
			Object key = getProperty(bean, property);
			List valueList = map.get(key);
			if(valueList==null){
				valueList = new ArrayList();
				map.put(key, valueList);
			}
			valueList.add(bean);
		}
		return map;
	}
	
	static <T> T getProperty(Object bean, String property){
		try {
			return (T)PropertyUtils.getProperty(bean, property);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}