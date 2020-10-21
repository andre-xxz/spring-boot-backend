package com.aulas.cursomc.services;

import com.aulas.cursomc.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {

        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //retorna o usuario que esta logado no sistema
        } catch (Exception e) {
            return null;
        }
    }
}
