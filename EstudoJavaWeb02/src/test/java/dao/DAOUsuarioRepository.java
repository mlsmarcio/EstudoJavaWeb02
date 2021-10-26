package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelTelefone;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin usuario, Long usuarioLogado) throws SQLException {
		String sql = "";
		PreparedStatement preparedStatement = null;
		
		if (usuario.isNovo()) {
			sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, fotouser, extensaofoto,"
					+ " cep, logradouro, numero, complemento, bairro, cidade, uf, ibge, datanascimento, rendamensal) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			
			preparedStatement.setString(10, usuario.getCep());
			preparedStatement.setString(11, usuario.getLogradouro());
			preparedStatement.setString(12, usuario.getNumero());
			preparedStatement.setString(13, usuario.getComplemento());
			preparedStatement.setString(14, usuario.getBairro());
			preparedStatement.setString(15, usuario.getCidade());
			preparedStatement.setString(16, usuario.getUf());
			preparedStatement.setString(17, usuario.getIbge());
			
			preparedStatement.setDate(18, usuario.getDataNascimento());
			preparedStatement.setDouble(19, usuario.getRendamensal());
		
		}else {
			boolean atualizarFoto = (usuario.getFotoUser() != null && !usuario.getFotoUser().isEmpty());
			
			sql = "update model_login set nome=?, email=?, login=?, senha=?, perfil=?, sexo=?, "
					+ " cep=?, logradouro=?, numero=?, complemento=?, bairro=?, cidade=?, uf=?, ibge=?, "
					+ "datanascimento=?, rendamensal=? "
					+ (atualizarFoto ? ", fotouser=?, extensaofoto=?" : "")
					+ " where id = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getLogin());
			preparedStatement.setString(4, usuario.getSenha());
			preparedStatement.setString(5, usuario.getPerfil());
			preparedStatement.setString(6, usuario.getSexo());
			
			preparedStatement.setString(7, usuario.getCep());
			preparedStatement.setString(8, usuario.getLogradouro());
			preparedStatement.setString(9, usuario.getNumero());
			preparedStatement.setString(10, usuario.getComplemento());
			preparedStatement.setString(11, usuario.getBairro());
			preparedStatement.setString(12, usuario.getCidade());
			preparedStatement.setString(13, usuario.getUf());
			preparedStatement.setString(14, usuario.getIbge());
			preparedStatement.setDate(15, usuario.getDataNascimento());
			preparedStatement.setDouble(16, usuario.getRendamensal());
			
			if (atualizarFoto) {
				preparedStatement.setString(17, usuario.getFotoUser());
				preparedStatement.setString(18, usuario.getExtensaoFotoUser());
				preparedStatement.setLong(19, usuario.getId());
				
			}else{
				preparedStatement.setLong(17, usuario.getId());
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
			usuario.setDataNascimento(resultSet.getDate("datanascimento"));
			usuario.setRendamensal(resultSet.getDouble("rendamensal"));
			
			usuario.setCep(resultSet.getString("cep"));
			usuario.setLogradouro(resultSet.getString("logradouro"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setComplemento(resultSet.getString("complemento"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setIbge(resultSet.getString("ibge"));
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
			usuario.setDataNascimento(resultSet.getDate("datanascimento"));
			usuario.setRendamensal(resultSet.getDouble("rendamensal"));
			
			usuario.setCep(resultSet.getString("cep"));
			usuario.setLogradouro(resultSet.getString("logradouro"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setComplemento(resultSet.getString("complemento"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setIbge(resultSet.getString("ibge"));
			
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
			usuario.setDataNascimento(resultSet.getDate("datanascimento"));
			usuario.setRendamensal(resultSet.getDouble("rendamensal"));
			
			usuario.setCep(resultSet.getString("cep"));
			usuario.setLogradouro(resultSet.getString("logradouro"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setComplemento(resultSet.getString("complemento"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setIbge(resultSet.getString("ibge"));
		}
		return usuario;
	}
	public ModelLogin consultaUsuarioId(Long id) throws Exception {
		ModelLogin usuario = new ModelLogin();
		String sql = "select * from model_login where id = ? and useradmin is false;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, id);
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
			usuario.setExtensaoFotoUser(resultSet.getString("extensaofoto"));
			usuario.setDataNascimento(resultSet.getDate("datanascimento"));
			usuario.setRendamensal(resultSet.getDouble("rendamensal"));
			
			usuario.setCep(resultSet.getString("cep"));
			usuario.setLogradouro(resultSet.getString("logradouro"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setComplemento(resultSet.getString("complemento"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setIbge(resultSet.getString("ibge"));
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
			usuario.setExtensaoFotoUser(resultSet.getString("extensaofoto"));
			usuario.setDataNascimento(resultSet.getDate("datanascimento"));
			usuario.setRendamensal(resultSet.getDouble("rendamensal"));
			
			usuario.setCep(resultSet.getString("cep"));
			usuario.setLogradouro(resultSet.getString("logradouro"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setComplemento(resultSet.getString("complemento"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setIbge(resultSet.getString("ibge"));
		}
		return usuario;
	}
	
	public List<ModelLogin> buscarUsuarioList(String nome, Long usuarioLogado) throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id=? order by nome limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2, usuarioLogado);
		ResultSet resultSet= preparedStatement.executeQuery();
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"), resultSet.getBoolean("useradmin"), resultSet.getString("perfil"), 
					resultSet.getString("sexo"), resultSet.getString("fotouser"), resultSet.getString("extensaofoto"), 
					resultSet.getString("cep"), resultSet.getString("logradouro"), resultSet.getString("numero"), 
					resultSet.getString("complemento"), resultSet.getString("bairro"), resultSet.getString("cidade"), 
					resultSet.getString("uf"), resultSet.getString("ibge"), resultSet.getDate("datanascimento"),
					resultSet.getDouble("rendamensal"));
			
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	public List<ModelLogin> buscarUsuarioListPaginado(Long usuarioLogado, Integer offset) throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login WHERE useradmin is false and usuario_id = ? order by nome offset ? limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, usuarioLogado);
		preparedStatement.setInt(2, offset);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"), resultSet.getBoolean("useradmin"), resultSet.getString("perfil"), 
					resultSet.getString("sexo"), resultSet.getString("fotouser"), resultSet.getString("extensaofoto"), 
					resultSet.getString("cep"), resultSet.getString("logradouro"), resultSet.getString("numero"), 
					resultSet.getString("complemento"), resultSet.getString("bairro"), resultSet.getString("cidade"), 
					resultSet.getString("uf"), resultSet.getString("ibge"), resultSet.getDate("datanascimento"),
					resultSet.getDouble("rendamensal"));
			
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public int consultaPaginaUsuarioPaginacao(String nome, Long userLogado) throws Exception {
		String sql = "select count(1) as total from model_login WHERE upper(nome) like upper(?) and useradmin is false " + 
				"and usuario_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2, userLogado);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		Double cadastros = resultSet.getDouble("total");
		Double porpagina= 5.0;
		Double pagina = cadastros / porpagina;
		if (pagina != pagina.intValue()) {
			pagina++;
		}
		return pagina.intValue();	
	}

	public List<ModelLogin> consultaPaginaUsuarioPaginacaoOffset(String nome, Long usuarioLogado, int offset) throws Exception {
			List<ModelLogin> listaUsuarios = new ArrayList<>();
			
			ModelLogin usuario = null;
			String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id=? order by nome offset ? limit 5";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nome + "%");
			preparedStatement.setLong(2, usuarioLogado);
			preparedStatement.setInt(3, offset);
			ResultSet resultSet= preparedStatement.executeQuery();
			while (resultSet.next()) {
				usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
						"", resultSet.getString("email"), resultSet.getBoolean("useradmin"), resultSet.getString("perfil"), 
						resultSet.getString("sexo"), resultSet.getString("fotouser"), resultSet.getString("extensaofoto"), 
						resultSet.getString("cep"), resultSet.getString("logradouro"), resultSet.getString("numero"), 
						resultSet.getString("complemento"), resultSet.getString("bairro"), resultSet.getString("cidade"), 
						resultSet.getString("uf"), resultSet.getString("ibge"), resultSet.getDate("datanascimento"),
						resultSet.getDouble("rendamensal"));
				
				listaUsuarios.add(usuario);
			}
			return listaUsuarios;
	}

	public List<ModelLogin> buscarUsuarioListRelatorio(Long usuarioLogado) throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login WHERE useradmin is false and usuario_id = ? order by nome";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, usuarioLogado);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"), resultSet.getBoolean("useradmin"), resultSet.getString("perfil"), 
					resultSet.getString("sexo"), resultSet.getString("fotouser"), resultSet.getString("extensaofoto"), 
					resultSet.getString("cep"), resultSet.getString("logradouro"), resultSet.getString("numero"), 
					resultSet.getString("complemento"), resultSet.getString("bairro"), resultSet.getString("cidade"), 
					resultSet.getString("uf"), resultSet.getString("ibge"), resultSet.getDate("datanascimento"),
					resultSet.getDouble("rendamensal"));
			
			usuario.setTelefones(this.listaFones(usuario.getId()));
			
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public List<ModelLogin> buscarUsuarioList(Long usuarioLogado) throws Exception {
		List<ModelLogin> listaUsuarios = new ArrayList<>();
		
		ModelLogin usuario = null;
		String sql = "select * from model_login WHERE useradmin is false and usuario_id = ? order by nome limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, usuarioLogado);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
					"", resultSet.getString("email"), resultSet.getBoolean("useradmin"), resultSet.getString("perfil"), 
					resultSet.getString("sexo"), resultSet.getString("fotouser"), resultSet.getString("extensaofoto"), 
					resultSet.getString("cep"), resultSet.getString("logradouro"), resultSet.getString("numero"), 
					resultSet.getString("complemento"), resultSet.getString("bairro"), resultSet.getString("cidade"), 
					resultSet.getString("uf"), resultSet.getString("ibge"), resultSet.getDate("datanascimento"),
					resultSet.getDouble("rendamensal"));
			
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
		preparedStatement.execute();
		connection.commit();
	}
	
	public int totalPagina(Long userLogado) throws SQLException, Exception {
		String sql = "select count(1) as total from model_login where usuario_id = ? and id <> ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement = connection.prepareStatement(sql);
		statement.setLong(1, userLogado);
		statement.setLong(2, userLogado);
		ResultSet resultSet= statement.executeQuery();
		resultSet.next();
		Double cadastros = resultSet.getDouble("total");
		Double porpagina= 5.0;
		Double pagina = cadastros / porpagina;
		if (pagina != pagina.intValue()) {
			pagina++;
		}
//		Double resto = pagina % 2;
//		if (resto > 0) {
//			pagina ++;
//		}
		return pagina.intValue();
	}
	
	public List<ModelTelefone> listaFones(Long idUsuario) throws Exception {
		List<ModelTelefone> listaTelefones = new ArrayList<>();
		
		ModelTelefone telefone = null;
		String sql = "select * from telefone where usuario_id =?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUsuario);
		ResultSet rs= preparedStatement.executeQuery();
		
		while (rs.next()) {
			telefone = new ModelTelefone(rs.getLong("id"), rs.getString("numero"), 
					rs.getLong("usuario_id"), 
					this.consultaUsuarioId(rs.getLong("usuario_cad_id")));
			
			listaTelefones.add(telefone);
		}
		return listaTelefones;
	}	
	
}
