package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DaoUsuarioRepository {

private Connection connection;
	
	public DaoUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin usuario) throws SQLException {
		String sql = "";
		PreparedStatement preparedStatement = null;
		
		if (usuario.isNovo()) {
			sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getEmail());
		
		}else {
			sql = "update model_login set nome = ?, email = ?, login = ?, senha = ? where id = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getLogin());
			preparedStatement.setString(4, usuario.getSenha());
			preparedStatement.setLong(5, usuario.getId());
		}
		preparedStatement.execute();
		connection.commit();
		
		return this.consultaUsuario(usuario.getLogin());
	}
	
	public ModelLogin consultaUsuario(String login) throws SQLException {
		ModelLogin usuario = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper(?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login);
		ResultSet resultSet= preparedStatement.executeQuery();
		if (resultSet.next()) {
			usuario.setId(resultSet.getLong("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
		}
		return usuario;
	}

	public ModelLogin consultaUsuarioId(Long id) throws Exception {
		ModelLogin usuario = new ModelLogin();
		String sql = "select * from model_login where id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		ResultSet resultSet= preparedStatement.executeQuery();
		if (resultSet.next()) {
			usuario.setId(resultSet.getLong("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
		}
		return usuario;
	}
	
	public List<ModelLogin> buscarUsuarioList(String nome) throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login where upper(nome) like upper(?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + nome + "%");
		ResultSet resultSet= preparedStatement.executeQuery();
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"));
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	public List<ModelLogin> buscarUsuarioList() throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet= preparedStatement.executeQuery();
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"));
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public boolean existeLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as contem from model_login where upper(login) = upper(?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login);
		ResultSet resultSet= preparedStatement.executeQuery();
		resultSet.next(); 
		return resultSet.getBoolean("contem");
	}
	
	public void deletarUser(Long idUser) throws SQLException, Exception {
		String sql = "delete from model_login where id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUser);
		preparedStatement.executeUpdate();
		connection.commit();
	}
	
}
