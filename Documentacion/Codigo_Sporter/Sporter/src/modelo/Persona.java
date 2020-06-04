 package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persona extends Usuario{
	
	private int id;
	private String localidad;
	private boolean existente;
	public ArrayList<Integer> participa = new ArrayList<Integer>();
	public ArrayList<Integer> practica = new ArrayList<Integer>();
	
	public Persona(Statement command) {
		super(command);
		id = -1;
		localidad = null;
		participa = null;
		existente = false;
	}
	
	public Persona(Statement command, int id) throws SQLException {
		super(command, id);
		this.id = id;
		if(getAdmin()) {
			throw new RuntimeException("Es admin");
		}
		ResultSet data = command.executeQuery("Select localidad from spoter.usuarios user where user.idUsuarios ="+ 1 +";");
		data.next();
		localidad = data.getString(1);
		
		data = command.executeQuery("SELECT evento_id_Evento FROM spoter.usuarios_has_evento where usuarios_idUsuarios = "+id+"; ");
		while(data.next()) participa.add(data.getInt(1));
		
		data = command.executeQuery("SELECT evento_id_Evento FROM spoter.usuarios_has_evento where usuarios_idUsuarios = "+id+"; "); // sentencias DUPLICADAS MIRAR!!!!
		while(data.next()) participa.add(data.getInt(1));
		existente = true;
	}
	
	//Daniel Cuevas
	public Persona(Statement command, String email) throws SQLException {
		super(command, email);
		if(getAdmin()) {
			throw new RuntimeException("Es admin");
		}
		ResultSet data = command.executeQuery("Select * from spoter.usuarios user where user.email = '"+ email +"';");
		data.next();
		id = data.getInt(1);
		localidad = data.getString(6);
		
		data = command.executeQuery("SELECT evento_id_Evento FROM spoter.usuarios_has_evento where usuarios_idUsuarios = "+ id +"; ");
		while(data.next()) participa.add(data.getInt(1));
		
		data = command.executeQuery("SELECT deporte_idDeporte FROM spoter.usuarios_has_deporte where usuarios_idUsuarios = "+ id +"; ");
		while(data.next()) practica.add(data.getInt(1));
		existente = true;
	}

	public int getId() {
		return id;
	}

	public String getLocalidad() {
		return localidad;
	}
	
	public void modificarPerfil(String nombre, String localidad, String email, String password, String [] deportes) throws SQLException {
		if(!existente) throw new RuntimeException("Un usuario que no existe no se modifica");
		command.execute("UPDATE `spoter`.`usuarios` SET `nombre` = '"+nombre+"', `localidad` = '"+localidad+"',`password` = '"+ password +"' WHERE (`email` = '"+email+"');");
		
		this.nombre = nombre;
		this.localidad = localidad;
		this.password = password;
		
		command.execute("DELETE from `spoter`.`usuarios_has_deporte` WHERE (`usuarios_idUsuarios` = " +id+ ");"); // NO ME BORRES POR FAVOR :o
		
		Deporte deporte = new Deporte(command);
		for(String nombre1 : deportes) {
			this.meterDeporte(deporte.obtenerIdDeporte(nombre1));
		}
		
	}
	
	public void crearPerfil(String nombre,String localidad,String email,String password, String [] deportes) throws SQLException {
		if(existente) throw new RuntimeException("Un usuario que existe no puede crear otro usuario");
		
		command.execute("INSERT INTO `spoter`.`usuarios` (`nombre`, `email`, `password`, `admin`, `localidad`) VALUES "
				+ "('"+ nombre +"', '"+ email +"', '"+ password +"', '"+ 0 +"', '"+ localidad +"');");
		
		this.localidad = localidad;this.nombre = nombre;this.email = email;this.password = password;
		ResultSet data = command.executeQuery("Select idUsuarios from spoter.usuarios order by idUsuarios desc;");
		data.next();
		id = data.getInt(1);
		
		
		existente = !existente;
		
		// Javier: Aniade deportes al perfil recibiendo como parámetro un array
		Deporte deporte = new Deporte(command);
		for(String nombre1 : deportes) {
			this.meterDeporte(deporte.obtenerIdDeporte(nombre1));
		}
		
		System.out.println("/nPERFIL CREADO");
		 
	}
	
	public void meterDeporte(int deporte) throws SQLException {
		// TODO Auto-generated method stub
		if(!existente) throw new RuntimeException("Un usuario que no existe no puede tener ni a�adir deportes");
		command.execute("INSERT INTO `spoter`.`usuarios_has_deporte` (`usuarios_idUsuarios`, `deporte_idDeporte`) VALUES ('"+getId()+"', '"+deporte+"');");
		practica.add(deporte);
	}
	
	// Daniel Cuevas: obtener una lista con los nombres de los deportes que practica un usuario. Necesario en el perfil de usuario
	public List<String> getListDeporte() throws SQLException {
		ResultSet data;
		List<String> listDeportes = new ArrayList<String>();
		data = command.executeQuery("SELECT nombre FROM spoter.deporte D "
				+ "INNER JOIN spoter.usuarios_has_deporte UD ON UD.usuarios_idUsuarios = "+ this.id +" && D.idDeporte = UD.deporte_idDeporte;");
		while(data.next()) {
				listDeportes.add(data.getString(1));
		}
		
		return listDeportes;
	}
	
	
	// Javier: Comprueba si la contraseña recibida como parametro es correcta
	public boolean confirmarContrasenia(String contrasenia) throws SQLException {
		boolean correcta = false;
		ResultSet data = command.executeQuery("Select password from spoter.usuarios user where user.idUsuarios ="+ id +";");
		data.next();
		System.out.println(data.getString(1).toString());
		if(contrasenia.compareTo(data.getString(1).toString()) == 0) {
			correcta = true;
		}
		return correcta;
	}
	
	// Javier: Devuelve el atributo existente
	public boolean getExistente() {
		return existente;
	}
}