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
        modalTitle.textContent = "Crear Nuevo Tema";
        document.querySelectorAll('.item-course input').forEach(input => {
            input.value =  '';
        })

    }else if(action === 'edit'){
        modalTitle.textContent = 'Editar Tema';
        const card = element.closest('.item-card');
        const title = card.querySelector('h4').textContent;
        
        // aca va a ir a buscar a la base de datos a buscar los dtos del tema, no olvidar que tambien seguro vamos a buscar por id, hay que revisar con los chicos cuando llegue el momento de implementar front con back. Mientras se agregan datos ficticios.
        const description = 'Estudio de proposiciones, conectores lógicos, tablas de verdad, tautologías, contradicciones, inferencias válidas, cuantificadores y lógica de predicados de primer orden.';
        const bibliography = '1. Libro de Lógica Formal, Autor: Juan Pérez, 2020. 2. Introducción a la Lógica, Autor: María López, 2019.';

        const input = document.querySelectorAll('.item-card-modal input');

        console.log("Inputs encontrados:", input); // Depuración
    console.log("Número de inputs:", input.length); // Verifica cuántos inputs hay
        input[0].value = title;
        input[1].value = description;
        input[2].value = bibliography;
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
