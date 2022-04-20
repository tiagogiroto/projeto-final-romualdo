package com.tiago.projeto_romualdo;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String movie;
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [id= auto increment no banco," + " nome=" + nome + ", email=" + email + ", telefone=" + telefone + "Movie : " + movie + "]";
	}
	
	
	
	
}
