package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reservahoteles.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @NotBlank(message = "Number Document is mandatory")
    @NotNull
    @Column(name = "number_document_user", nullable = false, unique = true)
    private String numberDocumentUser;

    @NotBlank(message = "Email is mandatory")
    @NotNull
    @Column(name = "email_user", nullable = false, unique = true)
    private String emailUser;

    @NotBlank(message = "Names are mandatory")
    @NotNull
    @Column(name = "names_user", nullable = false)
    private String namesUser;

    @NotBlank(message = "Lastnames are mandatory")
    @NotNull
    @Column(name = "lastnames_user", nullable = false)
    private String lastNamesUser;

    @NotBlank(message = "Phone number is mandatory")
    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<TokenEntity> tokens;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

}
