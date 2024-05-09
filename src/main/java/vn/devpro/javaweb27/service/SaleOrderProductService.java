package vn.devpro.javaweb27.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.javaweb27.model.SaleOrderProduct;

@Service
public class SaleOrderProductService extends BaseService<SaleOrderProduct> {

	@Override
	public Class<SaleOrderProduct> clazz() {
		return SaleOrderProduct.class;
	}
	
	@Transactional
	public List<SaleOrderProduct> findByOrderId(int orderId) {
		String sql = "SELECT * FROM tbl_sale_order_product WHERE sale_order_id = " + orderId;
				
		return super.executeNativeSql(sql);
	}
}
