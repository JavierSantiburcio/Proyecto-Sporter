// Daniel Cuevas P�rez

package modelo;

import java.util.Arrays;
import java.util.List;

public class Ubicacion {
	
	private List<String> listUbicacion;
	
	public Ubicacion() {
		
		listUbicacion = Arrays.asList(
					"A Coruña", "�lava","Albacete","Alicante","Almer�a","Asturias","�vila","Badajoz","Baleares","Barcelona","Burgos","C�ceres",
					"C�diz","Cantabria","Castell�n","Ciudad Real","C�rdoba","Cuenca","Girona","Granada","Guadalajara","Gipuzkoa","Huelva","Huesca","Ja�n","La Rioja",
					"Las Palmas","Le�n","L�rida","Lugo","Madrid","M�laga","Murcia","Navarra","Ourense","Palencia","Pontevedra","Salamanca","Segovia","Sevilla","Soria","Tarragona",
					"Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"
					); 
	}
	
	public List<String> getListUbicacion(){
		return listUbicacion;
	}
}