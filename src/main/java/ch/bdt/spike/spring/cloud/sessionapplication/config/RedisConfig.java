package ch.bdt.spike.spring.cloud.sessionapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper mapper) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        //template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJacksonJsonRedisSerializer(mapper));
        //template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
