package com.aulas.cursomc.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @NotEmpty
    @Email(message = "Email invalido")
    private String email;

    public EmailDTO() {

    }

}
