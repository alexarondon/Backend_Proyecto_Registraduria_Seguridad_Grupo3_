package com.mintic.seguridad.Repositorios;

import com.mintic.seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioUsuario extends MongoRepository<Usuario, String> {
    @Query("{'correo': ?0}") //##Forma para hacer consultas
            public Usuario getUserByEmail(String email);

}
