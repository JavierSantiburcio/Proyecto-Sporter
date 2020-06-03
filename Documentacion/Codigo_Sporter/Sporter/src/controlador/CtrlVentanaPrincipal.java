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
		
		if(comando.equals("Cerrar Sesion")) {
			System.out.println("Cerrar Sesion");
			ventana.cerrarSesion();
		}else if(comando.equals("Crear Evento")) {
			System.out.println("Crear Evento");
			ventana.crearEvento(persona);
		}
	}
}
