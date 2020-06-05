package imagenes;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Imagenes {
	private Image logo;
	private Image logo_sin_fondo;
	private Image logo_sin_nombre;
	private Image logo_solo_nombre;
	private Image icono_usuario;
	private Image iconoPerfil;
	private Image sporter;
	
	public Imagenes() {
		logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		logo_sin_fondo = new ImageIcon(this.getClass().getResource("/logo_sin_fondo.png")).getImage();
		logo_sin_nombre = new ImageIcon(this.getClass().getResource("/logo_sin_nombre.png")).getImage();
		logo_solo_nombre = new ImageIcon(this.getClass().getResource("/logo_solo_nombre.png")).getImage();
		icono_usuario = new ImageIcon(this.getClass().getResource("/icono_usuario.png")).getImage();
		sporter = new ImageIcon(this.getClass().getResource("/Sporter.png")).getImage();
	}
	
	public Imagenes(String url) {
		logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		logo_sin_fondo = new ImageIcon(this.getClass().getResource("/logo_sin_fondo.png")).getImage();
		logo_sin_nombre = new ImageIcon(this.getClass().getResource("/logo_sin_nombre.png")).getImage();
		logo_solo_nombre = new ImageIcon(this.getClass().getResource("/logo_solo_nombre.png")).getImage();
		icono_usuario = new ImageIcon(this.getClass().getResource("/icono_usuario.png")).getImage();
		sporter = new ImageIcon(this.getClass().getResource("/Sporter.png")).getImage();
		iconoPerfil = new ImageIcon(url).getImage();
	}
	
	 
	public Image getLogo() {
		// Devuelte la imagen del logo 
		
		return logo;
	}
	
	public Image getLogo_sin_fondo() {
		// Devuelve la imagen del logo sin fondo
		
		return logo_sin_fondo;
	}
	
	public Image getLogo_sin_nombre() {
		// Devuelve la imagen del logo sin nombre
		
		return logo_sin_nombre;
	}
	
	public Image getLogo_solo_nombre() {
		// Devuelve la imagen del nombre
		
		return logo_solo_nombre;
	}
	
	public Image getIconoUsuario() {
		// Devuelve la imagen del icono de usuario
		
		return icono_usuario;
	}
	
	public Image getLogoEscalado(int width, int height) {
		// Devuelte la imagen del logo escalada, los par�metros la x y la y
		
		return logo.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Image getLogo_sin_fondoEscalado(int width, int height) {
		// Devuelte la imagen del logo sin fondo escalada, los par�metros la x y la y
		
		return logo_sin_fondo.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Image getLogo_sin_nombreEscalado(int width, int height) {
		// Devuelte la imagen del logo sin nombre escalada, los par�metros la x y la y
		
		return logo_sin_nombre.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Image getLogo_solo_nombreEscalado(int width, int height) {
		// Devuelte la imagen del nombre escalada, los par�metros la x y la y
		
		return logo_solo_nombre.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Image getIconoUsuarioEscalado(int width, int height) {
		// Devuelte la imagen del nombre escalada, los par�metros la x y la y
		
		return icono_usuario.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Image getIconoPerfilEscalado(int width, int height) {
		// Devuelte la imagen del nombre escalada, los par�metros la x y la y
		
		return iconoPerfil.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Image getSporterEscalado(int width, int height) {
		// Devuelte la imagen del nombre escalada, los par�metros la x y la y
		
		return sporter.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
}
