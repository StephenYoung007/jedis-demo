package tech.ityoung;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testTemplate() {
        redisTemplate.opsForValue().set("name", "wang yang");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }
}
