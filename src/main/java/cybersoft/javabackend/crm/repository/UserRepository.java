package cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.javabackend.crm.dbconnection.MySqlConnection;
import cybersoft.javabackend.crm.dto.UpdateUserDto;
import cybersoft.javabackend.crm.dto.UserCreatedDto;
import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.model.Role;
import cybersoft.javabackend.crm.model.User;

public class UserRepository {
	public List<User> getAllUser() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();

		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.fullname as fullname, u.email as email, "
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM users u, roles r WHERE u.role_id = r.id";
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				int roleId = resultSet.getInt("role_id");
				Role role = getRoleFromUsers(roles, roleId);

				if (role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));

					roles.add(role);
				}

				user.setRole(role);

				users.add(user);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return users;
	}
	
	public List<UpdateUserDto> getUserDtoByRole(int roleID) throws SQLException {
		List<UpdateUserDto> lstUser = new ArrayList<>();
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT * from users where role_id = ?";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, roleID);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				UpdateUserDto user = new UpdateUserDto();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setAddress(resultSet.getString("address"));
				user.setRoleId(resultSet.getInt("role_id"));
				
				lstUser.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return lstUser;
	}

	private Role getRoleFromUsers(List<Role> roles, int roleId) {
		for (Role role : roles)
			if (role.getId() == roleId)
				return role;
		return null;
	}

	public boolean deleteById(int id) throws SQLException {
		String query = "delete from users where id = ?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			int resultSet = statement.executeUpdate();
			if (resultSet > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}

	public List<User> findAll() {
		List<User> lstUsers = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select * from users u join roles r on u.role_id = r.id;";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Role role = new Role();
				User user = new User();
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("name"));
				role.setDescription(res.getString("description"));

				user.setId(res.getInt("u.id"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setName(res.getString("fullname"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("address"));
				user.setRole(role);
				lstUsers.add(user);
			}
			conn.close();
			return lstUsers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(UpdateUserDto userDto) throws SQLException {
		String query = "update users set email = ?,password=?,fullname=?,role_id=? ,phone=?,address=?" + "where id = ?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userDto.getEmail());
			statement.setString(2, userDto.getPassword());
			statement.setString(3, userDto.getName());
			statement.setInt(4, userDto.getRoleId());
			statement.setString(5, userDto.getPhone());
			statement.setString(6, userDto.getAddress());
			statement.setInt(7, userDto.getId());
			int resultSet = statement.executeUpdate();
			if (resultSet > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to database.");
		} finally {
			connection.close();
		}

		return false;
	}

	public boolean addUser(UserCreatedDto userDto) throws SQLException {
		String query = "insert into users(email,password,fullname,phone,address,role_id)" + "values(?,?,?,?,?,?);";

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
		} finally {
			conn.close();
		}
		return true;	
	}

	public List<User> getListUserByRole(int roleID) {
		List<User> lstUser = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select * from users u join roles r on u.role_id = r.id where role_id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, roleID);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Role role = new Role();
				User user = new User();
				
				role.setId(res.getInt("id"));
				role.setName(res.getString("name"));
				role.setDescription(res.getString("description"));
				
				user.setId(res.getInt("u.id"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setName(res.getString("fullname"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("address"));
				user.setRole(role);
				lstUser.add(user);
			}
			conn.close();
			return lstUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getListUserByJob(int jobID) {
		List<User> lstUser = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct u.*, r.* from users u join tasks t on t.user_id = u.id"
					+ " join jobs j on j.id = t.job_id"
					+ " join roles r on r.id = u.role_id"
					+ " where j.id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, jobID);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Role role = new Role();
				User user = new User();
				
				role.setId(res.getInt("id"));
				role.setName(res.getString("name"));
				role.setDescription(res.getString("description"));
				
				user.setId(res.getInt("u.id"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setName(res.getString("fullname"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("address"));
				user.setRole(role);
				lstUser.add(user);
			}
			conn.close();
			return lstUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserById(int id) throws SQLException {
		User user = null;
		String query = "SELECT u.fullname as fullname, u.email as email, u.password as password,"
				+ "u.phone as phone,u.address as address, r.id as role_id, r.name as role_name, r.description as role_description"
				+ " FROM users u, roles r WHERE u.id = ? and u.role_id = r.id";

		Connection conn = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user = new User();
				
				user.setAddress(result.getString("address"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setName(result.getString("fullname"));
				user.setPhone(result.getString("phone"));

				Role role = new Role();
				role.setId(result.getInt("role_id"));
				role.setName(result.getString("role_name"));
				role.setDescription(result.getString("role_description"));
				user.setRole(role);
			}
			

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
			return user;
		} finally {
			conn.close();
		}
		return user;
	}

	public boolean checkUserNameExists(String name) throws SQLException {
		String query = "select * from users u where u.fullname = ? limit 1";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			connection.close();
		}
	}

	public boolean checkEmailExists(String email) throws SQLException {
		String query = "select * from users u where u.email = ? limit 1";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			connection.close();
		}
	}

	public void updateJobAfterDeleteUser(int id) throws SQLException {
		String query = "update jobs set manager_id = 0 where manager_id = ?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to database.");
		} finally {
			connection.close();
		}
	}
	
	public void updateTaskAfterDeleteUser(int id) throws SQLException {
		String query = "update tasks set user_id = 0 where user_id = ?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to database.");
		} finally {
			connection.close();
		}
	}
}
