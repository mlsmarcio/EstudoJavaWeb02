package servlet;

import java.io.Serializable;

import dao.DaoUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();
	
	public Long getUserLogado(HttpServletRequest request) {
		Long retorno= 0L;
		
		HttpSession session = request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		try {
			retorno = daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
