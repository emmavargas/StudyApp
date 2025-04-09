const modal = document.querySelector(".modal");
const cancel = document.querySelector(".cancel");

const modalTitle = document.getElementById('modal-title');

function openModal(action, element) {
    modal.style.display = "block";
    document.body.style.overflow = "hidden";
    setTimeout(() => {
        modal.style.opacity = "1";
    }, 10);

    if(action === 'create'){
        modalTitle.textContent = "Crear Nuevo Curso";
        document.querySelectorAll('.item-course input').forEach(input => {
            input.value =  '';
        })

    }else if(action === 'edit'){
        modalTitle.textContent = 'Editar Curso';
        const card = element.closest('.cursos-card');
        const title = card.querySelector('h2').textContent;
        // aca va a ir a buscar a la base de datos a buscar los dtos del curso, no olvidar que tambien seguro vamos a buscar por id, hay que revisar con los chicos cuando llegue el momento de implementar front con back. Mientras se agregan datos ficticios.
        const bibliography = '1. Libro de Lógica Formal, Autor: Juan Pérez, 2020. 2. Introducción a la Lógica, Autor: María López, 2019.';

        const input = document.querySelectorAll('.item-course input');
        input[0].value = title;
        input[1].value = bibliography;
    }

    
}

function closeModal() {
    modal.style.opacity = "0";
    document.body.style.overflow = "auto";
    setTimeout(() => {
        modal.style.display = "none";
    }, 300); 
}

window.onclick = function(event) {
    if (event.target === modal) {
        closeModal();
    }
}

cancel.onclick = function() {
    closeModal();
}



document.getElementById('modal-form').addEventListener('submit', function(event) {

    event.preventDefault(); 

    const title = document.getElementById('course-title').value;
    const bibiliography = document.getElementById('course-bibliography').value;

    const courseCard = createCourseCard(title, bibiliography);

    const coursesContainer = document.querySelector('.cursos-container');

    const rows = coursesContainer.querySelectorAll('.cursos-row');

    

    if(rows.length=== 0 || rows[rows.length-1].children.length >= 3){
        const newRow = document.createElement('div');
        newRow.classList.add('cursos-row');
        newRow.appendChild(courseCard);
        coursesContainer.appendChild(newRow);
    }else{
        rows[rows.length-1].appendChild(courseCard);
    }

    this.reset();
    closeModal();
});

function createCourseCard(title, bibiliography){
    const courseCard = document.createElement('div');
    courseCard.classList.add('cursos-card');
    // a futuro implementaremos guardar la bibliografia en un fetch
    courseCard.innerHTML = `
        <div class="title-card"> 
            <h2>${title}</h2>
            <img src="../assets/img/write.svg" alt="editar curso" onclick="openModal('edit',this)">
        </div>

        <span>0 temas</span>

        <div class="action-card">
            <button>Generar examen</button>
            <button onclick="redirectionTest()">Ver detalles</button>
        </div>
    `;
    return courseCard;
}
