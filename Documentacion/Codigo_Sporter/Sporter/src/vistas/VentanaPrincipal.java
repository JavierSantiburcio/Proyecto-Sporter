package vistas;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;

import colores.Colores;
import conexion.Conexion;
import controlador.CtrlVentanaCrearEvento;
import controlador.CtrlVentanaLogin;
import imagenes.Imagenes;
import modelo.*;
import render.Render;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable tablaEventos;
	protected static Statement command;
	private ArrayList<Evento> listaEventos;
	private Persona persona;
	private static Colores colores = new Colores();
	private Imagenes imagenes = new Imagenes();
	private JButton btnCerrarSesion, btnCrearEvento, btnBuscar;
	private DefaultTableModel modelo = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion conexion = new Conexion();
					command = conexion.getcommand();
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(Persona persona) throws SQLException{

		//Estetica ventana
		setResizable(false);
		setTitle("Sporter");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 612);
		contentPane = new JPanel();
		contentPane.setBackground(colores.getVerde());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnCrearEvento = new JButton("Crear Evento");
		btnCrearEvento.setBounds(15, 16, 117, 23);
		btnCrearEvento.setBackground(colores.getNaranja());
		
		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setBounds(456, 16, 117, 23);
		btnCerrarSesion.setBackground(colores.getNaranja());
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(460, 57, 103, 23);
		btnBuscar.setBackground(colores.getNaranja());
	
		JLabel lblIntroduzcaDeporte = new JLabel("Introduzca Deporte:");
		lblIntroduzcaDeporte.setBounds(15, 61, 117, 14);
		lblIntroduzcaDeporte.setForeground(colores.getAmarillo());
		
		textField = new JTextField();
		textField.setBounds(136, 58, 86, 20);
		textField.setColumns(10);
		
		JLabel lblIntroduzcaUbicacin = new JLabel("Introduzca Ubicaci\u00F3n:");
		lblIntroduzcaUbicacin.setBounds(232, 61, 128, 14);
		lblIntroduzcaUbicacin.setForeground(colores.getAmarillo());
		
		textField_1 = new JTextField();
		textField_1.setBounds(360, 58, 90, 20);
		textField_1.setColumns(10);
		
		JLabel lblUsuario = new JLabel(persona.getNombre());
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(230, 20, 216, 14);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 86, 516, 457);
		scrollPane.setToolTipText("");
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		

		JTable tablaEventos = new JTable();
		tablaEventos.setBorder(new MatteBorder(0, 0, 1, 1, (Color) Color.WHITE));
		tablaEventos.setShowVerticalLines(false);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.setName("Unirse");
		
		String[] titulos = {
				"Propietario", "Deporte", "Ubicacion", "Participantes", "fecha", " "
			};
		modelo.setColumnIdentifiers(titulos);
		tablaEventos.setModel(modelo);
		Object[] informacion = {null, null, null, null, null, btnUnirse};
		//es funcional pero falta unirlo a mysql
		/*
		for(int x = 0; x < listaEventos.size(); x++){
			informacion[0] = listaEventos.get(x).getId() + "";
			informacion[1] = listaEventos.get(x).getDeporte() + "";
			informacion[2] = listaEventos.get(x).getUbicacion() + "";
			informacion[3] = listaEventos.get(x).getNumeroParticipantes() + "";
			informacion[4] = listaEventos.get(x).getFecha() + "";
			modelo.addRow(informacion);
		}
		tablaEventos.setModel(modelo);
		*/
		//Tabla test
		for(int x = 0; x < 5; x++){
			informacion[0] = "0";
			informacion[1] = "1";
			informacion[2] = "2";
			informacion[3] = "3";
			informacion[4] = "4";
			modelo.addRow(informacion);
		}
		tablaEventos.setModel(modelo);
		
		tablaEventos.getTableHeader().setReorderingAllowed(false);
		tablaEventos.setDefaultRenderer(Object.class, new Render());
		tablaEventos.setPreferredScrollableViewportSize(tablaEventos.getPreferredSize());
		scrollPane.setViewportView(tablaEventos);
		
		contentPane.setLayout(null);
		contentPane.add(btnCrearEvento);
		contentPane.add(lblUsuario);
		contentPane.add(btnCerrarSesion);
		contentPane.add(lblIntroduzcaDeporte);
		contentPane.add(textField);
		contentPane.add(lblIntroduzcaUbicacin);
		contentPane.add(textField_1);
		contentPane.add(btnBuscar);
		contentPane.add(scrollPane);
	}
	public void controlVentanaPrincipal(ActionListener ctrl) {
		btnCerrarSesion.addActionListener(ctrl);
		btnCerrarSesion.setActionCommand("Cerrar Sesion");
		
		btnCrearEvento.addActionListener(ctrl);
		btnCrearEvento.setActionCommand("Crear Evento");
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
	public void crearEvento(Persona persona) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion conexion = new Conexion();
					command = conexion.getcommand();
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
}