-- INTRODUCIR DATOS EN CINES
INSERT INTO CINES (id_cine, name, address) VALUES (1, 'Cine Plaza', 'Calle Mayor 123');
INSERT INTO CINES (id_cine, name, address) VALUES (2, 'Cine Sol', 'Av. del Cine 45');

-- INTRODUCIR DATOS EN USERS
INSERT INTO USERS (name, email, password, address, phone) VALUES ('Laura Pérez', 'laura@cineplaza.com', '1234', 'Calle Luna 12', '600123456');
INSERT INTO USERS (name, email, password, address, phone) VALUES ('Carlos Ruiz', 'carlos@example.com', 'abcd1234', 'Av. Estrella 45', '611234567');
INSERT INTO USERS (name, email, password, address, phone) VALUES ('Ana Torres', 'ana@example.com', 'pass4567', 'Calle Sol 89', '622345678');
INSERT INTO USERS (name, email, password, address, phone) VALUES ('Miguel Gómez', 'miguel@cinesol.com', 'qwerty', 'Calle Cine 33', '633987654');
INSERT INTO USERS (name, email, password, address, phone) VALUES ('Elena Martínez', 'elena.martinez@example.com', 'elena2024', 'Calle Río 7', '644556677');
INSERT INTO USERS (name, email, password, address, phone) VALUES ('Javier López', 'javi.lopez@example.com', 'javilopez99', 'Av. Mar 10', '655667788');
INSERT INTO USERS (name, email, password, address, phone) VALUES ('Lucía Sánchez', 'lucia.sanchez@example.com', 'lucia123', 'Plaza del Sol 21', '666778899');
INSERT INTO USERS (name, email, password, address, phone) VALUES ('David Fernández', 'david.fernandez@example.com', 'davidpass', 'Camino Verde 14', '677889900');

-- INTRODUCIR DATOS EN EMPLOYEES (uno por uno)
INSERT INTO EMPLOYEES (id_user, position, id_cine) VALUES (1, 'Gerente', 1);
INSERT INTO EMPLOYEES (id_user, position, id_cine) VALUES (3, 'Taquillero', 1);
INSERT INTO EMPLOYEES (id_user, position, id_cine) VALUES (5, 'Encargada de Sala', 2);
INSERT INTO EMPLOYEES (id_user, position, id_cine) VALUES (4, 'Técnico de Sonido', 2);
INSERT INTO EMPLOYEES (id_user, position, id_cine) VALUES (7, 'Taquillera', 1);

-- INTRODUCIR DATOS EN PURCHASES
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-01', 15.99, 1);
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-02', 22.50, 2);
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-03', 30.00, 3);
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-04', 18.75, 4);
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-05', 45.20, 5);
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-06', 27.90, 6);
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-07', 33.40, 7);
INSERT INTO PURCHASES (date, total_price, id_user) VALUES ('2025-04-08', 12.99, 8);

-- INTRODUCIR DATOS EN MOVIES
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('Inception', 148, 'https://example.com/inception.jpg', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.');
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('The Matrix', 136, 'https://example.com/matrix.jpg', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.');
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('The Godfather', 175, 'https://example.com/godfather.jpg', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.');
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('Pulp Fiction', 154, 'https://example.com/pulpfiction.jpg', 'The lives of two mob hitmen, a boxer, a gangsters wife, and a pair of diner bandits intertwine in four tales of violence and redemption.');
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('Interstellar', 169, 'https://example.com/interstellar.jpg', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanitys survival.');
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('Fight Club', 139, 'https://example.com/fightclub.jpg', 'An insomniac office worker and a devil-may-care soap maker form an underground fight club.');
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('The Shawshank Redemption', 142, 'https://example.com/shawshank.jpg', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.');
INSERT INTO MOVIES (name, duration, poster_url, synopsis) VALUES ('The Dark Knight', 152, 'https://example.com/darkknight.jpg', 'When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.');

-- INTRODUCIR DATOS EN ROOMS
INSERT INTO ROOMS (capacity, id_cine) VALUES (100, 1);
INSERT INTO ROOMS (capacity, id_cine) VALUES (150, 1);
INSERT INTO ROOMS (capacity, id_cine) VALUES (80, 2);
INSERT INTO ROOMS (capacity, id_cine) VALUES (120, 2);
INSERT INTO ROOMS (capacity, id_cine) VALUES (200, 1);

-- INSERTAR DATOS EN SEATS
-- Sala 1 (id_room = 1) - Cine 1
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (1, 1);

-- Sala 2 (id_room = 2) - Cine 1
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (2, 1);

-- Sala 3 (id_room = 3) - Cine 2
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (3, 2);

-- Sala 4 (id_room = 4) - Cine 2
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);
INSERT INTO SEATS (id_room, id_cine) VALUES (4, 2);

-- Sala 5 (id_room = 5) - Cine 1
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);
INSERT INTO SEATS (id_room, id_cine) VALUES (5, 1);

-- INSERTAR DATOS EN SESSIONS
INSERT INTO SESIONS (id_room, id_movie, start_time, end_time) VALUES (1, 1, '18:00:00', '20:28:00');
INSERT INTO SESIONS (id_room, id_movie, start_time, end_time) VALUES (2, 2, '19:00:00', '21:16:00');
INSERT INTO SESIONS (id_room, id_movie, start_time, end_time) VALUES (3, 3, '17:30:00', '20:25:00');
INSERT INTO SESIONS (id_room, id_movie, start_time, end_time) VALUES (4, 4, '20:00:00', '22:34:00');
INSERT INTO SESIONS (id_room, id_movie, start_time, end_time) VALUES (5, 5, '18:00:00', '20:49:00');

-- INSERTAR DATOS EN TICKETS
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (1, 7.99, 1, 1);
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (2, 8.00, 1, 1);
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (3, 10.00, 2, 2);
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (4, 7.50, 3, 3);
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (5, 9.00, 4, 4);
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (6, 11.00, 5, 5);
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (7, 8.50, 1, 6);
INSERT INTO TICKETS (id_seat, price, id_session, id_buy) VALUES (8, 7.99, 2, 7);