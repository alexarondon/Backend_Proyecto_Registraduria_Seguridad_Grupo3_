package com.mintic.seguridad24.Repositorios;

import com.mintic.seguridad24.Modelos.Permiso;
import com.mintic.seguridad24.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermisosRoles extends MongoRepository<PermisosRoles, String> {
}
