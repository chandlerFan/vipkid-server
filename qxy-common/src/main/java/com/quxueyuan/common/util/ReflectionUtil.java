/** 
* Project Name: 26.02.06.XX 
* File Name: RefectionUtil.java 
* Package Name: com.huifenqi.saas.utils 
* Date: 2016年12月20日下午10:55:36 
* Copyright (c) 2016, www.huizhaofang.com All Rights Reserved. 
* 
*/
package com.quxueyuan.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
* ClassName: RefectionUtil
* date: 2016年12月20日 下午10:55:36
* Description: 
* 
* @author maoxinwu 
* @version  
* @since JDK 1.8 
*/
public class ReflectionUtil extends ReflectionUtils {

	private static final String SETTER_PREFIX = "set";

	private static final String GETTER_PREFIX = "get";

	/**
	 * 从子类角度查询所有生效的字段(直接使用)
	 * @param clazz
	 * @return
	 */
	public static List<Field> getAllEffectFields(Class<?> clazz) {

		List<Field> fieldList = new ArrayList<Field>();
		List<String> fieldNameList = new ArrayList<String>();

		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields();
				if (fields != null && fields.length > 0) {
					for (Field field : fields) {
						String fieldName = field.getName();
						if (!fieldNameList.contains(fieldName)) {
							fieldNameList.add(fieldName);
							fieldList.add(field);
						}
					}
				}
			} catch (Exception e) {
				// 这里甚么都不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会进入
			}
		}

		return fieldList;

	}

	/**
	 * 从子类角度查询所有生效的方法(直接使用)
	 * @param clazz
	 * @return
	 */
	public static List<Method> getAllEffectMethods(Class<?> clazz) {

		List<Method> methodList = new ArrayList<Method>();

		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Method[] methods = clazz.getDeclaredMethods();
				if (methods != null && methods.length > 0) {
					for (Method method : methods) {
						String methodName = method.getName();
						Class<?>[] parameterTypes = method.getParameterTypes();
						if (!isMethodExist(methodList, methodName, parameterTypes)) {
							methodList.add(method);
						}
					}
				}
			} catch (Exception e) {
				// 这里甚么都不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会进入
			}
		}

		return methodList;

	}

	/**
	 * 方法列表中是否存在相同方法名与入参的方法
	 * @param methodList
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static boolean isMethodExist(List<Method> methodList, String methodName, Class<?>[] parameterTypes) {
		if (methodList == null || methodList.size() == 0) {
			return false;
		}

		for (Method method : methodList) {
			String queryName = method.getName();
			Class<?>[] queryParameterTypes = method.getParameterTypes();
			if (queryName.equals(methodName) && Arrays.equals(parameterTypes, queryParameterTypes)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 调用Getter方法.
	 * 支持多级，如：对象名.对象名.方法
	 */
	public static Object invokeGetter(Object obj, String propertyName) {
		Object object = obj;
		for (String name : StringUtils.split(propertyName, ".")) {
			String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
			Method method = findMethod(obj.getClass(), getterMethodName);
			object = invokeMethod(method, obj, new Object[] {});
		}
		return object;
	}

	/**
	 * 调用Setter方法, 仅匹配方法名。
	 * 支持多级，如：对象名.对象名.方法
	 */
	public static void invokeSetter(Object obj, String propertyName, Object value, Class<?>... paramTypes) {
		Object object = obj;
		String[] names = StringUtils.split(propertyName, ".");
		for (int i = 0; i < names.length; i++) {
			if (i < names.length - 1) {
				String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(names[i]);
				Method method = findMethod(obj.getClass(), getterMethodName);
				object = invokeMethod(method, object, new Object[] {});
			} else {
				String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(names[i]);
				Method method = findMethod(obj.getClass(), setterMethodName, paramTypes);
				object = invokeMethod(method, obj, new Object[] { value });
			}
		}
	}

}
