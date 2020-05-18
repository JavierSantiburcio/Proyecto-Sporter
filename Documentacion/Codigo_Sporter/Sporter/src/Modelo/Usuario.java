package Modelo;
import java.sql.*;

public class Usuario {
	protected String nombre;
	protected String email;
	protected String password;
	protected Statement command;
	private boolean admin;
	
	public Usuario(Statement command) {
		this.command = command;
		nombre = null;
		email = null;
		password = null;
		admin = false;
	}
	
	public Usuario(Statement command, int id) throws SQLException {
		ResultSet data = command.executeQuery("Select nombre,email,password,admin from spoter.usuarios user where user.idUsuarios = "+ id +";"); 
		data.next();
		nombre = data.getString(1);
		email = data.getString(2);
		password = data.getString(3);
		admin = data.getBoolean(4);
		this.command = command;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public boolean getAdmin() {
		return admin;
	}
}
