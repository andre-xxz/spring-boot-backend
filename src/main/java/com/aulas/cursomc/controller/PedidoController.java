package com.aulas.cursomc.controller;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.domain.Pedido;
import com.aulas.cursomc.dto.CategoriaDTO;
import com.aulas.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> findPedidoById(@PathVariable Integer id){  //tambem pode utilizar sem especificar o retorno, como ResponseEntity<?>
        Pedido pedido = pedidoService.findPedidoById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertPedido(@Valid @RequestBody Pedido pedido) {
        pedido = pedidoService.insertPedido(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
