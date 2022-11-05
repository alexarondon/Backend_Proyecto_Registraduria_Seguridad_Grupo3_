package com.mintic.seguridad24.Controladores;

import com.mintic.seguridad24.Modelos.Rol;
import com.mintic.seguridad24.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class ControladorRol {
    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public List<Rol> index() {
        return this.miRepositorioRol.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol create(@RequestBody Rol infoRol) {
        return this.miRepositorioRol.save(infoRol);
    }

    @PutMapping("{id}")
    public Rol update(@PathVariable String id, @RequestBody Rol infoRol) {
        Rol rol = this.miRepositorioRol.findById(id).orElse(null);
        if (rol != null) {
            rol.setNombre(infoRol.getNombre());
            rol.setDescripcion(infoRol.getDescripcion());
            return this.miRepositorioRol.save(rol);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Rol rol = this.miRepositorioRol.findById(id).orElse(null);
        if (rol != null) {
            this.miRepositorioRol.delete(rol);
        }
    }

    @GetMapping("{id}")
    public Rol show(@PathVariable String id) {
        Rol rol = this.miRepositorioRol.findById(id).orElse(null);
        return rol;
    }


}
