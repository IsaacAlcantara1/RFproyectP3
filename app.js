let listaEmpleados = [];

const objEmpleado = {
    id: '',
    nombre: '',
    puesto: '',
    sueldo: '', 
    correo: ''
}

let editando = false;

const formulario = document.querySelector('#formulario');
const nombreInput = document.querySelector('#nombre');
const puestoInput = document.querySelector('#puesto');
const sueldoInput = document.querySelector('#sueldo'); 
const correoInput = document.querySelector('#correo');
const btnAgregarInput = document.querySelector('#btnAgregar');
const divEmpleados = document.querySelector('.div-empleados');

// Creamos un span para mostrar el mensaje de error
const mensajeError = document.createElement('span');
mensajeError.textContent = 'Todos los campos se deben llenar';
mensajeError.classList.add('error-message');
mensajeError.style.color = 'red'; // Establecemos el color rojo
mensajeError.style.display = 'none'; // Ocultamos el mensaje de error inicialmente
formulario.appendChild(mensajeError);

btnAgregarInput.addEventListener('click', () => {
    if(nombreInput.value === '' || puestoInput.value === '' || sueldoInput.value === '' || correoInput.value === '') { 
        // Mostramos el mensaje de error si los campos están vacíos
        mensajeError.style.display = 'block';
    } else {
        // Ocultamos el mensaje de error si los campos están llenos
        mensajeError.style.display = 'none';
    }
});

formulario.addEventListener('submit', validarFormulario);

function validarFormulario(e) {
    e.preventDefault();

    if(nombreInput.value === '' || puestoInput.value === '' || sueldoInput.value === '' || correoInput.value === '') { 
        // Mostramos el mensaje de error si los campos están vacíos
        mensajeError.style.display = 'block';
        return;
    } else {
        // Ocultamos el mensaje de error si los campos están llenos
        mensajeError.style.display = 'none';
    }

    if(editando) {
        editarEmpleado();
        editando = false;
    } else {
        objEmpleado.id = Date.now();
        objEmpleado.nombre = nombreInput.value;
        objEmpleado.puesto = puestoInput.value;
        objEmpleado.sueldo = sueldoInput.value; 
        objEmpleado.correo = correoInput.value;

        agregarEmpleado();
    }
}

function agregarEmpleado() {
    listaEmpleados.push({...objEmpleado});
    mostrarEmpleados();
    formulario.reset();
    limpiarObjeto();
}

function limpiarObjeto() {
    objEmpleado.id = '';
    objEmpleado.nombre = '';
    objEmpleado.puesto = '';
    objEmpleado.sueldo = ''; 
    objEmpleado.correo = '';
}

function mostrarEmpleados() {
    limpiarHTML();

    listaEmpleados.forEach(empleado => {
        const {id, nombre, puesto, sueldo, correo} = empleado; 

        const parrafo = document.createElement('p');
        parrafo.textContent = `${nombre} - ${puesto} - Sueldo: ${sueldo} - Correo: ${correo}`; 
        parrafo.dataset.id = id;

        const editarBoton = document.createElement('button');
        editarBoton.textContent = 'Editar';
        editarBoton.classList.add('btn', 'btn-editar');
        editarBoton.onclick = () => cargarEmpleado(empleado);
        parrafo.appendChild(editarBoton);

        const eliminarBoton = document.createElement('button');
        eliminarBoton.textContent = 'Eliminar';
        eliminarBoton.classList.add('btn', 'btn-eliminar');
        eliminarBoton.onclick = () => eliminarEmpleado(id);
        parrafo.appendChild(eliminarBoton);

        const hr = document.createElement('hr');

        divEmpleados.appendChild(parrafo);
        divEmpleados.appendChild(hr);
    });
}

function cargarEmpleado(empleado) {
    const {id, nombre, puesto, sueldo, correo} = empleado; 

    nombreInput.value = nombre;
    puestoInput.value = puesto;
    sueldoInput.value = sueldo; 
    correoInput.value = correo;

    objEmpleado.id = id;

    formulario.querySelector('button[type="submit"]').textContent = 'Actualizar';
    
    editando = true;
}

function editarEmpleado() {

    objEmpleado.nombre = nombreInput.value;
    objEmpleado.puesto = puestoInput.value;
    objEmpleado.sueldo = sueldoInput.value; 
    objEmpleado.correo = correoInput.value;

    listaEmpleados.forEach(empleado => {
        if(empleado.id === objEmpleado.id) {
            empleado.nombre = objEmpleado.nombre;
            empleado.puesto = objEmpleado.puesto;
            empleado.sueldo = objEmpleado.sueldo; 
            empleado.correo = objEmpleado.correo;
        }
    });

    limpiarHTML();
    mostrarEmpleados();
    formulario.reset();

    formulario.querySelector('button[type="submit"]').textContent = 'Agregar';
    
    editando = false;
}

function eliminarEmpleado(id) {
    listaEmpleados = listaEmpleados.filter(empleado => empleado.id !== id);
    limpiarHTML();
    mostrarEmpleados();
}

function limpiarHTML() {
    while(divEmpleados.firstChild) {
        divEmpleados.removeChild(divEmpleados.firstChild);
    }
}
const btnVacaciones = document.querySelector('#btnVacaciones');

btnVacaciones.addEventListener('click', () => {
    // Redirigir a otra página
    window.location.href = 'gestion.html'; 
});
