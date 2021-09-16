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
	private String perfil;
	private String sexo;
	private String fotoUser;
	private String extensaoFotoUser;
	
	public ModelLogin() {
	}
	
	public ModelLogin(Long id, String nome, String login, String senha, String email, String perfil, String sexo
						, String fotoUser, String extensaoFotoUser) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.perfil = perfil;
		this.sexo = sexo;
		this.fotoUser = fotoUser;
		this.extensaoFotoUser = extensaoFotoUser;
	}
	
	
	
	public String getFotoUser() {
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getExtensaoFotoUser() {
		return extensaoFotoUser;
	}

	public void setExtensaoFotoUser(String extensaoFotoUser) {
		this.extensaoFotoUser = extensaoFotoUser;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
