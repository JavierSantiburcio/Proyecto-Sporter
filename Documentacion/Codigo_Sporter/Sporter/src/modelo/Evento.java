package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Evento {
	private int id;
	private String ubicacion;
	private Integer numeroParticipantes;
	private String fecha;
	private int deporte;
	private int organiza;

	private Statement command;

	public Evento(Statement command) {
		id = -1;
		ubicacion = null;
		numeroParticipantes = -1;
		fecha = null;
		deporte = -1;
		this.command = command;
	}

	public Evento(Statement command, int id) throws SQLException {
		this.command = command;
		this.id = id;
		ResultSet data = command.executeQuery("SELECT * FROM spoter.evento even where even.id_Evento = " + id + ";");
		data.next();
		deporte = data.getInt(6);
		ubicacion = data.getString(2);
		numeroParticipantes = data.getInt(3);
		fecha = data.getString(4);

		organiza = data.getInt(5);
	}
	
	// Daniel
	public Evento(Statement command, int idEvento, String ubicacion, int num, String fecha, int creador, int deporte)throws SQLException {
		this.command = command;
		this.id = idEvento;
		this.ubicacion = ubicacion;
		this.numeroParticipantes = num;
		this.fecha = fecha;
		this.organiza = creador;
		this.deporte = deporte;
	}

	// Setters y getters
	public int getId() {
		return id;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	
	public int getOrganiza() {
		return organiza;
	}

	public void setUbicacion(String ubicacion) throws SQLException {
		command.execute(
				"UPDATE `spoter`.`evento` SET `ubicacion` = '" + ubicacion + "' WHERE (`id_Evento` = '" + id + "');");
		this.ubicacion = ubicacion;

	}

	public Integer getNumeroParticipantes() {
		return numeroParticipantes;
	}

	public void setNumeroParticipantes(Integer numeroParticipantes) throws SQLException {
		command.execute("UPDATE `spoter`.`evento` SET `numParticipantesAct` = '" + numeroParticipantes
				+ "' WHERE (`id_Evento` = '" + id + "');");
		this.numeroParticipantes = numeroParticipantes;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) throws SQLException {
		command.execute("UPDATE `spoter`.`evento` SET `fecha` = '" + fecha + "' WHERE (`id_Evento` = '" + id + "');");
		this.fecha = fecha;
	}

	public int getDeporte() {
		return deporte;
	}

	public void setDeporte(int deporte) throws SQLException {
		command.execute(
				"UPDATE `spoter`.`evento` SET `deporte` = '" + deporte + "' WHERE (`id_Evento` = '" + id + "');");
		this.deporte = deporte;
	}
	// Final setters getters

	// En el diagrama ponia devolver un evento, pero he dejado esta clase como la representacion de ese evento
	public void crearEvento(Persona persona, int deporte, String ubicacion, String fecha, int numeroParcipantes) throws SQLException {
		// Daniel: REINICIO EL CONTADOR DE CAMPO IDENTITY AL MAXIMO DE FILAS
		if(persona == null) {
			System.out.println("NULO persona");
		}else if(ubicacion == null) {
			System.out.println("NULO ubicacion");
		}else if(fecha == null){
			System.out.println("nulo fecha");
		}
		
		ResultSet numFilas = command.executeQuery("SELECT id_Evento FROM spoter.evento");
		int cont = 1;
		while (numFilas.next()) {
			cont++;
		}
		command.execute("ALTER TABLE spoter.evento AUTO_INCREMENT=" + cont + ";");

		command.execute("INSERT INTO `spoter`.`evento` (`ubicacion`, `numParticipantesAct`, `fecha`, `Creador`, `Deporte`)"
						+ " VALUES ('" + ubicacion + "', '" + numeroParcipantes + "', '" + fecha + "', '"
						+ persona.getId() + "', '" + deporte + "');");
		
		this.ubicacion = ubicacion;
		this.numeroParticipantes = numeroParcipantes;
		this.deporte = deporte;

		ResultSet data = command
				.executeQuery("Select id_Evento,creador,fecha from spoter.evento order by id_Evento desc;");
		data.next();
		id = data.getInt(1);
		organiza = data.getInt(2);
		this.fecha = data.getString(3);

		//Daniel: Cuando se crea un evento, tambien hay que a�adirlo a la tabla intermedia usuario_has_evento.
		command.execute("INSERT INTO `spoter`.`usuarios_has_evento` (`usuarios_idUsuarios`, `evento_id_Evento`) VALUES ('"
						+ persona.getId() + "', '" + id + "');");
	}
	
	//Poner deporte -1 si no se quiere modificar
	public void modificar_evento(int usuario, String ubicacion, String fecha, int deporte) throws SQLException {
		if (organiza == usuario) {
			if (ubicacion != null)
				setUbicacion(ubicacion);
			if (fecha != null)
				setFecha(fecha);
			if (deporte != -1)
				setDeporte(deporte);
		}
	}

	public void borrarevento(Persona persona,int idEvento) throws SQLException {
		//No compruebo si la persona es el creador porque ya lo controlo al generar el boton cancelar en la tabla
		// para los eventos donde el usuario es el creador. Sentencia 262 en la clase VentanaPerfilUsuario
		command.execute("DELETE FROM spoter.evento WHERE id_Evento = " + idEvento + ";");
		command.execute("DELETE FROM spoter.usuarios_has_evento WHERE evento_id_Evento = " + idEvento + ";");
	}

	public void unirse(Persona persona, int id) throws SQLException {
		command.execute(
				"INSERT INTO `spoter`.`usuarios_has_evento` (`usuarios_idUsuarios`, `evento_id_Evento`) VALUES ('"
						+ persona.getId() + "', '" + id + "');");
	}

	public void dejarEvento(Persona persona, int idEvento) throws SQLException {
		command.execute("DELETE FROM `spoter`.`usuarios_has_evento` WHERE (`usuarios_idUsuarios` = '" + persona.getId()
				+ "') and (`evento_id_Evento` = '" + idEvento + "');");
	}

	// Daniel: Metodo que devuelve una lista de los eventos de un usuario, donde es el propietario o participa en el.
	public List<Evento> getListEventos(int idUsuario) throws SQLException {

		List<Evento> listEventos = new ArrayList<Evento>();
		ResultSet data = command.executeQuery("((SELECT * FROM spoter.evento E WHERE E.Creador =" + idUsuario + ")"
				+ " UNION "
				+ "(SELECT id_Evento, ubicacion,numParticipantesAct,fecha,Creador,Deporte FROM spoter.evento E INNER JOIN spoter.usuarios_has_evento UE ON UE.evento_id_Evento"
				+ " IN " + "(SELECT id_Evento FROM spoter.evento EV WHERE EV.Creador !=" + idUsuario + ")" + " WHERE "
				+ "E.id_Evento = UE.evento_id_Evento" + " AND " + "UE.usuarios_idUsuarios =" + idUsuario + "))"
				+ " ORDER BY fecha ASC;");
		while (data.next()) {
			Evento evento = new Evento(command, data.getInt(1), data.getString(2), data.getInt(3), data.getString(4),
					data.getInt(5), data.getInt(6));
			listEventos.add(evento);
		}

		return listEventos;
	}
	// Rayan: Metodo que devuelve una lista de los eventos en base a la ubicacion y el deporte elegido y no incluye al usuario
	public List<Evento> getListEventos(String Ubicacion, int idUsuario, int iddeporte) throws SQLException {

		List<Evento> listEventos = new ArrayList<Evento>();
		ResultSet data = command.executeQuery("((SELECT * FROM spoter.evento E "
				+ "WHERE E.ubicacion IN ('"+Ubicacion+"') "
				+ "AND E.Deporte =" + iddeporte+ " "
				+ "AND E.Creador !=" + idUsuario + " "
				+ "AND E.id_Evento NOT IN (SELECT evento_id_Evento FROM spoter.usuarios_has_evento N WHERE N.usuarios_idUsuarios =" + idUsuario + " ))"
				+ " UNION "
				+ "(SELECT id_Evento, ubicacion,numParticipantesAct,fecha,Creador,Deporte FROM spoter.evento E "
				+ "WHERE E.ubicacion IN ('"+Ubicacion+"') "
				+ "AND E.Deporte =" + iddeporte+ " "
				+ "AND E.Creador !=" + idUsuario + " "
				+ "AND E.id_Evento NOT IN (SELECT evento_id_Evento FROM spoter.usuarios_has_evento N WHERE N.usuarios_idUsuarios =" + idUsuario + " )))"
				+ " ORDER BY fecha ASC;");
		while (data.next()) {
			Evento evento = new Evento(command, data.getInt(1), data.getString(2), data.getInt(3), data.getString(4),
					data.getInt(5), data.getInt(6));
			listEventos.add(evento);
		}
		return listEventos;
	}

	//Daniel: Metodo que devuleve el String del propietario del evento. Utilizadoal cargar la tabla del perfil de usuario
	public String getNombreUsuario(int idUsuario, int idEvento) throws SQLException {

		String nombreUsuario;
		ResultSet data = command.executeQuery("SELECT nombre FROM spoter.usuarios U "
				+ "INNER JOIN  spoter.evento E ON U.idUsuarios = " + idUsuario + " && E.id_Evento =" + idEvento + ";");
		data.next();
		nombreUsuario = data.getString(1);
		return nombreUsuario;
	}

	//Daniel: Metodo que devuleve el String del deporte del evento.Utilizado alcargar la tabla del perfil de usuario
	public String getNombreDeporte(int idDeporte, int idEvento) throws SQLException {

		String nombreDeporte;
		ResultSet data = command.executeQuery("SELECT nombre FROM spoter.deporte D "
				+ "INNER JOIN  spoter.evento E ON D.idDeporte = " + idDeporte + " && E.id_Evento = " + idEvento + ";");
		data.next();
		nombreDeporte = data.getString(1);
		return nombreDeporte;
	}

	//Daniel: Metodo que te devuelve el numero de participantes activos en un evento
	public int getNumParticipantesActivos(int idEvento) throws SQLException {
		int num;
		ResultSet data = command.executeQuery(
				"SELECT COUNT(*) FROM spoter.usuarios_has_evento WHERE evento_id_Evento =" + idEvento + ";");
		data.next();
		num = data.getInt(1);
		return num;
	}
	
	//Daniel: obtener el id del evento dado sus valores
		public int getIdEventoPropio(String fechaHora, int idCreador, int idDeporte, String nombreUbicacion) throws SQLException {
			int idEvento;
			ResultSet data = command.executeQuery("SELECT id_Evento FROM spoter.evento E WHERE E.Creador = " + idCreador + 
					" AND E.ubicacion = '" + nombreUbicacion + "' AND E.fecha = '" + fechaHora + "' AND E.Deporte = " + idDeporte + ";");
			data.next();
			idEvento = data.getInt(1);
			return idEvento;
		}
	

	//Daniel: obtener el id del evento dado sus valores
	public int getIdeventoUnido(String fechaHora, String nombreCreador, int idDeporte, String nombreUbicacion) throws SQLException {
		int idEvento;
		ResultSet data = command.executeQuery("SELECT id_Evento " + 
				"FROM spoter.evento E INNER JOIN spoter.usuarios U ON E.Creador = U.idUsuarios " + 
				"AND U.nombre = '" + nombreCreador + "' " + 
				"AND E.ubicacion = '" + nombreUbicacion  +"' " + 
				"AND E.fecha = '" + fechaHora + "' " + 
				"AND Deporte = " + idDeporte + ";");
		data.next();
		idEvento = data.getInt(1);
		return idEvento;
	}
	
	//Daniel: metodo para obtener las fechas de los eventos creados o unidos
	public List<String> getFechaTodosEventos(int idUsuario) throws SQLException{
		List<String> listFechaEventos = new ArrayList<String>();
		ResultSet data = command.executeQuery("((SELECT fecha FROM spoter.evento E WHERE E.Creador =" + idUsuario + ")"
				+ " UNION "
				+ "(SELECT fecha FROM spoter.evento E INNER JOIN spoter.usuarios_has_evento UE ON UE.evento_id_Evento"
				+ " IN " + "(SELECT id_Evento FROM spoter.evento EV WHERE EV.Creador !=" + idUsuario + ")" + " WHERE "
				+ "E.id_Evento = UE.evento_id_Evento" + " AND " + "UE.usuarios_idUsuarios =" + idUsuario + "));");
		while (data.next()) {
			listFechaEventos.add(data.getString(1));
		}
		return listFechaEventos;
	}
	
	//Daniel: metodo para obtener las fechas de los eventos creados
	public List<String> getFechaEventosCreados(int idUsuario) throws SQLException{
		List<String> listFechaEventos = new ArrayList<String>();
		ResultSet data = command.executeQuery("SELECT fecha FROM spoter.evento E WHERE E.Creador =" + idUsuario + ";");
		while (data.next()) {
			listFechaEventos.add(data.getString(1));
		}
		return listFechaEventos;
	}
	
	//Daniel: metodo para obtener las fechas de los eventos unidos
		public List<String> getFechaEventosUnidos(int idUsuario) throws SQLException{
			List<String> listFechaEventos = new ArrayList<String>();
			ResultSet data = command.executeQuery("SELECT fecha FROM spoter.evento E INNER JOIN spoter.usuarios_has_evento UE ON UE.evento_id_Evento"
					+ " IN " + "(SELECT id_Evento FROM spoter.evento EV WHERE EV.Creador !=" + idUsuario + ")" + " WHERE "
					+ "E.id_Evento = UE.evento_id_Evento" + " AND " + "UE.usuarios_idUsuarios =" + idUsuario + ";");
			while (data.next()) {
				listFechaEventos.add(data.getString(1));
			}
			return listFechaEventos;
		}

}