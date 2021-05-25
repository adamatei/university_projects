package fontys.sem3.it.ticketstore.config;

import fontys.sem3.it.ticketstore.filter.JWTAuthenticationFilter;
import fontys.sem3.it.ticketstore.filter.JWTAuthorizationFilter;
import fontys.sem3.it.ticketstore.service.AuthenticationUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationUserDetailService authenticationUserDetailService;

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                //ROLE BASED AUTHENTICATION START
                .antMatchers("/tickets_categories/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/concerts/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/tickets/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/users/**")/*.hasAnyAuthority("USER", "ADMIN")*/.permitAll()
                .antMatchers("/carts/**")/*.hasAnyAuthority("USER")*/.permitAll()
                .antMatchers("/address/**").hasAnyAuthority("USER")
                .antMatchers("/items/**").hasAnyAuthority("USER")
                .antMatchers("/orders/**").hasAnyAuthority("USER")
                .antMatchers("/payment/**").hasAnyAuthority("USER")
                //ROLE BASED AUTHENTICATION END
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

}