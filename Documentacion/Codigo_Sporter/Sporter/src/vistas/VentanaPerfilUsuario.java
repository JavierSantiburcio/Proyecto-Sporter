//Daniel Cuevas Perez - Ventana de Perfil de Usuario
package vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import colores.Colores;
import conexion.Conexion;
import controlador.CtrlVentanaFrmLogin;
import controlador.CtrlVentanaPerfilUsuario;
import imagenes.Imagenes;
import modelo.Evento;
import modelo.Persona;
import render.Render;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.Choice;


public class VentanaPerfilUsuario extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lbl_Icono, lbl_NombreUsuario, lblLocalidad, lblDeporte,lblEmail,lbl_InfoLocalidad,lbl_InfoEmail,lblHistorial;
	private static Persona persona;
	protected static Statement command;
	private static Colores colores = new Colores();
	private Imagenes imagenes = new Imagenes();
	public JTable table;
	private JButton btn_ModificarPerfil, btnReturn,btnCancelar,btnSalir;
	private DefaultTableModel modeloTabla;
	private JLabel lblNewLabel;
	private Choice choiceDeportes;
	private java.awt.List list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion conexion = new Conexion();
					command = conexion.getcommand();
					persona = new Persona(command,"DanielCP89@gmail.com");
					VentanaPerfilUsuario frame = new VentanaPerfilUsuario(persona);
					CtrlVentanaPerfilUsuario ctrl = new CtrlVentanaPerfilUsuario(frame);
					frame.controladorVista(ctrl);
					frame.controladorBotonesTable(ctrl);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public VentanaPerfilUsuario(Persona persona) throws Exception {
		
//------------------------------------------------------- INICIO ESTRUCTURA VISTA ------------------------------------------------//		
		setTitle("Sporter - Perfil Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(64, 191, 119));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl_Icono = new JLabel("");
		lbl_Icono.setBounds(24, 11, 30, 23);
		int w = lbl_Icono.getWidth();
		int h = lbl_Icono.getHeight();
		lbl_Icono.setIcon(new ImageIcon(imagenes.getIconoUsuarioEscalado(w, h)));
		contentPane.add(lbl_Icono);
		
		lbl_NombreUsuario = new JLabel();
		lbl_NombreUsuario.setBounds(64, 11, 241, 23);
		contentPane.add(lbl_NombreUsuario);
		
		btn_ModificarPerfil = new JButton("Modificar Perfil");
		btn_ModificarPerfil.setBounds(788, 11, 143, 23);
		btn_ModificarPerfil.setBackground(colores.getNaranja());
		contentPane.add(btn_ModificarPerfil);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 45, 954, 9);
		contentPane.add(separator);
		
		lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLocalidad.setBounds(10, 81, 65, 23);
		lblLocalidad.setForeground(colores.getAmarillo());
		contentPane.add(lblLocalidad);
		
		lblDeporte = new JLabel("Deportes:");
		lblDeporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeporte.setBounds(301, 81, 72, 23);
		lblDeporte.setForeground(colores.getAmarillo());
		contentPane.add(lblDeporte);
		
		choiceDeportes = new Choice();
		choiceDeportes.setFont(new Font("Dialog", Font.BOLD, 12));
		choiceDeportes.setBounds(379, 81, 205, 23);
		contentPane.add(choiceDeportes);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(661, 81, 45, 23);
		lblEmail.setForeground(colores.getAmarillo());
		contentPane.add(lblEmail);
		
		lbl_InfoLocalidad = new JLabel("New label");
		lbl_InfoLocalidad.setBounds(85, 81, 108, 23);
		contentPane.add(lbl_InfoLocalidad);
		
		lbl_InfoEmail = new JLabel("New label");
		lbl_InfoEmail.setBounds(716, 81, 215, 23);
		contentPane.add(lbl_InfoEmail);
		
		lblHistorial = new JLabel("Historial de Eventos");
		lblHistorial.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setBounds(24, 153, 909, 14);
		lblHistorial.setForeground(colores.getAmarillo());
		contentPane.add(lblHistorial);
		
		//Botones de la tabla
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setName("c");
		btnSalir = new JButton("Salir");
		btnSalir.setName("s");
		
		String t[] ={"Nº","FECHA","HORA","PROPIETARIO","DEPORTE","UBICACIÓN","NºPARTICIPANTES",""}; //Almaceno las columnas en el DefaultTableModel y hago que no sean editable las celdas
		modeloTabla = new DefaultTableModel(null,t){
	
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int filas, int columnas){
				return false;
			}
		};
		
		table = new JTable();
		table.setModel(modeloTabla);
		table.setBounds(80, 121, 472, 360);
		table.setBackground(Color.WHITE);
		table.getTableHeader().setReorderingAllowed(false); //No permite mover las columnas
		table.setFocusable(false); //Quito el focus de las celdas, para que cuando seleccione una celda se vea toda la fila seleccionada y no el focus de la celda
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(false); //No deja espacio abajo cuando se van eliminando filas.
		table.getColumnModel().getColumn(0).setMaxWidth(50); //configuro tama�o maximo de la columna 
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(200); 
		table.getColumnModel().getColumn(3).setMaxWidth(350);
		table.getColumnModel().getColumn(4).setMaxWidth(350); 
		table.getColumnModel().getColumn(5).setMaxWidth(350);
		table.getColumnModel().getColumn(6).setMaxWidth(350); 
		table.getColumnModel().getColumn(7).setMaxWidth(200); 
		table.setDefaultRenderer(Object.class, new Render()); // Para centrar valores de las celdas
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(24, 178, 909, 197);
		scrollPane_1.setViewportView(table);
		contentPane.add(scrollPane_1);
		
		btnReturn = new JButton("Volver al tablón");
		btnReturn.setBounds(379, 400, 205, 23);
		btnReturn.setBackground(colores.getNaranja());
		contentPane.add(btnReturn);
		
		lblNewLabel = new JLabel("Datos Usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(10, 56, 954, 14);
		lblNewLabel.setForeground(colores.getAmarillo());
		contentPane.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 110, 954, 9);
		contentPane.add(separator_1);
		
		
		cargarDatosLabel();
		generarContenidoTabla();
		
	}
