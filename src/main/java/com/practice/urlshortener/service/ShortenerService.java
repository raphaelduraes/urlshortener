package com.practice.urlshortener.service;

import com.practice.urlshortener.exception.UrlNotFoundException;
import com.practice.urlshortener.model.Url;
import com.practice.urlshortener.repository.UrlRepository;
import com.practice.urlshortener.utils.HashingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
@Service
public class ShortenerService {

    @Autowired
    private Environment env;

    @Autowired
    private UrlRepository urlRepository;

    private static final Logger logger = LoggerFactory.getLogger(ShortenerService.class);

    public String getOriginalUrl(String hash) {
        return urlRepository.findByBase64Hash(hash)
                .orElseThrow(UrlNotFoundException::new).getOriginalUrl();
    }

    public String shortUrl(String originalUrl) throws RuntimeException{
        try {
            //generate hash
            byte[] urlHash = HashingUtils.getMD5ValueOf(originalUrl);
            //encode it with Base64 algorithm to be used as part of the url
            String urlSecrecy = HashingUtils.getBase64EncodedValueOf(urlHash);
            //save on redis using the secrecy as id
            urlRepository.save(new Url(null, urlSecrecy, originalUrl));

            return env.getProperty("domain.name") + urlSecrecy;
        } catch(NoSuchAlgorithmException exception) {
            logger.error("Failed to generate MD5 hash", exception);
            throw new RuntimeException();
        }
    }
}
