<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="br.com.toytech.bean.Produto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Toy Tech</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta name="description" content="">
    	<meta name="author" content="">
		
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/bootstrap.css" rel="stylesheet" media="screen">
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
		<link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
		<link href="js/bootstrap.js" rel="javascript" media="screen">
		<link href="js/bootstrap.min.js" rel="javascript" media="screen">
		
		<style type="text/css">
			body {
				padding-top: 60px;
	      	}
	      	.sidebar-nav {
		        padding: 9px 0;
			}
	
			@media (max-width: 980px) { /* Enable use of floated navbar text */
	        	.navbar-text.pull-right {
	          		float: none;
	          		padding-left: 5px;
	          		padding-right: 5px;
	        	}
	      	}
	      	
	      	footer{
	      		background-color: coral;
				height: 50px;
				text-align: center;
				padding: 30px;
				margin-top: 80px;
			}
    	</style>
	</head>

	<body>
		
		<div class="navbar navbar-inverse navbar-fixed-top" style="background-color: #C9F0FF;">
	      <div class="navbar-inner" style="height: 90px;">
	        <div class="container-fluid">
  	          
  	          <a href="index.jsp"><img src="logo/logo.png" style="width: 90px;"></a>
	          
              <span style="font-size: 40px; color: #8582D8;">Toy Tech</span>

	          	<c:if test= "${not empty username}">
            		<p class="navbar-text pull-right" style="margin: 0px;">					
						Olá ${username}!  |  <a href="ServletLogin?cmd=logout">Sair</a>
	            	</p>
        		</c:if>

		        <c:if test= "${empty username}">
		            <p class="navbar-text pull-right" style="padding-top: 20px; background-color: black;">					
						<a href="login.jsp">Fazer Login</a>
	            	</p>
		        </c:if>
		        
	        </div><!--/.container-fluid -->
	      </div><!--/.navbar-inner -->
	    </div><!--/.navbar -->
	
	    <div class="container-fluid" style="padding-top: 50px;">
	      
	      <div class="row-fluid">
	        <div class="span3">
	          <div class="well sidebar-nav">
	            <ul class="nav nav-list">
	              <li class="nav-header">Categorias</li>
	              <li><a href="http://localhost:8080/ToyTech/ServletProduto?cmd=listar">Todas</a></li>
	              <li><a href="http://localhost:8080/ToyTech/ServletProduto?cmd=listarCategoria&idCategoria=1">Bonecas</a></li>
	              <li><a href="http://localhost:8080/ToyTech/ServletProduto?cmd=listarCategoria&idCategoria=2">Pelúcias</a></li>
	              <li><a href="http://localhost:8080/ToyTech/ServletProduto?cmd=listarCategoria&idCategoria=3">Carrinhos e Cia</a></li>
	              <li><a href="http://localhost:8080/ToyTech/ServletProduto?cmd=listarCategoria&idCategoria=4">Jogos de Tabuleiro</a></li>
	              <li><a href="http://localhost:8080/ToyTech/ServletProduto?cmd=listarCategoria&idCategoria=5">Blocos de Montar</a></li>
	              	              
	              <br />
	              
	              <c:if test= "${not empty username}">
            		<li class="nav-header">Área Administrativa</li>
	              	<li><a href="ServletCategoria?cmd=mostrar">Listar Categorias</a></li>
	              	<li><a href="ServletProduto?cmd=mostrar">Listar Produtos</a></li>
	              	<li><a href="ServletUsuario?cmd=mostrar">Listar Usuários</a></li>
        		  </c:if>
        		  	              
	            </ul>
	          </div><!--/.well -->
	        </div><!--/span-->

	        <div class="span9">
	         <div class="container-fluid">	
	          
	          <jsp:useBean id="categoria" scope="session" class="br.com.toytech.bean.Categoria" />
	          
	          	<!-- CONTEUDO AQUI -->
	          	<form action="ServletCategoria?cmd=excluir" method="post">
					 <tbody>
						 <tr>
						 	<th colspan="2"><h4>Excluir Categoria</h4><br /></th>
						 </tr>
						 <tr>
						 	
						 	<input type="hidden" name="idCategoria" value="<%=categoria.getIdCategoria()%>"/>
						 	
						 	<th><h4>Nome da Categoria</h4></th>
							<td><input type="text" name="nomeCategoria" size="10" maxlenght="10" value="<%=categoria.getNomeCategoria()%>" readonly="readonly"/></td>
							
						 </tr>
						 
						 <tr>
							<td colspan="2" align="center"><br />
							<input type="submit" value="Excluir"></td>
						 </tr>
					</tbody>
				</form>
	        </div><!--/span-->
	      </div>
	
	      <hr>
	
	    </div>

		<footer>
	        <p>&copy; Company 2013</p>
	    </footer>
	    
	</body>
</html>