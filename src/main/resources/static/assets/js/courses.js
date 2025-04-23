document.addEventListener('DOMContentLoaded', ()=> {
    const createBtn = document.getElementById('new-course-button');
    const editBtn = document.getElementById('edit')
    const createModal = document.getElementById('create-modal')
    const editModal = document.getElementById('edit-modal')
    const closeModal = document.querySelectorAll('.close-modal')
    const coursesContainer = document.querySelector(".courses-container");
    const createCourseBtn = document.getElementById('create-course');
    const newTitle = document.getElementById('new-title');
    const newBiblio = document.getElementById('new-biblio');
    const editTitle = document.getElementById('edit-title');
    const editBiblio = document.getElementById('edit-biblio');
    const saveEditBtn = document.getElementById('save-edit');

    let courseToEdit = null;

    const contadorCursos = document.querySelector('.my-courses p')
    const actualizarContador = ()=> {
        const total = coursesContainer.querySelectorAll('.course').length;
        contadorCursos.textContent = `${total} curso${total !==1 ? 's' : ''}`;
    }

    createBtn.addEventListener('click', ()=> {
        createModal.classList.remove('hidden');
    })

    editBtn.addEventListener('click', ()=> {
        editModal.classList.remove('hidden')
    })

    closeModal.forEach(btn => {
        btn.addEventListener('click', ()=> {
            createModal.classList.add('hidden')
            editModal.classList.add('hidden')
        })
    })

    createCourseBtn.addEventListener('click', ()=>{
        const title = newTitle.value.trim();
        const biblio = newBiblio.value.trim();

        if (title === "") {
            alert('Ponele un titulo')
            return;
        } 

        const course = document.createElement("div");
        course.classList.add('course')
        course.innerHTML = `
                <div class="course-header">
                    <div>
                        <h1>${title}</h1>
                        <p>0 temas</p>
                    </div>
                    <div class="course-controllers">
                        <img src="./assets/img/delete.svg" class="delete-btn" id='delete' alt="">
                        <img src="./assets/img/edit.svg" class="edit-btn" id="edit" alt="">
                    </div>
                </div>
                <hr>
                <div class="course-content">
                    <p>Bibliografia:</p>
                    <p class="bibliografia">${biblio}</p>  
                </div>
                <div class="course-buttons">
                    <button>Generar examen</button>
                    <button>Ver temas</button>
                </div>
        `
        document.querySelector('.courses-container').appendChild(course)
        createModal.classList.add('hidden')
        newTitle.value = "";
        newBiblio.value = "";
        actualizarContador();
    })


    coursesContainer.addEventListener("click", (e) => {
        if (e.target.classList.contains("delete-btn")) {
            const course = e.target.closest(".course");
            if (course) {
                course.remove();
                actualizarContador();
            }
        }
        if (e.target.classList.contains("edit-btn")) {
            courseToEdit = e.target.closest(".course");
            const title = courseToEdit.querySelector("h1").textContent;
            const biblio = courseToEdit.querySelector('.bibliografia').textContent;
            editTitle.value = title;
            editBiblio.value = biblio;
            editModal.classList.remove("hidden");
        }
    });

    saveEditBtn.addEventListener('click', ()=> {
        if (!courseToEdit) return;

        const newTitleValue = editTitle.value.trim();
        const newBiblioValue = editBiblio.value.trim();

        courseToEdit.querySelector('h1').textContent = newTitleValue;
        courseToEdit.querySelector('.bibliografia').textContent = newBiblioValue;
        
        editModal.classList.add('hidden');
    })
})