package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.repositories.ClienteRepository;
import com.aulas.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
//    public Categoria findCategoriaById(Integer id) {
//        Optional<Categoria> obj = categoriaRepository.findById(id);
//        return obj.orElseThrow(() -> new ObjectNotFoundException(
//                "Objeto não encontrado LMAO!  ID:" + id + ", Tipo: "+Categoria.class.getName()));
//    }
//

    public Cliente findClienteById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto cliente nao encontrado, id: "+id+" nome: "+Cliente.class.getName()));
    }
}
