/*
 * Copyright 2015 the original author or phly.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.factorybean;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.hunter.common.base.autoscan.MyBatisMapperScanner;

/**
 * 自定义mybatis session工厂bean
 * 
 * @Description: 处理自定义包路径
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
public class MybatisSqlSessionFactoryBean extends SqlSessionFactoryBean {
	private String packageName;

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		super.setMapperLocations(new MyBatisMapperScanner().scan(packageName));
		return super.buildSqlSessionFactory();
	}
}
