package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.*;

import conexion.Conexion;
import modelo.Administrador;
import modelo.Evento;
import modelo.Persona;

public class Pruebas {
	static Statement command;
	
	@BeforeAll
	public static void init() throws SQLException {
		
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		
		command.execute("SET FOREIGN_KEY_CHECKS = 0;");
		command.execute("TRUNCATE TABLE spoter.evento;");
		command.execute("TRUNCATE TABLE spoter.usuarios;");
		command.execute("TRUNCATE TABLE spoter.usuarios_has_deporte;");
		command.execute("TRUNCATE TABLE spoter.usuarios_has_evento;");
		command.execute("SET FOREIGN_KEY_CHECKS = 1;");
	}
	
	
	@Test
	public void CrearPerfilyModificar() throws SQLException {
		
//	 	CrearPerfil:Se aniade un usuario a la base de datos
		
		int numUsuarios;
		ResultSet res = command.executeQuery("select count(*) from spoter.usuarios;");
		res.next();
		numUsuarios = res.getInt(1);
		
		Persona persona = new Persona(command);
		
		res = command.executeQuery("select nombre from spoter.deporte where idDeporte = 1;");
		res.next();
		String[] deportes = {res.getString(1)};
		
		persona.crearPerfil("a", "a", "a", "a", deportes);
		
		res = command.executeQuery("select count(*) from spoter.usuarios;");
		res.next();
		
		//Se he sumado uno al numero de usuarios
		assertEquals(res.getInt(1),numUsuarios+1);
		
		numUsuarios++;
		
		res = command.executeQuery("select * from spoter.usuarios where spoter.usuarios.idUsuarios = "+persona.getId()+";");
		res.next();
		
		//Cada uno de los datos se guardan correctamente
		assertEquals(res.getString(2),persona.getNombre());
		assertEquals(res.getString(3),persona.getEmail());
		assertEquals(res.getString(4), persona.getPassword());
		assertEquals(res.getBoolean(5), persona.getAdmin());
		assertEquals(res.getString(6), persona.getLocalidad());
		
		Persona persona2 = new Persona(command);
		persona2.crearPerfil("b", "b", "b", "b", deportes);
		
		res = command.executeQuery("select count(*) from spoter.usuarios;");
		res.next();
		
		//Se aumenta en uno si le metemos un segundo usuario el numero de usuarios
		assertEquals(res.getInt(1),numUsuarios+1);
		numUsuarios++;
		
		
	//ModificarPerfil:	Se modifican los datos dados.
		res = command.executeQuery("select nombre from spoter.deporte where idDeporte = 2;");
		res.next();
		String [] deportes2 = {res.getString(1)};
		persona2 = new Persona(command,persona2.getId());
		persona2.modificarPerfil("c", "d","e","f",deportes2);
		
		res = command.executeQuery("select nombre,localidad from spoter.usuarios where spoter.usuarios.idUsuarios = "+persona2.getId()+";");
		res.next();
		
		//Comprobamos que los cambios se han ejecutado bien
		assertEquals(res.getString(1), "c");
		assertEquals(res.getString(2), "d");
		
	//Se Aniade deporte correctamente
		persona2.meterDeporte(1);
		
		res = command.executeQuery("select deporte_idDeporte from spoter.usuarios_has_deporte where spoter.usuarios_has_deporte.usuarios_idUsuarios = "+persona2.getId()+";");
		res.next();
		
		assertEquals(res.getInt(1), 1);
		
		command.execute("SET FOREIGN_KEY_CHECKS = 0;");
		command.execute("TRUNCATE TABLE spoter.usuarios;");
		command.execute("SET FOREIGN_KEY_CHECKS = 1;");
	}
	
	
	@Test
	public void FuncionesAdmin() throws SQLException {
		int id = 0;
		//Creamos un administrador
		command.execute("INSERT INTO `spoter`.`usuarios` (`nombre`, `email`, `password`, `admin`, `localidad`) VALUES ('a', 'a', 'a', '1', 'a');");
		ResultSet res = command.executeQuery("SELECT idUsuarios FROM spoter.usuarios order by idUsuarios desc;");
		res.next();
		
		id = res.getInt(1);
		
		Administrador admin = new Administrador(command, id);
		
		//Creamos un usuario normal
		command.execute("INSERT INTO `spoter`.`usuarios` (`nombre`, `email`, `password`, `admin`, `localidad`) VALUES ('b', 'b', 'b', '0', 'b');");
		Persona persona = new Persona(command,id+1);
		
		res = command.executeQuery("select count(*) from spoter.usuarios;");
		res.next();
		int num = res.getInt(1);
		
		admin.eliminarUsuario(persona);
		
		res = command.executeQuery("select count(*) from spoter.usuarios;");
		res.next();
		
		//Al borrar un usuario hay una persona menos en la base de datos.
		assertEquals(res.getInt(1), num-1);
		
		command.execute("INSERT INTO `spoter`.`evento` (`ubicacion`, `numParticipantesAct`, `fecha`, `Creador`, `Deporte`) VALUES ('a', '10', '11/11/11', '1', '1');");
		
		res = command.executeQuery("SELECT id_Evento FROM spoter.evento order by id_Evento desc;");
		res.next();
		id = res.getInt(1);
		
		res = command.executeQuery("select count(*) from spoter.evento;");
		res.next();
		num = res.getInt(1);
		
		Evento evento = new Evento(command,id);
		
		admin.eliminarEvento(evento);
		
		res = command.executeQuery("select count(*) from spoter.evento;");
		res.next();
		//Hay un elemento menos al borrar el evento.
		assertEquals(res.getInt(1), num-1);
		command.execute("SET FOREIGN_KEY_CHECKS = 0;");
		command.execute("TRUNCATE TABLE spoter.usuarios;");
		command.execute("SET FOREIGN_KEY_CHECKS = 1;");
	}
	
