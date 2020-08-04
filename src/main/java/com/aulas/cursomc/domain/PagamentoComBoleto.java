package com.aulas.cursomc.domain;

import com.aulas.cursomc.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Date dataVencimento;
    @Getter
    @Setter
    private Date dataPagamento;

    public PagamentoComBoleto(){

    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
