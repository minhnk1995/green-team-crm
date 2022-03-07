package cybersoft.javabackend.crm.dbconnection;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

public class MySqlConnectionTest {
	
	@Test
	public void getConnection() {
		assertNotEquals(MySqlConnection.getConnection(), null);
	}
	
}
