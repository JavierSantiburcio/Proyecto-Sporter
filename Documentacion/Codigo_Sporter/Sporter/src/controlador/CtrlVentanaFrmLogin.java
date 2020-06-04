package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import vistas.VentanaFormularioLogin;

public class CtrlVentanaFrmLogin implements ActionListener, ItemListener{
	
	private VentanaFormularioLogin ventana;
	private boolean modificar;
	
	public CtrlVentanaFrmLogin(VentanaFormularioLogin ventana, boolean modificar) {
		super();
		this.ventana = ventana;
		this.modificar = modificar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.contentEquals("CREAR")) {
			if(!modificar) {
				try {
					ventana.crearPerfil();
				}catch (SQLException e1){
					e1.printStackTrace();
				}
			}else {
				try {
					ventana.modificarPerfil();
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		}else if(comando.contentEquals("CANCELAR")){
			ventana.cerrarVentana();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(ventana.getlistDeportes())) {
			System.out.println("asdfe");
			String lDeportes [] = ventana.getlistDeportes().getSelectedItems();
			for (String str : lDeportes) {
				ventana.addDeportes(str);
			}
		}else if(e.getSource().equals(ventana.getChoiceUbi())){
			System.out.println("Addasse");
			String localizacion = ventana.getChoiceUbi().getSelectedItem();
			ventana.setLocalizacion(localizacion);
		}
		
	}
	

}
