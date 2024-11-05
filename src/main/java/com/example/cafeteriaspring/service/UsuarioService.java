package com.example.cafeteriaspring.service;
import com.example.cafeteriaspring.model.Usuario;
import com.example.cafeteriaspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para obtener un usuario por su ID
    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Método para agregar un nuevo usuario
    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para actualizar un usuario existente
    public Usuario updateUsuario(int id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre_usuario(usuarioDetails.getNombre_usuario());
            usuario.setContraseña(usuarioDetails.getContraseña());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Método para eliminar un usuario
    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

}