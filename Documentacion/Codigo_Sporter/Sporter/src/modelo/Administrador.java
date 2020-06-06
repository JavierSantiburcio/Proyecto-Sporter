package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Administrador extends Usuario{

	private String email;

	public Administrador(Statement command, int id) throws SQLException {
		super(command, id);
		email = getEmail();
		if(!getAdmin()) {
			throw new RuntimeException("No eres admin");
		}
	}

	public Administrador(Statement command, String email) throws SQLException {
		super(command, email);
		this.email = email;
		if(!getAdmin()) {
			throw new RuntimeException("No eres admin");
		}
	}
	public void eliminarUsuario(int idPersona) throws SQLException {

		System.out.println(idPersona);
		//ResultSet data = command.executeQuery("SELECT id_Evento FROM spoter.evento  WHERE Creador = "+idPersona+";");
		//data.next();
		//int idEvento = data.getInt(1);
		
		//Primero hacemos DELETE en las tablas intermedias
		//command.execute("DELETE FROM spoter.usuarios_has_evento WHERE usuarios_idUsuarios = " + idPersona + ";"); // elimino los eventos con los que esta relacionado
		//command.execute("DELETE FROM spoter.usuarios_has_evento WHERE evento_id_Evento = " + idEvento + ";"); // Los demas usuarios dejan de participar en los eventos del usuario eliminado
		command.execute("DELETE FROM spoter.usuarios WHERE (idUsuarios = " + idPersona + ")"); //  Elimino al usuario
		//command.execute("DELETE FROM spoter.evento WHERE Creador = " + idPersona + ";"); // Elimino sus eventos
	}
	public void eliminarEvento (Evento evento) throws SQLException{
		command.execute("DELETE FROM spoter.evento WHERE id_Evento = " + evento.getId() + ";");
		command.execute("DELETE FROM spoter.usuarios_has_evento WHERE evento_id_Evento = " + evento.getId() + ";");
	}

	public boolean confirmarContrasenia(String text) throws SQLException {
		boolean correcta = false;
		ResultSet data = command.executeQuery("Select password from spoter.usuarios user where email = '"+ email +"';");
		data.next();
		System.out.println(data.getString(1).toString());
		if(text.compareTo(data.getString(1).toString()) == 0) {
			correcta = true;
		}
		return correcta;
	}


}
