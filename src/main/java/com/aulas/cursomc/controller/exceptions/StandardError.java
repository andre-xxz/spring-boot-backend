package com.aulas.cursomc.controller.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private Long timeStamp;

    public StandardError(Integer status, String msg, Long timeStamp) {
        super();
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}
