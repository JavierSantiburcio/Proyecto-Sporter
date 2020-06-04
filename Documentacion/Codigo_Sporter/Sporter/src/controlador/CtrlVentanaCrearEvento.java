// Autor : Daniel Cuevas P�rez
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;

import vistas.VentanaFrmCrearEvento;

public class CtrlVentanaCrearEvento implements ActionListener, ItemListener {
	
	private VentanaFrmCrearEvento vista;
	
	public CtrlVentanaCrearEvento(VentanaFrmCrearEvento v){
		super();
		vista = v;
	}

	//Daniel: metodo que controla los eventos producidos por los botones del frm crear evento
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("CREAR") ) {
			try {
				vista.crearEvento();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (comando.contentEquals("CANCELAR")) {
			vista.cerrarVentana();
		}
		
	}

	//Daniel: metodo que controla los eventos del choise del frm crear evento
	public void itemStateChanged(ItemEvent e) {
		try {
			vista.cargarNumparticipantes();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
