package com.mintic.seguridad.Repositorios;

import com.mintic.seguridad.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRol extends MongoRepository<Rol, String> {
}
