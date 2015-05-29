package com.mycomp.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.myapp.entity.Blog;
import com.mycomp.myapp.entity.Role;
import com.mycomp.myapp.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	List<Blog> findByUser(User user);
}
