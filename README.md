# 🎬 Sistema de Gestión de Cine

## 👥 Integrantes
- Jan Vizcaíno
- Alain
- Germán
- Pablo

## 📝 Descripción
Este proyecto consiste en el desarrollo de un sistema backend para la gestión completa de un cine. Incluye funcionalidades para administrar usuarios, películas, salas, sesiones, asientos, empleados, compras y más. Cada entidad cuenta con su correspondiente controlador RESTful, siguiendo buenas prácticas de diseño de API.

## 🚀 Setup e Instalación

### 📋 Requisitos previos
- IntelliJ IDEA o IDE similar
- XAMPP o servidor web local
- Sistema de gestión de base de datos (MySQL/PostgreSQL)

### 🗄️ Configuración de la Base de Datos
1. Navega al directorio `Informes` del proyecto
2. Busca el documento llamado `BBDD`
3. Sigue las instrucciones contenidas en dicho documento para crear y configurar la base de datos

### ⚙️ Configuración de la API
1. Descarga el proyecto de la API
2. Abre el proyecto con IntelliJ IDEA o tu IDE preferido
3. Localiza los archivos de configuración de la base de datos
4. Modifica los campos de IP y credenciales para conectar con tu base de datos local
5. Ejecuta el proyecto desde tu IDE

### 🌐 Configuración del Frontend
1. Descarga el proyecto web
2. Hostea el proyecto usando una de estas opciones:
   - **XAMPP**: Coloca los archivos en la carpeta `htdocs` y accede mediante `localhost`
   - **Live Server**: Usa la extensión Live Server de VS Code o similar
3. Asegúrate de que la URL de la API en el frontend coincida con la dirección donde está ejecutándose tu backend

## 🔗 Endpoints
A continuación se listan los controladores y los endpoints disponibles:

### 📁 user-controller
- GET /api/users/{id}
- PUT /api/users/{id}
- DELETE /api/users/{id}
- GET /api/users
- POST /api/users
- GET /api/users/name/{name}

### 🎟️ ticket-controller
- GET /api/ticket/{id}
- PUT /api/ticket/{id}
- DELETE /api/ticket/{id}
- GET /api/ticket
- POST /api/ticket

### 🕒 sesion-controller
- GET /api/sessions/{id}
- PUT /api/sessions/{id}
- DELETE /api/sessions/{id}
- GET /api/sessions
- POST /api/sessions

### 💺 seat-controller
- GET /api/seats/{id}
- PUT /api/seats/{id}
- DELETE /api/seats/{id}
- GET /api/seats
- POST /api/seats

### 🏟️ room-controller
- GET /api/rooms/{id}
- PUT /api/rooms/{id}
- DELETE /api/rooms/{id}
- GET /api/rooms
- POST /api/rooms

### 🛒 purchase-controller
- GET /api/purchases/{id}
- PUT /api/purchases/{id}
- DELETE /api/purchases/{id}
- GET /api/purchases
- POST /api/purchases

### 🎬 movie-controller
- GET /api/movies/{id}
- PUT /api/movies/{id}
- DELETE /api/movies/{id}
- GET /api/movies
- POST /api/movies

### 👨‍💼 employee-controller
- GET /api/employees/{id}
- PUT /api/employees/{id}
- DELETE /api/employees/{id}
- GET /api/employees
- POST /api/employees

### 🏢 cine-controller
- GET /api/cines/{id}
- PUT /api/cines/{id}
- DELETE /api/cines/{id}
- GET /api/cines
- POST /api/cines