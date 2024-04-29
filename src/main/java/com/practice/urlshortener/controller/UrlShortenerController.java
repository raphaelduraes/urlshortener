package com.practice.urlshortener.controller;

import com.practice.urlshortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    @Autowired
    private ShortenerService shortenerService;

    @GetMapping("/{hash}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String hash) {
        return new ResponseEntity<>(shortenerService.getOriginalUrl(hash), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> getShortUrl(String originalUrl) {
        return new ResponseEntity<>(shortenerService.shortUrl(originalUrl), HttpStatus.OK);
    }
}
