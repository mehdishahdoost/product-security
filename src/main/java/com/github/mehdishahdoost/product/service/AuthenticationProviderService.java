package com.github.mehdishahdoost.product.service;

import com.github.mehdishahdoost.product.entity.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailService jpaUserDetailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SCryptPasswordEncoder sCryptPasswordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();


        CustomUserDetail customUserDetail = jpaUserDetailService.loadUserByUsername(name);
        switch(customUserDetail.getUser().getAlgorithm()) {
            case BCRYPT:
                return checkPassword(customUserDetail, password, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(customUserDetail, password, sCryptPasswordEncoder);
        }


        throw new BadCredentialsException("bad credential");
    }

    private Authentication checkPassword(CustomUserDetail user, String rawPassword, PasswordEncoder passwordEncoder) {
        if(passwordEncoder.matches(user.getPassword(), rawPassword)) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    user.getAuthorities());
        }else {
            throw new BadCredentialsException("bad credential");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
