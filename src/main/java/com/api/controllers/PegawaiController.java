package com.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
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

import com.api.exception.pegawai.*;
import com.api.models.entities.Pegawai;
import com.api.services.PegawaiService;
import com.api.util.FileDownloadUtils;
import com.api.util.FileUploadResponse;
import com.api.util.FileUploadUtil;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;
    // @Autowired

    @GetMapping("/pegawais")
    public ResponseEntity<Object> findAllPromo() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                pegawaiService.findAll());
    }

    @GetMapping("/pegawais/{id}")
    public ResponseEntity<Object> getByIdPromo(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                pegawaiService.findByIdPegawai(id));
    }

    @PostMapping(value = "pegawais", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> createPromo(@RequestBody @Validated Pegawai Pegawai) {

        return ResponseHandler.responseEntity("Berhasil menambah data", HttpStatus.CREATED,
                pegawaiService.createPegawai(Pegawai));
    }

    @PutMapping("/pegawais/{id}")
    public ResponseEntity<Object> updatePromo(@PathVariable("id") String id,
            @RequestBody @Validated Pegawai Pegawai) {

        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                pegawaiService.updatePegawai(id, Pegawai));

    }

    @DeleteMapping("/pegawais/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) {

        Pegawai promoDB = pegawaiService.findByIdPegawai(id);
        pegawaiService.deletePegawai(id);

        return ResponseHandler.responseEntity("Berhasil hapus data", HttpStatus.OK,
                promoDB);

    }

    @PutMapping("/pegawais/status/{id}")
    public ResponseEntity<Object> updatePromoStatus(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengubah status", HttpStatus.ACCEPTED,
                pegawaiService.updatePegawaiStatus(id));

    }

    @PostMapping(value = "pegawais/foto/{id}")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("foto") MultipartFile multipartFile,
            @PathVariable("id") String id)
            throws PegawaiExceptionNotFound {
        String foto = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = FileUploadUtil.saveFile(foto, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(foto);
        response.setDownloadUri("/pegawais/foto/" + filecode);
        response.setSize(size);
        pegawaiService.updateFotoPegawai(id, filecode + "-" + foto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/pegawais/{id}/{role}")
    public ResponseEntity<Object> updatePromo(@PathVariable("id") String id, @PathVariable("role") String role) {

        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                pegawaiService.addUserPegawai(id, role));

    }

    @GetMapping("pegawais/foto/{fileCode}")
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

    @GetMapping("/pegawais/email/{email}")
    public ResponseEntity<Object> getPegawaiByEmail(@PathVariable("email") String email) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                pegawaiService.findByEmail(email));
    }

}
