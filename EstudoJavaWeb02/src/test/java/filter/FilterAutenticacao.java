package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;

@WebFilter(urlPatterns = {"/principal/*"})	// Intercepta todas as requisições do projeto ou mapeamento
public class FilterAutenticacao implements Filter {
	private static Connection connection;
	private String contextPath;
	
    public FilterAutenticacao() {
    }
    
    // Encerra os processos quando o servidor é parado
    // finalizar processos de conexão com o banco de dados
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Intercepta as requisições e respostas do sistema  Ex.:
	// Validação de autenticação
	// Dar commit e rolback de transações do banco de dados
	// Validar e fazer redirecionamento de páginas.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		RequestDispatcher redireciona = null;
		HttpServletRequest req =  (HttpServletRequest) request;
		try {
			
			HttpSession session = req.getSession();
			//String usuarioLogado = (String) session.getAttribute("usuario");
			ModelLogin usuarioLogado = (ModelLogin) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath();// URL QUE ESTÁ SENDO ACESSADA, DIGITADA EX.: principal/principal.jsp
			
			// SE TENTAR ACESSAR ALGUMA TELA SEM ESTAR LOGADO, REDIRECIONA PARA LOGIN
			if (usuarioLogado.getLogin() == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
				redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(req, response);
				return; // PARA A EXECUÇÃO E REDIRECIONA PARA O LOGIN
				
			}else {
				// PROCESSO NORMAL DO SISTEMA
				//chain.doFilter(request, response);
				chain.doFilter(req, response);
			}
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//redireciona = request.getRequestDispatcher(contextPath + "/erro.jsp");
			
			request.setAttribute("msg", e.getMessage().toString());
			//redireciona.forward(request, response);
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// Inicia os processos  ou recursos quando o servidor sobre o projeto
	// Ex.: Inicia a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
		contextPath = fConfig.getServletContext().getContextPath();
		System.out.println(contextPath);
	}

}
