document.addEventListener("DOMContentLoaded", async () => {
    const main = document.querySelector("main");
    const overlay = document.getElementById("overlay");

    await loadStartWindow();

    document.querySelectorAll("header a").forEach(link => {
        link.addEventListener("click", async (e) => {
            e.preventDefault();
            const url = e.target.getAttribute("href");

            overlay.style.display = "none";

            await loadContent(url);
        });
    });

    async function loadStartWindow() {
        try {
            const response = await fetch("/views/start.html");
            const htmlText = await response.text();

            const tempDiv = document.createElement("div");
            tempDiv.innerHTML = htmlText;

            const startWindow = tempDiv.querySelector("#start-window");

            if (startWindow) {
                main.appendChild(startWindow);
                overlay.style.display = "block";

                startWindow.querySelector("#introduction")?.addEventListener("click", async () => {
                    await loadContent("introduction.html");
                    closeStartWindow(startWindow);
                });

                startWindow.querySelector("#cartel")?.addEventListener("click", async () => {
                    await loadContent("cartel.html");
                    closeStartWindow(startWindow);
                });

                startWindow.querySelector("#contact")?.addEventListener("click", async () => {
                    await loadContent("contact.html");
                    closeStartWindow(startWindow);
                });

                document.querySelector(".btn-principal")?.addEventListener("click", () => {
                    alert("Log In pulsado");
                });

                startWindow.querySelector(".btn-principal")?.addEventListener("click", () => {
                    alert("Log In pulsado");
                });

            } else {
                console.error("No se encontró #start-window en start.html");
            }
        } catch (error) {
            console.error("Error al cargar start.html:", error);
        }
    }

    async function loadContent(url) {
        try {
            const response = await fetch(`/views/${url}`);
            const htmlText = await response.text();

            const tempDiv = document.createElement("div");
            tempDiv.innerHTML = htmlText;

            const bodyContent = tempDiv.querySelector("body") || tempDiv;

            main.innerHTML = bodyContent.innerHTML;

            updateSectionTitle(url);

            if (url === "cartel.html") {
                loadMovies();
            }

        } catch (error) {
            console.error("Error al cargar el contenido:", error);
            main.innerHTML = "<p class='text-danger'>Error al cargar el contenido.</p>";
        }
    }

    function updateSectionTitle(url) {
        const sectionTitle = document.getElementById("section-title");
        const sectionSubtitle = document.getElementById("section-subtitle");

        switch (url) {
            case "introduction.html":
                sectionTitle.textContent = "Nuestro cine";
                sectionSubtitle.textContent = "Descubre la experiencia Cinefusion.";
                break;
            case "cartel.html":
                sectionTitle.textContent = "Cartelera";
                sectionSubtitle.textContent = "Las mejores películas las tienes en cinefusion.";
                break;
            case "contact.html":
                sectionTitle.textContent = "Contacto";
                sectionSubtitle.textContent = "Estamos aquí para ayudarte.";
                break;
            default:
                sectionTitle.textContent = "Sección";
                sectionSubtitle.textContent = "";
        }
    }

    function loadMovies() {
        fetch('./json/movies.json')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar el JSON');
                }
                return response.json();
            })
            .then(data => {
                const container = document.getElementById('grid-cartel');

                data.movies.forEach(movie => {
                    const movieCard = document.createElement('div');
                    movieCard.classList.add('p-3', 'position-relative', 'card-cartel');
                    movieCard.id = movie.id;

                    movieCard.innerHTML = `
                        <div class="imagen-hover-container">
                                <div class="image-container-movie">
                                    <img src="${movie.image}" onerror="this.src='../media/generic.png'" alt="${movie.title}">
                                </div>
                                <div class="overlay-movie">
                                    <h5 class="titulo">${movie.title}</h5>
                                </div>
                            </div>
                    `;

                    container.appendChild(movieCard);

                    movieCard.addEventListener("click", async function () {
                        await loadContent("tickets.html");
                        loadMovieInfo(movie.id);
                    });
                });
            })
            .catch(error => {
                console.error('Error al procesar el JSON:', error);
            });
    }

    async function loadMovieInfo(id) {
        const url = `./json/movies.json`;
    
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar el JSON');
                }
                return response.json();
            })
            .then(data => {
                const movie = data.movies.find(m => m.id === id);
                if (!movie) {
                    throw new Error(`No se encontró la película con id ${id}`);
                }
    
                const movieTitle = document.getElementById("movie-title");
                const movieImg = document.getElementById("movie-img");
                const movieDescription = document.getElementById("movie-description");
                const movieDirector = document.getElementById("movie-director");
                const movieDuration = document.getElementById("movie-duration");
                const movieCasting = document.getElementById("movie-casting");
    
                movieTitle.innerHTML = movie.title;
                movieImg.src = movie.image;
                movieDescription.innerHTML = movie.description;
                movieDirector.innerHTML = movie.director;
                movieDuration.innerHTML = movie.duration;
                movieCasting.innerHTML = movie.casting;
    
                loadSessions(id);
    
            })
            .catch(error => {
                console.error('Error al procesar el JSON:', error);
            });
    }
}); 

function closeStartWindow(startWindow) {
    startWindow.remove();
    overlay.style.display = "none";
}

function loadSessions(id) {
    const url = `./json/sessions.json`;

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

            data.sessions.forEach(session => {
                if (session.movieId == id) {
                    const sessionBlock = document.createElement("div");
                    sessionBlock.classList.add("mb-4");

                    const roomHeader = document.createElement("h2");
                    roomHeader.textContent = `Sala ${session.roomId}`;
                    sessionBlock.appendChild(roomHeader);

                    session.dates.forEach(date => {
                        const dateHeader = document.createElement("h4");
                        dateHeader.classList.add("session-date");
                        dateHeader.textContent = date.date;
                        sessionBlock.appendChild(dateHeader);

                        const timeGrid = document.createElement("div");
                        timeGrid.classList.add("session-time-grid");

                        date.times.forEach(time => {
                            const sessionCard = document.createElement("div");
                            sessionCard.classList.add("grid-sessions-card", "d-flex", "justify-content-center", "align-items-center");
                            sessionCard.innerHTML = `<p>${time}</p>`;

                            sessionCard.addEventListener("click", () => {
                                sitContainer.style.display = "block";
                                loadSits(session.roomId);
                            });

                            timeGrid.appendChild(sessionCard);
                        });

                        sessionBlock.appendChild(timeGrid);
                    });

                    gridSessions.appendChild(sessionBlock);
                }
            });
        })
        .catch(error => {
            console.error("Error al cargar sesiones:", error);
        });
}


function loadSits(roomId){
    const url = `./json/rooms.json`;
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

            const salaInfo = data.rooms.find(r => r.id === roomId); 

            sitGrid.style.gridTemplateColumns = `repeat(${salaInfo.cols}, minmax(30px, 1fr))`;


            if (!salaInfo) {
                sitGrid.innerHTML = "Sala no encontrada.";
                return;
            }

            for (let i = 0; i < salaInfo.totalSeats; i++) {
                const seatIcon = document.createElement("i");
                seatIcon.classList.add("fa-solid", "fa-couch");
                sitGrid.appendChild(seatIcon);
            }
        })
        .catch(error => {
            console.error("Error al cargar asientos:", error);
        });
}