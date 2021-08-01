package com.example.demo.userInfo;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@Service
@AllArgsConstructor
//@RequestMapping("api/v1")
public class UserLogin implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    //@GetMapping("/login")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

    }
    public String signUpUser(UserInfo userInfo) {
        boolean userExists = userRepository.findByEmail(userInfo.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email is not available");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userInfo.getPassword());

        userInfo.setPassword(encodedPassword);

        userRepository.save(userInfo);

        return "user found";
    }
}
