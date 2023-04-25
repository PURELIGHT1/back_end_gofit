package com.api.controllers.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.AuthenticationRequest;
import com.api.dto.AuthenticationResponse;
import com.api.dto.ResponseData;
import com.api.implement.UserImpl;
import com.api.models.entities.Member;
import com.api.models.entities.User;
import com.api.models.repos.UserRepo;
import com.api.services.MemberService;
import com.api.util.ResponseHandler;

@RestController
public class UserController {

    @Autowired
    private UserImpl service;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<User>> register(@RequestBody User user, Errors errors) {
        ResponseData<User> response = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setPayload(service.registerUser(user));
        response.setStatus(true);
        response.getMessage().add("Berhasil registrasi");
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    // @PostMapping("/login")
    // public Respons<> login(@RequestBody userData){

    // }
    // public ResponseEntity<Object> login(@RequestBody UserData userData){
    // return ResponseEntity.ok(service.authenticate(userData));
    // }
    // public ResponseEntity<ResponseData<User>> login(@RequestBody UserData user,
    // Errors errors) {
    // ResponseData<User> response = new ResponseData<>();
    // if (errors.hasErrors()) {
    // for (ObjectError error : errors.getAllErrors()) {
    // response.getMessage().add(error.getDefaultMessage());
    // }
    // response.setStatus(false);
    // response.setPayload(null);

    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    // }

    // response.setPayload(service.registerUser(user));
    // response.setStatus(true);
    // response.getMessage().add("Berhasil registrasi");
    // return ResponseEntity.ok(response);

    // }

    @PutMapping("/api/reset-password/{id}")
    public ResponseEntity<Object> resetPassword(@PathVariable("id") String id) {
        Member member = memberService.findByIdMember(id);
        User user = service.getUserById(id);

        DateFormat dateFormat = new SimpleDateFormat("d-MM-Y");
        String pass = dateFormat.format(member.getTglLahir());

        String encodedPassword = bCryptPasswordEncoder.encode(pass);
        user.setPasswordLogin(encodedPassword);
        userRepo.save(user);

        return ResponseHandler.responseEntity("Berhasil mengubah status", HttpStatus.ACCEPTED,
                userRepo.save(user));

    }
}
