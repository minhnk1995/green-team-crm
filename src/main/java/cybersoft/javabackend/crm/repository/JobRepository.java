package cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cybersoft.javabackend.crm.dbconnection.MySqlConnection;
import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Role;
import cybersoft.javabackend.crm.model.Status;
import cybersoft.javabackend.crm.model.Task;
import cybersoft.javabackend.crm.model.User;

public class JobRepository {

	public List<Job> getAllJobByUserID(int userID) {
		List<Job> lstJob = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct j.*, u.*, r.* from jobs j "
					+ "join tasks t on j.id = t.job_id "
					+ "join users u on j.manager_id = u.id "
					+ "join roles r on u.role_id = r.id "
					+ "where t.user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, userID);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Job job = new Job();
				User manager = new User();
				Role role = new Role();

				job.setId(res.getInt("id"));
				job.setName(res.getString("name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("end_date").getTime()).toLocalDateTime());

				manager.setId(res.getInt("manager_id"));
				manager.setName(res.getString("fullname"));
				manager.setEmail(res.getString("email"));
				manager.setPassword(res.getString("password"));
				manager.setPhone(res.getString("phone"));
				manager.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				manager.setRole(role);
				job.setManagerID(manager);
				
				lstJob.add(job);
			}
			conn.close();
			return lstJob;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Job> getAllJobsByManagerID(int userID) {
		List<Job> lstJob = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct j.*, u.*, r.* from jobs j "
					+ "join users u on j.manager_id = u.id "
					+ "join roles r on u.role_id = r.id "
					+ "where j.manager_id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, userID);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Job job = new Job();
				User manager = new User();
				Role role = new Role();

				job.setId(res.getInt("id"));
				job.setName(res.getString("name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("end_date").getTime()).toLocalDateTime());

				manager.setId(res.getInt("manager_id"));
				manager.setName(res.getString("fullname"));
				manager.setEmail(res.getString("email"));
				manager.setPassword(res.getString("password"));
				manager.setPhone(res.getString("phone"));
				manager.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				manager.setRole(role);
				job.setManagerID(manager);
				
				lstJob.add(job);
			}
			conn.close();
			return lstJob;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object checkNameExist(String name) {
		Job job = new Job();
		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select * from jobs where name = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				job.setId(res.getInt("id"));
			}
			conn.close();
			return job.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addJob(String name, String description, String start, String end, int managerID) {
			String query = "insert into jobs(name ,description ,start_date ,end_date ,manager_id)"
					+ "values(?,?,?,?,?);";
			
			Connection conn = MySqlConnection.getConnection();
			
			try {
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, name);
				statement.setString(2, description);
				statement.setString(3, start);
				statement.setString(4, end);
				statement.setInt(5, managerID);
				
				statement.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;			
	}

	public Job getJobByID(int jobID) {
		try {
			Job job = null;
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct j.*, u.*, r.* from jobs j join users u on u.id = j.manager_id"
					+ " join roles r on r.id = u.role_id"
					+ " where j.id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, jobID);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				job = new Job();
				User manager = new User();
				Role role = new Role();

				job.setId(res.getInt("j.id"));
				job.setName(res.getString("name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("end_date").getTime()).toLocalDateTime());

				manager.setId(res.getInt("manager_id"));
				manager.setName(res.getString("fullname"));
				manager.setEmail(res.getString("email"));
				manager.setPassword(res.getString("password"));
				manager.setPhone(res.getString("phone"));
				manager.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				manager.setRole(role);
				job.setManagerID(manager);
			}
			conn.close();
			return job;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Task> getAllTasksByJobID(int id) {
		List<Task> lstTask = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct t.*, u.*, j.*, s.*, r.* from tasks t join jobs j on t.job_id = j.id"
					+ " join users u on t.user_id = u.id"
					+ " join roles r on u.role_id = r.id"
					+ " join status s on t.status_id = s.id"
					+ " where t.job_id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Job job = new Job();
				User user = new User();
				Role role = new Role();
				Task task = new Task();
				Status status = new Status();
				
				status.setId(res.getInt("s.id"));
				status.setName(res.getString("s.name"));
				
				task.setId(res.getInt("t.id"));
				task.setName(res.getString("t.name"));
				task.setStart_date(new Timestamp(res.getDate("t.start_date").getTime()).toLocalDateTime());
				task.setEnd_date(new Timestamp(res.getDate("t.end_date").getTime()).toLocalDateTime());
				task.setStatus(status);

				job.setId(res.getInt("j.id"));
				job.setName(res.getString("j.name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("j.start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("j.end_date").getTime()).toLocalDateTime());

				user.setId(res.getInt("u.id"));
				user.setName(res.getString("fullname"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				user.setRole(role);
				task.setJob(job);
				task.setUser(user);
				
				lstTask.add(task);
			}
			conn.close();
			return lstTask;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Task> getTasksByUserIdAndJobId(int userId, int JobId) {
		List<Task> lstTask = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct t.*, u.*, j.*, s.*, r.* from tasks t join jobs j on t.job_id = j.id"
					+ " join users u on t.user_id = u.id"
					+ " join roles r on u.role_id = r.id"
					+ " join status s on t.status_id = s.id"
					+ " where t.job_id = ? and t.user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, JobId);
			statement.setInt(2, userId);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Job job = new Job();
				User user = new User();
				Role role = new Role();
				Task task = new Task();
				Status status = new Status();
				
				status.setId(res.getInt("s.id"));
				status.setName(res.getString("s.name"));
				
				task.setId(res.getInt("t.id"));
				task.setName(res.getString("t.name"));
				task.setStart_date(new Timestamp(res.getDate("t.start_date").getTime()).toLocalDateTime());
				task.setEnd_date(new Timestamp(res.getDate("t.end_date").getTime()).toLocalDateTime());
				task.setStatus(status);

				job.setId(res.getInt("j.id"));
				job.setName(res.getString("j.name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("j.start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("j.end_date").getTime()).toLocalDateTime());

				user.setId(res.getInt("u.id"));
				user.setName(res.getString("fullname"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				user.setRole(role);
				task.setJob(job);
				task.setUser(user);
				
				lstTask.add(task);
			}
			conn.close();
			return lstTask;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteProjectById(int jobID) {
		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "DELETE FROM jobs WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, jobID);
			statement.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteTasksByProjectId(int jobID) {
		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "DELETE FROM tasks WHERE job_id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, jobID);
			statement.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editJobById(int jobId, String name, String description, String start, String end, int managerID) {
		String query = "update jobs set name=?, description=?, start_date=?, end_date=?, manager_id=? where id=?; ";
		
		Connection conn = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, description);
			statement.setString(3, start);
			statement.setString(4, end);
			statement.setInt(5, managerID);
			statement.setInt(6, jobId);
			
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}

	public List<Job> getAllJob() {
		List<Job> lstJob = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct j.*, u.*, r.* from jobs j "
					+ "join users u on j.manager_id = u.id "
					+ "join roles r on u.role_id = r.id ";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Job job = new Job();
				User manager = new User();
				Role role = new Role();

				job.setId(res.getInt("id"));
				job.setName(res.getString("name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("end_date").getTime()).toLocalDateTime());

				manager.setId(res.getInt("manager_id"));
				manager.setName(res.getString("fullname"));
				manager.setEmail(res.getString("email"));
				manager.setPassword(res.getString("password"));
				manager.setPhone(res.getString("phone"));
				manager.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				manager.setRole(role);
				job.setManagerID(manager);
				
				lstJob.add(job);
			}
			conn.close();
			return lstJob;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Task> getAllTasks() {
		List<Task> lstTask = new ArrayList<>();

		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct t.*, u.*, j.*, s.*, r.* from tasks t join jobs j on t.job_id = j.id"
					+ " join users u on t.user_id = u.id"
					+ " join roles r on u.role_id = r.id"
					+ " join status s on t.status_id = s.id";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Job job = new Job();
				User user = new User();
				Role role = new Role();
				Task task = new Task();
				Status status = new Status();
				
				status.setId(res.getInt("s.id"));
				status.setName(res.getString("s.name"));
				
				task.setId(res.getInt("t.id"));
				task.setName(res.getString("t.name"));
				task.setStart_date(new Timestamp(res.getDate("t.start_date").getTime()).toLocalDateTime());
				task.setEnd_date(new Timestamp(res.getDate("t.end_date").getTime()).toLocalDateTime());
				task.setStatus(status);

				job.setId(res.getInt("j.id"));
				job.setName(res.getString("j.name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("j.start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("j.end_date").getTime()).toLocalDateTime());

				user.setId(res.getInt("u.id"));
				user.setName(res.getString("fullname"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				user.setRole(role);
				task.setJob(job);
				task.setUser(user);
				
				lstTask.add(task);
			}
			conn.close();
			return lstTask;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateTaskStatusToOne(int taskID) {
		String query = "update tasks set status_id=1 where id=?; ";
		
		Connection conn = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, taskID);
			
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTaskStatusToTwo(int taskID) {
		String query = "update tasks set status_id=2 where id=?; ";
		
		Connection conn = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, taskID);
			
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTaskStatusToThree(int taskID) {
		String query = "update tasks set status_id=3 where id=?; ";
		
		Connection conn = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, taskID);
			
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Task getTaskById(int taskId) {
		Task task = new Task();
		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select distinct t.*, s.*, j.*, u.*, r.* from tasks t join status s on t.status_id=s.id "
					+ "join jobs j on t.job_id=j.id "
					+ "join users u on j.manager_id=u.id "
					+ "join roles r on u.role_id=r.id "
					+ "where t.id=?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, taskId);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Job job = new Job();
				User user = new User();
				Role role = new Role();
				Status status = new Status();
				
				status.setId(res.getInt("s.id"));
				status.setName(res.getString("s.name"));
				
				task.setId(res.getInt("t.id"));
				task.setName(res.getString("t.name"));
				task.setStart_date(new Timestamp(res.getDate("t.start_date").getTime()).toLocalDateTime());
				task.setEnd_date(new Timestamp(res.getDate("t.end_date").getTime()).toLocalDateTime());
				task.setStatus(status);

				job.setId(res.getInt("j.id"));
				job.setName(res.getString("j.name"));
				job.setDescription(res.getString("description"));
				job.setStart_date(new Timestamp(res.getDate("j.start_date").getTime()).toLocalDateTime());
				job.setEnd_date(new Timestamp(res.getDate("j.end_date").getTime()).toLocalDateTime());

				user.setId(res.getInt("u.id"));
				user.setName(res.getString("fullname"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("address"));
				
				role.setId(res.getInt("r.id"));
				role.setName(res.getString("r.name"));
				role.setDescription(res.getString("r.description"));
				
				user.setRole(role);
				task.setJob(job);
				task.setUser(user);
				
			}
			conn.close();
			return task;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addTask(String name, String start, String end, int userId, int jobID) {
		String query = "insert into tasks(name, start_date, end_date, user_id, job_id, status_id)"
				+ "values(?,?,?,?,?,1);";
		
		Connection conn = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, start);
			statement.setString(3, end);
			statement.setInt(4, userId);
			statement.setInt(5, jobID);
			
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editTaskById(int taskId, String name, String start, String end, int userId) {
		String query = "update tasks set name=?, start_date=?, end_date=?, user_id=? where id=?; ";
		
		Connection conn = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, start);
			statement.setString(3, end);
			statement.setInt(4, userId);
			statement.setInt(5, taskId);
			
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteTaskById(int taskId) {
		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "DELETE FROM tasks WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, taskId);
			statement.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int getJobIdByTask(int taskId) {
		int jobID=-1;
		try {
			Connection conn = MySqlConnection.getConnection();
			String query = "select job_id from tasks where id = ?;";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, taskId);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				jobID=res.getInt("job_id");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobID;
	}
}
