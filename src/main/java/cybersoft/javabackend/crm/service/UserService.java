package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.Collection;
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
			if(userRepo.deleteById(id)) {
				updateJobAfterDeleteUser(id);
				updateTaskAfterDeleteUser(id);
			}
			return userRepo.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void updateJobAfterDeleteUser(int id) {
		try {
			userRepo.updateJobAfterDeleteUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateTaskAfterDeleteUser(int id) {
		try {
			userRepo.updateTaskAfterDeleteUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public boolean checkUserExistByEmail(String email) {
		try {
			return userRepo.checkEmailExists(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
