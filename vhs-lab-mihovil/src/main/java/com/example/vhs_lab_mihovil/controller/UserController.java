package com.example.vhs_lab_mihovil.controller;

import com.example.vhs_lab_mihovil.dto.UserDto;
import com.example.vhs_lab_mihovil.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("insert")
    public ResponseEntity insertUser(@Valid @RequestBody UserDto userDto){
       Integer insertedId = userService.insertUser(userDto);
       return new ResponseEntity(insertedId, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        Integer updatedId = userService.updateUser(userDto);
        return new ResponseEntity(updatedId, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
