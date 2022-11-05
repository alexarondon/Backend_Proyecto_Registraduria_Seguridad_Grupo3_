package com.mintic.seguridad.Repositorios;

import com.mintic.seguridad.Modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermiso extends MongoRepository<Permiso,String > {

}
