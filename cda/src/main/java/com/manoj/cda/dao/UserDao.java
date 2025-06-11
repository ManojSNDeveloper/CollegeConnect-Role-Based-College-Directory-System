package com.manoj.cda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoj.cda.entity.User;
import com.manoj.cda.repository.UserRepository;

@Repository
public class UserDao 
{
	@Autowired
	private UserRepository repo;
	
	public User saveUser(User user) 
	{
		return repo.save(user);
	}

	public List<User> viewAll() 
	{
		return repo.findAll();
	}

	public Optional<User> viewById(int id) 
	{
		return repo.findById(id);
	}

	public void save(User user) 
	{
		repo.save(user);
	}
}
