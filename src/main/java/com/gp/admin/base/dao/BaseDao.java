package com.gp.admin.base.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wangjiehan
 *
 * @param <T>
 */
public interface BaseDao<T> {

	public int insert(Map<String, T> param);
	
	public void batchInsert(List<T> models);
	
	public int update(Map<String, T> param);
	
	public int delete(Map<String, T> param);
	
	public int deleteByPrimaryKey(long id);
	
	public List<T> find(Map<String, Object> filters);
	
	public long findCount(Map<String, Object> filters);
	
	public T findByPrimaryKey(long id);

}
