package com.api.controllers.User;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.AuthenticationRequest;
import com.api.dto.AuthenticationResponse;
import com.api.dto.ResponseData;
import com.api.implement.builder.UserImpl;
import com.api.models.UserRole;
import com.api.models.entities.User;
import com.api.models.entities.token.Token;
import com.api.models.repos.TokenRepo;
import com.api.util.ResponseHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    @Autowired
    private UserImpl service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenRepo tokenRepo;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<User>> register(@RequestBody AuthenticationRequest user,
            Errors errors) {
        ResponseData<User> response = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            response.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        User userDB = new User();

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        userDB.setUserLogin(user.getUsername());
        userDB.setPasswordLogin(encodedPassword);
        userDB.setUserRole(UserRole.ADMIN);
        response.setData(service.registerUser(userDB));
        response.setStatus(true);
        response.getMessage().add("Berhasil registrasi");
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request,
            Errors errors) {
        ResponseData<AuthenticationResponse> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        AuthenticationResponse response = service.authenticate(request);

        responseData.getMessage().add("Berhasil login");
        responseData.setStatus(true);
        responseData.setData(response);

        return ResponseEntity.ok(responseData);

    }

    @PostMapping("/refresh_token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @PutMapping("/logoutUrl/{id}")
    public ResponseEntity<Object> login(@PathVariable("id") Long id) {
        List<Token> tokens = tokenRepo.findAllTokensByUser(id);
        for (int i = 0; i < tokens.size(); i++) {
            tokens.get(i).setRevoked(false);
            tokens.get(i).setExpired(false);
            tokenRepo.save(tokens.get(i));
        }
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK, tokens);
    }
}
