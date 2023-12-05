package com.demo.shop.config;


import com.demo.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvier = new DaoAuthenticationProvider();
        authProvier.setUserDetailsService(userDetailsService);
        authProvier.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvier;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                        .antMatchers("/css/**", "/js/**", "/img/**", "/vendor/**"
                        , "/font-awesome/**", "/summernote/**", "/files/**").permitAll()
                        .antMatchers("/home").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/user/**").hasAnyRole("USER")
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login_processing")
                        .defaultSuccessUrl("/admin/index", true)
                        .failureUrl("/login?success=fail")
                .and()
                        .logout().permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logoutSuccessful")

                .and()
                        .rememberMe()
                        .key("uniqueAndSecret")
                        .userDetailsService(this.userDetailsService)

                        .tokenValiditySeconds(86400);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(db.getDataSource());
        return db;
    }*/
}
