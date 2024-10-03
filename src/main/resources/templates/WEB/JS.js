const API_PRODUCTOS = 'http://localhost:8080/api/products';
const productId = new URLSearchParams(window.location.search).get('id'); // Capturamos el ID del producto desde la URL

document.addEventListener('DOMContentLoaded', () => {
    const productForm = document.getElementById('productForm');

    // Si hay un ID de producto en la URL, cargamos los detalles del producto
    if (productId) {
        cargarProducto(productId);
    }



    // Función para cargar los detalles de un producto
    function cargarProducto(id) {
        fetch(`${API_PRODUCTOS}/${id}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('nombreProducto').value = data.nombre_producto;
                document.getElementById('descripcionProducto').value = data.descripcion;
                document.getElementById('precioProducto').value = data.precio_unitario;
                document.getElementById('categoriaProducto').value = data.categoria;
                document.getElementById('stockActual').value = data.stock_actual;
                document.getElementById('unidadMedida').value = data.unidad_medida;
                document.getElementById('estadoProducto').value = data.estado_producto;
            })
            .catch(error => console.error('Error al cargar el producto:', error));
    }


    //añadir producto
    function addProduct() {
        const producto = {
            nombre_producto: document.getElementById('nombreProducto').value,
            descripcion: document.getElementById('descripcionProducto').value,
            precio_unitario: parseFloat(document.getElementById('precioProducto').value),
            categoria: document.getElementById('categoriaProducto').value,
            stock_actual: parseInt(document.getElementById('stockActual').value),
            unidad_medida: document.getElementById('unidadMedida').value,
            estado_producto: document.getElementById('estadoProducto').value,
            fecha_registro: new Date().toISOString().split('T')[0] // Envío de fecha en formato YYYY-MM-DD
        };

        fetch(API_PRODUCTOS, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(producto),
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                return response.json();
            })
            .then(() => {
                alert('Producto agregado con éxito');
                window.location.href = 'producto-list.html';
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al agregar el producto: ' + error.message);
            });
    }

// Manejar envío del formulario de productos
    if (productForm) {
        productForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const nombreProducto = document.getElementById('nombreProducto').value;
            const descripcion = document.getElementById('descripcionProducto').value;
            const precioProducto = parseFloat(document.getElementById('precioProducto').value);
            const categoria = document.getElementById('categoriaProducto').value;
            const stockActual = parseInt(document.getElementById('stockActual').value);
            const unidadMedida = document.getElementById('unidadMedida').value;
            const estadoProducto = document.getElementById('estadoProducto').value;

            const productData = {
                nombre_producto: nombreProducto,
                descripcion: descripcion,
                precio_unitario: precioProducto,
                categoria: categoria,
                stock_actual: stockActual,
                unidad_medida: unidadMedida,
                estado_producto: estadoProducto
            };

            if (productId) {
                // Si existe un ID de producto, actualizamos el producto
                updateProducto(productId, productData);
            } else {
                // De lo contrario, agregamos un nuevo producto
                addProduct();
            }
        });
    }



    // Mostrar productos en la página de lista de productos
    fetch(API_PRODUCTOS)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('table tbody');
            if (tableBody) {
                tableBody.innerHTML = ''; // Limpiamos la tabla antes de agregar los nuevos datos
                data.forEach(product => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                    <td>${product.id_producto}</td>
                    <td>${product.nombre_producto}</td>
                    <td>${product.descripcion}</td>
                    <td>${product.precio_unitario}</td>
                    <td>${product.categoria}</td>
                    <td>${product.stock_actual}</td>
                    <td>${product.unidad_medida}</td>
                    <td>${product.estado_producto}</td>
                    <td>${product.fecha_registro}</td>
                    <td>
                        <a href="producto-form.html?id=${product.id_producto}" class="btn btn-warning btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Editar Producto">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <a href="producto-list.html?id=${product.id_producto}" class="btn btn-danger btn-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Eliminar Producto" onclick="return confirm('¿Estás seguro de que deseas eliminar este producto?');">
                            <i class="bi bi-trash"></i>
                        </a>
                    </td>
                `;
                    tableBody.appendChild(row);
                });
            } else {
                console.error('No se encontró la tabla de productos');
            }
        })
        .catch(error => console.error('Error:', error));

    // Función para editar un producto
    window.editProduct = function(id) {
        fetch(`/api/products/${id}`, {
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                // Rellenar los campos del formulario con los datos del producto
                document.getElementById('nombreProducto').value = data.nombre_producto;
                document.getElementById('descripcionProducto').value = data.descripcion;
                document.getElementById('precioProducto').value = data.precio_unitario;
                document.getElementById('categoriaProducto').value = data.categoria;
                document.getElementById('stockActual').value = data.stock_actual;
                document.getElementById('unidadMedida').value = data.unidad_medida;
                document.getElementById('estadoProducto').value = data.estado_producto;
            })
            .catch(error => console.error('Error:', error));
    }

    // Función para actualizar un producto
    function updateProducto(id, producto) {
        fetch(`${API_PRODUCTOS}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(producto),
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                return response.json();
            })
            .then(() => {
                alert('Producto actualizado con éxito');
                window.location.href = 'producto-list.html';  // Redirige a la lista de productos
            })
            .catch(error => {
                console.error('Error al actualizar el producto:', error);
                alert('Error al actualizar el producto: ' + error.message);
            });
    }

    // Función para eliminar un producto
    window.deleteProduct = function(id, button) {
        if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
            fetch(`/api/products/${id}`, {
                method: 'DELETE',
            })
                .then(response => {
                    if (response.ok) {
                        button.closest('tr').remove();
                    } else {
                        throw new Error('No se pudo eliminar el producto');
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    }
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
