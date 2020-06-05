// Autor; Jose Luis Gonzalez Ramirez

package render;


import javax.swing.table.DefaultTableModel;

import modelo.Evento;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;



public class Mimodelo2 extends DefaultTableModel{

	//private ArrayList<Evento> listaEventos;  
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void ver_tabla(JTable tabla){
		
		
		JButton btn2Eliminar = new JButton("Eliminar");
		btn2Eliminar.setName("e");
		
		tabla.setDefaultRenderer(Object.class, new Render());
	
		DefaultTableModel d = new DefaultTableModel
					(
							new Object [][] {
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar},
				                {null, null, null, btn2Eliminar}
				            },
				            new String [] {
				                "id", "Nombre Usuario", "Email",  ""
				            }
	        )
	        {
	            public boolean isCellEditable(int row, int column){
	            	if (column==4) {
	                	return true;
	                }
					return false;
	            }
	        };
	        
	        tabla.setModel(d);
	        
	        tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
	  
	
	            }

	     
}
		