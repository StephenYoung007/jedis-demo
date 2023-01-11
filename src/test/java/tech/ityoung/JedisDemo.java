package tech.ityoung;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import tech.ityoung.config.JedisConnectionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisDemo {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        jedis = JedisConnectionFactory.getJedis();
        jedis.select(0);
    }

    @Test
    void testString() {
        String result = jedis.set("name", "stephen");
        System.out.println("result = " + result);

        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testMap() {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", "stephen");
        userMap.put("gendle", "male");
        String user = jedis.hmset("user:1", userMap);
        jedis.hset("user:1", "age", "28");
        jedis.hset("user:2", "age", "28");
        System.out.println("user = " + user);
        List<String> fields = jedis.hmget("user:1", "name", "gendle", "age");
        System.out.println("fields = " + fields);
        Map<String, String> userMapResult = jedis.hgetAll("user:1");
        System.out.println("userMapResult = " + userMapResult);
    }

    @Test
    void testJedisPool() {
        String result = jedis.set("name", "stephen");
        System.out.println("result = " + result);


        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
