package cybersoft.javabackend.crm.dbconnection;

import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.repository.UserRepository;
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {
	private static UserRepository repo;
	@BeforeAll
	public void setup() {
		 repo = new UserRepository();
	}
	@Test
	public void addUserTest() {
		UserDto user = new UserDto("admin@gamil.com","admin2","admin2","0002","HCM",2);
		
		try {
			assertNotEquals(repo.addUser(user), false);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
