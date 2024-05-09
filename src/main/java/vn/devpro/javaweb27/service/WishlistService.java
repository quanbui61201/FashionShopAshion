package vn.devpro.javaweb27.service;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.javaweb27.model.Wishlist;

@Service
public class WishlistService {

	@PersistenceContext
	EntityManager entityManager;

	public Wishlist getById(int id) {
		return entityManager.find(Wishlist.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Wishlist> findAll() {
		return entityManager.createNativeQuery("SELECT * FROM tbl_wishlist").getResultList();
	}
	public Wishlist getByUserIdAndProductId(int userId, int productId) {
	    String sql = "SELECT * FROM tbl_wishlist WHERE user_id = :userId AND product_id = :productId";
	    try {
	        return (Wishlist) entityManager.createNativeQuery(sql, Wishlist.class)
	                .setParameter("userId", userId)
	                .setParameter("productId", productId)
	                .getSingleResult();
	    } catch (NoResultException e) {
	    	return null;
	    }
	}
	public boolean isExist(int userId, int productId) {
	    String sql = "SELECT COUNT(*) FROM tbl_wishlist WHERE user_id = :userId AND product_id = :productId";
	    BigInteger count = (BigInteger) entityManager.createNativeQuery(sql)
	            .setParameter("userId", userId)
	            .setParameter("productId", productId)
	            .getSingleResult();
	    return count.intValue() > 0;
	}

    public BigInteger getTotalWishlistByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM tbl_wishlist WHERE user_id = :userId";
        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("userId", userId);
            BigInteger totalWishlist = (BigInteger) query.getSingleResult();
            return totalWishlist;
        } catch (NoResultException e) {
            return BigInteger.ZERO;
        }
    }
    
	@Transactional
	public Wishlist saveOrUpdate(Wishlist wishlist) {
		if (wishlist.getId() == null || wishlist.getId() <= 0) {
			entityManager.persist(wishlist);
			return wishlist;
		} else {
			return entityManager.merge(wishlist);
		}
	}

	public void delete(Wishlist wishlist) {
		entityManager.remove(wishlist);
	}

	@Transactional
	public void deleteById(int id) {
		Wishlist wishlist = this.getById(id);
		entityManager.remove(wishlist);
	}
	
	public void deleteByUserIdAndProductId(int userId, int productId) {
		if (this.isExist(userId, productId)) {
			Wishlist wishlist = this.getByUserIdAndProductId(userId, productId);
			this.delete(wishlist);
		}
	}
	
}
