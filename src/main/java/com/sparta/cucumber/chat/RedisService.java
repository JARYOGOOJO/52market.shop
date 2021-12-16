package com.sparta.cucumber.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public String getRedisStringValue(String key){
        ValueOperations<String,String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println("Redis key : "+key);
        System.out.println("Redis value : "+stringStringValueOperations.get(key));
        return stringStringValueOperations.get(key);
    }

    public void setRedisStringValue(String key, String value){
        ValueOperations<String,String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(key,value);
        System.out.println("Redis key : "+key);
        System.out.println("Redis value : "+stringValueOperations.get(key));
    }
}
