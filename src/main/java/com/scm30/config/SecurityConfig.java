package com.scm30.config;

import com.scm30.services.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;



    //Configuration of Authentication Provider:

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {

    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

    provider.setPasswordEncoder(passwordEncoder());

    return provider;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(authorize->
            authorize
                    .requestMatchers("/user/**").authenticated()
                    .anyRequest().permitAll()

        );

        httpSecurity.formLogin(formLogin->
                formLogin.loginPage("/login")
                        .loginProcessingUrl("/authenticate")
                        .successForwardUrl("/user/dashboard")
//                        .failureForwardUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->
                logoutForm.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                );
            httpSecurity.oauth2Login(Customizer.withDefaults());





        return httpSecurity.build();
}


@Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

//    @Bean
//    public UserDetailsService userDetailsService(){
////        UserDetails user1 = User
////                .withDefaultPasswordEncoder()
////                .username("Aman")
////                .password("hello@123")
////                .roles("ADMIN", "USER")
////                .build();
////        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
////        return inMemoryUserDetailsManager;
//
}
