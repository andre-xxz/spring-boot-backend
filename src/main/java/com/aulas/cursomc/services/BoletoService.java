package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7); //apenas seta a data do vencimento como 7 dias depois do pedido
        pagto.setDataVencimento(cal.getTime());
    }
}
