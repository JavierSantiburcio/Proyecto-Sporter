# Especificación de requisitos 
## Del proyecto <project name>

Versión 0.3  
Generada por el equipo Sporter  
08/05/2020  

Índice
=================
* [Versiones](#versiones)
* 1 [Introducción](#1-introducción)
  * 1.1 [Objetivo del documento](#11-objetivo-del-documento)
  * 1.2 [Ámbito del proyecto](#12-ámbito-del-proyecto)
  * 1.5 [Resumen del documento](#15-resumen-del-documento)
* 2 [Vista general del producto](#2-vista-general-del-producto)
  * 2.1 [Perspectiva del producto](#21-perspectiva-del-producto)
  * 2.2 [Funciones del producto](#22-funciones-del-producto)
  * 2.3 [Restricciones del producto](#23-restricciones-del-producto)
  * 2.4 [Perfiles de usuario](#24-perfiles-de-usuario)
  * 2.5 [Suposiciones y dependencias](#25-suposiciones-y-dependencias)
* 3 [Interfaces externas](#3-interfaces-externas)
    * 3.1 [Interfaces con el Usuario](#31-interfaces-con-el-usuario)
    * 3.2 [Interfaces con el Hardware](#32-interfaces-con-el-hardware)
    * 3.3 [Interfaces con el Software](#33-interfaces-con-el-software)
* 4 [Requisitos](#4-requisitos)
  * 4.1 [Precedencia y prioridad](#41-precedencia-y-prioridad) 
  * 4.2 [Funcionales](#42-funcionales)
  * 4.3 [Calidad de Servicio](#43-calidad-de-servicio)
    * 4.3.1 [Rendimiento](#431-rendimiento)
    * 4.3.2 [Seguridad](#432-seguridad)
  * 4.4 [Normativas aplicables](#44-normativas-aplicables)
  * 4.5 [Diseño e implementación](#45-diseño-e-implementación)
    * 4.5.1 [Instalación](#451-instalación)
* 5 [Verificación](#5-verificación)
* 6 [Apéndices](#6-apéndices) 
  
## Versiones
| Name               | Date       | Reason For Changes   | Version |
|--------------------|------------|----------------------|---------|
| Javier Santiburcio | 14/04/2020 | Primera idea         | 0.1     |
| Sporter            | 05/05/2020 | Actualización grupal | 0.2     |
| Sporter            | 08/05/2020 | Actualización final  | 0.3     |

## 1. Introducción

En este documento se describirán todas los requisitos que cumplirá la aplicación 'Sporter'.

### 1.1 Objetivo del documento

El documento tiene como objeto especificar al cliente las funcionalidades que se implementarán para dar el proyecto como finalizado. Sirva este documento como contrato en el que el equipo de 'Sporter' se compromete con el propio cliente a implementar los requisitos aquí mencionados. 

### 1.2 Ámbito del proyecto

El producto que será descrito posteriormente a lo largo de este manuscrito, será la aplicación 'Sporter' al completo. El número de versión actual es la 1.0, aún sin implementar. Dicha aplicación se trata de un programa de escritorio en el que un usuario se registra introduciendo sus deportes en equipo favoritos y su ubicación. El usuario visualizará en el tablón las publicaciones de eventos que pongan otros usuarios que coincidan con su ubicación y gustos. Desde 'Sporter' pensamos en lo beneficiosa que sería esta aplicación para deportistas puesto que a veces es complicado encontrar gente con la que practicar deporte en grupo y por tanto vemos un nicho de mercado al que podemos acceder puesto que las aplicaciones de la competencia no tienen mucho éxito.

### 1.5 Resumen del documento

Este documento describe los requisitos que deberá cumplir nuestra aplicación para calificarla como finalizada.

## 2. Vista general del producto

### 2.1 Perspectiva del producto

El objetivo de nuestra aplicación es emparejar a personas con eventos deportivos. Por lo que es muy importante el correcto emparejamiento entre usuarios, la creación de un perfil, la publicación de eventos y una interfaz intuitiva.

Este es un producto nuevo que no pertenece a ningún sistema mayor.

### 2.2 Funciones del producto

Las funciones principales que 'Sporter' llevará a cabo serán:

* La creación y edición de un perfil: El usuario podrá crear un perfil que contendrá sus datos, deportes favoritos y ubicación así como podrá editarlo en un futuro
* La publicación de eventos: El usuario podrá publicar un evento indicando un deporte, ubicación y fecha/hora
* El emparejamiento entre usuario-evento: El sistema permite al usuario ver en el tablón de publicaciones los eventos más adecuados en base a sus datos
* Filtro de búsqueda de eventos: El sistema permite al usuario ver en el tablón de publicaciones los eventos más adecuados en base a los filtros seleccionados
* El administrador puede eliminar eventos y perfiles: El sistema permite al administrador acceder a la lista de todos los perfiles,eventos para poder eliminarlos si fuese necesario.

Diagrama de casos de uso:

![DiagramaCasosDeUso](https://github.com/JavierSantiburcio/Proyecto-Sporter/blob/master/Documentacion/Imagenes/ImagenDiagramaCasosDeUso.png)

### 2.3 Restricciones del producto

Será compatible con los siguientes sistemas operativos:
- Arquitectura del sistema 32/64 bit.
- Windows 10 (8u51 y superiores).
- Windows 8.x (escritorio).
- Windows 7 SP1.
- Windows Vista SP2.
- Windows Server 2008 R2 SP1 (64 bits).
- Windows Server 2012 y 2012 R2 (64 bits).
- Mac OS Catalina Versión 10.15.4.

Requisitos mínimos del PC:
- 512 Mb de memoria Ram.
- 1024 Mb máquina virtual.
- 1 GB de espacio de disco duro.

### 2.4 Perfiles de usuario

Los distintos perfiles de usuarios que se pueden dar son:
- Usuario administrador: usuario con privilegios en la aplicación que administra eventos y perfiles de usuarios.
- Usuario principiante: usuario que no ha utilizado anteriormente la aplicación y está descubriendo las principales funciones.
- Usuario casual: usuario que no usa la aplicación de forma frecuente pero conoce las principales funciones. 
- Usuario experto: usuario que utiliza la aplicación asiduamente y conoce todas las funciones.

### 3 Interfaces externas

#### 3.1 Interfaces con el usuario

Nuestra aplicación necesitará una interfaz de usuario. Esta interfaz contará con 3 pantallas diferentes:
* La ventana de Login consistirá en una pantalla de inicio de la aplicación que contará con dos botones: uno para iniciar sesión y otro para registrarse, así como el logo de 'Sporter' en grande, un campo para introducir el correo electrónico del usuario y otro para la contraseña del mismo. Cuando se pulse el botón "registrar", aparecerá un formulario para introducir los datos a rellenar. Si pulsamos el botón iniciar sesión accederá a la ventana principal de la aplicación (tablón de publicaciones) habiendo introducido previamente un correo y contraseña correcta.
* La ventana tablón de publicaciones estará compuesta por una parte central donde se muestran los eventos. Tal como aparece en la imagen. A la izquierda aparecerá un botón para crear eventos, el cual al pulsarlo muestra el formulario de  crear evento. A la derecha se ve un icono que identifica el perfil del usuario y su nombre. Al pulsarlo te muestra la pantalla de perfil.
* La ventana perfil de usuario mostrará el perfil con la foto(icono), nombre y deportes favoritos y debajo todos los eventos en los que participa o ha creado el mismo.
* Si el usuario es el administrador, la pantalla principal constará de dos tablas que contendrán todos los eventos y perfiles dados de alta en la aplicación que le permitirá administrar la eliminación de eventos y/o perfiles de usuarios.

* Borrador de las interfaces de usuario:
![Borrardor](https://github.com/JavierSantiburcio/Proyecto-Sporter/blob/master/Documentacion/Imagenes/Borrador_Sporter.png)


#### 3.3 Interfaces con el Software

Las conexiones software que habrá que implementar serán la base de datos, desarrollada en MySQL (versión 8) y el paquete JDK 8 de Java.

## 4. Requisitos

### 4.1 Precedencia y prioridad

| Id  | Nombre                              | Descripción                                                                                                                                                                                                                                                 |  Prioridad  |   Precedencia   |     Tipo     |
|-----|-------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------:|:---------------:|:------------:|
| R1  | Base de datos                       | La base de datos será MySql.                                                                                                                                                                                                                                | Fundamental |                 | No Funcional |
| R2  | Creación de perfil                  | El usuario podrá crear un perfil con un email, nombre, foto de perfil, deportes favoritos y una ubicación.                                                                                                                                                  | Fundamental |        R1       |   Funcional  |
| R3  | Perfil de usuario                   | El perfil de usuario contará con una foto de perfil, un nombre, un email asociado, una serie de deportes favoritos y una ubicación.                                                                                                                         | Fundamental |        R2       |   Funcional  |
| R4  | Iniciar sesión                      | El usuario podrá iniciar sesión en la aplicación usando el email asociado a su perfil. Cuando haya iniciado sesión se pasará a la pantalla del tablón de publicaciones.                                                                                     | Fundamental |        R3       |   Funcional  |
| R5  | Publicar un evento                  | El usuario podrá publicar un evento que tendrá un nombre, un creador (el propio usuario), una ubicación, un deporte, un número de participantes.                                                                                                            | Fundamental |        R4       |   Funcional  |
| R6  | Eliminar un evento                  | El usuario podrá eliminar un evento desde su perfil.                                                                                                                                                                                                        | Fundamental |      R4,R5      |   Funcional  |
| R7  | Unirse a un evento                  | El usuario podrá unirse a un evento desde el tablón de publicaciones. El evento aumentará en una unidad el número de participantes.                                                                                                                         | Fundamental |        R4       |   Funcional  |
| R8  | Dejar un evento                     | El usuario podrá dejar un evento del que sea participante. El número de participantes disminuirá en una unidad.                                                                                                                                             | Fundamental |      R4,R7      |   Funcional  |
| R9  | Emparejamiento                      | El sistema ha de emparejar los eventos más adecuados con un usuario y mostrarlos en su tablón de publicaciones. El emparejamiento se basará en la cercanía del usuario con la ubicación del evento, así como la coincidencia con el deporte favorito.       | Fundamental |     R3,R4,R5    |   Funcional  |
| R10 | Filtro de búsqueda                  | El usuario podrá realizar un filtro de búsqueda sobre los eventos que quiere visualizar.                                                                                                                                                                    | Fundamental |        R1       |   Funcional  |
| R11 | Tablón de publicaciones             | El sistema mostrará una ventana con los eventos a los que se puede unir el usuario.<br>El usuario podrá navegar entre los eventos o unirse a ellos. Podrá publicar eventos, ir a su perfil o realizar filtrado de búsquedas de los eventos que se muestran. | Fundamental | R4,R5,R7,R9,R10 |   Funcional  |
| R12 | Visualizar perfil                   | El sistema muestra al usuario la ventana donde podrá visualizar su propio perfil, aparecerán sus datos y eventos.                                                                                                                                           | Fundamental |   R3,R4,R5,R7   |   Funcional  |
| R13 | Rendimiento                         | El sistema tendrá un tiempo de respuesta medio máximo de 3s ante las acciones del usuario.                                                                                                                                                                  | Fundamental |    R1,R11,R12   | No Funcional |
| R14 | Perfiles duplicados                 | El sistema comprobará que no se pueda crear un nuevo perfil de usuario con email ya existente en la base de datos.                                                                                                                                          | Fundamental |      R1,R3      | No Funcional |
| R15 | Perfil administrador                | El sistema mostrará al usuario administrador una ventana con dos columnas "eventos, usuarios" dados de alta en la base de datos.<br>El administrador podrá usar el filtro de búsqueda para acotar los datos mostrados.                                      | Fundamental |      R1,R4      |   Funcional  |
| R16 | Eliminar perfil desde administrador | El administrador podrá eliminar un perfil de cualquier usuario.                                                                                                                                                                                             | Fundamental |      R3,R15     |   Funcional  |
| R17 | Eliminar evento desde administrador | El administrador podrá eliminar cualquier evento.                                                                                                                                                                                                           | Fundamental |      R5,R15     |   Funcional  |
| R18 | Actualización Perfil de usuario     | Los datos del perfil, a excepción del email y del nombre, podrán ser actualizados por el usuario.                                                                                                                                                           | Fundamental |        R2       | No Funcional |
| R19 | Restricción publicar un evento      | El sistema no permitirá crear un evento si el usuario participa o ha creado uno en esa fecha/hora.                                                                                                                                                          | Fundamental |        R4       | No Funcional |
| R20 | Restricción unirse a un evento      | El sistema no permitirá al usuario unirse a un evento si participa o tiene creado otro que se realiza en la misma fecha/hora.                                                                                                                               | Fundamental |        R4       | No Funcional |
| R21 | Restricción eliminar evento         | El usuario tiene que ser propietario/creador del evento para poder realizar su eliminación desde su panel de perfil.                                                                                                                                        | Fundamental |      R4,R5      | No Funcional |
| R22 | Restricción dejar evento            | El usuario solo podrá abandonar un evento del que es participante y desde su panel de perfil.                                                                                                                                                               | Fundamental |      R4,R7      | No Funcional |
| R23 | Restricción de creación de perfil   | No se podrá crear un perfil con un email que ya esté asociado a otro perfil.                                                                                                                                                                                | Fundamental |        R1       | No Funcional |

### 4.2 Funcionales

### R2 - Creación de perfil
El usuario podrá crear un perfil con email, nombre, foto de perfil, deportes favoritos y ubicación.
#### Dependerá del R1.
#### Prioridad alta.
#### Es importante que se pueda crear un perfil para el correcto funcionamiento de la aplicación.

### R3 - Perfil de usuario
El perfil de usuario contará con una foto de perfil, un nombre, un email asociado, una serie de deportes favoritos y una ubicación.
#### Dependerá de R2.
#### Prioridad alta.
#### Es una de las bases de nuestra aplicación para poder encontrar eventos, tendremos que tener una serie de deportes favoritos y una ubicación concreta. El nombre y email no podrán ser cambiados para evitar inconsistencia en la base de datos y en los datos mostrados a otros usuarios que se relacionan con él.

### R4 - Iniciar sesión 
El usuario podrá iniciar sesión en la aplicación usando el email asociado a su perfil. Cuando haya iniciado sesión se pasará a la pantalla del tablón de publicaciones.
#### Dependerá de R3.
#### Prioridad alta.
#### El usuario ha de poder iniciar sesión para acceder al tablón de publicaciones.

### R5 - Publicar un evento 
El usuario podrá publicar un evento que tendrá un nombre, un creador (el propio usuario), una ubicación, un deporte, un número de participantes. 
#### Dependerá de R4.
#### Prioridad alta
#### Debe de haber eventos a los que asociar a perfiles

### R6 - Eliminar un evento 
El usuario podrá eliminar un evento desde su perfil.
#### Dependerá de R4 y R5.
#### Prioridad media
#### Se deberá poder eliminar un evento que haya sido publicado 

### R7 - Unirse a un evento
El usuario podrá unirse a un evento desde el tablón de publicaciones. El evento aumentará en una unidad el número de participantes.
#### Dependerá de R4.
#### Prioridad alta.
#### Unirse a un evento será fundamental para correcto funcionamiento de la aplicación.

### R8 - Dejar un evento
El usuario podrá dejar un evento del que sea participante. El número de participantes disminuirá en una unidad. 
#### Dependerá de R4 y R7.
#### Prioridad media.
#### El usuario deberá poder dejar un evento en el que ya no esté interesado en participar. 

### R9 - Emparejamiento
El sistema ha de emparejar los eventos más adecuados con un usuario y mostrarlos en su tablón de publicaciones. El emparejamiento se basará en la cercanía del usuario con la ubicación del evento, así como la coincidencia con el deporte favorito.
#### Dependerá de R3, R4 y R5.
#### Prioridad alta.
#### Para que la aplicación funcione correctamente será importante que el emparejamiento esté bien realizado. 

### R10 - Filtro de búsqueda 
El usuario podrá realizar un filtro de búsqueda sobre los eventos que quiere visualizar.
#### Dependerá de R1.
#### Prioridad baja.
#### La aplicación deberá contar con un filtro de búsqueda para facilitar a los usuarios encontrar eventos concretos.

### R11 - Tablón de publicaciones
El sistema mostrará una ventana con los eventos a los que se puede unir el usuario. El usuario podrá navegar entre los eventos o unirse a ellos. Podrá publicar eventos, ir a su perfil o realizar filtrado de búsquedas de los eventos que se muestran.
#### Prioridad alta.
#### Dependerá de R4, R5, R7, R9 y R10.
#### Es fundamental el correcto funcionamiento del tablón pues es la parte principal de la aplicación.

### R12 - Visualizar perfil
El sistema muestra al usuario la ventana donde podrá visualizar su propio perfil, aparecerán sus datos y eventos.
#### Prioridad alta.
#### Dependerá de R3, R4, R5 y R7.
#### Es importante que la aplicación tenga un apartado donde ver el propio perfil con los eventos a los que acudirá el usuario.

### R15 - Perfil administrador
El sistema mostrará al usuario administrador una ventana con dos columnas "eventos, usuarios" dados de alta en la base de datos.
El administrador podrá usar el filtro de búsqueda para acotar los datos mostrados. 
#### Prioridad alta.
#### Dependerá de R1, R4.
#### Necesario que exista un administrador para mantener la consistencia de la Base de datos y eliminar perfiles y eventos falsos. 

### R16 - Eliminar Perfil desde administrador
El administrador podrá eliminar un perfil de cualquier usuario. 
#### Dependerá de R3 y R15.
#### Prioridad media
#### El administrador deberá poder eliminar un perfil desde la ventana principal de su perfil.

### R17 - Eliminar evento desde administrador
El administrador podrá eliminar cualquier evento.
#### Dependerá de R5 y R15.
#### Prioridad media
#### El administrador deberá poder eliminar un evento que haya sido publicado desde la ventana principal de su perfil

### 4.3 Calidad de Servicio

### R1 - Base de datos
La base de datos será MySql.
#### Carece de dependencias.
#### Prioridad alta.
#### Elección de entorno donde almacenar los datos por su facilidad de programación, acceso y documentación de soporte. 

### R13- Rendimiento
El sistema tendrá un tiempo de respuesta medio máximo de 3s ante las acciones del usuario.
#### Prioridad baja.
#### Dependerá de R1, R11, R12.
#### La aplicación deberá de tener un tiempo de respuesta.

### R14 - Perfiles duplicados
El sistema comprobará que no se pueda crear un nuevo perfil de usuario con email ya existente en la base de datos.
#### Prioridad alta.
#### Dependerá de R1, R3.
#### Evitar datos redundantes en la base de datos y que otro usuario pueda sobreescribir un perfil existente.

### R18 - Actualización Perfil de usuario
Los datos del perfil, a excepción del email y del nombre, podrán ser actualizados por el usuario. 
#### Dependerá de R2.
#### Prioridad alta.
#### Permite al usuario modificar sus datos de perfil.

### R19 - Restricción publicar un evento 
El sistema no permitirá crear un evento si el usuario participa o ha creado uno en esa fecha/hora.
#### Dependerá de R4.
#### Prioridad alta
#### En la base de datos un usuario no puede tener eventos con la misma fecha/hora.

### R20 - Restricción unirse a un evento
El sistema no permitirá al usuario unirse a un evento si participa o tiene creado otro que se realiza en la misma fecha/hora.
#### Dependerá de R4.
#### Prioridad alta.
#### En la base de datos un usuario no puede tener eventos con la misma fecha/hora.

### R21 - Restricción eliminar evento 
El usuario tiene que ser propietario/creador del evento para poder realizar su eliminación desde su panel de perfil.
#### Dependerá de R4 y R5.
#### Prioridad media
#### Para que un usuario elimine un evento tiene que ser el propietario/creador del evento.

### R22 - Restricción dejar evento
El usuario solo podrá abandonar un evento del que es participante y desde su panel de perfil.
#### Dependerá de R4 y R7.
#### Prioridad media.
### Solo se permite abandonar un evento si es participante y no es el propietario.

### R23 - Restricción de creación de perfil
No se podrá crear un perfil con un email que ya esté asociado a otro perfil.
#### Dependerá del R1.
#### Prioridad alta.
#### La base de datos no puede tener datos de usuarios duplicados.
 
#### 4.3.1 Rendimiento

La aplicación deberá de tener una respuesta rápida ante cualquier acción realizada por el usuario. Por respuesta rápida entenderemos un tiempo medio máximo de 3 segundos desde que el usuario realiza una acción y el sistema le da una respuesta a esta.

#### 4.5.1 Instalación

El usuario deberá de tener los siguientes requisitos de instalación:;
- Paquete Java 8.
- MySql 8.
- Windows 10 o MacOS Catalina.

## 5. Verificación

* Registrarse: Se crearán varios perfiles, cada uno por orden tendrán una cosa mal y el resto bien: correo en base de datos, nombre en base de dato. Comprobaremos que den las excepciones que deben saltar y por último crearemos un perfil con todo correcto y se comprobará que se haya introducido en la base de datos.

* Iniciar sesión: Comprobaremos si al introducir un correo o una contraseña mal, o no se rellena alguno o ambos campos, salen las excepciones especificadas para cada caso. Por último, en caso de que se acceda con un correo que existe en la base de datos y la contraseña asociada a ese perfil, se comprobará que se accede a la aplicación.

* Publicar evento: Un usuario publicara un evento. Se comprobará que se ha añadido ese evento a la base de datos. También se intentará publicar un evento a la misma fecha/hora del evento anterior, así como intentar publicar un evento a la misma fecha/hora que otro evento en el que se participa, ambas pruebas deberán lanza una excepción.

* Eliminar evento: Un usuario intentará eliminar un evento no propio, daremos por válido si salta la excepción correspondiente. Luego borraremos un evento propio y comprobaremos si ese evento sigue existiendo en la base de datos, si no está se dará por válido.

* Unirse a un evento: Un usuario intentará unirse a un evento. Primero el usuario intentará unirse a un evento ajeno en el que no está registrado y vacío, comprobaremos que el número de participantes en el evento a aumentado en 1 y que tu nombre se encuentra en el evento. Luego nos intentaremos unir a un evento lleno que nos debe de dar la excepción correspondiente. También se unirá a un evento y a posteriori intentará unirse en otro evento a la misma fecha/hora que el evento anterior, el sistema deberá mostrar la excepción correspondiente. Por último, debemos intentar unirnos a un evento propio que debe dar la excepción correspondiente. 

* Dejar un evento: Un usuario intentará irse de un evento, si el nombre de el usuario se ha desvinculado del evento y el número de participantes del mismo ha disminuido en 1 se dará por válido.

* Eliminar perfil desde administrador: Un usuario administrador, intentará borrar un perfil, si el número de usuarios ha disminuido en 1 en la base de datos y el nombre de ese usuario no se encuentra en esta, se dará por válido.

* Eliminar evento desde administrador: Un usuario administrador, intentará borrar un evento, si el número de eventos ha disminuido en 1 en la base de datos y el evento no se encuentra en esta, se dará por válido.

* Filtrar búsqueda: Un usuario introducirá los datos para filtrar. Se comprueba que los resultados coinciden con los filtros y si todos los resultados coinciden con el filtro, se dará por válido.

* Actualización perfil de usuario: Un usuario intentará modificar en su perfil de usuario su imagen de perfil, ubicación y deportes favoritos.
Si los datos son incorrectos se lanzará una excepción. Si los datos de usuario se han cambiado adecuadamente se dará por válido.
