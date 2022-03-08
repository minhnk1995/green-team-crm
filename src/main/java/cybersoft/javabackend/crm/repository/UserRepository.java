package cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cybersoft.javabackend.crm.dbconnection.MySqlConnection;
import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.model.User;

public class UserRepository {
	public List<User> findAll(){
		
		return null;
	}
	public void deleteById(int id) {
		
	
	}
	public boolean addUser(UserDto userDto) throws SQLException {
		String query = "insert into users(email,password,fullname,phone,address,role_id)"
				+ "values(?,?,?,?,?,?);";
		
		Connection conn = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, userDto.getEmail());
			statement.setString(2, userDto.getPassword());
			statement.setString(3, userDto.getName());
			statement.setString(4, userDto.getPhone());
			statement.setString(5, userDto.getAddress());
			statement.setInt(6, userDto.getRoleId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
			return false;
		}finally {
			conn.close();
		}
		return true;
		
	
	}
}
