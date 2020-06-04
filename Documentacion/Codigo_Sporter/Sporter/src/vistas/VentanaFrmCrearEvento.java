// Autor : Daniel Cuevas Pérez - Ventana formulario crear evento

package vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import colores.Colores;
import conexion.Conexion;
import modelo.Deporte;
import modelo.Evento;
import modelo.Persona;
import modelo.Ubicacion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.awt.Choice;


public class VentanaFrmCrearEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private Persona persona;
	protected static Statement command;
	private static Colores colores = new Colores();
	private JPanel contentPane;
	private JTextField textField_Propietario,textField_Fecha,textField_numPart, textField_Hora;
	private JButton button_CrearEvento, button_Cancelar;
	private Choice choice_Deporte,choice_Ubicacion;
	private JLabel lblPropietario,lblDeporte,lblUbicacion,lblFecha,lblHora,lblNumeroParticipantes;
	
//------------------------------------------------------------ INICIO ESTRUCTURA VENTANA ---------------------------------------------------------//
	
	public VentanaFrmCrearEvento(Persona persona) throws SQLException {
		
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		
		this.persona = persona;
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Sporter - Crear Evento Deportivo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 191, 119));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPropietario = new JLabel("PROPIETARIO");
		lblPropietario.setBounds(33, 20, 236, 14);
		lblPropietario.setForeground(colores.getAmarillo());
		contentPane.add(lblPropietario);
		
		textField_Propietario = new JTextField();
		textField_Propietario.setBounds(33, 40, 342, 20);
		textField_Propietario.setText(persona.getNombre());
		textField_Propietario.setEditable(false);
		contentPane.add(textField_Propietario);
		textField_Propietario.setColumns(10);
		
		lblDeporte = new JLabel("DEPORTE");
		lblDeporte.setBounds(33, 80, 236, 14);
		lblDeporte.setForeground(colores.getAmarillo());
		contentPane.add(lblDeporte);
		
		lblUbicacion = new JLabel("UBICACIÓN");
		lblUbicacion.setBounds(33, 140, 236, 14);
		lblUbicacion.setForeground(colores.getAmarillo());
		contentPane.add(lblUbicacion);
		
		lblFecha = new JLabel("FECHA (aaaa-mm-dd)");
		lblFecha.setBounds(33, 200, 236, 14);
		lblFecha.setForeground(colores.getAmarillo());
		contentPane.add(lblFecha);
		
		textField_Fecha = new JTextField();
		textField_Fecha.setToolTipText("");
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(33, 220, 342, 20);
		contentPane.add(textField_Fecha);
		
		lblHora = new JLabel("HORA (hh:mm:ss)");
		lblHora.setBounds(33, 260, 236, 14);
		lblHora.setForeground(colores.getAmarillo());
		contentPane.add(lblHora);
		
		textField_Hora = new JTextField();
		textField_Hora.setToolTipText("");
		textField_Hora.setColumns(10);
		textField_Hora.setBounds(33, 280, 342, 20);
		contentPane.add(textField_Hora);
		
		lblNumeroParticipantes = new JLabel("NºJUGADORES");
		lblNumeroParticipantes.setBounds(285, 80, 90, 14);
		lblNumeroParticipantes.setForeground(colores.getAmarillo());
		contentPane.add(lblNumeroParticipantes);
		
		textField_numPart = new JTextField();
		textField_numPart.setColumns(10);
		textField_numPart.setBounds(285, 100, 90, 20);
		textField_numPart.setEditable(false);
		contentPane.add(textField_numPart);
		
		
		button_CrearEvento = new JButton("Crear");
		button_CrearEvento.setBounds(84, 333, 90, 22);
		button_CrearEvento.setBackground(colores.getNaranja());
		contentPane.add(button_CrearEvento);
		
		button_Cancelar = new JButton("Cancelar");
		button_Cancelar.setBounds(236, 333, 90, 22);
		button_Cancelar.setBackground(colores.getNaranja());
		contentPane.add(button_Cancelar);
		
		choice_Deporte = new Choice();
		choice_Deporte.setBounds(33, 100, 236, 20);
		contentPane.add(choice_Deporte);
		
		
		choice_Ubicacion = new Choice();
		choice_Ubicacion.setBounds(33, 160, 342, 20);
		contentPane.add(choice_Ubicacion);
		
		cargarNombreUsuario();
		cargarChoiceDeporte(choice_Deporte);
		cargarChoiceUbicacion(choice_Ubicacion);
		cargarNumparticipantes();
	}


//--------------------------------------------------------- FIN INICIO ESTRUCTURA VENTANA -----------------------------------------------------------//
	
//----------------------------------------------------------------- CONTROLADORES -------------------------------------------------------------------//
		
		public void controlVentana(ActionListener ctrl){
			button_CrearEvento.addActionListener(ctrl);
			button_CrearEvento.setActionCommand("CREAR");
			
			button_Cancelar.addActionListener(ctrl);
			button_Cancelar.setActionCommand("CANCELAR");
		}
		
		
		public void controlChoise(ItemListener ctrl) {
			choice_Deporte.addItemListener(ctrl);
		}
		
		
//---------------------------------------------------------------- FIN CONTROLADORES ------------------------------------------------------//		
		
//---------------------------------------------------------------- METODOS AUXILIADRES ----------------------------------------------------//
		
		public void crearEvento() throws SQLException {
			int idDeporte;
			Deporte deporte = new Deporte(command);
			idDeporte = deporte.obtenerIdDeporte(choice_Deporte.getSelectedItem());
			String ubicacion = choice_Ubicacion.getSelectedItem();
			String fecha = textField_Fecha.getText();
			String hora = textField_Hora.getText();
			String fechaHora = fecha + " " + hora;
			int numParticipantes = Integer.parseInt(textField_numPart.getText());
			if(fecha.equals("")) {
				JOptionPane.showMessageDialog(this, "Campo Fecha vacío.","ADVERTENCIA", JOptionPane.WARNING_MESSAGE, null);
			}else if(hora.equals("")){
				JOptionPane.showMessageDialog(this, "Campo Hora vacío.","ADVERTENCIA", JOptionPane.WARNING_MESSAGE, null);
			}else {
				Evento evento = new Evento(command);
				evento.crearEvento(persona, idDeporte, ubicacion, fechaHora, numParticipantes);
				JOptionPane.showMessageDialog(this, "El evento deportivo se ha creado correctamente.","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
			}
		}
		
		//Daniel: Metodo para cargar el campo de texto nombre usuario del formulario
		public void cargarNombreUsuario() {
			textField_Propietario.setText(persona.getNombre());
		}
		
		//M�todo para actualizar el textField_NumeroParticipantes dependiendo del Choice_Deporte seleccionado
		public void cargarNumparticipantes() throws SQLException {
			Deporte deporte = new Deporte(command);
			String nombreDeporte = choice_Deporte.getSelectedItem();
			int num = deporte.obtenerNumParticipanteDeporte(nombreDeporte);
			textField_numPart.setText(String.valueOf(num));
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

		public void cerrarVentana() {
			this.dispose();
		}
//--------------------------------------------------------------- FIN  METODOS AUXILIADRES -----------------------------------------------------------//
		
		
		
}
