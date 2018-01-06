package com.rahogata.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Configures application security.
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${secure.requests.post}")
    private String[] securedPostUris;

    @Value("${secure.requests.get}")
    private String[] securedGetUris;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("rahogata").password("secret123").authorities(Collections.emptyList());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            // we don't need CSRF because our token is invulnerable
            .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // don't create
                                                                                                         // session
            .and().headers().cacheControl(); // disable page caching
        if (securedPostUris.length != 0) {
            httpSecurity.requestMatchers().antMatchers(HttpMethod.POST, securedPostUris).and().authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
        }

        if (securedGetUris.length != 0) {
            httpSecurity.requestMatchers().antMatchers(HttpMethod.GET, securedGetUris).and().authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
        }
    }
}
