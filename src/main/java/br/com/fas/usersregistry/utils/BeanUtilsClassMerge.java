package br.com.fas.usersregistry.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsClassMerge implements ClassMerge {

	@Override
	public <T> T merge(Object a, T b) {
		try {
			BeanUtils.copyProperties(b, a);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Could not merge objcts " + a + " " + b, e);
		} 
		
		return b;
	}

}
