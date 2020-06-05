//Autor: Rayan Chaves Da Silva
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;

import vistas.VentanaPrincipal;

public class CtrlVentanaPrincipal implements ActionListener, MouseListener{
	private VentanaPrincipal ventana;
	
	public CtrlVentanaPrincipal(VentanaPrincipal ventana){
		super();
		this.ventana = ventana;
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
				ventana.crearEvento();
				break;
			case "Perfil Usuario":
				try {
					ventana.verVentanaUsuario();
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
				break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent evt) {
		
		int column = ventana.tablaEventos.getColumnModel().getColumnIndexAtX(evt.getX()); // posicion x de la columna cuando hacemos click
		int row = evt.getY() / ventana.tablaEventos.getRowHeight(); // fila
		
		if(row < ventana.tablaEventos.getRowCount() && row >= 0 && column < ventana.tablaEventos.getColumnCount() && column >= 0) { // dentro del rango de la tabla
			Object value = ventana.tablaEventos.getValueAt(row, column);
			if(value instanceof JButton) {
				((JButton) value).doClick();
				JButton boton = (JButton) value;
				if (boton.getName().equals("Unirse")) {
					try {
						ventana.Unirse();
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
