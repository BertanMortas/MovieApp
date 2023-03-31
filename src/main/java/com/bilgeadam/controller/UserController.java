package com.bilgeadam.controller;

import com.bilgeadam.constant.EndPointList;
import com.bilgeadam.dto.request.UserRegisterRequestDto;
import com.bilgeadam.dto.response.UserLoginResponseDto;
import com.bilgeadam.entity.User;
import com.bilgeadam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.bilgeadam.constant.EndPointList.*;

import javax.persistence.Enumerated;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<User> register(String name,String surname,String email,String password,String repassword){
        return ResponseEntity.ok(userService.register(name, surname, email, password, repassword));
    }

    @PostMapping(REGISTER_DTO)
    public ResponseEntity<UserRegisterRequestDto> registerDto(@RequestBody UserRegisterRequestDto dto){
        return ResponseEntity.ok(userService.registerDto(dto));
    }

    @PostMapping(REGISTER_MAPPER)
    public ResponseEntity<UserRegisterRequestDto> registerMapper(@RequestBody @Valid UserRegisterRequestDto dto){
        return ResponseEntity.ok(userService.registerMapper(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(String email, String password){
        return ResponseEntity.ok(userService.login(email, password));
    }
    @PostMapping(LOGIN_DTO)
    public ResponseEntity<String> loginDto(@RequestBody UserLoginResponseDto dto){
        return ResponseEntity.ok(userService.loginDto(dto));
    }
    @PostMapping(LOGIN_MAPPER)
    public ResponseEntity<UserLoginResponseDto> loginMapper(@RequestBody UserLoginResponseDto dto){
        return ResponseEntity.ok(userService.loginMapper(dto));
    }
    @PostMapping(CUSTOM_LOGIN)
    public ResponseEntity customLogin(@RequestBody UserLoginResponseDto dto){
        return userService.costomLogin(dto);
    }
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<Optional<User>> findById(Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<User> delete(Integer id){
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping(FIND_BY_ORDER_NAME_ASC)
    public ResponseEntity<List<User>> findByOrderByName(){
        return ResponseEntity.ok(userService.findByOrderByName());
    }

    @GetMapping(FIND_BY_NAME_CONTAINING)
    public ResponseEntity<List<User>> findAllByNameContainsIgnoreCase(String value){
        return ResponseEntity.ok(userService.findAllByNameContainsIgnoreCase(value));
    }

    @GetMapping(EXISTS_NAME)
    public ResponseEntity<Boolean> existsByNameIgnoreCase(String value){
        return ResponseEntity.ok(userService.existsByNameIgnoreCase(value));
    }

    @GetMapping(FIND_BY_EMAIL)
    public ResponseEntity<List<User>> findByEmailIgnoreCase(String email){
        return ResponseEntity.ok(userService.findByEmailIgnoreCase(email));
    }
    @GetMapping(FIND_PASSWORD_LENGTH)
    public ResponseEntity<List<User>> findPasswordLength(int lenght){
        return ResponseEntity.ok(userService.findPasswordByLength(lenght));
    }
    @GetMapping(FIND_PASSWORD_LENGTH_NATIVE)
    public ResponseEntity<List<User>> findPasswordLength2(int lenght){
        return ResponseEntity.ok(userService.findPasswordByLengthNative(lenght));
    }
}
