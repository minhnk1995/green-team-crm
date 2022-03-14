package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.List;

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

	public void add(UserDto dto) {
		try {
			userRepo.addUser(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
