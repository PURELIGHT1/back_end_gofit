package com.api.implement.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.dto.AuthenticationRequest;
import com.api.dto.AuthenticationResponse;
import com.api.models.TokenType;
import com.api.models.entities.GenerateTabel;
import com.api.models.entities.Member;
import com.api.models.entities.User;
import com.api.models.entities.token.Token;
import com.api.models.repos.GenerateRepo;
import com.api.models.repos.MemberRepo;
import com.api.models.repos.TokenRepo;
import com.api.models.repos.UserRepo;
import com.api.security.JwtService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserImpl {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private GenerateRepo generateRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerUser(User user) {
        boolean userExists = userRepo.findByUserLogin(user.getUserLogin()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Username sudah terdaftar");
        }

        // String encodedPassword =
        // bCryptPasswordEncoder.encode(user.getPasswordLogin());
        // user.setPasswordLogin(encodedPassword);
        // user.setUserRole(UserRole.ADMIN);
        // userRepo.save(user);
        User userDB = userRepo.save(user);
        User userTemp = userRepo.findById(userDB.getId()).get();
        if (userTemp.getId() == 1) {
            GenerateTabel generateTabel = new GenerateTabel(1, 0, 0, 0, 0);
            generateRepo.save(generateTabel);
        }

        return userDB;
    }

    public User getUserById(String id) {
        Member memberDB = memberRepo.findById(id).get();
        memberDB.getEmail();

        return userRepo.findByUserLogin(memberDB.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User dengan username '%s' tidak ditemukan", memberDB.getEmail())));
    }

    // public AuthenticationResponse authenticate(AuthenticationRequest request) {
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(request.getUsername(),
    // request.getPassword()));

    // User user = userRepo.findByUserLogin(request.getUsername()).orElseThrow();

    // String jwtToken = jwtService.generateToken(user);
    // return AuthenticationResponse.builder()
    // .password(request.getPassword())
    // .username(request.getUsername())
    // .token(jwtToken)
    // .build();
    // }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User userDB = userRepo.findByUserLogin(request.getUsername()).orElseThrow();
        // if (userDB != null) {
        String jwtToken = jwtService.generateToken(userDB);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .username(request.getUsername())
                .id(userDB.getId())
                .role(userDB.getUserRole())
                .pegawai(userDB.getPegawai())
                .instruktur(userDB.getInstruktur())
                .member(userDB.getMember())
                .build();

        revokeAllUserToken(userDB);
        saveUserToken(userDB, jwtToken);

        return authenticationResponse;
        // } else {
        // return AuthenticationResponse
        // }

    }

    private void revokeAllUserToken(User user) {
        var validUserTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepo.save(token);
    }

    // public AuthenticationResponse authenticate(User user) {

    // // AuthenticationManager authenticationManager = securityApi.authManager(new
    // // AuthenticationConfiguration(user.getU));
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(user.getUsername(),
    // user.getPassword()));
    // User userDB = userRepo.findByUserLogin(user.getUserLogin())
    // .orElseThrow();

    // // generate TOken
    // String jwtToken = jwtService.generateToken(userDB);

    // AuthenticationResponse response = new AuthenticationResponse();
    // response.setUserLogin(userDB.getUserLogin());
    // response.setUserPassword(userDB.getPassword());
    // response.setToken(jwtToken);
    // return response;
    // }
}
