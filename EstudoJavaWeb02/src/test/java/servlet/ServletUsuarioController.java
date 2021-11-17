package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig; // prepara para o upload
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.MSG;
import model.ModelLogin;
import model.TipoMSG;
import util.ReportUtil;

@MultipartConfig
@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private  DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
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
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
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
				
				response.addHeader("totalPagina", "" +  daoUsuarioRepository.consultaPaginaUsuarioPaginacao(nomeBusca, super.getUserLogado(request)));
				
				response.getWriter().write(json);
			
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscaUserAjaxPage")) {
				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaPaginaUsuarioPaginacaoOffset(nomeBusca, super.getUserLogado(request), Integer.parseInt(pagina));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.addHeader("totalPagina", "" +  daoUsuarioRepository.consultaPaginaUsuarioPaginacao(nomeBusca, super.getUserLogado(request)));
				
				response.getWriter().write(json);	
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				idUser = request.getParameter("id");
				ModelLogin usuario = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(idUser), super.getUserLogado(request));
				
				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
				request.setAttribute("users", usuarios);
				
				request.setAttribute("msg", MSG.criar(TipoMSG.PRIMARY, "Editar", "Usuário em edição!"));
				request.setAttribute("user", usuario);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
				request.setAttribute("users", usuarios);
				request.setAttribute("msg", MSG.criar(TipoMSG.PRIMARY, Integer.toString(usuarios.size()), "Usuário(s) Listado(s)!"));
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
			
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				idUser = request.getParameter("id");
				
				ModelLogin usuario = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(idUser), super.getUserLogado(request));
				if (usuario.getFotoUser() != null && !usuario.getFotoUser().isEmpty()) {
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + usuario.getExtensaoFotoUser());
					// REMOVE A STRING INICIAL QUE É NECESSÁRIA PARA EXIBIÇÃO NO OBJETO IMG. OBTÉM A STRING DA VÍRGULA EM DIANTE 
					response.getOutputStream().write(new Base64().decodeBase64(usuario.getFotoUser().split("\\,")[1]));
				}
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				Integer offset = Integer.parseInt(request.getParameter("registro"));
				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioListPaginado(super.getUserLogado(request), offset);
				request.setAttribute("users", usuarios);
				request.setAttribute("paginaAtual", offset);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);

			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioHTML")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if ((dataInicial == null || dataInicial.isEmpty()) && (dataFinal == null || dataFinal.isEmpty())) {
					request.setAttribute("listaUser", daoUsuarioRepository.buscarUsuarioListRelatorio(super.getUserLogado(request)));
					
				}else {
					request.setAttribute("listaUser", daoUsuarioRepository.buscarUsuarioListRelatorio(super.getUserLogado(request),
							dataInicial, dataFinal));
				}
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("/principal/relusuario.jsp").forward(request, response);
			
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				List<ModelLogin> listaUsuario = null;
				
				if ((dataInicial == null || dataInicial.isEmpty()) && (dataFinal == null || dataFinal.isEmpty())) {
					listaUsuario = daoUsuarioRepository.buscarUsuarioListRelatorio(super.getUserLogado(request));
					
				}else {
					listaUsuario = daoUsuarioRepository.buscarUsuarioListRelatorio(super.getUserLogado(request), dataInicial, dataFinal);
				}
				
				byte[] relatorio = new ReportUtil().geraRelatorioPDF(listaUsuario, "rel-user-jsp", request.getServletContext());
				
				response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
				response.getOutputStream().write(relatorio);
				
			}else {
				List<ModelLogin> usuarios = daoUsuarioRepository.buscarUsuarioList(super.getUserLogado(request));
				request.setAttribute("users", usuarios);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
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
			String imagemBase64 = null;
			String extensaoFotoUser = null;
			
			/* OBTÉM A FOTO E CONVERTE PARA BASE 64 */
			if (ServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("fileFoto");
				if (part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream()); /* Converte imagem para byte */
					imagemBase64 ="data:" + part.getContentType() + ";base64," + new Base64().encodeBase64String(foto);
					extensaoFotoUser = part.getContentType().split("\\/")[1];
				}
			}
			
			String dataNascimentoStr = request.getParameter("dataNascimento");
			Date dataNascimento = null;
			if (dataNascimentoStr != null && !dataNascimentoStr.isEmpty()) {
				dataNascimento = Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimentoStr)));
			}			
					
			String rendaMensal = request.getParameter("rendamensal");
			rendaMensal = rendaMensal.replaceAll("\\$","").replaceAll(" ", "").replaceAll("\\.", "").replaceAll("\\,", ".").replace("R", "");
			if (rendaMensal.trim() == null || rendaMensal.isEmpty()) {
				rendaMensal = "0.0";
			}
			
			ModelLogin usuario = new ModelLogin(id != null && !id.isEmpty() ? Long.parseLong(id) : 0L, 
					request.getParameter("nome"), request.getParameter("login"), request.getParameter("senha"), 
					request.getParameter("email"), false, request.getParameter("perfil"), request.getParameter("sexo"),
					imagemBase64, extensaoFotoUser, request.getParameter("cep"), request.getParameter("logradouro"), 
					request.getParameter("numero"), request.getParameter("complemento"), request.getParameter("bairro"), 
					request.getParameter("cidade"), request.getParameter("uf"), request.getParameter("ibge"), 
					dataNascimento, Double.parseDouble(rendaMensal));
			
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
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			request.getRequestDispatcher("/principal/usuario.jsp").forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", MSG.criar(TipoMSG.DANGER, "Ops", e.getMessage()));
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}		
	}

}
