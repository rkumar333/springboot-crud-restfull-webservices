package net.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.spring.demo.entity.User;
import net.spring.demo.exception.ResourcesNotFoundException;
import net.spring.demo.repo.UserRepo;



@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	//get all users
	@GetMapping
	public List<User> getallUsers(){
		return this.userRepo.findAll();		
	}
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {
		return this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("user not found with id:"+userId));
		
	}
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepo.save(user);
		
	}
	
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable("id") long userId) {
		User existing= this.userRepo.findById(userId).orElseThrow(()->new ResourcesNotFoundException("user not found with Id:"+userId));
		existing.setFirstname(user.getFirstname());
		existing.setLastname(user.getLastname());
		existing.setEmail(user.getEmail());
		return this.userRepo.save(existing);
	}
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		User existing= this.userRepo.findById(userId).orElseThrow(()->new ResourcesNotFoundException("user not found with Id:"+userId));
		this.userRepo.delete(existing);
		return ResponseEntity.ok().build();

		
	}

}
