package cybersoft.javabackend.crm.dto;

public class UserCreatedDto {
	private String email;
	private String password;
	private String rPassword;
	private String name;
	private String address;
	private String phone;
	private int roleId;
	
	
	
	public UserCreatedDto() {
		
	}
	
	
	public UserCreatedDto(String email, String password, String rPassword, String name, String address, String phone,
			int roleId) {

		this.email = email;
		this.password = password;
		this.rPassword = rPassword;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.roleId = roleId;
	}


	public String getrPassword() {
		return rPassword;
	}
	public void setrPassword(String rPassword) {
		this.rPassword = rPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
}
