package com.api.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.UbahPasswordRequest;
import com.api.exception.member.*;
import com.api.implement.services.MemberService;
import com.api.models.entities.Member;
import com.api.models.repos.MemberRepo;
import com.api.util.FileDownloadUtils;
import com.api.util.FileUploadResponse;
import com.api.util.FileUploadUtil;
import com.api.util.PDFGeneratorService;
import com.api.util.ResponseHandler;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class MemberController {

    private final PDFGeneratorService pdfGeneratorService;

    public MemberController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepo repo;

    @GetMapping("/members")
    public ResponseEntity<Object> findAllMember() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                memberService.findAll());
    }

    @GetMapping("/members/aktif")
    public ResponseEntity<Object> findAllMemberAktiv() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                memberService.findAllAktif());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Object> getByIdMember(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                memberService.findByIdMember(id));
    }

    @GetMapping("/members/find/{nama}")
    public ResponseEntity<Object> getByNamaMember(@PathVariable("nama") String nama) {
        List<Member> memberDB = repo.findByNama(nama);
        if (memberDB.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseHandler.responseEntity("Berhasil mengambil data",
                HttpStatus.OK,
                memberDB);
    }

    // @GetMapping("/members/aktivasi")
    // public ResponseEntity<Object> getByNamaMember() {
    // return ResponseHandler.responseEntity("Berhasil mengambil data",
    // HttpStatus.OK,
    // userRepo.findByAktivasi());
    // }

    @PostMapping(value = "members", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> createMember(@RequestBody @Validated Member member) {
        Member cekMember = repo.findEmailMember(member.getEmail());
        if (cekMember == null) {
            return ResponseHandler.responseEntity("Berhasil menambah data", HttpStatus.CREATED,
                    memberService.createMember(member));
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Object> updateMember(@PathVariable("id") String id,
            @RequestBody @Validated Member member) {

        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                memberService.updateMember(id, member));

    }

    @PutMapping("/members/edit-password/{id}")
    public ResponseEntity<Object> updatePasswordInstruktur(@PathVariable("id") String id,
            @RequestBody @Validated UbahPasswordRequest request) {

        return ResponseHandler.responseEntity("Berhasil ubah password",
                HttpStatus.CREATED, memberService.ubahPasswordMember(id, request));

    }

    // @PutMapping("/members/status/{id}")
    // public ResponseEntity<Object> updateMemberStatus(@PathVariable("id") String
    // id) {

    // return ResponseHandler.responseEntity("Berhasil mengubah data",
    // HttpStatus.OK,
    // memberService.updateMemberStatus(id));

    // }

    // @DeleteMapping("/members/{id}")
    // public ResponseEntity<Object> deleteMemberStatus(@PathVariable("id") String
    // id) {

    // return ResponseHandler.responseEntity("Berhasil hapus data",
    // HttpStatus.ACCEPTED,
    // memberService.updateMemberStatus(id));

    // }
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable("id") String id) {

        Member memberDB = memberService.findByIdMember(id);
        memberService.deleteMember(id);
        return ResponseHandler.responseEntity("Berhasil hapus data",
                HttpStatus.ACCEPTED,
                memberDB);

    }

    @PostMapping("members/foto/{id}")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("foto") MultipartFile multipartFile,
            @PathVariable("id") String id)
            throws MemberExceptionNotFound {
        String foto = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = FileUploadUtil.saveFile(foto, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(foto);
        response.setDownloadUri("/Members/foto/" + filecode);
        response.setSize(size);
        memberService.updateFotoMember(id, filecode + "-" + foto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("members/foto/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {

        FileDownloadUtils downloadUtil = new FileDownloadUtils();

        Resource resource = null;

        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        // String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        // MediaType.parseMediaType(contentType);
        return ResponseEntity.ok()
                // .contentType(MediaType.parseMediaType(null))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    @PutMapping("/members/resetPassword/{id}")
    public ResponseEntity<Object> resetPasswordMember(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mereset password",
                HttpStatus.ACCEPTED,
                memberService.updatePasswordMember(id));

    }

    // @PutMapping("/members/reset-password/{id}")
    // public ResponseEntity<Object> resetPassword(@PathVariable("id") String id) {
    // if (repo.findById(id).isEmpty()) {
    // throw new MemberExceptionBadRequest("Data tidak ditemukan");
    // }

    // Member member = repo.findById(id).get();
    // User user = userRepo.findUserByMember(member);

    // DateFormat dateFormat = new SimpleDateFormat("d-MM-Y");
    // String pass = dateFormat.format(member.getTglLahir());

    // String encodedPassword = bCryptPasswordEncoder.encode(pass);

    // user.setPasswordLogin(encodedPassword);
    // User userDB = userRepo.save(user);

    // return ResponseHandler.responseEntity("Berhasil mengubah password",
    // HttpStatus.ACCEPTED,
    // userDB);

    // }

    @GetMapping("/members/pdf")
    public void generateCardMember(HttpServletResponse response) {
        response.setContentType("application/pdf");
        // DateFormat dateFormat = new SimpleDateFormat("DD-MM-YY");
        DateFormat dateFormat = new SimpleDateFormat("y-MM-dd");
        Date date = new Date();
        String currentDateTime = dateFormat.format(date);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Card_Member_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }

}