	@Test
	public void EventoGuardaBienEventos() throws SQLException {
		command.execute("INSERT INTO `spoter`.`usuarios` (`nombre`, `email`, `password`, `admin`, `localidad`) VALUES ('a', 'a', 'a', '0', 'a');");
		int id = 0;
		ResultSet res = command.executeQuery("SELECT idUsuarios FROM spoter.usuarios orde by idUsuarios desc;");
		
		res.next();
		id = res.getInt(1);
		Persona persona  = new Persona(command, id);
		
		command.execute("INSERT INTO `spoter`.`evento` (`ubicacion`, `numParticipantesAct`, `fecha`, `Creador`, `Deporte`) VALUES ('a', '10', '11/11/11', '"+id+"', '1');");
		
		
		res = command.executeQuery("SELECT id_Evento FROM spoter.evento order by id_Evento desc;");
		res.next();
		
		id = res.getInt(1);
		Evento evento = new Evento(command, id);
		
		res = command.executeQuery("Select * from spoter.evento where id_Evento = "+id+"");
		res.next();
		
		
		//Comprobamos que cige bien los datos
		assertEquals(res.getInt(1), evento.getId());
		assertEquals(res.getString(2), evento.getUbicacion());
		assertEquals(res.getInt(3), evento.getNumeroParticipantes());
		assertEquals(res.getString(4), evento.getFecha());
		assertEquals(res.getInt(6), evento.getDeporte());
		
		evento= new Evento(command);
		evento.crearEvento(persona, 1, "k", "11/11/11", 10);
		
		res = command.executeQuery("Select * from spoter.evento where id_Evento = "+ evento.getId() +"");
		res.next();
		
		//Comprobamos que mete bien los datos en la vase de datos
		assertEquals(res.getInt(1), evento.getId());
		assertEquals(res.getString(2), evento.getUbicacion());
		assertEquals(res.getInt(3), evento.getNumeroParticipantes());
		assertEquals(res.getString(4), evento.getFecha());
		assertEquals(res.getInt(6), evento.getDeporte());
		
		command.execute("SET FOREIGN_KEY_CHECKS = 0;");
		command.execute("TRUNCATE TABLE spoter.usuarios;");
		command.execute("TRUNCATE TABLE spoter.evento;");
		command.execute("SET FOREIGN_KEY_CHECKS = 1;");
		
	}
	
