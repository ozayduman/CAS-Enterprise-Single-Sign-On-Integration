package com.ozayduman.casclient.conf;

import com.ozayduman.casclient.repository.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePool;
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
    // There is no need for these statements beacause RedisAutoConfiguration handles everyting
    /*@Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration ()
                .master("mymaster")
                .sentinel("mgm-07",26379)
                .sentinel("mgm-15",26379)
                .sentinel("mgm-18",26379);
        return new LettuceConnectionFactory(sentinelConfig);
    }*/

    /*@Bean
    public RedisConnectionFactory connectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration ()
                .master("mymaster")
                .sentinel("mgm-07",26379)
                .sentinel("mgm-15",26379)
                .sentinel("mgm-18",26379);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig);
        jedisConnectionFactory.setPassword("ozayduman");
        return jedisConnectionFactory;
    }*/

    /*
     * For Redis Repository Operations
     */
    /*@Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.setConnectionFactory(connectionFactory());
        return template;
    }*/
}

