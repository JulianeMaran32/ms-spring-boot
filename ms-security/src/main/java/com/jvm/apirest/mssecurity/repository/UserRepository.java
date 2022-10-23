package com.jvm.apirest.mssecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvm.apirest.mssecurity.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
