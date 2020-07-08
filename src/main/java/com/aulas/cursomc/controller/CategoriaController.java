package com.aulas.cursomc.controller;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findCategoriaById(@PathVariable Integer id) {

        Categoria obj = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok().body(obj);

    }


}
