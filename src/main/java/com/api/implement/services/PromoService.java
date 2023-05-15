package com.api.implement.services;

import java.util.List;
import com.api.models.entities.Promo;

public interface PromoService {

    List<Promo> findAll();

    Promo findByIdPromo(int id);

    Promo updatePromo(int id, Promo promo);

    Promo updatePromoStatus(int id);

    Promo createPromo(Promo promo);

    void deletePromo(int id);

    List<Promo> findByjenis(String jenis);
}
