//Autor: Jose Luis Gonzalez Ramirez

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;

import vistas.VentanaAdministrador;
import modelo.Administrador;
import modelo.Persona;


public class CtrlVentanaAdministrador implements ActionListener, MouseListener{

	
	private VentanaAdministrador vista;
	private Persona persona;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public CtrlVentanaAdministrador(VentanaAdministrador v){
		super();
		vista = v;
		this.persona = persona;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		switch(comando) {
		case "SALIR":
			System.out.println("Sesion Cerrada");
			vista.cerrarVentana();
			break;
		case "ELIMINARUSUARIO":
			
			vista.borrarUsuario(persona);
			break;
		case "PERFIL":
			try {
				vista.verPerfil();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "ELIMINAREVENTO":
			try{
				vista.borrarEvento();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		case "FILTRAR":
			try{
				vista.filtrarEvento();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
	}

	}


	@Override
	public void mouseClicked(MouseEvent evt) {
		int column = vista.tabla.getColumnModel().getColumnIndexAtX(evt.getX()); // posicion x de la columna cuando hacemos click
		int row = evt.getY() / vista.tabla.getRowHeight(); // fila
		
		if(row < vista.tabla.getRowCount() && row >= 0 && column < vista.tabla.getColumnCount() && column >= 0) { // dentro del rango de la tabla
			Object value = vista.tabla.getValueAt(row, column);
			if(value instanceof JButton) {
				((JButton) value).doClick();
				JButton boton = (JButton) value;

				if (boton.getName().equals("f")) {
					try {
						vista.filtrarEvento();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (boton.getName().equals("cs")) {
					vista.cerrarVentana();
				}
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
}
