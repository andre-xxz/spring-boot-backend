package com.aulas.cursomc.controller;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) { //tambem pode utilizar sem especificar o retorno, como ResponseEntity<?>
            Categoria obj = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insertCategoria(@RequestBody Categoria categoria){
        categoria = categoriaService.insertCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCategoria(@RequestBody Categoria categoria, @PathVariable Integer id){
        categoria.setId(id); //apenas para garantir que eh esse o id recebido pelo end-point
        categoria = categoriaService.updateCategoria(categoria);
        return ResponseEntity.noContent().build();
    }

}
