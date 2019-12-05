<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	function validarLogin() {
		if (document.formLogin.username.value == "") {
			alert("Usuário não informado. Tente novamente");
			return false;
		}
		if (document.formLogin.senha.value == "") {
			alert("Senha não informada. Tente novamente");
			return false;
		}
		document.formLogin.submit();
	}
</script>

<meta charset="UTF-8">
<title>Toytech</title>
</head>
<body>
	<style>
		form{
		    width: 80%;
		    margin: 0 auto;
		    border-radius: 10px;
		    height: auto;
		   	padding: 90px 40px;
		    text-align: center;
		    font-size: 20px;
		    border: 1px solid #ccc;
		    background-color: #cccccc30;
		}
		form input {
		    margin: 20px 10px;
		    border-radius: 4px;
		    text-decoration: none;
		    box-shadow: 0 0 0 0;
		    border: 1px solid;
		    outline: 0;
		    padding: 6px;
		}
		form a {
		    text-decoration: none;
		    color: #000;
		    cursor: pointer;
		}
		form input[type="submit"]{
			cursor:pointer;
		}
	</style>
	<h3>${message}</h3>

	<form action="ServletLogin?cmd=logar" method="post" name="formLogin">
		Usuário:<input type="text" name="username" required><br>
		Senha:<input type="password" name="senha" required><br> 
		<input type="submit" value="Entrar" onclick="validarLogin()"> 
		<a href="index.jsp">Voltar</a>
	</form>

</body>
</html>



