const API_DETALLE_PEDIDOS = 'http://localhost:8080/api/detalle-pedidos';

// Función para mostrar detalles de pedidos en la página de lista de pedidos

function mostrarDetallesPedidos() {

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
          <td>${detallePedido.producto.id_producto}</td>
          <td>${detallePedido.cantidad}</td>
          <td>${detallePedido.subtotal}</td>
          <td>${detallePedido.nota_detalle}</td>
          <td>${new Date(detallePedido.fecha_detalle).toLocaleDateString()}</td>
          <td>${detallePedido.estado_detalle}</td>
          <td>${detallePedido.descuento}</td>
        `;
                    tableBody.appendChild(row);
                });
            } else {
                console.error('No se encontró la tabla de detalles de pedidos');
            }
        })
        .catch(error => console.error('Error:', error));
}

// Agregar evento de submit al formulario
document.addEventListener('DOMContentLoaded', () => {
    const detallePedidoForm = document.getElementById('detallePedidoForm');
    if (detallePedidoForm) {
        detallePedidoForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const idPedido = document.getElementById('id_pedido').value;
            const producto = document.getElementById('producto').value;
            const cantidad = document.getElementById('cantidad').value;
            const subtotal = document.getElementById('subtotal').value;
            const notaDetalle = document.getElementById('nota_detalle').value;
            const fechaDetalle = document.getElementById('fecha_detalle').value;
            const estadoDetalle = document.getElementById('estado_detalle').value;
            const descuento = document.getElementById('descuento').value;

            const detallePedido = {
                pedido: { id_pedido: idPedido },
                producto: { nombre: producto },
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
            window.location.href = 'detalle-pedido-list.html';  // Redirige a la lista de detalles de pedidos
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
        producto: { nombre: detallePedido.producto.nombre },
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
            window.location.href = 'detalle-pedido-list.html';  // Redirige a la lista de detalles de pedidos
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al actualizar el detalle de pedido: ' + error.message);
        });
}

// Cargar los detalles del pedido en el formulario
function cargarDetallePedido(id) {
    fetch(`${API_DETALLE_PEDIDOS}/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener el detalle de pedido');
            }
            return response.json();
        })
        .then(data => {
            // Rellenar el formulario con los detalles del pedido
            const idDetalleField = document.getElementById('id_detalle');
            const idPedidoField = document.getElementById('id_pedido');
            const productoField = document.getElementById('producto');
            const cantidadField = document.getElementById('cantidad');
            const subtotalField = document.getElementById('subtotal');
            const notaDetalleField = document.getElementById('nota_detalle');
            const fechaDetalleField = document.getElementById('fecha_detalle');
            const estadoDetalleField = document.getElementById('estado_detalle');
            const descuentoField = document.getElementById('descuento');

            if (idDetalleField) idDetalleField.value = data.id_detalle;
            if (idPedidoField) idPedidoField.value = data.pedido.id_pedido;
            if (productoField) productoField.value = data.producto.nombre;
            if (cantidadField) cantidadField.value = data.cantidad;
            if (subtotalField) subtotalField.value = data.subtotal;
            if (notaDetalleField) notaDetalleField.value = data.nota_detalle || '';
            if (fechaDetalleField) fechaDetalleField.value = new Date(data.fecha_detalle).toISOString().split('T')[0];
            if (estadoDetalleField) estadoDetalleField.value = data.estado_detalle;
            if (descuentoField) descuentoField.value = data.descuento;
        })
        .catch(error => console.error('Error:', error));
}


// Llama a la función para mostrar los detalles de pedidos al cargar la página
document.addEventListener('DOMContentLoaded', mostrarDetallesPedidos);

// Llama a la función para mostrar los detalles de pedidos al cargar la página
mostrarDetallesPedidos();



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