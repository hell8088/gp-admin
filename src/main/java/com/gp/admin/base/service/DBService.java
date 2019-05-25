package com.gp.admin.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.base.domain.Domain;

/**
 * 
 * @author wangjiehan
 *
 * @param <D>
 * @param <T>
 */
@Service
public class DBService<D extends BaseDao<T>, T extends Domain> {
	
	public int insert(D dao, T t) {
		Map<String, T> model = new HashMap<String, T>();
		model.put("model", t);
		return dao.insert(model);
	}

	public void batchInsert(D dao, List<T> models) {
		dao.batchInsert(models);
	}

	public int update(D dao, T t) {
		Map<String, T> model = new HashMap<String, T>();
		model.put("model", t);
		return dao.update(model);
	}

	public int delete(D dao, T t) {
		Map<String, T> model = new HashMap<String, T>();
		model.put("model", t);
		return dao.delete(model);
	}

	public int deleteByPrimaryKey(D dao, long id) {
		return dao.deleteByPrimaryKey(id);
	}

	public List<T> find(D dao, Map<String, Object> filters){
		return dao.find(filters);
	}

	public long findCount(D dao, Map<String, Object> filters){
		return dao.findCount(filters);
	}

	public T findByPrimaryKey(D dao, long id){
		return dao.findByPrimaryKey(id);
	}

}
