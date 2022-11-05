package com.mintic.seguridad.Controladores;

import com.mintic.seguridad.Modelos.Permiso;
import com.mintic.seguridad.Repositorios.RepositorioPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.event.WindowFocusListener;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")

public class ControladorPermiso {

    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    @GetMapping("")
    public List<Permiso> index(){
        return this.miRepositorioPermiso.findAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Permiso create(@RequestBody Permiso infoPermiso){
        return this.miRepositorioPermiso.save(infoPermiso);
    }

    @PutMapping("{id}")
    public Permiso update(@PathVariable String id, @RequestBody Permiso infoPermiso){
        Permiso permiso = this.miRepositorioPermiso.findById(id).orElse(null);
        if(permiso != null){
            permiso.setUrl(infoPermiso.getUrl());
            permiso.setMetodo(infoPermiso.getMetodo());
            return this.miRepositorioPermiso.save(permiso);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permiso permiso = this.miRepositorioPermiso.findById(id).orElse(null);
        if(permiso != null){
            this.miRepositorioPermiso.delete(permiso);
        }
    }

    @GetMapping("{id}")
    public Permiso show(@PathVariable String id){
        Permiso permiso = this.miRepositorioPermiso.findById(id).orElse(null);
        return permiso;
    }

}
