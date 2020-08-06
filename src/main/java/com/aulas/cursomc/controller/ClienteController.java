package com.aulas.cursomc.controller;

import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) { //tambem pode utilizar sem especificar o retorno, como ResponseEntity<?>
        Cliente obj = clienteService.findClienteById(id);
        return ResponseEntity.ok().body(obj);
    }

}
