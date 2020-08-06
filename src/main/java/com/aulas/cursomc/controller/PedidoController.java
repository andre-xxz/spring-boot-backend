package com.aulas.cursomc.controller;

import com.aulas.cursomc.domain.Pedido;
import com.aulas.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    //TODO: VER POR QUE O ENDPOINT /pedidos tras 404
    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> findPedidoById(@PathVariable Integer id){  //tambem pode utilizar sem especificar o retorno, como ResponseEntity<?>
        Pedido pedido = pedidoService.findPedidoById(id);
        return ResponseEntity.ok().body(pedido);
    }
}
