package vn.devpro.javaweb27.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.model.UserRole;

@Service
public class UserService extends BaseService<User> implements Jw27Constant {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	public Class<User> clazz() {
		return User.class;
	}

	public List<User> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_user WHERE status=1");
	}

	public boolean isUploadFile(MultipartFile file) {
		if (file == null || file.getOriginalFilename().isEmpty()) {
			return false;
		}
		return true;
	}

	@Transactional
	public User saveAddUser(User user, MultipartFile avatarFile, Integer roleId) throws IOException {
		
		user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
	    User savedUser = super.saveOrUpdate(user);

	    if (roleId != null) {
	        UserRole userRole = new UserRole();
	        userRole.setUserId(savedUser.getId());
	        userRole.setRoleId(roleId);

	        userRoleService.saveOrUpdate(userRole);
	    }

		if (isUploadFile(avatarFile) ) {
			String path = FOLDER_UPLOAD + "User/" + avatarFile.getOriginalFilename();
			File file  = new File(path);
			avatarFile.transferTo(file);
			savedUser.setAvatar("User/" + avatarFile.getOriginalFilename());
		}
		
		return super.saveOrUpdate(savedUser);
	}

	@Transactional
	public User saveEditUser(User user, MultipartFile avatarFile) throws IOException {
		
		User dbUser = super.getById(user.getId());
		
		if (isUploadFile(avatarFile) ) {
			String path = FOLDER_UPLOAD + "User/" + dbUser.getAvatar();
			File file  = new File(path);
			file.delete();
			
			path = FOLDER_UPLOAD + "User/" + avatarFile.getOriginalFilename();
			file  = new File(path);
			avatarFile.transferTo(file);
			
			user.setAvatar("User/" + avatarFile.getOriginalFilename());
		} else {
	        user.setAvatar(dbUser.getAvatar());
		}
		
		return super.saveOrUpdate(user);
	}

	@Transactional
	public void deleteUser(User user) {
		String path = FOLDER_UPLOAD + user.getAvatar();
		File file = new File(path);
		file.delete();
		
		super.delete(user);
	}

	@Transactional
	public List<User> searchUser (SearchModel userSearch) {

	    String sql = "SELECT DISTINCT u.* FROM tbl_user u " +
	                 "INNER JOIN tbl_user_role ur ON u.id=ur.user_id WHERE 1=1";

	    if (userSearch.getStatus() != 2) {
	        sql += " AND u.status=" + userSearch.getStatus();
	    }

	    if (userSearch.getRoleId() != 0) {
	        sql += " AND ur.role_id=" + userSearch.getRoleId();
	    }

	    if (!StringUtils.isEmpty(userSearch.getKeyword())) {
	        String keyword = userSearch.getKeyword().toLowerCase();
	        sql += " AND (LOWER(u.username) like '%" + keyword + "%'" +
	               " OR LOWER(u.name) like '%" + keyword + "%'" +
	               " OR LOWER(u.email) like '%" + keyword + "%'" +
	               " OR LOWER(u.description) like '%" + keyword + "%')";
	    }

		if (!StringUtils.isEmpty(userSearch.getBeginDate()) && !StringUtils.isEmpty(userSearch.getEndDate())) {
			String beginDate = userSearch.getBeginDate();
			String endDate = userSearch.getEndDate();
			sql += " AND u.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		sql += " LIMIT " + userSearch.getSizeOfPage() + " OFFSET " + (userSearch.getCurrentPage() - 1) * userSearch.getSizeOfPage();
		
		return super.executeNativeSql(sql);
	}

	@Transactional
	public int countTotalItems (SearchModel userSearch) {

	    String sql = "SELECT COUNT(DISTINCT u.id) FROM tbl_user u " +
	                 "INNER JOIN tbl_user_role ur ON u.id=ur.user_id WHERE 1=1";

	    if (userSearch.getStatus() != 2) {
	        sql += " AND u.status=" + userSearch.getStatus();
	    }

	    if (userSearch.getRoleId() != 0) {
	        sql += " AND ur.role_id=" + userSearch.getRoleId();
	    }

	    if (!StringUtils.isEmpty(userSearch.getKeyword())) {
	        String keyword = userSearch.getKeyword().toLowerCase();
	        sql += " AND (LOWER(u.username) like '%" + keyword + "%'" +
	               " OR LOWER(u.name) like '%" + keyword + "%'" +
	               " OR LOWER(u.email) like '%" + keyword + "%'" +
	               " OR LOWER(u.description) like '%" + keyword + "%')";
	    }

		if (!StringUtils.isEmpty(userSearch.getBeginDate()) && !StringUtils.isEmpty(userSearch.getEndDate())) {
			String beginDate = userSearch.getBeginDate();
			String endDate = userSearch.getEndDate();
			sql += " AND u.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		BigInteger totalItems = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
	    return totalItems.intValue();
	}
	
}
