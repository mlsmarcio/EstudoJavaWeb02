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
	
	public ModelLogin gravarUsuario(ModelLogin usuario, Long usuarioLogado) throws SQLException {
		String sql = "";
		PreparedStatement preparedStatement = null;
		
		if (usuario.isNovo()) {
			sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, fotouser, extensaofoto) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getEmail());
			preparedStatement.setLong(5, usuarioLogado);
			preparedStatement.setString(6, usuario.getPerfil());
			preparedStatement.setString(7, usuario.getSexo());
			preparedStatement.setString(8, usuario.getFotoUser());
			preparedStatement.setString(9, usuario.getExtensaoFotoUser());
		
		}else {
			boolean atualizarFoto = (usuario.getFotoUser() != null && !usuario.getFotoUser().isEmpty());
			
			sql = "update model_login set nome = ?, email = ?, login = ?, senha = ?, perfil=?, sexo=?"
					+ (atualizarFoto ? ", fotouser=?, extensaofoto=?" : "")
					+ " where id = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getLogin());
			preparedStatement.setString(4, usuario.getSenha());
			preparedStatement.setString(5, usuario.getPerfil());
			preparedStatement.setString(6, usuario.getSexo());
			
			if (atualizarFoto) {
				preparedStatement.setString(7, usuario.getFotoUser());
				preparedStatement.setString(8, usuario.getExtensaoFotoUser());
				preparedStatement.setLong(9, usuario.getId());
				
			}else{
				preparedStatement.setLong(7, usuario.getId());
			}
		}
		preparedStatement.execute();
		connection.commit();
		
		return this.consultaUsuario(usuario.getLogin(), usuarioLogado);
	}
	
	public ModelLogin consultaUsuario(String login, Long usuarioLogado) throws SQLException {
		ModelLogin usuario = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is false and usuario_id=?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login);
		preparedStatement.setLong(2, usuarioLogado);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		if (resultSet.next()) {
			usuario.setId(resultSet.getLong("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setPerfil(resultSet.getString("perfil"));
			usuario.setSexo(resultSet.getString("sexo"));
			usuario.setFotoUser(resultSet.getString("fotouser"));
		}
		return usuario;
	}
	
	public ModelLogin consultaUsuarioLogado(String login) throws SQLException {
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
			usuario.setUserAdmin(resultSet.getBoolean("useradmin"));
			usuario.setPerfil(resultSet.getString("perfil"));
			usuario.setSexo(resultSet.getString("sexo"));
			usuario.setFotoUser(resultSet.getString("fotouser"));
		}
		return usuario;
	}
	

	public ModelLogin consultaUsuario(String login) throws SQLException {
		ModelLogin usuario = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is false;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		if (resultSet.next()) {
			usuario.setId(resultSet.getLong("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setUserAdmin(resultSet.getBoolean("useradmin"));
			usuario.setPerfil(resultSet.getString("perfil"));
			usuario.setSexo(resultSet.getString("sexo"));
			usuario.setFotoUser(resultSet.getString("fotouser"));
		}
		return usuario;
	}

	public ModelLogin consultaUsuarioId(Long id, Long usuarioLogado) throws Exception {
		ModelLogin usuario = new ModelLogin();
		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id=?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		preparedStatement.setLong(2, usuarioLogado);
		ResultSet resultSet= preparedStatement.executeQuery();
		if (resultSet.next()) {
			usuario.setId(resultSet.getLong("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setPerfil(resultSet.getString("perfil"));
			usuario.setSexo(resultSet.getString("sexo"));
			usuario.setFotoUser(resultSet.getString("fotouser"));
		}
		return usuario;
	}
	
	public List<ModelLogin> buscarUsuarioList(String nome, Long usuarioLogado) throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id=?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2, usuarioLogado);
		ResultSet resultSet= preparedStatement.executeQuery();
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"), resultSet.getString("perfil"), resultSet.getString("sexo"),
					resultSet.getString("fotouser"), resultSet.getString("extensaofoto"));
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	public List<ModelLogin> buscarUsuarioList(Long usuarioLogado) throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login WHERE useradmin is false and usuario_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, usuarioLogado);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"), resultSet.getString("perfil"), resultSet.getString("perfil"),
					resultSet.getString("fotouser"), resultSet.getString("extensaofoto"));
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	// NESSE MOMENTO NÃO TEMOS O ID DO USUÁRIO LOGADO, ENTÃO, NÃO PASSAMOS NA CONSULTA
	public boolean existeLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as contem from model_login where upper(login) = upper(?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login);
		ResultSet resultSet= preparedStatement.executeQuery();
		resultSet.next(); 
		return resultSet.getBoolean("contem");
	}
	
	// 
	public void deletarUser(Long idUser) throws SQLException, Exception {
		String sql = "delete from model_login where id = ?  and useradmin is false";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUser);
		preparedStatement.executeUpdate();
		connection.commit();
	}
	
}
