let preguntaActual = 1;
const totalPreguntas = 10;

document.getElementById("siguienteBtn").addEventListener("click", () => {
  if (preguntaActual < totalPreguntas) {
    preguntaActual++;
    actualizarProgreso();
    // Aquí puedes cargar otra pregunta si lo deseas
  }
});

function actualizarProgreso() {
  const porcentaje = (preguntaActual / totalPreguntas) * 100;
  document.getElementById("progressBar").style.width = porcentaje + "%";
  document.querySelector(".progress-text").textContent = `Pregunta ${preguntaActual} de ${totalPreguntas}`;
}
