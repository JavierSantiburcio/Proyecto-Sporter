# Diagrama de Clases - Sporter

## 1. Paquete Vista

La vista recibe la respuesta del controlador por la “InterfazVista” y se compone de las siguientes clases:

* **VentanaLogin**: es la primera ventana que se encuentra el usuario. Se compone de un pequeño formulario para introducir los datos de email y contraseña para iniciar sesion en la aplicación. Además contiene un botón de registrarse para navegar hacia el formulario de registro.
* **VentanaFrmLogin**: muestra un formulario donde el usuario tendrá que introducir sus datos personales para crear su perfil y registrarse en el sistema.
* **VentanaPrincipal**: es la vista principal de la aplicación, en la cual, se muestran todos los eventos a los que se puede unir.Ademas podrá acceder a todas las funcionalidades de la aplicación como: crear evento, unirse a un evento, entrar en el perfil de usuario y cerrar sesión;
* **VentanaFrmEvento**: formulario donde se recogen todos los datos para la creación de un evento.
* **VentanaPerfilUsuario**: ventana que muestra sus datos personales principales y los eventos, en los cuales, es propietario o participa en ellos. Además podrá acceder a un formulario de perfil donde podrá modificar sus datos personales.
* **VentanaPrincipalAdministrador**: ventana con privilegios, que muestra al administrador todos los eventos y usuarios del sistema.

## 2. Paquete Modelo

El modelo se comunica con el controlador por mediación de “InterfazModelo”y se compone de la siguientes clases:
* **Usuario**: clase padre que almacena los datos personales del usuario, estos datos serán heredados a la clase persona y administrador.
* **Persona**: clase que hereda de usuario y permite la creación y la modificación de un perfil. Una persona se relaciona con los eventos pudiendo organizar o participar en ningún o muchos eventos. Una persona puede practicar uno o más deportes. 
* **Evento**: esta clase contiene toda la información de un evento y las operaciones que se pueden realizar sobre él. Los eventos son organizador por una persona, que es el creador del evento (Creador/Organizador) y por al menos un participante que será el organizador. Un evento creado está relacionado con un deporte y pueden ser eliminador por su creador o por el administrador.
* **Deporte**: clase que almacena el tipo de deporte que practica una persona o el tipo de deporte que tiene asignado un evento.
* **Administrador**: clase que hereda de Usuario y permite eliminar todos los objetos de Persona y Evento.

## 3. Paquete Controlador

El controlador realiza una comunicación de datos con el modelo y manda una respuesta determinada a la vista por mediación de la “InterfazCtr”. Se compone de las siguientes clases:

* **CtrPersona**: recibe el evento de entrada del paquete vista cuando un usuario quiere iniciar sesión o crear un perfil.
* **CtrEvento**: recibe el evento de entrada del paquete vista cuando un usuario quiere realizar alguna operación sobre un evento.

## 4. Diagrama de clases:

![DiagramaCasosDeUso](https://github.com/JavierSantiburcio/Proyecto-Sporter/blob/master/Documentacion/Imagenes/Imagen_DiagramaClasesSporter.PNG)
