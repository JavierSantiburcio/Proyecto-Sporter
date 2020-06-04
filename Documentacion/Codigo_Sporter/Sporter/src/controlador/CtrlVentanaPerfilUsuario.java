// Daniel Cuevas Perez - Controlador de la ventana de Perfil de Usuario
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;

import vistas.VentanaPerfilUsuario;

public class CtrlVentanaPerfilUsuario implements ActionListener, MouseListener{

	
	private VentanaPerfilUsuario vista;
	
	
	//Daniel: metodo que controla los eventos producidos por los botones de la ventana perfil usuario
	public CtrlVentanaPerfilUsuario(VentanaPerfilUsuario v){
		super();
		vista = v;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		switch (comando) {
		case "MODIFICAR":
			try {
				vista.abrirVentanaFormularioLogin();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;

		case "VOLVER":
			vista.volver();;
			break;
		}

	}


	@Override
	public void mouseClicked(MouseEvent evt) {
		
		int column = vista.table.getColumnModel().getColumnIndexAtX(evt.getX()); // posicion x de la columna cuando hacemos click
		int row = evt.getY() / vista.table.getRowHeight(); // fila
		
		if(row < vista.table.getRowCount() && row >= 0 && column < vista.table.getColumnCount() && column >= 0) { // dentro del rango de la tabla
			Object value = vista.table.getValueAt(row, column);
			if(value instanceof JButton) {
				((JButton) value).doClick();
				JButton boton = (JButton) value;

				if (boton.getName().equals("c")) {
					try {
						vista.cancelarEvento();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (boton.getName().equals("s")) {
					try {
						vista.salirEvento();
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
