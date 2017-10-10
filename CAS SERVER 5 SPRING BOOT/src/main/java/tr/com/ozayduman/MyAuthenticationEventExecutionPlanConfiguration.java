package tr.com.ozayduman;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apereo.cas.configuration.*;
/**
 * Created by ozayd on 3.10.2017.
 */
@Configuration("MyAuthenticationEventExecutionPlanConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class MyAuthenticationEventExecutionPlanConfiguration implements AuthenticationEventExecutionPlanConfigurer {
    @Autowired
    private CasConfigurationProperties casProperties;
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Bean
    @RefreshScope
    public AuthenticationHandler myAuthenticationHandler() {
        final MyAuthenticationHandler handler = new MyAuthenticationHandler("MyAuthenticationHandler",servicesManager,authMyPrincipalFactory(),1);
        /*
            Configure the handler by invoking various setter methods.
            Note that you also have full access to the collection of resolved CAS settings.
            Note that each authentication handler may optionally qualify for an 'order`
            as well as a unique name.
        */
        return handler;
    }
    @RefreshScope
    @Bean
    public PrincipalFactory authMyPrincipalFactory() {
        return new DefaultPrincipalFactory();
    }

    @Override
    public void configureAuthenticationExecutionPlan(final AuthenticationEventExecutionPlan plan) {
         plan.registerAuthenticationHandler(myAuthenticationHandler());
    }
}