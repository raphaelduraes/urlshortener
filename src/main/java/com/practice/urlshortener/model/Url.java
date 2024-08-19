package com.practice.urlshortener.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "url", timeToLive = 60L)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Url{
    @Id
    private String base64Hash;

    private String originalUrl;
}
