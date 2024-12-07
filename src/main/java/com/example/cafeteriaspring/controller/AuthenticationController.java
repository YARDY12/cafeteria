package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Usuario;
import com.example.cafeteriaspring.service.UsuarioService;
import com.example.cafeteriaspring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService userService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(
                    userDetails.getUsername(),
                    userDetails.getAuthorities().iterator().next().getAuthority()
            );

            Usuario user = userService.findByUsername(username);
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", new HashMap<String, Object>() {{
                put("id", user.getId());
                put("username", user.getUsername());
                put("nombre", user.getNombre());
                put("apellido", user.getApellido());
                put("email", user.getEmail());
                put("role", user.getRoles().iterator().next().getName());
            }});

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error de autenticación: " + e.getMessage());
        }
    }
}