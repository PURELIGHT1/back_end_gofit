package com.api.implement;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.exception.promo.PromoExceptionBadRequest;
import com.api.exception.promo.PromoExceptionNotFound;
import com.api.implement.services.PromoService;
import com.api.models.entities.Promo;
import com.api.models.repos.PromoRepo;

@Service
public class PromoImpl implements PromoService {

    @Override
    public Promo updatePromoStatus(int id) {
        if (promoRepo.findById(id).isEmpty()) {
            throw new PromoExceptionNotFound("Data tidak ditemukan");
        }
        Promo promoDB = promoRepo.findById(id).get();
        // promoDB.setStatus(true);

        return promoRepo.save(promoDB);
    }

    @Autowired
    PromoRepo promoRepo;

    @Override
    public Promo createPromo(Promo promo) {
        Promo promoDB = new Promo();
        if (Objects.nonNull(promo.getNama()) &&
                !"".equalsIgnoreCase(promo.getNama())) {
            promoDB.setNama(promo.getNama());
        } else {
            throw new PromoExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(promo.getJenis()) &&
                !"".equalsIgnoreCase(promo.getJenis())) {
            promoDB.setJenis(promo.getJenis());
        } else {
            throw new PromoExceptionBadRequest("Jenis tidak boleh kosong");
        }
        // promoDB.setStatus(true);
        promoDB.setAkhir(promo.getAkhir());
        promoDB.setMulai(promo.getMulai());

        if (Objects.nonNull(promo.getDeskripsi()) &&
                !"".equalsIgnoreCase(promo.getDeskripsi())) {
            promoDB.setDeskripsi(promo.getDeskripsi());
        } else {
            throw new PromoExceptionBadRequest("Deskripsi tidak boleh kosong");
        }
        return promoRepo.save(promoDB);
    }

    @Override
    public List<Promo> findByjenis(String jenis) {
        if (promoRepo.findByjenis(jenis).isEmpty()) {
            throw new PromoExceptionNotFound("Data tidak ditemukan");
        }
        return promoRepo.findByjenis(jenis);
    }

    @Override
    public void deletePromo(int id) {

        promoRepo.deleteById(id);
    }

    @Override
    public List<Promo> findAll() {

        return (List<Promo>) promoRepo.findAll();
    }

    @Override
    public Promo findByIdPromo(int id) {

        if (promoRepo.findById(id).isEmpty()) {
            throw new PromoExceptionNotFound("Data tidak ditemukan");
        }
        return promoRepo.findById(id).get();
    }

    @Override
    public Promo updatePromo(int id, Promo promo) {
        if (promoRepo.findById(id).isEmpty()) {
            throw new PromoExceptionNotFound("Data tidak ditemukan");
        }

        Promo promoDB = promoRepo.findById(id).get();

        if (Objects.nonNull(promo.getNama()) && !"".equalsIgnoreCase(promo.getNama())) {
            promoDB.setNama(promo.getNama());
        } else {
            throw new PromoExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(promo.getJenis()) && !"".equalsIgnoreCase(promo.getJenis())) {
            promoDB.setJenis(promo.getJenis());
        } else {
            throw new PromoExceptionBadRequest("Jenis tidak boleh kosong");
        }

        promoDB.setAkhir(promo.getAkhir());
        promoDB.setMulai(promo.getMulai());

        if (Objects.nonNull(promo.getDeskripsi()) &&
                !"".equalsIgnoreCase(promo.getDeskripsi())) {
            promoDB.setDeskripsi(promo.getDeskripsi());
        } else {
            throw new PromoExceptionBadRequest("Deskripsi tidak boleh kosong");
        }

        return promoRepo.save(promoDB);
    }

}
