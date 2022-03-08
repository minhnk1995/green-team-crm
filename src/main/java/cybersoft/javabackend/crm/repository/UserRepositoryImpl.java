package cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cybersoft.javabackend.crm.dbconnection.MySqlConnection;
import cybersoft.javabackend.crm.model.User;

public class UserRepositoryImpl implements UserRepository {

	@Override
	public List<User> getAllUsers() {
		List<User> lstUsers = new ArrayList<User>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "SELECT * FROM users";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setFullname(res.getString("fullname"));
				user.setAvatar(res.getString("avatar"));
				user.setRole_id(res.getInt("role_id"));
				lstUsers.add(user);
			}
			conn.close();
			return lstUsers;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
