package com.anton.sring.spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.anton.sring.spring_boot.models.Role;
import com.anton.sring.spring_boot.models.User;
import com.anton.sring.spring_boot.repository.RoleRepository;
import com.anton.sring.spring_boot.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements com.anton.sring.spring_boot.service.UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(User user) {
        userRepository.deleteById(user.getId());
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        addUser(user);
    }

    @Override
    public User findbyid(User user) {
        Optional<User> tmp = userRepository.findById(user.getId());
        return tmp.get();
    }

    @Override
    public List<Role> findRolesById(User user) {
        user = findbyid(user);
        return user.getRoleSet().stream().collect(Collectors.toList());
    }

    @Override
    public User findByUserLogin(String userlogin) {
        TypedQuery<User> typedQuery = (TypedQuery<User>) entityManager
                .createQuery("select D from User D where D.login = '" + userlogin + "'");
        return typedQuery.getResultList().get(0);
    }

    @Override
    public List<String> getAllRoles(Role role) {
        Query query = entityManager.createQuery("select role from Role");
        return query.getResultList();
    }

    @Override
    public List<Role> getAllRolesObjects() {
        TypedQuery<Role> typedQueryquery = (TypedQuery<Role>) entityManager.createQuery("from Role");
        return typedQueryquery.getResultList();
    }

    @Override
    public User findByUserPassword(String name) {
        TypedQuery<User> typedQuery = (TypedQuery<User>) entityManager.createQuery("select D from User D where D.password = '" + name + "'");
        return typedQuery.getResultList().get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return buildUserForAuthentication(findByUserLogin(s), buildUserAuthority(findByUserLogin(s).getRoleSet()));
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();

        for (Role userRole : roles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<>(setAuths);
        return Result;
    }
}