// Daniel Cuevas P�rez

package modelo;

import java.util.Arrays;
import java.util.List;

public class Ubicacion {
	
	private List<String> listUbicacion;
	
	public Ubicacion() {
		
		listUbicacion = Arrays.asList(
					"A Coruña", 'Á'+"lava","Albacete","Alicante","Almer"+'í'+"a","Asturias",'Á'+"vila","Badajoz","Baleares","Barcelona","Burgos","C"+'á'+"ceres",
					"C"+'á'+"diz","Cantabria","Castell"+'ó'+"n","Ciudad Real","C"+'ó'+"rdoba","Cuenca","Girona","Granada","Guadalajara","Gipuzkoa","Huelva","Huesca","Ja"+'é'+"n","La Rioja",
					"Las Palmas","Le"+'ó'+"n","L"+'é'+"rida","Lugo","Madrid","M"+'á'+"laga","Murcia","Navarra","Ourense","Palencia","Pontevedra","Salamanca","Segovia","Sevilla","Soria","Tarragona",
					"Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"
					); 
	}
	
	public List<String> getListUbicacion(){
		return listUbicacion;
	}
}