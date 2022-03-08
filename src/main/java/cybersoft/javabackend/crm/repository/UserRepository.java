package cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.javabackend.crm.dbconnection.MySqlConnection;
import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.model.Role;
import cybersoft.javabackend.crm.model.User;

public class UserRepository {
	
	public List<User> findAll(){
		List<User> lstUsers = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "SELECT * FROM users";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setName(res.getString("fullname"));
				user.setAddress(res.getString("address"));
				user.setRole(getUserRole(res.getInt("role_id")));
				lstUsers.add(user);
			}
			conn.close();
			return lstUsers;
		} catch (Exception e) {
			System.out.println("lỗi ở trên");
			e.printStackTrace();
		}
		return null;
	}
	
	private Role getUserRole(int role_id) {
		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "SELECT * FROM roles WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, role_id);
			ResultSet res = statement.executeQuery();
			Role role = new Role();
			while (res.next()) {
				role.setId(res.getInt("id"));
				role.setName(res.getString("name"));
				role.setDescription(res.getString("description"));
			}
			return role;
		} catch (Exception e) {
			System.out.println("lỗi ở dưới");
			e.printStackTrace();
		}

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
