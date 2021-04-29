package ru.geekbrains.springsecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/only_for_admins/**").hasRole("ADMIN")
                .antMatchers("/read_profile/**").hasAuthority("READ_PROFILE")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");

    }

    // Spring security in memory auth
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2y$12$p063E5reS4B76546lqKGNeFeHWsULH5tTF.BfYy3fXMumRCxKXZvC")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$p063E5reS4B76546lqKGNeFeHWsULH5tTF.BfYy3fXMumRCxKXZvC")
//                .roles("ADMIN","USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }


    // Spring security JDBC auth

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2y$12$p063E5reS4B76546lqKGNeFeHWsULH5tTF.BfYy3fXMumRCxKXZvC")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$p063E5reS4B76546lqKGNeFeHWsULH5tTF.BfYy3fXMumRCxKXZvC")
//                .roles("ADMIN", "USER")
//                .build();

        //        if (!users.userExists(user.getUsername())) {
//            users.createUser(user);
//        }
//        if (!users.userExists(admin.getUsername())) {
//            users.createUser(admin);
//        }
        return new JdbcUserDetailsManager(dataSource);
    }

}
