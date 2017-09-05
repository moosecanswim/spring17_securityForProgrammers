package me.moosecanswim.securityforprogrammers;

import me.moosecanswim.securityforprogrammers.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SSPersonDetailsService personDetailsService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    public UserDetailsService userDetailsServiceBead() throws Exception{
        return new SSPersonDetailsService(personRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        System.out.println("into the configure");
        auth
                        .userDetailsService(userDetailsServiceBean());
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("dave").password("begreat").roles("ADMIN")
//                .and()
//                .withUser("newuser").password("newuserpa$$").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN");

    }
}