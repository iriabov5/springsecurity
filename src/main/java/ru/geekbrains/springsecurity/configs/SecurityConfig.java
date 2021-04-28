package ru.geekbrains.springsecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/profile").authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");

    }

    // Spring security in memory auth
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2y$12$p063E5reS4B76546lqKGNeFeHWsULH5tTF.BfYy3fXMumRCxKXZvC")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2y$12$p063E5reS4B76546lqKGNeFeHWsULH5tTF.BfYy3fXMumRCxKXZvC")
                .roles("ADMIN","USER")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

}
