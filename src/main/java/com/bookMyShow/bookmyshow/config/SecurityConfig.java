//package com.bookMyShow.bookmyshow;
//
//import com.bookMyShow.bookmyshow.converters.UserBuilder;
//import com.bookMyShow.bookmyshow.dto.UserDto;
//import com.bookMyShow.bookmyshow.repository.UserRepository;
//import com.bookMyShow.bookmyshow.services.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cglib.proxy.NoOp;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static java.util.stream.Collectors.toList;
//import static org.apache.coyote.http11.Constants.a;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final UserRepository userRepository;
//    private final UserService userService;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf(customizer->customizer.disable())
//                        .authorizeHttpRequests(request-> request.anyRequest().authenticated())
//                                .httpBasic(Customizer.withDefaults())
//                                        .sessionManagement(session->
//                                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//       return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        provider.setUserDetailsService(userService);
//        return provider;
//
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//
//
//}
//
