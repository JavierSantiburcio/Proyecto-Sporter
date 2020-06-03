// Autor; Jose Luis Gonzalez Ramirez

package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import colores.Colores;
import conexion.Conexion;
import imagenes.Imagenes;
import modelo.Administrador;
import modelo.Deporte;
import modelo.Evento;
import modelo.Persona;
import modelo.Ubicacion;
import modelo.Usuario;
import modelo.Administrador.*;
import render.Mimodelo;
import render.Mimodelo2;
import render.Render;



public class VentanaAdministrador extends JFrame  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Usuario usuario;
	private Colores colores = new Colores();
	private Imagenes imagenes = new Imagenes();
	private JPanel contentPane;
	protected static Statement command;
	private ArrayList<Evento> listaEventos;
	private JLabel lblAdministrador,lblDeporte,lblUbicacion;
	private JButton btCerrarSesion,btFiltro,btn2Eliminar,btnEliminar;
	private JToggleButton btnPerfil;
	private JTable tabla1;
	public JTable tabla;
	private Choice choice_Deporte,choice_Ubicacion;
	private DefaultTableModel modeloTabla;
	private static Administrador admin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion conexion = new Conexion();
					command = conexion.getcommand();
					admin = new Administrador(command,34);
					VentanaAdministrador frame = new VentanaAdministrador(admin);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	public void VentanaAdministrador() throws SQLException {
		
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		
		String[] titulos = new String[] {
				"Propietario", "Deporte", "Ubicacion", "Participantes", "fecha", " "
			};
		
		//Object[][] datos = obtenerMatrizDatos(titulos);
		
		// si se pulsa filtrar cargar tabla nueva con
		int idDeporte;
		Deporte deporte = new Deporte(command);
		idDeporte = deporte.obtenerIdDeporte(choice_Deporte.getSelectedItem());
		String ubicacion = choice_Ubicacion.getSelectedItem();
		// falta cargar tablas
		
		
		
	}
	
	*/
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaAdministrador(Administrador admin) throws SQLException{
		
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Sporter");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 334);
		
		contentPane = new JPanel();
		contentPane.setBackground(colores.getVerde());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		
		lblDeporte = new JLabel("Deporte");
		lblDeporte.setBounds(6, 62, 61, 16);
		lblDeporte.setForeground(colores.getAmarillo());
		contentPane.add(lblDeporte);
		
		lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setBounds(165, 62, 68, 16);
		lblUbicacion.setForeground(colores.getAmarillo());
		contentPane.add(lblUbicacion);
		
		btFiltro = new JButton("Filtrar");
		btFiltro.setBounds(375, 57, 117, 29);
		btFiltro.setForeground(colores.getNaranja());
		contentPane.add(btFiltro);
		btFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
			}
		});	
		
		choice_Deporte = new Choice();
		choice_Deporte.setBounds(65, 58, 100, 27);
		contentPane.add(choice_Deporte);
		
		
		choice_Ubicacion = new Choice();
		choice_Ubicacion.setBounds(232, 58, 120, 27);
		contentPane.add(choice_Ubicacion);
		
		cargarChoiceDeporte(choice_Deporte);
		cargarChoiceUbicacion(choice_Ubicacion);
		
		
		btCerrarSesion = new JButton("Cerrar sesion");
		btCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});		
		btCerrarSesion.setBounds(726, 6, 117, 29);
		btCerrarSesion.setName("cs");
		contentPane.add(btCerrarSesion);
		
		lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setBounds(618, 11, 108, 16);
		contentPane.add(lblAdministrador);
		
		btnPerfil = new JToggleButton("Perfil");
		btnPerfil.setForeground(colores.getNaranja());
		btnPerfil.setBounds(545, 5, 61, 39);
		contentPane.add(btnPerfil);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
			}
		});	
		// tabla derecha Eliminar Eventos 
		
		@SuppressWarnings("serial")
		Mimodelo t = new Mimodelo();
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setName("e");
		
		String titulos[] ={"ID","FECHA","HORA","PROPIETARIO","DEPORTE","UBICACIÒN","Nº PARTICIPANTES"," "};
		
		tabla = new JTable();
		tabla.setModel(new javax.swing.table.DefaultTableModel(
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
	        ));			
		
			tabla.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                tablaMouseClicked(evt);
	            }

				private void tablaMouseClicked(MouseEvent evt) {
					// TODO Auto-generated method stub
					
				}
	        });
		
		JScrollPane scrollPaneEventos = new JScrollPane();
	    scrollPaneEventos.setBounds(8, 114, 620, 192);
	    scrollPaneEventos.setViewportView(tabla);
	    contentPane.add(scrollPaneEventos);			
		scrollPaneEventos.setColumnHeaderView(btnEliminar);	
		
		//String[] titulos = new String[] {
		//		"Propietario", "Deporte", "Ubicacion", "Participantes", "fecha", "hora"," "
		//	};
		
		//listaEventos = obtenerMatrizDatos(titulos,btnEliminar);
		
		t.ver_tabla(tabla);
		
		
		
		// tabla derecha Eliminar Usuarios
		
		Mimodelo2 t1 = new Mimodelo2();
		JButton btn2Eliminar = new javax.swing.JButton("Eliminar");
		btn2Eliminar.setName("ee");
		
		
		
		JTable tabla1 = new javax.swing.JTable();
		
		tabla1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar},
	                {null, btn2Eliminar}
	            },
	            new String [] {
	                "Nombre Usuario",  " "
	            }
	        ));
		JScrollPane scrollPaneNonmbreUsuario = new JScrollPane();
		scrollPaneNonmbreUsuario .setBounds(637, 115, 221, 192);
		scrollPaneNonmbreUsuario .setViewportView(tabla1);
		contentPane.add(scrollPaneNonmbreUsuario );
		scrollPaneNonmbreUsuario.setColumnHeaderView(btn2Eliminar);
		
		//String[] titulos2 = new String[] {
		//		"Nombre Usuarios",  " "
		//	};
		
		//Object informacion2[][]= obtenerMatrizDatos(titulos2,btn2Eliminar);   
		
		t1.ver_tabla(tabla1);
		
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {
	            

	            private void tablaMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {//GEN-FIRST:event_tablaMouseClicked
			        
			        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
			        int row = evt.getY()/tabla1.getRowHeight();
			        
			        if(row < tabla.getRowCount() && row >= 0 && column < tabla1.getColumnCount() && column >= 0){
			            Object value = tabla1.getValueAt(row, column);
			            if(value instanceof JButton){
			                ((JButton)value).doClick();
			                JButton boton = (JButton) value;

			             
			                if(boton.getName().equals("e")){
			                    JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
			                    System.out.println("Click en el boton eliminar");
			                    Object id;
								//EVENTOS ELIMINAR
			                    borrarEvento();
			                
			                }
			            }
			        
			        }
			        
			    }//GEN-LAST:event_tablaMouseClicked
	        });
	       
		tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
	        

	        private void tablaMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {//GEN-FIRST:event_tablaMouseClicked
		        
		        int column = tabla1.getColumnModel().getColumnIndexAtX(evt.getX());
		        int row = evt.getY()/tabla1.getRowHeight();
		        
		        if(row < tabla1.getRowCount() && row >= 0 && column < tabla1.getColumnCount() && column >= 0){
		            Object value = tabla1.getValueAt(row, column);
		            if(value instanceof JButton){
		                ((JButton)value).doClick();
		                JButton boton = (JButton) value;

		             
		                if(boton.getName().equals("ee")){
		                    JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
		                    System.out.println("Click en el boton eliminar");
		                    Object id;
							//EVENTOS ELIMINAR
		                    //borrarusuario(persona);
		                
		                }
		            }
		        
		        }
		        
		    }//GEN-LAST:event_tablaMouseClicked
	    });
	
	}
	
	
   
 


	
    public void borrarEvento() throws SQLException {
    	
    		Conexion conexion = new Conexion();
    		command = conexion.getcommand();
    		int id = (int) tabla.getModel().getValueAt(tabla.getSelectedRow(), 0); // obtengo valor ID de la fila seleccionada
    		Evento evento = new Evento(command,id);
    		admin.eliminarEvento(evento);
    		Mimodelo t = new Mimodelo();
    		t.removeRow(tabla.getSelectedRow());// Elimina la fila que getSelectedRow() devuelve.
    		JOptionPane.showMessageDialog(this, "Se ha eliminado el evento  el evento deportivo "+ evento.getId() +" correctamente","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
    	   
    	
    }
    
    public void borrarUsuario(Persona persona)  {
    	
    		int id = (int) tabla1.getModel().getValueAt(tabla1.getSelectedRow(), 0);
    		admin.eliminarUsuario(persona);
    		Mimodelo2 t1 = new Mimodelo2();
    		t1.removeRow(tabla1.getSelectedRow());// Elimina la fila que getSelectedRow() devuelve.
    		JOptionPane.showMessageDialog(this, "Se ha eliminado el evento  el evento deportivo "+ persona.getId() +" correctamente","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
    }
	
	//Metodo para rellenar los items del choice de deporte
			private void cargarChoiceDeporte(Choice c) throws SQLException {
				Conexion conexion = new Conexion();
				command = conexion.getcommand();
				
				Deporte deporte = new Deporte(command);
				List<String> listDeporte = deporte.obtenerListaDeporte();
				for(String item : listDeporte) {
					c.add(item);
				}
			}
			
			//Metodo para rellenar los items del choice de ubicacion
			private void cargarChoiceUbicacion(Choice c) {
				Ubicacion ub = new Ubicacion();
				List<String> listUbicacion = ub.getListUbicacion();
				for(String item : listUbicacion) {
					c.add(item);
				} 
	
			}
	
			public void filtrarEvento() {
				// TODO Auto-generated method stub
				
			}

			public void perfilUsuario() {
				// TODO Auto-generated method stub
				
			}

			public void iniciarSesion() {
				// TODO Auto-generated method stub
				
			}	
			
			public void cerrarVentana() {
				this.dispose();
			}

			
			
}

