package tech.ityoung;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import tech.ityoung.model.User;

import java.util.Set;

@SpringBootTest
public class StephenApplicationTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testTemplate() {
/*        redisTemplate.opsForValue().set("name", "wang yang");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);*/
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);
        keys.forEach(key -> {
            redisTemplate.delete(key);
        });
    }

    @Test
    void testAutoObjectDesirializer() {
        User user = new User("stephen", 28);
        /**
         * 自动序列化反序列化
         */
        redisTemplate.opsForValue().set("user", user);
        User users = (User) redisTemplate.opsForValue().get("user");
        System.out.println("user = " + users);
    }

    @Test
    void testManulObjectDesirializer() throws JsonProcessingException {
        User user = new User("stephen", 28);
        /**
         * 自动序列化反序列化
         */
        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user", valueAsString);
        String userString = stringRedisTemplate.opsForValue().get("user");
        User readValue = objectMapper.readValue(userString, User.class);
        System.out.println("user = " + readValue);
    }
}
