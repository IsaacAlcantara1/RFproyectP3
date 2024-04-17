const form = document.getElementById('ausenciaForm');
const listaAusencias = document.getElementById('listaAusencias');


const mensajeError = document.createElement('span');
mensajeError.textContent = 'Todos los campos son obligatorios';
mensajeError.style.color = 'red';
mensajeError.style.display = 'none'; 
document.querySelector('.form-container').appendChild(mensajeError);

form.addEventListener('submit', agregarAusencia);

function agregarAusencia(event) {
    event.preventDefault();
    
    const nombre = form.nombre.value;
    const tipo = form.tipo.value;
    const fecha = form.fecha.value;

    if (nombre && tipo && fecha) {
        const nuevaAusencia = { nombre, tipo, fecha };
        mostrarAusencia(nuevaAusencia);
        form.reset();
        mensajeError.style.display = 'none'; 
    } else {
        mensajeError.style.display = 'block'; 
    }
}

function mostrarAusencia(ausencia) {
    const li = document.createElement('li');
    li.innerHTML = `${ausencia.nombre}: ${ausencia.tipo.charAt(0).toUpperCase() + ausencia.tipo.slice(1)} el ${formatDate(ausencia.fecha)}`;
    const deleteBtn = document.createElement('button');
    deleteBtn.textContent = 'Eliminar';
    deleteBtn.classList.add('delete-btn');
    deleteBtn.addEventListener('click', () => {
        li.remove();
    });
    li.appendChild(deleteBtn);
    listaAusencias.appendChild(li);
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
}
