package br.com.ecosystem.domain.model;

import javax.validation.constraints.NotEmpty;

import br.com.ecosystem.domain.entity.UserEntity;

public class UserDto {
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String login;
	@NotEmpty
	private String password;

	public UserDto() {
		super();
	}

	public UserDto(Long id, String name, String email, String login, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	public UserDto(UserEntity e) {
		this.id = e.getId();
		this.name = e.getName();
		this.email = e.getEmail();
		this.login = e.getLogin();
		this.password = e.getPassword();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
