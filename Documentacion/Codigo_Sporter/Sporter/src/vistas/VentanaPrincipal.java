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
	private Imagenes imagenes = new Imagenes();
	private JButton btnCerrarSesion, btnCrearEvento, btnBuscar, lblUsuario, btnUnirse;
	private DefaultTableModel modelo = new DefaultTableModel();
	private Persona persona;

	public VentanaPrincipal(Persona persona) throws SQLException{
		//Conectar a MySQL
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		
		//Estetica ventana
		setPersona(persona);
		setResizable(false);
		setTitle("Sporter");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 612);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(colores.getVerde());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Botones
		
		btnCrearEvento = new JButton("Crear Evento");
		btnCrearEvento.setBounds(15, 16, 117, 23);
		btnCrearEvento.setBackground(colores.getNaranja());
		contentPane.add(btnCrearEvento);
		
		btnCerrarSesion = new JButton("Cerrar Sesi"+'ó'+"n");
		btnCerrarSesion.setBounds(472, 16, 120, 23);
		btnCerrarSesion.setBackground(colores.getNaranja());
		contentPane.add(btnCerrarSesion);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(489, 57, 103, 23);
		btnBuscar.setBackground(colores.getNaranja());
		contentPane.add(btnBuscar);
		
		//Opciones Busqueda
	
		JLabel lblIntroduzcaDeporte = new JLabel("Introduzca Deporte:");
		lblIntroduzcaDeporte.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblIntroduzcaDeporte.setBounds(15, 61, 97, 14);
		lblIntroduzcaDeporte.setForeground(colores.getAmarillo());
		
		choice_Deporte = new Choice();
		choice_Deporte.setBounds(113, 58, 109, 20);
		
		JLabel lblIntroduzcaUbicacin = new JLabel("Introduzca Ubicaci"+'ó'+"n:");
		lblIntroduzcaUbicacin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblIntroduzcaUbicacin.setBounds(228, 61, 103, 14);
		lblIntroduzcaUbicacin.setForeground(colores.getAmarillo());
		
		choice_Ubicacion = new Choice();
		choice_Ubicacion.setBounds(332, 58, 153, 20);

		contentPane.add(choice_Ubicacion);
		contentPane.add(lblIntroduzcaDeporte);
		contentPane.add(choice_Deporte);
		contentPane.add(lblIntroduzcaUbicacin);
		
		//Nombre Usuario
		
		lblUsuario = new JButton();
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(246, 20, 216, 14);
		lblUsuario.setBackground(null);
		lblUsuario.setBorderPainted(false);
		contentPane.add(lblUsuario);
		
		//Tabla Eventos
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 582, 457);
		scrollPane.setToolTipText("");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);

		
		btnUnirse = new JButton("Unirse");
		btnUnirse.setName("Unirse");
		String[] titulos = {
				"Propietario", "Deporte", "Ubicaci"+'ó'+"n", "Participantes", "Fecha", "Hora", " "
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
		cargarChoiceDeporte(choice_Deporte);
		cargarChoiceUbicacion(choice_Ubicacion);
		llenarTabla(persona.getLocalidad(), persona.getListDeporte(), persona.getId());
	}
	
	//Daniel: Metodo para cargar el nombre del botón. Hace falta para cuando se retorna de perfildeUsuario, por si hubiera alguna modificaión
	public void cargarNombreBoton() {
		lblUsuario.setText(persona.getNombre());
	}
	
	public void controlVentanaPrincipal(ActionListener ctrl) {
		btnCerrarSesion.addActionListener(ctrl);
		btnCerrarSesion.setActionCommand("Cerrar Sesion");
		
		btnCrearEvento.addActionListener(ctrl);
		btnCrearEvento.setActionCommand("Crear Evento");
		
		lblUsuario.addActionListener(ctrl);
		lblUsuario.setActionCommand("Perfil Usuario");
		
		btnBuscar.addActionListener(ctrl);
		btnBuscar.setActionCommand("Buscar Evento");
		
		btnUnirse.addActionListener(ctrl);
		btnUnirse.setActionCommand("Unirse");
	}
	public void controladorBotonesTable(MouseListener ctrl) {
		tablaEventos.addMouseListener(ctrl);
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
