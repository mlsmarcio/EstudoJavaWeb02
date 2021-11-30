package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;

public class DaoVersionadorBanco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	public DaoVersionadorBanco() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravaArquivoSqlRodado (String nomeFile) throws Exception {
		String sql = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nomeFile);
		preparedStatement.execute();
	}
	
	public boolean arquivoSqlRodado(String nomeDoArquivo) throws Exception{
		
		String sql = "select count(1) > 0 as rodado from versionadorbanco where arquivo_sql = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nomeDoArquivo);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getBoolean("rodado");
	}

}
