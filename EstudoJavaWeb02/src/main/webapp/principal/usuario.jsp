<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>	
  
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
		
		<jsp:include page="navbar.jsp"></jsp:include>
		
          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
              	
				<jsp:include page="navbar-main.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                     
                      <!-- Page-header start -->
                      <jsp:include page="Page-header.jsp"></jsp:include>                      
                      
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
										
                                        <div class="row">
                                        	<div class="col-sm-12">
										
		                                        <div class="card">
		                                            <div class="card-block">
		                                                <h4 class="sub-title">Cadastro de Usuários</h4>										
				                                        
                                                        <form class="form-material" autocomplete="off" method="post" action="<%= request.getContextPath() %>/ServletUsuarioController" id="formUser">
                                                        	
                                                        	<!--  AÇÃO QUE SERÁ ENVIADA PARA O SERVLET -->
                                                        	<input type="hidden" name="acao" id="acao" value="">
                                                        	
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly value="${user.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required value="${user.nome}" autocomplete="off" autofocus>
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" id="email" class="form-control" required autocomplete="off" value="${user.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Email:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
																<select class="form-control" aria-label="Default select example" name="perfil" id="perfil">
																  <option disabled ${user.perfil == '' ? 'selected' :''}>Selecione o Perfil</option>
																  <option value="ADMIN" ${user.perfil == 'ADMIN' ? 'selected' :''}>Administrador</option>
																  <option value="SECRETARIA" ${user.perfil == 'SECRETARIA' ? 'selected' :''}>Secretária</option>
																  <option value="AUXILIAR" ${user.perfil == 'AUXILIAR' ? 'selected' :''}>Auxiliar</option>
																</select>
																<span class="form-bar"></span>
                                                                <label class="float-label">Perfil:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" id="login" class="form-control" value="${user.login}" required autocomplete="off">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" id="senha" class="form-control" value="${user.senha}" required autocomplete="off">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha:</label>
                                                            </div>
                                                            
												            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limpaForm();">Novo</button>
												            <button class="btn btn-success waves-effect waves-light">Salvar</button>
												            <button type="button" onclick="criarDelete();" class="btn btn-info waves-effect waves-light">Excluir</button>
                                                            <button type="button" onclick="criarDeleteAjax();" class="btn btn-info waves-effect waves-light">Excluir Com Ajax</button>
                                                            <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exemploModal">Pesquisar</button>
                                                        </form>
				                                        
			                                        </div>
		                                        </div>
	                                        </div>
	                                	</div>	
										<div class="container mt-1" id="msg">
											${msg}
										</div>

										<div style="height: 300px; overflow: scroll;">
											<table id="tblListaUsuarios" class="table table-hover">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">NOME</th>
														<th scope="col">EMAIL</th>
														<th scope="col">LOGIN</th>
														<th scope="col">VER</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${users}" var="item">
														<tr>
															<td><c:out value="${item.id}"></c:out></td>
															<td><c:out value="${item.nome}"></c:out></td>
															<td><c:out value="${item.email}"></c:out></td>
															<td><c:out value="${item.login}"></c:out></td>
															<td><a class="btn btn-success" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${item.id}">Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>
    
    <!-- MODAL -->
	<div class="modal fade" id="exemploModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Pesquisa de Usuários</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
			<div class="input-group mb-3">
			  <input type="text" class="form-control" placeholder="Nome" aria-label="name" id="nomeBusca" name="nomeBusca" aria-describedby="basic-addon2">
			  <div class="input-group-append">
			    <button class="btn btn-success" type="button" onclick="buscarUsuario()">Buscar</button>
			  </div>
			</div>	    
			
			<div style="height: 300px; overflow: scroll;">
			    <table id="tblResultadoBusca" class="table table-hover">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">NOME</th>
				      <th scope="col">VER</th>
				    </tr>
				  </thead>
				  <tbody>
				  </tbody>
				</table>
	        </div>
	        <span id="totalResultados"></span>
	      </div>
	      
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>    
    
	
	<script type="text/javascript">
		function verEditar(id){
			var urlAction = document.getElementById('formUser').action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}
		
		function buscarUsuario(){
			var nomeBusca = document.getElementById('nomeBusca').value;
			if (nomeBusca != null && nomeBusca.trim() != ''){
				
				var urlAction = document.getElementById('formUser').action;
				$.ajax({
					
					method: "get",
					url: urlAction,
					data: "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
					success: function(response){
						var json = JSON.parse(response);
						
						// VER NO CONSOLE DO NAVEGADOR
						//console.info(json);
						
						// REMOVE AS LINHAS JÁ EXISTENTES 
						$('#tblResultadoBusca > tbody > tr').remove();
						
						// ADICIONA LINHAS PARA OS REGISTROS JSON
						for (var p = 0; p < json.length; p++){
							$('#tblResultadoBusca > tbody').append('<tr> <td>' + json[p].id + '</td>' +
									'<td>' + json[p].nome + '</td> ' + 
									'<td><button type="btton" onclick="verEditar(' + json[p].id +')" class="btn btn-info sm">Ver</button></td> </tr>');
						} 
						document.getElementById('totalResultados').textContent = ' Resultados: ' + json.length;
					}
					
				}).fail(function(xhr, status, errorThrown){
					alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
				});				
			}
		}
		
		function criarDeleteAjax(){
			if (document.getElementById("id").value <= 0){
				alert("Nenhum usuário selecionado!");
				return;
			}			
			if (confirm('Deseja Excluir o Usuário Selecionado?')){
				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;
				
				$.ajax({
					
					method: "get",
					url: urlAction,
					data: "id=" + idUser + '&acao=deletarAjax',
					success: function(response){
						limpaForm();
						document.getElementById('msg').textContent = response;
					}
					
				}).fail(function(xhr, status, errorThrown){
					alert('Erro ao deletar usuário por id: ' + xhr.responseText);
				});
			}
		}
		
	
		function criarDelete(){
			if (document.getElementById("id").value <= 0){
				alert("Nenhum usuário selecionado!");
				return;
			}
			if (confirm('Deseja Excluir o Usuário Selecionado?')){
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}
		}
	
		function limpaForm(){
			var elementos = document.getElementById("formUser").elements;
			for (let x in elementos){
				elementos[x].value='';
			}
			document.getElementById("nome").focus();
		}
		
	</script>
</body>

</html>
