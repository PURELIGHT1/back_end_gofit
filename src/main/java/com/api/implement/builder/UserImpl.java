package com.api.implement.builder;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.dto.AuthenticationRequest;
import com.api.dto.AuthenticationResponse;
import com.api.dto.TokenResponse;
import com.api.models.TokenType;
import com.api.models.UserRole;
import com.api.models.entities.GenerateTabel;
import com.api.models.entities.Kelas;
import com.api.models.entities.Kode;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.Promo;
import com.api.models.entities.User;
import com.api.models.entities.token.Token;
import com.api.models.repos.GenerateRepo;
import com.api.models.repos.KelasRepo;
import com.api.models.repos.KodeRepo;
import com.api.models.repos.MemberRepo;
import com.api.models.repos.PegawaiRepo;
import com.api.models.repos.PromoRepo;
import com.api.models.repos.TokenRepo;
import com.api.models.repos.UserRepo;
import com.api.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserImpl {

    @Autowired
    private UserRepo repo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private KodeRepo kodeRepo;

    @Autowired
    private KelasRepo kelasRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private GenerateRepo generateRepo;

    @Autowired
    private PromoRepo promoRepo;

    @Autowired
    private PegawaiRepo pegawaiRepo;

    @Autowired
    private JwtService JwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(User user) {
        boolean userExists = repo.findByUserLogin(user.getUserLogin()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Username sudah terdaftar");
        }
        User userDB = repo.save(user);
        User userTemp = repo.findById(userDB.getId()).get();
        Date dateGenerate = new Date();

        // Tambah Generate Table
        if (userTemp.getId() == 1) {
            Date now = new Date();
            GenerateTabel generateTabel = new GenerateTabel(1, 0, 2, 0, 100, now);
            generateRepo.save(generateTabel);
        }

        // Fungsi tambah 7 hari
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateGenerate);

        cal.add(Calendar.DATE, 7);
        Date akhir = cal.getTime();

        // Tambah Promo
        Promo promoDB = Promo.build(1, "Promo Kelas Reguler GoFit Gym", "Reguler", dateGenerate, akhir,
                "Member harus memiliki deposit, dengan minimal deposit Rp.500.000. Setiap deposit Rp.3.000.000,- mendapat bonus deposit Rp.300.000,- Uang yang sudah didepositkan tidak dapat diminta kembali.",
                "A");
        Promo promoDB2 = Promo.build(2, "Promo Kelas Paket GoFit Gym", "Paket", dateGenerate, akhir,
                "Berlaku untuk jenis workout yang sama. Bayar 5 sesi, gratis 1. Berlaku 1 bulan sejak pembayaran. Bayar 10, gratis 3. Berlaku 2 bulan sejak pembayaran. Pada paket ini, setiap kali member mengikuti kelas yang sudah dibeli paketnya, maka yang terpotong adalah jumlah deposit kelasnya, bukan deposit uangnya. Pembelian paket kelas hanya diperbolehkan jika sisa paket sebelumnya sudah habis, atau sudah hangus (intinya sisa deposit = 0)",
                "A");
        promoRepo.save(promoDB);
        promoRepo.save(promoDB2);

        // Tambah Pegawai Kasir dan MO
        Date tglLahirMO = new GregorianCalendar(2000, Calendar.SEPTEMBER, 17).getTime();
        Date tglLahirKasir = new GregorianCalendar(2002, Calendar.JANUARY, 1).getTime();
        Pegawai pegawaiBD = Pegawai.build("P01", "Manajer Operasional Gofit Gym", "mo.png", "mo@gmail.com",
                "Jl. Babarsari No.43, Janti, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281",
                tglLahirMO, "082375178720", "A", "1", null, null);
        Pegawai pegawaiBD2 = Pegawai.build("P02", "Kasir Gofit Gym", "kasir.png", "kasir@gmail.com",
                "Jl. Babarsari No.43, Janti, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281",
                tglLahirKasir, "082375178720", "A", "1", null, null);

        pegawaiRepo.save(pegawaiBD);
        pegawaiRepo.save(pegawaiBD2);
        boolean moExists = repo.findByUserLogin(pegawaiBD.getEmail()).isPresent();
        if (moExists) {
            throw new IllegalStateException("Username sudah terdaftar");
        }
        User mo = new User();
        mo.setUserLogin(pegawaiBD.getEmail());
        String encodedPasswordMo = bCryptPasswordEncoder.encode("123456");
        mo.setPasswordLogin(encodedPasswordMo);
        mo.setUserRole(UserRole.MO);
        mo.setPegawai(pegawaiBD);
        repo.save(mo);

        boolean kasirExists = repo.findByUserLogin(pegawaiBD2.getEmail()).isPresent();
        if (kasirExists) {
            throw new IllegalStateException("Username sudah terdaftar");
        }
        User kasir = new User();
        kasir.setUserLogin(pegawaiBD2.getEmail());
        String encodedPasswordKasir = bCryptPasswordEncoder.encode("123456");
        kasir.setPasswordLogin(encodedPasswordKasir);
        kasir.setUserRole(UserRole.KASIR);
        kasir.setPegawai(pegawaiBD2);
        repo.save(kasir);

        // Tambah Kelas
        List<Kelas> listKelas = new ArrayList<>();
        Kelas kelas = new Kelas(1, "SPINE Corrector", 150000, 10);
        Kelas kelas2 = new Kelas(2, "MUAYTHAI", 150000, 10);
        Kelas kelas3 = new Kelas(3, "PILATES", 150000, 10);
        Kelas kelas4 = new Kelas(4, "ASTHANGA", 150000, 10);
        Kelas kelas5 = new Kelas(5, "Body Combat", 150000, 10);
        Kelas kelas6 = new Kelas(6, "ZUMBA", 150000, 10);
        Kelas kelas7 = new Kelas(7, "HATHA", 150000, 10);
        Kelas kelas8 = new Kelas(8, "Wall Swing", 150000, 10);
        Kelas kelas9 = new Kelas(9, "Basic Swing", 150000, 10);
        Kelas kelas10 = new Kelas(10, "Bellydance", 150000, 10);
        Kelas kelas11 = new Kelas(11, "BUNGEE*", 200000, 10);
        Kelas kelas12 = new Kelas(12, "Yogalates", 150000, 10);
        Kelas kelas13 = new Kelas(13, "BOXING", 150000, 10);
        Kelas kelas14 = new Kelas(14, "Calisthenics", 150000, 10);
        Kelas kelas15 = new Kelas(15, "Pound Fit", 150000, 10);
        Kelas kelas16 = new Kelas(16, "Trampoline Workout*", 200000, 10);
        Kelas kelas17 = new Kelas(17, "Yoga For Kids", 150000, 10);
        Kelas kelas18 = new Kelas(18, "Abs Pilates", 150000, 10);
        Kelas kelas19 = new Kelas(19, "Swing For Kids", 150000, 10);
        Kelas kelas20 = new Kelas(20, "SPINE", 150000, 10);

        listKelas.add(kelas);
        listKelas.add(kelas2);
        listKelas.add(kelas3);
        listKelas.add(kelas4);
        listKelas.add(kelas5);
        listKelas.add(kelas6);
        listKelas.add(kelas7);
        listKelas.add(kelas8);
        listKelas.add(kelas9);
        listKelas.add(kelas10);
        listKelas.add(kelas11);
        listKelas.add(kelas12);
        listKelas.add(kelas13);
        listKelas.add(kelas14);
        listKelas.add(kelas15);
        listKelas.add(kelas16);
        listKelas.add(kelas17);
        listKelas.add(kelas18);
        listKelas.add(kelas19);
        listKelas.add(kelas20);
        kelasRepo.saveAll(listKelas);

        // pegawai, instruktur, member,
        Kode kode = Kode.build(1, "A", "Aktif");
        Kode kode2 = Kode.build(2, "I", "Tidak Aktif");

        // aktivasi
        Kode kode3 = Kode.build(3, "W", "Menunggu Pembayaran");
        Kode kode4 = Kode.build(4, "P", "Terbayar");

        // jadwalharian, jadwalumum
        Kode kode5 = Kode.build(5, "G", "Sedang Berlangsung");
        Kode kode6 = Kode.build(6, "E", "Berakhir");
        Kode kode7 = Kode.build(7, "L", "Libur");
        Kode kode8 = Kode.build(8, "S", "Terjadwal");

        // izin instruktur
        Kode kode9 = Kode.build(9, "PE", "Pending");
        Kode kode10 = Kode.build(10, "C", "Izin Diterima");
        Kode kode11 = Kode.build(11, "D", "DiTolak");
        Kode kode12 = Kode.build(12, "AP", "Presensi Kelas");

        // presensi member
        Kode kode13 = Kode.build(13, "H", "Hadir");
        Kode kode14 = Kode.build(14, "Al", "Alpha");
        Kode kode15 = Kode.build(15, "Iz", "Izin");

        Kode kode16 = Kode.build(16, "T", "Presensi Tersedia");
        Kode kode17 = Kode.build(17, "DONE", "Presensi Sudah Di Tutup");

        Kode kode18 = Kode.build(18, "BP", "Belum Presensi");

        Kode kode19 = Kode.build(19, "Block", "Block");

        Kode kode20 = Kode.build(20, "BOOK", "Booking");

        kodeRepo.save(kode);
        kodeRepo.save(kode2);
        kodeRepo.save(kode3);
        kodeRepo.save(kode4);
        kodeRepo.save(kode5);
        kodeRepo.save(kode6);
        kodeRepo.save(kode7);
        kodeRepo.save(kode8);
        kodeRepo.save(kode9);
        kodeRepo.save(kode10);
        kodeRepo.save(kode11);
        kodeRepo.save(kode12);
        kodeRepo.save(kode13);
        kodeRepo.save(kode14);
        kodeRepo.save(kode15);
        kodeRepo.save(kode16);
        kodeRepo.save(kode17);
        kodeRepo.save(kode18);
        kodeRepo.save(kode19);
        kodeRepo.save(kode20);
        return userDB;
    }

    public User getUserById(String id) {
        Member memberDB = memberRepo.findById(id).get();
        memberDB.getEmail();

        return repo.findByUserLogin(memberDB.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User dengan username '%s' tidak ditemukan", memberDB.getEmail())));
    }

    // public AuthenticationResponse authenticate(AuthenticationRequest request) {
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(request.getUsername(),
    // request.getPassword()));

    // User user = repo.findByUserLogin(request.getUsername()).orElseThrow();

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

        User userDB = repo.findByUserLogin(request.getUsername()).orElseThrow();
        // if (userDB != null) {
        String jwtToken = jwtService.generateToken(userDB);
        String refreshToken = jwtService.generateRefreshToken(userDB);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
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

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userLogin;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        userLogin = JwtService.extractUsername(refreshToken);

        if (userLogin != null) {
            var user = repo.findByUserLogin(userLogin).orElseThrow();
            if (JwtService.isTokenValid(refreshToken, user)) {

                var accessToken = jwtService.generateToken(user);
                var authResponse = TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
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
    // User userDB = repo.findByUserLogin(user.getUserLogin())
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
