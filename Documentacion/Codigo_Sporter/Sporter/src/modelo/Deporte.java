package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//He creado clase creardeporte, se puede eliminar, junto al primer constructor;

public class Deporte {
	private int id;
	private String nombre;
	private int numParticipante;
	private Statement command;
	
	public Deporte(Statement command) {
		this.command = command;
	}
	
	public Deporte(Statement command,int id ) throws SQLException {
		ResultSet data = command.executeQuery("Select* from spoter.deporte where spoter.deporte.idDeporte = "+id+";"); 
		data.next();
		nombre = data.getString(2);
		numParticipante = data.getInt(3);
		this.command = command;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumParticipante() {
		return numParticipante;
	}
	
	public void crearDeporte(String deporte, int numpart) throws SQLException {
		command.execute("INSERT INTO `spoter`.`deporte` (`nombre`, `numParticipantes`) VALUES ('"+deporte+"', '"+numpart+"');");
	}
	
	//Daniel: Obtener una lista con todos los nombres de deportes y usarla para cargar el choice de la vista crear evento deportivo
	public List<String> obtenerListaDeporte() throws SQLException {
		List<String> listDeporte = new ArrayList<String>();
		ResultSet data = command.executeQuery("SELECT nombre FROM spoter.deporte;");
		while(data.next()) {
			listDeporte.add(data.getString(1));
		}
		return listDeporte;
	}
	
	//Daniel: obtener el id de un deporte dado su nombre. Necesario para el choice de deporte del frm crear evento
	public int obtenerIdDeporte(String nombre) throws SQLException {
		int idNombre;
		
		ResultSet data = command.executeQuery("SELECT idDeporte FROM spoter.deporte Where nombre = '" + nombre +"';");
		data.next();
		idNombre = data.getInt(1);
		
		return idNombre;
	}
	
	//Daniel: obtener el numero de participante de un deporte dado su nombre. Necesario para completar el campo de texto numero participantes del Frm crear evento
	public int obtenerNumParticipanteDeporte(String nombre) throws SQLException {
		int num;
		ResultSet data = command.executeQuery("SELECT * FROM spoter.deporte Where nombre = '"+ nombre + "';");
		data.next();
		num = data.getInt(3);
		return num;
			
	}
	
}