package com.egg.biblioteca.repositorios;

import com.egg.biblioteca.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

}
