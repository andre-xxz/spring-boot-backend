package com.aulas.cursomc.controller;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.dto.CategoriaDTO;
import com.aulas.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAllCategorias() { //busca categorias por DTO, trazendo apenas o id e nome
        List<Categoria> listCategorias = categoriaService.findAllCategorias();
        List<CategoriaDTO> listDto = listCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value= "page", defaultValue = "0") Integer page, //paremetros opcionais
            @RequestParam(value= "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value= "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value= "direction", defaultValue = "ASC") String direction) {
        Page<Categoria> listCategorias = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDto = listCategorias.map(obj -> new CategoriaDTO(obj)); //como Page ja vem no padrao Java8, nao precisa de stream/collect
        return ResponseEntity.ok().body(listDto);
    }


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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) { //tambem pode utilizar sem especificar o retorno, como ResponseEntity<?>
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }


}
