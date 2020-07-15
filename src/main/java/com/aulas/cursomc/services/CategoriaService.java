package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.repositories.CategoriaRepository;
import com.aulas.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //SPRING 1.X
//    public Categoria findCategoriaById(Integer id) {
//        Optional<Categoria> obj = categoriaRepository.findById(id);
//        if(obj.equals(null)){
//            throw new ObjectNotFoundException("Objeto nao encontrado ! "+"ID: "+id+ " tipo:"+ Categoria.class.getName());
//        }
//
//        //return obj;
//        //  return obj.orElse(null);
//    }
    //SPRING 2.X
    public Categoria findCategoriaById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado LMAO!  ID:" + id + ", Tipo: "+Categoria.class.getName()));
    }

}
