<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="resources/css/bootstrap.css">
	<link rel="stylesheet" href="resources/css/estilo.css">
	
	<title>CURSO JSP</title>
</head>

<body>

	<div class="container mt-3 text-center">
		<h3>Informe suas Credenciais</h3>
	</div>
	<div class="container mt-5">
		<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="was-validated" >
			<input type="hidden" value="<%= request.getParameter("url") %>" name="url" >
			
			<div class="row mt-3">
				<div class="col-sm-3"></div>
				
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-2">
							<label class="form-label" for="login">Login:</label>
						</div>
						<div class="col-sm-10">
							<input id="login" name="login" type="text" placeholder="Login" class="form-control" required autocomplete="off">
							<!-- div class="valid-feedback">Ok</div -->
							<div class="invalid-feedback">Por Favor Informe o Login!</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
			
			<div class="row mt-3">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-2">
							<label class="form-label" for="senha">Senha:</label>
						</div>
						<div class="col-sm-10">
							<input id="senha" name="senha" type="password" placeholder="Senha" class="form-control" required>
							<!--  div class="valid-feedback">Ok</div -->
							<div class="invalid-feedback">Por Favor Informe a Senha!</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
			
			<div class="row mt-3">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Acessar" class="btn btn-primary  btn-block">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<div class="container mt-3">
		${msg}
	</div>						      
	
    <script src="resources/js/jquery.slim.min.js"></script>
    <script src="resources/js/popper.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>