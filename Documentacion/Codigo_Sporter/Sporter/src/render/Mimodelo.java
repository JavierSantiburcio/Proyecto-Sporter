// Autor; Jose Luis Gonzalez Ramirez

package render;

import javax.swing.table.DefaultTableModel;

import modelo.Evento;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;



public class Mimodelo extends DefaultTableModel{

	//private ArrayList<Evento> listaEventos;  
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void ver_tabla(JTable tabla){
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setName("e");
		
		tabla.setDefaultRenderer(Object.class, new Render());
	
		DefaultTableModel d = new DefaultTableModel
				(
			            new Object [][] {
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar},
			                {null, null, null, null, null, null, null, btnEliminar}
			            },
			            new String [] {
			            		"ID","FECHA","HORA","PROPIETARIO","DEPORTE","UBICACIÒN","Nº PARTICIPANTES"," "
			            }
			        )
				
	        {
	            public boolean isCellEditable(int row, int column){
	            	if (column==7) {
	                	return true;
	                }
					return false;
	            }
	        };
	        
	        tabla.setModel(d);
	        
	        tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
	      
	     
	}
}
		