package com.aulas.cursomc.services;

import com.aulas.cursomc.domain.*;
import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.domain.Cidade;
import com.aulas.cursomc.domain.enums.TipoCliente;
import com.aulas.cursomc.dto.ClienteDTO;
import com.aulas.cursomc.dto.ClienteNewDTO;
import com.aulas.cursomc.repositories.CidadeRepository;
import com.aulas.cursomc.repositories.ClienteRepository;
import com.aulas.cursomc.repositories.EnderecoRepository;
import com.aulas.cursomc.services.exceptions.DataIntegrityException;
import com.aulas.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//TODO: parei no 40, 16:31

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

//    @Autowired
//    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente findClienteById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto cliente nao encontrado, id: " + id + " nome: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insertCliente(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
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

    public Cliente fromDTO(ClienteDTO clienteDto) { //para adaptar a controller para receber uma DTO ao inves da classe em si
        return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO clienteDto) {
        Cliente cli = new Cliente(null, clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getCpfOuCnpj(), TipoCliente.toEnum((clienteDto.getTipo())));
        Cidade cid = new Cidade(clienteDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, clienteDto.getLogradouro(), clienteDto.getNumero(), clienteDto.getComplemento(), clienteDto.getBairro(), clienteDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(clienteDto.getTelefone1());
        if (clienteDto.getTelefone2() != null) {
            cli.getTelefones().add(clienteDto.getTelefone2());
        }
        if (clienteDto.getTelefone3() != null) {
            cli.getTelefones().add(clienteDto.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }


}
