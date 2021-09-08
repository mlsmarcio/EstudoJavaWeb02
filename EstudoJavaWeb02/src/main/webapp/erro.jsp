<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela de exibição de erros</title>

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="resources/css/bootstrap.css">
	<link rel="stylesheet" href="resources/css/estilo.css">
	
</head>
<body>
	<div class="container">
		<h1>Mensagem de Erro, entre em contato com a equipe de suporte do sistema.</h1>
		${msg}
		<% //	out.print(request.getAttribute("msg"));	%>
	</div>

    <script src="resources/js/jquery.slim.min.js"></script>
    <script src="resources/js/popper.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>