package com.contact.smartmanagerspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig  {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        try {
            httpSecurity.csrf().disable()
            // .cors().disable()
            .authorizeHttpRequests()
            .requestMatchers("/user")
            .hasRole("USER")
            .requestMatchers("/public")
            .hasRole("USER")
            .requestMatchers("/send/email")
            .permitAll()
             .anyRequest()
             .authenticated()
            //  .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and().formLogin()
            .loginPage("/public/signin")
            .defaultSuccessUrl("/user/index",true)
            .failureUrl("/public/signin")
            ;
                        // .hasRole("USER")
        } catch (Exception e) {
            
            e.printStackTrace();
        }

        return httpSecurity.build() ;
    }

    @Bean
    public UserDetailsService getUserDetailsService()
    {
        return new UserDetailsServiceImpl() ;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder() ;
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider() ;
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService()) ;
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider ;
    }

    //  configure method..
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    //     auth.authenticationProvider(authenticationProvider()) ;

    // }



}
