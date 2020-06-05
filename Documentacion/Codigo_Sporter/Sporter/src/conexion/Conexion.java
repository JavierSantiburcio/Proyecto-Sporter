package conexion;


import java.sql.*;

public class Conexion {
	
	private Statement command;
	
	public Conexion() throws SQLException {											// ESTO ES PARA QUE NO DE UNA EXCEPCION
		command = DriverManager.getConnection("jdbc:mysql://localhost:3306/spoter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "7544").createStatement();
	}
	public Statement getcommand() {
		return command;
	}
}
