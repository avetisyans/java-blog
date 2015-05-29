package com.mycomp.myapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setName("admin");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		
		Blog blogJavavids = new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavavids.setUser(userAdmin);
		blogRepository.save(blogJavavids);
		
		Item item1 = new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("first item");
		item1.setLink("http://www.javavids.com");
		item1.setPublishDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("second item");
		item2.setLink("http://www.javavids.com");
		item2.setPublishDate(new Date());
		itemRepository.save(item2);
		
		
		
	}
	
	
}
