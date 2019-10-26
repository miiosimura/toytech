<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Toy Tech</title>
	</head>

	<body>

		<form action="LoginServlet" method="post">
			Nome Completo:<input type="text" name="nome"><br>
			Nome de Usuário:<input type="text" name="username"><br>
			Senha:<input type="password" name="senha"><br>
			<input type="submit" value="Cadastrar">
		</form>

	</body>
</html>