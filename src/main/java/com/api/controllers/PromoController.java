package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ResponseData;
import com.api.models.entities.Promo;
import com.api.services.PromoService;
import com.api.util.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin
public class PromoController {

        @Autowired
        private PromoService promoService;

        @PostMapping(value = "promos", consumes = { "application/xml", "application/json" })
        public ResponseEntity<ResponseData<Promo>> createPromo(@RequestBody @Valid Promo promo, Errors errors) {
                ResponseData<Promo> responseData = new ResponseData<>();
                if (errors.hasErrors()) {
                        for (ObjectError error : errors.getAllErrors()) {
                                responseData.getMessage().add(error.getDefaultMessage());
                        }
                        responseData.setStatus(false);
                        responseData.setData(null);

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
                responseData.getMessage().add("Berhasil menambah Data");
                responseData.setStatus(true);
                responseData.setData(promoService.createPromo(promo));
                return ResponseEntity.ok(responseData);

                // return ResponseHandler.responseEntity("Berhasil menambah data",
                // HttpStatus.CREATED,
                // promoService.createPromo(promo));
                // return ResponseEntity.ok(promoService.savePromo(promoRequest));
        }

        @PutMapping("/promos/{id}")
        public ResponseEntity<Object> updatePromo(@PathVariable("id") Integer id,
                        @RequestBody @Validated Promo promo) {

                return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                                promoService.updatePromo(id, promo));

        }

        @GetMapping("/promos/{id}")
        public ResponseEntity<Object> getByIdPromo(@PathVariable("id") Integer id) {

                return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                                promoService.findByIdPromo(id));
        }

        @GetMapping("/promos")
        public ResponseEntity<Object> findAllPromo() {

                return ResponseHandler.responseEntity("Berhasil mengambil seluruh", HttpStatus.OK,
                                promoService.findAll());
        }

        @DeleteMapping("/promos/{id}")
        public ResponseEntity<Object> deleteById(@PathVariable("id") Integer id) {

                Promo promoDB = promoService.findByIdPromo(id);
                promoService.deletePromo(id);

                return ResponseHandler.responseEntity("Berhasil hapus data", HttpStatus.OK,
                                promoDB);

        }

        @PutMapping("/promos/status/{id}")
        public ResponseEntity<Object> updatePromoStatus(@PathVariable("id") Integer id) {

                return ResponseHandler.responseEntity("Berhasil mengubah status", HttpStatus.ACCEPTED,
                                promoService.updatePromoStatus(id));

        }

        @GetMapping("/promos/jenis/{jenis}")
        public ResponseEntity<Object> getPromoByJenis(@PathVariable("jenis") String jenis) {

                return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                                promoService.findByjenis(jenis));
        }
}
