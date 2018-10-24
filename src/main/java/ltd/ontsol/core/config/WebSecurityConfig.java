package ltd.ontsol.core.config;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ltd.ontsol.core.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Inject
    private UserDetailsService userDetailsService;


    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security. config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(final HttpSecurity http) {
        try {
            http.csrf().disable()

                    .authorizeRequests()
                    .antMatchers("/*").permitAll()
                    .antMatchers("/app/rest/**").permitAll()
                    .antMatchers("/uploads/**").permitAll()
                    .antMatchers("/error/**").permitAll()
                    .antMatchers("/test/**").permitAll()
                    .antMatchers("/manage/**").authenticated()
                    .antMatchers("/soap/**").authenticated()
                    .and()
                    //解决 X-Frame-Options edit by run 2018/06/22
                    .headers().frameOptions().disable()
                    .and()

                    .formLogin()
                    .loginPage("/login").permitAll()
                    .successHandler(loginSuccessHandler())
                    .and()

                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .and()

                    .exceptionHandling().accessDeniedPage("/access?error");
        } catch (Exception e) {
            log.error("Error during security setup", e);
        }

    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/front_static/**");
        web.ignoring().antMatchers("/manage_static/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
