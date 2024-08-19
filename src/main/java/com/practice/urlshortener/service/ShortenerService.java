package com.practice.urlshortener.service;

import com.practice.urlshortener.exception.UrlNotFoundException;
import com.practice.urlshortener.model.Url;
import com.practice.urlshortener.repository.UrlRepository;
import com.practice.urlshortener.utils.HashingUtils;
import org.apache.commons.validator.routines.UrlValidator;
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

    @Autowired
    private UrlValidator urlValidator;

    private static final Logger logger = LoggerFactory.getLogger(ShortenerService.class);

    public String getOriginalUrl(String hash) throws UrlNotFoundException {
        Url url = urlRepository.findById(hash).orElseThrow(UrlNotFoundException::new);

        return url.getOriginalUrl();
    }

    public String shortUrl(String originalUrl) throws RuntimeException{
        try {
            validateUrl(originalUrl);
            String urlSecrecy = HashingUtils.getSHA256EncodedValueOf(originalUrl);
            urlRepository.save(
                    new Url(urlSecrecy, originalUrl)
            );

            return env.getProperty("domain.name") + "/" + urlSecrecy;
        } catch(NoSuchAlgorithmException exception) {
            logger.error("Failed to generate SHA256 hash", exception);
            throw new RuntimeException();
        }
    }

    private void validateUrl(String url) {
        if (url.isEmpty() || !urlValidator.isValid(url)) {
            throw new RuntimeException();
        }
    }
}
