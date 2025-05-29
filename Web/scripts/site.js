let currentSession = null;
let selectedSeats = [];
let cartItems = [];

document.addEventListener("DOMContentLoaded", async () => {
    loadMovies();
});

function loadMovies() {
    fetch('http://172.17.40.12:8080/api/movies')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar el JSON');
            }
            return response.json();
        })
        .then(data => {
            const container = document.getElementById('grid-cartel');


            data.forEach(movie => {

                const movieCard = document.createElement('div');
                movieCard.className = `
                relative rounded-2xl overflow-hidden shadow-lg cursor-pointer transform transition duration-300 hover:scale-105 group
                w-full max-w-xs bg-gray-800
                `;
                movieCard.id = movie.id_movie;
                movieCard.setAttribute('x-on:click', "tab = 'tickets'");

                movieCard.innerHTML = `
                    <div class="w-full aspect-[2/3] relative">
                        <img src="${movie.poster_url}" onerror="this.src='./media/generic.png'" alt="${movie.title}"
                            class="w-full h-full object-cover object-center transition-opacity duration-300 group-hover:opacity-70" />
                        <div class="absolute inset-0 bg-gradient-to-t from-primary/80 to-transparent opacity-0 group-hover:opacity-100 transition duration-300 flex items-end p-4">
                        <h5 class="text-white text-4xl font-semibold">${movie.title}</h5>
                        </div>
                    </div>
                    `;

                container.appendChild(movieCard);

                movieCard.addEventListener('click', () => loadMovieInfo(movie.id_movie));

            });
        })
        .catch(error => {
            console.error('Error al procesar el JSON:', error);
        });
}

async function loadMovieInfo(id) {
    // CORRECCIÓN: Ocultar y limpiar contenedor de asientos
    const sitContainer = document.getElementById("sit-rows");
    sitContainer.style.display = "none";
    sitContainer.classList.add("hidden");

    const sitGrid = document.getElementById("sit-grid");
    sitGrid.innerHTML = "";

    // Limpiar selección anterior
    selectedSeats = [];
    currentSession = null;
    updateCartDisplay();

    const movieTitle = document.getElementById("movie-title");
    const movieImg = document.getElementById("movie-img");
    const movieDescription = document.getElementById("movie-description");
    const movieDirector = document.getElementById("movie-director");
    const movieDuration = document.getElementById("movie-duration");
    const movieCasting = document.getElementById("movie-casting");

    movieTitle.innerHTML = "Cargando...";
    movieImg.src = "./media/generic.png";
    movieDescription.innerHTML = "";
    movieDirector.innerHTML = "";
    movieDuration.innerHTML = "";
    movieCasting.innerHTML = "";

    const url = `http://172.17.40.12:8080/api/movies`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar el JSON');
            }
            return response.json();
        })
        .then(data => {
            const movie = data.find(m => m.id_movie === id);
            if (!movie) {
                throw new Error(`No se encontró la película con id ${id}`);
            }

            movieTitle.innerHTML = movie.title;
            movieImg.src = movie.poster_url;
            movieDescription.innerHTML = movie.synopsis;
            movieDirector.innerHTML = movie.director;
            movieDuration.innerHTML = movie.duration;
            movieCasting.innerHTML = movie.main_protagonist;

            loadSessions(id);

        })
        .catch(error => {
            console.error('Error al procesar el JSON:', error);
            movieTitle.innerHTML = "Error al cargar la película";
            movieImg.src = "./media/generic.png";
        });
}


