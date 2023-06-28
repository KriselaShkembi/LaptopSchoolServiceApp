package com.example.laptopschoolserviceapp.services;


import com.example.laptopschoolserviceapp.models.User;
import com.example.laptopschoolserviceapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> perdorues = userRepository.findByEmail(email); // username

        if (perdorues.isEmpty()) {
            throw new UsernameNotFoundException("Perdoruesi nuk u gjet: " + email);
        } else {
            org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User
                    .withUsername(email)

                    .password(perdorues.get().getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority(perdorues.get().getRole().name())));

            return builder.build();
        }
    }


}
