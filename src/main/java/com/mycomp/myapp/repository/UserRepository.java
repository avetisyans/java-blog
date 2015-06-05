package com.mycomp.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.myapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

}
