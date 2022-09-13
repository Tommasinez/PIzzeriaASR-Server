package it.advancia.pizzeria.payload.request;

import java.util.List;

import javax.validation.constraints.*;

public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
;

	private List<String> role;

	@NotBlank
	@Size(min = 1, max = 40)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRole() {
		return this.role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
}