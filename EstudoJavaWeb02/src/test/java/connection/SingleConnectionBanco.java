package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnectionBanco {
	
	//private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp2?autoReconnect=true&&charSet=ISO8859-1";
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp2?autoReconnect=true&&charSet=UTF-8";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	// SE CHAMAR A CLASSE REALIZAR� A CONEX�O
	static {
		conectar();
	}
	
	// SE INSTANCIAR A CLASSE REALIZAR� A CONEX�O
	public SingleConnectionBanco() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); //CARREGA O DRIVER DE CONEX�O DO BANCO
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); // PARA N�O EFETUAR ALTERA��ES SEM NOSSO COMANDO
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
