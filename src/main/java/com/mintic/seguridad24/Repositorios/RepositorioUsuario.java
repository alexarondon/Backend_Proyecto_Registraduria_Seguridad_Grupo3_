package com.mintic.seguridad24.Repositorios;

import com.mintic.seguridad24.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario extends MongoRepository<Usuario, String> {
}
