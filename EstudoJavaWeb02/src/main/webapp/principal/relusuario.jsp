<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
														<h4 class="sub-title">Relatório de Usuários</h4>

														<form class="form-material" autocomplete="off" method="get"
														  action="<%=request.getContextPath()%>/ServletUsuarioController" id="formRel">
															
															<input type="hidden" name="acao" id="acao" value="imprimirRelatorioUser">
															
															<div class="form-row align-items-center">
																<div class="col-auto">
																	<label class="sr-only" for="dataInicial">Data Inicial</label>
																	<input type="text" class="form-control mb-2"
																		name="dataInicial" id="dataInicial" value="${dataInicial}" placeholder="Data Inicial">
																</div>
																<div class="col-auto">
																	<label class="sr-only" for="dataFinal">Data Final</label>
																	<input type="text" class="form-control mb-2"
																		name="dataFinal" id="dataFinal" value="${dataFinal}" placeholder="Data Inicial">
																</div>
																<div class="col-auto">
																	<button type="submit" class="btn btn-primary mb-2">Imprimir Relatório</button>
																</div>
															</div>
														</form>
														
														<div style="height: 300px; overflow: scroll;">
															<table id="tblListaUsuarios" class="table table-hover">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">NOME</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${listaUser}" var="item">
																		<tr>
																			<td><c:out value="${item.id}"></c:out></td>
																			<td><c:out value="${item.nome}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>														
													</div>
												</div>
											</div>


										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>
	
	<script type="text/javascript">
	$( function() {
		  $("#dataInicial, #dataFinal").datepicker({
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
	
	</script>
</body>

</html>
