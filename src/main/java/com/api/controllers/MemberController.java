package com.api.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.api.exception.member.*;
import com.api.models.entities.Member;
import com.api.services.MemberService;
import com.api.util.FileDownloadUtils;
import com.api.util.FileUploadResponse;
import com.api.util.FileUploadUtil;
import com.api.util.PDFGeneratorService;
import com.api.util.ResponseHandler;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin
public class MemberController {

    private final PDFGeneratorService pdfGeneratorService;

    public MemberController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @Autowired
    private MemberService MemberService;

    @GetMapping("/members")
    public ResponseEntity<Object> findAllPromo() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                MemberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Object> getByIdPromo(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                MemberService.findByIdMember(id));
    }

    @PostMapping(value = "members", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> createPromo(@RequestBody @Validated Member member) {

        return ResponseHandler.responseEntity("Berhasil menambah data", HttpStatus.CREATED,
                MemberService.createMember(member));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Object> updatePromo(@PathVariable("id") String id,
            @RequestBody @Validated Member member) {

        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                MemberService.updateMember(id, member));

    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) {

        Member promoDB = MemberService.findByIdMember(id);
        MemberService.deleteMember(id);

        return ResponseHandler.responseEntity("Berhasil hapus data", HttpStatus.OK,
                promoDB);

    }

    @PutMapping("/members/status/{id}")
    public ResponseEntity<Object> updatePromoStatus(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengubah status", HttpStatus.ACCEPTED,
                MemberService.updateMemberStatus(id));

    }

    @PostMapping(value = "members/foto/{id}")
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
        MemberService.updateFotoMember(id, filecode + "-" + foto);

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

    // @PutMapping("/members/resetPassword/{id}")
    // public ResponseEntity<Object> resetPasswordMember(@PathVariable("id") String
    // id) {

    // return ResponseHandler.responseEntity("Berhasil mereset password",
    // HttpStatus.ACCEPTED,
    // MemberService.updateMemberStatus(id));

    // }

    @GetMapping("/members/pdf")
    public void generateCardMember(HttpServletResponse response) {
        response.setContentType("application/pdf");
        // DateFormat dateFormat = new SimpleDateFormat("DD-MM-YY");
        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        Date date = new Date();
        String currentDateTime = dateFormat.format(date);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Card_Member_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }

}
