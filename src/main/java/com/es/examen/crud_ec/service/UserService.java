package com.es.examen.crud_ec.service;

import java.util.List;
import java.util.Optional;

import com.es.examen.crud_ec.dto.RequestUserDTO;
import com.es.examen.crud_ec.dto.ResponseUserDTO;

public interface UserService {
    ResponseUserDTO saveUser(RequestUserDTO userDTO);
    Optional<ResponseUserDTO> getById(Long id);
    List<ResponseUserDTO> getAllUsers();
    Optional<ResponseUserDTO> updateUser(RequestUserDTO userDTO);
    Boolean deleteById(Long id);    
}
