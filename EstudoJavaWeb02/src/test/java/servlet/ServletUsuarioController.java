package servlet;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private  DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();
	
    public ServletUsuarioController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String idUser = "";
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(Long.parseLong(idUser));

				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
				request.setAttribute("users", usuarios);
				
				request.setAttribute("msg", MSG.criar(TipoMSG.INFO, "Concluído", "Excluído com Sucesso!"));
				request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
				idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(Long.parseLong(idUser));
				response.getWriter().write("Excluído Com Sucesso!");
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.buscarUsuarioList(nomeBusca, super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);
			
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				idUser = request.getParameter("id");
				ModelLogin usuario = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(idUser), super.getUserLogado(request));
				
				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
				request.setAttribute("users", usuarios);
				
				request.setAttribute("msg", MSG.criar(TipoMSG.PRIMARY, "Editar", "Usuário em edição!"));
				request.setAttribute("user", usuario);
				request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
				request.setAttribute("users", usuarios);
				request.setAttribute("msg", MSG.criar(TipoMSG.PRIMARY, Integer.toString(usuarios.size()), "Usuário(s) Listado(s)!"));
				request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
				
			}else {
				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
				request.setAttribute("users", usuarios);
				
				request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", MSG.criar(TipoMSG.DANGER, "Ops", e.getMessage()));
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String id = request.getParameter("id");
			
			ModelLogin usuario = new ModelLogin(id != null && !id.isEmpty() ? Long.parseLong(id) : 0L, 
					request.getParameter("nome"), request.getParameter("login"), request.getParameter("senha"), 
					request.getParameter("email"), request.getParameter("perfil"));
			
			if (daoUsuarioRepository.existeLogin(usuario.getLogin()) && usuario.isNovo()) {
				request.setAttribute("msg", MSG.criar(TipoMSG.DANGER, "Atenção", "Login já cadastrado!"));
				
			}else {
				String msg = usuario.isNovo() ? "Usuário Cadastrado" : "Dados Atualizados";
				usuario = daoUsuarioRepository.gravarUsuario(usuario, super.getUserLogado(request));
				request.setAttribute("msg", MSG.criar(TipoMSG.PRIMARY, "Concluido", msg));
			}
				
			List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
			request.setAttribute("users", usuarios);
			
			request.setAttribute("user", usuario);
			request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", MSG.criar(TipoMSG.DANGER, "Ops", e.getMessage()));
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}		
	}

}
