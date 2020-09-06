package com.aulas.cursomc.controller;

import com.aulas.cursomc.controller.utils.URL;
import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.domain.Produto;
import com.aulas.cursomc.dto.CategoriaDTO;
import com.aulas.cursomc.dto.ProdutoDTO;
import com.aulas.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> findProdutoById(@PathVariable Integer id){  //tambem pode utilizar sem especificar o retorno, como ResponseEntity<?>
        Produto produto = produtoService.findProdutoById(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj)); //como Page ja vem no padrao Java8, nao precisa de stream/collect
        return ResponseEntity.ok().body(listDto);
    }

}
