package servlet;

import java.io.Serializable;
import java.sql.SQLException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		//Long retorno= 0L;
		
		HttpSession session = request.getSession();
		//String usuarioLogado = (String) session.getAttribute("usuario");
		//retorno = daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado.getLogin()).getId();
		//return retorno;
		ModelLogin usuario =  (ModelLogin) session.getAttribute("usuario");
		return usuario.getId();
	}

	public ModelLogin getUserLogadoObject(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		//String usuarioLogado = (String) session.getAttribute("usuario");
		//return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado);
		ModelLogin usuarioLogado = (ModelLogin) session.getAttribute("usuario");
		return usuarioLogado;
		
	}

}
