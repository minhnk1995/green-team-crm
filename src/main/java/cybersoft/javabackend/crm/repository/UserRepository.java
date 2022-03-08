package cybersoft.javabackend.crm.repository;

import java.util.List;

import cybersoft.javabackend.crm.model.User;

public interface UserRepository {

	List<User> getAllUsers();
}