function loadSessions(id) {
    const gridSessions = document.getElementById("grid-sessions");
    gridSessions.innerHTML = "";

    const url = `http://172.17.40.12:8080/api/sessions`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al procesar el JSON: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            const sitContainer = document.getElementById("sit-rows");
            const groupedSessions = {};

            data.forEach(session => {
                if (session.id_movie == id) {
                    if (!groupedSessions[session.date]) {
                        groupedSessions[session.date] = {};
                    }
                    if (!groupedSessions[session.date][session.num_room]) {
                        groupedSessions[session.date][session.num_room] = [];
                    }
                    groupedSessions[session.date][session.num_room].push(session);
                }
            });

            if (Object.keys(groupedSessions).length === 0) {
                const noSessionsMsg = document.createElement("p");
                noSessionsMsg.textContent = "Nada por aquí...";
                noSessionsMsg.classList.add("no-sessions-message");
                gridSessions.appendChild(noSessionsMsg);
                return;
            }

            for (const date in groupedSessions) {
                const sessionBlock = document.createElement("div");
                sessionBlock.classList.add("mb-4");

                const dateHeader = document.createElement("h2");
                dateHeader.classList.add("session-date");
                dateHeader.textContent = formatDate(date).trim();
                sessionBlock.appendChild(dateHeader);

                for (const room in groupedSessions[date]) {
                    const roomHeader = document.createElement("h4");
                    roomHeader.textContent = `Sala ${room}`;
                    roomHeader.classList.add('session-room');
                    sessionBlock.appendChild(roomHeader);

                    const timeGrid = document.createElement("div");
                    timeGrid.classList.add("session-time-grid");

                    groupedSessions[date][room].forEach(session => {
                        const sessionCard = document.createElement("div");
                        sessionCard.classList.add(
                            "grid-sessions-card",
                            "d-flex",
                            "justify-content-center",
                            "align-items-center",
                            "text-black"
                        );

                        sessionCard.innerHTML = `<p>${session.startTime} - ${session.endTime}</p>`;

                        sessionCard.addEventListener("click", () => {
                            sitContainer.style.display = "block";
                            sitContainer.classList.remove("hidden");
                            // CORRECCIÓN: Pasar el objeto session completo
                            loadSits(session.num_room, session);
                        });

                        timeGrid.appendChild(sessionCard);
                    });

                    sessionBlock.appendChild(timeGrid);
                }

                gridSessions.appendChild(sessionBlock);
            }
        })
        .catch(error => {
            console.error("Error al cargar sesiones:", error);
        });
}



async function loadSits(roomId, sessionData) {
    currentSession = sessionData;

    const sitGrid = document.getElementById("sit-grid");
    sitGrid.innerHTML = "";

    selectedSeats = [];
    updateCartDisplay();

    try {
        const roomsRes = await fetch("http://172.17.40.12:8080/api/rooms");
        if (!roomsRes.ok) throw new Error("Error al cargar salas");
        const rooms = await roomsRes.json();

        const roomNum = Number(roomId);
        const salaInfo = rooms.find(r => r.num_room === roomNum);
        if (!salaInfo) {
            sitGrid.innerHTML = "Sala no encontrada.";
            return;
        }

        sitGrid.className = 'grid gap-2';
        sitGrid.classList.add(
            `grid-cols-${salaInfo.max_cols}`,
            `grid-rows-${salaInfo.max_rows}`
        );

        const seatsRes = await fetch("http://172.17.40.12:8080/api/seats");
        if (!seatsRes.ok) throw new Error("Error al cargar asientos");
        const allSeats = await seatsRes.json();
        const roomSeats = allSeats.filter(seat => seat.num_room === roomNum);

        const ticketsRes = await fetch("http://172.17.40.12:8080/api/ticket");
        if (!ticketsRes.ok) throw new Error("Error al cargar tickets");
        const allTickets = await ticketsRes.json();

        roomSeats.forEach(seat => {
            const seatIcon = document.createElement("i");
            seatIcon.classList.add("fa-solid", "fa-couch", "text-2xl", "p-2", "rounded", "transition-colors");
            seatIcon.id = `seat-${seat.id_seat}`;

            const ocupado = allTickets.some(
                ticket => ticket.id_seat === seat.id_seat && ticket.id_session === sessionData.id_session
            );

            if (ocupado) {
                seatIcon.classList.add("text-red-500", "cursor-not-allowed");
                seatIcon.title = "Asiento ocupado";
            } else {
                seatIcon.classList.add("text-gray-400", "hover:text-primary", "cursor-pointer");
                seatIcon.title = "Clic para seleccionar";

                seatIcon.addEventListener("click", () => seleccionarAsiento(seat, sessionData));
            }

            sitGrid.appendChild(seatIcon);
        });

    } catch (error) {
        console.error("Error al cargar asientos:", error);
        sitGrid.innerHTML = "Error al cargar los datos.";
    }
}

function seleccionarAsiento(seat, session) {
    const seatIcon = document.getElementById(`seat-${seat.id_seat}`);

    const seatIndex = selectedSeats.findIndex(s => s.id_seat === seat.id_seat);

    if (seatIndex > -1) {
        // Deseleccionar asiento
        selectedSeats.splice(seatIndex, 1);
        seatIcon.classList.remove("text-primary", "bg-primary/20");
        seatIcon.classList.add("text-gray-400", "hover:text-primary");
    } else {
        // Seleccionar asiento
        selectedSeats.push({
            ...seat,
            session: session,
            price: 8.50
        });
        seatIcon.classList.remove("text-gray-400", "hover:text-primary");
        seatIcon.classList.add("text-primary", "bg-primary/20");
    }

    updateCartDisplay();
}

