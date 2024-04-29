package com.practice.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(timeToLive = 60L)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Url implements Serializable {
    @Id
    private Long id;
    private String base64Hash;
    private String originalUrl;
}
