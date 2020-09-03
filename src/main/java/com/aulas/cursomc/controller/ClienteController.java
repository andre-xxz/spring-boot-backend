package com.aulas.cursomc.controller;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.dto.CategoriaDTO;
import com.aulas.cursomc.dto.ClienteDTO;
import com.aulas.cursomc.dto.ClienteNewDTO;
import com.aulas.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAllClientes() {
        List<Cliente> listClientes = clienteService.findAllClientes();
        List<ClienteDTO> listDto = listClientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page, //paremetros opcionais
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> listClientes = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDto = listClientes.map(obj -> new ClienteDTO(obj)); //como Page ja vem no padrao Java8, nao precisa de stream/collect
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertCliente(@Valid @RequestBody ClienteNewDTO clienteDto) {
        Cliente cliente = clienteService.fromDTO(clienteDto);
        cliente = clienteService.insertCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCliente(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Integer id) {
        Cliente cliente = clienteService.fromDTO(clienteDto);
        cliente.setId(id);
        cliente = clienteService.updateCliente(cliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) { //tambem pode utilizar sem especificar o retorno, como ResponseEntity<?>
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }


}
