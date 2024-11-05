package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.exepciones.MiException;
import com.egg.biblioteca.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialServicio editorialServicio;

    // Método GET para mostrar el formulario de registro de editorial
    @GetMapping("/registrar")
    public String registrar() {
        return "editorial_form.html";
    }

    // Método POST para registrar la nueva editorial
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,ModelMap modelo) {
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito","El Editorial fue cargado correctamente!");
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }
        return "index.html";
    }
    @GetMapping("/lista")  // Acá es donde realizamos el mapeo
    public String listar(ModelMap model) {
        List<Editorial> editoriales = editorialServicio.listarEditorial();
        model.addAttribute("editoriales",editoriales);
        return "editorial_list.html";
    }
}
