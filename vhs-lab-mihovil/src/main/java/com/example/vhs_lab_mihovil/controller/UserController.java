package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.UserDto;
import com.example.vhs_lab_mihovil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAll")
    public ResponseEntity getAllUsers() {
       List<UserDto> result = userService.getAllUsers();
       return new ResponseEntity(result, HttpStatus.OK);
    }
}
