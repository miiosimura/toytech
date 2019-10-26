package br.com.toytech.web;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.toytech.bean.Produto;
import br.com.toytech.dao.ProdutoDAO;

@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		// a variável cmd indica o tipo de ação - incluir, alterar, consulta.....
		String cmd = request.getParameter("cmd");
		
		// cria um objeto dao - CRUD
		ProdutoDAO dao;
		
		// cria um objeto do tipo produto
		Produto produto = new Produto();
		
		if (cmd != null) {
			try {
				// inicializa os atributos da classe Produto
				produto.setIdProduto(Integer.parseInt(request.getParameter("txtIdProduto")));
				produto.setIdCategoria(Integer.parseInt(request.getParameter("txtIdCategoria")));
				produto.setNomeProduto(request.getParameter("txtNomeProduto"));
				produto.setPrecoUnitario(Double.parseDouble(request.getParameter("txtPrecoUnitario")));
				produto.setImgProduto(request.getParameter("txtImgProduto"));
				produto.setDescricao(request.getParameter("txtDescricao"));
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		try {
			// cria a instancia do objeto dao
			dao = new ProdutoDAO();
			
			RequestDispatcher rd = null;
			 
			if (cmd.equalsIgnoreCase("listar")) {
				List<Produto> produtoList = dao.todosProdutos();
				request.setAttribute("produtoList", produtoList);		// cria uma sessão para encaminhar a lista para uma JSP
				rd = request.getRequestDispatcher("/produtos.jsp");		// redireciona para a JSP produtos
			}
			
			else if (cmd.equalsIgnoreCase("incluir")) {
				dao.salvar(produto);
				rd = request.getRequestDispatcher("ServletProduto?cmd=listar");
			} 
						
			else if (cmd.equalsIgnoreCase("exc")) { 	// consulta produto para exclusão
				produto = dao.procurarProduto(produto.getIdProduto());
				HttpSession session = request.getSession(true);
				session.setAttribute("produto", produto);
				rd = request.getRequestDispatcher("/excProduto.jsp");
			} 
			
			else if (cmd.equalsIgnoreCase("excluir")) {	// exclui produto
				dao.excluir(produto);
				rd = request.getRequestDispatcher("ServletProduto?cmd=listar");
				
			} 
			
			else if (cmd.equalsIgnoreCase("atu")) {		// consulta produto para alteração
				produto = dao.procurarProduto(produto.getIdProduto());
				HttpSession session = request.getSession(true);
				session.setAttribute("produto", produto);
				rd = request.getRequestDispatcher("/atuProduto.jsp");
				
			} 
			
			else if (cmd.equalsIgnoreCase("atualizar")) {	// altera produto
				dao.atualizar(produto);
				rd = request.getRequestDispatcher("ServletProduto?cmd=listar");
				
			} 
			
			else if (cmd.equalsIgnoreCase("consultar")) {	// consulta produto
				String paramValue = request.getParameter("idProduto");
				produto = dao.procurarProduto(Integer.parseInt(paramValue));
				HttpSession session = request.getSession(true);
				session.setAttribute("produto", produto);
				rd = request.getRequestDispatcher("/detalhes.jsp");				
			}
			
			else if (cmd.equalsIgnoreCase("listarCategoria")) {
				String paramValue = request.getParameter("idCategoria");
				List<Produto> produtoList = dao.listarProdutosCategoriaX(Integer.parseInt(paramValue));
				request.setAttribute("produtoList", produtoList);		// cria uma sessão para encaminhar a lista para uma JSP
				rd = request.getRequestDispatcher("/produtos.jsp");		// redireciona para a JSP produtos
			}
			
			
						
			// executa a ação de direcionar para a página JSP
			rd.forward(request, response);
			
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
