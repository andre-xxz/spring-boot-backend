package com.aulas.cursomc.services.validation;

import com.aulas.cursomc.controller.exceptions.FieldMessage;
import com.aulas.cursomc.domain.Cliente;
import com.aulas.cursomc.domain.enums.TipoCliente;
import com.aulas.cursomc.dto.ClienteNewDTO;
import com.aulas.cursomc.repositories.ClienteRepository;
import com.aulas.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF Invalido"));
        }

        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Invalido"));
        }

        if(objDto.getTipo() == null){
            list.add(new FieldMessage("tipo", "Tipo nao pode ser nulo"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessage("Email", "Email já existente."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}