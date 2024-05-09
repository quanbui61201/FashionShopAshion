package vn.devpro.javaweb27.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.javaweb27.model.BaseModel;

@Service
public abstract class BaseService<E extends BaseModel> {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public abstract Class<E> clazz();
	
	public E getById(int id) {
		return entityManager.find(clazz(), id);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Table table = clazz().getAnnotation(Table.class);
		return (List<E>) entityManager.createNativeQuery("SELECT * FROM " + table.name(), clazz()).getResultList();
	}
	
	@Transactional
	public E saveOrUpdate(E entity) {
		if (entity.getId() == null || entity.getId() <= 0) {
			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}
	}
	
	public void delete(E entity) {
		entityManager.remove(entity);
	}
	
	public void deleteById(int id) {
		E entity = this.getById(id);
		entityManager.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> executeNativeSql(String sql) {
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public E getEntityByNativeSQL(String sql) {
		List<E> list = executeNativeSql(sql);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
}
