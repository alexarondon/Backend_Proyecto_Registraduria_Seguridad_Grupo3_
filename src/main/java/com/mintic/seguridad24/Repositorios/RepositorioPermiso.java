package com.mintic.seguridad24.Repositorios;

import com.mintic.seguridad24.Modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermiso extends MongoRepository<Permiso, String> {
}
