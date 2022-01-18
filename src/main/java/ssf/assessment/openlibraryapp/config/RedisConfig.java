package ssf.assessment.openlibraryapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class RedisConfig {

    public static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.database}")
    private Integer redisDatabase;

    private final String redisPassword;
    public RedisConfig(){
        redisPassword = System.getenv("Redis_password");
    }
    @Bean("book_cache")
    public RedisTemplate<String, String> createRedisTemplate(){

        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setDatabase(redisDatabase);
        config.setHostName(redisHost);
        config.setPort(redisPort);
        if(null !=redisPassword){
            config.setPassword(redisPassword);
            logger.info("Setting paswword for redis");
        }        
        final JedisClientConfiguration jedisClient = JedisClientConfiguration
                .builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(
                    config, jedisClient);
        jedisFac.afterPropertiesSet();
        
        final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;

    }

    
}

