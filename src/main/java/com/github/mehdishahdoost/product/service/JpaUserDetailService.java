package com.github.mehdishahdoost.product.service;

import com.github.mehdishahdoost.product.entity.CustomUserDetail;
import com.github.mehdishahdoost.product.entity.User;
import com.github.mehdishahdoost.product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetail loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(s).orElseThrow(() ->
                new UsernameNotFoundException("Username not found!"));

        return new CustomUserDetail(user);
    }
}
