package com.anton.sring.spring_boot.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Transactional
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<com.anton.sring.spring_boot.models.Role> roleSet;

    @Column(name = "firstname", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "age")
    private String age;

    @Column(name = "email", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    public User(Set<com.anton.sring.spring_boot.models.Role> roleSet, String name, String lastname, String age, String login, String password) {
        this.roleSet = roleSet;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public User(long id, Set<com.anton.sring.spring_boot.models.Role> roleSet, String name, String lastname, String age, String login, String password) {
        this.id = id;
        this.roleSet = roleSet;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(long id) {
        this.id = id;
    }

    public User() {

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<com.anton.sring.spring_boot.models.Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<com.anton.sring.spring_boot.models.Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.password;
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleSet=" + roleSet +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}