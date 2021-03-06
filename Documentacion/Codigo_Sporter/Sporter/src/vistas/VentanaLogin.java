// Autor: Francisco Javier Santiburcio Vicente

package vistas;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import colores.Colores;
import conexion.Conexion;
import controlador.CtrlVentanaAdministrador;
import controlador.CtrlVentanaFrmLogin;
import controlador.CtrlVentanaLogin;
import controlador.CtrlVentanaPrincipal;
import imagenes.Imagenes;
import modelo.Administrador;
import modelo.Persona;
import modelo.Usuario;

public class VentanaLogin extends JFrame {
	private static Colores colores = new Colores();
	private Imagenes imagenes = new Imagenes();
	protected static Statement command;
	private JPanel contentPane;
	private JTextField field_usuario;
	private JPasswordField field_contrasenia;
	private JButton boton_iniciarSesion, boton_crearUsuario;
	private boolean error = false;

	// Lanza la ventana
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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

	// Estética de la ventana
	public VentanaLogin() throws ParseException {
		setResizable(false);


		//Creamos el controlador 
		setTitle("Sporter");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setForeground(colores.getNaranja());
		setBackground(colores.getNaranja());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 388);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(colores.getVerde());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		// Panel para contener los campos de usuario y contrasenia
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(92, 183, 395, 64);
		panel_1.setBackground(colores.getVerde());
		contentPane.add(panel_1);
		panel_1.setLayout(null);


		// Campos Usuario 
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(0, 5, 61, 16);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(colores.getAmarillo());

		field_usuario = new JTextField();
		field_usuario.setBounds(110, 0, 189, 26);
		panel_1.add(field_usuario);
		field_usuario.setColumns(10);

		// Campos Contrasenia
		JLabel lblNewLabel_1 = new JLabel("Contrase"+'ñ'+"a:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(0, 43, 93, 16);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(colores.getAmarillo());

		field_contrasenia = new JPasswordField();
		field_contrasenia.setBounds(110, 38, 189, 26);
		panel_1.add(field_contrasenia);
		field_contrasenia.setColumns(10);

		// Campos Botones de registro 
		boton_crearUsuario = new JButton("Registrarme");
		boton_crearUsuario.setBounds(84, 276, 183, 29);
		boton_crearUsuario.setBackground(colores.getNaranja());
		contentPane.add(boton_crearUsuario);

		boton_iniciarSesion = new JButton("Iniciar sesi"+'ó'+"n");

		// Por defecto no está habilitado
		boton_iniciarSesion.setEnabled(true);

		boton_iniciarSesion.setBounds(312, 276, 183, 29);
		boton_iniciarSesion.setBackground(colores.getNaranja());
		contentPane.add(boton_iniciarSesion);

		// Logo de Sporter
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(180, 6, 220, 200);

		int width = lblNewLabel_2.getWidth();
		int height = lblNewLabel_2.getHeight();
		lblNewLabel_2.setIcon(new ImageIcon(imagenes.getLogo_sin_fondoEscalado(width, height)));
		contentPane.add(lblNewLabel_2);

		if(error) {
			// Campo mensaje de error
			JPanel panel = new JPanel();
			panel.setForeground(Color.BLACK);
			panel.setBounds(6, 334, 261, 26);
			panel.setBackground(colores.getNaranja());	
			contentPane.add(panel);

			// Por defecto desactivado
			panel.setVisible(error);

			JLabel lblNewLabel_3 = new JLabel("Usuario o contrase"+'ñ'+"a incorrecto");
			panel.add(lblNewLabel_3);
		}

	}

	// Controlador

	public void controlVentana(ActionListener ctrl) {
		boton_iniciarSesion.addActionListener(ctrl);
		boton_iniciarSesion.setActionCommand("INICIAR SESION");

		boton_crearUsuario.addActionListener(ctrl);
		boton_crearUsuario.setActionCommand("CREAR USUARIO");
	}

	// 
	@SuppressWarnings("deprecation")
	public void iniciarSesion() throws SQLException {
		boolean sesion = false;
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		try {
			Persona persona = new Persona(command, field_usuario.getText());
			if(persona.getExistente()) {
				if(persona.confirmarContrasenia(field_contrasenia.getText())) {
					sesion = true;
					irVentanaPrincipal(persona);
				}else {
					JOptionPane.showMessageDialog(this, "Usuario o contrase"+'ñ'+"a incorrectos","Atenci"+'ó'+"n", JOptionPane.WARNING_MESSAGE, null);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Usuario o contrase"+'ñ'+"a incorrectos","Atenci"+'ó'+"n", JOptionPane.WARNING_MESSAGE, null);
			}
		}catch(RuntimeException e) {
			Administrador administrador = new Administrador(command, field_usuario.getText());
			if(administrador.confirmarContrasenia(field_contrasenia.getText())) {
				sesion = true;
				irVentanaAdmin(administrador);
			}else {
				JOptionPane.showMessageDialog(this, "Usuario o contrase"+'ñ'+"a incorrectos","Atenci"+'ó'+"n", JOptionPane.WARNING_MESSAGE, null);
			}
		}catch(SQLException e1) {
			JOptionPane.showMessageDialog(this, "Usuario o contrase"+'ñ'+"a incorrectos","Atenci"+'ó'+"n", JOptionPane.WARNING_MESSAGE, null);
		}

	}



	private void irVentanaAdmin(Administrador administrador) {
		try {
			dispose();
			VentanaAdministrador frame = new VentanaAdministrador(administrador);
			CtrlVentanaAdministrador crtl= new CtrlVentanaAdministrador(frame);
			frame.controladorVista(crtl);
			frame.controladorBotonesTable(crtl);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void irVentanaPrincipal(Persona persona) {
		try {
			dispose();
			VentanaPrincipal frame = new VentanaPrincipal(persona);
			CtrlVentanaPrincipal ctrl = new CtrlVentanaPrincipal(frame);
			frame.controladorBotonesTable(ctrl);
			frame.controlVentanaPrincipal(ctrl);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearUsuario() {
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