//------------------------------------------------------------ FIN INICIO ESTRUCTURA VISTA -------------------------------------------------------//
	
//------------------------------------------------------------ LOS CONTROLADORES BOTONES ---------------------------------------------------------//
		//CONTROLADOR DE BOTONES
		public void controladorVista(ActionListener ctrl){
			
			btnReturn.addActionListener(ctrl);
			btnReturn.setActionCommand("VOLVER");
			
			btn_ModificarPerfil.addActionListener(ctrl);
			btn_ModificarPerfil.setActionCommand("MODIFICAR");
			
			btnCancelar.addActionListener(ctrl);
			btnCancelar.setActionCommand("CANCELAR");
			
			btnSalir.addActionListener(ctrl);
			btnSalir.setActionCommand("SALIR");
		}
		
		public void controladorBotonesTable(MouseListener ctrl) {
			table.addMouseListener(ctrl);
		}
		
		
//---------------------------------------------------------------- FIN CONTROLADORES ----------------------------------------------------------------//
		
//----------------------------------------------------------------- METODOS AUXILIARES --------------------------------------------------------------//	
	
	//Metodo para cargar los datos de los JLabel
	public void cargarDatosLabel() throws SQLException {
		lbl_NombreUsuario.setText(persona.getNombre());
		lbl_InfoLocalidad.setText(persona.getLocalidad());
		lbl_InfoEmail.setText(persona.getEmail());
		
		for(String item : persona.getListDeporte()) {
			choiceDeportes.add(item);
		}
		             
	}
	
	//METODO QUE GENERA EL CONTENIDO DE LA TABLA CUANDO SE INICIA LA VISTA
	public void generarContenidoTabla() throws Exception {
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		
		List<Evento> listEventos = new ArrayList<Evento>();
		Evento evento = new Evento(command);
		listEventos = evento.getListEventos(persona.getId()); // Almaceno una lista con todos los objetos de eventos de la base da datos
		
		for (int i = 0; i < listEventos.size(); i++) {// En el bucle voy a�adiendo filas 
			Object[] rowData = new Object[8];
			
			String propietario = evento.getNombreUsuario(listEventos.get(i).getOrganiza(), listEventos.get(i).getId());
			String deporte = evento.getNombreDeporte(listEventos.get(i).getDeporte(), listEventos.get(i).getId()); 
			
			String date = listEventos.get(i).getFecha();
			String fecha = date.substring(0, 10);
			String hora = date.substring(11, 19);
			
			rowData[0] = listEventos.get(i).getId();
			rowData[1] = fecha;
			rowData[2] = hora;
			rowData[3] = propietario;
			rowData[4] = deporte;		
			rowData[5] = listEventos.get(i).getUbicacion();
			rowData[6] = listEventos.get(i).getNumParticipantesActivos(listEventos.get(i).getId())+ "/"+listEventos.get(i).getNumeroParticipantes();
			if(listEventos.get(i).getOrganiza() == persona.getId()) {
				rowData[7] = btnCancelar;
			}else {
				rowData[7] = btnSalir;
			}
			
			modeloTabla.addRow(rowData); // A�ado la fila al DafaultModel con el array rowData 
		}
	}

	public void volver() {
		this.dispose();
		
	}
	
	public void limpiarTabla() {
		int num = modeloTabla.getRowCount()-1;
		System.out.println(num);
		for(int i = num; i>=0;i--) {
			modeloTabla.removeRow(i);
		}
	}

	public void cancelarEvento() throws Exception {
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0); // obtengo valor ID de la fila seleccionada
		Evento evento = new Evento(command,id);
		evento.borrarevento(persona);
		modeloTabla.removeRow(table.getSelectedRow());// Elimina la fila que getSelectedRow() devuelve.
		JOptionPane.showMessageDialog(this, "El evento deportivo "+evento.getId()+" se ha cancelado correctamente","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
	}

	public void salirEvento() throws SQLException {
		Conexion conexion = new Conexion();
		command = conexion.getcommand();
		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0); // obtengo valor ID de la fila seleccionada
		Evento evento = new Evento(command,id);
		evento.dejarEvento(persona);
		modeloTabla.removeRow(table.getSelectedRow());// Elimina la fila que getSelectedRow() devuelve.
		JOptionPane.showMessageDialog(this, "Has abandonado el evento deportivo "+ evento.getId() +" correctamente","Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
	}

	//Daniel: Navegación a la ventana del formulario de modificar perfil de Javi
	public void nuevaVentana() throws SQLException {
		VentanaFormularioLogin nuevaVentana = new VentanaFormularioLogin(persona, true);
		CtrlVentanaFrmLogin ctrl = new CtrlVentanaFrmLogin(nuevaVentana, true);
		nuevaVentana.controlVentana(ctrl);
		nuevaVentana.setVisible(true);
	}
//----------------------------------------------------------------- FIN  METODOS AUXILIARES ---------------------------------------------------------------------------//	
}
