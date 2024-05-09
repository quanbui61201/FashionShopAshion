package vn.devpro.javaweb27.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.ProductImage;

@Service
public class ProductService extends BaseService<Product> implements Jw27Constant {

	@Autowired
	private ProductImageService productImageService;
	
	@Override
	public Class<Product> clazz() {
		return Product.class;
	}

	public List<Product> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_product WHERE status=1 ORDER BY create_date DESC;");
	}
	
	public boolean isUploadFile(MultipartFile file) {
		if (file == null || file.getOriginalFilename().isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean isUploadFiles(MultipartFile[] files) {
		if (files == null || files.length == 0) {
			return false;
		}
		return true;
	}

	@Transactional
	public Product saveAddProduct(Product product, MultipartFile avatarFile, MultipartFile[] imageFiles) throws IOException {
		
		if (isUploadFile(avatarFile) ) {
			String path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
			File file  = new File(path);
			avatarFile.transferTo(file);
			product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
		}
		
		if (isUploadFiles(imageFiles)) {
			for (MultipartFile imageFile : imageFiles) {
				if (isUploadFile(imageFile)) {
					String path = FOLDER_UPLOAD + "Product/Image/" + imageFile.getOriginalFilename();
					File file  = new File(path);
					imageFile.transferTo(file);
					
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/" + imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					
					product.addRelationalImage(productImage);
				}
			}
		}
		
		return super.saveOrUpdate(product);
	}

	@Transactional
	public Product saveEditProduct(Product product, MultipartFile avatarFile, MultipartFile[] imageFiles) throws IOException {
		
		Product dbProduct = super.getById(product.getId());
		
		if (isUploadFile(avatarFile) ) {
			String path = FOLDER_UPLOAD + dbProduct.getAvatar();
			File file  = new File(path);
			file.delete();
			
			path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
			file  = new File(path);
			avatarFile.transferTo(file);
			
			product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
		} else {
	        product.setAvatar(dbProduct.getAvatar());
	    }
		
		if (isUploadFiles(imageFiles)) {
			for (MultipartFile imageFile : imageFiles) {
				if (isUploadFile(imageFile)) {
					String path = FOLDER_UPLOAD + "Product/Image/" + imageFile.getOriginalFilename();
					File file  = new File(path);
					imageFile.transferTo(file);
					
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/" + imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					
					product.addRelationalImage(productImage);
				}
			}
		}
		
		return super.saveOrUpdate(product);
	}

	@Transactional
	public void deleteProduct(Product product) {
		String sql = "SELECT * FROM tbl_product_image WHERE product_id =" + product.getId();
		List<ProductImage> productImages = productImageService.executeNativeSql(sql);
		
		for (ProductImage productImage : productImages) {
			String path = FOLDER_UPLOAD + productImage.getPath();
			File file = new File(path);
			file.delete();
			
//			product.removeRelationalImage(productImage); //restrict
		}
		
		String path = FOLDER_UPLOAD + product.getAvatar();
		File file = new File(path);
		file.delete();
		
		super.delete(product);
	}

	@Transactional
	public List<Product> searchProduct (SearchModel productSearch) {
		String sql = "SELECT * FROM tbl_product p WHERE 1=1";
		
		if (productSearch.getStatus() != 2) {
			sql += " AND p.status=" + productSearch.getStatus();
		}

		if (productSearch.getCategoryId() != 0) {
			sql += " AND p.category_id=" + productSearch.getCategoryId();
		}

	    if (productSearch.getProductPrice() != 0) {
	        switch (productSearch.getProductPrice()) {
	            case 1:
	                sql += " AND p.sale_price BETWEEN 0 AND 50";
	                break;
	            case 2:
	                sql += " AND p.sale_price BETWEEN 50 AND 200";
	                break;
	            case 3:
	                sql += " AND p.sale_price BETWEEN 200 AND 500";
	                break;
	            case 4:
	                sql += " AND p.sale_price BETWEEN 500 AND 1000";
	                break;
	            case 5:
	                sql += " AND p.sale_price > 1000";
	                break;
	            default:
	                break;
	        }
	    }

		if (!StringUtils.isEmpty(productSearch.getKeyword())) {
			String keyword = productSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(p.name) like '%" + keyword + "%'" +
					" OR LOWER(p.short_description) like '%" + keyword + "%'" +
					" OR LOWER(p.seo) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(productSearch.getBeginDate()) && !StringUtils.isEmpty(productSearch.getEndDate())) {
			String beginDate = productSearch.getBeginDate();
			String endDate = productSearch.getEndDate();
			sql += " AND p.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}

		sql += " ORDER BY p.id DESC";
		sql += " LIMIT " + productSearch.getSizeOfPage() + " OFFSET " + (productSearch.getCurrentPage() - 1) * productSearch.getSizeOfPage();
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public List<Product> hotTrend (int num) {
		String sql = "SELECT p.* FROM tbl_product p JOIN ("
				+ "SELECT product_id, COUNT(*) AS total_wishlist FROM tbl_wishlist "
				+ "JOIN tbl_product ON tbl_wishlist.product_id = tbl_product.id WHERE tbl_product.status = 1 "
				+ "GROUP BY product_id ORDER BY total_wishlist DESC LIMIT " + num + ") "
				+ "AS top_wishlist ON p.id = top_wishlist.product_id;";
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public List<Product> bestSeller (int num) {
		String sql = "SELECT p.* FROM tbl_product p JOIN ("
				+ "SELECT product_id, SUM(quantity) AS total_sold FROM tbl_sale_order_product "
				+ "JOIN tbl_product ON tbl_sale_order_product.product_id = tbl_product.id WHERE tbl_product.status = 1 "
				+ "GROUP BY product_id ORDER BY total_sold  DESC LIMIT " + num + ") "
				+ "AS top_sold ON p.id = top_sold.product_id;";
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public List<Product> feature (int num) {
		String sql = "SELECT p.* FROM tbl_product p JOIN ("
				+ "SELECT product_id, COUNT(*) AS total_order FROM tbl_sale_order_product "
				+ "JOIN tbl_product ON tbl_sale_order_product.product_id = tbl_product.id WHERE tbl_product.status = 1 "
				+ "GROUP BY product_id ORDER BY total_order  DESC LIMIT " + num + ") "
				+ "AS top_sold ON p.id = top_sold.product_id;";
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public int countTotalItems (SearchModel productSearch) {
		String sql = "SELECT COUNT(*) FROM tbl_product p WHERE 1=1";
		
		if (productSearch.getStatus() != 2) {
			sql += " AND p.status=" + productSearch.getStatus();
		}

		if (productSearch.getCategoryId() != 0) {
			sql += " AND p.category_id=" + productSearch.getCategoryId();
		}

	    if (productSearch.getProductPrice() != 0) {
	        switch (productSearch.getProductPrice()) {
	            case 1:
	                sql += " AND p.price BETWEEN 0 AND 50";
	                break;
	            case 2:
	                sql += " AND p.price BETWEEN 50 AND 200";
	                break;
	            case 3:
	                sql += " AND p.price BETWEEN 200 AND 500";
	                break;
	            case 4:
	                sql += " AND p.price BETWEEN 500 AND 1000";
	                break;
	            case 5:
	                sql += " AND p.price > 1000";
	                break;
	            default:
	                break;
	        }
	    }

		if (!StringUtils.isEmpty(productSearch.getKeyword())) {
			String keyword = productSearch.getKeyword().toLowerCase();
			sql +=  " AND (LOWER(p.name) like '%" + keyword + "%'" +
					" OR LOWER(p.seo) like '%" + keyword + "%')";
		}
		
		if (!StringUtils.isEmpty(productSearch.getBeginDate()) && !StringUtils.isEmpty(productSearch.getEndDate())) {
			String beginDate = productSearch.getBeginDate();
			String endDate = productSearch.getEndDate();
			sql += " AND p.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		BigInteger totalItems = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
	    return totalItems.intValue();
	}
	

	@Transactional
	public List<Product> getRelatedProducts (int productId) {
		String sql = "SELECT * FROM tbl_product WHERE category_id = (SELECT category_id FROM tbl_product WHERE id = " + productId + ") AND id != " + productId + " LIMIT 4";
		return super.executeNativeSql(sql);
	}

}
