package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ProfileMemberResponse2;
import com.api.dto.ResponseData;
import com.api.implement.builder.ProfileInsImpl;
import com.api.implement.builder.ProfileMemberImpl;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class ProfileController {

    @Autowired
    private ProfileMemberImpl profileMemberImpl;

    @Autowired
    private ProfileInsImpl profileInsImpl;

    @GetMapping("/profile/member/{id}")
    public ResponseEntity<ResponseData<ProfileMemberResponse2>> findAllProfile(@PathVariable("id") String id) {
        ResponseData<ProfileMemberResponse2> responseData = new ResponseData<>();

        ProfileMemberResponse2 response = profileMemberImpl.getAll2(id);
        responseData.getMessage().add("Berhasil Mengambil profile member");
        responseData.setStatus(true);
        responseData.setData(response);

        return ResponseEntity.ok(responseData);
    }

    // @GetMapping("/histori/member/{id}")
    // public ResponseEntity<ResponseData<ProfileMemberResponse>>
    // findAllHistori(@PathVariable("id") String id) {
    // ResponseData<ProfileMemberResponse> responseData = new ResponseData<>();

    // ProfileMemberResponse response = profileMemberImpl.getAll(id);
    // responseData.getMessage().add("Berhasil Mengambil profile member");
    // responseData.setStatus(true);
    // responseData.setData(response);

    // return ResponseEntity.ok(responseData);
    // }

    @GetMapping("/histori/member/{id}")
    public ResponseEntity<Object> findAllHistori(@PathVariable("id") String id) {

        // ProfileMemberResponse response = profileMemberImpl.getAll(id);
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                profileMemberImpl.getAll(id));
    }

    @GetMapping("/histori/instruktur/{id}")

    public ResponseEntity<Object> findAllHistoriIns(@PathVariable("id") String id) {

        // ProfileMemberResponse response = profileMemberImpl.getAll(id);
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                profileInsImpl.getAll(id));
    }
}
