package com.mintic.seguridad.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Usuario {
    @Id
    private String _id;
    private String seudonimo;
    private String correo;
    private String contrasena;

    @DBRef
    private Rol rol; //Se agregar la llave principal a usuario para hacer la relacion

    public Usuario(String seudonimo, String correo, String contrasena) { //Constructor
        this.seudonimo = seudonimo;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Get y Set

    public String get_id() {return _id;    }

    public Rol getRol() {   //Se agrega el get del rol, para la relacion
        return rol;
    }

    public void setRol(Rol rol) { ////Se agrega el set   del rol, para la relacion
        this.rol = rol;
    }

    public String getSeudonimo() {
        return seudonimo;
    }

    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
