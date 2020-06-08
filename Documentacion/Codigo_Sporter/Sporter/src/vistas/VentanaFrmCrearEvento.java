// Autor : Daniel Cuevas Pérez - Ventana formulario crear evento

package vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import colores.Colores;
import conexion.Conexion;
import imagenes.Imagenes;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.Choice;


public class VentanaFrmCrearEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private Persona persona;
	protected static Statement command;
	private static Colores colores = new Colores();
	private JPanel contentPane;
	private JTextField textField_Propietario,textField_anio,textField_numPart;
	private JButton button_CrearEvento, button_Cancelar;
	private Choice choice_Deporte,choice_Ubicacion;
	private JLabel lblPropietario,lblDeporte,lblUbicacion,lblFecha,lblHora,lblNumeroParticipantes;
	private JTextField textField_mes;
	private JTextField textField_dia;
	private JTextField textField_hora;
	private JTextField textField_minuto;
	private Imagenes imagenes = new Imagenes();
	
//------------------------------------------------------------ INICIO ESTRUCTURA VENTANA ---------------------------------------------------------//
	
	public VentanaFrmCrearEvento(Persona persona) throws SQLException {
		
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		
		this.persona = persona;
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Sporter - Crear Evento Deportivo");
		setIconImage(imagenes.getLogo_sin_nombreEscalado(16, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 445);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 191, 119));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPropietario = new JLabel("PROPIETARIO");
		lblPropietario.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		lblDeporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeporte.setBounds(33, 80, 236, 14);
		lblDeporte.setForeground(colores.getAmarillo());
		contentPane.add(lblDeporte);
		
		lblUbicacion = new JLabel("UBICACIÓN");
		lblUbicacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUbicacion.setBounds(33, 140, 236, 14);
		lblUbicacion.setForeground(colores.getAmarillo());
		contentPane.add(lblUbicacion);
		
		lblFecha = new JLabel("FECHA (dd/mm/aaaa)");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha.setBounds(33, 200, 236, 14);
		lblFecha.setForeground(colores.getAmarillo());
		contentPane.add(lblFecha);
		
		textField_anio = new JTextField();
		textField_anio.setToolTipText("");
		textField_anio.setColumns(10);
		textField_anio.setBounds(155, 220, 44, 20);
		contentPane.add(textField_anio);
		
		lblHora = new JLabel("HORA (hh:mm)");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHora.setBounds(33, 260, 236, 14);
		lblHora.setForeground(colores.getAmarillo());
		contentPane.add(lblHora);
		
		lblNumeroParticipantes = new JLabel("NºJUGADORES");
		lblNumeroParticipantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroParticipantes.setBounds(285, 80, 90, 14);
		lblNumeroParticipantes.setForeground(colores.getAmarillo());
		contentPane.add(lblNumeroParticipantes);
		
		textField_numPart = new JTextField();
		textField_numPart.setColumns(10);
		textField_numPart.setBounds(285, 100, 90, 20);
		textField_numPart.setEditable(false);
		contentPane.add(textField_numPart);
		
		
		button_CrearEvento = new JButton("Crear");
		button_CrearEvento.setBounds(236, 333, 90, 22);
		button_CrearEvento.setBackground(colores.getNaranja());
		contentPane.add(button_CrearEvento);
		
		button_Cancelar = new JButton("Cancelar");
		button_Cancelar.setBounds(84, 333, 90, 22);
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
		
		JLabel lbl_separadorFecha = new JLabel("/");
		lbl_separadorFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_separadorFecha.setBounds(83, 220, 7, 19);
		contentPane.add(lbl_separadorFecha);
		
		textField_mes = new JTextField();
		textField_mes.setToolTipText("");
		textField_mes.setColumns(10);
		textField_mes.setBounds(95, 220, 44, 20);
		contentPane.add(textField_mes);
		
		textField_dia = new JTextField();
		textField_dia.setToolTipText("");
		textField_dia.setColumns(10);
		textField_dia.setBounds(33, 220, 44, 20);
		contentPane.add(textField_dia);
		
		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(143, 220, 7, 19);
		contentPane.add(lblNewLabel_1);
		
		textField_hora = new JTextField();
		textField_hora.setToolTipText("");
		textField_hora.setColumns(10);
		textField_hora.setBounds(33, 280, 44, 20);
		contentPane.add(textField_hora);
		
		textField_minuto = new JTextField();
		textField_minuto.setToolTipText("");
		textField_minuto.setColumns(10);
		textField_minuto.setBounds(95, 280, 44, 20);
		contentPane.add(textField_minuto);
		
		JLabel lbl_separadorHora = new JLabel(":");
		lbl_separadorHora.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_separadorHora.setBounds(84, 280, 7, 19);
		contentPane.add(lbl_separadorHora);
		
		cargarNumparticipantes();
		cargarFechaHora();
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
		
		public void crearEvento() throws SQLException, ParseException {
			int idDeporte;
			Deporte deporte = new Deporte(command);
			idDeporte = deporte.obtenerIdDeporte(choice_Deporte.getSelectedItem());

			//Obtengo los datos de los campos del formulario
			String ubicacion = choice_Ubicacion.getSelectedItem();

			String anios = textField_anio.getText();
			String mes = textField_mes.getText();
			String dias = textField_dia.getText();

			String horas = textField_hora.getText();
			String minutos = textField_minuto.getText();

			String fecha = anios + "-" + mes + "-" + dias;
			String hora = horas + ":" + minutos + ":00";
			//Obtengo la fecha en el formato de la BD
			String fechaHora = fecha + " " + hora;

			int numParticipantes = Integer.parseInt(textField_numPart.getText());

			//Controlo que los campos de texto de Fecha y Hora no estan vacios
			if(anios.equals("") || mes.equals("") || dias.equals("")) {

				JOptionPane.showMessageDialog(this, "Campo Fecha vac"+'í'+"o o incompleto.","ADVERTENCIA", JOptionPane.WARNING_MESSAGE, null);

			}else if(horas.equals("") || minutos.equals("") ){

				JOptionPane.showMessageDialog(this, "Campo Hora vac"+'í'+"o o incompleto.","ADVERTENCIA", JOptionPane.WARNING_MESSAGE, null);

			}else {

				Date dateUsuario = new Date();
				Date dateActual = new Date();

				DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// Obtengo la fecha del sistema con ese formato

				String fechaActual = formatoFecha.format(dateActual).toString();

				dateActual = formatoFecha.parse(fechaActual);
				dateUsuario = formatoFecha.parse(fechaHora);

				System.out.println(dateActual);
				System.out.println(dateUsuario);
				//Controlo que no pueda crear eventos con fecha y hora anteriores a la actual
				if(dateUsuario.before(dateActual)) {
					System.out.println(dateUsuario +" es anterior " +dateActual);
					JOptionPane.showMessageDialog(this, "No se pudo crear el evento.\n"
							+ "La fecha y hora introducida es anterior a la actual.","ADVERTENCIA", JOptionPane.WARNING_MESSAGE, null);
				}else {

					Evento evento = new Evento(command);
					//Obtengo todas las fechas de los eventos del usuario donde es creador o esta unido
					List<String> listFechasTodosEventos = evento.getFechaTodosEventos(persona.getId());

					System.out.println("Lista de fecha eventos usuario: "+listFechasTodosEventos.toString());
					System.out.println("Fecha introducida:" + fechaHora);

					//Compruebo que no se crea un evento en la misma franja horaria de otro evento creado o unido
					if(listFechasTodosEventos.contains(fechaHora)) {

						JOptionPane.showMessageDialog(this, "No se pudo crear el evento.\n"
								+ "Tiene un evento en la misma franja horaria.","ADVERTENCIA", JOptionPane.WARNING_MESSAGE, null);

					} else {

						evento.crearEvento(persona, idDeporte, ubicacion, fechaHora, numParticipantes);
						JOptionPane.showMessageDialog(this, "El evento deportivo se ha creado correctamente.\n"
								+ "Podr"+'á'+" visualizarlo desde su perfil.","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
						
						cerrarVentana();

					}
				}
			}
		}
		
		
		//Daniel: metodo para cargar la fecha y la hora actual del sistema y ayudar al usuario
		public void cargarFechaHora(){
			Date date = new Date();
			
			DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss"); // Obtengo la hora del sistema con ese formato
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// Obtengo la fecha del sistema con ese formato
		
			
			String horaCompleta = hourFormat.format(date).toString();
			String fechaCompleta = dateFormat.format(date).toString();
		
			String anio = fechaCompleta.substring(0, 4);
			String meses = fechaCompleta.substring(5, 7);
			String dias = fechaCompleta.substring(8, 10);
			
			String horas = horaCompleta.substring(0,2);
			String minutos = horaCompleta.substring(3,5);
			
			textField_hora.setText(horas);
			textField_minuto.setText(minutos);
			textField_anio.setText(anio);
			textField_mes.setText(meses);
			textField_dia.setText(dias);
			
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
}
