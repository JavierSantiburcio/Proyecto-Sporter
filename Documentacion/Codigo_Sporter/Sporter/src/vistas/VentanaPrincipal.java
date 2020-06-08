package vistas;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;

import colores.Colores;
import conexion.Conexion;
import controlador.CtrlVentanaCrearEvento;
import controlador.CtrlVentanaLogin;
import controlador.CtrlVentanaPerfilUsuario;
import imagenes.Imagenes;
import modelo.*;
import render.Render;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {
	private JPanel contentPane;
	public JTable tablaEventos;
	private Choice choice_Deporte,choice_Ubicacion;
	protected static Statement command;
	private static Colores colores = new Colores();
	private Imagenes imagenes;
	private JLabel lbl_IconoPerfil;
	private JButton btnCerrarSesion, btnCrearEvento, btnBuscar, btn_Usuario, btnUnirse;
	private DefaultTableModel modelo = new DefaultTableModel();
	private Persona persona;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lbl_Sporter;

	public VentanaPrincipal(Persona persona) throws SQLException{
		
		//Conectar a MySQL
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		imagenes = new Imagenes(persona.getUrl());
		
		//Estetica ventana
		setPersona(persona);
		setResizable(false);
		setTitle("Sporter");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 692);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(colores.getVerde());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(46, 139, 87));
		panel_1.setBounds(38, 88, 813, 65);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(696, 36, 103, 23);
		panel_1.add(btnBuscar);
		btnBuscar.setBackground(colores.getNaranja());
		
		choice_Ubicacion = new Choice();
		choice_Ubicacion.setBounds(500, 39, 166, 20);
		panel_1.add(choice_Ubicacion);
		cargarChoiceUbicacion(choice_Ubicacion);
		
		//Opciones Busqueda
	
		JLabel lblIntroduzcaDeporte = new JLabel("Introduzca Deporte:");
		lblIntroduzcaDeporte.setBounds(6, 39, 141, 20);
		panel_1.add(lblIntroduzcaDeporte);
		lblIntroduzcaDeporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIntroduzcaDeporte.setForeground(colores.getAmarillo());
		
		choice_Deporte = new Choice();
		choice_Deporte.setBounds(153, 39, 166, 20);
		panel_1.add(choice_Deporte);
		cargarChoiceDeporte(choice_Deporte);
		
		JLabel lblIntroduzcaUbicacin = new JLabel("Introduzca Ubicaci"+'ó'+"n:");
		lblIntroduzcaUbicacin.setBounds(353, 39, 141, 20);
		panel_1.add(lblIntroduzcaUbicacin);
		lblIntroduzcaUbicacin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIntroduzcaUbicacin.setForeground(colores.getAmarillo());
		
		JLabel lblNewLabel = new JLabel("Filrar por deporte y ubicación concretos");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblNewLabel.setBounds(228, 0, 388, 27);
		lblNewLabel.setForeground(colores.getAmarillo());
		panel_1.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(46, 139, 87));
		panel.setBounds(38, 11, 813, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Nombre Usuario
		
		btn_Usuario = new JButton();
		btn_Usuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btn_Usuario.setHorizontalAlignment(SwingConstants.LEADING);
		btn_Usuario.setBounds(79, 35, 165, 24);
		btn_Usuario.setBackground(colores.getNaranja());
		panel.add(btn_Usuario);
		
		
		
		lbl_IconoPerfil = new JLabel("");
		lbl_IconoPerfil.setBounds(10, 8, 59, 51);
		panel.add(lbl_IconoPerfil);
		
		cargarImagenPerfil();
		
		btnCerrarSesion = new JButton("Cerrar Sesi"+'ó'+"n");
		btnCerrarSesion.setBounds(80, 8, 120, 24);
		panel.add(btnCerrarSesion);
		btnCerrarSesion.setBackground(colores.getNaranja());
		
		//Botones
		
		btnCrearEvento = new JButton("Crear Evento");
		btnCrearEvento.setBounds(686, 8, 117, 46);
		panel.add(btnCrearEvento);
		btnCrearEvento.setBackground(colores.getNaranja());
		
		lbl_Sporter = new JLabel("");
		lbl_Sporter.setBounds(314, -86, 186, 151);
		int w = lbl_Sporter.getWidth();
		int h = lbl_Sporter.getHeight();
		lbl_Sporter.setIcon(new ImageIcon(imagenes.getLogo_solo_nombreEscalado(w, h)));
		panel.add(lbl_Sporter);
		
		//Tabla Eventos
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 164, 813, 465);
		scrollPane.setToolTipText("");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);

		
		btnUnirse = new JButton("Unirse");
		btnUnirse.setName("Unirse");
		String[] titulos = {
				"PROPIETARIO", "DEPORTE", "UBICACI"+'Ó'+"N", "PARTICIPANTES", "FECHA", "HORA", " "
			};
		modelo = new DefaultTableModel(null,titulos){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int filas, int columnas){
				return false;
			}
		};
		
		tablaEventos = new JTable();
		tablaEventos.setModel(modelo);
		tablaEventos.setBackground(Color.WHITE);
		tablaEventos.setShowVerticalLines(false);
		tablaEventos.getTableHeader().setReorderingAllowed(false);
		tablaEventos.setFocusable(false);
		tablaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaEventos.setFillsViewportHeight(false);
		tablaEventos.setDefaultRenderer(Object.class, new Render());
		tablaEventos.setPreferredScrollableViewportSize(tablaEventos.getPreferredSize());
		scrollPane.setViewportView(tablaEventos);
		
		cargarNombreBoton();
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 87, 886, 4);
		contentPane.add(separator);
		
		llenarTabla(persona.getLocalidad(), persona.getListDeporte(), persona.getId());
	}
	
	//Daniel: Metodo para cargar el nombre del botón. Hace falta para cuando se retorna de perfildeUsuario, por si hubiera alguna modificaión
	public void cargarNombreBoton() {
		btn_Usuario.setText(persona.getNombre());
	}
	
	public void controlVentanaPrincipal(ActionListener ctrl) {
		btnCerrarSesion.addActionListener(ctrl);
		btnCerrarSesion.setActionCommand("Cerrar Sesion");
		
		btnCrearEvento.addActionListener(ctrl);
		btnCrearEvento.setActionCommand("Crear Evento");
		
		btn_Usuario.addActionListener(ctrl);
		btn_Usuario.setActionCommand("Perfil Usuario");
		
		btnBuscar.addActionListener(ctrl);
		btnBuscar.setActionCommand("Buscar Evento");
		
		btnUnirse.addActionListener(ctrl);
		btnUnirse.setActionCommand("Unirse");
	}
	public void controladorBotonesTable(MouseListener ctrl) {
		tablaEventos.addMouseListener(ctrl);
	}
	
	public void cargarImagenPerfil() {
		int w = lbl_IconoPerfil.getWidth();
		int h = lbl_IconoPerfil.getHeight();
		imagenes = new Imagenes(persona.getUrl());
		if(persona.getUrl() == null || persona.getUrl().equals("")) {
			lbl_IconoPerfil.setIcon( new ImageIcon(imagenes.getIconoUsuarioEscalado(w, h)));
		}else {
			lbl_IconoPerfil.setIcon( new ImageIcon(imagenes.getIconoPerfilEscalado(w, h)));
		}
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
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public void cerrarSesion() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dispose();
					Conexion conexion = new Conexion();
					command = conexion.getcommand();
					VentanaLogin frame = new VentanaLogin();
					CtrlVentanaLogin ctrl = new CtrlVentanaLogin(frame);
					frame.controlVentana(ctrl);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void crearEvento() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFrmCrearEvento vista = new VentanaFrmCrearEvento(persona);
					CtrlVentanaCrearEvento ctrl = new CtrlVentanaCrearEvento(vista); // Primero te creas el controlador y le metes la vista
					vista.controlVentana(ctrl); // Segundo: el metodo de la vista controlador le metes el controlador anteriormente creado
					vista.controlChoise(ctrl);
					vista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void verVentanaUsuario() throws Exception{
		VentanaPerfilUsuario frame = new VentanaPerfilUsuario(this , persona);
		CtrlVentanaPerfilUsuario ctrl = new CtrlVentanaPerfilUsuario(frame);
		frame.controladorVista(ctrl);
		frame.controladorBotonesTable(ctrl);
		frame.setVisible(true);
	}
	public void llenarTabla(String ubicacion, List<String> deportes, int idUsuario) throws SQLException{
		List<Evento> listEventos = new ArrayList<Evento>();
		Evento evento = new Evento(command);
		for(int x = 0 ; x < deportes.size(); x++) {
			Deporte deport = new Deporte(command);
			int deportex = deport.obtenerIdDeporte(deportes.get(x));
			listEventos = evento.getListEventos(ubicacion, idUsuario, deportex);
			for(int i = 0; i < listEventos.size(); i++){
				Object[] informacion = new Object[7]; 
				String date = listEventos.get(i).getFecha();
				String fecha = date.substring(0, 10);
				String hora = date.substring(11, 19);

				informacion[0] = evento.getNombreUsuario(listEventos.get(i).getOrganiza(), listEventos.get(i).getId());
				informacion[1] = evento.getNombreDeporte(listEventos.get(i).getDeporte(), listEventos.get(i).getId());
				informacion[2] = listEventos.get(i).getUbicacion();;
				informacion[3] = listEventos.get(i).getNumParticipantesActivos(listEventos.get(i).getId())+ "/"+listEventos.get(i).getNumeroParticipantes();
				informacion[4] = fecha;
				informacion[5] = hora;
				informacion[6] = btnUnirse;
				modelo.addRow(informacion);
			}
		}
		if(tablaEventos.getRowCount() == 0) {
			Object[] Vacio = {null, null, "No", "hay", "eventos", null, null};
			modelo.addRow(Vacio);
		}
	}
	public void llenarTablaBuscar() throws SQLException{
		modelo.setRowCount(0);
		List<Evento> listEventos = new ArrayList<Evento>();
		Evento evento = new Evento(command);
		Deporte deportes = new Deporte(command);
		int iddeporte = deportes.obtenerIdDeporte(choice_Deporte.getSelectedItem());
		String ubicacion = choice_Ubicacion.getSelectedItem();
		
		listEventos = evento.getListEventos(ubicacion, this.persona.getId(), iddeporte);
		for(int i = 0; i < listEventos.size(); i++){
			Object[] informacion = new Object[7]; 
			String date = listEventos.get(i).getFecha();
			String fecha = date.substring(0, 10);
			String hora = date.substring(11, 19);

			informacion[0] = evento.getNombreUsuario(listEventos.get(i).getOrganiza(), listEventos.get(i).getId());
			informacion[1] = evento.getNombreDeporte(listEventos.get(i).getDeporte(), listEventos.get(i).getId());
			informacion[2] = listEventos.get(i).getUbicacion();;
			informacion[3] = listEventos.get(i).getNumParticipantesActivos(listEventos.get(i).getId())+ "/"+listEventos.get(i).getNumeroParticipantes();
			informacion[4] = fecha;
			informacion[5] = hora;
			informacion[6] = btnUnirse;
			modelo.addRow(informacion);
		}
		if(tablaEventos.getRowCount() == 0) {
			Object[] Vacio = {null, null, "No", "hay", "eventos", null, null};
			modelo.addRow(Vacio);
		}
	}
	public void Unirse() throws SQLException{
		String nombrePropietario = (String) tablaEventos.getModel().getValueAt(tablaEventos.getSelectedRow(), 0);
		String nombreDeporte = (String) tablaEventos.getModel().getValueAt(tablaEventos.getSelectedRow(), 1);
		String nombreUbicacion = (String) tablaEventos.getModel().getValueAt(tablaEventos.getSelectedRow(), 2);
		String fecha = (String) tablaEventos.getModel().getValueAt(tablaEventos.getSelectedRow(), 4);
		String hora = (String) tablaEventos.getModel().getValueAt(tablaEventos.getSelectedRow(), 5);
		
		String fechaHora = fecha + " " + hora; 
		
		Deporte deporte = new Deporte(command);
		int idDeporte = deporte.obtenerIdDeporte(nombreDeporte);
		int numParticipantes = deporte.obtenerNumParticipanteDeporte(nombreDeporte);
		
		Evento evento = new Evento(command);
		int idEvento = evento.getIdeventoUnido(fechaHora,nombrePropietario,idDeporte,nombreUbicacion);
		int numParticipantesActivo = evento.getNumParticipantesActivos(idEvento);
		
		if(numParticipantes == numParticipantesActivo) {
			JOptionPane.showMessageDialog(this, "Evento Lleno","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
		}else {
			evento.unirse(persona, idEvento);
			modelo.removeRow(tablaEventos.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Te has unido al evento\n"
					+ "Podr"+'á'+" visualizarlo desde su perfil.","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
		}
	}
}
