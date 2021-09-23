package servlet;

import java.io.IOException;

import dao.DAOLoginRepository;
import dao.DaoUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MSG;
import model.ModelLogin;
import model.TipoMSG;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DaoUsuarioRepository daoUsuarioRepository =  new DaoUsuarioRepository();
	
	
    public ServletLogin() {
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher redireciona = request.getRequestDispatcher("index.jsp");
			redireciona.forward(request, response);
			
		}else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		usuario = new ModelLogin(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("login"), 
//		"", resultSet.getString("email"), resultSet.getBoolean("useradmin"), resultSet.getString("perfil"), 
//		resultSet.getString("sexo"), resultSet.getString("fotouser"), resultSet.getString("extensaofoto"), 
//		resultSet.getString("cep"), resultSet.getString("logradouro"), resultSet.getString("numero"), 
//		resultSet.getString("complemento"), resultSet.getString("bairro"), resultSet.getString("cidade"), 
//		resultSet.getString("uf"), resultSet.getString("ibge"));			
		
		ModelLogin modelLogin = new ModelLogin(0L, "", request.getParameter("login"), request.getParameter("senha"), "", false,
				"", "", "", "", "", "", "", "", "", "", "", "");
		
		String url = request.getParameter("url");
		RequestDispatcher redireciona = null;
		
		try {
			if (modelLogin.getLogin() != null && !modelLogin.getLogin().isEmpty() && 
					modelLogin.getSenha() != null && !modelLogin.getSenha().isEmpty()) {
				
				if (daoLoginRepository.validarAutenticacao(modelLogin)) {
					
					modelLogin = daoUsuarioRepository.consultaUsuarioLogado(modelLogin.getLogin());
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					request.getSession().setAttribute("perfil", modelLogin.getPerfil());
					
					if (url == null || url.equals("null") || url.isEmpty()) {
						url= "principal/principal.jsp";
					}
					
					redireciona = request.getRequestDispatcher(url);
					
				}else {
					request.setAttribute("msg", MSG.criar(TipoMSG.WARNING,"","Login ou senha incorretos!"));
					redireciona = request.getRequestDispatcher("index.jsp");
				}
				redireciona.forward(request, response);
				
			}else {
				redireciona = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", MSG.criar(TipoMSG.INFO,"","Login ou senha incorretos!"));
				redireciona.forward(request, response);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", MSG.criar(TipoMSG.DANGER, "Ops", e.getMessage()));
			redireciona.forward(request, response);
		}
		
	}
	
//	private String preparaMsg(String msg, String titulo) {
//		
//		titulo = (titulo.isEmpty()?"Atenção!":titulo);
//		
//		return "<div class='alert alert-info alert-dismissible fade show'> " + 
//				"<button type='button' class='close' data-dismiss='alert'>&times;</button>" + 
//				"<strong>" + titulo + "</strong> " + msg +
//				"</div>";
//	}

}
