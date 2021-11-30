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
				                                        
				                                        <!-- enctype - para poder fazer upload de foto
				                                        	 Adicionar a Notação @MultipartConfig no servlet
				                                         -->
                                                        <form class="form-material" enctype="multipart/form-data" autocomplete="off" method="post" 
                                                        	action="<%= request.getContextPath() %>/ServletUsuarioController" id="formUser">
                                                        	
                                                        	<!--  AÇÃO QUE SERÁ ENVIADA PARA O SERVLET -->
                                                        	<input type="hidden" name="acao" id="acao" value="">
                                                        	
                                                            <div class="form-row">
	                                                            <div class="form-group col-md-6 form-default form-static-label">
	                                                                <input type="text" name="id" id="id" class="form-control" readonly value="${user.id}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">ID:</label>
	                                                            </div>
	                                                            
	                                                            <div class="form-group col-md-6 form-default input-group mb-4">
	                                                           	  	<div class="input-group-prepend">
	                                                            	    <span class="input-group-text" id="basic-addon1">
																			<c:if test="${user.fotoUser != '' && user.fotoUser != null}">
																				<a id="linkImage" href="<%= request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${user.id}">
																			    	<img class="d-flex align-self-center img-radius" id="fotoembase64" alt="Imagem Usuário" src="${user.fotoUser}" width="70px">
																			    </a>
																			</c:if>
																			<c:if test="${user.fotoUser == '' || user.fotoUser == null}">
																			    <img class="d-flex align-self-center img-radius" id="fotoembase64" alt="Imagem Usuário" src="resources/img/usuario.jpg" width="70px"> <!-- assets/images/avatar-1.jpg -->
																			</c:if>
	                                                         	      		
	                                                            	    </span>
																	</div>
																	<input id="fileFoto" name="fileFoto" type="file" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto')" 
																		class="form-control-file" style="margin: 15px; margin-left: 5px;">
	                                                            </div>
                                                            
	                                                            <div class="form-group col-md-5 form-default form-static-label">
	                                                                <input type="text" name="nome" id="nome" class="form-control" required value="${user.nome}" autocomplete="off" autofocus>
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Nome:</label>
	                                                            </div>
	                                                            <div class="form-group  col-md-5 form-default form-static-label">
	                                                                <input type="email" name="email" id="email" class="form-control" required autocomplete="off" value="${user.email}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Email:</label>
	                                                            </div>
	                                                            <div class="form-group  col-md-2 form-default form-static-label">
	                                                                <input type="text" name="rendamensal" id="rendamensal" class="form-control" required autocomplete="off" value="${user.rendamensal}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Renda Mensal:</label>
	                                                            </div>
	                                                            
	                                                            <div class="form-group col-md-6 form-default form-static-label">
																	<select class="form-control" aria-label="Default select example" name="perfil" id="perfil">
																	  <option disabled ${user.perfil == '' || user.perfil == null ? 'selected' :''}>Selecione o Perfil</option>
																	  <option value="ADMIN" ${user.perfil == 'ADMIN' ? 'selected' :''}>Administrador</option>
																	  <option value="SECRETARIA" ${user.perfil == 'SECRETARIA' ? 'selected' :''}>Secretária</option>
																	  <option value="AUXILIAR" ${user.perfil == 'AUXILIAR' ? 'selected' :''}>Auxiliar</option>
																	</select>
																	<span class="form-bar"></span>
	                                                                <label class="float-label">Perfil:</label>
	                                                            </div>
	                                                            <div class="form-group col-md-6 form-default form-static-label">
	                                                                <input type="text" name="dataNascimento" id="dataNascimento" class="form-control" required autocomplete="off" value="${user.dataNascimento}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Dat. Nascimento:</label>
	                                                            </div>
                                                            
															
																<div class="form-group col-md-3">
																	<label for="cep">CEP </label> 
																	<input type="text" id="cep"	name="cep" value="${user.cep}" class="form-control" required
																		pattern="\d{5}-\d{3}" placeholder="00000-000" onblur="consultaCep()">
																</div>
																<div class="form-group col-md-6">
																	<label for="logradouro">Endereço</label> 
																	<input type="text" value="${user.logradouro}" placeholder="Endereço"	class="form-control" id="logradouro" name="logradouro">
																</div>
																<div class="form-group col-md-3">
																	<label for="numero">Número</label> 
																	<input type="text" value="${user.numero}" Class="form-control" placeholder="Número" id="numero" name="numero">
																</div>

																<div class="form-group col-md-6">
																	<label for="complemento">Complemento</label> 
																	<input type="text" id="complemento" name="complemento" placeholder="Complemento" value="${user.complemento}" 
																	class="form-control" placeholder="Complemento">
																</div>
																<div class="form-group col-md-6">
																	<label for="bairro">Bairro</label> 
																	<input type="text" id="bairro" name="bairro" value="${user.bairro}" placeholder="Bairro"
																	class="form-control" placeholder="Bairro">
																</div>

																<div class="form-group col-md-3">
																	<label for="ibge">IBGE</label> 
																	<input type="text" value="${user.ibge}"class="form-control" id="ibge" name="ibge">
																</div>
									
																<div class="form-group col-md-6">
																	<label for="cidade">Cidade</label> 
																	<input type="text" value="${user.cidade}" class="form-control" placeholder="Cidade" id="cidade" name="cidade">
																</div>
																<div class="form-group col-md-3">
																	<label for="uf">Estado</label> 
																	<select id="uf" name="uf"	class="form-control">
																		<option value="AC" ${user.uf == 'AC' ? 'selected' :''} >Acre</option>
																		<option value="AL" ${user.uf == 'AL' ? 'selected' :''} >Alagoas</option>
																		<option value="AP" ${user.uf == 'AP' ? 'selected' :''} >Amapá</option>
																		<option value="AM" ${user.uf == 'AM' ? 'selected' :''} >Amazonas</option>
																		<option value="BA" ${user.uf == 'BA' ? 'selected' :''} >Bahia</option>
																		<option value="CE" ${user.uf == 'CE' ? 'selected' :''} >Ceará</option>
																		<option value="DF" ${user.uf == 'DF' ? 'selected' :''} >Distrito Federal</option>
																		<option value="ES" ${user.uf == 'ES' ? 'selected' :''} >Espírito Santo</option>
																		<option value="GO" ${user.uf == 'GO' ? 'selected' :''} >Goiás</option>
																		<option value="MA" ${user.uf == 'MA' ? 'selected' :''} >Maranhão</option>
																		<option value="MT" ${user.uf == 'MT' ? 'selected' :''} >Mato Grosso</option>
																		<option value="MS" ${user.uf == 'MS' ? 'selected' :''} >Mato Grosso do Sul</option>
																		<option value="MG" ${user.uf == 'MG' ? 'selected' :''} >Minas Gerais</option>
																		<option value="PA" ${user.uf == 'PA' ? 'selected' :''} >Pará</option>
																		<option value="PB" ${user.uf == 'PB' ? 'selected' :''} >Paraíba</option>
																		<option value="PR" ${user.uf == 'PR' ? 'selected' :''}>Paraná</option>
																		<option value="PE" ${user.uf == 'PE' ? 'selected' :''}>Pernambuco</option>
																		<option value="PI" ${user.uf == 'PI' ? 'selected' :''}>Piauí</option>
																		<option value="RJ" ${user.uf == 'RJ' ? 'selected' :''}>Rio de Janeiro</option>
																		<option value="RN" ${user.uf == 'RN' ? 'selected' :''}>Rio Grande do Norte</option>
																		<option value="RS" ${user.uf == 'RS' ? 'selected' :''}>Rio Grande do Sul</option>
																		<option value="RO" ${user.uf == 'RO' ? 'selected' :''}>Rondônia</option>
																		<option value="RR" ${user.uf == 'RR' ? 'selected' :''}>Roraima</option>
																		<option value="SC" ${user.uf == 'SC' ? 'selected' :''}>Santa Catarina</option>
																		<option value="SP" ${user.uf == 'SP' ? 'selected' :''}>São Paulo</option>
																		<option value="SE" ${user.uf == 'SE' ? 'selected' :''}>Sergipe</option>
																		<option value="TO" ${user.uf == 'TO' ? 'selected' :''}>Tocantins</option>
																	</select>
																</div>

	                                                            <div class="form-group col-md-6 form-default form-static-label">
	                                                                <input type="text" name="login" id="login" class="form-control" value="${user.login}" required autocomplete="off">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Login:</label>
	                                                            </div>
	                                                            <div class="form-group col-md-6 form-default form-static-label">
	                                                                <input type="password" name="senha" id="senha" class="form-control" value="${user.senha}" required autocomplete="off">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Senha:</label>
	                                                            </div>
															</div>
															<div class="form-group row"> 
															    <div class="col-sm-2">                                                           
																  <input class="" type="radio" name="sexo" id="sexoM" value="M" ${user.sexo == 'M' ? 'checked' :''} >
																  <label class="form-check-label" for="sexoM"> Masculino </label>
																</div>
																<div class="col-sm-2">										  
																  <input class="" type="radio" name="sexo" id="sexoF" value="F" ${user.sexo == 'F' ? 'checked' :''}>
																  <label class="form-check-label" for="sexoF"> Feminino </label>
																</div>
																<div class="col-sm-4"></div>
                                                            </div>
                                                            
												            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limpaForm();">Novo</button>
												            <button class="btn btn-success waves-effect waves-light">Salvar</button>
												            <button type="button" onclick="criarDelete();" class="btn btn-info waves-effect waves-light">Excluir</button>
                                                            <button type="button" onclick="criarDeleteAjax();" class="btn btn-info waves-effect waves-light">Excluir Com Ajax</button>
                                                            <c:if test="${user.id > 0}">
                                                            	<a href="<%= request.getContextPath() %>/ServletTelefoneController?idUser=${user.id}" class="btn btn-primary waves-effect waves-light">Telefone</a>
                                                            </c:if>
                                                            <button id="botaoPesquisa" type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exemploModal">Pesquisar</button>
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
										
										<!-- PAGINAÇÃO -->
										<nav aria-label="...">
										  <ul class="pagination justify-content-center">
										<%
										    	int totalPagina = 0;
												int paginaAtual = 0;
												String url = "";
												
												//
												if (request.getAttribute("totalPagina") != null){
													totalPagina = (int) request.getAttribute("totalPagina");
												}
												if (request.getAttribute("paginaAtual") != null){
													paginaAtual = (int) request.getAttribute("paginaAtual");
												}
												
												//CONFIGURA O LINK [ANTERIOR]
												url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&registro=" + (paginaAtual - 5);
												if (paginaAtual == 0){
													out.print("<li class='page-item disabled'>");
												}else{
													out.print("<li class='page-item'>");
												}
												out.print("  <a class='page-link' href=\"" +  url + "\" tabindex='-1'>Anterior</a>");
												out.print("</li>");
												
										    	//LINKS DAS PÁGINAS
										    	for (int pagina = 0; pagina < totalPagina; pagina++){
										    		url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&registro=" + (pagina * 5);
										    		
										    		if ((pagina * 5) == paginaAtual){
										    			out.print("<li class=\"page-item active\"><a class=\"page-link\" href=\"" +  url + "\">" + (pagina + 1) + 
										    					"<span class=\"sr-only\">(atual)</span></a></li>");
										    		} else {
										    			out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" +  url + "\">" + (pagina + 1) + "</a></li>");
										    		}
										    	}
										    	
										    	//LINK PRÓXIMO
										    	url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&registro=" + (paginaAtual + 5);
										    	if (paginaAtual == (totalPagina -1) * 5){
										    		out.print("<li class=\"page-item disabled\">");
										    	}else{
										    		out.print("<li class=\"page-item\">");
										    	}
										    	out.print("  <a class='page-link' href=\"" +  url + "\" tabindex='-1'>Próximo</a>");
												out.print("</li>");
										%>    
										  </ul>
										</nav>										
										
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
				
			  	<input type="text" class="form-control" placeholder="Nome" aria-label="name" id="nomeBusca" 
			  		name="nomeBusca" aria-describedby="basic-addon2">
			  	
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
	        
	        <!-- PAGINAÇÃO NO MODAL -->
	        <nav aria-label="Page navigation">
	        	<ul class="pagination" id="ulPaginacaoUserAjax"></ul>
	        </nav>
	        
	        <!-- EXIBE A QUANTIDADE DE ITENS RETORNADOS -->
	        <span id="totalResultados"></span>
	      </div>
	      
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div> <!-- FIM - MODAL -->    
    
    
	<script type="text/javascript">
		// FORMATA O CAMPO MONETÁRIO
		$("#rendamensal").maskMoney({showSymbol : true, symbol : "R$ ", decimal : ",", thousands : "."});
		const formatter = new Intl.NumberFormat('pt-BR', {currency : 'BRL',	minimumFractionDigits : 2});
		$("#rendamensal").val("R$ " + formatter.format($("#rendamensal").val()));
		//--
		
		// FORMATA A DATA
		if ($("#dataNascimento").val() != null && $("#dataNascimento").val() != '') {
			let dataNascimento = $("#dataNascimento").val();
			let dateFormat = new Date(dataNascimento);
	    	$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'}));
    	}
		
		// DATA DE NASCIMENTO
		$( function() {
			  $("#dataNascimento").datepicker({
				    dateFormat: 'dd/mm/yy',
				    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
				    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
				    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
				    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
				    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
				    nextText: 'Próximo',
				    prevText: 'Anterior',
				    showAnim: 'clip', 
			        changeMonth: true,
				    changeYear: true
				});
		} );

		// CAMPOS QUE RECEBEM APENAS NÚMEROS
		$("#numero").keypress(function (event) {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});

		// EXIBE O ENDEREÇO AO DEIXAR O CAMPO CEP
		function consultaCep(){
			//Nova variável "cep" somente com dígitos.
			let cep = $("#cep").val().replace(/\D/g, '');
			let logradouro = $("#logradouro").val();

            //Verifica se campo cep possui valor informado.
            if (cep != "" && logradouro == "") {

                //Expressão regular para validar o CEP.
                var validacep = /^[0-9]{8}$/;

                //Valida o formato do CEP.
                if(validacep.test(cep)) {

                    //Preenche os campos com "..." enquanto consulta webservice.
                    $("#logradouro").val("...");
                    $("#numero").val("...");
                    $("#complemento").val("...");
                    $("#bairro").val("...");
                    $("#cidade").val("...");
                    $("#uf").val("...");
                    $("#ibge").val("...");

                    //Consulta o webservice viacep.com.br/
                    $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                        if (!("erro" in dados)) {
                            //Atualiza os campos com os valores da consulta.
                            $("#logradouro").val(dados.logradouro);
                            $("#numero").val("");
                            $("#complemento").val("");
                            $("#bairro").val(dados.bairro);
                            $("#cidade").val(dados.localidade);
                            $("#uf").val(dados.uf);
                            $("#ibge").val(dados.ibge);
                        } //end if.
                        else {
                            //CEP pesquisado não foi encontrado.
							$("#logradouro").val("");
							$("#numero").val("");
							$("#complemento").val("");
							$("#bairro").val("");
							$("#cidade").val("");
							$("#uf").val("");
							$("#ibge").val("");

                            alert("CEP não encontrado.");
                        }
                    });
                } //end if.
                else {
                    //cep é inválido.
                    limpa_formulário_cep();
                    alert("Formato de CEP inválido.");
                }
            } //end if.
		}

		// EXIBE A FOTO AO SELECIONAR O ARQUIVO
		function visualizarImg(fotoembase64, fileFoto){
			let preview = document.getElementById(fotoembase64);
			let fileUser = document.getElementById(fileFoto).files[0];

			let reader = new FileReader();

			reader.onloadend = function(){
				preview.src = reader.result; /* Carrega a foto na tela */
			};

			if (fileUser){
				reader.readAsDataURL(fileUser); /* Preview da Imagem */
			}else {
				preview.src = '';
			}
		}

		// LISTA O USUÁRIO PARA EDIÇÃO
		function verEditar(id){
			var urlAction = document.getElementById('formUser').action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		// Busca de Usuário via Ajax - Click no botão da Paginação
		function buscaUserPagAjax(url){
			let urlAction = document.getElementById('formUser').action;
			
			$.ajax({
				method: "get",
				url: urlAction,
				data: url,
				success: function(response, textStatus, xhr){
					let json = JSON.parse(response);
					
					// VER NO CONSOLE DO NAVEGADOR
					//console.info(json);
					
					// REMOVE AS LINHAS JÁ EXISTENTES 
					$('#tblResultadoBusca > tbody > tr').remove();

					// REMOVE OS BOTÕES DE NAVEGAÇÃO
					$('#ulPaginacaoUserAjax > li').remove();
					
					// ADICIONA LINHAS PARA OS REGISTROS JSON
					for (var p = 0; p < json.length; p++){
						$('#tblResultadoBusca > tbody').append('<tr> <td>' + json[p].id + '</td>' +
								'<td>' + json[p].nome + '</td> ' + 
								'<td><button type="btton" onclick="verEditar(' + json[p].id +')" class="btn btn-info sm">Ver</button></td> </tr>');
					} 
					document.getElementById('totalResultados').textContent = ' Resultados: ' + json.length;

					// RECUPERA O TOTAL DE PÁGINAS DO CABEÇALHO DA REQUISIÇÃO QUE FOI ENVIADO NO SERVLET
					let totalPagina = xhr.getResponseHeader("totalPagina");
					let nomeBusca = document.getElementById('nomeBusca').value;
					
					// MONTA A PAGINAÇÃO NO MODAL
					let urlAjax = "";
					let strHtml = "";                 
					for (var p = 0; p < totalPagina; p++){	            
						urlAjax = "nomeBusca=" + nomeBusca + "&acao=buscaUserAjaxPage&pagina=" + (p * 5);
						strHtml = "<li class=\"page-item\"><a href=\"#\" class=\"page-link\" onclick=\"buscaUserPagAjax('" + urlAjax + "')\">" + (p +  1) + "</a></li>";
						$("#ulPaginacaoUserAjax").append(strHtml);
					}
				}
				
			}).fail(function(xhr, status, errorThrown){
				alert('Erro ao buscar usuário por nome via Ajax: ' + xhr.responseText);
			});				

		}

		// Busca usuários e exibe no Modal - Click no botão BUSCAR
		function buscarUsuario(){
			var nomeBusca = document.getElementById('nomeBusca').value;
			if (nomeBusca != null && nomeBusca.trim() != ''){
				
				let urlAction = document.getElementById('formUser').action;
				$.ajax({
					method: "get",
					url: urlAction,
					data: "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
					success: function(response, textStatus, xhr){
						let json = JSON.parse(response);
						
						// VER NO CONSOLE DO NAVEGADOR
						//console.info(json);
						
						// REMOVE AS LINHAS JÁ EXISTENTES 
						$('#tblResultadoBusca > tbody > tr').remove();

						// REMOVE OS BOTÕES DE NAVEGAÇÃO
						$('#ulPaginacaoUserAjax > li').remove();
						
						// ADICIONA LINHAS PARA OS REGISTROS JSON
						for (var p = 0; p < json.length; p++){
							$('#tblResultadoBusca > tbody').append('<tr> <td>' + json[p].id + '</td>' +
									'<td>' + json[p].nome + '</td> ' + 
									'<td><button type="btton" onclick="verEditar(' + json[p].id +')" class="btn btn-info sm">Ver</button></td> </tr>');
						} 
						document.getElementById('totalResultados').textContent = ' Resultados: ' + json.length;

						// RECUPERA O TOTAL DE PÁGINAS DO CABEÇALHO DA REQUISIÇÃO QUE FOI ENVIADO NO SERVLET
						let totalPagina = xhr.getResponseHeader("totalPagina");
						
						// MONTA A PAGINAÇÃO NO MODAL
						let url = "";
						let strHtml = "";
						for (var p = 0; p < totalPagina; p++){
							url = "nomeBusca=" + nomeBusca + "&acao=buscaUserAjaxPage&pagina=" + (p*5) ;
							strHtml = "<li class=\"page-item\"> <a href=\"#\" class=\"page-link\" onclick=\"buscaUserPagAjax('" + url + "')\">" + (p + 1) + "</a></li>";
							$("#ulPaginacaoUserAjax").append(strHtml);
						}
						
					}
					
				}).fail(function(xhr, status, errorThrown){
					alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
				});				
			}
		}

		// EXCLUSÃO DE REGISTROS
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
		
		// CONFIRMAÇÃO PARA DELETAR
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
			let elementos = document.getElementById("formUser").elements;
			for (let x in elementos){
				elementos[x].value='';
			}
			let preview = document.getElementById('fotoembase64');
			preview.src='';	
			document.getElementById("linkImage").href='';		
			document.getElementById("nome").focus();
		}
/*
		$( "#nomeBusca" ).keypress(function( event ) {
			  if ( event.which == 13 ) {
				  event.preventDefault();
				  alert('entrou');
			     buscarUsuario();
			  }
		}		*/
	</script>
</body>

</html>
