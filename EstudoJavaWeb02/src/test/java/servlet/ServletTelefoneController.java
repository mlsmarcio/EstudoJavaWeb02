package servlet;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MSG;
import model.ModelLogin;
import model.ModelTelefone;
import model.TipoMSG;

@WebServlet("/ServletTelefoneController")
public class ServletTelefoneController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
       
    public ServletTelefoneController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equals("ExcluirFone") ) {
				String idFone = request.getParameter("id");
				daoTelefoneRepository.deleteFone(Long.parseLong(idFone));
				request.setAttribute("msg", MSG.criar(TipoMSG.PRIMARY, "Concluido", "Excluído com Sucesso!"));
			}
			
			String idUser = request.getParameter("idUser");
			
			if (idUser != null && !idUser.isEmpty()) {
				ModelLogin usuario = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(idUser));
				request.setAttribute("user", usuario);
				
				List<ModelTelefone> listaTelefones = daoTelefoneRepository.listaFones(usuario.getId());
				request.setAttribute("listaTelefones", listaTelefones);
				request.getRequestDispatcher("/principal/telefone.jsp").forward(request, response);
				
			}else {
				// ENCAMINHA PARA O SERVLET USUÁRIO 
				request.getRequestDispatcher("/ServletUsuarioController").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String usuario_id = request.getParameter("id");
			String numeroTelefone = request.getParameter("numero");
			//String idUser = request.getParameter("idUser");
			
			ModelTelefone modelTelefone = new ModelTelefone();
			modelTelefone.setNumero(numeroTelefone);
			
			modelTelefone.setUsuario_id(Long.parseLong(usuario_id));
			modelTelefone.setUsuario_cad(super.getUserLogadoObject(request));
			
			if (!daoTelefoneRepository.existeFone(numeroTelefone, Long.parseLong(usuario_id))) {
				daoTelefoneRepository.gravaTelefone(modelTelefone);
				request.setAttribute("msg", MSG.criar(TipoMSG.PRIMARY, "Concluido", "Salvo com Sucesso!"));
				
			} else {
				request.setAttribute("msg", MSG.criar(TipoMSG.DANGER, "Atenção", "Telefone já cadastrado!"));
			}
			
			request.setAttribute("user", daoUsuarioRepository.consultaUsuarioId(modelTelefone.getUsuario_id()));
			
			List<ModelTelefone> listaTelefones = daoTelefoneRepository.listaFones(modelTelefone.getUsuario_id());
			request.setAttribute("usuario", modelTelefone.getUsuario_cad());
			request.setAttribute("listaTelefones", listaTelefones);
			request.getRequestDispatcher("/principal/telefone.jsp").forward(request, response);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
