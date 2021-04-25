package pl.gondzio.songApp.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@Data
@Entity
public class User implements UserDetails, Serializable {

    public static final int MAIN_USER = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private boolean enabled = true;

    private String username;
    private String password;

    private String fullName;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
    private Set<Role> authorities = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public User(String username, String fullName, int id) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public static User getSystemUser(){
        return new User("System", "System user", 0);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getId(){
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}