package cybersoft.javabackend.crm.dto;

public class UpdateUserDto {
	private int id;
	private String name;
	private String password;
	private String rPassword;
	private String email;
	private String phone;
	private String address;
	private int roleId;
	
	
	

	public UpdateUserDto() {
	}

	public UpdateUserDto(int id, String name, String password, String rPassword, String email, String phone,
			String address, int roleId) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.rPassword = rPassword;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
	}
	public String getrPassword() {
		return rPassword;
	}
	public void setrPassword(String rPassword) {
		this.rPassword = rPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int role) {
		this.roleId = role;
	}
	
	
}
