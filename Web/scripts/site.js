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
            // También puedes mostrar un mensaje de error al usuario aquí si quieres
            movieTitle.innerHTML = "Error al cargar la película";
            movieImg.src = "./media/generic.png";
        });
}



function loadSessions(id) {
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
            const gridSessions = document.getElementById("grid-sessions");
            gridSessions.innerHTML = "";

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

            // Comprobar si groupedSessions está vacío
            if (Object.keys(groupedSessions).length === 0) {
                // No hay sesiones para mostrar
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
                            loadSits(session.num_room);
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



function loadSits(roomId) {
    const url = `http://172.17.40.12:8080/api/sessions`;
    const sitGrid = document.getElementById("sit-grid");

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar el JSON');
            }
            return response.json();
        })
        .then(data => {
            sitGrid.innerHTML = "";

            const salaInfo = data.find(r => r.num_room === roomId);

            sitGrid.style.gridTemplateColumns = `repeat(${salaInfo.cols}, minmax(30px, 1fr))`;


            if (!salaInfo) {
                sitGrid.innerHTML = "Sala no encontrada.";
                return;
            }

            for (let i = 0; i < salaInfo.totalSeats; i++) {
                const seatIcon = document.createElement("i");
                seatIcon.classList.add("fa-solid", "fa-couch");
                seatIcon.id = `seat-${i}`;

                seatIcon.addEventListener("click", function () {
                    reservar();
                })

                sitGrid.appendChild(seatIcon);
            }
        })
        .catch(error => {
            console.error("Error al cargar asientos:", error);
        });
}

function reservar() {

}


function formatDate(dateStr) {
    const [year, month, day] = dateStr.split('-');
    return `${day}/${month}/${year}`;
}
