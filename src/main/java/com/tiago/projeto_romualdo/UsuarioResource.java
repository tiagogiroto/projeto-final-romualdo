package com.tiago.projeto_romualdo;

import java.util.List;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
	UsuarioRepository repo = new UsuarioRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() {
		System.out.print("Get All");		
		return repo.getUsuarios();	
		
	}
	
	@GET
	@Path("usuario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("id") int id) {
		System.out.println(id);
		return repo.getUsuario(id);
	}
	
	@GET
	@Path("usuario_movie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsuarioMovie(@PathParam("id") int id) {
		System.out.println(id);
		
		Usuario usuario = repo.takeMovieOfUser(id);
		String id_movie =  usuario.getMovie();
		
		Client client = ClientBuilder.newClient();
		
		 WebTarget target = client.target("https://api.themoviedb.org/3/movie/"+id_movie+"?api_key=9d01d43a03a9aceeb1ce96b3139b0252&language=pt-BR");
//		 String s = target.request().get(String.class);
		 
		 
		 Invocation.Builder builder = target.request();
		 String response = builder.get(String.class);
		
		 System.out.print(response);

		
		return response;
	}
	
	
	@POST
	@Path("usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario createUsuario(Usuario a) {
		System.out.println(a);
		repo.create(a);
		
		return a;
	}
	
	@PUT
	@Path("usuarioUpdate")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario updateUsuario(Usuario a) {
		System.out.println(a);
		
		if(repo.getUsuario(a.getId()).getId()==0) 
		{
			repo.create(a);
		} else {
			repo.update(a);			
		}
		
		
		return a;
	}
	
	@DELETE
	@Path("deleteById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUsuario(@PathParam("id") int id) {
		
		System.out.println(id);
		repo.deleteById(id);
		
		return "Id deletado : "+ id;
		
	}


}
