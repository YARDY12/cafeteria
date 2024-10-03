const API_DETALLE_PEDIDOS = 'http://localhost:8080/api/detalle-pedidos';
const detalleId = new URLSearchParams(window.location.search).get('id');// Capturamos el ID del detalle desde la URL
// Función para mostrar detalles de pedidos en la página de lista de pedidos


const detallePedidoForm = document.getElementById('detallePedidoForm');

// Si hay un ID de detalle de pedido en la URL, cargamos los detalles del pedido
if (detalleId) {
    cargarDetallePedido(detalleId);
}

// Manejar envío del formulario de detalles de pedidos
if (detallePedidoForm) {
    detallePedidoForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const idPedido = document.getElementById('id_pedido').value;

        const cantidad = parseFloat(document.getElementById('cantidad').value);
        const subtotal = parseFloat(document.getElementById('subtotal').value);
        const notaDetalle = document.getElementById('nota_detalle').value;
        const fechaDetalle = document.getElementById('fecha_detalle').value;
        const estadoDetalle = document.getElementById('estado_detalle').value;
        const descuento = parseFloat(document.getElementById('descuento').value);

        const detallePedidoData = {
            pedido: { id_pedido: idPedido },

            cantidad: cantidad,
            subtotal: subtotal,
            nota_detalle: notaDetalle,
            fecha_detalle: fechaDetalle,
            estado_detalle: estadoDetalle,
            descuento: descuento
        };

        if (detalleId) {
            // Si existe un ID de detalle, actualizamos el detalle
            updateDetallePedido(detalleId, detallePedidoData);
        } else {
            // De lo contrario, agregamos un nuevo detalle de pedido
            addDetallePedido(detallePedidoData);
        }
    });
}


fetch(API_DETALLE_PEDIDOS)
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al obtener los detalles de pedidos');
        }
        return response.json();
    })
    .then(data => {
        const tableBody = document.querySelector('table tbody');
        if (tableBody) {
            tableBody.innerHTML = ''; // Limpiamos la tabla antes de agregar los nuevos datos
            data.forEach(detallePedido => {
                const row = document.createElement('tr');
                row.innerHTML = `
          <td>${detallePedido.id_detalle}</td>
          <td>${detallePedido.pedido.id_pedido}</td>
          
          <td>${detallePedido.cantidad}</td>
          <td>${detallePedido.subtotal}</td>
          <td>${detallePedido.nota_detalle || 'N/A'}</td>
          <td>${new Date(detallePedido.fecha_detalle).toLocaleDateString()}</td>
          <td>${detallePedido.estado_detalle}</td>
          <td>${detallePedido.descuento}</td>
          <td>
              <a href="detalle-form.html?id=${detallePedido.id_detalle}" class="btn btn-warning btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Editar Detalle">
                  <i class="bi bi-pencil-square"></i>
              </a>
              <button class="btn btn-danger btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Eliminar Detalle" onclick="deleteDetallePedido(${detallePedido.id_detalle}, this)">
                  <i class="bi bi-trash"></i>
              </button>
          </td>
        `;
                tableBody.appendChild(row);
            });
        } else {
            console.error('No se encontró la tabla de detalles de pedidos');
        }
    })
    .catch(error => console.error('Error:', error));


// Agregar evento de submit al formulario
document.addEventListener('DOMContentLoaded', () => {
    const detallePedidoForm = document.getElementById('detallePedidoForm');
    if (detallePedidoForm) {
        detallePedidoForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const idPedido = document.getElementById('id_pedido').value;

            const cantidad = document.getElementById('cantidad').value;
            const subtotal = document.getElementById('subtotal').value;
            const notaDetalle = document.getElementById('nota_detalle').value;
            const fechaDetalle = document.getElementById('fecha_detalle').value;
            const estadoDetalle = document.getElementById('estado_detalle').value;
            const descuento = document.getElementById('descuento').value;

            const detallePedido = {
                pedido: { id_pedido: idPedido },

                cantidad: cantidad,
                subtotal: subtotal,
                nota_detalle: notaDetalle,
                fecha_detalle: fechaDetalle,
                estado_detalle: estadoDetalle,
                descuento: descuento
            };

            addDetallePedido(detallePedido);
        });
    }
});

// Agregar detalle de pedido

function addDetallePedido(detallePedido) {
    fetch(API_DETALLE_PEDIDOS, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(detallePedido),
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text); });
            }
            return response.json();
        })
        .then(() => {
            alert('Detalle de pedido agregado con éxito');
            window.location.href = 'detalle-list.html';  // Redirige a la lista de detalles de pedidos
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al agregar el detalle de pedido: ' + error.message);
        });
}

// Actualizar detalle de pedido
function updateDetallePedido(id, detallePedido) {
    const updatedDetallePedido = {
        id_detalle: detallePedido.id_detalle,
        pedido: { id_pedido: detallePedido.pedido.id_pedido },

        cantidad: detallePedido.cantidad,
        subtotal: detallePedido.subtotal,
        nota_detalle: detallePedido.nota_detalle,
        fecha_detalle: detallePedido.fecha_detalle,
        estado_detalle: detallePedido.estado_detalle,
        descuento: detallePedido.descuento
    };

    fetch(`${API_DETALLE_PEDIDOS}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedDetallePedido),
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text); });
            }
            return response.json();
        })
        .then(() => {
            alert('Detalle de pedido actualizado con éxito');
            window.location.href = 'detalle-list.html';  // Redirige a la lista de detalles de pedidos
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al actualizar el detalle de pedido: ' + error.message);
        });
}

// Función para eliminar un detalle de pedido
window.deleteDetallePedido = function(id, button) {
    if (confirm('¿Estás seguro de que deseas eliminar este detalle de pedido?')) {
        fetch(`${API_DETALLE_PEDIDOS}/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert('Detalle eliminado con éxito');
                    button.closest('tr').remove(); // Remueve la fila de la tabla
                } else {
                    throw new Error('No se pudo eliminar el detalle del pedido');
                }
            })
            .catch(error => {
                console.error('Error al eliminar el detalle del pedido:', error);
                alert('Error al eliminar el detalle del pedido: ' + error.message);
            });
    }
};

