package com.ozayduman.casclient.conf;

import com.ozayduman.casclient.repository.Citizen;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
/**
 * Created by ozayd on 14.09.2017.
 */
@EnableRedisHttpSession
@Configuration
//@EnableRedisRepositories
public class SessionConfig {
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    /*
     * For Redis Repository operations
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.setConnectionFactory(connectionFactory());
        return template;
    }
}

