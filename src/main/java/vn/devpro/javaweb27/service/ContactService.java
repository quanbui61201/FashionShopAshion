package vn.devpro.javaweb27.service;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.Contact;

@Service
public class ContactService extends BaseService<Contact> {

	@Override
	public Class<Contact> clazz() {
		return Contact.class;
	}

	public List<Contact> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_contact WHERE status=1");
	}
	
	@Transactional
	public void deleteContactById(int contactId) {
		super.deleteById(contactId);
	}

	@Transactional
	public List<Contact> searchContact (SearchModel contactSearch) {
		
		String sql = "SELECT * FROM tbl_contact c WHERE 1=1";
		
		if (contactSearch.getStatus() != 2) {
			sql += " AND c.status=" + contactSearch.getStatus();
		}

		if (!StringUtils.isEmpty(contactSearch.getKeyword())) {
			String keyword = contactSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(c.name) like '%" + keyword + "%'" + 
					" OR LOWER(c.mobile) like '%" + keyword + "%'" + 
					" OR LOWER(c.email) like '%" + keyword + "%'" + 
					" OR LOWER(c.address) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(contactSearch.getBeginDate()) && !StringUtils.isEmpty(contactSearch.getEndDate())) {
			String beginDate = contactSearch.getBeginDate();
			String endDate = contactSearch.getEndDate();
			sql += " AND c.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}

		sql += " LIMIT " + contactSearch.getSizeOfPage() + " OFFSET " + (contactSearch.getCurrentPage() - 1) * contactSearch.getSizeOfPage();
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public int countTotalItems (SearchModel contactSearch) {
		
		String sql = "SELECT COUNT(*) FROM tbl_contact c WHERE 1=1";
		
		if (contactSearch.getStatus() != 2) {
			sql += " AND c.status=" + contactSearch.getStatus();
		}

		if (!StringUtils.isEmpty(contactSearch.getKeyword())) {
			String keyword = contactSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(c.name) like '%" + keyword + "%'" + 
					" OR LOWER(c.mobile) like '%" + keyword + "%'" + 
					" OR LOWER(c.email) like '%" + keyword + "%'" + 
					" OR LOWER(c.address) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(contactSearch.getBeginDate()) && !StringUtils.isEmpty(contactSearch.getEndDate())) {
			String beginDate = contactSearch.getBeginDate();
			String endDate = contactSearch.getEndDate();
			sql += " AND c.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}

		BigInteger totalItems = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
	    return totalItems.intValue();
	}
}
