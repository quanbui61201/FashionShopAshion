package vn.devpro.javaweb27.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.javaweb27.model.UserRole;

@Service
public class UserRoleService {

	@PersistenceContext
	EntityManager entityManager;

	public UserRole getById(int id) {
		return entityManager.find(UserRole.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findAll() {
		return entityManager.createNativeQuery("SELECT * FROM tbl_user_role").getResultList();
	}
	
	@Transactional
	public UserRole saveOrUpdate(UserRole userRole) {
		if (userRole.getId() == null || userRole.getId() <= 0) {
			entityManager.persist(userRole);
			return userRole;
		} else {
			return entityManager.merge(userRole);
		}
	}

	public void delete(UserRole userRole) {
		entityManager.remove(userRole);
	}

	public void deleteById(int id) {
		UserRole userRole = this.getById(id);
		entityManager.remove(userRole);
	}
	
}
