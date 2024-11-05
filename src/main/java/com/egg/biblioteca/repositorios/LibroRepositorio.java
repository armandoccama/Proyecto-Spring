package com.egg.biblioteca.repositorios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    // Método para buscar un libro por título
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    Libro buscarPorTitulo(@Param("titulo") String titulo);

    // Método para buscar libros por autor
    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> buscarPorAutor(@Param("autor") Autor autor);

}