function updateCartDisplay() {
    const carritoContenido = document.getElementById('carrito-contenido');
    const carritoTotal = document.getElementById('carrito-total');

    if (selectedSeats.length === 0) {
        carritoContenido.innerHTML = '<p class="text-gray-400">No hay asientos seleccionados</p>';
        carritoTotal.textContent = '0.00€';
        return;
    }

    let html = '';
    let total = 0;

    selectedSeats.forEach(seat => {
        total += seat.price;
        html += `
            <div class="flex items-center justify-between border-b border-primary pb-2">
                <div>
                    <p class="font-semibold">${currentSession.movie_title || 'Película'}</p>
                    <p class="text-sm text-primary">Asiento: Fila ${Math.ceil(seat.id_seat / 10)} - ${seat.id_seat}</p>
                    <p class="text-xs text-gray-400">${currentSession.date} - ${currentSession.startTime}</p>
                </div>
                <div class="text-right">
                    <p class="font-semibold text-primary">${seat.price.toFixed(2)}€</p>
                    <button onclick="removeFromCart(${seat.id_seat})" class="text-red-400 text-xs hover:text-red-300">
                        Eliminar
                    </button>
                </div>
            </div>
        `;
    });

    carritoContenido.innerHTML = html;
    carritoTotal.textContent = `${total.toFixed(2)}€`;
}

// Función para eliminar del carrito
function removeFromCart(seatId) {
    const seatIndex = selectedSeats.findIndex(s => s.id_seat === seatId);
    if (seatIndex > -1) {
        selectedSeats.splice(seatIndex, 1);

        // Actualizar visualmente el asiento
        const seatIcon = document.getElementById(`seat-${seatId}`);
        if (seatIcon) {
            seatIcon.classList.remove("text-primary", "bg-primary/20");
            seatIcon.classList.add("text-gray-400", "hover:text-primary");
        }

        updateCartDisplay();
    }
}

function formatDate(dateStr) {
    const [year, month, day] = dateStr.split('-');
    return `${day}/${month}/${year}`;
}

async function finalizarCompra() {
    if (selectedSeats.length === 0) {
        alert('Selecciona al menos un asiento');
        return;
    }

    try {
        const purchaseData = {
            id_buy: 0,
            date: null,
            total_price: selectedSeats.reduce((sum, seat) => sum + seat.price, 0),
            user: {
                id_user: 1,
                name: "Cliente Existente",
                email: "cliente@ejemplo.com",
                password: "123456",
                address: "Calle Falsa 123",
                phone: "123456789"
            }
        };



        const purchaseResponse = await fetch('http://172.17.40.12:8080/api/purchases', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(purchaseData)
        });

        if (!purchaseResponse.ok) throw new Error('Error al crear la compra');
        const purchaseResult = await purchaseResponse.json();

        for (const seat of selectedSeats) {
            const ticketData = {
                id_ticket: 0,
                price: seat.price,
                id_buy: purchaseResult.id_buy,
                id_session: seat.session.id_session,
                id_room: seat.session.num_room,
                id_seat: seat.id_seat,
                id_cine: 0
            };

            const ticketResponse = await fetch('http://172.17.40.12:8080/api/ticket', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(ticketData)
            });

            if (!ticketResponse.ok) {
                console.error('Error al crear ticket para asiento:', seat.id_seat);
            }
        }

        alert('¡Compra realizada con éxito!');
        selectedSeats = [];
        updateCartDisplay();

        if (currentSession) {
            loadSits(currentSession.num_room, currentSession);
        }

    } catch (error) {
        console.error('Error al finalizar compra:', error);
        alert('Error al procesar la compra');
    }
}


document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('icon-carrito').addEventListener('click', function () {
        document.getElementById('menu-carrito').style.width = '400px';
    });

    document.getElementById('cerrar-carrito').addEventListener('click', function () {
        document.getElementById('menu-carrito').style.width = '0';
    });

    const finalizarBtn = document.querySelector('#menu-carrito button[class*="bg-primary"]');
    if (finalizarBtn) {
        finalizarBtn.addEventListener('click', finalizarCompra);
    }
});