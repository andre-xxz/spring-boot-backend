package com.aulas.cursomc.dto;

import com.aulas.cursomc.services.validation.ClienteInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteInsert //anotacao customizada, para validar cpf ou cnpj
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email invalido")
    private String email;
    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOuCnpj;
    @Getter
    @Setter
    private Integer tipo;
    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;
    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;
    @Getter
    @Setter
    private String complemento;
    @Getter
    @Setter
    private String bairro;
    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;
    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento obrigatório")
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
