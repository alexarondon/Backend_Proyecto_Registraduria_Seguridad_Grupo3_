package com.mintic.seguridad.Repositorios;


import com.mintic.seguridad.Modelos.Permiso;
import com.mintic.seguridad.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermisosRoles extends MongoRepository<PermisosRoles, String> {
}
