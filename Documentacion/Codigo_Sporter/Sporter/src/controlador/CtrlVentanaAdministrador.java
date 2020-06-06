//Autor: Jose Luis Gonzalez Ramirez

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;

import vistas.VentanaAdministrador;
import modelo.Administrador;
import modelo.Persona;


public class CtrlVentanaAdministrador implements ActionListener, MouseListener{

	private VentanaAdministrador vista;

	public CtrlVentanaAdministrador(VentanaAdministrador v){
		super();
		vista = v;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch(comando) {
		case "SALIR":
			try {
				vista.cerrarVentana();
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			break;

		case "FILTRAR":
			try{
				vista.limpiarTabla();
				vista.filtrarEvento();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}

	}


	@Override
	public void mouseClicked(MouseEvent evt) {
		int columnEvento = vista.tablaEvento.getColumnModel().getColumnIndexAtX(evt.getX()); // posicion x de la columna cuando hacemos click
		int rowEvento = evt.getY() / vista.tablaEvento.getRowHeight(); // fila
		
		int columnUsuario = vista.tablaUsuario.getColumnModel().getColumnIndexAtX(evt.getX()); // posicion x de la columna cuando hacemos click
		int rowUsuario = evt.getY() / vista.tablaUsuario.getRowHeight(); // fila
		
		if(rowEvento < vista.tablaEvento.getRowCount() && rowEvento >= 0 && columnEvento < vista.tablaEvento.getColumnCount() && columnEvento >= 0) { // dentro del rango de la tabla
			Object value = vista.tablaEvento.getValueAt(rowEvento, columnEvento);
			if(value instanceof JButton) {
				((JButton) value).doClick();
				JButton boton = (JButton) value;
				if (boton.getName().equals("e")) {
					try {
						vista.borrarEvento();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			}
		}else if(rowUsuario < vista.tablaUsuario.getRowCount() && rowUsuario >= 0 && columnUsuario < vista.tablaUsuario.getColumnCount() && columnUsuario >= 0) {
			Object value = vista.tablaUsuario.getValueAt(rowUsuario, columnUsuario);
			if(value instanceof JButton) {
				((JButton) value).doClick();
				JButton boton = (JButton) value;
				if (boton.getName().equals("u")) {
					try {
						vista.borrarUsuario();
					} catch (Exception e) {
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
