package com.ozayduman.casclient;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CsrfFilter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${server.port}")
    private String serverPort;

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService("http://localhost:"+serverPort+"/a/login/cas");
        return serviceProperties;
    }

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider());
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
        return casAuthenticationProvider;
    }

    @Bean
    public AuthenticationUserDetailsService authenticationUserDetailsService() {
        return new UserDetailsByNameServiceWrapper(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                return new User("ozay", "ozay", authorities);
            }
        });
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator("http://localhost:8080/cas/");
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
       /* casAuthenticationFilter.setSessionAuthenticationStrategy(sessionStrategy());
        SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        savedRequestAwareAuthenticationSuccessHandler.setDefaultTargetUrl("/");
        casAuthenticationFilter.setAuthenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler);*/
        return casAuthenticationFilter;
    }

   /* @Bean
    public SessionAuthenticationStrategy sessionStrategy() {
        SessionAuthenticationStrategy sessionStrategy = new SessionFixationProtectionStrategy();
        return sessionStrategy;
    }*/

    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl("http://localhost:8080/cas/login");
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .exceptionHandling()
               .authenticationEntryPoint(casAuthenticationEntryPoint()).and().addFilter(casAuthenticationFilter())
               //.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
               //.addFilterBefore(casLogoutFilter(), LogoutFilter.class) //SLO  ??
               .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class); //SLO
       http
               .authorizeRequests()
               .antMatchers("/login/**","/service/greeting2")
               .permitAll()
               .and()
               .formLogin().permitAll().and()
               .authorizeRequests().anyRequest().authenticated();
       http.csrf().disable();
       /**
        * <logout invalidate-session="true" delete-cookies="JSESSIONID" />
        */
       /*http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
               .deleteCookies("JSESSIONID");*/
   }

   /* @Bean
    public LogoutFilter casLogoutFilter() {
        return new LogoutFilter("http://localhost:8080/cas/logout", casLogoutHandler());
    }

    @Bean
    public SecurityContextLogoutHandler casLogoutHandler() {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.setClearAuthentication(true);
        logoutHandler.setInvalidateHttpSession(true);
        return logoutHandler;
    }*/

    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix("cas");
        return singleSignOutFilter;
    }
/*
    @Bean
    public LogoutFilter requestLogoutFilter() {
        SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
        handler.setClearAuthentication(true);
        handler.setInvalidateHttpSession(true);
        LogoutFilter logoutFilter = new LogoutFilter("http://localhost:8080/cas", handler);
        return logoutFilter;
    }*/



  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider());
    }*/
}
