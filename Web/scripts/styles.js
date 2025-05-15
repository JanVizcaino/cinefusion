document.addEventListener("DOMContentLoaded", function(){
    appendVideo();
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
