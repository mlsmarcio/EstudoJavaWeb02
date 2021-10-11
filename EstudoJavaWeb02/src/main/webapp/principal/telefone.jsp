<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
		                            
		                                                <h4 class="sub-title">Cadastro de Telefones</h4>
		                                                <form class="form-material" autocomplete="off" method="post" 
                                                        	action="<%= request.getContextPath() %>/ServletTelefoneController" id="formFone">

															<div class="form-row">
																<div class="form-group col-md-6 form-default form-static-label">
																	<input type="text" name="id" id="id" class="form-control" readonly value="${user.id}">
																	<span class="form-bar"></span> <label class="float-label">ID Usuario:</label>
																</div>
	                                                            <div class="form-group col-md-6 form-default form-static-label">
	                                                                <input type="text" name="nome" id="nome" class="form-control" readonly value="${user.nome}" autocomplete="off">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Nome:</label>
	                                                            </div>
															</div>
															
															<div class="form-row">
																<div class="form-group col-md-6 form-default form-static-label">
																	<input type="text" name="numero" id="numero" class="form-control" required >
																	<span class="form-bar"></span> <label class="float-label">Número:</label>
																</div>
															</div>															
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
														</form>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
										<div class="container mt-1" id="msg">
											${msg}
										</div>
										
										<div style="height: 300px; overflow: scroll;">
											<table id="tblListaTelefones" class="table table-hover">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">NÚMERO</th>
														<th scope="col">EXCLUIR</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${listaTelefones}" var="item">
														<tr>
															<td><c:out value="${item.id}"></c:out></td>
															<td><c:out value="${item.numero}"></c:out></td>
															<td>
															  <a class="btn btn-success" 
															  href="<%= request.getContextPath() %>/ServletTelefoneController?acao=ExcluirFone&id=${item.id}&idUser=${user.id}">
																Excluir	</a>
															</td>
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
</body>

</html>
