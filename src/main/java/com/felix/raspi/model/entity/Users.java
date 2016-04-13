package com.felix.raspi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felix.raspi.constants.TableConstant;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fsoewito on 2/23/2016.
 */

@Entity
@Table(name = TableConstant.TABLE_USERS)
public class Users implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = TableConstant.SEQ_USERS, sequenceName = TableConstant.SEQ_USERS)
    @GeneratedValue(generator = TableConstant.SEQ_USERS)
    @Column(name = TableConstant.COL_ID)
    private Integer id;

    @Column(name = TableConstant.COL_USERNAME, nullable = false, length = TableConstant.LEN_USERNAME)
    private String username;

    @Email
    @Column(name = TableConstant.COL_EMAIL, nullable = false, length = TableConstant.LEN_EMAIL)
    private String email;

    @Column(name = TableConstant.COL_PASSWORD, nullable = false, length = TableConstant.LEN_PASSWORD)
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_authorities",
            joinColumns = { @JoinColumn(name = "users_id") },
            inverseJoinColumns = { @JoinColumn(name = "authorities_id") })
    private Set<Authorities> roles = new HashSet<>();

    public Users() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authorities> getRoles() {
        return roles;
    }

    public void setRoles(Set<Authorities> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
