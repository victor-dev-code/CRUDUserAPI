package com.es.examen.crud_ec.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.es.examen.crud_ec.dto.RequestUserDTO;
import com.es.examen.crud_ec.dto.ResponseUserDTO;
import com.es.examen.crud_ec.entity.User;
import com.es.examen.crud_ec.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseUserDTO saveUser(RequestUserDTO userDTO) {
        log.info("Agregando datos del nuevo usuario con nombre: {}", userDTO.getUsername());

        User userEntity = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepository.save(userEntity), ResponseUserDTO.class);
    }

    @Override
    public Optional<ResponseUserDTO> getById(Long id) {
        log.info("Se busca usuario con el id {}", id);
        return userRepository.findById(id).map(
            user -> {
                log.info("Se ha encontrado el usuario {}", user.getUsername());
                return modelMapper.map(user, ResponseUserDTO.class);
            }
        );
    }

    @Override
    public Optional<ResponseUserDTO> updateUser(RequestUserDTO userDTO) {
        log.info("Se busca el usuario con el id {}", userDTO.getId());
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.findById(userDTO.getId()).map(
            userDB -> {
                log.info("Se encuentra el usuario {} ", userDB.getUsername());
                return modelMapper.map(userRepository.save(user), ResponseUserDTO.class);
            }
        );
    }

    @Override
    public Boolean deleteById(Long id) {
        log.info("Se busca el usuario con el id {}", id);
        return userRepository.findById(id).map(
            user -> {
                userRepository.delete(user);
                log.info("Se elimina el usuario solicitado");
                return true;
            }
        ).orElse(false);
    }

    @Override
    public List<ResponseUserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(
            usuario -> {
                return modelMapper.map(usuario, ResponseUserDTO.class);
            }
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<ResponseUserDTO> findByName(String username) {
        log.info("Buscando el usuario {}", username);
        return userRepository.findByUsername(username).map(
            user -> {
                log.info("Se ha encontrado el usuario solicitado {}", username);
                return modelMapper.map(user, ResponseUserDTO.class);
            }
        );
    }

    
}
