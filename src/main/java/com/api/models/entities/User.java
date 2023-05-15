package com.api.models.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.models.UserRole;
import com.api.models.entities.token.Token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "_user")
@Table(name = "_user")
public class User implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return userLogin;
    }

    @Override
    public String getPassword() {
        return passwordLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "jabatan")
    private UserRole userRole;
    @Column(length = 100, nullable = false, unique = true, name = "username")
    private String userLogin;
    @Column(nullable = false, name = "password")
    private String passwordLogin;

    @ManyToOne
    @JoinColumn(name = "id_pegawai", nullable = true)
    private Pegawai pegawai;

    @ManyToOne
    @JoinColumn(name = "id_instruktur", nullable = true)
    private Instruktur instruktur;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = true)
    private Member member;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
}
