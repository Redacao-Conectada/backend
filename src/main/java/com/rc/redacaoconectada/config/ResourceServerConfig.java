package com.rc.redacaoconectada.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  private Environment env;

  @Autowired
  private JwtTokenStore tokenStore;

  private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
  private static final String[] USERS_ROUTE = {"/users/**"};
  private static final String[] ESSAYS_ROUTE = {"/essays/**"};
  private static final String[] CORRECTIONS_ROUTE = {"/corrections/**"};

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(tokenStore);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // H2 DATABASE CONFIG
    if (Arrays.asList(env.getActiveProfiles()).contains("test"))
      http.headers().frameOptions().disable();

    // TODO Configurar rotas de remoção de usuário apenas para admin (futuramente)
    http.authorizeRequests()
            .antMatchers(PUBLIC).permitAll()
            .antMatchers(HttpMethod.POST, USERS_ROUTE).permitAll()
            .antMatchers(USERS_ROUTE).hasAnyRole("STUDENT", "TEACHER", "ADMIN")
            .antMatchers(HttpMethod.GET, ESSAYS_ROUTE).permitAll()
            .antMatchers(ESSAYS_ROUTE).hasAnyRole("STUDENT", "TEACHER", "ADMIN")
            .antMatchers(HttpMethod.GET, CORRECTIONS_ROUTE).hasAnyRole("STUDENT", "TEACHER", "ADMIN")
            .antMatchers(HttpMethod.POST, CORRECTIONS_ROUTE).hasAnyRole("TEACHER", "ADMIN")
            .antMatchers(CORRECTIONS_ROUTE).hasRole("ADMIN")
            .anyRequest().authenticated();

    http.cors().configurationSource(corsConfigurationSource());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
    corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
    corsConfig.setAllowCredentials(true);
    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return source;
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilter() {
    FilterRegistrationBean<CorsFilter> bean
            = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }
}
