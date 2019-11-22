package br.com.toytech.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.toytech.bean.Categoria;
import br.com.toytech.bean.Produto;
import br.com.toytech.bean.Usuario;
import br.com.toytech.dao.ProdutoDAO;
import br.com.toytech.dao.UsuarioDAO;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");		
		UsuarioDAO dao;
		Usuario usuario = new Usuario();
				
		if (cmd != null) {
			try {
				usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
				usuario.setNome(request.getParameter("nome"));
				usuario.setUsername(request.getParameter("username"));
				usuario.setSenha(request.getParameter("senha"));
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
				
		try {
			dao = new UsuarioDAO();
			RequestDispatcher rd = null;
						
			if (cmd.equalsIgnoreCase("logar")) {
				String username = request.getParameter("username");
				String senha = request.getParameter("senha");				
				usuario = dao.verificaUsuario(username);
								
				if ((usuario == null) || (!usuario.getSenha().toString().equals(senha))){
					request.setAttribute("message", "Usu�rio ou senha incorretos, tente novamente!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					
				} else if ((usuario.getUsername().toString().equals(username)) && (usuario.getSenha().toString().equals(senha))) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);		
					request.getRequestDispatcher("ServletProduto?cmd=listar").forward(request, response);
				}
			} 
			
			else if (cmd.equalsIgnoreCase("logout")) {
				HttpSession session = request.getSession();
				session.invalidate();
				request.getRequestDispatcher("ServletProduto?cmd=listar").forward(request, response);
				
			} 
			
			else if (cmd.equalsIgnoreCase("listar")) {
				List<Usuario> usuarioList = dao.todosUsuarios();
				request.setAttribute("usuarioList", usuarioList);
				rd = request.getRequestDispatcher("/listUsuario.jsp");
			}
						
			else if (cmd.equalsIgnoreCase("incluir")) {
				dao.salvar(usuario);
				rd = request.getRequestDispatcher("ServletUsuario?cmd=listar");
			} 
				
			else if (cmd.equalsIgnoreCase("exc")) {
				usuario = dao.procurarUsuario(Integer.toString(usuario.getIdUsuario()));
				HttpSession session = request.getSession(true);
				session.setAttribute("usuario", usuario);
				rd = request.getRequestDispatcher("/excUsuario.jsp");
			} 
			
			else if (cmd.equalsIgnoreCase("excluir")) {	
				dao.excluir(usuario);
				rd = request.getRequestDispatcher("listUsuario.jsp");
				
			} 
			
			else if (cmd.equalsIgnoreCase("atu")) {
				usuario = dao.procurarUsuario(Integer.toString(usuario.getIdUsuario()));
				HttpSession session = request.getSession(true);
				session.setAttribute("usuario", usuario);
				rd = request.getRequestDispatcher("/altUsuario.jsp");
				
			} 
			
			else if (cmd.equalsIgnoreCase("atualizar")) {
				dao.atualizar(usuario);
				rd = request.getRequestDispatcher("listUsuario.jsp");
				
			} 
			
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
        
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}