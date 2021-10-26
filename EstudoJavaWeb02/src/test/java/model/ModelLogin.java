package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	private Date dataNascimento;
	private Double rendamensal;
	
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String ibge;
	
	private List<ModelTelefone> telefones = new ArrayList<>();
	
	public ModelLogin() {
	}
	
	public ModelLogin(Long id, String nome, String login, String senha, String email, boolean userAdmin, String perfil,
			String sexo, String fotoUser, String extensaoFotoUser, String cep, String logradouro, String numero,
			String complemento, String bairro, String cidade, String uf, String ibge, Date dataNascimento, Double rendamensal) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.userAdmin = userAdmin;
		this.perfil = perfil;
		this.sexo = sexo;
		this.fotoUser = fotoUser;
		this.extensaoFotoUser = extensaoFotoUser;
		this.dataNascimento = dataNascimento;
		this.rendamensal = rendamensal;
		
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.ibge = ibge;
	}
	
	public List<ModelTelefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<ModelTelefone> telefones) {
		this.telefones = telefones;
	}
	
	public void setRendamensal(Double rendamensal) {
		this.rendamensal = rendamensal;
	}
	public Double getRendamensal() {
		return rendamensal;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
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
