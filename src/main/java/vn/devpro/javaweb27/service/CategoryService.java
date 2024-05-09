package vn.devpro.javaweb27.service;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.Category;

@Service
public class CategoryService extends BaseService<Category> {
	
	@Override
	public Class<Category> clazz() {
		return Category.class;
	}
	
	public List<Category> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_category WHERE status=1");
	}
	
	@Transactional
	public void deleteCategoryById(int categoryId) {
		super.deleteById(categoryId);
	}

	@Transactional
	public List<Category> searchCategory (SearchModel categorySearch) {
		String sql = "SELECT * FROM tbl_category c WHERE 1=1";
		
		if (categorySearch.getStatus() != 2) {
			sql += " AND c.status=" + categorySearch.getStatus();
		}

		if (!StringUtils.isEmpty(categorySearch.getKeyword())) {
			String keyword = categorySearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(c.name) like '%" + keyword + "%'" +
					" OR LOWER(c.seo) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(categorySearch.getBeginDate()) && !StringUtils.isEmpty(categorySearch.getEndDate())) {
			String beginDate = categorySearch.getBeginDate();
			String endDate = categorySearch.getEndDate();
			sql += " AND c.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		sql += " LIMIT " + categorySearch.getSizeOfPage() + " OFFSET " + (categorySearch.getCurrentPage() - 1) * categorySearch.getSizeOfPage();
		
		return super.executeNativeSql(sql);
	}
	
	@Transactional
	public int countTotalItems(SearchModel categorySearch) {
	    String sql = "SELECT COUNT(*) FROM tbl_category c WHERE 1=1";

	    if (categorySearch.getStatus() != 2) {
	        sql += " AND c.status=" + categorySearch.getStatus();
	    }

	    if (!StringUtils.isEmpty(categorySearch.getKeyword())) {
	        String keyword = categorySearch.getKeyword().toLowerCase();
	        sql +=  " AND (LOWER(c.name) like '%" + keyword + "%'" +
	                " OR LOWER(c.seo) like '%" + keyword + "%')";
	    }

	    if (!StringUtils.isEmpty(categorySearch.getBeginDate()) && !StringUtils.isEmpty(categorySearch.getEndDate())) {
	        String beginDate = categorySearch.getBeginDate();
	        String endDate = categorySearch.getEndDate();
	        sql += " AND c.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
	    }

	    BigInteger totalItems = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
	    return totalItems.intValue();
	}

	@Transactional
	public int countProductsById(int categoryId) {
	    String sql = "SELECT COUNT(*) FROM tbl_product p WHERE 1=1 AND p.category_id=" + categoryId;

	    BigInteger totalItems = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
	    return totalItems.intValue();
	}
	
}
