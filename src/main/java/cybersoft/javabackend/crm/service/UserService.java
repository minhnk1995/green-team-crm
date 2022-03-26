package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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

	public void add(UserDto dto) {
		try {
			userRepo.addUser(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getListUserByRole(int roleID) {
		return userRepo.getListUserByRole(roleID);
	}

	public int countUserByJob(int id) {
		return userRepo.getListUserByJob(id).size();
	}

	public List<UpdateUserDto> getUserDtoByRole(int roleUser) {
		try {
			return userRepo.getUserDtoByRole(roleUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
