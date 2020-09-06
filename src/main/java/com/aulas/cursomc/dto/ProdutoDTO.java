package com.aulas.cursomc.dto;

import com.aulas.cursomc.domain.Produto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ProdutoDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private Double preco;

    public ProdutoDTO(){

    }

    public ProdutoDTO(Produto obj){
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }

}
