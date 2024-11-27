

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

