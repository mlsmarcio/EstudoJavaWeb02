package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private boolean userAdmin;

	public ModelLogin() {
	}
	
	public ModelLogin(Long id, String nome, String login, String senha, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}
	
	
	
	public boolean isUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isNovo() {
		return this.id == null || this.id == 0;
	}

}
