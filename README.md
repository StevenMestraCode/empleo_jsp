# Empleo_jsp - Aplicación Web con Servlets y JSP

## Descripción General

Este repositorio contiene el código fuente, configuración y documentación de la actividad académica **"Servlets/JSP: introducción a la segunda generación del desarrollo de aplicaciones web"** para la asignatura **Desarrollo Web - Unidad 1**.

La aplicación es un sistema de gestión de empleos que implementa una arquitectura **MVC (Modelo-Vista-Controlador)** utilizando **Java Servlets**, **JSP (JavaServer Pages)** y **JDBC** para la persistencia de datos en una base de datos **PostgreSQL** alojada en la nube (Render).

El sistema maneja **dos entidades principales**:
- **Usuario** (id, clave, nombre, correo, rol)
- **Empleo** (id, nombre, categoría, área de trabajo, empresa, nivel, sueldo, funciones, cargo del jefe)

---

## Propósito de la Actividad

Desarrollar y desplegar una aplicación web completa con Java Servlet/JSP que permita evidenciar:
- Manejo de peticiones HTTP.
- Vistas JSP dinámicas.
- Acceso a datos mediante JDBC.
- Separación de responsabilidades (patrón MVC).
- Operaciones CRUD completas.
- Autenticación de usuarios.
- Generación de consultas y reportes parametrizados.
- Recuperación de contraseña por correo electrónico.
- Despliegue en un servicio de Internet.

---

## Arquitectura y Patrones Utilizados

La aplicación sigue el patrón de diseño **MVC (Modelo - Vista - Controlador)**:

### Modelo (Model)
Ubicado en el paquete `modelo`. Contiene:
- **Clases de entidad**: `usuario.java`, `empleo.java` (representan los objetos del dominio).
- **Clases CRUD**: `CRUDusuario.java`, `CRUDempleo.java` (encapsulan las operaciones de acceso a datos).
- **Conexión a BD**: `ConexionBaseDatos.java` (gestiona la conexión JDBC).
- **Utilidades**: `EnviarCorreo.java` (envío de correos con JavaMail).

### Controlador (Controller)
Ubicado en el paquete `controladores`. Contiene:
- **`ServletUsuario.java`**: gestiona todas las peticiones relacionadas con usuarios.
- **`ServletEmpleo.java`**: gestiona todas las peticiones relacionadas con empleos.

Los servlets reciben las peticiones HTTP (GET/POST), procesan la acción solicitada mediante un parámetro `accion`, invocan los métodos del CRUD correspondiente y redirigen a la vista JSP adecuada.

### Vista (View)
Ubicada en la carpeta `web/`. Contiene:
- **JSP de usuario**: `login.jsp`, `agregar.jsp`, `buscar.jsp`, `modificar.jsp`, `eliminar.jsp`, `listar.jsp`, `recuperacionClave.jsp`, `reporteUsuarioRol.jsp`, `reporteUsuariosNombre.jsp`.
- **JSP de empleo**: `agregar.jsp`, `buscar.jsp`, `modificar.jsp`, `eliminar.jsp`, `listar.jsp`, `reporteEmpleoEmpresa.jsp`, `reporteEmpleoAreaTrabajo.jsp`.
- **JSP generales**: `index.jsp`, `mensaje.jsp`.

---

## Estructura del Proyecto
empleo_jsp/
├── src/
│ ├── java/
│ │ ├── modelo/
│ │ │ ├── usuario.java
│ │ │ ├── empleo.java
│ │ │ ├── CRUDusuario.java
│ │ │ ├── CRUDempleo.java
│ │ │ ├── ConexionBaseDatos.java
│ │ │ └── EnviarCorreo.java
│ │ └── controladores/
│ │ ├── ServletUsuario.java
│ │ └── ServletEmpleo.java
│ └── ...
├── web/
│ ├── index.jsp
│ ├── mensaje.jsp
│ ├── usuario/
│ │ ├── login.jsp
│ │ ├── agregar.jsp
│ │ ├── buscar.jsp
│ │ ├── modificar.jsp
│ │ ├── eliminar.jsp
│ │ ├── listar.jsp
│ │ ├── recuperacionClave.jsp
│ │ ├── reporteUsuarioRol.jsp
│ │ └── reporteUsuariosNombre.jsp
│ ├── empleo/
│ │ ├── agregar.jsp
│ │ ├── buscar.jsp
│ │ ├── modificar.jsp
│ │ ├── eliminar.jsp
│ │ ├── listar.jsp
│ │ ├── reporteEmpleoEmpresa.jsp
│ │ └── reporteEmpleoAreaTrabajo.jsp
│ └── WEB-INF/
│ ├── web.xml
│ └── lib/
│ └── jbcrypt-0.4.jar
├── Dockerfile
├── dist/
│ └── empleo_jsp.war
└── README.md

---

## Base de Datos

El proyecto utiliza **PostgreSQL** (alojado en Render). Se incluyen en el repositorio los scripts SQL para creación de tablas y carga de datos iniciales.

### Script de creación de tablas

```sql
-- Tabla usuarios
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    clave VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    rol VARCHAR(50) NOT NULL
);

-- Tabla empleo
CREATE TABLE empleo (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    areaTrabajo VARCHAR(100),
    empresa VARCHAR(100),
    nivel VARCHAR(50),
    sueldo VARCHAR(50),
    funciones TEXT,
    cargoJefe VARCHAR(100)
);
```

## Funciones:

Inicio de Sesión: Se guarda el objeto usuario_login en la sesión HTTP.

Control de acceso: Todos los JSP verifican que exista usuario_login en sesión; si no, redirigen al login.

Cierre de sesión: Acción cerrarSesion que invalida la sesión.

Encriptación de contraseñas
Se utiliza BCrypt (librería jBCrypt) para encriptar las contraseñas de los usuarios antes de almacenarlas en la base de datos.

Implementado en ServletUsuario (acción recuperarClave):

El usuario ingresa su correo en recuperacionClave.jsp.
El sistema genera una clave temporal aleatoria.
La encripta con BCrypt y la actualiza en la BD.
Envía la clave temporal al correo usando JavaMail (EnviarCorreo.java).
El usuario inicia sesión con esa clave temporal.

 Reportes Parametrizados
Se implementaron 2 reportes por cada entidad:

Usuario
Reporte por nombre: Busca usuarios por coincidencia en el nombre.
Reporte por rol: Filtra usuarios según su rol (ADMIN, Cliente, etc.).

Empleo
Reporte por empresa: Filtra empleos según la empresa.
Reporte por área de trabajo: Filtra empleos según el área (Desarrollo, Ventas, etc.).

## Tecnologías Utilizadas
Tecnología	Versión	Uso
Java	17	Lenguaje principal
Servlet API	4.0	Controladores
JSP	2.3	Vistas
JDBC	4.2	Acceso a datos
PostgreSQL	14+	Base de datos
BCrypt (jBCrypt)	0.4	Encriptación de contraseñas
JavaMail	1.6	Envío de correos
Docker	-	Contenedor
Tomcat	9	Servidor de aplicaciones
Render	-	Hosting en la nube

## Autor
Steven David Mestra

Correo institucional: smestram@unicartagena.edu.co

GitHub: @StevenMestraCode