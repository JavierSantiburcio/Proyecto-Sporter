//Autor: Rayan Chaves Da Silva
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import vistas.VentanaPrincipal;
import modelo.Persona;

public class CtrlVentanaPrincipal implements ActionListener{
	private VentanaPrincipal ventana;
	private Persona persona;
	
	public CtrlVentanaPrincipal(VentanaPrincipal ventana, Persona persona){
		super();
		this.ventana = ventana;
		this.persona = persona;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		

		switch(comando) {
			case "Cerrar Sesion":
				System.out.println("Sesion Cerrada");
				ventana.cerrarSesion();
				break;
			case "Crear Evento":
				ventana.crearEvento(persona);
				break;
			case "Perfil Usuario":
				try {
					ventana.verVentanaUsuario(persona);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Buscar Evento":
				try{
					ventana.llenarTablaBuscar();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
		}
	}
}
