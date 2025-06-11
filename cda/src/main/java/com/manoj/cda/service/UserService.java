package com.manoj.cda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manoj.cda.dao.UserDao;
import com.manoj.cda.entity.User;
import com.manoj.cda.response_structure.ResponseStructure;

@Service
public class UserService 
{
	
	@Autowired
	private UserDao dao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) 
	{  
		User save = dao.saveUser(user);
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("User Saved Successfully").body(save).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public ResponseEntity<ResponseStructure<List<User>>> viewAll() 
	{
		List<User> all = dao.viewAll();
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("All User Found Successfully").body(all).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}
 
	public ResponseEntity<ResponseStructure<Optional<User>>> viewById(int id) 
	{
		Optional<User> byId = dao.viewById(id);
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("USer Found Successfully By Id").body(byId).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK.value()).body(rs);
		return re;
	}

	public String changePassword(int userId, String oldPassword, String newPassword) 
	{
	    Optional<User> optionalUser = dao.viewById(userId);
	    if (optionalUser.isEmpty()) {
	        throw new RuntimeException("User not found with ID: " + userId);
	    }

	    User user = optionalUser.get();

	    if (!user.getPassword().equals(oldPassword)) {
	        return "Old password is incorrect.";
	    }

	    user.setPassword(newPassword);
	    dao.save(user);
	    return "Password updated successfully.";
	}
}

