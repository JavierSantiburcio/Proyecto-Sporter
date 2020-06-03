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
	public void eliminarUsuario(Persona persona) {
		try {
			command.execute("DELETE FROM `spoter`.`usuarios` WHERE (`idUsuarios` = '" + persona.getId() + "')");
		} catch (SQLException e) {
			throw new RuntimeException("No existe ese id de usuario");
		}
	}
	public void eliminarEvento (Evento evento) throws SQLException{
		command.execute("DELETE FROM `spoter`.`evento` WHERE (`id_Evento` = '"+evento.getId()+"');");
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
