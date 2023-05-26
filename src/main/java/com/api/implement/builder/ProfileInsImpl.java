package com.api.implement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.JadwalHarianResponse;
import com.api.dto.ProfileInsResponse;
import com.api.implement.JadwalHarianImpl;
import com.api.implement.PresensiInstrukturImpl;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.PresensiInstruktur;
import com.api.models.repos.BookingKelasRepo;

@Service
public class ProfileInsImpl {

    @Autowired
    private JadwalHarianImpl jadwalHarianImpl;

    @Autowired
    private BookingKelasRepo repo;

    @Autowired
    private PresensiInstrukturImpl presensiInstrukturImpl;

    public ProfileInsResponse getAll(String id) {
        ProfileInsResponse DB = new ProfileInsResponse();

        List<JadwalHarian> listJadwalHarianDB = jadwalHarianImpl.findAllJadwalHarianIns(id);
        List<JadwalHarianResponse> list = new ArrayList<>();
        for (int i = 1; i < listJadwalHarianDB.size(); i++) {
            JadwalHarianResponse jadwalDB = new JadwalHarianResponse();
            jadwalDB.setId(listJadwalHarianDB.get(i).getId());
            jadwalDB.setInstruktur(listJadwalHarianDB.get(i).getInstruktur());
            jadwalDB.setInstrukturPeganti(listJadwalHarianDB.get(i).getInstrukturPeganti());
            jadwalDB.setKelas(listJadwalHarianDB.get(i).getKelas());
            jadwalDB.setTglJadwal(listJadwalHarianDB.get(i).getTglJadwal());
            jadwalDB.setHariJadwal(listJadwalHarianDB.get(i).getHariJadwal());
            jadwalDB.setSesiJadwal(listJadwalHarianDB.get(i).getSesiJadwal());
            jadwalDB.setStatus(listJadwalHarianDB.get(i).getStatus());
            Integer totalMember = repo.findAllMemberBooking(listJadwalHarianDB.get(i));
            jadwalDB.setTotalMember(totalMember);
            list.add(jadwalDB);
        }

        List<PresensiInstruktur> listPresensiInstruktur = presensiInstrukturImpl.findAllJadwalHarianIns(id);

        DB.setListJadwalHarian(list);
        DB.setListPresensiInstruktur(listPresensiInstruktur);
        return DB;
    }
}
