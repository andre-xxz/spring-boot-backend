package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.Categoria;
import com.aulas.cursomc.dto.CategoriaDTO;
import com.aulas.cursomc.repositories.CategoriaRepository;
import com.aulas.cursomc.services.exceptions.DataIntegrityException;
import com.aulas.cursomc.services.exceptions.ObjectNotFoundException;
//import com.sun.javafx.scene.traversal.Direction;
import com.sun.javafx.scene.traversal.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deleteCategoria(Integer id) {
        findCategoriaById(id);
        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possua produtos");
        }
    }

    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }
    public Categoria fromDTO(CategoriaDTO categoriaDTO){ //para adaptar a controller para receber uma DTO ao inves da classe em si
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

}
