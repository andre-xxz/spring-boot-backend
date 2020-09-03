package com.aulas.cursomc.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String cpfOuCnpj;
    @Getter
    @Setter //verificar
    private Integer tipo;
    @Getter
    @Setter
    private String logradouro;
    @Getter
    @Setter
    private String numero;
    @Getter
    @Setter
    private String complemento;
    @Getter
    @Setter
    private String bairro;
    @Getter
    @Setter
    private String cep;
    @Getter
    @Setter
    private String telefone1;
    @Getter
    @Setter
    private String telefone2;
    @Getter
    @Setter
    private String telefone3;
    @Getter
    @Setter
    private Integer cidadeId;

    public ClienteNewDTO() {

    }
}
