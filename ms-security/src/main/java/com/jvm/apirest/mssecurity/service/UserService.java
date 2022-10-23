package com.jvm.apirest.mssecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jvm.apirest.mssecurity.model.UserModel;
import com.jvm.apirest.mssecurity.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

}