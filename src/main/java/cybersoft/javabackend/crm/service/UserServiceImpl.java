package cybersoft.javabackend.crm.service;

import java.util.List;

import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.repository.UserRepository;
import cybersoft.javabackend.crm.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {

	UserRepository repository;
	
	public UserServiceImpl() {
		repository = new UserRepositoryImpl();
	}

	@Override
	public List<User> getAllUsers() {
		
		return repository.getAllUsers();
	}

}
