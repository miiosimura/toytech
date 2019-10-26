package br.com.toytech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.toytech.bean.Produto;
import br.com.toytech.bean.Usuario;
import br.com.toytech.util.ConnectionFactory;

public class UsuarioDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Usuario usuario;
	
	public UsuarioDAO() throws Exception {
		// chama a classe ConnectionFactory e estabele uma conexão
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}
	
	public void cadastrarUsuario(Usuario usuario) throws Exception {
		
		if (usuario == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			
			String SQL = "INSERT INTO usuario (idUsuario, nome, username, senha) values (?, ?, ?, ?)";
			conn = this.conn;			
			ps = conn.prepareStatement(SQL);
			
			ps.setInt(1, usuario.getIdUsuario());
			ps.setString(2, usuario.getNome());
			ps.setString(3, usuario.getUsername());
			ps.setString(4, usuario.getSenha());
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
			
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	public Usuario verificaUsuario(String username) throws Exception {

		try {
			String SQL = "SELECT * FROM usuario WHERE username = ?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if (rs.next()) {				
				int idUsuario = rs.getInt("idUsuario");
				String nome = rs.getString("nome");
				String username_ms = rs.getString("username");
				String senha = rs.getString("senha");			
				usuario = new Usuario(idUsuario, nome, username_ms, senha);
				return usuario;
			
			} else {
				return null;
			}
			
			
			
		} catch (SQLException sqle) {
			throw new Exception(sqle);
			
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
}
