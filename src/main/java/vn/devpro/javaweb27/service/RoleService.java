package vn.devpro.javaweb27.service;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.Role;

@Service
public class RoleService extends BaseService<Role> {

	@Override
	public Class<Role> clazz() {
		return Role.class;
	}

	public List<Role> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_role WHERE status=1");
	}
	
	@Transactional
	public void deleteRoleById(int roleId) {
		super.deleteById(roleId);
	}

	@Transactional
	public Role getRoleByName(String name) {
		return super.executeNativeSql("SELECT * FROM tbl_role WHERE status=1 AND name='" + name + "'").get(0);
	}

	@Transactional
	public List<Role> searchRole (SearchModel roleSearch) {
		String sql = "SELECT * FROM tbl_role r WHERE 1=1";
		
		if (roleSearch.getStatus() != 2) {
			sql += " AND r.status=" + roleSearch.getStatus();
		}

		if (!StringUtils.isEmpty(roleSearch.getKeyword())) {
			String keyword = roleSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(r.name) like '%" + keyword + "%'" +
					" OR LOWER(r.description) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(roleSearch.getBeginDate()) && !StringUtils.isEmpty(roleSearch.getEndDate())) {
			String beginDate = roleSearch.getBeginDate();
			String endDate = roleSearch.getEndDate();
			sql += " AND p.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		sql += " LIMIT " + roleSearch.getSizeOfPage() + " OFFSET " + (roleSearch.getCurrentPage() - 1) * roleSearch.getSizeOfPage();
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public int countTotalItems (SearchModel roleSearch) {
		String sql = "SELECT COUNT(*) FROM tbl_role r WHERE 1=1";
		
		if (roleSearch.getStatus() != 2) {
			sql += " AND r.status=" + roleSearch.getStatus();
		}

		if (!StringUtils.isEmpty(roleSearch.getKeyword())) {
			String keyword = roleSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(r.name) like '%" + keyword + "%'" +
					" OR LOWER(r.description) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(roleSearch.getBeginDate()) && !StringUtils.isEmpty(roleSearch.getEndDate())) {
			String beginDate = roleSearch.getBeginDate();
			String endDate = roleSearch.getEndDate();
			sql += " AND p.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		sql += " LIMIT " + roleSearch.getSizeOfPage() + " OFFSET " + (roleSearch.getCurrentPage() - 1) * roleSearch.getSizeOfPage();
		
		BigInteger totalItems = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
	    return totalItems.intValue();
	}

}
