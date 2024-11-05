package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.exepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")  // Acá es donde realizamos el mapeo
    public String registrar() {
        return "autor_form.html";   // Acá es que retornamos con el método.
    }

    @PostMapping("/registro") // localhost:8080/autor/registrar
    public String registro(@RequestParam String nombre,ModelMap modelo) {
        try {
            autorServicio.crearAutor(nombre);    // llamo a mi servicio para persistir
            modelo.put("exito","El Autor fue cargado correctamente!");
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }
        return "index.html";
    }
    @GetMapping("/lista")  // Acá es donde realizamos el mapeo
    public String listar(ModelMap model) {
        List<Autor> autores = autorServicio.listarAutores();
        model.addAttribute("autores",autores);
        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorServicio.getOne(id));

        return "autor_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {
        try {
            autorServicio.modificarAutor(nombre, id);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
        }

    }

}
