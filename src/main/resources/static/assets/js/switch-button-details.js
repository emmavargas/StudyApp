function switchContent(contentType, button) {
    const card = button.closest('.item-card');
    
    const buttons = card.querySelectorAll('.switch-btn');
    
    buttons.forEach(btn => btn.classList.remove('active'));
    
    button.classList.add('active');
    
    const paragraph = card.querySelector('p');
    
    if (contentType === 'description') {
        paragraph.textContent = 'Estudio de proposiciones, conectores lógicos, tablas de verdad, tautologías, contradicciones, inferencias válidas, cuantificadores y lógica de predicados de primer orden.';
    } else if (contentType === 'bibliography') {
        paragraph.textContent = '1. Libro de Lógica Formal, Autor: Juan Pérez, 2020. 2. Introducción a la Lógica, Autor: María López, 2019.';
    }
}