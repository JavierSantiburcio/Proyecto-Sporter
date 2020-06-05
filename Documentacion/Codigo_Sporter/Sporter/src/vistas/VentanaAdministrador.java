// Autor; Jose Luis Gonzalez Ramirez

package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import colores.Colores;
import conexion.Conexion;
import controlador.CtrlVentanaAdministrador;
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
	private DefaultTableModel modeloTabla2;
	private Persona persona;

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
					CtrlVentanaAdministrador crtl= new CtrlVentanaAdministrador(frame);
					frame.controladorVista(crtl);
					frame.controladorBotonesTable(crtl);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaAdministrador(Administrador admin) throws SQLException{
		setResizable(false);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Sporter");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 334);
		
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
		btCerrarSesion.setBounds(819, 5, 117, 29);
		btCerrarSesion.setName("cs");
		contentPane.add(btCerrarSesion);
		
		lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setBounds(712, 13, 108, 16);
		contentPane.add(lblAdministrador);
		
		btnPerfil = new JToggleButton("Perfil");
		btnPerfil.setForeground(colores.getNaranja());
		btnPerfil.setBounds(562, 5, 61, 39);
		contentPane.add(btnPerfil);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
			}
		});	
		// tabla derecha Eliminar Eventos 
		
		@SuppressWarnings("serial")
		//Mimodelo t = new Mimodelo();
		

		
		String titulos[] ={"ID","FECHA","HORA","PROPIETARIO","DEPORTE","UBICACIÒN","Nº PARTICIPANTES"," "};
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setName("e");
		
		
		modeloTabla = new DefaultTableModel(null,titulos){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int filas, int columnas){
				return false;
			}
		};
		
		
		tabla = new JTable();
		tabla.setModel(modeloTabla);
		tabla.setBounds(8, 114, 620, 192);
		tabla.setBackground(Color.WHITE);
		tabla.getTableHeader().setReorderingAllowed(false); //No permite mover las columnas
		tabla.setFocusable(false); //Quito el focus de las celdas, para que cuando seleccione una celda se vea toda la fila seleccionada y no el focus de la celda
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setFillsViewportHeight(false); //No deja espacio abajo cuando se van eliminando filas.
		 
		tabla.getColumnModel().getColumn(0).setMaxWidth(200);
		tabla.getColumnModel().getColumn(1).setMaxWidth(200); 
		tabla.getColumnModel().getColumn(2).setMaxWidth(350);
		tabla.getColumnModel().getColumn(3).setMaxWidth(350); 
		tabla.getColumnModel().getColumn(4).setMaxWidth(350);
		tabla.getColumnModel().getColumn(5).setMaxWidth(350);
		tabla.getColumnModel().getColumn(6).setMaxWidth(350);
		tabla.getColumnModel().getColumn(7).setMaxWidth(200); 
		tabla.setDefaultRenderer(Object.class, new Render()); // Para centrar valores de las celdas
		
		/*
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
		
			
	        }); */
		
		JScrollPane scrollPaneEventos = new JScrollPane();
	    scrollPaneEventos.setBounds(8, 114, 620, 192);
	    scrollPaneEventos.setViewportView(tabla);
	    contentPane.add(scrollPaneEventos);			
		scrollPaneEventos.setColumnHeaderView(btnEliminar);	
		
		generarContenidoTabla1();
		
		//String[] titulos = new String[] {
		//		"Propietario", "Deporte", "Ubicacion", "Participantes", "fecha", "hora"," "
		//	};
		
		//listaEventos = obtenerMatrizDatos(titulos,btnEliminar);
		
		//t.ver_tabla(tabla);
		
		
		
		// tabla derecha Eliminar Usuario
		
		/*
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
	        
	    */
		//Mimodelo2 t1 = new Mimodelo2();
				btn2Eliminar = new JButton("Eliminar");
				btn2Eliminar.setName("ee");
	   String titulos2[] ={"id", "Nombre Usuario", ""};
	   
	   modeloTabla2 = new DefaultTableModel(null,titulos2){
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int filas, int columnas){
						return false;
					}
				};
	    
		//modeloTabla2 = new miModelo2();
		
		tabla1 = new JTable();
		tabla1.setModel(modeloTabla2);
		tabla1.setBounds(637, 115, 221, 192);
		tabla1.setBackground(Color.WHITE);
		tabla1.getTableHeader().setReorderingAllowed(false); //No permite mover las columnas
		tabla1.setFocusable(false); //Quito el focus de las celdas, para que cuando seleccione una celda se vea toda la fila seleccionada y no el focus de la celda
		tabla1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla1.setFillsViewportHeight(false); //No deja espacio abajo cuando se van eliminando filas.
		tabla1.getColumnModel().getColumn(0).setMaxWidth(350); //configuro tama�o maximo de la columna 
		tabla1.getColumnModel().getColumn(1).setMaxWidth(200);
		tabla1.getColumnModel().getColumn(2).setMaxWidth(200);
		tabla1.setDefaultRenderer(Object.class, new Render());
		
		
		
		JScrollPane scrollPaneNonmbreUsuario = new JScrollPane();
		scrollPaneNonmbreUsuario .setBounds(637, 115, 307, 192);
		scrollPaneNonmbreUsuario .setViewportView(tabla1);
		
		contentPane.add(scrollPaneNonmbreUsuario );
		scrollPaneNonmbreUsuario.setColumnHeaderView(btn2Eliminar);
		
		   
		generarContenidoTabla2();
		
		
		
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
		                    //borrarevento(evento);
		                
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
	
	public void generarContenidoTabla1() throws SQLException {
		List<Evento> listEventosA = new ArrayList<Evento>();
		Evento evento = new Evento(command);
		listEventosA = evento.getListEventos(persona.getId()); // Almaceno una lista con todos los objetos de eventos de la base da datos
		
		for (int i = 0; i < listEventosA.size(); i++) {// En el bucle voy a�adiendo filas 
			Object[] rowData = new Object[8];
			
			int id = evento.getId();
			String propietario = evento.getNombreUsuario(listEventosA.get(i).getOrganiza(), listEventosA.get(i).getId());
			String deporte = evento.getNombreDeporte(listEventosA.get(i).getDeporte(), listEventosA.get(i).getId()); 
			
			String date = listEventosA.get(i).getFecha();
			String fecha = date.substring(0, 10);
			String hora = date.substring(11, 19);
			
			rowData[0] = id;
			rowData[1] = fecha;
			rowData[2] = hora;
			rowData[3] = propietario;
			rowData[4] = deporte;		
			rowData[5] = listEventosA.get(i).getUbicacion();
			rowData[6] = listEventosA.get(i).getNumParticipantesActivos(listEventosA.get(i).getId())+ "/"+listEventosA.get(i).getNumeroParticipantes();
		    rowData[7] = btnEliminar;
			
		    modeloTabla.addRow(rowData); // A�ado la fila al DafaultModel con el array rowData 
		}
			
			
		
	}


     String titulos[] ={"ID","FECHA","HORA","PROPIETARIO","DEPORTE","UBICACIÒN","Nº PARTICIPANTES"," "};	

	public void generarContenidoTabla2() throws SQLException {
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		
		List<Persona> listPersonas = new ArrayList<Persona>();
		ResultSet data;
		
		persona = new Persona(command);
		
		listPersonas =  getListPersonas();// Almaceno una lista con todas los personas de la base da datos
		
		for (int i = 0; i < listPersonas.size(); i++) {//  anadiendo filas 
			Object[] rowData = new Object[3];
			
			Integer id = listPersonas.get(i).getId();
			String nombre= listPersonas.get(i).getNombre();
			String email = listPersonas.get(i).getEmail();
			rowData[0] = id;
			rowData[1] = nombre;
			rowData[2] =  email;
			rowData[3] = btn2Eliminar;
			
			modeloTabla.addRow(rowData); 
		}

	}
		
	public List<Persona> getListPersonas() throws SQLException {
			List<Persona> listPersonas = new ArrayList<Persona>();
			ResultSet data;
			Persona persona = new Persona(command); 
			data = command.executeQuery("SELECT idUsuarios from usuarios where admin=0;");
			while(data.next()) {
				Integer id= data.getInt(1);
				persona = new Persona(command,id);
				listPersonas.add(persona);
				
			}
			return listPersonas;
		}


	





	//----------------------------------------------
   
	private String getString(int i) {
		// TODO Auto-generated method stub
		return null;
	}





	public void controladorVista(ActionListener crtl) {
		
		btCerrarSesion.addActionListener(crtl);
		btCerrarSesion.setActionCommand("SALIR");
		
		
		btFiltro.addActionListener(crtl);
		btFiltro.setActionCommand("FILTRAR");
		
		
		btn2Eliminar.addActionListener(crtl);
		btn2Eliminar.setActionCommand("ELIMINARUSUARIO");
		
		
		btnEliminar.addActionListener(crtl);
		btnEliminar.setActionCommand("ELIMINAREVENTO");
		
		
		btnPerfil.addActionListener(crtl);
		btnPerfil.setActionCommand("PERFIL");
		
	}
    
	public void controladorBotonesTable(MouseListener ctrl) {
		tabla.addMouseListener(ctrl);
		tabla1.addMouseListener(ctrl);
	}
	
   //-----------------------------------------------------------------
	
	


	
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

