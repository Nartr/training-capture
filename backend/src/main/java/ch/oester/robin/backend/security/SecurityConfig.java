package ch.oester.robin.backend.security;

import ch.oester.robin.backend.user.UserDetailsServiceImpl;
import ch.oester.robin.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/resources/styles/*", "/resources/images/*", "/registration").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/", true)
        .permitAll()
        .and()
        .httpBasic()
        .and()
        .csrf().disable()
        .logout()
        .logoutSuccessUrl("/login?logout").permitAll()
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .and()
        .sessionManagement().invalidSessionUrl("/login?expired")
        .maximumSessions(1).expiredUrl("/login?expired")
        .sessionRegistry(sessionRegistry());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if(!registry.hasMappingForPattern("/resources/**")) {
      registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
    }
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("forward:/index.html");
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Bean
  public PasswordEncoder customPasswordEncoder() {
    return new CustomPasswordEncoder();
  }

  @Bean
  public UserDetailsService customUserDetailsService() {
    return new UserDetailsServiceImpl(userRepository);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService()).passwordEncoder(customPasswordEncoder());
  }
}
