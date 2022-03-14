package cybersoft.javabackend.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.repository.AuthRepository;
import cybersoft.javabackend.crm.repository.UserRepository;

public class AuthService {
	private AuthRepository repo;
	private UserRepository userRepo;

	public AuthService() {
		repo = new AuthRepository();
		userRepo = new UserRepository();
	}

	public Optional<User> login(String email, String password) {
		List<User> lstUser = new ArrayList<>(userRepo.findAll());
		Optional<User> currentUser = lstUser.stream().filter(t -> t.getEmail().equals(email))
				.filter(t -> t.getPassword().equals(password)).findFirst();

		return currentUser;
	}
}
