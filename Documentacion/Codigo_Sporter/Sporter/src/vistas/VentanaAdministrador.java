// Autor; Jose Luis Gonzalez Ramirez

package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import colores.Colores;
import conexion.Conexion;

import controlador.CtrlVentanaFrmLogin;
import controlador.CtrlVentanaLogin;
import imagenes.Imagenes;
import modelo.Administrador;
import modelo.Deporte;
import modelo.Evento;
import modelo.Persona;
import modelo.Ubicacion;
import render.Render;


public class VentanaAdministrador extends JFrame  {

	private static final long serialVersionUID = 1L;
	private Colores colores = new Colores();
	private Imagenes imagenes = new Imagenes();
	private JPanel contentPane;
	protected static Statement command;
	private JLabel lblAdministrador,lblDeporte,lblUbicacion;
	private JButton btCerrarSesion,btFiltro,btn2Eliminar,btnEliminar;
	public JTable tablaEvento, tablaUsuario;
	private Choice choice_Deporte,choice_Ubicacion;
	private DefaultTableModel modeloTablaEvento,modeloTablaUsuario;
	private Persona persona;

	private Administrador administrador;
	private JPanel panel;



	//------------------------------------------------------------------------------------ ESTRUCTURA VISTA -----------------------------------------------------------------//	
	public VentanaAdministrador(Administrador admin) throws Exception{

		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		administrador = admin;

		setResizable(false);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Sporter - Ventana Administrador");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1320, 434);

		contentPane = new JPanel();
		contentPane.setBackground(colores.getVerde());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); 
		setContentPane(contentPane);
				

		panel = new JPanel();
		panel.setBackground(new Color(46, 139, 87));
		panel.setBounds(10, 145, 795, 45);
		contentPane.add(panel);
		panel.setLayout(null);


		lblDeporte = new JLabel("Deporte:");
		lblDeporte.setBounds(10, 18, 61, 16);
		panel.add(lblDeporte);
		lblDeporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeporte.setForeground(colores.getAmarillo());

		lblUbicacion = new JLabel("Ubicaci"+'ó'+"n:");
		lblUbicacion.setBounds(303, 18, 68, 16);
		panel.add(lblUbicacion);
		lblUbicacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUbicacion.setForeground(colores.getAmarillo());

		btFiltro = new JButton("Filtrar Evento");
		btFiltro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btFiltro.setBounds(668, 14, 117, 20);
		panel.add(btFiltro);
		btFiltro.setForeground(new Color(0, 0, 0));

		choice_Deporte = new Choice();
		choice_Deporte.setBounds(77, 14, 169, 20);
		panel.add(choice_Deporte);

		cargarChoiceDeporte(choice_Deporte);


