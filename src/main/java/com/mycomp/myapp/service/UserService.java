package com.mycomp.myapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mycomp.myapp.entity.Blog;
import com.mycomp.myapp.entity.Item;
import com.mycomp.myapp.entity.User;
import com.mycomp.myapp.repository.BlogRepository;
import com.mycomp.myapp.repository.ItemRepository;
import com.mycomp.myapp.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(long id) {
		return userRepository.findOne(id);
	}


	public User findOneWithBlogs(int id) {
		
		User user = findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishDate"));
			blog.setItems(items);
		}
		
		user.setBlogs(blogs);
		
		return user;
	}

	public void save(User user) {
		userRepository.save(user);
	}
}
