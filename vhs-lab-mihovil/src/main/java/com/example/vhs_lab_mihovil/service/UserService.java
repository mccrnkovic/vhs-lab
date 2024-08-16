package com.example.vhs_lab_mihovil.service;

import com.example.vhs_lab_mihovil.dto.UserDto;
import com.example.vhs_lab_mihovil.mapper.UserMapper;
import com.example.vhs_lab_mihovil.model.User;
import com.example.vhs_lab_mihovil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(UserMapper.MAPPER::toDto)
                .collect(Collectors.toList());
        return userDtos;
    }
}
