package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import cybersoft.javabackend.crm.dto.UpdateUserDto;
import cybersoft.javabackend.crm.dto.UserCreatedDto;
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

	public boolean deleteById(int id) {
		try {
			return userRepo.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean add(UserCreatedDto dto) {
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

	public boolean checkUserExistByName(String name) {
		try {
			return userRepo.checkUserNameExists(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
