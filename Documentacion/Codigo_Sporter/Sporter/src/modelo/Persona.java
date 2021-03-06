 package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persona extends Usuario{
	
	private int id;
	private String localidad, url;
	private boolean existente;
	public ArrayList<Integer> participa = new ArrayList<Integer>();
	public ArrayList<Integer> practica = new ArrayList<Integer>();
	
	public Persona(Statement command) {
		super(command);
		id = -1;
		localidad = null;
		participa = null;
		practica = null;
		url = null;
		existente = false;
	}
	
	public Persona(Statement command, int id) throws SQLException {
		super(command, id);
		this.id = id;
		
		if(getAdmin()) {
			throw new RuntimeException("Es admin");
		}
		ResultSet data = command.executeQuery("Select localidad, url from spoter.usuarios user where user.idUsuarios ="+ id +";");
		data.next();
		localidad = data.getString(1);
		url = data.getString(2);
		data = command.executeQuery("SELECT evento_id_Evento FROM spoter.usuarios_has_evento where usuarios_idUsuarios = "+id+"; ");
		while(data.next()) participa.add(data.getInt(1));
		
		data = command.executeQuery("SELECT deporte_idDeporte FROM spoter.usuarios_has_deporte where usuarios_idUsuarios = "+ id +"; ");
		while(data.next()) practica.add(data.getInt(1));
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
		url = data.getString(7);
		
		data = command.executeQuery("SELECT evento_id_Evento FROM spoter.usuarios_has_evento where usuarios_idUsuarios = "+ id +"; ");
		while(data.next()) participa.add(data.getInt(1));
		
		data = command.executeQuery("SELECT deporte_idDeporte FROM spoter.usuarios_has_deporte where usuarios_idUsuarios = "+ id +"; ");
		while(data.next()) practica.add(data.getInt(1));
		existente = true;
	}
	
	//Daniel Cuevas
	public Persona (Statement command, int id, String nombre, String email, String pass, boolean admin, String localidad) {
		super(command,nombre,email,pass,admin);
		this.id = id;
		this.localidad = localidad;
		this.existente = true;
		this.url = null;
		participa = null;
		practica = null;
	}

	public int getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}

	public String getLocalidad() {
		return localidad;
	}
	
	public void modificarPerfil(String nombre, String localidad, String email, String password, String [] deportes, String url) throws SQLException {
		if(!existente) throw new RuntimeException("Un usuario que no existe no se modifica");
		command.execute("UPDATE `spoter`.`usuarios` SET `nombre` = '"+nombre+"', `localidad` = '"+localidad+"',`password` = '"+ password +"' , `url` = '" + url +"'  WHERE (`email` = '"+email+"');");
		
		this.nombre = nombre;
		this.localidad = localidad;
		this.password = password;
		this.url = url;
		
		command.execute("DELETE from `spoter`.`usuarios_has_deporte` WHERE (`usuarios_idUsuarios` = " +id+ ");"); // NO ME BORRES POR FAVOR :o
		
		Deporte deporte = new Deporte(command);
		for(String nombre1 : deportes) {
			this.meterDeporte(deporte.obtenerIdDeporte(nombre1));
		}
		
	}
	
	public void crearPerfil(String nombre,String localidad,String email,String password, String [] deportes, String url) throws SQLException {
//		if(existente) throw new RuntimeException("Un usuario que existe no puede crear otro usuario");
		if(estaEmail(email)) throw new SQLException("Email ya registrado en la base de datos");
		
		//Daniel : Resetear contador al numero de filas
		ResultSet numFilas = command.executeQuery("SELECT idUsuarios FROM spoter.usuarios");
		int cont = 1;
		while (numFilas.next()) {
			cont++;
		}
		command.execute("ALTER TABLE spoter.usuarios AUTO_INCREMENT=" + cont + ";");
		
		command.execute("INSERT INTO `spoter`.`usuarios` (`nombre`, `email`, `password`, `admin`, `localidad` , `url`) VALUES "
				+ "('"+ nombre +"', '"+ email +"', '"+ password +"', '"+ 0 +"', '"+ localidad +"' , '" + url + "');");
		
		this.localidad = localidad;this.nombre = nombre;this.email = email;this.password = password;
		ResultSet data = command.executeQuery("Select idUsuarios from spoter.usuarios order by idUsuarios desc;");
		data.next();
		id = data.getInt(1);
		this.url = url;
		
		
		existente = !existente;
		
		// Javier: Aniade deportes al perfil recibiendo como parámetro un array
		Deporte deporte = new Deporte(command);
		for(String nombre1 : deportes) {
			this.meterDeporte(deporte.obtenerIdDeporte(nombre1));
		}
		 
	}
	
	private boolean estaEmail(String email) throws SQLException {
		ResultSet data;
		data = command.executeQuery("SELECT nombre from spoter.usuarios where email = '" + email + "';");
		
		return data.next();
	}
	
	// Jose Luis: Obtener una lista de todos las personas(usuarios de BD) del sistea
	public List<Persona> getAllPersonas() throws SQLException{
		List<Persona> listPersona = new ArrayList<Persona>();
		ResultSet data;
		data = command.executeQuery("SELECT * FROM spoter.usuarios WHERE admin = 0;");
		while(data.next()) {
			Persona persona = new Persona(command, data.getInt(1),data.getString(2), data.getString(3),data.getString(4),data.getBoolean(5),data.getString(6));
			listPersona.add(persona);
		}
		
		return listPersona;
	}

	public void meterDeporte(int deporte) throws SQLException {
		// TODO Auto-generated method stub
		if(!existente) throw new RuntimeException("Un usuario que no existe no puede tener ni a�adir deportes");
		command.execute("INSERT INTO `spoter`.`usuarios_has_deporte` (`usuarios_idUsuarios`, `deporte_idDeporte`) VALUES ('"+getId()+"', '"+deporte+"');");
		practica = new ArrayList<Integer>();
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