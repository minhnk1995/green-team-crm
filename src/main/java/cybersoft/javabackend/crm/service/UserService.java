package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import cybersoft.javabackend.crm.dto.UpdateUserDto;
import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.repository.UserRepository;

public class UserService {
	private UserRepository userRepo;
	
	public UserService() {
		userRepo = new UserRepository();
	}

	public List<User> getAllUser() {
		List<User> users = null;
		try {
			users = userRepo.getAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	public List<User> findAll() {
		
		return userRepo.findAll();
	}

	public void deleteById(int id) {
		try {
			userRepo.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean add(UserDto dto) {
		try {
			return userRepo.addUser(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public User getUserById(int id){
		try {
			return userRepo.getUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserDto getUserByName(String name) {
		UserDto user = null;
		try {
			user = userRepo.getUserByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return user;
	}

	public boolean updateUser(UpdateUserDto user) {
		
		try {
			return userRepo.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
