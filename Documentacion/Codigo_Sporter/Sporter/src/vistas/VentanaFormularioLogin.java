//Autor: Francisco Javier Santiburcio Vicente

package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.sql.*;
import java.util.Arrays;

import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import colores.Colores;
import conexion.Conexion;
import controlador.CtrlVentanaFrmLogin;
import controlador.CtrlVentanaPerfilUsuario;
import imagenes.Imagenes;
import modelo.Deporte;
import modelo.Evento;
import modelo.Persona;
import modelo.Ubicacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Choice;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.List;
import java.awt.Font;




public class VentanaFormularioLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private Colores colores = new Colores();
	private Imagenes imagenes = new Imagenes();
	private JPanel contentPane;
	private JTextField textUsr, textEmail;
	private JPasswordField passwordField;
	private List listDeportes;
	private Choice choice_ubi;
	private JButton button_cancelar, button_crear;
	private Statement comando = new Conexion().getcommand();
	static final int MAX_TAM = 20;
	private String [] lDeportes = new String[MAX_TAM];
	private int nDeportes = 0;
	private String localizacion;
	private Persona persona;
	private boolean modificar;
	private VentanaPerfilUsuario ventanaPerfilUsuario;


	public VentanaFormularioLogin(VentanaPerfilUsuario vista, Persona persona, boolean modificar) throws SQLException{
		//this.persona = new Persona(comando, "jiji"); -----------PARA PRUEBAS
		//this.modificar = true;

		this.persona = persona; 
		this.modificar = modificar;
		this.ventanaPerfilUsuario = vista;

		setTitle("Sporter - Datos usuario");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 503);
		setLocationRelativeTo(vista);
		contentPane = new JPanel();
		contentPane.setBackground(colores.getVerde());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel para la etiqueta y campo USUARIO 
		JPanel panel = new JPanel();
		panel.setBounds(38, 25, 409, 51);
		panel.setBackground(colores.getVerde());
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre de usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 8, 130, 14);
		lblNewLabel.setForeground(colores.getAmarillo());
		panel.add(lblNewLabel);

		String campoUsr = "";
		if(modificar) {
			campoUsr = persona.getNombre();
		}
		textUsr = new JTextField(campoUsr);
		textUsr.setBounds(150, 6, 200, 20);
		panel.add(textUsr);
		textUsr.setColumns(10);

		Dimension size = textUsr.getSize();

		// Panel para la etiqueta y campo EMAIL
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(38, 87, 409, 51);
		panel_1.setBackground(colores.getVerde());
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 8, 130, 14);
		lblNewLabel_1.setForeground(new Color(255, 222, 89));
		panel_1.add(lblNewLabel_1);

		String campoEmail = "";
		if(modificar) {
			campoEmail = persona.getEmail();
		}
		textEmail = new JTextField(campoEmail);
		textEmail.setBounds(150, 5, 200, 20);
		if(modificar) textEmail.setEditable(false);
		panel_1.add(textEmail);
		textEmail.setColumns(10);

		// Panel para la etiqueta y campo CONTRASENIA 
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(38, 150, 409, 51);
		panel_1_1.setBackground(colores.getVerde());
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Contrase"+'ñ'+"a:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 8, 130, 14);
		lblNewLabel_1_1.setForeground(colores.getAmarillo());
		panel_1_1.add(lblNewLabel_1_1);

		String campoPsswd = "";
		if(modificar) {
			campoPsswd = persona.getPassword();
		}
		passwordField = new JPasswordField(campoPsswd);
		passwordField.setBounds(150, 5, 200, 20);
		panel_1_1.add(passwordField);
		passwordField.setColumns(10);


		// Panel para la etiqueta y la selecciÃ³n de DEPORTES
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 213, 409, 99);
		panel_2.setBackground(colores.getVerde());
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Deportes favoritos:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 36, 134, 14);
		lblNewLabel_2.setForeground(colores.getAmarillo());
		panel_2.add(lblNewLabel_2);

		listDeportes = new java.awt.List(5, true);
		listDeportes.setBounds(150, 10, 200, 74);
		listDeportes.setMultipleMode(true);
		panel_2.add(listDeportes);

		// Panel para la etiqueta y la selecciÃ³n de LOCALIZACION
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(38, 324, 409, 51);
		panel_2_1.setBackground(colores.getVerde());
		contentPane.add(panel_2_1);
		panel_2_1.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Localizaci"+'ó'+"n:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(10, 12, 134, 14);
		lblNewLabel_2_1.setForeground(colores.getAmarillo());
		panel_2_1.add(lblNewLabel_2_1);

		choice_ubi = new Choice();
		choice_ubi.setBounds(150, 12, 200, 20);
		panel_2_1.add(choice_ubi);


		// Panel para los botones
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(new Color(64, 191, 119));
		panel_2_1_1.setBounds(38, 387, 409, 51);
		contentPane.add(panel_2_1_1);

		button_cancelar = new JButton("Cancelar");
		button_cancelar.setBackground(colores.getNaranja());
		panel_2_1_1.add(button_cancelar);

		String textCrear;
		if(modificar) {
			textCrear = "Guardar cambios";
		}else{
			textCrear = "Crear perfil";
		}

		button_crear = new JButton(textCrear);
		button_crear.setBackground(colores.getNaranja());
		panel_2_1_1.add(button_crear);

		if(modificar) {
			cargarChoiceLocalizacionMod(choice_ubi);
			cargarListaDeportesMod(listDeportes);
		}else {
			cargarChoiceLocalizacion(choice_ubi);
			cargarListaDeportes(listDeportes);			
		}
	}

	// Controladores
	public void controlVentana(ActionListener ctrl) {
		button_crear.addActionListener(ctrl);
		button_crear.setActionCommand("CREAR");

		button_cancelar.addActionListener(ctrl);
		button_cancelar.setActionCommand("CANCELAR");
	}

	public void controlChoiceList(ItemListener ctrl) {
		listDeportes.addItemListener(ctrl);
		choice_ubi.addItemListener(ctrl);
	}

	// Getters para el cotrolador
	public java.awt.List getlistDeportes() {
		return listDeportes;
	}

	public Choice getChoiceUbi() {
		return choice_ubi;
	}

	// Seters para el controlador
	public void addDeportes(String deporte) {
		if(nDeportes + 1 == MAX_TAM) {
			lDeportes = Arrays.copyOf(lDeportes, MAX_TAM * 2);
		}
		lDeportes [nDeportes + 1] = deporte;
		nDeportes++;
	}

	public void setLocalizacion(String localizacion2) {
		localizacion = localizacion2;
	}

	// Cargamos las listas
	private void cargarChoiceLocalizacion(Choice choice_ubi2) {
		Ubicacion ub = new Ubicacion();
		java.util.List<String> listUbicacion = ub.getListUbicacion();

		for(String item : listUbicacion) {
			choice_ubi2.add(item);
		}

	}

	private void cargarListaDeportes(List listDeportes) throws SQLException {
		Deporte depo = new Deporte(comando);
		java.util.List<String> listDepo =  depo.obtenerListaDeporte();

		for(String item : listDepo) {
			listDeportes.add(item);
		}

	}

	private void cargarChoiceLocalizacionMod(Choice choice_ubi) {
		Ubicacion ub = new Ubicacion();
		java.util.List<String> listUbicacion = ub.getListUbicacion();
		//		listUbicacion.remove(persona.getLocalidad());

		choice_ubi.add(persona.getLocalidad());
		for(String item : listUbicacion) {
			choice_ubi.add(item);
		}
	}

	private void cargarListaDeportesMod(List listDeportes) throws SQLException {
		Deporte depo = new Deporte(comando);
		java.util.List<String> listDepo =  depo.obtenerListaDeporte();

		for(String item : listDepo) {
			listDeportes.add(item);
		}

		for(int i = 0; i < listDepo.size(); i++) {
			if(estaEnPersona(listDeportes.getItem(i), persona)) {
				listDeportes.select(i);
			}
		}

	}

	private boolean estaEnPersona(String deporte, Persona persona) throws SQLException {
		boolean esta = false;
		int i = 0; 
		java.util.List<String> deportes = persona.getListDeporte();
		while(i < deportes.size() && !esta) {
			if(deportes.get(i).compareTo(deporte) == 0) {
				esta = true;
			}else {
				i++;
			}
		}

		return esta;
	}

	// Metodo encargado de crear el perfil de usuario en la base de datos

	public void crearPerfil() throws SQLException {
		String usr = textUsr.getText();
		String email = textEmail.getText();
		localizacion = choice_ubi.getSelectedItem();
		@SuppressWarnings("deprecation")
		String password = passwordField.getText();
		Persona persona = new Persona(comando);  
		String [] deportes = listDeportes.getSelectedItems();

		persona.crearPerfil(usr, localizacion, email, password, deportes);
		
		JOptionPane.showMessageDialog(this, "Perfil creado correctamente.","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);

		this.cerrarVentana();
	}


	//Metodo encargado de modificar el perfil de un usario 
	public void modificarPerfil() throws Exception {
		String usr = textUsr.getText();
		String email = textEmail.getText();
		localizacion = choice_ubi.getSelectedItem();
		@SuppressWarnings("deprecation")
		String password = passwordField.getText();
		String [] deportes = listDeportes.getSelectedItems();

		persona.modificarPerfil(usr, localizacion, email, password, deportes); 
		
		ventanaPerfilUsuario.setPersona(persona);
		ventanaPerfilUsuario.eliminarItemsChoice();
		ventanaPerfilUsuario.cargarDatos();
		ventanaPerfilUsuario.limpiarTabla();
		ventanaPerfilUsuario.generarContenidoTabla();
		
		JOptionPane.showMessageDialog(this, "Datos modificados correctamente.","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
		this.cerrarVentana();
	}

	public void cerrarVentana() {
		this.dispose();
	}


}
