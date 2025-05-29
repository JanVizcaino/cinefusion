document.addEventListener("DOMContentLoaded", function () {
    appendVideo();
    navToggler();
    chartToggler();
});

function appendVideo() {
    const video = document.createElement("video");
    video.autoplay = true;
    video.muted = true;
    video.loop = true;
    video.playbackRate = 0.5;
    video.id = "backgroundVideo";

    const source = document.createElement("source");
    source.src = "./media/background.mp4";
    source.type = "video/mp4";

    video.appendChild(source);
    video.innerHTML += "Tu navegador no soporta videos HTML5.";

    document.querySelector("main").appendChild(video);

    const videoOverlay = document.createElement("div");
    videoOverlay.id = "videoOverlay";
    document.querySelector("main").appendChild(videoOverlay);
}

function navToggler() {
    const nav = document.getElementById('sidebarNav');

    nav.addEventListener("mouseenter", () => {
        nav.classList.remove('opacity-30', 'w-14', 'overflow-hidden', 'p-1');
        nav.classList.add('opacity-100', 'p-3', 'w-32');

        Array.from(nav.children).forEach(button => {
            const iconClass = button.querySelector('i')?.className;
            if (iconClass === 'fa-solid fa-house') {
                button.textContent = 'Inicio';
            } else if (iconClass === 'fa-solid fa-film') {
                button.textContent = 'Cartelera';
            } else if (iconClass === 'fa-solid fa-envelope') {
                button.textContent = 'Contacto';
            } else if (iconClass === 'fa-solid fa-circle-user'){
                button.textContent = 'Cliente';
            }
        });
    });

    nav.addEventListener("mouseleave", () => {
        if (window.scrollY > 50) {
            nav.classList.add('opacity-30', 'w-14', 'overflow-hidden', 'p-1');
            nav.classList.remove('opacity-100', 'p-3', 'w-32');

            Array.from(nav.children).forEach(button => {
                const text = button.textContent.trim();
                if (text === 'Inicio') {
                    button.innerHTML = '<i class="fa-solid fa-house"></i>';
                } else if (text === 'Cartelera') {
                    button.innerHTML = '<i class="fa-solid fa-film"></i>';
                } else if (text === 'Contacto') {
                    button.innerHTML = '<i class="fa-solid fa-envelope"></i>';
                } else if (text === 'Cliente'){
                    button.innerHTML = '<i class="fa-solid fa-circle-user"></i>';  
                }
            });
        }
    });

    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            nav.classList.add('opacity-30', 'w-14', 'overflow-hidden', 'p-1');
            nav.classList.remove('opacity-100', 'p-3', 'w-32');

            Array.from(nav.children).forEach(button => {
                const text = button.textContent.trim();
                if (text === 'Inicio') {
                    button.innerHTML = '<i class="fa-solid fa-house"></i>';
                } else if (text === 'Cartelera') {
                    button.innerHTML = '<i class="fa-solid fa-film"></i>';
                } else if (text === 'Contacto') {
                    button.innerHTML = '<i class="fa-solid fa-envelope"></i>';
                } else if (text === 'Cliente'){
                    button.innerHTML = '<i class="fa-solid fa-circle-user"></i>';  
                }
            });
        } else {
            nav.classList.remove('opacity-30', 'w-14', 'overflow-hidden', 'p-1');
            nav.classList.add('opacity-100', 'p-3', 'w-32');

            Array.from(nav.children).forEach(button => {
                const iconClass = button.querySelector('i')?.className;
                if (iconClass === 'fa-solid fa-house') {
                    button.textContent = 'Inicio';
                } else if (iconClass === 'fa-solid fa-film') {
                    button.textContent = 'Cartelera';
                } else if (iconClass === 'fa-solid fa-envelope') {
                    button.textContent = 'Contacto';
                } else if (iconClass === 'fa-solid fa-circle-user'){
                    button.textContent = 'Cliente';
                }
            });
        }
    });
}

function chartToggler(){
    const iconCarrito = document.getElementById('icon-carrito');
    const menuCarrito = document.getElementById('menu-carrito');
    const cerrarCarrito = document.getElementById('cerrar-carrito');

    function mostrarCarrito() {
        menuCarrito.classList.remove("w-0");
        menuCarrito.classList.add("w-96", "border-l-4");
        console.log("mostrar");
    }

    function ocultarCarrito() {
        menuCarrito.classList.remove("w-96", "border-l-4");
        menuCarrito.classList.add("w-0");
        console.log("ocultar");
    }

    iconCarrito.addEventListener('click', function(event){
        event.stopPropagation(); 
        if(menuCarrito.classList.contains("w-0")){
            mostrarCarrito();
        } else {
            ocultarCarrito();
        }
    });

    cerrarCarrito?.addEventListener('click', function(event){
        event.stopPropagation();
        ocultarCarrito();
    });

    document.addEventListener('click', function(event){
        if (!menuCarrito.contains(event.target) && !iconCarrito.contains(event.target)) {
            if (!menuCarrito.classList.contains("w-0")) {
                ocultarCarrito();
            }
        }
    });
}
