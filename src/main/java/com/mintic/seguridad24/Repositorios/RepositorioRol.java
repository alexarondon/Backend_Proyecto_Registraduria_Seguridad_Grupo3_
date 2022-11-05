package com.mintic.seguridad24.Repositorios;

import com.mintic.seguridad24.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRol extends MongoRepository<Rol, String> {
}
