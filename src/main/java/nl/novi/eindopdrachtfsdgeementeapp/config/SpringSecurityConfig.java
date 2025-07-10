package nl.novi.eindopdrachtfsdgeementeapp.config;

import nl.novi.eindopdrachtfsdgeementeapp.filter.JwtRequestFilter;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.services.CustomUserDetailsService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET, "/municipalities/province").permitAll()
                        .requestMatchers(HttpMethod.GET, "/municipalities/by-province/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/municipalities/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/email/**").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/*/authorities").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.POST, "/users/*/authorities").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/*/authorities/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/proposals").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/proposals/**}").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.GET, "/proposals/user/**").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.GET, "/proposals/municipality").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.POST, "/proposals").hasAnyRole("ADMIN","RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.DELETE, "/proposals/**").hasAnyRole("ADMIN", "RESIDENT")
                        .requestMatchers(HttpMethod.POST, "/proposals/*/photo").hasAnyRole("ADMIN", "RESIDENT")
                        .requestMatchers(HttpMethod.GET, "/proposals/*/photo").permitAll()
                        .requestMatchers(HttpMethod.GET, "/proposals/*/reviews").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.POST, "/proposals/*/reviews").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.GET, "/statuses/**").hasAnyRole("ADMIN", "MUNICIPALITY", "RESIDENT")
                        .requestMatchers(HttpMethod.GET, "/statuses/proposal/**").hasAnyRole("ADMIN", "RESIDENT", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.PUT, "/statuses/proposal/**").hasAnyRole("ADMIN", "MUNICIPALITY")
                        .requestMatchers(HttpMethod.POST, "/municipalities").hasAnyRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
