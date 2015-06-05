package com.mycomp.myapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycomp.myapp.entity.Blog;
import com.mycomp.myapp.entity.Item;
import com.mycomp.myapp.entity.Role;
import com.mycomp.myapp.entity.User;
import com.mycomp.myapp.repository.BlogRepository;
import com.mycomp.myapp.repository.ItemRepository;
import com.mycomp.myapp.repository.RoleRepository;
import com.mycomp.myapp.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
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


	public User findOneWithBlogs(Long long1) {
		
		User user = findOne(long1);
		List<Blog> blogs = blogRepository.findByUser(user);
		
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishDate"));
			blog.setItems(items);
		}
		
		user.setBlogs(blogs);
		
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		
		userRepository.save(user);
	}

	public User findOneWithBlogs(String name) {
		
		User user = userRepository.findByName(name);
		
		return findOneWithBlogs(user.getId());
	}

	public void delete(long id) {
		userRepository.delete(id);
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
	}
}
