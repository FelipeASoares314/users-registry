package br.com.fas.usersregistry.utils;

import org.springframework.beans.BeanUtils;

public class BeanUtilsClassMerge implements ClassMerge {

	@Override
	public <T> T merge(Object a, T b) {
		BeanUtils.copyProperties(a, b);
		return b;
	}

}
