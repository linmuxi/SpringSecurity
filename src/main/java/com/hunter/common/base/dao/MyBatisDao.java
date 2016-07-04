/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hunter.common.base.exceptionhandler.exception.DaoException;
import com.hunter.common.base.model.BaseVO;

/**
 * mybatis持久类
 * 
 * @Description: 使用mybatis访问数据库公共类
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
@Repository
@Scope("prototype")
public class MyBatisDao {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public void delete(String statementId, Object deleteObject) {
		try {
			this.sqlSessionTemplate.delete(statementId, deleteObject);
		} catch (Exception e) {
			throw new DaoException("根据参数对象删除数据失败,参数列表(statementId:"
					+ statementId + " deleteObject:" + deleteObject + ")", e);
		}
	}

	public void insert(String statementId, Object insertObject) {
		try {
			this.sqlSessionTemplate.insert(statementId, insertObject);
		} catch (Exception e) {
			throw new DaoException("根据参数对象新增数据失败,参数列表(statementId:"
					+ statementId + " insertObject:" + insertObject + ")", e);
		}
	}

	public <T extends BaseVO> void insert(String statementId, T entity) {
		try {
			this.sqlSessionTemplate.insert(statementId, entity);
		} catch (Exception e) {
			throw new DaoException("根据参数对象新增数据失败,参数列表(statementId:"
					+ statementId + " entities:" + entity + ")", e);
		}
	}

	public void update(String statementId, Object updateObject) {
		try {
			this.sqlSessionTemplate.update(statementId, updateObject);
		} catch (Exception e) {
			throw new DaoException("根据参数对象更新数据失败,参数列表(statementId:"
					+ statementId + " updateObject:" + updateObject + ")", e);
		}
	}

	public <T extends BaseVO> void update(String statementId, T entity) {
		try {
			this.sqlSessionTemplate.update(statementId, entity);
		} catch (Exception e) {
			throw new DaoException("根据参数对象更新数据失败,参数列表(statementId:"
					+ statementId + " entity:" + entity + ")", e);
		}
	}

	public Object getObject(String statementId, Object selectParamObject) {
		try {
			return this.sqlSessionTemplate.selectOne(statementId,
					selectParamObject);
		} catch (Exception e) {
			throw new DaoException("根据参数对象读取数据失败,参数列表(statementId:"
					+ statementId + " selectParamObject:" + selectParamObject
					+ ")", e);
		}
	}

	public <T extends Serializable> List<T> queryList(String statementId,
			Object queryParamObject) {
		try {
			return this.sqlSessionTemplate.selectList(statementId,
					queryParamObject);
		} catch (Exception e) {
			throw new DaoException("查询全部数据失败,参数列表(statementId:" + statementId
					+ " queryParamObject:" + queryParamObject + ")", e);
		}
	}

	public <T extends Serializable> List<T> queryList(String statementId,
			Object queryParamObject, int pageNo, int pageSize) {
		try {
			int iOffset = (pageNo - 1) * pageSize;
			RowBounds objRowBounds = new RowBounds(iOffset, pageSize);
			return this.sqlSessionTemplate.selectList(statementId,
					queryParamObject, objRowBounds);
		} catch (Exception e) {
			throw new DaoException("分页查询全部数据失败,参数列表(statementId:" + statementId
					+ " queryParamObject:" + queryParamObject + " pageNo:"
					+ pageNo + " pageSize:" + pageSize + ")", e);
		}
	}
	
	
	public List<Map<String,Object>> queryListMap(String statementId,Object queryParamObject){
		try {
			return this.sqlSessionTemplate.selectList(statementId,queryParamObject);
		} catch (Exception e) {
			throw new DaoException("查询全部数据失败,参数列表(statementId:" + statementId
					+ " queryParamObject:" + queryParamObject + ")", e);
		}
	}
}
