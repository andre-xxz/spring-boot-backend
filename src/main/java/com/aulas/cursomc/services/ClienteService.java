package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.dto.ClienteDTO;
import com.aulas.cursomc.repositories.ClienteRepository;
import com.aulas.cursomc.services.exceptions.DataIntegrityException;
import com.aulas.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findClienteById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto cliente nao encontrado, id: " + id + " nome: " + Cliente.class.getName()));
    }

    public Cliente updateCliente(Cliente cliente) {
        Cliente newCliente = findClienteById(cliente.getId());
        updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }

    public void deleteCliente(Integer id) {
        findClienteById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir por que ha entidades relacionadas");
        }
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) { //para adaptar a controller para receber uma DTO ao inves da classe em si
//        return new Cliente(clienteDTO.getId(), clienteDTO.getNome());
        //throw new UnsupportedOperationException();
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }


}
