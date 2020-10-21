package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.*;
import com.aulas.cursomc.domain.enums.EstadoPagamento;
import com.aulas.cursomc.repositories.*;
import com.aulas.cursomc.security.UserSS;
import com.aulas.cursomc.services.exceptions.AuthorizationException;
import com.aulas.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido findPedidoById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado LMAO!  ID:" + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insertPedido(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.findClienteById(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findProdutoById(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        //emailService.sendOrderConfirmationEmail(pedido); //manda o email toString
        emailService.sendOrderConfirmationHtmlEmail(pedido); //manda email html thymeleaf
        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        UserSS user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Acesso negasdo.");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Cliente cliente = clienteService.findClienteById(user.getId());

        return pedidoRepository.findPedidoByCliente(cliente, pageRequest);
    }


}
