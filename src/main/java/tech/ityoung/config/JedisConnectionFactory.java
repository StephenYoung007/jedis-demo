package tech.ityoung.config;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class JedisConnectionFactory {
    private static final JedisPool JEDIS_POOL;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxWait(Duration.of(100L, TimeUnit.MICROSECONDS.toChronoUnit()));
        JEDIS_POOL = new JedisPool(jedisPoolConfig, "192.168.31.11", 6379);
    }

    public static Jedis getJedis() {
        return JEDIS_POOL.getResource();
    }
}
