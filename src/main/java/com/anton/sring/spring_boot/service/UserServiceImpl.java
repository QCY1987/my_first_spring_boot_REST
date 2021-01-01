package com.anton.sring.spring_boot.service;

import com.anton.sring.spring_boot.dao.UserRepository;
import com.anton.sring.spring_boot.models.Role;
import com.anton.sring.spring_boot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

   // @Autowired
  //  private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> index() {
        return userRepository.findAll();
    }



    public User show (Long id) {
        User user=null;
        Optional <User> optional=userRepository.findById(id);
        if (optional.isPresent()) {
            user=optional.get();
        }
        return user;
    }

    public boolean save (User user) {
        User SaveUser = userRepository.findByEmail(user.getEmail());
        if(SaveUser != null) {
            return false;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
       // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      //  userRepository.save(user);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    public void update (User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }



    public void delete (User user) {
        userRepository.delete(user);
    }
   /* @Override
    public User showUserByUsername(String username) {
        return userRepository.showUserByUsername(username);
    }
*/
    @Override

    public UserDetails loadUserByUsername (String a) throws UsernameNotFoundException {
        User user= userRepository.findByName(a);
        Set<GrantedAuthority> grantedAuthorities= new HashSet<>();
        for (Role role: user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),grantedAuthorities);
    }


}
