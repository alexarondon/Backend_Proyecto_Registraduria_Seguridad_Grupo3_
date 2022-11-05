package com.mintic.seguridad.Controladores;

import com.mintic.seguridad.Modelos.Rol;
import com.mintic.seguridad.Modelos.Usuario;
import com.mintic.seguridad.Repositorios.RepositorioRol;
import com.mintic.seguridad.Repositorios.RepositorioUsuario;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")

public class ControladorUsuario {

    @Autowired
    private RepositorioUsuario miRespositorioUsuario;

    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public List<Usuario> index(){
        return this.miRespositorioUsuario.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario){
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.miRespositorioUsuario.save(infoUsuario);
    }

    @PutMapping("{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario infoUsuario){
        Usuario usuario = this.miRespositorioUsuario.findById(id).orElse(null);
        if (usuario != null){
            usuario.setSeudonimo(infoUsuario.getSeudonimo());
            usuario.setCorreo(infoUsuario.getCorreo());
            usuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return this.miRespositorioUsuario.save(usuario);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Usuario usuario = this.miRespositorioUsuario.findById(id).orElse(null);
        if(usuario != null){
            this.miRespositorioUsuario.delete(usuario);
        }
    }

    @GetMapping("{id}")
    public  Usuario show(@PathVariable String id){
        Usuario usuario = this.miRespositorioUsuario.findById(id).orElse(null);
        return usuario;
    }

    @PutMapping("{id_usuario}/rol/{id_rol}") //asociacion de usuario a un rol
    public Usuario setRol(@PathVariable String id_usuario, @PathVariable String id_rol){
        Usuario usuario = this.miRespositorioUsuario.findById(id_usuario).orElse(null);
        Rol rol = this.miRepositorioRol.findById(id_rol).orElse(null);
        if(usuario != null && rol != null){
            usuario.setRol(rol);
            return this.miRespositorioUsuario.save(usuario);
        }else{
            return null;
        }
    }


    //Inicio Cifrado de la contraseña
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

    //Fin Cifrado de la contraseña


    ///Funcionalidad nueva de busqueda por correo
    @PostMapping("validate")
    public Usuario validate(@RequestBody Usuario infoUsuario, final HttpServletResponse response) throws IOException {
        Usuario usuario = this.miRespositorioUsuario.getUserByEmail(infoUsuario.getCorreo());
        if (usuario != null){
            if(usuario.getContrasena().equals(convertirSHA256(infoUsuario.getContrasena()))){
                usuario.setContrasena("");
                return usuario;
            }else{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
}




