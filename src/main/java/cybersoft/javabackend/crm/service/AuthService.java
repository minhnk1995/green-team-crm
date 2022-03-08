package cybersoft.javabackend.crm.service;

import cybersoft.javabackend.crm.repository.AuthRepository;

public class AuthService {
private AuthRepository repo;
	
	public AuthService() {
		repo = new AuthRepository();
	}

	public boolean login(String email, String password) {
		
		return false;
	}
}
