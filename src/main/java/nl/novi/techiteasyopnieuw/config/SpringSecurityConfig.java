package nl.novi.techiteasyopnieuw.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    /*TODO inject customUserDetailService en jwtRequestFilter; gedaan*/

@Autowired
private UserDetailsService customUserDetailsService;

@Autowired
private Filter jwtRequestFilter;

    public SpringSecurityConfig(UserDetailsService customUserDetailsService, Filter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        UserDetailsService customUserDetailsService;
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }



    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                                auth
                                        // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
//                .requestMatchers("/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                                        /*TODO voeg de antmatchers toe voor admin(post en delete) en user (overige)*/
                                        .requestMatchers("/authenticated").authenticated()
                                        .requestMatchers("/authenticate").permitAll()/*alleen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
                                        .anyRequest().denyAll() /*Deze voeg je altijd als laatste toe, om een default beveiliging te hebben voor eventuele vergeten endpoints of endpoints die je later toevoegd. */
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        Filter jwtRequestFilter;
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
