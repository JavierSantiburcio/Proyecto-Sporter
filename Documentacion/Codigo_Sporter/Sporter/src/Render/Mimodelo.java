package Render;

import javax.swing.table.DefaultTableModel;

import Modelo.Evento;

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
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar},
		                {null, null, null, null, null, btnEliminar}
		            },
		            new String [] {
		                "Propietario", "Deporte", "Ubicacion", "Participantes", "Fecha", " "
		            }
	        )
	        {
	            public boolean isCellEditable(int row, int column){
	            	if (column==5) {
	                	return true;
	                }
					return false;
	            }
	        };
	        
	        tabla.setModel(d);
	        
	        tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
	  
	//public boolean isCellEditable(int row, int column){
	//                if (column==5) {
	//                	return true;
	//                }
	//				return false;
	            }
/*
	private Object[][] obtenerMatrizDatos(String[] st, JButton btFiltro){
		Object informacion[][] = new String[listaEventos.size()][st.length];
		for(int x = 0; x < informacion.length; x++){
			informacion[x][0] = listaEventos.get(x).getId() + "";
			informacion[x][1] = listaEventos.get(x).getDeporte() + "";
			informacion[x][2] = listaEventos.get(x).getUbicacion() + "";
			informacion[x][3] = listaEventos.get(x).getNumeroParticipantes() + "";
			informacion[x][4] = listaEventos.get(x).getFecha() + "";
			informacion[x][5] = btFiltro;
		}
		return informacion;
	}
	        
	*/        
	     
}
		