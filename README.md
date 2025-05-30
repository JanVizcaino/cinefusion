👥 Integrantes
-Jan Vizcaíno
-Alain
-Germán
-Pablo

📝 Descripción
-Este proyecto consiste en el desarrollo de un sistema backend para la gestión completa de un cine. Incluye funcionalidades para administrar usuarios, películas, salas, sesiones, asientos, empleados, compras y más. 
Cada entidad cuenta con su correspondiente controlador RESTful, siguiendo buenas prácticas de diseño de API.

🔗 Endpoints: A continuación se listan los controladores y los endpoints disponibles:

📁 user-controller
-GET /api/users/{id}
-PUT /api/users/{id}
-DELETE /api/users/{id}
-GET /api/users
-POST /api/users
-GET /api/users/name/{name}

🎟️ ticket-controller
-GET /api/ticket/{id}
-PUT /api/ticket/{id}
-DELETE /api/ticket/{id}
-GET /api/ticket
-POST /api/ticket

🕒 sesion-controller
-GET /api/sessions/{id}
-PUT /api/sessions/{id}
-DELETE /api/sessions/{id}
-GET /api/sessions
-POST /api/sessions

💺 seat-controller
-GET /api/seats/{id}
-PUT /api/seats/{id}
-DELETE /api/seats/{id}
-GET /api/seats
-POST /api/seats

🏟️ room-controller
-GET /api/rooms/{id}
-PUT /api/rooms/{id}
-DELETE /api/rooms/{id}
-GET /api/rooms
-POST /api/rooms

🛒 purchase-controller
-GET /api/purchases/{id}
-PUT /api/purchases/{id}
-DELETE /api/purchases/{id}
-GET /api/purchases
-POST /api/purchases

🎬 movie-controller
-GET /api/movies/{id}
-PUT /api/movies/{id}
-DELETE /api/movies/{id}
-GET /api/movies
-POST /api/movies

👨‍💼 employee-controller
-GET /api/employees/{id}
-PUT /api/employees/{id}
-DELETE /api/employees/{id}
-GET /api/employees
-POST /api/employees

🏢 cine-controller
-GET /api/cines/{id}
-PUT /api/cines/{id}
-DELETE /api/cines/{id}
-GET /api/cines
-POST /api/cines
