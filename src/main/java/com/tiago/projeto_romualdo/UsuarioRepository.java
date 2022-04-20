package com.tiago.projeto_romualdo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class UsuarioRepository {

	Connection con = null;

	List<Usuario> usuario;
	
	public UsuarioRepository() {
	
		String url = "jdbc:mysql://localhost/projeto_romualdo?useTimezone=true&serverTimezone=UTC";
		String user = "admin";
		String pass = "admin";
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
	}
	
	public List<Usuario> getUsuarios(){

		List<Usuario> usuarios = new ArrayList<>();
		String sql = "select * from usuario";		
		
		
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) 
			{
				Usuario user = new Usuario();
				user.setId(rs.getInt(1));
				user.setNome(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setTelefone(rs.getString(4));
				user.setMovie(rs.getString(5));
				
				usuarios.add(user);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return usuarios;
	}
	
	public Usuario getUsuario(int id) {
		
		String sql = "select * from usuario where id_user="+id;		
		Usuario u = new Usuario();
		
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) 
			{
				
				u.setId(rs.getInt(1));
				u.setNome(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setTelefone(rs.getString(4));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return u;
	}

	public void create(Usuario a) {

		String sql = "insert into usuario values(default,?,?,?)";
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, a.getNome());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getTelefone());
			
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		
	}
	
	public void update(Usuario a) {

		String sql = "update usuario set nome=?, email=?, telefone=? where id_user=?";
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, a.getNome());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getTelefone());
			ps.setInt(4 , a.getId());
			
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		
	}
	
	public void deleteById(int id) {

		String sql = "delete from usuario where id_user=" + id;		
		
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute(sql);

			
		}
		
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return;
	}

	public Usuario takeMovieOfUser(int id) {
		
		
		String sql = "select * from usuario where id_user="+id;		
		Usuario u = new Usuario();
		
		try 
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) 
			{
				
				u.setNome(rs.getString(2));
				u.setMovie(rs.getString(5));

			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return u;
	}
}
