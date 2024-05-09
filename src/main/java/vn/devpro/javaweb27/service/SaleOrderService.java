package vn.devpro.javaweb27.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.SaleOrder;

@Service
public class SaleOrderService extends BaseService<SaleOrder> {

	@Override
	public Class<SaleOrder> clazz() {
		return SaleOrder.class;
	}

	public List<SaleOrder> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_sale_order WHERE status=1");
	}

	@Transactional
	public void deleteSaleOrderById(int saleOrderId) {
		super.deleteById(saleOrderId);
	}

	@Transactional
	public List<SaleOrder> searchOrder (SearchModel orderSearch) {
		
		String sql = "SELECT * FROM tbl_sale_order so WHERE 1=1";
		
		if (orderSearch.getStatus() != 2) {
			sql += " AND so.status=" + orderSearch.getStatus();
		}

		if (!StringUtils.isEmpty(orderSearch.getKeyword())) {
			String keyword = orderSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(so.code) like '%" + keyword + "%'" +
					" OR LOWER(so.customer_name) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_mobile) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_email) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_address) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(orderSearch.getBeginDate()) && !StringUtils.isEmpty(orderSearch.getEndDate())) {
			String beginDate = orderSearch.getBeginDate();
			String endDate = orderSearch.getEndDate();
			sql += " AND so.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		sql += " ORDER BY so.id DESC";
		sql += " LIMIT " + orderSearch.getSizeOfPage() + " OFFSET " + (orderSearch.getCurrentPage() - 1) * orderSearch.getSizeOfPage();
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public int countTotalItems (SearchModel orderSearch) {
		
		String sql = "SELECT COUNT(*) FROM tbl_sale_order so WHERE 1=1";
		
		if (orderSearch.getStatus() != 2) {
			sql += " AND so.status=" + orderSearch.getStatus();
		}

		if (!StringUtils.isEmpty(orderSearch.getKeyword())) {
			String keyword = orderSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(so.code) like '%" + keyword + "%'" +
					" OR LOWER(so.customer_name) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_mobile) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_email) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_address) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(orderSearch.getBeginDate()) && !StringUtils.isEmpty(orderSearch.getEndDate())) {
			String beginDate = orderSearch.getBeginDate();
			String endDate = orderSearch.getEndDate();
			sql += " AND so.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		BigInteger totalItems = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
	    return totalItems.intValue();
	}

	@Transactional
	public int totalSale (SearchModel orderSearch) {
		
		String sql = "SELECT SUM(so.total) FROM tbl_sale_order so WHERE so.status = 1";
		
		if (!StringUtils.isEmpty(orderSearch.getKeyword())) {
			String keyword = orderSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(so.code) like '%" + keyword + "%'" +
					" OR LOWER(so.customer_name) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_mobile) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_email) like '%" + keyword + "%'" + 
					" OR LOWER(so.customer_address) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(orderSearch.getBeginDate()) && !StringUtils.isEmpty(orderSearch.getEndDate())) {
			String beginDate = orderSearch.getBeginDate();
			String endDate = orderSearch.getEndDate();
			sql += " AND so.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		BigDecimal total = (BigDecimal) entityManager.createNativeQuery(sql).getSingleResult();
	    return total.intValue();
	}

	@Transactional
	public SaleOrder saveOrder(SaleOrder saleOrder) {
		return super.saveOrUpdate(saleOrder);
	}
	
	public List<Integer> getMonthlyRevenue(int year) {
		List<Integer> monthlyRevenueList = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			BigDecimal revenue = (BigDecimal) entityManager
	                .createNativeQuery("SELECT COALESCE(SUM(total), 0) FROM tbl_sale_order WHERE status = 1 AND YEAR(create_date) = :year AND MONTH(create_date) = :month")
	                .setParameter("year", year)
	                .setParameter("month", i)
	                .getSingleResult();
			monthlyRevenueList.add(revenue.intValue());
		}

        return monthlyRevenueList;
    }

	public List<Integer> getMonthlyOrders(int year) {
		List<Integer> monthlyOrderList = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			BigInteger orders = (BigInteger) entityManager
	                .createNativeQuery("SELECT COALESCE(COUNT(*), 0) FROM tbl_sale_order WHERE status = 1 AND YEAR(create_date) = :year AND MONTH(create_date) = :month")
	                .setParameter("year", year)
	                .setParameter("month", i)
	                .getSingleResult();
			monthlyOrderList.add(orders.intValue());
		}

        return monthlyOrderList;
    }

	public List<Integer> getMonthlyProducts(int year) {
		List<Integer> monthlyProductList = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			BigDecimal products = (BigDecimal) entityManager
	                .createNativeQuery("SELECT COALESCE(SUM(quantity), 0) FROM tbl_sale_order_product WHERE status = 1 AND YEAR(create_date) = :year AND MONTH(create_date) = :month")
	                .setParameter("year", year)
	                .setParameter("month", i)
	                .getSingleResult();
			monthlyProductList.add(products.intValue());
		}

        return monthlyProductList;
    }
	
}
