package cybersoft.javabackend.crm.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import cybersoft.javabackend.crm.dto.UpdateUserDto;
import cybersoft.javabackend.crm.dto.UserCreatedDto;
import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.model.User;

@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {
	private static UserRepository repo;
	@BeforeAll
	public void setup() {
		 repo = new UserRepository();
	}
	@Test
	public void addUserTest() {
		UserCreatedDto user = new UserCreatedDto("admin@gamil.com","admin2","admin2","admin2","0002","HCM",2);
		
		try {
			assertNotEquals(repo.addUser(user), false);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteByIdTest() throws SQLException{
		
		try {
			boolean result = repo.deleteById(8);
			assertNotEquals(result, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void updateUserTest() {
		UpdateUserDto userDto = new UpdateUserDto(1, "admin 1", "1234","1234", "nhandeptrai@gmail.com","0923", "hcm",1);
		try {
			assertNotEquals(repo.updateUser(userDto), false);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
}