// Cargar los detalles de un pedido
function cargarDetallePedido(id) {
    fetch(`${API_DETALLE_PEDIDOS}/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener el detalle del pedido');
            }
            return response.json();
        })
        .then(data => {
            // Rellenar los campos del formulario con los detalles del pedido
            document.getElementById('id_detalle').value = data.id_detalle; // Verifica que este ID existe
            document.getElementById('id_pedido').value = data.pedido.id_pedido; // Verifica que este ID existe

            document.getElementById('cantidad').value = data.cantidad; // Verifica que este ID existe
            document.getElementById('subtotal').value = data.subtotal; // Verifica que este ID existe
            document.getElementById('nota_detalle').value = data.nota_detalle || ''; // Verifica que este ID existe
            document.getElementById('fecha_detalle').value = new Date(data.fecha_detalle).toISOString().split('T')[0]; // Verifica que este ID existe
            document.getElementById('estado_detalle').value = data.estado_detalle; // Verifica que este ID existe
            document.getElementById('descuento').value = data.descuento; // Verifica que este ID existe
        })
        .catch(error => console.error('Error al cargar el detalle del pedido:', error));
}

function mostrarDetallesPedidos() {
    fetch(API_DETALLE_PEDIDOS)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener los detalles de pedidos');
            }
            return response.json();
        })
        .then(data => {
            const tableBody = document.getElementById('detalle-pedido-table-body');
            if (tableBody) {
                tableBody.innerHTML = ''; // Limpiamos la tabla antes de agregar los nuevos datos
                data.forEach(detallePedido => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                      <td>${detallePedido.id_detalle}</td>
                      <td>${detallePedido.pedido.id_pedido}</td>
                      <td>${detallePedido.cantidad}</td>
                      <td>${detallePedido.subtotal}</td>
                      <td>${detallePedido.nota_detalle}</td>
                      <td>${new Date(detallePedido.fecha_detalle).toLocaleDateString()}</td>
                      <td>${detallePedido.estado_detalle}</td>
                      <td>${detallePedido.descuento}</td>
                      <td>
                          <a href="detalle-form.html?id=${detallePedido.id_detalle}" class="btn btn-warning btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Editar Detalle">
                              <i class="bi bi-pencil-square"></i>
                          </a>
                          <button class="btn btn-danger btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Eliminar Detalle" onclick="deleteDetallePedido(${detallePedido.id_detalle}, this)">
                              <i class="bi bi-trash"></i>
                          </button>
                      </td>
                  `;
                    tableBody.appendChild(row);
                });
            } else {
                console.error('No se encontró la tabla de detalles de pedidos');
            }
        })
        .catch(error => console.error('Error:', error));
}

// Cargar la lista de detalles de pedidos al cargar la página
document.addEventListener('DOMContentLoaded', mostrarDetallesPedidos);

// Llama a la función para mostrar los detalles de pedidos al cargar la página
mostrarDetallesPedidos();

document.addEventListener('DOMContentLoaded', () => {
    const detalleId = new URLSearchParams(window.location.search).get('id');
    if (detalleId) {
        cargarDetallePedido(detalleId);
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const detallePedidoForm = document.getElementById('detallePedidoForm');
    if (detallePedidoForm) {
        detallePedidoForm.addEventListener('submit', (e) => {
            e.preventDefault();
            // Asegúrate de que todos los campos son válidos antes de proceder
            if (detallePedidoForm.checkValidity()) {
                // Resto del código para recoger los datos y llamar a addDetallePedido
            } else {
                detallePedidoForm.classList.add('was-validated');
            }
        });
    }
});

// Inicialización de tooltips de Bootstrap
var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
if (tooltipTriggerList.length) { // Verifica si hay elementos para inicializar
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}

// Validación de Formularios
(function () {
    'use strict';
    var forms = document.querySelectorAll('.needs-validation');
    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
})();

// Mostrar spinner de carga al hacer clic en los enlaces de acción
function showLoading(element) {
    const spinner = element.querySelector('.spinner-border');
    if (spinner) {
        spinner.classList.remove('d-none');
    }
}

// Función para agregar efecto de transformación a los botones
document.querySelectorAll('.btn').forEach(button => {
    button.addEventListener('mouseover', () => {
        button.style.transform = 'scale(1.05)';
        button.style.transition = 'transform 0.2s ease-in-out';
    });

    button.addEventListener('mouseout', () => {
        button.style.transform = 'scale(1)';
    });
});

// Efecto de hover en las opciones de la lista de pedidos
document.querySelectorAll('.list-group-item').forEach(item => {
    item.addEventListener('mouseover', () => {
        item.style.transform = 'scale(1.05)';
        item.style.transition = 'transform 0.3s ease';
    });

    item.addEventListener('mouseout', () => {
        item.style.transform = 'scale(1)';
    });
});


// Agregar efecto de hover a las filas de la tabla
document.addEventListener('DOMContentLoaded', function() {
    const tableRows = document.querySelectorAll('.table tbody tr');
    tableRows.forEach(row => {
        row.addEventListener('mouseenter', () => {
            row.style.backgroundColor = 'rgba(0, 0, 0, 0.1)';
            row.style.transition = 'background-color 0.3s ease';
        });
        row.addEventListener('mouseleave', () => {
            row.style.backgroundColor = '';
        });
    });
});