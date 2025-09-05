package com.es.examen.crud_ec.service;

import java.util.Optional;

import com.es.examen.crud_ec.dto.RequestUserDTO;

public interface UserService {
    RequestUserDTO saveUser(RequestUserDTO userDTO);
    Optional<RequestUserDTO> getById(Long id);
    Optional<RequestUserDTO> updateUser(RequestUserDTO userDTO);
    Boolean deleteById(Long id);    
}
