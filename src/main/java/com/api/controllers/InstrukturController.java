package com.api.controllers;

import java.io.IOException;

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

import com.api.exception.instruktur.InstrukturExceptionNotFound;
import com.api.models.entities.Instruktur;
import com.api.services.InstrukturService;
import com.api.util.FileDownloadUtils;
import com.api.util.FileUploadResponse;
import com.api.util.FileUploadUtil;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin
public class InstrukturController {

    @Autowired
    private InstrukturService instrukturService;

    @GetMapping("/instrukturs")
    public ResponseEntity<Object> findAllPromo() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                instrukturService.findAll());
    }

    @GetMapping("/instrukturs/{id}")
    public ResponseEntity<Object> getByIdPromo(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                instrukturService.findByIdInstruktur(id));
    }

    @PostMapping(value = "instrukturs", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> createPromo(@RequestBody @Validated Instruktur instruktur) {

        return ResponseHandler.responseEntity("Berhasil menambah data", HttpStatus.CREATED,
                instrukturService.createInstruktur(instruktur));
    }

    @PutMapping("/instrukturs/{id}")
    public ResponseEntity<Object> updatePromo(@PathVariable("id") String id,
            @RequestBody @Validated Instruktur instruktur) {

        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                instrukturService.updateInstruktur(id, instruktur));

    }

    @DeleteMapping("/instrukturs/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) {

        Instruktur promoDB = instrukturService.findByIdInstruktur(id);
        instrukturService.deleteInstruktur(id);

        return ResponseHandler.responseEntity("Berhasil hapus data", HttpStatus.OK,
                promoDB);

    }

    @PutMapping("/instrukturs/status/{id}")
    public ResponseEntity<Object> updatePromoStatus(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengubah status", HttpStatus.ACCEPTED,
                instrukturService.updateInstrukturStatus(id));

    }

    @PostMapping(value = "instrukturs/foto/{id}")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("foto") MultipartFile multipartFile,
            @PathVariable("id") String id)
            throws InstrukturExceptionNotFound {
        String foto = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = FileUploadUtil.saveFile(foto, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(foto);
        response.setDownloadUri("/instrukturs/foto/" + filecode);
        response.setSize(size);
        instrukturService.updateFotoInstruktur(id, filecode + "-" + foto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("instrukturs/foto/{fileCode}")
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

        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    @GetMapping("/instrukturs/email/{email}")
    public ResponseEntity<Object> getInstrukturByEmail(@PathVariable("email") String email) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                instrukturService.findByEmail(email));
    }

    @GetMapping("/instrukturs/inisial/{inisial}")
    public ResponseEntity<Object> getInstrukturByInisial(@PathVariable("inisial") String inisial) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                instrukturService.findByInisial(inisial));
    }

}
