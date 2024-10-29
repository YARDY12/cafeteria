package com.example.cafeteriaspring.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleado;
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 caracteres")
    private String nombre;
    @NotBlank(message = "El apellido es requerido")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;
    @NotBlank(message = "El género es requerido")
    private String genero;
    @NotBlank(message = "El correo es requerido")
    @Email(message = "El correo no es válido")
    private String email;
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "El teléfono no es válido") // Ejemplo de validación de teléfono
    private String telefono;
    @NotBlank(message = "El puesto es requerido")
    private String puesto;
    @NotNull(message = "El salario es requerido")
    @Column(nullable = false)
    private double salario;


    public Empleado() {
    }

    public Empleado(int id_empleado, String nombre, String apellido, String genero, String email, String telefono, String puesto, double salario) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.salario = salario;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }


}
