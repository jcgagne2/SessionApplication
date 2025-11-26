package ch.bdt.spike.spring.cloud.sessionapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("admin")
                               .password(passwordEncoder.encode("password"))
                               .roles("ADMIN")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults())
            //.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
            //        SessionCreationPolicy.ALWAYS))
            //.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.maximumSessions(
            //        1))
            .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/")
                                                                           .hasRole("ADMIN")
                                                                           .anyRequest()
                                                                           .authenticated());
        return http.build();
    }

    /**
     * Pour avoir des sessions indépendantes, le cookie de session doit porter un nom différent.
     * Voir aussi spring.session.data.redis.namespace.
     */
    //@Bean
    //public CookieSerializer cookieSerializer() {
    //    DefaultCookieSerializer s = new DefaultCookieSerializer();
    //    s.setCookieName(applicationName + "_SESSION");
    //    return s;
    //}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}