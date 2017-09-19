package com.ozayduman.casclient;

import com.ozayduman.casclient.conf.WebApplicationContextConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class, SecurityAutoConfiguration.class,
		DispatcherServletAutoConfiguration.class, WebMvcAutoConfiguration.class })
public class CasClientApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebApplicationContextConfiguration.class).web(true)
				.run(args);
	}
}