	@Test
	public void BorrarEvento() throws SQLException {
		command.execute("INSERT INTO `spoter`.`usuarios` (`nombre`, `email`, `password`, `admin`, `localidad`) VALUES ('a', 'a', 'a', '0', 'a');");
		
		int id;
		int Creador;
		ResultSet res = command.executeQuery("SELECT idUsuarios FROM spoter.usuarios order by idUsuarios desc;");
		res.next();
		Creador = res.getInt(1);
		
		command.execute("INSERT INTO `spoter`.`evento` (`ubicacion`, `numParticipantesAct`, `fecha`, `Creador`, `Deporte`) VALUES ('b', '10', '11/11/11', '"+Creador+"', '1');");
		
		res = command.executeQuery("SELECT id_Evento FROM spoter.evento order by id_Evento desc;");
		res.next();
		id = res.getInt(1);
		
		int numero;
		res = command.executeQuery("SELECT Count(*) FROM spoter.evento;");
		res.next();
		numero = res.getInt(1);
		
		Evento evento = new Evento(command, id);
		Persona persona = new Persona(command,Creador);
		
		//Borrar evento
		evento.borrarevento(persona); // Daniel : he modificado un poco ese metodo, ahora tiene tambien el idEvento
		
		res = command.executeQuery("SELECT Count(*) FROM spoter.evento;");
		res.next();
		
		assertEquals(res.getInt(1), numero-1);
		
		command.execute("SET FOREIGN_KEY_CHECKS = 0;");
		command.execute("TRUNCATE TABLE spoter.usuarios;");
		command.execute("TRUNCATE TABLE spoter.evento;");
		command.execute("SET FOREIGN_KEY_CHECKS = 1;");
	}
	
	@Test
	public void UnirseyDejarEvento() throws SQLException {
		command.execute("INSERT INTO `spoter`.`usuarios` (`nombre`, `email`, `password`, `admin`, `localidad`) VALUES ('a', 'a', 'a', '0', 'a');");
		
		int id;
		int Creador;
		ResultSet res = command.executeQuery("SELECT idUsuarios FROM spoter.usuarios order by idUsuarios desc;");
		res.next();
		Creador = res.getInt(1);
		
		command.execute("INSERT INTO `spoter`.`evento` (`ubicacion`, `numParticipantesAct`, `fecha`, `Creador`, `Deporte`) VALUES ('b', '10', '11/11/11', '"+Creador+"', '1');");
		
		res = command.executeQuery("SELECT id_Evento FROM spoter.evento order by id_Evento desc;");
		res.next();
		id = res.getInt(1);
		
		Evento evento = new Evento(command, id);
		Persona persona = new Persona(command,Creador);
		
		res = command.executeQuery("SELECT Count(*) FROM spoter.usuario_has_evento where evento_id_Evento = "+id+";");
		res.next();
		int numero = res.getInt(1);
		
		evento.unirse(persona);
		
		res = command.executeQuery("SELECT Count(*) FROM spoter.usuario_has_evento where evento_id_Evento = "+id+";");
		res.next();
		
		//Se ha aumentado en uno el numero de participantes en este evento
		assertEquals(res.getInt(1), numero + 1 );
		numero++;
		
		evento.dejarEvento(persona);// Daniel : he modificado un poco ese metodo, ahora tiene tambien el idEvento
		
		res = command.executeQuery("SELECT Count(*) FROM spoter.usuario_has_evento where evento_id_Evento = "+id+";");
		res.next();
		
		assertEquals(res.getInt(1), numero - 1);
		
		command.execute("SET FOREIGN_KEY_CHECKS = 0;");
		command.execute("TRUNCATE TABLE spoter.usuarios;");
		command.execute("TRUNCATE TABLE spoter.evento;");
		command.execute("SET FOREIGN_KEY_CHECKS = 1;");
	}
}
