package com.aulas.cursomc.repositories;

import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    Page<Pedido> findPedidoByCliente(Cliente cliente, Pageable pageRequest);
}
