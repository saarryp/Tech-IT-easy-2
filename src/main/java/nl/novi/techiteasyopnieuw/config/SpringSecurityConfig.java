package nl.novi.techiteasyopnieuw.config;

import jakarta.servlet.Filter;
import nl.novi.techiteasyopnieuw.filter.JwtRequestFilter;
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
private JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(UserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
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
                                        //MIJN REQUESTMATCHERS VOOR TELEVISION, REMOTECONTROLLERS, WALLBRACKETS EN CIMODULES
                                        //eerst role admin
                                        .requestMatchers(HttpMethod.POST, "/televisions").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/televisions").hasRole("ADMIN")
                                        //lijkt me dat de putmapping hier ook onder valt
                                        .requestMatchers(HttpMethod.PUT, "/televisions").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/televisions").authenticated()

                                        .requestMatchers(HttpMethod.POST, "/remotecontrollers").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/remotecontrollers").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/remotecontrollers").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/remotecontrollers").authenticated()

                                        .requestMatchers(HttpMethod.POST,"/wall_brackets").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/wall_brackets").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/wall_brackets").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/wall_brackets").authenticated()

                                        .requestMatchers(HttpMethod.POST,"/cimodules").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/cimodules").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/cimodules").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/cimodules").authenticated()

                                        .requestMatchers("/authenticated").authenticated()
                                        .requestMatchers("/authenticate").permitAll()/*alleen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
                                        .anyRequest().denyAll() /*Deze voeg je altijd als laatste toe, om een default beveiliging te hebben voor eventuele vergeten endpoints of endpoints die je later toevoegd. */
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
