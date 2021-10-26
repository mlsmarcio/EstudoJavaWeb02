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

public class DAOTelefoneRepository {
	
	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravaTelefone(ModelTelefone modelTelefone) throws Exception {
		String sql = "insert into telefone (numero, usuario_id, usuario_cad_id) values (?, ?, ?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, modelTelefone.getNumero());
		preparedStatement.setLong(2, modelTelefone.getUsuario_id());
		preparedStatement.setLong(3, modelTelefone.getUsuario_cad().getId());
		preparedStatement.execute();
		connection.commit();
	}
	
	public void deleteFone (Long id) throws Exception {
		String sql = "delete from telefone where id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		preparedStatement.executeUpdate();
		connection.commit();
		
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
					daoUsuarioRepository.consultaUsuarioId(rs.getLong("usuario_cad_id")));
			
			listaTelefones.add(telefone);
		}
		return listaTelefones;
	}
	
	public boolean existeFone(String fone, Long idUse) throws Exception {
		String sql = "select count(1) > 0 as existe from telefone where usuario_id = ? and numero = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUse);
		preparedStatement.setString(2, fone);
		ResultSet rs= preparedStatement.executeQuery();
		rs.next();
		
		return rs.getBoolean("existe");
	}

}
