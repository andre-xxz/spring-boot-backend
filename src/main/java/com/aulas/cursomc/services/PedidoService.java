package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.domain.Pedido;
import com.aulas.cursomc.repositories.PedidoRepository;
import com.aulas.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findPedidoById(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado LMAO!  ID:" + id + ", Tipo: "+ Pedido.class.getName()));
    }

}
