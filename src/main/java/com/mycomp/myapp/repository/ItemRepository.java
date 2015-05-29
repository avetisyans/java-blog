package com.mycomp.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.myapp.entity.Blog;
import com.mycomp.myapp.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByBlog(Blog blog, Pageable pageable);

}
