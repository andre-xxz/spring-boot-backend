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

    public Categoria findCategoriaById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado LMAO!  ID:" + id + ", Tipo: "+Categoria.class.getName()));
    }

    public Categoria insertCategoria(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Categoria categoria){
        findCategoriaById(categoria.getId());

        return categoriaRepository.save(categoria);
    }
}