		choice_Ubicacion = new Choice();
		choice_Ubicacion.setBounds(377, 14, 149, 20);
		panel.add(choice_Ubicacion);
		cargarChoiceUbicacion(choice_Ubicacion);
		btFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
			}
		});	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(46, 139, 87));
		panel_1.setBounds(10, 11, 1294, 44);
		contentPane.add(panel_1);
		panel_1.setLayout(null);


		btCerrarSesion = new JButton("Cerrar sesión");
		btCerrarSesion.setBounds(1171, 11, 113, 25);
		panel_1.add(btCerrarSesion);
		btCerrarSesion.setName("cs");

		lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setBounds(10, 10, 136, 25);
		panel_1.add(lblAdministrador);
		lblAdministrador.setFont(new Font("Tahoma", Font.BOLD, 12));

		// ------------------------------------------------------------------------------- TABLA EVENTOS ---------------------------------------------------------//

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setName("e");

		String titulosEvento[] ={"ID","FECHA","HORA","PROPIETARIO","DEPORTE","UBICACI"+'Ó'+"N","N"+'º'+" PARTICIPANTES",""};
		modeloTablaEvento = new DefaultTableModel(null,titulosEvento){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int filas, int columnas){
				return false;
			}
		};


		tablaEvento = new JTable();
		tablaEvento.setModel(modeloTablaEvento);
		tablaEvento.setBounds(8, 114, 620, 192);
		tablaEvento.setBackground(Color.WHITE);
		tablaEvento.getTableHeader().setReorderingAllowed(false); //No permite mover las columnas
		tablaEvento.setFocusable(false); //Quito el focus de las celdas, para que cuando seleccione una celda se vea toda la fila seleccionada y no el focus de la celda
		tablaEvento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaEvento.setFillsViewportHeight(false); //No deja espacio abajo cuando se van eliminando filas.

		tablaEvento.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaEvento.getColumnModel().getColumn(1).setMaxWidth(200);
		tablaEvento.getColumnModel().getColumn(2).setMaxWidth(200); 
		tablaEvento.getColumnModel().getColumn(3).setMaxWidth(400);
		tablaEvento.getColumnModel().getColumn(4).setMaxWidth(300); 
		tablaEvento.getColumnModel().getColumn(5).setMaxWidth(300);
		tablaEvento.getColumnModel().getColumn(6).setMaxWidth(350); 
		tablaEvento.getColumnModel().getColumn(7).setMaxWidth(150);
		tablaEvento.setDefaultRenderer(Object.class, new Render()); // Para centrar valores de las celdas


		JScrollPane scrollPaneEventos = new JScrollPane();
		scrollPaneEventos.setBounds(10, 201, 795, 192);
		scrollPaneEventos.setViewportView(tablaEvento);
		contentPane.add(scrollPaneEventos);			
		scrollPaneEventos.setColumnHeaderView(btnEliminar);	

		generarContenidoTablaEventos();

		// -------------------------------------------------------------------- FIN TABLA EVENTOS ---------------------------------------------------------//

		// -------------------------------------------------------------------- TABLA USUARIOS ------------------------------------------------------------//

		btn2Eliminar = new JButton("Eliminar");
		btn2Eliminar.setName("u");

		String titulosUsuario[] ={"ID", "NOBRE", "EMAIL", ""};

		modeloTablaUsuario = new DefaultTableModel(null,titulosUsuario){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int filas, int columnas){
				return false;
			}
		};

		tablaUsuario = new JTable();
		tablaUsuario.setModel(modeloTablaUsuario);
		tablaUsuario.setBounds(637, 115, 221, 192);
		tablaUsuario.setBackground(Color.WHITE);
		tablaUsuario.getTableHeader().setReorderingAllowed(false); //No permite mover las columnas
		tablaUsuario.setFocusable(false); //Quito el focus de las celdas, para que cuando seleccione una celda se vea toda la fila seleccionada y no el focus de la celda
		tablaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaUsuario.setFillsViewportHeight(false); //No deja espacio abajo cuando se van eliminando filas.
		tablaUsuario.getColumnModel().getColumn(0).setMaxWidth(40); //configuro tama�o maximo de la columna 
		tablaUsuario.getColumnModel().getColumn(1).setMaxWidth(450);
		tablaUsuario.getColumnModel().getColumn(2).setMaxWidth(450);
		tablaUsuario.getColumnModel().getColumn(3).setMaxWidth(150);
		tablaUsuario.setDefaultRenderer(Object.class, new Render());

		JScrollPane scrollPaneNonmbreUsuario = new JScrollPane();
		scrollPaneNonmbreUsuario .setBounds(841, 201, 463, 192);
		scrollPaneNonmbreUsuario .setViewportView(tablaUsuario);
		contentPane.add(scrollPaneNonmbreUsuario );
		scrollPaneNonmbreUsuario.setColumnHeaderView(btn2Eliminar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 1314, 2);
		contentPane.add(separator);
		
		JLabel lblTituloTablaEvento = new JLabel("TABLA DE EVENTOS DEPORTIVOS");
		lblTituloTablaEvento.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblTituloTablaEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloTablaEvento.setBounds(10, 120, 795, 14);
		lblTituloTablaEvento.setForeground(colores.getAmarillo());
		contentPane.add(lblTituloTablaEvento);
		
		JLabel lblTituloTablaUsuarios = new JLabel("TABLA USUARIOS");
		lblTituloTablaUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloTablaUsuarios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblTituloTablaUsuarios.setBounds(841, 176, 463, 14);
		lblTituloTablaUsuarios.setForeground(colores.getAmarillo());
		contentPane.add(lblTituloTablaUsuarios);

		generarContenidoTablaUsuarios();

		// ---------------------------------------------------------------------- FIN TABLA USUARIOS ------------------------------------------------------------------//	

	}

	//---------------------------------------------------------------------- FIN ESTRUCTURA VISTA -----------------------------------------------------------------//	

	//------------------------------------------------------------------------- CONTROLADORES ---------------------------------------------------------------------//

	public void controladorVista(ActionListener crtl) {
		btCerrarSesion.addActionListener(crtl);
		btCerrarSesion.setActionCommand("SALIR");

		btFiltro.addActionListener(crtl);
		btFiltro.setActionCommand("FILTRAR");

	}

	public void controladorBotonesTable(MouseListener ctrl) {
		tablaEvento.addMouseListener(ctrl);
		tablaUsuario.addMouseListener(ctrl);
	}

	//------------------------------------------------------------------------- FIN  CONTROLADORES -----------------------------------------------------------------//

	//------------------------------------------------------------------------ METODOS AUXILIARES ------------------------------------------------------------------//

	public void generarContenidoTablaEventos() throws Exception {
		List<Evento> listEventos = new ArrayList<Evento>();
		Evento evento = new Evento(command);
		listEventos = evento.getListAllEventos();

		for (int i = 0; i < listEventos.size(); i++) {// En el bucle voy a�adiendo filas 
			Object[] rowData = new Object[8];

			int id = listEventos.get(i).getId();
			String propietario = evento.getNombreUsuario(listEventos.get(i).getOrganiza(), listEventos.get(i).getId());
			String deporte = evento.getNombreDeporte(listEventos.get(i).getDeporte(), listEventos.get(i).getId()); 

			String date = listEventos.get(i).getFecha();
			String fecha = date.substring(0, 10);
			String hora = date.substring(11, 19);

			rowData[0] = id;
			rowData[1] = fecha;
			rowData[2] = hora;
			rowData[3] = propietario;
			rowData[4] = deporte;		
			rowData[5] = listEventos.get(i).getUbicacion();
			rowData[6] = listEventos.get(i).getNumParticipantesActivos(listEventos.get(i).getId())+ "/"+listEventos.get(i).getNumeroParticipantes();
			rowData[7] = btnEliminar;

			modeloTablaEvento.addRow(rowData); // A�ado la fila al DafaultModel con el array rowData 
		}
	}


	public void generarContenidoTablaUsuarios() throws Exception {

		List<Persona> listPersona = new ArrayList<Persona>();
		Persona persona = new Persona(command);
		listPersona = persona.getAllPersonas();

		if (listPersona.size() == 0) {
			JOptionPane.showMessageDialog(this, "No hay usuarios en la BD","Atenci"+'ó'+"n", JOptionPane.WARNING_MESSAGE, null);

		} else { 
			for (int i = 0; i < listPersona.size(); i++) {//  anadiendo filas 

				Object[] rowData = new Object[4];

				int idUsuario = listPersona.get(i).getId();
				String nombrel= listPersona.get(i).getNombre();
				String emaill = listPersona.get(i).getEmail();
				rowData[0] = idUsuario;
				rowData[1] = nombrel;
				rowData[2] =  emaill;
				rowData[3] = btn2Eliminar;

				modeloTablaUsuario.addRow(rowData); 
			}

		}

	}

	public void borrarEvento() throws SQLException{

		int id = (int) tablaEvento.getModel().getValueAt(tablaEvento.getSelectedRow(), 0); // obtengo valor ID de la fila seleccionada
		Evento evento = new Evento(command,id);
		administrador.eliminarEvento(evento);
		modeloTablaEvento.removeRow(tablaEvento.getSelectedRow());// Elimina la fila que getSelectedRow() devuelve.
		JOptionPane.showMessageDialog(this, "Se ha eliminado el evento deportivo "+ evento.getId() +" correctamente.","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
	}

	public void borrarUsuario() throws Exception  {

		int idPersona = (int) tablaUsuario.getModel().getValueAt(tablaUsuario.getSelectedRow(), 0);
		administrador.eliminarUsuario(idPersona);
		modeloTablaUsuario.removeRow(tablaUsuario.getSelectedRow());// Elimina la fila que getSelectedRow() devuelve.
		JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
		this.limpiarTabla();
		this.generarContenidoTablaEventos();
		
	}

	//Metodo para rellenar los items del choice de deporte
	private void cargarChoiceDeporte(Choice c) throws SQLException {

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

	public void filtrarEvento() throws SQLException{
		List<Evento> listEventos = new ArrayList<Evento>();
		Evento evento = new Evento(command);
		
		Deporte deportes = new Deporte(command);
		int idDeporte = deportes.obtenerIdDeporte(choice_Deporte.getSelectedItem());
		String ubicacion = choice_Ubicacion.getSelectedItem();
		
		listEventos = evento.getListAllEventosFiltro(ubicacion, idDeporte);

		for (int i = 0; i < listEventos.size(); i++) {// En el bucle voy a�adiendo filas 
			Object[] rowData = new Object[8];

			int id = listEventos.get(i).getId();
			String propietario = evento.getNombreUsuario(listEventos.get(i).getOrganiza(), listEventos.get(i).getId());
			String deporte = evento.getNombreDeporte(listEventos.get(i).getDeporte(), listEventos.get(i).getId()); 

			String date = listEventos.get(i).getFecha();
			String fecha = date.substring(0, 10);
			String hora = date.substring(11, 19);

			rowData[0] = id;
			rowData[1] = fecha;
			rowData[2] = hora;
			rowData[3] = propietario;
			rowData[4] = deporte;		
			rowData[5] = listEventos.get(i).getUbicacion();
			rowData[6] = listEventos.get(i).getNumParticipantesActivos(listEventos.get(i).getId())+ "/"+listEventos.get(i).getNumeroParticipantes();
			rowData[7] = btnEliminar;

			modeloTablaEvento.addRow(rowData); // A�ado la fila al DafaultModel con el array rowData 
		}
	}

	public void cerrarVentana() throws ParseException {
		this.dispose();
		VentanaLogin frame = new VentanaLogin();
		CtrlVentanaLogin ctrl = new CtrlVentanaLogin(frame);
		frame.controlVentana(ctrl);
		frame.setVisible(true);
	}
	
	public void limpiarTabla() {
		int num = modeloTablaEvento.getRowCount()-1;
		for(int i = num; i>=0;i--) {
			modeloTablaEvento.removeRow(i);
		}
	}

	public void abrirVentanaFormularioLogin() {
		try {
			VentanaFormularioLogin frame = new VentanaFormularioLogin(null,null,false);
			CtrlVentanaFrmLogin ctrl = new CtrlVentanaFrmLogin(frame, false);
			frame.controlVentana(ctrl);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}