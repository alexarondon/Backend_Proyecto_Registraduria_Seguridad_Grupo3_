package com.mintic.seguridad24.Controladores;

import com.mintic.seguridad24.Modelos.Rol;
import com.mintic.seguridad24.Modelos.Usuario;
import com.mintic.seguridad24.Repositorios.RepositorioRol;
import com.mintic.seguridad24.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;

    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public List<Usuario> index() {
        return this.miRepositorioUsuario.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario) {
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.miRepositorioUsuario.save(infoUsuario);
    }

    @PutMapping("{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario infoUsuario) {
        Usuario usuario = this.miRepositorioUsuario.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setSeudonimo(infoUsuario.getSeudonimo());
            usuario.setCorreo(infoUsuario.getCorreo());
            usuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return this.miRepositorioUsuario.save(usuario);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Usuario usuario = this.miRepositorioUsuario.findById(id).orElse(null);
        if (usuario != null) {
            this.miRepositorioUsuario.delete(usuario);
        }
    }

    @GetMapping("{id}")
    public Usuario show(@PathVariable String id) {
        Usuario usuario = this.miRepositorioUsuario.findById(id).orElse(null);
        return usuario;
    }

    @PutMapping("{id_usuario}/rol/{id_rol}")
    public Usuario setRol(@PathVariable String id_usuario, @PathVariable String id_rol) {
        Usuario usuario = this.miRepositorioUsuario.findById(id_usuario).orElse(null);
        Rol rol = this.miRepositorioRol.findById(id_rol).orElse(null);

        if (usuario != null && rol != null) {
            usuario.setRol(rol);
            return this.miRepositorioUsuario.save(usuario);
        } else {
            return null;
        }

    }


    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
