//package com.senai.escola.Utils;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
////ainda nao esta sendo utilizada
//
//@Configuration
//@EnableMethodSecurity
//
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // rotas publicas (login e cadastro)
//                        .requestMatchers("/auth/**").permitAll()
//
//                        // permissões especificas
//                        .requestMatchers("/alunos/**").hasAnyRole("ADMIN", "PROFESSOR")
//                        .requestMatchers("/professor/**").hasRole("ADMIN")
//
//                        // qualquer outra rota vai precisar de autenticação
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
