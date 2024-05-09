package vn.devpro.javaweb27.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.javaweb27.model.ProductImage;

@Service
public class ProductImageService extends BaseService<ProductImage> {

	@Override
	public Class<ProductImage> clazz() {
		return ProductImage.class;
	}

	public List<ProductImage> getProductImagesByProductId(int productId) {
		String sql = "SELECT * FROM tbl_product_image pi WHERE pi.product_id=" + productId;
		
		return super.executeNativeSql(sql);
	}
	
	@Transactional
	public void deleteProductImageById(int productImageId) {
		super.deleteById(productImageId);
	}

}
