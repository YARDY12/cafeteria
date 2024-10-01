const API_PEDIDOS = 'http://localhost:8080/api/pedidos';
const pedidoId = new URLSearchParams(window.location.search).get('id'); // Capturamos el ID del pedido desde la URL

document.addEventListener('DOMContentLoaded', () => {

    const pedidoForm = document.getElementById('pedidoForm');

    // Si hay un ID de pedido en la URL, cargamos los detalles del pedido
    if (pedidoId) {
        cargarPedido(pedidoId);
    }

    // Manejar envío del formulario de pedidos
    if (pedidoForm) {
        pedidoForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const fecha = document.getElementById('fecha').value;
            const numMesa = document.getElementById('num_mesa').value;
            const nomCliente = document.getElementById('nom_cliente').value;
            const notaEspecial = document.getElementById('nota_especial').value;
            const estado = document.getElementById('estado').value;
            const metodoPago = document.getElementById('metodo_pago').value;
            const nomMesero = document.getElementById('nom_mesero').value; // Capturamos el nombre del mesero
            const precio = parseFloat(document.getElementById('precio').value); // Capturamos el precio
            const cantidad = parseInt(document.getElementById('cantidad').value); // Capturamos la cantidad
            const total = precio * cantidad; // Calculamos el total

            const pedidoData = {
                fecha: fecha,
                num_mesa: numMesa,
                nom_cliente: nomCliente,
                nota_especial: notaEspecial,
                estado: estado,
                metodo_pago: metodoPago,
                total: total,
                nom_mesero: nomMesero // Asegúrate de incluir el nombre del mesero
            };

            if (pedidoId) {
                // Si existe un ID de pedido, actualizamos el pedido
                updatePedido(pedidoId, pedidoData);
            } else {
                // De lo contrario, agregamos un nuevo pedido
                addPedido(pedidoData);
            }
        });
    }

    // Agregar pedido
    function addPedido(pedido) {
        fetch(API_PEDIDOS, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pedido),
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                return response.json();
            })
            .then(() => {
                alert('Pedido agregado con éxito');
                window.location.href = 'pedido-list.html';  // Redirige a la lista de pedidos
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al agregar el pedido: ' + error.message);
            });
    }


    // Actualizar pedido
    function updatePedido(id, pedido) {
        fetch(`${API_PEDIDOS}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pedido),
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                return response.json();
            })
            .then(() => {
                alert('Pedido actualizado con éxito');
                window.location.href = 'pedido-list.html';  // Redirige a la lista de pedidos
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al actualizar el pedido: ' + error.message);
            });
    }

    // Cargar los detalles del pedido en el formulario
    function cargarPedido(id) {
        fetch(`${API_PEDIDOS}/${id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener el pedido');
                }
                return response.json();
            })
            .then(data => {
                // Rellenar el formulario con los detalles del pedido
                const fechaField = document.getElementById('fecha');
                const numMesaField = document.getElementById('num_mesa');
                const nomClienteField = document.getElementById('nom_cliente');
                const notaEspecialField = document.getElementById('nota_especial');
                const estadoField = document.getElementById('estado');
                const metodoPagoField = document.getElementById('metodo_pago');
                const precioField = document.getElementById('precio');
                const cantidadField = document.getElementById('cantidad');
                const nomMeseroField = document.getElementById('nom_mesero');

                if (fechaField) fechaField.value = new Date(data.fecha).toISOString().split('T')[0];
                if (numMesaField) numMesaField.value = data.num_mesa;
                if (nomClienteField) nomClienteField.value = data.nom_cliente;
                if (notaEspecialField) notaEspecialField.value = data.nota_especial || '';
                if (estadoField) estadoField.value = data.estado;
                if (metodoPagoField) metodoPagoField.value = data.metodo_pago;
                if (precioField) precioField.value = data.total / (data.cantidad || 1); // Ajustar si tienes el campo cantidad
                if (cantidadField) cantidadField.value = data.cantidad; // Si tienes este campo en el formulario
                if (nomMeseroField) nomMeseroField.value = data.nom_mesero; // Asegúrate de tener este campo en tu formulario
            })
            .catch(error => console.error('Error:', error));
    }



    // Función para mostrar pedidos en la página de lista de pedidos

    function mostrarPedidos() {
        fetch(API_PEDIDOS)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener los pedidos');
                }
                return response.json();
            })
            .then(data => {
                const tableBody = document.querySelector('table tbody');
                if (tableBody) {
                    tableBody.innerHTML = ''; // Limpiamos la tabla antes de agregar los nuevos datos
                    data.forEach(pedido => {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                        <td>${pedido.id_pedido}</td>
                        <td>${new Date(pedido.fecha).toLocaleDateString()}</td>
                        <td>${pedido.num_mesa}</td>
                        <td>${pedido.nom_cliente}</td>
                        <td>${pedido.nota_especial || 'N/A'}</td>
                        <td>${pedido.estado}</td>
                        <td>${pedido.total.toFixed(2)}</td>
                        <td>${pedido.metodo_pago}</td>
                        <td>${pedido.nom_mesero || 'N/A'}</td>
                        <td>
                            <a href="pedido-form.html?id=${pedido.id_pedido}" class="btn btn-warning btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Editar Pedido">
                                <i class="bi bi-pencil-square"></i>
                            </a>
                            <a href="pedido-list.html?id=${pedido.id_pedido}" class="btn btn-danger btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Eliminar Pedido" onclick="return confirm('¿Estás seguro de que deseas eliminar este pedido?');">
                                <i class="bi bi-trash"></i>
                            </a>
                        </td>
                    `;
                        tableBody.appendChild(row);
                    });
                } else {
                    console.error('No se encontró la tabla de pedidos');
                }
            })
            .catch(error => console.error('Error:', error));
    }


    function deletePedido(id) {
        console.log('Eliminando pedido con id:', id);
        if (confirm('¿Estás seguro de que deseas eliminar este pedido?')) {
            fetch(`${API_PEDIDOS}/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Registro eliminado correctamente');
                        mostrarPedidos(); // Llama a mostrarPedidos para actualizar la lista
                    } else {
                        console.error('Error al eliminar el registro');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    }

    // Llama a la función para mostrar los pedidos al cargar la página
    document.addEventListener('DOMContentLoaded', mostrarPedidos);

    // Llama a la función para mostrar los pedidos al cargar la página
    mostrarPedidos();

    document.addEventListener('DOMContentLoaded', () => {
        const eliminarButtons = document.querySelectorAll('.btn-danger');
        eliminarButtons.forEach(button => {
            button.addEventListener('click', () => {
                const id = button.getAttribute('data-id');
                deletePedido(id);
            });
        });
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
});