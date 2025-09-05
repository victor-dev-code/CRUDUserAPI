package com.es.examen.crud_ec.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.es.examen.crud_ec.dto.RequestUserDTO;
import com.es.examen.crud_ec.entity.User;
import com.es.examen.crud_ec.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public RequestUserDTO saveUser(RequestUserDTO userDTO) {
        User userEntity = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepository.save(userEntity), RequestUserDTO.class);
    }

    @Override
    public Optional<RequestUserDTO> getById(Long id) {
        return userRepository.findById(id).map(
            user -> {
                return modelMapper.map(user, RequestUserDTO.class);
            }
        );
    }

    @Override
    public Optional<RequestUserDTO> updateUser(RequestUserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.findById(userDTO.getId()).map(
            userDB -> {
                return modelMapper.map(userRepository.save(user), RequestUserDTO.class);
            }
        );
    }

    @Override
    public Boolean deleteById(Long id) {
        return userRepository.findById(id).map(
            user -> {
                userRepository.delete(user);
                return true;
            }
        ).orElse(false);
    }

    
}
